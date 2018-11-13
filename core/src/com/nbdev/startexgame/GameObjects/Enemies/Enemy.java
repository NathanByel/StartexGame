package com.nbdev.startexgame.GameObjects.Enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.GameObjects.Explosion;
import com.nbdev.startexgame.GameObjects.GameObject;
import com.nbdev.startexgame.GameObjects.HealthBar;
import com.nbdev.startexgame.GameObjects.Weapons.Weapon;
import com.nbdev.startexgame.Pools.ExplosionPool;

public class Enemy extends GameObject  implements Pool.Poolable {
    private static final Vector2 fastSpeed = new Vector2(0, -200f);

    private HealthBar healthBar;
    private boolean visible;
    private Weapon weapon;

    public Enemy() {
        super(100);

        canGetDamage = true;
        healthBar = new HealthBar(100, 10, Color.GREEN, Color.BLACK);

        healthBar.setRange(0f, 100f);
        healthBar.setValue(health);

        setPos(new Vector2(BaseScreen.V_WIDTH / 2, BaseScreen.V_HEIGHT));

        weapon = new Weapon(this);
    }

    public void set(
            TextureRegion region,
            Vector2 v0,
            float height,
            int health,
            Weapon.Type weaponType
    ) {
        this.weapon.set(weaponType, true);
        this.textureRegion = region;

        this.v.set(v0);
        this.v.y *= 1000; // костыль
        this.health = health;

        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());

        healthBar.setWidth((int)(textureRegion.getRegionWidth() * 0.8f));
        healthBar.setRange(0f, health);
        //healthBar.setValue(health);

        visible = false;
        alive = true;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        healthBar.setPosition(pos.x - healthBar.getWidth()/2, pos.y + getHalfHeight() + 10);
        healthBar.setValue(health);
        healthBar.act(delta);

        weapon.update(delta);

        if(visible) {
            shot();
            this.pos.mulAdd(v, delta);
        } else {
            this.pos.mulAdd(fastSpeed, delta);
            if(pos.y + getHeight() < BaseScreen.V_HEIGHT) {
                visible = true;
            }
        }

        if (getTop() < 0) {
            healthBar.setValue(0f);
            health = 0;
            alive = false;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if(healthBar.getValue() > 0) {
            healthBar.draw(batch, 1f);
        }
    }

    public boolean damage(int damage) {
        int dh = health - damage;
        if(dh <= 0) {
            destroy();
            return true;
        } else {
            health = dh;
            return false;
        }
    }

    public boolean shot() {
        return weapon.shot(pos);
    }

    public void destroy() {
        healthBar.setValue(0f);
        health = 0;
        alive = false;
        Explosion explosion = ExplosionPool.getPool().obtain();
        explosion.setHeightProportion(getHeight());
        explosion.setPos(pos);
        GameAssets.getInstance().get(GameAssets.destroyedSound);
    }

    @Override
    public void dispose() {
        weapon.dispose();
    }

    @Override
    public void reset() {
        alive = false;
        pos.set(BaseScreen.V_WIDTH / 2, BaseScreen.V_HEIGHT);
    }
}
