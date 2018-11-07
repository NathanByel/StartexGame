package com.nbdev.startexgame.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.Cat;
import com.nbdev.startexgame.CatController;

public class GameScreen extends BaseScreen {
    private final Game game;
    private Texture background;
    private Cat cat;

    public GameScreen(final Game game) {
        this.game = game;
        background = new Texture("background.jpg");
        cat = new Cat();
        new CatController(cat);
    }

    @Override
    public void render(float delta) {
        batch.draw(background, 0, 0);
        cat.render(batch);
    }

    @Override
    public void dispose() {
        cat = null;
        background.dispose();
    }
}
