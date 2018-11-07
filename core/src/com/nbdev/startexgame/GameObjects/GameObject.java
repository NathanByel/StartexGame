package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.nbdev.startexgame.Sprite;

public class GameObject extends Sprite implements Disposable {
    protected int health;
    public boolean alive;
    protected boolean canGetDamage;

    public GameObject(int health) {
        this(new Vector2(0f, 0f));
        this.health = health;
    }

    public GameObject(Vector2 pos) {
        this.pos = pos;
    }

    public boolean isCollision(GameObject other) {
        return false;
    }

    public void setPos(Vector2 pos) {
        this.pos.set(pos);
    }

    public Vector2 getPos() {
        return pos;
    }

    @Override
    public void dispose() {
    }
}
