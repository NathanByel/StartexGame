package com.nbdev.startexgame.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class GameAssets extends AssetManager implements Disposable {
    private static GameAssets gameAssets;

    public static final AssetDescriptor<TextureAtlas> textureAtlas =
            new AssetDescriptor<TextureAtlas>("mainAtlas.tpack", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> shieldAtlas =
            new AssetDescriptor<TextureAtlas>("shieldAtlas.tpack", TextureAtlas.class);

    public static final AssetDescriptor<Texture> background =
            new AssetDescriptor<Texture>("bg2.jpg", Texture.class);

    public static final AssetDescriptor<Music> music =
            new AssetDescriptor<Music>("sound/music.mp3", Music.class);

    public static final AssetDescriptor<Sound> destroyedSound =
            new AssetDescriptor<Sound>("sound/destroyed.mp3", Sound.class);

    public static final AssetDescriptor<Sound> damageSound =
            new AssetDescriptor<Sound>("sound/damage.mp3", Sound.class);

    public static final AssetDescriptor<Sound> shotSound =
            new AssetDescriptor<Sound> ("sound/shot.mp3", Sound.class);

    public static final AssetDescriptor<Sound> shieldShotSound =
            new AssetDescriptor<Sound> ("sound/shield_shot.wav", Sound.class);

    public static final AssetDescriptor<Sound> shieldSound =
            new AssetDescriptor<Sound> ("sound/shield.mp3", Sound.class);

    public static GameAssets getInstance() {
        if (gameAssets == null) {
            gameAssets = new GameAssets();
        }
        return gameAssets;
    }

    public void load()
    {
        load(textureAtlas);
        load(shieldAtlas);
        load(background);

        load(shieldSound);
        load(shieldShotSound);
        load(shotSound);
        load(damageSound);
        load(destroyedSound);
        load(music);
    }


    @Override
    public void dispose() {
        get(shieldSound).stop();
        get(shieldShotSound).stop();
        get(shotSound).stop();
        get(damageSound).stop();
        get(destroyedSound).stop();
        get(music).stop();
        super.dispose();
        gameAssets = null;
    }
}
