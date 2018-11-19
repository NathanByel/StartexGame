package com.nbdev.startexgame.ItemsBar;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.utils.Rect;

public interface SlotItem {
    float getValue();
    boolean isAlive();
    TextureRegion getTextureRegion();
    void set(Object owner, Vector2 pos, Vector2 v, float amount);
    Rect getRect();
    void setOwner(Object owner);
    Object getOwner();
    void update(float delta);
    void draw(SpriteBatch batch);
}
