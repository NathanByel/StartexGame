package com.nbdev.startexgame.Buttons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.utils.Rect;

public abstract class Button extends Rect {
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

    public boolean isButton(Vector2 coord2d) {
        return  (coord2d.x > pos.x) && (coord2d.x < pos.x + region.getRegionWidth()) &&
                (coord2d.y > pos.y) && (coord2d.y < pos.y + region.getRegionHeight());
    }

    public boolean touchDown(Vector2 coord2d, int pointer, int button) {
        if(isButton(coord2d)) {
            scale = 1.2f;
        }

        return false;
    }

    public boolean touchUp(Vector2 coord2d, int pointer, int button) {
        if(isButton(coord2d)) {
            scale = 1f;
            buttonUp();
        }

        return false;
    }

    public void buttonDown() {

    }

    public void buttonUp() {

    }
}
