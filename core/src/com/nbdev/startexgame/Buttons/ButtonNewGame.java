package com.nbdev.startexgame.Buttons;

import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Assets.GameAssets;

public class ButtonNewGame extends Button {
    public ButtonNewGame(Vector2 pos) {
        super(pos, GameAssets.getInstance().get(GameAssets.mainAtlas).findRegion("button_new_game"));
    }
}
