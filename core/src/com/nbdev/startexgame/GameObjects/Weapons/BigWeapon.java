package com.nbdev.startexgame.GameObjects.Weapons;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nbdev.startexgame.Assets.GameAssets;

public class BigWeapon extends Weapon {
    private static final float BULLET_VY = -0.25f;
    private static final float BULLET_HEIGHT = 0.06f;
    private static final int BULLET_DAMAGE = 12;
    private static final float RELOAD_INTERVAL = 4f;

    public BigWeapon(Object owner, boolean enemy) {
        super(owner, GameAssets.getInstance().get(GameAssets.shotSound));

        bulletV.y = BULLET_VY * 2000; // костыль;
        bulletHeight = BULLET_HEIGHT;
        bulletDamage = BULLET_DAMAGE;
        reloadInterval = RELOAD_INTERVAL;

        if (enemy) {
            bulletAnimation = new Animation<TextureRegion>(
                    0.25f,
                    GameAssets.getInstance().get(GameAssets.textureAtlas).findRegion("bulletEnemy")
            );
        } else {
            bulletAnimation = new Animation<TextureRegion>(
                    0.25f,
                    GameAssets.getInstance().get(GameAssets.textureAtlas).findRegion("bulletMainShip")
            );
            bulletV.y *= -1;
            reloadInterval = 0.2f;
        }
    }

}
