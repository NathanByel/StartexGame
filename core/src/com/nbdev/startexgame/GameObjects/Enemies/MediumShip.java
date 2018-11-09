package com.nbdev.startexgame.GameObjects.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Atlas.MainAtlas;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.GameObjects.GameObject;
import com.nbdev.startexgame.GameObjects.HealthBar;
import com.nbdev.startexgame.utils.Regions;

public class MediumShip extends GameObject {
/*
    public MediumShip() {
        super(100);
        if(damageSound == null) {
            damageSound = Gdx.audio.newSound(Gdx.files.internal("sound/damage.mp3"));
        }

        if(destroyedSound == null) {
            destroyedSound = Gdx.audio.newSound(Gdx.files.internal("sound/destroyed.mp3"));
        }

        canGetDamage = true;
        this.textureRegion = Regions.split( MainAtlas.getAtlas().findRegion("enemy1"), 1, 2, 2)[0];
        healthBar = new HealthBar((int)(textureRegion.getRegionWidth() * 0.8f), 5, Color.GREEN, Color.BLACK);
        healthBar.setRange(0f, 100f);
        healthBar.setValue(health);

        setPos(new Vector2(BaseScreen.V_WIDTH / 2, 800));

        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());
    }*/
}
