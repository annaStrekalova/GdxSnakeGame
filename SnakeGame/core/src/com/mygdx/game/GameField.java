/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import snakeGame.model.events.ActionEvent;
import snakeGame.model.events.FieldListener;

/**
 *
 * @author Анна
 */
public class GameField implements Screen{
    
    private Array<GameObject> objects = new Array<GameObject>();
        
    private final SnakeGame game;
    private final Texture grassTexture;
    private Sprite grassSpr;
    private Snake _snake;
    private Array<GameObject> varObjects;
    
    public GameField(SnakeGame game){
        this.game = game;
        _snake = new Snake();
        this.grassTexture = new Texture("grass.jpg");
        this.grassSpr = new Sprite(this.grassTexture);
    }
    
    /*Опеределение */
    public GameObject overlapsSnakeWithGameObject(){
        for(GameObject obj : this.objects){
            if(getSnake().headRectangle.overlaps(obj.objectRectangle))
                return obj;
        }
        return null;
    }
    
    public Sprite getSprite(){
        return this.grassSpr;
    }
    
    public void addObject(GameObject obj){
        objects.add(obj);
    }
    
    public Array<GameObject> getObjects(){
        return this.objects;
    }
    
    public Array<GameObject> getObjectWithVariablePos(){
        this.varObjects = new Array<GameObject>();
        for(GameObject obj : getObjects()){
            if(obj instanceof Food){
                if(((Food) obj).hasVariablePos)
                    this.varObjects.add(obj);
            }
        }
        return this.varObjects;
    }
    
    public Snake getSnake(){
        return this._snake;
    }
    
    public void collisionWithEdges() {
        if (getSnake().snakeParts.get(0).getX() <= -1 || getSnake().snakeParts.get(0).getX() >= 1348 ||
                getSnake().snakeParts.get(0).getY() <= -1 || getSnake().snakeParts.get(0).getY() >= 680) {
            collision();
        }
    }
    
    public boolean checkCollisionsForObjectsInField(GameObject obj, float posX, float posY){
        for(int i=0; i < objects.size; i++){
            if(!(objects.get(i).equals(obj))){
                 if(objects.get(i).objectRectangle.overlaps(obj.objectRectangle)){    
                     return true;
                }
            }
        }
        if((getSnake().snakeParts.get(0).getX() == posX && (getSnake().snakeParts.get(0).getY() == posY ||
              getSnake().snakeParts.get(0).getY() == posY+40 || getSnake().snakeParts.get(0).getY() == posY-40)) ||
          (getSnake().snakeParts.get(0).getY() == posY && (getSnake().snakeParts.get(0).getX() == posX ||
               getSnake().snakeParts.get(0).getX() == posX+40 || getSnake().snakeParts.get(0).getX() == posX-10))){
           return true;
       }
       return false;
    }

    /////////////////////// События  ////////////////////////////////////////
    private ArrayList<FieldListener> listeners = new ArrayList();
    
    private ActionEvent _event = new ActionEvent(this);
        
    public void addFieldListener(FieldListener listener) {
        listeners.add(listener); 
    } 
    
    public void collision() {
        for (FieldListener listener : listeners)
            listener.collision(_event);
    }
    
    @Override
    public void show() {

    }

    @Override
    public void render(float f) {

    }

    @Override
    public void resize(int i, int i1) {
        
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {

    }

}
