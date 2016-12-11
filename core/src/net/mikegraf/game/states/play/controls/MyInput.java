package net.mikegraf.game.states.play.controls;

public class MyInput {

    // Constants.
    public static final int NUM_KEYS = 4;
    public static int WALK_LEFT = 0;
    public static int WALK_UP = 1;
    public static int WALK_RIGHT = 2;
    public static int WALK_DOWN = 3;

    private static boolean[] keys = new boolean[NUM_KEYS];
    private static boolean[] prevKeys = new boolean[NUM_KEYS];

    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            prevKeys[i] = keys[i];
        }
    }

    public static void setKey(int i, boolean b) {
        keys[i] = b;
    }

    public static boolean isDown(int i) {
        return keys[i];
    }

    public static boolean isPressed(int i) {
        return keys[i] && !prevKeys[i];
    }
}
