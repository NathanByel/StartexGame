package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.GameObjects.Weapons.Weapon;
import com.nbdev.startexgame.Pools.ExplosionPool;
import com.nbdev.startexgame.utils.Regions;

public class Player extends GameObject {
    private Weapon weapon;
    private boolean autoShot;
    private Shield shield;

    public Player() {
        super(100);
        canGetDamage = true;
        alive = true;

        this.textureRegion = Regions.split(
                GameAssets.getInstance().get(GameAssets.textureAtlas).findRegion("main_ship"),
                1,
                2,
                2)[0];

        setPos(new Vector2(BaseScreen.V_WIDTH / 2, 200));

        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());

        weapon = new Weapon(this);
        weapon.set(Weapon.Type.SMALL_WEAPON, false);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        weapon.update(delta);

        if(shield != null) {
            shield.setPos(pos);
            shield.update(delta);
            if(!shield.decrementTime(delta)) {
                shield = null;
                System.out.println("shield off");
            }
        }

        if(autoShot) {
            shot();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(alive) {
            super.draw(batch);

            if(shield != null) {
                shield.draw(batch);
            }
        }
    }

    public boolean shot() {
        if(alive) {
            return weapon.shot(pos);
        }
        return false;
    }

    public boolean damage(int damage) {
        if(shield != null) {
            GameAssets.getInstance().get(GameAssets.shieldShotSound).play();
            return false;
        }

        GameAssets.getInstance().get(GameAssets.damageSound).play();
        int dh = health - damage;
        if (dh <= 0) {
            health = 0;
            destroy();
            return true;
        } else {
            health = dh;
            System.out.println("player damage");
            return false;
        }
    }

    public void setShield(Shield shield) {
        this.shield = shield;
        shield.set(this);
        shield.setHeightProportion(getHeight() + 100);
        GameAssets.getInstance().get(GameAssets.shieldSound).loop(0.7f);
    }

    public void destroy() {
        health = 0;
        alive = false;
        Explosion explosion = ExplosionPool.getPool().obtain();
        explosion.setHeightProportion(getHeight());
        explosion.setPos(pos);
        GameAssets.getInstance().get(GameAssets.destroyedSound);
    }

    // Control
    public boolean mouseMoved(Vector2 coord2d) {
        if(alive) {
            coord2d.y = 200;
            setPos(coord2d);
        }
        return false;
    }

    public boolean touchDown(Vector2 coord2d, int pointer, int button) {
        if(alive) {
            shot();
            autoShot = true;
        }
        return false;
    }

    public boolean touchUp(Vector2 coord2d, int pointer, int button) {
        autoShot = false;
        return false;
    }

    @Override
    public void dispose() {
        weapon.dispose();
    }
}
