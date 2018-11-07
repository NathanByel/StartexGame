package com.nbdev.startexgame.Pools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.nbdev.startexgame.GameObjects.Enemy;

import java.util.List;

public class EnemyPool {
    private final Array<Enemy> activeEnemies = new Array<Enemy>();
    private final Pool<Enemy> enemiesPool = new Pool<Enemy>() {
        @Override
        protected Enemy newObject() {
            return new Enemy();
        }
    };


    public Enemy obtain() {
        Enemy enemy = enemiesPool.obtain();
        activeEnemies.add(enemy);

        return enemy;
    }

    public void update(float delta) {
        for (int i = activeEnemies.size; --i >= 0;) {
            Enemy item = activeEnemies.get(i);
            if (item.alive) {
                item.update(delta);
            } else {
                activeEnemies.removeIndex(i);
                enemiesPool.free(item);
            }
        }
    }


    public void draw(SpriteBatch batch) {
        for (Enemy activeEnemy : activeEnemies) {
            if(activeEnemy.alive) {
                activeEnemy.draw(batch);
            }
        }
    }

    public Array<Enemy> getActive() {
        return activeEnemies;
    }
}
