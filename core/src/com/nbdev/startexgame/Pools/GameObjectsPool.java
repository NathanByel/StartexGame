package com.nbdev.startexgame.Pools;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.nbdev.startexgame.GameObjects.GameObject;

import java.util.Iterator;

public abstract class GameObjectsPool<T extends GameObject> extends Pool<T> {
    private final Array<T> activeObjects = new Array<T>();

    @Override
    public T obtain() {
        T object = super.obtain();
        object.alive = true;
        activeObjects.add(object);
        return object;
    }

    @Override
    public void free(T object) {
        super.free(object);
        activeObjects.removeValue(object, false);
    }

    public void update(float delta) {
        for (T object : activeObjects) {
            if (object.alive) {
                object.update(delta);
            } else {
                free(object);
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for (GameObject activeObject : activeObjects) {
            if(activeObject.alive) {
                activeObject.draw(batch);
            }
        }
    }

    public Array<T> getActive() {
        return activeObjects;
    }

    public void dispose() {
        freeAll(activeObjects);
        clear();
    }
}
