package net.mikegraf.game.states.play.controls;

public class PlayerInputHandler {

    private PlayerInputData data;

    public PlayerInputHandler() {
        data = new PlayerInputData();
    }

    public PlayerInputData getPlayerInput() {
        data.downDown = MyInput.isDown(MyInput.WALK_DOWN);
        data.upDown = MyInput.isDown(MyInput.WALK_UP);
        data.leftDown = MyInput.isDown(MyInput.WALK_LEFT);
        data.rightDown = MyInput.isDown(MyInput.WALK_RIGHT);
        return data;
    }
}
