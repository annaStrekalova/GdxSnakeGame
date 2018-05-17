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
public abstract class GameObject{
    protected Texture objectTexture;        //текстура объекта
    protected Sprite objectSprite;          //спрайт объекта
    protected Rectangle objectRectangle;    //прямоугольник объекта
    protected int timeBeforeRender = -1;     //время до отрисовки спрайта
    
    public abstract void createObjectRectangle();

    boolean getEffect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
