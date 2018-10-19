package com.nbdev.startexgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nbdev.startexgame.StartexGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)(StartexGame.V_WIDTH / 2.5);
		config.height = (int)(StartexGame.V_HEIGHT / 2.5);
		config.resizable = false;
		new LwjglApplication(new StartexGame(), config);
	}
}
