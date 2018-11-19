package com.nbdev.startexgame.Assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class GameAssets extends AssetManager implements Disposable {
    private static GameAssets gameAssets;

    public static final AssetDescriptor<TextureAtlas> mainAtlas =
            new AssetDescriptor<TextureAtlas>("images/mainAtlas.tpack", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> itemsAtlas =
            new AssetDescriptor<TextureAtlas>("images/itemsAtlas.tpack", TextureAtlas.class);

    public static final AssetDescriptor<Texture> itemSlot =
            new AssetDescriptor<Texture>("images/item_slot.png", Texture.class);

    public static final AssetDescriptor<Texture> background =
            new AssetDescriptor<Texture>("images/bg2.jpg", Texture.class);

    public static final AssetDescriptor<Music> music =
            new AssetDescriptor<Music>("sound/music.mp3", Music.class);

    public static final AssetDescriptor<Sound> destroyedSound =
            new AssetDescriptor<Sound>("sound/destroyed.mp3", Sound.class);

    public static final AssetDescriptor<Sound> hitSound =
            new AssetDescriptor<Sound>("sound/hit.wav", Sound.class);

    public static final AssetDescriptor<Sound> hit1Sound =
            new AssetDescriptor<Sound>("sound/hit1.wav", Sound.class);

    public static final AssetDescriptor<Sound> hit2Sound =
            new AssetDescriptor<Sound>("sound/hit2.wav", Sound.class);

    public static final AssetDescriptor<Sound> shotSound =
            new AssetDescriptor<Sound>("sound/shot.wav", Sound.class);

    public static final AssetDescriptor<Sound> shot1Sound =
            new AssetDescriptor<Sound>("sound/shot1.wav", Sound.class);

    public static final AssetDescriptor<Sound> shieldHitSound =
            new AssetDescriptor<Sound>("sound/shield_hit.wav", Sound.class);

    public static final AssetDescriptor<Sound> shieldSound =
            new AssetDescriptor<Sound>("sound/shield.mp3", Sound.class);

    public static GameAssets getInstance() {
        if (gameAssets == null) {
            gameAssets = new GameAssets();
        }
        return gameAssets;
    }

    public void load()
    {
        load(mainAtlas);
        load(itemsAtlas);
        load(background);
        load(itemSlot);

        load(shotSound);
        load(shot1Sound);
        load(hitSound);
        load(hit1Sound);
        load(hit2Sound);
        load(shieldHitSound);
        load(shieldSound);
        load(destroyedSound);
        load(music);
    }

    @Override
    public void dispose() {
        get(shieldSound).stop();
        get(shotSound).stop();
        get(shot1Sound).stop();
        get(hitSound).stop();
        get(hit1Sound).stop();
        get(hit2Sound).stop();
        get(shieldHitSound).stop();
        get(destroyedSound).stop();
        get(music).stop();
        super.dispose();
        gameAssets = null;
    }
}
