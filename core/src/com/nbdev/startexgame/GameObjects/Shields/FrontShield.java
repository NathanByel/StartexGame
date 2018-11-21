package com.nbdev.startexgame.GameObjects.Shields;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.GameObjects.GameObject;
import com.nbdev.startexgame.GameObjects.Weapons.Bullet;
import com.nbdev.startexgame.ItemsBar.SlotItem;

public class FrontShield extends Shield implements SlotItem {
    public FrontShield() {
        Array<TextureRegion> array = new Array<TextureRegion>();
        for (int i = 0; i <= 15; i++) {
            array.add(GameAssets.getInstance().get(GameAssets.itemsAtlas).findRegion("front_shield", i));
        }

        shieldAnimation = new Animation<TextureRegion>(0.1f, array);
        shieldAnimation.setPlayMode(Animation.PlayMode.LOOP);

        shieldSound = GameAssets.getInstance().get(GameAssets.shieldSound);
        hitSound = GameAssets.getInstance().get(GameAssets.hit1Sound);
    }

    @Override
    public int action(GameObject object) {
        if(object instanceof Bullet) {
            Bullet bullet = (Bullet) object;
            bullet.getV().y *= -1;
            bullet.setOwner(owner);
            hitSound.play();
        }
        return 0;
    }
}
