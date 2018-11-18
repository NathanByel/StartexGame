package com.nbdev.startexgame.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Assets.MenuAssets;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.Buttons.ButtonPlay;
import com.nbdev.startexgame.Buttons.ButtonQuit;

public class MenuScreen extends BaseScreen {
    private final Game game;
    private MenuAssets menuAssets;

    private ButtonPlay buttonPlay;
    private ButtonQuit buttonQuit;

    public MenuScreen(final Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        menuAssets = new MenuAssets();
        menuAssets.load();
        menuAssets.finishLoading(); // Ожидание загрузки ресурсов

        buttonPlay = new ButtonPlay(new Vector2(300, 200), menuAssets.get(MenuAssets.textureAtlas)) {
            @Override
            public void buttonUp() {
                menuAssets.get(MenuAssets.buttonSound).play();
                game.setScreen(new GameScreen(game));
            }
        };

        buttonQuit = new ButtonQuit(new Vector2(BaseScreen.V_WIDTH - 300, 200), menuAssets.get(MenuAssets.textureAtlas)) {
            @Override
            public void buttonUp() {
                menuAssets.get(MenuAssets.buttonSound).play();
                Gdx.app.exit();
            }
        };

        System.out.println("show menu");
        super.show();
        menuAssets.get(MenuAssets.music).setVolume(0.5f);
        menuAssets.get(MenuAssets.music).setLooping(true);
        menuAssets.get(MenuAssets.music).play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(menuAssets.get(MenuAssets.background), 0, 0);
        buttonPlay.draw(batch);
        buttonQuit.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        System.out.println("dispose menu");
        menuAssets.dispose();
    }

    @Override
    public void hide() {
        dispose();
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
