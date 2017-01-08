package net.mikegraf.game.testing.states.play.controls;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.states.play.controls.PlayerInputData;
import net.mikegraf.game.states.play.controls.PlayerInputHandler;
import net.mikegraf.game.states.play.entities.behavior.movement.PlayerMovementBehavior;

public class PlayerMovementBehaviorTests {

    private PlayerMovementBehavior behavior;
    private PlayerInputHandler inputHandler;
    private PlayerInputData inputData;

    @Before
    public void myBefore() {
        inputHandler = mock(PlayerInputHandler.class);
        behavior = new PlayerMovementBehavior(inputHandler);
        inputData = new PlayerInputData();
        when(inputHandler.getPlayerInput()).thenReturn(inputData);
    }

    @After
    public void myAfter() {
        inputHandler = null;
        behavior = null;
        inputData = null;
    }

    @Test
    public void handleInputNoInput() {
        inputData.downDown = false;
        inputData.upDown = false;
        inputData.leftDown = false;
        inputData.rightDown = false;

        Vector2 vec = behavior.createMovementVector();

        assertEquals(new Vector2(0, 0), vec);
    }

    @Test
    public void handleInputUp() {
        Vector2 desiredMovementVector = new Vector2(0, 1);

        playerController.handleInput(true, false, false, false, false);

        verify(player, times(1)).move(desiredMovementVector);
    }

    @Test
    public void handleInputDown() {
        Vector2 desiredMovementVector = new Vector2(0, -1);

        playerController.handleInput(false, true, false, false, false);

        verify(player, times(1)).move(desiredMovementVector);
    }

    @Test
    public void handleInputLeft() {
        Vector2 desiredMovementVector = new Vector2(-1, 0);

        playerController.handleInput(false, false, true, false, false);

        verify(player, times(1)).move(desiredMovementVector);
    }

    @Test
    public void handleInputRight() {
        Vector2 desiredMovementVector = new Vector2(1, 0);

        playerController.handleInput(false, false, false, true, false);

        verify(player, times(1)).move(desiredMovementVector);
    }

    @Test
    public void handleInputMultipleContinueUp() {
        Vector2 desiredMovementVector = new Vector2(0, 1);

        playerController.handleInput(true, false, false, false, false);
        playerController.handleInput(true, true, true, true, false);

        verify(player, times(2)).move(desiredMovementVector);
    }

    @Test
    public void handleInputMultipleContinueDown() {
        Vector2 desiredMovementVector = new Vector2(0, -1);

        playerController.handleInput(false, true, false, false, false);
        playerController.handleInput(true, true, true, true, false);

        verify(player, times(2)).move(desiredMovementVector);
    }

    @Test
    public void handleInputMultipleContinueLeft() {
        Vector2 desiredMovementVector = new Vector2(-1, 0);

        playerController.handleInput(false, false, true, false, false);
        playerController.handleInput(true, true, true, true, false);

        verify(player, times(2)).move(desiredMovementVector);
    }

    @Test
    public void handleInputMultipleContinueRight() {
        Vector2 desiredMovementVector = new Vector2(1, 0);

        playerController.handleInput(false, false, false, true, false);
        playerController.handleInput(true, true, true, true, false);

        verify(player, times(2)).move(desiredMovementVector);
    }

    @Test
    public void handleInputOperateTouchedObjectsCalledIfOperatePressed() {
        playerController.handleInput(false, false, false, false, true);

        verify(player, times(1)).operateTouchedObjects();
    }

    @Test
    public void handleInputOperateTouchedObjectsNotCalledIfOperateNotPressed() {
        playerController.handleInput(false, false, false, false, false);

        verify(player, times(0)).operateTouchedObjects();
    }
}
