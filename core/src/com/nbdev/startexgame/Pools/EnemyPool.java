package com.nbdev.startexgame.Pools;

import com.nbdev.startexgame.GameObjects.Enemies.Enemy;

public class EnemyPool extends GameObjectsPool<Enemy> {
    private static EnemyPool enemyPool;

    @Override
    protected Enemy newObject() {
        return new Enemy();
    }

    public static EnemyPool getPool() {
        if(enemyPool == null) {
            enemyPool = new EnemyPool();
        }
        return enemyPool;
    }

    @Override
    public void dispose() {
        super.dispose();
        enemyPool = null;
    }
}
