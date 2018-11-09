package com.nbdev.startexgame.GameObjects.Enemies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Atlas.MainAtlas;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.GameObjects.Bullet;
import com.nbdev.startexgame.GameObjects.Explosion;
import com.nbdev.startexgame.GameObjects.GameObject;
import com.nbdev.startexgame.GameObjects.HealthBar;
import com.nbdev.startexgame.Pools.ExplosionPool;

public class Enemy extends GameObject {
    private HealthBar healthBar;
    private static Sound damageSound;
    private static Sound destroyedSound;
    private Bullet bullet;

    public Enemy() {
        super(100);
        if(damageSound == null) {
            damageSound = Gdx.audio.newSound(Gdx.files.internal("sound/damage.mp3"));
        }

        if(destroyedSound == null) {
            destroyedSound = Gdx.audio.newSound(Gdx.files.internal("sound/destroyed.mp3"));
        }

        canGetDamage = true;
        //this.textureRegion = Regions.split( MainAtlas.getAtlas().findRegion("enemy0"), 1, 2, 2)[0];
        //healthBar = new HealthBar((int)(textureRegion.getRegionWidth() * 0.8f), 5, Color.GREEN, Color.BLACK);
        healthBar = new HealthBar(0, 5, Color.GREEN, Color.BLACK);

        healthBar.setRange(0f, 100f);
        healthBar.setValue(health);

        setPos(new Vector2(BaseScreen.V_WIDTH / 2, 800));
        healtBarSetPos();

        //setHeight(textureRegion.getRegionHeight());
        //setHeightProportion(textureRegion.getRegionHeight());
        //setWidth(textureRegion.getRegionWidth());
    }

    private void healtBarSetPos() {
        healthBar.setPosition(pos.x - healthBar.getWidth()/2, pos.y + getHalfHeight() + 15);
    }

    public void set(
            TextureRegion region,
            Vector2 v0,
            float height,
            int health,
            Bullet bullet
    ) {
        this.textureRegion = region;
        //this.pos.set(pos0);
        //healtBarSetPos();
        this.v.set(v0);
        this.v.y *= 10000;
        this.health = health;

        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());
        healthBar.setWidth((int)(textureRegion.getRegionWidth() * 0.8f));

        this.bullet = bullet;
        alive = true;
    }

private float timer;
    @Override
    public void update(float delta) {
        super.update(delta);
        healtBarSetPos();
        healthBar.setValue(health);
        healthBar.act(delta);
        this.pos.mulAdd(v, delta);

        timer += delta;
        if(timer > 3000) {
            timer = 0;
            shot();
        }


        if (pos.y < 0) {
            alive = false;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        healthBar.draw(batch, 1f);
    }

    public void damage(int damage) {
        int dh = health - damage;
        if(dh <= 0) {
            destroy();
        } else {
            health = dh;
            damageSound.play();
        }
    }

    public void shot() {
        bullet.set(
                this,
                MainAtlas.getAtlas().findRegion("bulletEnemy"),
                getPos(),
                new Vector2(0f, -500f),
                1f,
                null,
                20);

        //shotSound.play();
    }

    public void destroy() {
        health = 0;
        alive = false;
        Explosion explosion = ExplosionPool.getPool().obtain();
        explosion.setPos(pos);
        destroyedSound.play();
    }

    @Override
    public void dispose() {
        damageSound.dispose();
        destroyedSound.dispose();
    }
}
