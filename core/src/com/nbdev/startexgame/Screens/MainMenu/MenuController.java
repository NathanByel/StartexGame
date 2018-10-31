package com.nbdev.startexgame.Screens.MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.nbdev.startexgame.Button;
import com.nbdev.startexgame.StartexGame;

import java.util.Arrays;
import java.util.List;


public class MenuController implements InputProcessor {
    private Vector3 coord3d = new Vector3();
    private Vector2 coord2d = new Vector2();
    private List<Button> buttons;

    public MenuController(Button ... buttons) {
        this.buttons = Arrays.asList(buttons);
        Gdx.input.setInputProcessor(this);
    }

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
        coord3d.set(screenX, screenY, 0);
        StartexGame.getCamera().unproject(coord3d);
        coord2d.set(coord3d.x, coord3d.y);

        for (Button btn : buttons) {
            if(btn.isButton(coord2d)) {
                btn.buttonDown();
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        coord3d.set(screenX, screenY, 0);
        StartexGame.getCamera().unproject(coord3d);
        coord2d.set(coord3d.x, coord3d.y);

        for (Button btn : buttons) {
            if(btn.isButton(coord2d)) {
                btn.buttonUp();
            }
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        coord3d.set(screenX, screenY, 0);
        StartexGame.getCamera().unproject(coord3d);
        coord2d.set(coord3d.x, coord3d.y);

        for (Button btn : buttons) {
            if(btn.isButton(coord2d)) {
                btn.mouseIn();
            } else {
                btn.mouseOut();
            }
        }

        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
