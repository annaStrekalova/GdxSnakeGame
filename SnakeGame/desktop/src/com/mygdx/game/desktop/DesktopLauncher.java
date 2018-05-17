package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.SnakeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "SNAKE";
            config.addIcon("menu/icon.png", Files.FileType.Internal);
            config.width = 1350;
            config.height = 683;
            config.resizable = false;
            new LwjglApplication(new SnakeGame(), config);
	}
}
