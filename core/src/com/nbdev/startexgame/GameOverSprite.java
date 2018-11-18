package com.nbdev.startexgame;

import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.utils.Sprite;

public class GameOverSprite extends Sprite {
    public GameOverSprite(Vector2 pos) {
        textureRegion = GameAssets.getInstance().get(GameAssets.textureAtlas).findRegion("message_game_over");
        this.pos.set(pos);
        setHeightProportion(textureRegion.getRegionHeight());
        setScale(2f);
    }
}
