package net.mikegraf.game.states.play.controls;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class MyInputHandler extends InputAdapter {

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.LEFT) {
            MyInput.setKey(MyInput.WALK_LEFT, true);
        } else if (keycode == Keys.UP) {
            MyInput.setKey(MyInput.WALK_UP, true);
        } else if (keycode == Keys.RIGHT) {
            MyInput.setKey(MyInput.WALK_RIGHT, true);
        } else if (keycode == Keys.DOWN) {
            MyInput.setKey(MyInput.WALK_DOWN, true);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.LEFT) {
            MyInput.setKey(MyInput.WALK_LEFT, false);
        } else if (keycode == Keys.UP) {
            MyInput.setKey(MyInput.WALK_UP, false);
        } else if (keycode == Keys.RIGHT) {
            MyInput.setKey(MyInput.WALK_RIGHT, false);
        } else if (keycode == Keys.DOWN) {
            MyInput.setKey(MyInput.WALK_DOWN, false);
        }
        return true;
    }
}
