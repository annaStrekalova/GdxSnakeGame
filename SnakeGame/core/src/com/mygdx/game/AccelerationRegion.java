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
public class AccelerationRegion extends SpeedRegion{

    public AccelerationRegion() {
        this.objectTexture = new Texture("acceleration_region.jpg");
        this.objectSprite = new Sprite(this.objectTexture);
        this.effect = 2;
        this.timeOfAction = 100;
        createObjectRectangle();
    }

    @Override
    public void createObjectRectangle(){
        this.objectRectangle = new Rectangle();
        this.objectRectangle.setWidth(80);
        this.objectRectangle.setHeight(40);
    }
    
    @Override
    public int getTimeOfAction(){
        return this.timeOfAction;
    }
    
    @Override
    public int getRegionEffect(){
        return this.effect;
    }
}
