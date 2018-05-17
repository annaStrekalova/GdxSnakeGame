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
abstract public class SpeedRegion extends GameObject{
    
    protected int effect = 0;//эффект объекта
    protected int timeOfAction = 0;//время действия
     
    public abstract int getTimeOfAction();
    public abstract int getRegionEffect();
}
