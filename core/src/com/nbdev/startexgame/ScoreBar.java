package com.nbdev.startexgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreBar {
    private static BitmapFont font;
    private GlyphLayout scoreGlyph;
    private GlyphLayout lifeGlyph;

    public ScoreBar() {
        font = new BitmapFont();
        font.setColor(Color.CYAN);

        font.getData().setScale(5f);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        scoreGlyph = new GlyphLayout();
        lifeGlyph = new GlyphLayout();
    }

    public void setScore(int score) {
        scoreGlyph.setText(font, "Score: " + score);
    }

    public void setHealth(int health) {
        lifeGlyph.setText(font, "Life: " + health);
    }

    public void draw(SpriteBatch batch) {
        font.draw(
                batch,
                scoreGlyph,
                20,
                BaseScreen.V_HEIGHT - 20);

        font.draw(
                batch,
                lifeGlyph,
                BaseScreen.V_WIDTH - 300,
                BaseScreen.V_HEIGHT - 20);
    }
}
