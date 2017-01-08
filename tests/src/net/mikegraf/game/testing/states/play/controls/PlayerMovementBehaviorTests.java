package net.mikegraf.game.testing.states.play.controls;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
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
    	inputData.downDown = false;
        inputData.upDown = true;
        inputData.leftDown = false;
        inputData.rightDown = false;

        Vector2 vec = behavior.createMovementVector();

        assertEquals(new Vector2(0, 1), vec);
    }

    @Test
    public void handleInputDown() {
    	inputData.downDown = true;
        inputData.upDown = false;
        inputData.leftDown = false;
        inputData.rightDown = false;

        Vector2 vec = behavior.createMovementVector();

        assertEquals(new Vector2(0, -1), vec);
    }

    @Test
    public void handleInputLeft() {
    	inputData.downDown = false;
        inputData.upDown = false;
        inputData.leftDown = true;
        inputData.rightDown = false;

        Vector2 vec = behavior.createMovementVector();

        assertEquals(new Vector2(-1, 0), vec);
    }

    @Test
    public void handleInputRight() {
    	inputData.downDown = false;
        inputData.upDown = false;
        inputData.leftDown = false;
        inputData.rightDown = true;

        Vector2 vec = behavior.createMovementVector();

        assertEquals(new Vector2(1, 0), vec);
    }
}
