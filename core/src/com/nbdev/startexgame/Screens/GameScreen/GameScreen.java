package com.nbdev.startexgame.Screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.Cat;

public class GameScreen extends BaseScreen {
    private final Game game;
    private Texture background;
    private Cat cat;
    private Vector2 direction;

    public GameScreen(final Game game) {
        this.game = game;
        direction = new Vector2(0,0);
        background = new Texture("background.jpg");
        cat = new Cat();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        cat.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        cat = null;
        background.dispose();
    }

    // Control
    /*
    @Override
    public boolean mouseMoved(Vector2 coord2d) {
        cat.goTo(coord2d);
        return false;
    }
    */

    @Override
    public boolean touchDown(Vector2 coord2d, int pointer, int button) {
        cat.goTo(coord2d);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.A:
                direction.x = -1;
                break;

            case Input.Keys.RIGHT:
            case Input.Keys.D:
                direction.x = 1;
                break;

            case Input.Keys.UP:
            case Input.Keys.W:
                direction.y = 1;
                break;

            case Input.Keys.DOWN:
            case Input.Keys.S:
                direction.y = -1;
                break;
        }
        if(!direction.isZero()) {
            cat.moveDirection(direction);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.A:
                direction.x = 0;
                break;

            case Input.Keys.RIGHT:
            case Input.Keys.D:
                direction.x = 0;
                break;

            case Input.Keys.UP:
            case Input.Keys.W:
                direction.y = 0;
                break;

            case Input.Keys.DOWN:
            case Input.Keys.S:
                direction.y = 0;
                break;
        }

        if(direction.isZero()) {
            cat.stop();
        } else {
            cat.moveDirection(direction);
        }
        return false;
    }
}
