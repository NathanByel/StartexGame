package com.nbdev.startexgame.GameObjects.Weapons;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nbdev.startexgame.Assets.GameAssets;

public class MediumWeapon extends Weapon {
    private static final float BULLET_VY = -0.3f;
    private static final float BULLET_HEIGHT = 0.02f;
    private static final float BULLET_HIT_HEIGHT = 0.10f;
    private static final int BULLET_DAMAGE = 5;
    private static final float RELOAD_INTERVAL = 4f;

    public MediumWeapon(Object owner, boolean enemy) {
        super(
                owner,
                GameAssets.getInstance().get(GameAssets.shotSound),
                GameAssets.getInstance().get(GameAssets.hitSound)
        );

        bulletV.y = BULLET_VY * 2000; // костыль;
        bulletHeight = BULLET_HEIGHT;
        bulletHitHeight = BULLET_HIT_HEIGHT;
        bulletDamage = BULLET_DAMAGE;
        reloadInterval = RELOAD_INTERVAL;

        if (enemy) {
            bulletAnimation = new Animation<TextureRegion>(
                    0.25f,
                    GameAssets.getInstance().get(GameAssets.mainAtlas).findRegion("bulletEnemy")
            );
        } else {
            bulletAnimation = new Animation<TextureRegion>(
                    0.25f,
                    GameAssets.getInstance().get(GameAssets.mainAtlas).findRegion("bulletMainShip")
            );
            bulletV.y *= -1;
            reloadInterval = 0.2f;
        }

        Array<TextureRegion> array = new Array<TextureRegion>();
        for (int i = 0; i <= 8; i++) {
            array.add(GameAssets.getInstance().get(GameAssets.itemsAtlas).findRegion("absorbtion", i));
        }
        hitAnimation = new Animation<TextureRegion>(0.03f, array);
        hitAnimation.setPlayMode(Animation.PlayMode.NORMAL);
    }

}
