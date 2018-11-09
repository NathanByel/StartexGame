package com.nbdev.startexgame.GameObjects.Weapons;

import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Atlas.MainAtlas;
import com.nbdev.startexgame.Pools.BulletPool;

public class Weapon {
    protected Vector2 bulletV = new Vector2();
    protected float bulletHeight;
    protected int bulletDamage;

    protected float reloadInterval;
    protected float reloadTimer;

    public void set(float bulletVY, float bulletHeight, int bulletDamage, float reloadInterval) {
        this.bulletV.y = bulletVY;
        this.bulletHeight = bulletHeight;
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
    }

    public void shot(Vector2 pos) {
        Bullet bullet = BulletPool.getPool().obtain();

        bullet.set(
                this,
                MainAtlas.getAtlas().findRegion("bulletMainShip"),
                pos,
                new Vector2(0f, 500f),
                1f,
                null,
                20);

        //shotSound.play();
    }
}
