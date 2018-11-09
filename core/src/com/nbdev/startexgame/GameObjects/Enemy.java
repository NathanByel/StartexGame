package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Atlas.MainAtlas;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.utils.Regions;

public class Enemy extends GameObject {
    private HealthBar healthBar;
    private Vector2 v = new Vector2();
    private static Sound damageSound;
    private static Sound destroyedSound;

    public Enemy() {
        super(100);
        if(damageSound == null) {
            damageSound = Gdx.audio.newSound(Gdx.files.internal("sound/damage.mp3"));
        }

        if(destroyedSound == null) {
            destroyedSound = Gdx.audio.newSound(Gdx.files.internal("sound/destroyed.mp3"));
        }

        canGetDamage = true;
        this.textureRegion = Regions.split( MainAtlas.getAtlas().findRegion("enemy0"), 1, 2, 2)[0];
        healthBar = new HealthBar((int)(textureRegion.getRegionWidth() * 0.8f), 5, Color.GREEN, Color.BLACK);
        healthBar.setRange(0f, 100f);
        healthBar.setValue(health);

        setPos(new Vector2(BaseScreen.V_WIDTH / 2, 800));

        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());
    }

    public void set(
            Vector2 pos0,
            Vector2 v0,
            int health,
            int damage
    ) {
        this.pos.set(pos0);
        this.v.set(v0);
        this.health = health;
        alive = true;
    }


    @Override
    public void update(float delta) {
        super.update(delta);
        this.pos.mulAdd(v, delta);

        if (pos.y < 0) {
            alive = false;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        healthBar.setPosition(pos.x - healthBar.getWidth()/2, pos.y + getHalfHeight() + 15);
        healthBar.setValue(health);
        healthBar.act(Gdx.graphics.getDeltaTime());
        healthBar.draw(batch, 1f);
    }

    public void damage(int damage) {
        int dh = health - damage;
        if(dh <= 0) {
            health = 0;
            alive = false;
            destroyedSound.play();
        } else {
            health = dh;
            damageSound.play();
        }
    }

    @Override
    public void dispose() {
        damageSound.dispose();
        destroyedSound.dispose();
    }
}
