package com.nbdev.startexgame.Screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.GameObjects.Enemy;
import com.nbdev.startexgame.GameObjects.Player;
import com.nbdev.startexgame.Pools.BulletPool;
import com.nbdev.startexgame.Pools.EnemyPool;

public class GameScreen extends BaseScreen {
    private final Game game;
    private Texture background;

    private Player player;
    private Vector2 direction;
    private BulletPool bulletPool;
    private EnemyPool enemyPool;
    private long oldTime;


    public static TextureAtlas textureAtlas = new TextureAtlas("mainAtlas.tpack");

    public GameScreen(final Game game) {
        this.game = game;
        direction = new Vector2(0,0);
        background = new Texture("background.jpg");

        enemyPool = new EnemyPool();
        bulletPool = new BulletPool(enemyPool);
        player = new Player(bulletPool);
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    private void update(float delta) {
        player.update(delta);
        bulletPool.update(delta);
        enemyPool.update(delta);

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
        bulletPool.draw(batch);
        enemyPool.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }

    // Control
    @Override
    public boolean mouseMoved(Vector2 coord2d) {
        coord2d.y = 200;
        player.setPos(coord2d);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 coord2d, int pointer, int button) {
        System.out.println(button);
        if(button == 0) {
            player.shot();
        }
        return false;
    }
}
