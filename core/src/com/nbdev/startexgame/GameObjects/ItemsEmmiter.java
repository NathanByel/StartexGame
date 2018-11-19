package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.GameObjects.Shields.Shield;
import com.nbdev.startexgame.GameObjects.Weapons.RotatorWeapon;
import com.nbdev.startexgame.GameObjects.Weapons.Weapon;
import com.nbdev.startexgame.ItemsBar.SlotItem;

import java.util.Random;

public class ItemsEmmiter {
    private Random random;
    private Vector2 v;
    private Shield shield;
    private Weapon weapon;

    public ItemsEmmiter() {
        random = new Random();
        v = new Vector2(0, -300f);
        shield = new Shield();
        weapon = new RotatorWeapon(null, false);
    }

    public SlotItem getItem(Vector2 pos) {
        SlotItem item;
        if(random.nextBoolean()) {
            item = new Shield();
            System.out.println("new shield");
        } else {
            item = new RotatorWeapon(null, false);
            System.out.println("new weapon");
        }

        item.set(null, pos, v, 8f);
        return item;
    }
}
