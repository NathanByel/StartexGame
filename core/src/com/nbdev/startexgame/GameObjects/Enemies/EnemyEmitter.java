package com.nbdev.startexgame.GameObjects.Enemies;

import com.badlogic.gdx.math.Vector2;
import com.nbdev.startexgame.Screens.GameScreen;

public class EnemyEmitter {
    private static final float GENERATE_INTERVAL = 3f;
    private EnemyFactory enemyFactory;
    private float generateTimer;

    public EnemyEmitter(EnemyFactory enemyFactory) {
        this.enemyFactory = enemyFactory;
    }

    public void generate(float delta) {
        generateTimer += delta;
        if(generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0f;

            Enemy enemy;
            float type = (float) Math.random();
            if (type < 0.7f) {
                enemy = enemyFactory.getEnemy(EnemyFactory.Type.SMALL_SHIP);
            } else if (type < 0.9) {
                enemy = enemyFactory.getEnemy(EnemyFactory.Type.MEDIUM_SHIP);
            } else {
                enemy = enemyFactory.getEnemy(EnemyFactory.Type.BIG_SHIP);
            }

            enemy.setPos(new Vector2((float)(Math.random() * (GameScreen.V_WIDTH - enemy.getWidth()) + enemy.getHalfWidth()),
                    GameScreen.V_HEIGHT + enemy.getHalfHeight()));
        }
    }
}
