package com.nbdev.startexgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class BaseScreen implements Screen, InputProcessor {
    public static final int V_WIDTH = 1080;
    public static final int V_HEIGHT = 1920;

    private static OrthographicCamera camera = new OrthographicCamera();
    protected SpriteBatch batch;

    private Vector3 coord3d = new Vector3();
    private Vector2 coord2d = new Vector2();


    @Override
    public void show() {
        camera.setToOrtho(false, V_WIDTH, V_HEIGHT);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

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
        batch.dispose();
    }

    // Input
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        unprojectCoord(coord2d, screenX, screenY);
        touchDown(coord2d, pointer, button);
        return false;
    }

    public boolean touchDown(Vector2 coord2d, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        unprojectCoord(coord2d, screenX, screenY);
        touchUp(coord2d, pointer, button);
        return false;
    }

    public boolean touchUp(Vector2 coord2d, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        unprojectCoord(coord2d, screenX, screenY);
        touchDragged(coord2d, pointer);
        return false;
    }

    public boolean touchDragged(Vector2 coord2d, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        unprojectCoord(coord2d, screenX, screenY);
        mouseMoved(coord2d);
        return false;
    }

    public boolean mouseMoved(Vector2 coord2d) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private void unprojectCoord(Vector2 coord2d, int screenX, int screenY) {
        coord3d.set(screenX, screenY, 0);
        camera.unproject(coord3d);
        coord2d.set(coord3d.x, coord3d.y);
    }
}
