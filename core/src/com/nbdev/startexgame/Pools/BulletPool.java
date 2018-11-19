package com.nbdev.startexgame.Pools;

import com.nbdev.startexgame.GameObjects.Weapons.Bullet;

public class BulletPool extends GameObjectsPool<Bullet> {
    private static BulletPool bulletPool;

    public static BulletPool getPool() {
        if(bulletPool == null) {
            bulletPool = new BulletPool();
            System.out.println("new bullet pool");
        }
        return bulletPool;
    }

    @Override
    protected Bullet newObject() {
        System.out.println("new bullet");
        return new Bullet();
    }

    @Override
    public void dispose() {
        super.dispose();
        bulletPool = null;
    }
}
