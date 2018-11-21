package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.GameObjects.Shields.Shield;
import com.nbdev.startexgame.GameObjects.Weapons.Bullet;
import com.nbdev.startexgame.GameObjects.Weapons.SmallWeapon;
import com.nbdev.startexgame.GameObjects.Weapons.Weapon;
import com.nbdev.startexgame.ItemsBar.ItemsBar;
import com.nbdev.startexgame.ItemsBar.SlotItem;
import com.nbdev.startexgame.Pools.ExplosionPool;
import com.nbdev.startexgame.utils.Regions;

public class Player extends GameObject {
    private ItemsBar itemsBar;
    private Weapon weapon;
    private Shield shield;
    private boolean autoShot;
    private Sound getItemSound;

    public Player(ItemsBar itemsBar) {
        super(100);
        this.itemsBar = itemsBar;
        canGetDamage = true;
        alive = true;

        getItemSound = GameAssets.getInstance().get(GameAssets.getItemSound);

        this.textureRegion = Regions.split(
                GameAssets.getInstance().get(GameAssets.mainAtlas).findRegion("main_ship"),
                1,
                2,
                2)[0];

        setPos(new Vector2(BaseScreen.V_WIDTH / 2, 200));

        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());

        weapon = new SmallWeapon(this, false);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        weapon.update(delta);

        if(shield != null) {
            shield.setPos(pos);
            shield.update(delta);
            if(!shield.decrementTime(delta)) {
                itemsBar.removeItem(shield);
                shield.alive = false;
                shield = null;
                System.out.println("shield off");
            }
        }

        if(weapon.getValue() == 0) {
            itemsBar.removeItem(weapon);
            weapon.alive = false;
            weapon = new SmallWeapon(this, false);
            System.out.println("standart weapon");
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

    public boolean hit(GameObject object) {
        int damage = 0;
        if(shield != null) {
            shield.hit();
            damage = shield.action(object);
        } else {
            if(object instanceof Bullet) {
                Bullet bullet = (Bullet) object;
                damage = bullet.getDamage();
                bullet.hit();
            }
        }

        if(damage > 0) {
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
        return false;
    }

    public void destroy() {
        health = 0;
        alive = false;
        Explosion explosion = ExplosionPool.getPool().obtain();
        explosion.setHeightProportion(getHeight());
        explosion.setPos(pos);
        GameAssets.getInstance().get(GameAssets.destroyedSound).play();
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
    }

    public void pickUp(SlotItem item) {
        if(item instanceof Shield) {
            Shield newShield = (Shield) item;
            if(shield == null) {
                shield = newShield;
                shield.set(this);
                shield.setOwner(this);
                shield.setHeightProportion(getHeight() + 100);

                itemsBar.addItem(item);
                System.out.println("got shield");
            } else if (newShield.getClass() == shield.getClass()){
                shield.setActionTime(newShield.getActionTime());
                newShield.alive = false;
            } else {
                itemsBar.removeItem(shield);
                shield.alive = false;

                shield = newShield;
                shield.set(this);
                shield.setOwner(this);
                shield.setHeightProportion(getHeight() + 100);

                itemsBar.addItem(item);
                System.out.println("got new shield");
            }
            getItemSound.play();
        } else if(item instanceof Weapon) {
            Weapon newWeapon = (Weapon) item;
            if(weapon instanceof SmallWeapon) {
                weapon = newWeapon;
                weapon.setOwner(this);

                itemsBar.addItem(item);
                System.out.println("got weapon");
            } else if (newWeapon.getClass() == weapon.getClass()){
                weapon.setBulletsAmount(newWeapon.getBulletsAmount());
            }
            getItemSound.play();
        }
    }
}
