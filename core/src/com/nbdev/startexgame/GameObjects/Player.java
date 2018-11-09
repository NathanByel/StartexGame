package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Atlas.MainAtlas;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.GameObjects.Weapons.Weapon;
import com.nbdev.startexgame.utils.Regions;

public class Player extends GameObject {
    private Weapon weapon;

    public Player() {
        super(100);
        canGetDamage = true;

        this.textureRegion = Regions.split(MainAtlas.getAtlas().findRegion("main_ship"), 1, 2, 2)[0];
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
    }

    public boolean shot() {
        return weapon.shot(pos);
    }

    public void damage(int damage) {
        int dh = health - damage;
        if(dh <= 0) {
            health = 0;
            //destroy();
        } else {
            health = dh;
            //damageSound.play();
            System.out.println("player damage");
        }
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
