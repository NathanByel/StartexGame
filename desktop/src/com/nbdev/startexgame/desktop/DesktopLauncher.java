package com.nbdev.startexgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nbdev.startexgame.StartexGame;

public class DesktopLauncher {
	public static final float DESKTOP_SCALE = 2.5f;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int)(StartexGame.V_WIDTH / DESKTOP_SCALE);
		config.height = (int)(StartexGame.V_HEIGHT / DESKTOP_SCALE);
		config.resizable = false;
		new LwjglApplication(new StartexGame(), config);
	}
}
