package com.nbdev.startexgame.Screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nbdev.startexgame.Cat;
import com.nbdev.startexgame.CatController;
import com.nbdev.startexgame.StartexGame;

public class GameScreen extends StartexGame implements CurrentScreen {
    private Texture background;
    private Cat cat;

    public GameScreen() {
        background = new Texture("background.jpg");
        cat = new Cat();
        new CatController(cat);
        StartexGame.setScreen(this);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(background, 0, 0);
        cat.render(batch);
    }

    @Override
    public void dispose() {
        cat = null;
        background.dispose();
    }
}
