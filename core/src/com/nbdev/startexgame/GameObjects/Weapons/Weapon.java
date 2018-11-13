package com.nbdev.startexgame.GameObjects.Weapons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.Pools.BulletPool;

public class Weapon implements Disposable {
    public enum Type {
        SMALL_WEAPON,
        MEDIUM_WEAPON,
        BIG_WEAPON
    }

    private static final float SMALL_BULLET_VY = -0.3f;
    private static final float SMALL_BULLET_HEIGHT = 0.01f;
    private static final int SMALL_BULLET_DAMAGE = 1;
    private static final float SMALL_RELOAD_INTERVAL = 3f;

    private static final float MEDIUM_BULLET_VY = -0.3f;
    private static final float MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final int MEDIUM_BULLET_DAMAGE = 5;
    private static final float MEDIUM_RELOAD_INTERVAL = 4f;

    private static final float BIG_BULLET_VY = -0.25f;
    private static final float BIG_BULLET_HEIGHT = 0.06f;
    private static final int BIG_BULLET_DAMAGE = 12;
    private static final float BIG_RELOAD_INTERVAL = 4f;

    private Object owner;
    private Vector2 bulletV = new Vector2();
    private float bulletHeight;
    private int bulletDamage;

    private float reloadInterval;
    private float reloadTimer;
    private TextureRegion textureRegion;

    public Weapon(Object owner) {
        this.owner = owner;
    }

    public void set(Type type, boolean enemy) {
        switch (type) {
            case SMALL_WEAPON:
                bulletV.y = SMALL_BULLET_VY;
                bulletHeight = SMALL_BULLET_HEIGHT;
                bulletDamage = SMALL_BULLET_DAMAGE;
                reloadInterval = SMALL_RELOAD_INTERVAL;
                break;

            case MEDIUM_WEAPON:
                bulletV.y = MEDIUM_BULLET_VY;
                bulletHeight = MEDIUM_BULLET_HEIGHT;
                bulletDamage = MEDIUM_BULLET_DAMAGE;
                reloadInterval = MEDIUM_RELOAD_INTERVAL;
                break;

            case BIG_WEAPON:
                bulletV.y = BIG_BULLET_VY;
                bulletHeight = BIG_BULLET_HEIGHT;
                bulletDamage = BIG_BULLET_DAMAGE;
                reloadInterval = BIG_RELOAD_INTERVAL;
                break;
        }

        if (enemy) {
            //textureRegion = MainAtlas.getAtlas().findRegion("bulletEnemy");
            textureRegion =  GameAssets.getInstance().get(GameAssets.textureAtlas).findRegion("bulletEnemy");
        } else {
            //textureRegion = MainAtlas.getAtlas().findRegion("bulletMainShip");
            textureRegion = GameAssets.getInstance().get(GameAssets.textureAtlas).findRegion("bulletMainShip");
            bulletV.y *= -1;
            reloadInterval = 0.2f;
        }

        bulletV.y *= 2000; // костыль
    }

    public boolean shot(Vector2 pos) {
        if(reloadTimer <= 0) {
            Bullet bullet = BulletPool.getPool().obtain();
            bullet.set(owner,
                    textureRegion,
                    pos,
                    bulletV,
                    bulletHeight,
                    null,
                    bulletDamage);

            GameAssets.getInstance().get(GameAssets.shotSound).play();

            reloadTimer = reloadInterval;
            return true;
        }
        return false;
    }

    public void update(float delta) {
        if(reloadTimer > 0) {
            reloadTimer -= delta;
        }
    }

    @Override
    public void dispose() {
    }
}
