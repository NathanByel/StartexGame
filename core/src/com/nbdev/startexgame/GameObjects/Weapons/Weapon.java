package com.nbdev.startexgame.GameObjects.Weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Pools.BulletPool;

public abstract class Weapon {
    public static final int INFINITY_BULLETS = Integer.MIN_VALUE;
    protected Vector2 bulletV = new Vector2();
    protected float bulletHeight;
    protected float bulletHitHeight;
    protected int bulletDamage;
    protected float reloadInterval;

    protected Animation<TextureRegion> bulletAnimation;
    protected Animation<TextureRegion> hitAnimation;
    protected int bulletsAmount;

    private Object owner;
    private Sound shotSound;
    private Sound hitSound;
    private float reloadTimer;

    public Weapon(Object owner, Sound shotSound, Sound hitSound) {
        this.owner = owner;
        this.shotSound = shotSound;
        this.hitSound = hitSound;
        this.bulletsAmount = INFINITY_BULLETS;
    }

    public boolean shot(Vector2 pos) {
        if(reloadTimer <= 0 && (bulletsAmount > 0 || bulletsAmount == INFINITY_BULLETS)) {
            Bullet bullet = BulletPool.getPool().obtain();
            bullet.set(owner,
                    bulletAnimation,
                    hitAnimation,
                    hitSound,
                    pos,
                    bulletV,
                    bulletHeight,
                    bulletHitHeight,
                    null,
                    bulletDamage);

            shotSound.play();
            reloadTimer = reloadInterval;
            if(bulletsAmount > 0) {
                bulletsAmount--;
            }
            return true;
        }
        return false;
    }

    public void update(float delta) {
        if(reloadTimer > 0) {
            reloadTimer -= delta;
        }
    }

    public void setBulletsAmount(int amount) {
        bulletsAmount = amount;
    }

    public int getBulletsAmount() {
        return bulletsAmount;
    }

    public void addBullets(int amount) {
        bulletsAmount += amount;
    }
}
