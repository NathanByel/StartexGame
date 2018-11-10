package com.nbdev.startexgame.Buttons;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.utils.Sprite;

public abstract class Button extends Sprite {
    public Button(Vector2 pos, TextureRegion region) {
        this.pos = pos;
        this.textureRegion = region;
        setHeight(region.getRegionHeight());
        setWidth(region.getRegionWidth());
    }

    public boolean touchDown(Vector2 coord2d, int pointer, int button) {
        if(isMe(coord2d)) {
            setScale(1.1f);
        }

        return false;
    }

    public boolean touchUp(Vector2 coord2d, int pointer, int button) {
        if(isMe(coord2d)) {
            buttonUp();
        }
        setScale(1f);
        return false;
    }

    public void buttonDown() {

    }

    public void buttonUp() {

    }
}
