package com.nbdev.startexgame.Assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class MenuAssets extends AssetManager implements Disposable {
    //public AssetManager manager = new AssetManager();

    public static final AssetDescriptor<Texture> background =
            new AssetDescriptor<Texture>("bg2.jpg", Texture.class);

    public static final AssetDescriptor<TextureAtlas> textureAtlas =
            new AssetDescriptor<TextureAtlas>("menu/menu.tpack", TextureAtlas.class);

    public static final AssetDescriptor<Sound> buttonSound =
            new AssetDescriptor<Sound>("sound/button.wav", Sound.class);

    public static final AssetDescriptor<Music> music =
            new AssetDescriptor<Music>("sound/main_menu.mp3", Music.class);

    public void load()
    {
        //manager.load(background);
        //manager.load(textureAtlas);

        //manager.load(buttonSound);
        //manager.load(music);
        load(background);
        load(textureAtlas);

        load(buttonSound);
        load(music);
    }


    @Override
    public void dispose() {
        //manager.get(buttonSound).stop();
        //manager.get(music).stop();
        //manager.dispose();

        get(buttonSound).stop();
        get(music).stop();
        super.dispose();
    }
}
