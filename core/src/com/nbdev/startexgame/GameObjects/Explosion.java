package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.utils.Regions;

public class Explosion extends GameObject implements Pool.Poolable {
    private Animation<TextureRegion> explodeAnimation;
    private float stateTime;

    public Explosion() {
        explodeAnimation = new Animation<TextureRegion>(
                0.01f,
                Regions.split( GameAssets.getInstance().get(GameAssets.textureAtlas).
                                findRegion("explosion"), 9, 9, 81)
        );
        stateTime = 0;
        textureRegion = explodeAnimation.getKeyFrame(stateTime);
        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());
    }

    public void set(Vector2 pos) {
        this.pos.set(pos);
    }


    @Override
    public void update(float delta) {
        textureRegion = explodeAnimation.getKeyFrame(stateTime);
        if(explodeAnimation.isAnimationFinished(stateTime)) {
            alive = false;
            System.out.println("explosion finished");
        }
        stateTime += delta;
    }

    @Override
    public void reset() {
        stateTime = 0;
    }

    @Override
    public void dispose() {
    }
}
