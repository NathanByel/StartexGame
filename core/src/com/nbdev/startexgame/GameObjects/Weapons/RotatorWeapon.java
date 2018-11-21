package com.nbdev.startexgame.GameObjects.Weapons;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nbdev.startexgame.Assets.GameAssets;

public class RotatorWeapon extends Weapon {
    private static final float BULLET_VY = -0.4f;
    private static final float BULLET_HEIGHT = 0.006f;
    private static final float BULLET_HIT_HEIGHT = 0.015f;
    private static final int BULLET_DAMAGE = 12;
    private static final float RELOAD_INTERVAL = 4f;

    public RotatorWeapon(Object owner, boolean enemy) {
        super(
                owner,
                GameAssets.getInstance().get(GameAssets.shot1Sound),
                GameAssets.getInstance().get(GameAssets.hit2Sound)
        );

        bulletV.y = BULLET_VY * 2000; // костыль;
        bulletHeight = BULLET_HEIGHT;
        bulletHitHeight = BULLET_HIT_HEIGHT;
        bulletDamage = BULLET_DAMAGE;
        reloadInterval = RELOAD_INTERVAL;

        Array<TextureRegion> array = new Array<TextureRegion>();
        for (int i = 0; i <= 5; i++) {
            array.add(GameAssets.getInstance().get(GameAssets.itemsAtlas).findRegion("rotator", i));
        }
        bulletAnimation = new Animation<TextureRegion>(0.025f, array);
        bulletAnimation.setPlayMode(Animation.PlayMode.LOOP);

        array.clear();
        for (int i = 0; i <= 7; i++) {
            array.add(GameAssets.getInstance().get(GameAssets.itemsAtlas).findRegion("explosion", i));
        }
        hitAnimation = new Animation<TextureRegion>(0.03f, array);
        hitAnimation.setPlayMode(Animation.PlayMode.NORMAL);


        if (!enemy) {
            bulletV.y *= -1;
            reloadInterval = 0.5f;
        }
    }
}
