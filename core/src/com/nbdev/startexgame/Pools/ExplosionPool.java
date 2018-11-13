package com.nbdev.startexgame.Pools;

import com.nbdev.startexgame.GameObjects.Explosion;

public class ExplosionPool extends GameObjectsPool<Explosion> {
    private static ExplosionPool explosionPool;

    private ExplosionPool() {
    }

    public static ExplosionPool getPool() {
        if(explosionPool == null) {
            explosionPool = new ExplosionPool();
        }
        return explosionPool;
    }

    @Override
    protected Explosion newObject() {
        return new Explosion();
    }

    @Override
    public void dispose() {
        super.dispose();
        explosionPool = null;
    }
}
