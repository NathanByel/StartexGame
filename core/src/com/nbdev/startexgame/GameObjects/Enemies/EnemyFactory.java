package com.nbdev.startexgame.GameObjects.Enemies;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Assets.GameAssets;

import com.nbdev.startexgame.GameObjects.Weapons.BigWeapon;
import com.nbdev.startexgame.GameObjects.Weapons.MediumWeapon;
import com.nbdev.startexgame.GameObjects.Weapons.SmallWeapon;
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
            GameAssets.getInstance().get(GameAssets.mainAtlas).findRegion("enemy0"),
            1, 2, 2)[0];

    private TextureRegion enemyMediumRegion = Regions.split(
            GameAssets.getInstance().get(GameAssets.mainAtlas).findRegion("enemy1"),
            1, 2, 2)[0];

    private TextureRegion enemyBigRegion = Regions.split(
            GameAssets.getInstance().get(GameAssets.mainAtlas).findRegion("enemy2"),
            1, 2, 2)[0];


    private Vector2 enemySmallV = new Vector2(0, -0.2f);
    private Vector2 enemyMediumV = new Vector2(0, -0.03f);
    private Vector2 enemyBigV = new Vector2(0, -0.005f);

    private Weapon smallWeapon = new SmallWeapon(null, true);
    private Weapon mediumWeapon = new MediumWeapon(null, true);
    private Weapon bigWeapon = new BigWeapon(null, true);

    public Enemy getEnemy(Type type) {
        Enemy enemy = EnemyPool.getPool().obtain();

        switch(type) {
            case SMALL_SHIP:
                enemy.set(
                        enemySmallRegion,
                        enemySmallV,
                        ENEMY_SMALL_HEIGHT,
                        ENEMY_SMALL_HP,
                        smallWeapon
                );
                break;

            case MEDIUM_SHIP:
                enemy.set(
                        enemyMediumRegion,
                        enemyMediumV,
                        ENEMY_MEDIUM_HEIGHT,
                        ENEMY_MEDIUM_HP,
                        mediumWeapon
                );
                break;

            case BIG_SHIP:
                enemy.set(
                        enemyBigRegion,
                        enemyBigV,
                        ENEMY_BIG_HEIGHT,
                        ENEMY_BIG_HP,
                        bigWeapon
                );
                break;
        }
        return enemy;
    }
}
