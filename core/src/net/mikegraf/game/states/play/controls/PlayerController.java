package net.mikegraf.game.states.play.controls;

import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.states.play.actors.Player;

public class PlayerController {

    private Player player;
    private Vector2 movementVector;

    public PlayerController(Player p) {
        player = p;
        movementVector = new Vector2(0, 0);
    }

    public void handleInput(boolean upDown, boolean downDown, boolean leftDown, boolean rightDown,
            boolean operatePressed) {

        handleMovement(upDown, downDown, leftDown, rightDown);

        if (operatePressed) {
            player.operateTouchedObjects();
        }
    }

    private void handleMovement(boolean upDown, boolean downDown, boolean leftDown, boolean rightDown) {

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

        player.move(movementVector);

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
