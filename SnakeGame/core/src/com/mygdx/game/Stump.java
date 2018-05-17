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
public class Stump extends GameObject{
    
    public Stump() {
        this.objectTexture = new Texture("stump.png");
        this.objectSprite = new Sprite(this.objectTexture);
        createObjectRectangle();
    }

    @Override
    public void createObjectRectangle(){
        this.objectRectangle = new Rectangle();
        this.objectRectangle.setWidth(52);
        this.objectRectangle.setHeight(36);
    }
}
