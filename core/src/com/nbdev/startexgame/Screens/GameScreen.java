package com.nbdev.startexgame.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.Background;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.Buttons.ButtonNewGame;
import com.nbdev.startexgame.GameObjects.Enemies.EnemyEmitter;
import com.nbdev.startexgame.GameObjects.Enemies.EnemyFactory;
import com.nbdev.startexgame.GameObjects.Enemies.Enemy;
import com.nbdev.startexgame.GameObjects.Player;
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

    public GameScreen(final Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        GameAssets.getInstance().load();
        GameAssets.getInstance().finishLoading(); // Ожидание загрузки ресурсов

        background = new Background();

        scoreBar = new ScoreBar();
        player = new Player();
        EnemyFactory enemyFactory = new EnemyFactory();
        enemyEmitter = new EnemyEmitter(enemyFactory);

        System.out.println("show game");
        super.show();

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
                if(!bullet.isOutside(player) && bullet.getOwner() != player) {
                    bullet.alive = false;
                    if(player.damage(bullet.getDamage())) {

                    }
                } else if (!bullet.isOutside(enemy) && bullet.getOwner() == player) {
                    bullet.alive = false;
                    if(enemy.damage(bullet.getDamage())) {
                        score++;
                        System.out.println("set menu screen");

                        //if(!isEnd) {
                        //    Timer.schedule(new Timer.Task(){
                        //        @Override
                        //        public void run() {
                                    // Do your work
                                    System.out.println("timer");
                                    gameEnd = true;
                                    game.setScreen(new GameOverScreen(game));

                         //       }
                          //  }, 0.1f);
                         //   isEnd = true;
                        //}

                        System.out.println("return");
                        return;
                    }
                }
            }
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);

        if(!gameEnd) {
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
        //super.hide();
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
