package com.nbdev.startexgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import java.util.Random;

public class Background implements Disposable {
    private Texture background;
    private Vector2 pos;
    private Vector2 v;
    private float timer;
    private Random random;

    public Background() {
        background = new Texture("bg2.jpg");
        pos = new Vector2();
        v = new Vector2(-20f, -400f);
        random = new Random();
    }

    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (pos.y < -background.getHeight()) {
            pos.y = 0;
        }

        timer += delta;
        if(timer > 5) {
            timer = 0;

            if(random.nextBoolean()) {
                v.x *= -1;
            } else {
                //v.x *= random.nextFloat() * 5;
                v.x += 1f;
            }
        }

        if(v.x > 0 && pos.x >= 0) {
            v.x *= -1;
        } else if(v.x < 0 && Math.abs(pos.x) > Math.abs(background.getWidth() - BaseScreen.V_WIDTH)) {
            v.x *= -1;
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(background, pos.x, pos.y + background.getHeight());
        batch.draw(background, pos.x, pos.y);
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
