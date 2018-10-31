package com.nbdev.startexgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public final class CatController implements InputProcessor {
    private Cat cat;
    private Vector2 direction = new Vector2(0,0);
    private Vector3 coord3d = new Vector3();
    private Vector2 coord2d = new Vector2();

    public CatController(Cat cat) {
        this.cat = cat;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.LEFT:   direction.x = -1;   break;
            case Input.Keys.RIGHT:  direction.x = 1;    break;
            case Input.Keys.UP:     direction.y = 1;    break;
            case Input.Keys.DOWN:   direction.y = -1;   break;
        }
        if(!direction.isZero()) {
            cat.moveDirection(direction);
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.LEFT:   direction.x = 0;    break;
            case Input.Keys.RIGHT:  direction.x = 0;    break;
            case Input.Keys.UP:     direction.y = 0;    break;
            case Input.Keys.DOWN:   direction.y = 0;    break;
        }

        if(direction.isZero()) {
            cat.stop();
        } else {
            cat.moveDirection(direction);
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        coord3d.set(screenX, screenY, 0);
        StartexGame.getCamera().unproject(coord3d);
        coord2d.set(coord3d.x, coord3d.y);

        cat.goTo(coord2d);
        return true;
    }


    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
