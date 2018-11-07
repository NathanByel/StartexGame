package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.Pools.BulletPool;

public class Player extends GameObject {
    TextureAtlas textureAtlas;
    Sound shotSound;
    BulletPool bulletPool;

    public Player(TextureAtlas textureAtlas, BulletPool bulletPool) {
        super(100);
        this.textureAtlas = textureAtlas;
        this.bulletPool = bulletPool;
        shotSound = Gdx.audio.newSound(Gdx.files.internal("s.mp3"));

        this.textureRegion = textureAtlas.findRegion("main_ship");
        setPos(new Vector2(BaseScreen.V_WIDTH / 2, 200));

        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());
    }

    public void shot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(
                this,
                textureAtlas.findRegion("bulletMainShip"),
                getPos(),
                new Vector2(0f, 300f),
                0,
                null,
                20);

        shotSound.play(1.0f);
    }
}
