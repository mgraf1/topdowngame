package net.mikegraf.game.states.play.entities.behavior.movement;

import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.states.play.controls.MyInput;

public class PlayerMovementBehavior implements IMovementBehavior {

    private Vector2 movementVector;

    public PlayerMovementBehavior() {
        movementVector = new Vector2();
    }

    @Override
    public Vector2 createMovementVector() {
        boolean upDown = MyInput.isDown(MyInput.WALK_UP);
        boolean downDown = MyInput.isDown(MyInput.WALK_DOWN);
        boolean leftDown = MyInput.isDown(MyInput.WALK_LEFT);
        boolean rightDown = MyInput.isDown(MyInput.WALK_RIGHT);

        int numDirections = countInputs(upDown, downDown, leftDown, rightDown);

        if (numDirections == 0) {
            movementVector.x = 0;
            movementVector.y = 0;
        } else if (numDirections > 1) {
            // Continue momentum.
        } else if (upDown) {
            movementVector.y = 1;
            movementVector.x = 0;
        } else if (downDown) {
            movementVector.y = -1;
            movementVector.x = 0;
        } else if (leftDown) {
            movementVector.y = 0;
            movementVector.x = -1;
        } else if (rightDown) {
            movementVector.y = 0;
            movementVector.x = 1;
        }

        return movementVector;
    }

    private int countInputs(boolean one, boolean two, boolean three, boolean four) {
        int count = 0;
        if (one)
            count++;
        if (two)
            count++;
        if (three)
            count++;
        if (four)
            count++;
        return count;
    }
}
