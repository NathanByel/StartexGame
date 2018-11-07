package com.nbdev.startexgame.Pools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.nbdev.startexgame.GameObjects.Bullet;

public class BulletPool {

    // Массив, содержащий активные пули.
    private final Array<Bullet> activeBullets = new Array<Bullet>();

    // Пул для пуль.
    private final Pool<Bullet> bulletPool = new Pool<Bullet>() {
        @Override
        protected Bullet newObject() {
            return new Bullet();
        }
    };


    public Bullet obtain() {
        Bullet bullet = bulletPool.obtain();
        activeBullets.add(bullet);

        return bullet;
    }

    public void update(float delta) {
        // пули
        for (int i = activeBullets.size; --i >= 0;) {
            Bullet item = activeBullets.get(i);
            if (item.alive) {
                item.update(delta);
            } else {
                activeBullets.removeIndex(i);
                bulletPool.free(item);
            }
        }
    }


    public void draw(SpriteBatch batch) {
        for (Bullet activeBullet : activeBullets) {
            if(activeBullet.alive) {
                activeBullet.draw(batch);
            }
        }
    }
}
