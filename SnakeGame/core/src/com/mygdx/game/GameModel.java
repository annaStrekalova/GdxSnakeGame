/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;

import java.util.EventObject;
import java.util.Random;
import snakeGame.model.events.FieldListener;
import snakeGame.model.events.SnakeActionListener;

/**
 *
 * @author Анна
 */
public class GameModel {

    private SnakeGame game;
    private GameField field;
    private int timeForDrawBarrier = 0;
    private float _randomX, _randomY;
    private boolean changePosBarrier = false, chagePosRegion = false;
    private int timeOfActionRegion = 0;
    private int timeRegion = 0, timeBeforeVarPos=90;
    private Array<GameObject> varObjects = new Array<GameObject>();
    
    public GameModel(SnakeGame game) {
        this.game = game;
        this.field = new GameField(game);
    }

    public void start(){
        generateGameObjects();
        this.varObjects = field.getObjectWithVariablePos();
        field.getSnake().addActionListener(new GameModel.SnakeListener());
        field.addFieldListener(new GameModel.CollisionListener());
    }  
    
    private void generateGameObjects(){
        field.addObject(new Apple());
        field.addObject(new Mushroom());        
        field.addObject(new Meat());
        field.addObject(new EnergyDrink());
        field.addObject(new Stone());
        field.addObject(new Stump());
        field.addObject(new AccelerationRegion());
        field.addObject(new DecelerationRegion());
    }
    
    /*Отсчет времени до отрисовки объекта*/
    public void countdownToRenderObject(GameObject obj){
       obj.timeBeforeRender--;
    }
    
    private int getRandomNumber(int num){
        Random rand = new Random();
        return rand.nextInt(num);
    }

    public void setPositionGameObject(){
        boolean flag = false;
        for(GameObject obj : field.getObjects()){
            boolean collision = true;
            //Если объект появляется первый раз на поле, или объект съедобный и он был съеден, или нужно изменить позицию барьеров
            if(obj.timeBeforeRender == -1 || obj instanceof Food && ((Food) obj).isEat == true || 
                                                   !(obj instanceof Food) && (flag || this.chagePosRegion)){
                if(!(obj instanceof Food)){
                    //Установить время timeBeforeRender для барьера
                   obj.timeBeforeRender = this.getRandomNumber(70);
                }
                //Если объект был съеден
                if(obj instanceof Food && ((Food) obj).isEat == true){
                    //Установить объекту рандомное время до начала рисования
                    ((Food) obj).timeBeforeRender = this.getRandomNumber(100);
                    //Изменить флаг чтобы в следующий render не генерировалась новая для него позиция
                    ((Food) obj).isEat = false;
                    flag = true;
                }
                //Если объект появляется первый раз
                if(obj.timeBeforeRender == -1)
                    //Установить его в 0 как уже существующий на поле
                    obj.timeBeforeRender = 0;
                while(collision){
                    //Генерация рандомной позиции
                    this._randomX = getRandomNumber(1000);
                    this._randomY = getRandomNumber(630);
                    obj.objectSprite.setPosition(this._randomX, this._randomY);
                    updatePositionRectangleGameObject(obj);
                    collision = field.checkCollisionsForObjectsInField(obj, this._randomX, this._randomY);
                }
            }
        }
        setVariablePosition();
        this.chagePosRegion = false;
    }
    
    void setVariablePosition(){
        float posX = 0, posY = 0;
        if(this.timeBeforeVarPos!=0)
            this.timeBeforeVarPos--;
        else{
            for(int i = 0; i < varObjects.size; i++){
                if(i==0){
                    posX = varObjects.get(i).objectSprite.getX();
                    posY = varObjects.get(i).objectSprite.getY();
                }
                if(i==varObjects.size-1){
                    varObjects.get(i).objectSprite.setX(posX);
                    varObjects.get(i).objectSprite.setY(posY);
                }
                else if(varObjects.get(i+1) != null){
                    varObjects.get(i).objectSprite.setX(varObjects.get(i+1).objectSprite.getX());
                    varObjects.get(i).objectSprite.setY(varObjects.get(i+1).objectSprite.getY());
                }
            }
            updatePositionRectangleAllObjects(varObjects);
            this.timeBeforeVarPos=90;
        }
    }
    
    /*Взаимодействие змеи с объектом*/
    private void interactSnakeWithObject(){
        GameObject obj;
        obj = field.overlapsSnakeWithGameObject();
        if(obj != null){
            //Если объект является едой
            if(obj instanceof Food){
                //Если змея съела объект
                field.getSnake().eatFood((Food) obj);
                ((Food) obj).isEat = true;
            }
            else if(obj instanceof SpeedRegion){
                this.timeOfActionRegion = ((SpeedRegion) obj).getTimeOfAction();
                field.getSnake().changeSpeed(((SpeedRegion) obj).getRegionEffect());
                this.timeRegion = ((SpeedRegion) obj).getRegionEffect();
                chagePosRegion = true;
                if(field.getSnake().getSpeed() == 0)
                    identifyGameOver();
            }
           else
                identifyGameOver();
        }
    }
    
    private void identifyGameOver(){
        game.setScreen(new GameOverScreen(game));
    }
    
    public GameField field(){
        return this.field;
    }
    
    private void updatePositionRectangleGameObject(GameObject obj){
        obj.objectRectangle.x = obj.objectSprite.getX();
        obj.objectRectangle.y = obj.objectSprite.getY();
    }
    
    private void updatePositionRectangleAllObjects(Array<GameObject> allObjects){
        for(GameObject obj : allObjects){
            obj.objectRectangle.x = obj.objectSprite.getX();
            obj.objectRectangle.y = obj.objectSprite.getY();
        }
    }
        
    /*Обновление позиций прямоугольников соответствующих каждому объекту*/
    private void updatePositionRectanglesSnake(){
        field.getSnake().headRectangle.x = field.getSnake().snakeParts.get(0).getX();
        field.getSnake().headRectangle.y = field.getSnake().snakeParts.get(0).getY();
        for(int i = 0; i < field.getSnake().bodyRectangle.size; i++){
            field.getSnake().bodyRectangle.get(i).x = field.getSnake().snakeParts.get(i+1).getX();
            field.getSnake().bodyRectangle.get(i).y = field.getSnake().snakeParts.get(i+1).getY();
        }
    }
    
    ///////////////////////////
    private class SnakeListener implements SnakeActionListener {

        @Override
        public void makeMove(EventObject e) {
            updatePositionRectanglesSnake();
            interactSnakeWithObject();
            if(timeRegion != 0){ 
                if(timeOfActionRegion > 0)
                    timeOfActionRegion--;
                else{
                    field.getSnake().changeSpeed(-timeRegion);
                    timeRegion = 0;
                } 
            }
        }

        @Override
        public void changeSpeedPlus(EventObject e) {
      
        }

        @Override
        public void changeSpeedMinus(EventObject e) {
           
        }
    }
        
    private class CollisionListener implements FieldListener {

        @Override
        public void collision(EventObject e) {
            identifyGameOver();
        }
    }
}
