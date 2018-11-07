package com.nbdev.startexgame.Screens.MainMenu.Buttons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Button;

public class ButtonQuit extends Button {
    public ButtonQuit(Vector2 pos, TextureAtlas textureAtlas) {
        super(pos, textureAtlas.findRegion("quit"));
    }
}
