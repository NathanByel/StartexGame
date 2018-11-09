package com.nbdev.startexgame.Buttons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class ButtonPlay extends Button {
    public ButtonPlay(Vector2 pos, TextureAtlas textureAtlas) {
        super(pos, textureAtlas.findRegion("play"));
    }
}
