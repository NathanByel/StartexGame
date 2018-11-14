package com.nbdev.startexgame.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.Background;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.GameObjects.Enemies.EnemyEmitter;
import com.nbdev.startexgame.GameObjects.Enemies.EnemyFactory;
import com.nbdev.startexgame.GameObjects.Enemies.Enemy;
import com.nbdev.startexgame.GameObjects.Player;
import com.nbdev.startexgame.GameObjects.Shield;
import com.nbdev.startexgame.GameObjects.Weapons.Bullet;
import com.nbdev.startexgame.Pools.BulletPool;
import com.nbdev.startexgame.Pools.EnemyPool;
import com.nbdev.startexgame.Pools.ExplosionPool;
import com.nbdev.startexgame.ScoreBar;

public class GameScreen extends BaseScreen {
    private final Game game;
    private Background background;

    private ScoreBar scoreBar;
    private Player player;
    private EnemyEmitter enemyEmitter;

    private int score;
    private boolean gameEnd;
    private Shield shield;

    public GameScreen(final Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        System.out.println("show game");

        GameAssets.getInstance().load();
        GameAssets.getInstance().finishLoading(); // Ожидание загрузки ресурсов

        background = new Background();

        scoreBar = new ScoreBar();
        player = new Player();
        shield = new Shield();
        EnemyFactory enemyFactory = new EnemyFactory();
        enemyEmitter = new EnemyEmitter(enemyFactory);

        GameAssets.getInstance().get(GameAssets.music).setVolume(0.5f);
        GameAssets.getInstance().get(GameAssets.music).setLooping(true);
        GameAssets.getInstance().get(GameAssets.music).play();
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    private void update(float delta) {
        background.update(delta);

        if(!gameEnd) {
            if(shield != null && shield.alive) {
                shield.update(delta);
            }
            player.update(delta);
            BulletPool.getPool().update(delta);
            ExplosionPool.getPool().update(delta);
            EnemyPool.getPool().update(delta);
            enemyEmitter.generate(delta);

            collisionCheck();
            scoreBar.setHealth(player.getHealth());
            scoreBar.setScore(score);
        }
    }

    private void collisionCheck() {
        for (Enemy enemy : EnemyPool.getPool().getActive()) {
            for (Bullet bullet : BulletPool.getPool().getActive()) {
                if(player.alive && !bullet.isOutside(player) && bullet.getOwner() != player) {
                    bullet.alive = false;
                    if(player.damage(bullet.getDamage())) {
                        System.out.println("set menu screen");
                        Timer.schedule(new Timer.Task(){
                            @Override
                            public void run() {
                                gameEnd = true;
                                System.out.println("timer");
                                game.setScreen(new GameOverScreen(game));
                            }
                        }, 1f);
                        return;
                    }
                } else if (!bullet.isOutside(enemy) && bullet.getOwner() == player) {
                    bullet.alive = false;
                    if(enemy.damage(bullet.getDamage())) {
                        score++;

                        if(!shield.alive) {
                            shield.setHeightProportion(100);
                            shield.set(null, enemy.getPos(), new Vector2(0, -300f), 8f);
                        }
                    }
                }
            }
        }

        if(shield.alive && (shield.getOwner() != player) && !player.isOutside(shield)) {
            player.setShield(shield);
            System.out.println("got shield");
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);

        if(!gameEnd) {
            if(shield != null && shield.alive) {
                shield.draw(batch);
            }
            player.draw(batch);
            BulletPool.getPool().draw(batch);
            ExplosionPool.getPool().draw(batch);
            EnemyPool.getPool().draw(batch);
        }

        scoreBar.draw(batch);
        batch.end();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        System.out.println("dispose game");
        background.dispose();

        BulletPool.getPool().dispose();
        ExplosionPool.getPool().dispose();
        EnemyPool.getPool().dispose();

        GameAssets.getInstance().dispose();
    }

    // Control
    @Override
    public boolean touchDragged(Vector2 coord2d, int pointer) {
        player.mouseMoved(coord2d);
        return false;
    }

    @Override
    public boolean mouseMoved(Vector2 coord2d) {
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
}
