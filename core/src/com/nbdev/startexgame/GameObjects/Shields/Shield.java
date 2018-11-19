package com.nbdev.startexgame.GameObjects.Shields;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.GameObjects.GameObject;
import com.nbdev.startexgame.ItemsBar.SlotItem;

public class Shield extends GameObject implements SlotItem {
    private static final float FLASH_SPEED = 0.3f;
    private float actionTime;
    private float transparency;
    private float flashSpeed;
    private Animation<TextureRegion> shieldAnimation;
    private float stateTime;
    private Object owner;

    public Shield() {
        Array<TextureRegion> array = new Array<TextureRegion>();
        for (int i = 0; i <= 10; i++) {
            array.add(GameAssets.getInstance().get(GameAssets.itemsAtlas).findRegion("shield1", i));
        }

        shieldAnimation = new Animation<TextureRegion>(0.2f, array);
        shieldAnimation.setPlayMode(Animation.PlayMode.LOOP);

        flashSpeed = FLASH_SPEED;
        transparency = 1f;
        stateTime = 0;
        textureRegion = shieldAnimation.getKeyFrame(stateTime);
        setHeightProportion(textureRegion.getRegionHeight());
    }

    public void set(GameObject owner, Vector2 pos, Vector2 v, float actionTime) {
        this.owner = owner;
        this.transparency = 1f;
        this.pos.set(pos);
        this.v.set(v);
        this.actionTime = actionTime;
        alive = true;
    }

    public void set(GameObject owner) {
        this.owner = owner;
        this.transparency = 1f;
        this.v.set(0, 0);
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

    public boolean decrementTime(float delta) {
        actionTime -= delta;
        if(actionTime <= 0) {
            GameAssets.getInstance().get(GameAssets.shieldSound).stop();
            alive = false;
            owner = null;
            return false;
        }

        return true;
    }

    public Object getOwner() {
        return owner;
    }

    @Override
    public void dispose() {
    }

    @Override
    public float getValue() {
        return actionTime;
    }

    @Override
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
}
