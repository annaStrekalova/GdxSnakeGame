/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import snakeGame.model.events.ActionEvent;
import snakeGame.model.events.SnakeActionListener;

/**
 *
 * @author Анна
 */
public class Snake {
    
    private SnakeGame game;
    public Texture snakeHead, snakeBody, snakeTail;
    public Array<Sprite> snakeParts;
    public Rectangle headRectangle;//прямоугольник змеи
    public Array<Rectangle> bodyRectangle;
    private ArrayList<Float> coordX = new ArrayList<Float>();
    private ArrayList<Float> coordY = new ArrayList<Float>();
    private int timeFrame = 0;//время кадра
    private int bodyCount = 0;
    public int moveDirection;//направление движения
    private int speed = 2;
    
    public Snake (){
        snakeParts = new Array<Sprite>();
        snakeHead = new Texture("snake_head1.png");
        snakeTail = new Texture("snake_tail1.png");
        snakeParts.add(new Sprite(snakeHead));
        snakeParts.add(new Sprite(snakeTail)); 
        snakeBody = new Texture("snake_body1.png");
        createRectangles();
    }
    
    /*Вернуть скорость*/
    public int getSpeed(){
        return this.speed;
    }
    
    /*Сделать ход*/
    public void move(){
        moveHead();
        timeFrame++;
        if(timeFrame>20){ 
            moveBody();
            makeMove();
        }
    }
   
    /*Сделать поворот всего тела*/
    private void makeTurnOfTheBody(int rotation){
        for(int i=0; i < snakeParts.size; i++){
            snakeParts.get(i).setRotation(rotation);
        }
    }
        
    /*Сдвинуть голову*/
    private void moveHead() { 
        switch (moveDirection) {
            case 0:
                snakeParts.get(0).setPosition(snakeParts.get(0).getX(), snakeParts.get(0).getY() + speed);
                makeTurnOfTheBody(0);
            break;
            case 1:
                snakeParts.get(0).setPosition(snakeParts.get(0).getX(), snakeParts.get(0).getY() - speed);
                makeTurnOfTheBody(180);
            break;
            case 2:
                snakeParts.get(0).setPosition(snakeParts.get(0).getX() - speed, snakeParts.get(0).getY());
                makeTurnOfTheBody(90);
            break;
            default:
                snakeParts.get(0).setPosition(snakeParts.get(0).getX() + speed, snakeParts.get(0).getY());
                makeTurnOfTheBody(-90);
            break;
        }
        coordX.add(snakeParts.get(0).getX());
        coordY.add(snakeParts.get(0).getY());
    }
    
    /*Сдвинуть тело*/
    private void moveBody() {
        for(int i=1; i < snakeParts.size; i++){ 
            bodyCount = coordX.size() - (40/this.speed) * i;
            snakeParts.get(i).setPosition(coordX.get(bodyCount), coordY.get(bodyCount));
        }
    }
    
    /*Создать прямоугольник змеи*/
    private void createRectangles(){
        this.headRectangle = new Rectangle();
        this.headRectangle.setWidth(40);
        this.headRectangle.setHeight(40);
        this.bodyRectangle = new Array<Rectangle>();
        this.bodyRectangle.add(new Rectangle().setSize(40, 40));
    }
    
    /*Съесть еду*/
    public void eatFood(Food food){
        if(food.isSpeedFood){
            if(!(this.speed <= 2 && food instanceof Meat))
                changeSpeed(food.getFoodEffect());
        }
        else{
            if(food instanceof Apple){//влияет на длину
                increaseInLength(food.getFoodEffect());
            }
            if(food instanceof Mushroom){
                if(snakeParts.size > 2)
                    cutLength(food.getFoodEffect());
            }
        }
    }
    
    /*Увеличить скорость*/
    public void changeSpeed(int value){
        int bufInt = this.speed;
        this.speed += value;
        if(this.speed > bufInt)
            changeSpeedPlus();
        else if(this.speed < bufInt)
            changeSpeedMinus();
    }
    
    /*Увеличение длины*/
    private void increaseInLength(int increment){
       // for(int i=0; i < increment; i++){//Добавить проверку, что змея прошла дост
            snakeParts.insert(snakeParts.size-1, new Sprite(snakeBody));
            bodyRectangle.insert(bodyRectangle.size-1, new Rectangle().setSize(40, 40));
        //}
    }
    
    /*Уменьшение длины*/
    private void cutLength(int increment){
        snakeParts.removeIndex(snakeParts.size-2);
        bodyRectangle.removeIndex(bodyRectangle.size-2);
    }
    
    ///////////////////// События ////////////////////////////////////////////
    private final ArrayList<SnakeActionListener> listeners = new ArrayList();
    
    private final ActionEvent _event = new ActionEvent(this);
        
    public void addActionListener(SnakeActionListener listener) {
        listeners.add(listener); 
    } 

    public void changeSpeedPlus() {
        for (SnakeActionListener listener : listeners)
            listener.changeSpeedPlus(_event); 
    }
    
    public void changeSpeedMinus() {
        for (SnakeActionListener listener : listeners)
            listener.changeSpeedMinus(_event); 
    }
        
    public void makeMove() {
        for (SnakeActionListener listener : listeners)
            listener.makeMove(_event);
    }
} 

    

        

