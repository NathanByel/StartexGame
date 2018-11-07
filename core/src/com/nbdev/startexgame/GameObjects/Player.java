package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.BaseScreen;

public class Player extends GameObject {
    Sound shotSound;

    public Player(TextureAtlas textureAtlas) {
        super(100);
        shotSound = Gdx.audio.newSound(Gdx.files.internal("s.mp3"));

        this.textureRegion = textureAtlas.findRegion("main_ship");
        setPos(new Vector2(BaseScreen.V_WIDTH / 2, 200));

        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());
    }

    public void shot() {
        shotSound.play(1.0f);
    }
}
