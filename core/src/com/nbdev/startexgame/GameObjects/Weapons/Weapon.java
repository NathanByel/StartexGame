package com.nbdev.startexgame.GameObjects.Weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.GameObjects.GameObject;
import com.nbdev.startexgame.ItemsBar.SlotItem;
import com.nbdev.startexgame.Pools.BulletPool;

public abstract class Weapon extends GameObject implements SlotItem {
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
        this.pos.mulAdd(v, delta);

        if (getTop() < 0) {
            alive = false;
        }

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

    @Override
    public void dispose() {
    }

    @Override
    public float getValue() {
        if(bulletsAmount == INFINITY_BULLETS) {
            return 1;
        } else {
            return bulletsAmount;
        }
    }

    @Override
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    @Override
    public void set(Object owner, Vector2 pos, Vector2 v, float amount) {
        this.owner = owner;
        this.pos.set(pos);
        this.v.set(v);
        this.bulletsAmount = (int) amount;
        this.alive = true;
    }

    @Override
    public Object getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Object owner) {
        this.owner = owner;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(alive) {
            this.textureRegion = bulletAnimation.getKeyFrame(0);
            setHeightProportion(120);
            super.draw(batch);
        }
    }
}
