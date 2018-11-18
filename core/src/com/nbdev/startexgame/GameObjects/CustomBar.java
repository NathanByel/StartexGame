package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.nbdev.startexgame.utils.Sprite;

public class CustomBar {
    private ProgressBar progressBar;

    private static Drawable getColoredDrawable(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));

        pixmap.dispose();

        return drawable;
    }

    /**
     * @param width of the health bar
     * @param height of the health bar
     */
    public CustomBar(int width, int height, boolean vertical, Color color, Color backColor) {
        ProgressBar.ProgressBarStyle style = new ProgressBar.ProgressBarStyle();
        style.background = getColoredDrawable(width, height, backColor);
        if(vertical) {
            style.knob= getColoredDrawable(width, 0, color);
        } else {
            style.knob = getColoredDrawable(0, height, color);
        }

        style.knobBefore = getColoredDrawable(width, height, color);

        progressBar = new ProgressBar(0f, 1f, 0.01f, vertical, style);

        progressBar.setWidth(width);
        progressBar.setHeight(height);

        progressBar.setAnimateDuration(0.0f);
        progressBar.setValue(1f);

        progressBar.setAnimateDuration(0.25f);
    }

    public void update(float delta) {
        progressBar.act(delta);
    }

    public void draw(SpriteBatch batch, float alpha) {
        progressBar.draw(batch, alpha);
    }

    public void setRange(float min, float max) {
        progressBar.setRange(min, max);
    }

    public void setValue(float value) {
        progressBar.setValue(value);
    }

    public float getValue() {
        return progressBar.getValue();
    }

    public void setPosition(float x, float y) {
        progressBar.setPosition(x, y);
    }

    public void setWidth(float width) {
        progressBar.setWidth(width);
    }

    public float getWidth() {
        return progressBar.getWidth();
    }

    public void setHeight(float height) {
        progressBar.setHeight(height);
    }

    public float getHeight() {
        return progressBar.getHeight();
    }
}
