package net.mikegraf.game.states.play.controls;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

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
        } else if (keycode == Keys.CONTROL_LEFT) {
            MyInput.setKey(MyInput.OPERATE, true);
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
        } else if (keycode == Keys.CONTROL_LEFT) {
            MyInput.setKey(MyInput.OPERATE, false);
        }
        return true;
    }
}
