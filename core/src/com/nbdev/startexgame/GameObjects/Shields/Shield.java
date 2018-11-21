package com.nbdev.startexgame.GameObjects.Shields;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.GameObjects.GameObject;
import com.nbdev.startexgame.ItemsBar.SlotItem;

public abstract class Shield extends GameObject implements SlotItem {
    private static final float FLASH_SPEED = 0.3f;
    private float actionTime;
    private float transparency;
    private float flashSpeed;

    protected Animation<TextureRegion> shieldAnimation;
    protected Animation<TextureRegion> hitAnimation;
    protected float stateTime;
    protected Object owner;
    protected Sound shieldSound;
    protected Sound hitSound;

    public Shield() {
        flashSpeed = FLASH_SPEED;
        transparency = 1f;
        stateTime = 0;
    }

    @Override
    public void set(Object owner, Vector2 pos, Vector2 v, float actionTime) {
        this.owner = owner;
        this.transparency = 1f;
        this.pos.set(pos);
        this.v.set(v);
        this.actionTime = actionTime;

        textureRegion = shieldAnimation.getKeyFrame(stateTime);
        setHeightProportion(120f);
        alive = true;
    }

    @Override
    public void setOwner(Object owner) {
        this.owner = owner;
        this.transparency = 1f;
        this.v.set(0, 0);
        if(owner != null) {
            shieldSound.loop(0.7f);
        }
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        textureRegion = shieldAnimation.getKeyFrame(stateTime);
        stateTime += delta;

        if(actionTime <= 1f) {
            transparency = actionTime;
        } else {
            transparency += flashSpeed * delta;
            if(transparency > 1f) {
                transparency = 1f;
                flashSpeed = -FLASH_SPEED;
            } else if(transparency < 0.3f) {
                transparency = 0.3f;
                flashSpeed = FLASH_SPEED;
            }
        }

        if (getTop() < 0) {
            alive = false;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(1f,1f,1f, transparency);
        super.draw(batch);
        batch.setColor(1f,1f,1f, 1f);
    }

    public void hit() {
        hitSound.play();
    }

    public boolean decrementTime(float delta) {
        actionTime -= delta;
        if(actionTime <= 0) {
            shieldSound.stop();
            alive = false;
            owner = null;
            return false;
        }

        return true;
    }

    public abstract int action(GameObject object);

    public float getActionTime() {
        return actionTime;
    }

    public void setActionTime(float actionTime) {
        this.actionTime = actionTime;
    }

    @Override
    public Object getOwner() {
        return owner;
    }

    @Override
    public float getValue() {
        return actionTime;
    }

    @Override
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    @Override
    public void dispose() {
    }
}
