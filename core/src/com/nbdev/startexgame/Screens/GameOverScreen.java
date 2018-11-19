package com.nbdev.startexgame.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.Background;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.Buttons.ButtonNewGame;
import com.nbdev.startexgame.GameOverSprite;

public class GameOverScreen  extends BaseScreen {
    private final Game game;

    private Background background;
    private ButtonNewGame buttonNewGame;
    private GameOverSprite gameOverSprite;


    public GameOverScreen(final Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        GameAssets.getInstance().load();
        GameAssets.getInstance().finishLoading(); // Ожидание загрузки ресурсов

        background = new Background();
        gameOverSprite = new GameOverSprite( new Vector2(BaseScreen.V_WIDTH/2, BaseScreen.V_HEIGHT/2) );

        buttonNewGame = new ButtonNewGame(
                new Vector2(BaseScreen.V_WIDTH/2, BaseScreen.V_HEIGHT/3)) {
            @Override
            public void buttonUp() {
                game.setScreen(new GameScreen(game));
            }
        };

        System.out.println("show menu");
        super.show();
        GameAssets.getInstance().get(GameAssets.music).setVolume(0.5f);
        GameAssets.getInstance().get(GameAssets.music).setLooping(true);
        GameAssets.getInstance().get(GameAssets.music).play();
    }

    @Override
    public void render(float delta) {
        background.update(delta);

        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        background.draw(batch);
        buttonNewGame.draw(batch);
        gameOverSprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        System.out.println("dispose menu");
        background.dispose();
        GameAssets.getInstance().dispose();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public boolean touchDown(Vector2 coord2d, int pointer, int button) {
        buttonNewGame.touchDown(coord2d, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 coord2d, int pointer, int button) {
        buttonNewGame.touchUp(coord2d, pointer, button);
        return false;
    }
}
