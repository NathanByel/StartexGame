package com.nbdev.startexgame.Screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.nbdev.startexgame.BaseScreen;
import com.nbdev.startexgame.GameObjects.Bullet;
import com.nbdev.startexgame.GameObjects.Enemy;
import com.nbdev.startexgame.GameObjects.Player;

public class GameScreen extends BaseScreen {
    private final Game game;
    private Texture background;
    private TextureAtlas mainAtlas;

    private Player player;
    private Enemy enemy;
    private Vector2 direction;

    // Массив, содержащий активные пули.
    private final Array<Bullet> activeBullets = new Array<Bullet>();

    // Пул для пуль.
    private final Pool<Bullet> bulletPool = new Pool<Bullet>() {
        @Override
        protected Bullet newObject() {
            return new Bullet();
        }
    };

    public GameScreen(final Game game) {
        this.game = game;
        direction = new Vector2(0,0);
        background = new Texture("background.jpg");
        mainAtlas = new TextureAtlas("mainAtlas.tpack");

        player = new Player(mainAtlas);
        enemy = new Enemy(mainAtlas);
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    private void update(float delta) {
        enemy.update(delta);
        player.update(delta);

        // пули
        for (int i = activeBullets.size; --i >= 0;) {
            Bullet item = activeBullets.get(i);
            if (item.alive) {
                item.update(delta);
            } else {
                activeBullets.removeIndex(i);
                bulletPool.free(item);
            }
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0.128f, 0.53f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(background, 0, 0);
        player.draw(batch);
        enemy.draw(batch);

        for (Bullet activeBullet : activeBullets) {
            if(activeBullet.alive) {
                activeBullet.draw(batch);
            }
        }

        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }

    // Control
    @Override
    public boolean mouseMoved(Vector2 coord2d) {
        coord2d.y = 200;
        player.setPos(coord2d);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 coord2d, int pointer, int button) {
        System.out.println(button);
        if(button == 0) {
            Bullet item = bulletPool.obtain();
            item.set(
                    player,
                    mainAtlas.findRegion("bulletMainShip"),
                    player.getPos(),
                    new Vector2(0f, 300f),
                    0,
                    null,
                    20);

            activeBullets.add(item);
            player.shot();
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        /*switch(keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.A:
                direction.x = -1;
                break;

            case Input.Keys.RIGHT:
            case Input.Keys.D:
                direction.x = 1;
                break;

            case Input.Keys.UP:
            case Input.Keys.W:
                direction.y = 1;
                break;

            case Input.Keys.DOWN:
            case Input.Keys.S:
                direction.y = -1;
                break;
        }
        if(!direction.isZero()) {
            cat.moveDirection(direction);
        }*/
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        /*switch(keycode) {
            case Input.Keys.LEFT:
            case Input.Keys.A:
                direction.x = 0;
                break;

            case Input.Keys.RIGHT:
            case Input.Keys.D:
                direction.x = 0;
                break;

            case Input.Keys.UP:
            case Input.Keys.W:
                direction.y = 0;
                break;

            case Input.Keys.DOWN:
            case Input.Keys.S:
                direction.y = 0;
                break;
        }

        if(direction.isZero()) {
            cat.stop();
        } else {
            cat.moveDirection(direction);
        }*/
        return false;
    }
}
