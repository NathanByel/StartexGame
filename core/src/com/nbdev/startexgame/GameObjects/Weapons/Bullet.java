package com.nbdev.startexgame.GameObjects.Weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.GameObjects.GameObject;
import com.nbdev.startexgame.utils.Rect;

import java.util.Arrays;

public class Bullet extends GameObject implements Pool.Poolable {
    protected Rect worldBounds;
    protected Vector2 v = new Vector2();
    protected int damage;
    protected Object owner;
    private Animation<TextureRegion> bulletAnimation;
    private Animation<TextureRegion> hitAnimation;
    private float hitHeight;
    private float stateTime;
    protected Sound hitSound;

    public enum State {
        MOVE, HIT, END
    }
    private State state;

    public Bullet() {
        super(0);
        canGetDamage = false;
    }

    public void set(
            Object owner,
            Animation<TextureRegion> bulletAnimation,
            Animation<TextureRegion> hitAnimation,
            Sound hitSound,
            Vector2 pos0,
            Vector2 v0,
            float height,
            float hitHeight,
            Rect worldBounds,
            int damage
    ) {
        this.owner = owner;
        this.bulletAnimation = bulletAnimation;
        this.hitAnimation = hitAnimation;
        this.hitSound = hitSound;
        this.pos.set(pos0);
        this.v.set(v0);

        textureRegion = bulletAnimation.getKeyFrame(0);
        setHeightProportion(height * textureRegion.getRegionHeight() * 100); // костыль

        this.hitHeight = hitHeight;
        this.worldBounds = worldBounds;
        this.damage = damage;
        stateTime = 0;
        state = State.MOVE;
        alive = true;
    }

    public void hit() {
        hitSound.play();
        if(hitAnimation != null) {
            setHeightProportion(hitHeight * textureRegion.getRegionHeight() * 100); // костыль
            stateTime = 0;
            textureRegion = hitAnimation.getKeyFrame(stateTime);
            v.set(0, 0);
            state = State.HIT;
        } else {
            alive = false;
        }
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);

        switch (state) {
            case MOVE:
                textureRegion = bulletAnimation.getKeyFrame(stateTime);
                break;

            case HIT:
                textureRegion = hitAnimation.getKeyFrame(stateTime);
                if(hitAnimation.isAnimationFinished(stateTime)) {
                    alive = false;
                }
                break;
        }

        stateTime += delta;

        if (pos.y < 0 || pos.y >= BaseScreen.V_HEIGHT) {
            alive = false;
        }
    }

    public int getDamage() {

        return damage;
    }

    public Vector2 getV() {
        return v;
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

    public State getState() {
        return state;
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
