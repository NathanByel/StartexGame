package com.nbdev.startexgame.GameObjects.Weapons;

public class WeaponGenerator {
    public enum Type {
        SMALL_WEAPON,
        MEDIUM_WEAPON,
        BIG_WEAPON
    }

    private static final float SMALL_BULLET_VY = -0.3f;
    private static final float SMALL_BULLET_HEIGHT = 0.01f;
    private static final int SMALL_BULLET_DAMAGE = 1;
    private static final float SMALL_RELOAD_INTERVAL = 3f;

    private static final float MEDIUM_BULLET_VY = -0.3f;
    private static final float MEDIUM_BULLET_HEIGHT = 0.02f;
    private static final int MEDIUM_BULLET_DAMAGE = 5;
    private static final float MEDIUM_RELOAD_INTERVAL = 4f;

    private static final float BIG_BULLET_VY = -0.25f;
    private static final float BIG_BULLET_HEIGHT = 0.06f;
    private static final int BIG_BULLET_DAMAGE = 12;
    private static final float BIG_RELOAD_INTERVAL = 4f;

    public static Weapon getWeapon(WeaponGenerator.Type type) {
        Weapon weapon = WeaponPool.getPool().obtain();
        switch(type) {
            case SMALL_WEAPON:
                weapon.set(
                        SMALL_BULLET_VY,
                        SMALL_BULLET_HEIGHT,
                        SMALL_BULLET_DAMAGE,
                        SMALL_RELOAD_INTERVAL
                );
                break;

            case MEDIUM_WEAPON:
                weapon.set(
                        MEDIUM_BULLET_VY,
                        MEDIUM_BULLET_HEIGHT,
                        MEDIUM_BULLET_DAMAGE,
                        MEDIUM_RELOAD_INTERVAL
                );
                break;

            case BIG_WEAPON:
                weapon.set(
                        BIG_BULLET_VY,
                        BIG_BULLET_HEIGHT,
                        BIG_BULLET_DAMAGE,
                        BIG_RELOAD_INTERVAL
                );
                break;
        }
        return weapon;
    }
}