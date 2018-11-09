package com.nbdev.startexgame.Pools;

import com.nbdev.startexgame.GameObjects.Enemy;

public class EnemyPool extends GameObjectsPool<Enemy> {
    @Override
    protected Enemy newObject() {
        return new Enemy();
    }
}
