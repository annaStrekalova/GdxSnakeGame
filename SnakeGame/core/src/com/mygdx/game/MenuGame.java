/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 *
 * @author Анна
 */
public class MenuGame implements Screen{

    private SnakeGame game;
    private Texture background, startBtn, exitBtn, startBtnClick, exitBtnClick, descriptionBtn;
    private Music menuMusic;
    private  int timeFrame = 0;
    private OrthographicCamera camera;//OrthographicCamera, PerspectiveCamera
    BitmapFont debug;
    
    private float width = Gdx.graphics.getWidth();
    private float height = Gdx.graphics.getHeight();
    private final float appWidth = 1350;
    private final float appHeight = 690;
    
    public MenuGame(SnakeGame game){
        this.game = game;
        
        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("fastsong.mp3"));
        menuMusic.setLooping(true);
        menuMusic.play();
        
        camera = new OrthographicCamera(width, height);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.setToOrtho(false, appWidth, appHeight);
        camera.update();
    }
    
    private void mousePressed(){
        if (Gdx.input.isTouched()) {
            int resultY = 690-260;
            int resultYExit = 690-160;
           if(Gdx.input.getX() >= 150 && Gdx.input.getX() <= 150+220 && Gdx.input.getY() >= resultY-65 && Gdx.input.getY() <= resultY){
                game.batch.draw(startBtnClick, 150, 260);
                menuMusic.stop();
                game.setScreen(new GamePanel(game));
            }
            else if(Gdx.input.getX() >= 150 && Gdx.input.getX() <= 150+222 && Gdx.input.getY() >= resultYExit-65 && Gdx.input.getY() <= resultYExit){
                game.batch.draw(exitBtnClick, 150, 160);
                Gdx.app.exit();
           }
        }
    }
    
    private void drawMenuElement(){
        setBackgroundTexture();
        game.batch.draw(this.background, 0, 0);//Рисует прямоугольник с нижним левым углом в x, y, имеющим ширину и высоту текстуры
        setButtonsTexture();
        game.batch.draw(this.startBtn, 150, 260);
        game.batch.draw(this.exitBtn, 150, 160);
    }
   
    private void setButtonsTexture(){
        this.startBtn = new Texture("menu/start_btn.png");
        this.startBtnClick = new Texture("menu/start_btn_click.png");
        this.exitBtn = new Texture("menu/exit_btn.png");
        this.exitBtnClick = new Texture("menu/exit_btn_click.png");
    }
    
    private void setBackgroundTexture(){
        if(this.timeFrame < 5)
            this.background = new Texture("menu/background.png");
        else if(this.timeFrame > 4 && this.timeFrame < 10)
            this.background = new Texture("menu/background_green.png");
        else
            this.background = new Texture("menu/background_white.png");
        this.timeFrame++;
        if(this.timeFrame == 15)
            this.timeFrame = 0;
    }
    
    @Override
    public void show() {
       
    }

    @Override
    public void render(float delta) {
        camera.update();//Пересчитывает матрицу проекции и просмотра этой камеры и плоскости Фрустума.
        game.batch.setProjectionMatrix(camera.combined);//Устанавливает матрицу проецирования, которая будет использоваться этим пакетом
        
        game.batch.begin();//Устанавливает пакет для рисования
        drawMenuElement();
        mousePressed();
        game.batch.end();
        dispose();
    }

    @Override
    public void dispose() {
        this.background.dispose();
        this.startBtn.dispose();
        this.exitBtn.dispose();
        this.startBtnClick.dispose();
        this.exitBtnClick.dispose();
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
    
}
