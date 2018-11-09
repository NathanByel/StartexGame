package com.nbdev.startexgame.Atlas;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class MainAtlas extends TextureAtlas{
    private static TextureAtlas textureAtlas;

    private MainAtlas() {
    }

    public static TextureAtlas getAtlas() {
        if(textureAtlas == null) {
            textureAtlas = new TextureAtlas("mainAtlas.tpack");
        }

        return textureAtlas;
    }

    @Override
    public void dispose() {
        super.dispose();
        textureAtlas = null;
    }
}
