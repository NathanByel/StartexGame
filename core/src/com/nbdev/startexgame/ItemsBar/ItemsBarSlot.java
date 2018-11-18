package com.nbdev.startexgame.ItemsBar;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nbdev.startexgame.Assets.GameAssets;
import com.nbdev.startexgame.GameObjects.CustomBar;
import com.nbdev.startexgame.Screens.GameScreen;
import com.nbdev.startexgame.utils.Sprite;

public class ItemsBarSlot extends Sprite {
    private final float SHOW_SPEED = 250f;
    private final float HIDE_SPEED = 250f;
    private final float TRANSPARENCY = 0.7f;

    public enum State {
        HIDING, SHOWING, VISIBLE, INVISIBLE
    }

    private int barBottomOffset;
    private int barRightOffset;

    private State state;
    private float xPos;
    private Sprite sprite;
    private CustomBar bar;
    private SlotItem item;

    public ItemsBarSlot() {
        textureRegion = new TextureRegion( GameAssets.getInstance().get(GameAssets.itemSlot) );
        setHeightProportion(150f);

        state = State.INVISIBLE;
        xPos = GameScreen.V_WIDTH + getWidth();

        setRight(xPos);

        sprite = new Sprite();

        bar = new CustomBar((int)(getWidth() * 0.1f), (int)(getHeight() * 0.8f), true, Color.BLUE, Color.CLEAR);
        barBottomOffset = (int)((getHeight() - bar.getHeight()) / 2f);
        barRightOffset = (int)(bar.getWidth() * 1.9f);
    }

    @Override
    public void update(float delta) {
        switch (state) {
            case HIDING:
                if(xPos < GameScreen.V_WIDTH + getWidth()) {
                    xPos += HIDE_SPEED * delta;
                } else {
                    xPos = GameScreen.V_WIDTH + getWidth();
                    state = State.INVISIBLE;
                }
                setRight(xPos);
                break;

            case SHOWING:
                if(xPos > GameScreen.V_WIDTH) {
                    xPos -= SHOW_SPEED * delta;
                } else {
                    xPos = GameScreen.V_WIDTH;
                    state = State.VISIBLE;
                }
                setRight(xPos);
                break;

            case VISIBLE:
                sprite.setTextureRegion(item.getTextureRegion());
                bar.setValue(item.getValue());
                //if(item.getValue() <= 0) {
                //    state = State.HIDING;
                //    item = null;
                //}
                break;
        }

        if(state != State.INVISIBLE) {
            sprite.setLeft(getLeft());
            sprite.setBottom(getBottom());

            bar.setPosition(getRight() - barRightOffset, getBottom() + barBottomOffset);
            bar.update(delta);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if(state != State.INVISIBLE) {
            batch.setColor(1f, 1f, 1f, TRANSPARENCY);
            super.draw(batch);
            sprite.draw(batch);
            batch.setColor(1f, 1f, 1f, 1f);

            bar.draw(batch, TRANSPARENCY);
        }
    }

    public void show() {
        if(state == State.INVISIBLE) {
            state = State.SHOWING;
        }
    }

    public void hide() {
        if(state == State.VISIBLE) {
            state = State.HIDING;
        }
    }

    public void setBarRange(float min, float max) {
        bar.setRange(min, max);
    }

    public void setBarValue(float value) {
        bar.setValue(value);
    }

    public void setItem(SlotItem item) {
        this.item = item;
        sprite.setTextureRegion(item.getTextureRegion());
        sprite.setHeightProportion(getHeight());

        bar.setRange(0f, item.getValue());
        bar.setValue(item.getValue());

        state = State.SHOWING;
    }

    public SlotItem getItem() {
        return item;
    }

    public State getState() {
        return state;
    }
}
