package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.nbdev.startexgame.Assets.GameAssets;

public class Shield extends GameObject {
    private float time;
    private Animation<TextureRegion> shieldAnimation;
    private float stateTime;
    private Object owner;

    public Shield() {
        Array<TextureRegion> array = new Array<TextureRegion>();
        for (int i = 0; i <= 10; i++) {
            array.add(GameAssets.getInstance().get(GameAssets.shieldAtlas).findRegion(((i < 10)?"0":"") + i));
        }

        shieldAnimation = new Animation<TextureRegion>(0.2f, array);
        shieldAnimation.setPlayMode(Animation.PlayMode.LOOP);

        stateTime = 0;
        textureRegion = shieldAnimation.getKeyFrame(stateTime);
        setHeightProportion(textureRegion.getRegionHeight());
    }

    public void set(GameObject owner, Vector2 pos, Vector2 v, float time) {
        this.owner = owner;
        this.pos.set(pos);
        this.v.set(v);
        this.time = time;
        alive = true;
    }

    public void set(GameObject owner) {
        this.owner = owner;
        this.v.set(0, 0);
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(v, delta);
        textureRegion = shieldAnimation.getKeyFrame(stateTime);
        stateTime += delta;

        if (getTop() < 0) {
            alive = false;
        }
    }

    public boolean decrementTime(float delta) {
        time -= delta;
        if(time <= 0) {
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
}
