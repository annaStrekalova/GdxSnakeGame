package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SnakeGame extends Game {
    
    SpriteBatch batch;//текстуры и координаты для рисования прямоугольника
    Texture img;
    GameModel _model;
    
    @Override
    public void create () {
        this.batch = new SpriteBatch();
        _model = new GameModel(this);
        this.setScreen(new MenuGame(this));
    }
     
    /*Обновление логики игры*/
    @Override
    public void render () {
        super.render();
    }
}
