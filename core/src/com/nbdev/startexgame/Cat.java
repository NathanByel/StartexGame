package com.nbdev.startexgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Cat {
    private static final float SCALE_FACTOR = 0.8f;
    private static final float CAT_WALK_SPEED = 300f;

    // Анимации загружаются один раз для всех будущих объектов
    private static Animation<Texture> idleAnimation  = null;
    private static Animation<Texture> walkAnimation  = null;

    private int scaledWidth;
    private int scaledHeight;

    private Animation<Texture> currentAnimation;
    private float animationTime = 0;

    private Vector2 currentPos = new Vector2();
    private Vector2 newPos = new Vector2();
    private Vector2 direction = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 movement = new Vector2();

    private CatState catState = CatState.IDLE;
    private CatState oldCatState = CatState.IDLE;

    private enum CatState {
        IDLE,
        WALK,
        WALK_TO
    }

    public Cat() {
        this(new Vector2(0,0));
    }

    public Cat(Vector2 point) {
        currentPos.set(point);
        newPos.set(currentPos);

        if( (idleAnimation == null) || (walkAnimation == null) ) {
            Array<Texture> frames = new Array<Texture>();
            for (int i = 1; i <= 10; i++) {
                frames.add(new Texture(Gdx.files.internal("cat/Idle (" + i + ").png")));
            }
            idleAnimation = new Animation<Texture>(0.1f, frames, Animation.PlayMode.LOOP);
            frames.clear();

            for (int i = 1; i <= 10; i++) {
                frames.add(new Texture(Gdx.files.internal("cat/Walk (" + i + ").png")));
            }
            walkAnimation = new Animation<Texture>(0.05f, frames, Animation.PlayMode.LOOP);
            frames.clear();
        }

        scaledWidth = (int) (idleAnimation.getKeyFrame(0).getWidth() * SCALE_FACTOR);
        scaledHeight = (int) (idleAnimation.getKeyFrame(0).getHeight() * SCALE_FACTOR);

        changeCatState(CatState.IDLE);
    }

    public void goTo(Vector2 point) {
        newPos.set(point.x - scaledWidth / 2f, point.y - scaledHeight / 2f);
        changeCatState(CatState.WALK_TO);
        System.out.println("Cat go to: x=" + point.x + ", y=" + point.y);
    }

    public void moveDirection(Vector2 direction) {
        changeCatState(CatState.WALK);
        this.direction.set(direction);
        System.out.println("Cat move direction: " + direction);
    }

    public void stop() {
        changeCatState(CatState.IDLE);
        System.out.println("Cat stop");
    }

    public void render(SpriteBatch batch) {
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);

        animationTime += dt;
        Texture currentFrame = currentAnimation.getKeyFrame(animationTime);
        batch.draw (currentFrame,
                currentPos.x, currentPos.y,
                scaledWidth, scaledHeight,
                0, 0,
                currentFrame.getWidth(), currentFrame.getHeight(),
                (direction.x < 0), false);
    }

    private void update(float dt) {
        switch(catState) {
            case IDLE:
                break;

            case WALK_TO:
                direction.set(newPos);
                direction.sub(currentPos);
                direction.nor();

                velocity.set(direction);
                velocity.scl(CAT_WALK_SPEED);

                movement.set(velocity);
                movement.scl(dt);

                if (currentPos.dst2(newPos) > movement.len2()) {
                    currentPos.add(movement);
                } else {
                    currentPos.set(newPos);
                    changeCatState(CatState.IDLE);
                }
                break;

            case WALK:
                velocity.set(direction);
                velocity.scl(CAT_WALK_SPEED);

                movement.set(velocity);
                movement.scl(dt);
                currentPos.add(movement);
                break;
        }
    }

    private void changeCatState(CatState newState) {
        oldCatState = catState;
        animationTime = 0;
        catState = newState;
        System.out.println("Old cat state: " + oldCatState + ", State: " + newState);

        switch(catState) {
            case IDLE:      currentAnimation = idleAnimation;   break;

            case WALK:
            case WALK_TO:   currentAnimation = walkAnimation;   break;
        }
    }
}
