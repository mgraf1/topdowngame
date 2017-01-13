package net.mikegraf.game.testing.states.play.entities.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.states.play.controls.PlayerInputData;
import net.mikegraf.game.states.play.controls.PlayerInputHandler;
import net.mikegraf.game.states.play.entities.controller.PlayerController;
import net.mikegraf.game.states.play.entities.player.Player;

public class PlayerControllerTests {

    private PlayerController behavior;
    private PlayerInputHandler inputHandler;
    private PlayerInputData inputData;
    private Player player;

    @Before
    public void myBefore() {
        inputHandler = mock(PlayerInputHandler.class);
        behavior = new PlayerController(inputHandler);
        inputData = new PlayerInputData();
        player = mock(Player.class);
        when(inputHandler.getPlayerInput()).thenReturn(inputData);
    }

    @After
    public void myAfter() {
        inputHandler = null;
        behavior = null;
        inputData = null;
        player = null;
    }

    @Test
    public void handleInputNoInput() {
        inputData.downDown = false;
        inputData.upDown = false;
        inputData.leftDown = false;
        inputData.rightDown = false;

        Vector2 vec = behavior.update(player, 0);

        assertEquals(new Vector2(0, 0), vec);
    }

    @Test
    public void handleInputUp() {
    	inputData.downDown = false;
        inputData.upDown = true;
        inputData.leftDown = false;
        inputData.rightDown = false;

        Vector2 vec = behavior.update(player, 0);

        assertEquals(new Vector2(0, 1), vec);
    }

    @Test
    public void handleInputDown() {
    	inputData.downDown = true;
        inputData.upDown = false;
        inputData.leftDown = false;
        inputData.rightDown = false;

        Vector2 vec = behavior.update(player, 0);

        assertEquals(new Vector2(0, -1), vec);
    }

    @Test
    public void handleInputLeft() {
    	inputData.downDown = false;
        inputData.upDown = false;
        inputData.leftDown = true;
        inputData.rightDown = false;

        Vector2 vec = behavior.update(player, 0);

        assertEquals(new Vector2(-1, 0), vec);
    }

    @Test
    public void handleInputRight() {
    	inputData.downDown = false;
        inputData.upDown = false;
        inputData.leftDown = false;
        inputData.rightDown = true;

        Vector2 vec = behavior.update(player, 0);

        assertEquals(new Vector2(1, 0), vec);
    }
    
    @Test
    public void updatePlayerOperatesTouchedItemsOnButtonPress() {
    	inputData.operatePressed = true;
    	
    	behavior.update(player, 0);
    	
    	verify(player, times(1)).operateTouchedObjects();
    }
    
    @Test
    public void updatePlayerOperatesDoesntTouchItemsOnNoButtonPress() {
    	inputData.operatePressed = false;
    	
    	behavior.update(player, 0);
    	
    	verify(player, times(0)).operateTouchedObjects();
    }
}
