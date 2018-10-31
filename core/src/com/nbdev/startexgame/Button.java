package com.nbdev.startexgame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Button {
    private Vector2 pos;
    private TextureRegion region;
    private float scale = 1f;

    public Button(Vector2 pos, TextureRegion region) {
        this.pos = pos;
        this.region = region;
    }

    public void render(SpriteBatch batch) {
        batch.draw( region,
                    pos.x, pos.y,
                    0, 0,
                    region.getRegionWidth(), region.getRegionHeight(),
                    1, scale,
                    0);
    }

    public boolean isButton(Vector2 coord) {
        return  (coord.x > pos.x) && (coord.x < pos.x + region.getRegionWidth()) &&
                (coord.y > pos.y) && (coord.y < pos.y + region.getRegionHeight());
    }

    public void mouseIn() {
        scale = 1.2f;
    }

    public void mouseOut() {
        scale = 1f;
    }

    public abstract void buttonDown();
    public abstract void buttonUp();
}
