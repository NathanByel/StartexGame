package com.nbdev.startexgame.GameObjects.Weapons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.GameObjects.GameObject;
import com.nbdev.startexgame.utils.Rect;

public class Bullet extends GameObject implements Pool.Poolable, Disposable {

    private Rect worldBounds;
    private Vector2 v = new Vector2();
    private int damage;
    private Object owner;

    public Bullet() {
        super(0);
        canGetDamage = false;
    }

    public void set(
            Object owner,
            TextureRegion region,
            Vector2 pos0,
            Vector2 v0,
            float height,
            Rect worldBounds,
            int damage
    ) {
        this.owner = owner;
        this.textureRegion = region;
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height * textureRegion.getRegionHeight() * 100); // костыль
        this.worldBounds = worldBounds;
        this.damage = damage;
        alive = true;
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);

        if (pos.y >= BaseScreen.V_HEIGHT) {
            alive = false;
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    @Override
    public void reset() {
        pos.set(0,0);
        alive = false;
    }

    @Override
    public void dispose() {

    }
}
