package com.nbdev.startexgame.GameObjects.Enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Assets.GameAssets;

import com.nbdev.startexgame.GameObjects.Weapons.Weapon;
import com.nbdev.startexgame.Pools.EnemyPool;
import com.nbdev.startexgame.utils.Regions;

public class EnemyFactory {
    public enum Type {
        SMALL_SHIP,
        MEDIUM_SHIP,
        BIG_SHIP
    }

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_MEDIUM_HEIGHT = 0.1f;
    private static final int ENEMY_MEDIUM_HP = 5;

    private static final float ENEMY_BIG_HEIGHT = 0.2f;
    private static final int ENEMY_BIG_HP = 20;

    private TextureRegion enemySmallRegion = Regions.split(
            GameAssets.getInstance().get(GameAssets.textureAtlas).findRegion("enemy0"),
            1, 2, 2)[0];

    private TextureRegion enemyMediumRegion = Regions.split(
            GameAssets.getInstance().get(GameAssets.textureAtlas).findRegion("enemy1"),
            1, 2, 2)[0];

    private TextureRegion enemyBigRegion = Regions.split(
            GameAssets.getInstance().get(GameAssets.textureAtlas).findRegion("enemy2"),
            1, 2, 2)[0];


    private Vector2 enemySmallV = new Vector2(0, -0.2f);
    private Vector2 enemyMediumV = new Vector2(0, -0.03f);
    private Vector2 enemyBigV = new Vector2(0, -0.005f);


    public Enemy getEnemy(Type type) {
        Enemy enemy = EnemyPool.getPool().obtain();

        switch(type) {
            case SMALL_SHIP:
                enemy.set(
                        enemySmallRegion,
                        enemySmallV,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP,
                        Weapon.Type.SMALL_WEAPON
                );
                break;

            case MEDIUM_SHIP:
                enemy.set(
                        enemyMediumRegion,
                        enemyMediumV,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP,
                        Weapon.Type.MEDIUM_WEAPON
                );
                break;

            case BIG_SHIP:
                enemy.set(
                        enemyBigRegion,
                        enemyBigV,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP,
                        Weapon.Type.BIG_WEAPON
                );
                break;
        }
        return enemy;
    }
}
