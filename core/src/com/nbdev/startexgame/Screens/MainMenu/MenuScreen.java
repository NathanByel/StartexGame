package com.nbdev.startexgame.Screens.MainMenu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.Screens.GameScreen.GameScreen;
import com.nbdev.startexgame.Buttons.ButtonPlay;
import com.nbdev.startexgame.Buttons.ButtonQuit;

public class MenuScreen extends BaseScreen {
    private final Game game;
    private Texture background;
    private TextureAtlas textureAtlas;
    private ButtonPlay buttonPlay;
    private ButtonQuit buttonQuit;

    public MenuScreen(final Game game) {
        this.game = game;
        background = new Texture("background.jpg");
        textureAtlas = new TextureAtlas("menu/menu.tpack");
        buttonPlay = new ButtonPlay(new Vector2(100, 200), textureAtlas) {
            @Override
            public void buttonUp() {
                game.setScreen(new GameScreen(game));
            }
        };

        buttonQuit = new ButtonQuit(new Vector2(730, 200), textureAtlas) {
            @Override
            public void buttonUp() {
                Gdx.app.exit();
            }
        };
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        buttonPlay.render(batch);
        buttonQuit.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        textureAtlas.dispose();
    }


    @Override
    public boolean touchDown(Vector2 coord2d, int pointer, int button) {
        buttonPlay.touchDown(coord2d, pointer, button);
        buttonQuit.touchDown(coord2d, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 coord2d, int pointer, int button) {
        buttonPlay.touchUp(coord2d, pointer, button);
        buttonQuit.touchUp(coord2d, pointer, button);
        return false;
    }
}
