/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Анна
 */
public class Meat extends Food{

    public Meat() {
        this.objectTexture = new Texture("meat.png");
        this.objectSprite = new Sprite(this.objectTexture);
        this.effect = -1;
        this.isSpeedFood = true;
        createObjectRectangle();
    }

    @Override
    public void createObjectRectangle(){
        this.objectRectangle = new Rectangle();
        this.objectRectangle.setWidth(48);
        this.objectRectangle.setHeight(51);
    }
    
    @Override
    public int getFoodEffect() {
        return this.effect;
    }
}
