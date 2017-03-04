package net.mikegraf.game.states.play.entities.controller;

import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.states.play.controls.PlayerInputData;
import net.mikegraf.game.states.play.controls.PlayerInputHandler;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.player.Player;

public class PlayerController implements IController {

    private Vector2 movementVector;
    private PlayerInputHandler inputHandler;

    public PlayerController(PlayerInputHandler inputHandler) {
        this.movementVector = new Vector2();
        this.inputHandler = inputHandler;
    }

    @Override
    public Vector2 update(GameEntity entity, float deltaTime) {
        PlayerInputData inputData = inputHandler.getPlayerInput();
        boolean upDown = inputData.upDown;
        boolean downDown = inputData.downDown;
        boolean leftDown = inputData.leftDown;
        boolean rightDown = inputData.rightDown;

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

        if (inputData.operatePressed) {
            ((Player) entity).operateTouchedObjects();
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
