package com.nbdev.startexgame.Pools;

import com.nbdev.startexgame.GameObjects.Weapons.Bullet;

public class BulletPool extends GameObjectsPool<Bullet> {
    private static BulletPool bulletPool;

    private BulletPool() {
    }

    public static BulletPool getPool() {
        if(bulletPool == null) {
            bulletPool = new BulletPool();
        }
        return bulletPool;
    }

    @Override
    protected Bullet newObject() {
        return new Bullet();
    }

    @Override
    public void dispose() {
        super.dispose();
        bulletPool = null;
    }
}
