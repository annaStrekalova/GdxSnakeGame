/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 *
 * @author Анна
 */
abstract public class Food extends GameObject{
    
    protected int effect = 0;//эффект объекта 
    protected boolean isSpeedFood = false;
    protected boolean isEat = false;
    protected boolean hasVariablePos;
        
    public abstract int getFoodEffect();
}
