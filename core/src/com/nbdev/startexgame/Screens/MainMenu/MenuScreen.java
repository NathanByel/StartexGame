package com.nbdev.startexgame.Screens.MainMenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Button;
import com.nbdev.startexgame.Screens.CurrentScreen;
import com.nbdev.startexgame.Screens.GameScreen;
import com.nbdev.startexgame.StartexGame;

public class MenuScreen implements CurrentScreen {
    private CurrentScreen THIS;
    private Texture background;
    private TextureAtlas textureAtlas;
    private Button buttonPlay;
    private Button buttonQuit;

    public MenuScreen() {
        THIS = this;
        background = new Texture("background.jpg");
        textureAtlas = new TextureAtlas("menu/menu.pack");
        buttonPlay = new Button(new Vector2(100, 200), textureAtlas.findRegion("play")) {
            @Override
            public void buttonDown() {

            }

            @Override
            public void buttonUp() {
                THIS.dispose();
                new GameScreen();
            }
        };

        buttonQuit = new Button(new Vector2(730, 200), textureAtlas.findRegion("quit")) {
            @Override
            public void buttonDown() {

            }

            @Override
            public void buttonUp() {
                System.exit(0);
            }
        };

        StartexGame.setScreen(this);
        new MenuController(buttonPlay, buttonQuit);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0);
        buttonPlay.render(batch);
        buttonQuit.render(batch);
    }

    @Override
    public void dispose() {
        background.dispose();
        textureAtlas.dispose();
    }
}
