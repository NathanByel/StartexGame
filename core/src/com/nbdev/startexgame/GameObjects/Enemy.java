package com.nbdev.startexgame.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.HealthBar;

public class Enemy extends GameObject {
    HealthBar healthBar;

    public Enemy(TextureAtlas textureAtlas) {
        super(100);

        this.textureRegion = textureAtlas.findRegion("enemy0");
        healthBar = new HealthBar((int)(textureRegion.getRegionWidth() * 0.8f), 5, Color.GREEN, Color.BLACK);
        healthBar.setRange(0f, 100f);
        healthBar.setValue(health);

        setPos(new Vector2(BaseScreen.V_WIDTH / 2, 800));

        setHeight(textureRegion.getRegionHeight());
        setWidth(textureRegion.getRegionWidth());
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        healthBar.setPosition(pos.x - healthBar.getWidth()/2, pos.y + getHalfHeight() + 15);
        healthBar.setValue(health);
        healthBar.act(Gdx.graphics.getDeltaTime());
        healthBar.draw(batch, 1f);
    }
}
