package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Atlas.MainAtlas;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.Pools.BulletPool;
import com.nbdev.startexgame.utils.Regions;

public class Player extends GameObject {
    private Sound shotSound;

    public Player() {
        super(100);
        canGetDamage = true;
        shotSound = Gdx.audio.newSound(Gdx.files.internal("sound/shot.mp3"));

        this.textureRegion = Regions.split(MainAtlas.getAtlas().findRegion("main_ship"), 1, 2, 2)[0];
        setPos(new Vector2(BaseScreen.V_WIDTH / 2, 200));

        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());
    }

    public void shot() {
        Bullet bullet = BulletPool.getBulletPool().obtain();

        bullet.set(
                this,
                MainAtlas.getAtlas().findRegion("bulletMainShip"),
                getPos(),
                new Vector2(0f, 500f),
                1f,
                null,
                20);

        shotSound.play();
    }

    @Override
    public void dispose() {
        shotSound.dispose();
    }

    // Control
    public boolean mouseMoved(Vector2 coord2d) {
        coord2d.y = 200;
        setPos(coord2d);
        return false;
    }

    public boolean touchDown(Vector2 coord2d, int pointer, int button) {
        if(button == 0) {
            shot();
        }
        return false;
    }

    public boolean touchUp(Vector2 coord2d, int pointer, int button) {
        return false;
    }

    public boolean keyDown(int keycode) {
        return false;
    }

    public boolean keyUp(int keycode) {
        return false;
    }
}
