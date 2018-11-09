package com.nbdev.startexgame.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sprite extends com.nbdev.startexgame.utils.Rect {
    private float angle;
    private float scale = 1f;
    protected TextureRegion textureRegion;

    public void draw(SpriteBatch batch) {
        batch.draw(
                textureRegion, //текущий регион
                getLeft(), getBottom(), // точка отрисовки
                halfWidth, halfHeight, // точка вращения
                getWidth(), getHeight(), // ширина и высота
                scale, scale, // масштаб по x и y
                angle // угол вращения
        );
    }

    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = textureRegion.getRegionWidth() / (float) textureRegion.getRegionHeight();
        setWidth(height * aspect);
    }

    public void update(float delta) {

    }

    public void resize(Rect worldBounds) {

    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
