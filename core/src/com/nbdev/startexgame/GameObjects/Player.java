package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.Pools.BulletPool;
import com.nbdev.startexgame.Screens.GameScreen.GameScreen;

public class Player extends GameObject {
    private Sound shotSound;
    private BulletPool bulletPool;

    public Player(BulletPool bulletPool) {
        super(100);
        canGetDamage = true;
        this.bulletPool = bulletPool;
        shotSound = Gdx.audio.newSound(Gdx.files.internal("sound/shot.mp3"));

        this.textureRegion = GameScreen.textureAtlas.findRegion("main_ship");
        setPos(new Vector2(BaseScreen.V_WIDTH / 2, 200));

        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());
    }

    public void shot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(
                this,
                GameScreen.textureAtlas.findRegion("bulletMainShip"),
                getPos(),
                new Vector2(0f, 500f),
                0,
                null,
                20);

        shotSound.play();
    }

    @Override
    public void dispose() {
        super.dispose();
        shotSound.dispose();
    }
}
