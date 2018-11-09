package com.nbdev.startexgame.Screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Atlas.MainAtlas;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.GameObjects.Bullet;
import com.nbdev.startexgame.GameObjects.Enemy;
import com.nbdev.startexgame.GameObjects.Player;
import com.nbdev.startexgame.Pools.BulletPool;
import com.nbdev.startexgame.Pools.EnemyPool;

public class GameScreen extends BaseScreen {
    private final Game game;
    private Texture background;

    private Player player;
    private EnemyPool enemyPool;
    private long oldTime;

    public GameScreen(final Game game) {
        this.game = game;
        background = new Texture("background.jpg");

        enemyPool = new EnemyPool();
        player = new Player();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    private void update(float delta) {
        player.update(delta);
        BulletPool.getBulletPool().update(delta);
        enemyPool.update(delta);

        for (Enemy enemy : enemyPool.getActive()) {
            for (Bullet bullet : BulletPool.getBulletPool().getActive()) {
                if (!bullet.isOutside(enemy)) {
                    enemy.damage(bullet.getDamage());
                    bullet.alive = false;
                }
            }
        }

        long time = System.currentTimeMillis();
        if(time - oldTime > 3000) {
            oldTime = time;
            Enemy enemy = enemyPool.obtain();
            enemy.set(
                    new Vector2((float)(Math.random() * GameScreen.V_WIDTH), GameScreen.V_HEIGHT),
                    new Vector2(0, -100f),
                    100,
                    5
            );
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(background, 0, 0);
        player.draw(batch);

        BulletPool.getBulletPool().draw(batch);
        enemyPool.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        MainAtlas.getAtlas().dispose();
        BulletPool.getBulletPool().dispose();
        enemyPool.dispose();
    }

    // Control
    @Override
    public boolean mouseMoved(Vector2 coord2d) {
        player.mouseMoved(coord2d);
        return false;
    }

    @Override
    public boolean touchDragged(Vector2 coord2d, int pointer) {
        player.mouseMoved(coord2d);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 coord2d, int pointer, int button) {
        player.touchDown(coord2d, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 coord2d, int pointer, int button) {
        player.touchUp(coord2d, pointer, button);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        player.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        player.keyUp(keycode);
        return false;
    }
}
