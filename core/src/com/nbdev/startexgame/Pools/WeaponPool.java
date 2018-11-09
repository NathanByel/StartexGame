package com.nbdev.startexgame.Pools;

import com.nbdev.startexgame.GameObjects.Weapons.Weapon;

public class WeaponPool extends GameObjectsPool<Weapon> {
    private static WeaponPool weaponPool;

    private WeaponPool() {
    }

    public static WeaponPool getPool() {
        if(weaponPool == null) {
            weaponPool = new WeaponPool();
        }
        return weaponPool;
    }

    @Override
    protected Weapon newObject() {
        return new Weapon();
    }

    @Override
    public void dispose() {
        super.dispose();
        weaponPool = null;
    }
}
