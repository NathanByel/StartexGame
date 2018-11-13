package com.nbdev.startexgame.Buttons;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Assets.GameAssets;

public class ButtonNewGame extends Button {
    public ButtonNewGame(Vector2 pos, TextureAtlas textureAtlas) {
        super(pos, GameAssets.getInstance().get(GameAssets.textureAtlas).findRegion("button_new_game"));
    }
}