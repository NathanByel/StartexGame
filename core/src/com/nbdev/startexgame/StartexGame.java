package com.nbdev.startexgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartexGame extends ApplicationAdapter {
	public static final int V_WIDTH = 1080;
	public static final int V_HEIGHT = 1920;

    private SpriteBatch batch;
    private Texture background;

    private Cat cat;

	@Override
	public void create () {
		OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, V_WIDTH, V_HEIGHT);

		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);

		background = new Texture("background.jpg");
        cat = new Cat();
        new CatController(cat, camera);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		cat.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
