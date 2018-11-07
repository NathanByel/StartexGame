package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.utils.Pool;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.Sprite;
import com.nbdev.startexgame.Rect;

public class Bullet extends Sprite implements Pool.Poolable {

    public boolean alive;
    private Rect worldBounds;
    private Vector2 v = new Vector2();
    private int damage;
    private Object owner;

    public Bullet() {
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
        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());
        //setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
        alive = true;
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);

        if (pos.y > BaseScreen.V_HEIGHT) {
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
}
