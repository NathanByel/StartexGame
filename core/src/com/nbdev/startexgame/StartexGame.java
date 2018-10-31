package com.nbdev.startexgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nbdev.startexgame.Screens.CurrentScreen;
import com.nbdev.startexgame.Screens.MainMenu.MenuScreen;

public class StartexGame extends Game {
	public static final int V_WIDTH = 1080;
	public static final int V_HEIGHT = 1920;

	private static CurrentScreen currentScreen;
	private static OrthographicCamera camera = new OrthographicCamera();
    private SpriteBatch batch;

	@Override
	public void create () {
        camera.setToOrtho(false, V_WIDTH, V_HEIGHT);

		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);

		new MenuScreen();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		currentScreen.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		currentScreen.dispose();
	}

	public static void setScreen(CurrentScreen screen) {
		currentScreen = screen;
	}

	public static OrthographicCamera getCamera() {
		return camera;
	}
}
