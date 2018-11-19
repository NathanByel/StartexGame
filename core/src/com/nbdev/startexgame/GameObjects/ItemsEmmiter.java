package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.GameObjects.Shields.FrontShield;
import com.nbdev.startexgame.GameObjects.Shields.SphereShield;
import com.nbdev.startexgame.GameObjects.Weapons.RotatorWeapon;
import com.nbdev.startexgame.ItemsBar.SlotItem;

import java.util.Random;

public class ItemsEmmiter {
    private Random random;
    private Vector2 v;

    public ItemsEmmiter() {
        random = new Random();
        v = new Vector2(0, -300f);
    }

    public SlotItem getItem(Vector2 pos) {
        SlotItem item;
        switch (random.nextInt(3)) {
            case 0:
                item = new SphereShield();
                System.out.println("new front shield");
                break;

            case 1:
                item = new FrontShield();
                System.out.println("new front shield");
                break;

            case 2:
            default:
                item = new RotatorWeapon(null, false);
                System.out.println("new weapon");
        }

        item.set(null, pos, v, 8f);
        return item;
    }
}
