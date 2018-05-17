/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author Анна
 */
public class GameOverScreen implements Screen{
    
    SnakeGame game;
    BitmapFont info;
    Music menuMusic;
    Texture background;
    
    public GameOverScreen(SnakeGame game){
        this.game = game;
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("Game_Over.mp3"));
        menuMusic.play();
        background = new Texture("game_over.png");
    }
    
    @Override
    public void show() {
        info = new BitmapFont();
        info.setColor(Color.WHITE);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        
        game.batch.draw(background, 0, 0);
        info.draw(game.batch, "Press any key to back menu.", 565, 50);

        game.batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            menuMusic.stop();
            game.setScreen(new MenuGame(game));
        }
    }

    @Override
    public void resize(int width, int height) {

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
