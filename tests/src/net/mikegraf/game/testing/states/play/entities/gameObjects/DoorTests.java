package net.mikegraf.game.testing.states.play.entities.gameObjects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.GameEntityState;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.gameObjects.Door;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerItemCondition;

public class DoorTests {

    private final String ITEM_TYPE = "itemType";

    private Door door;
    private PhysicsModel physModel;
    private IView view;
    private IController controller;
    private ICondition<Player> cond;
    private SoundEffectIndex soundEffectIndex;
    private Player player;

    @Before
    public void myBefore() {
        soundEffectIndex = mock(SoundEffectIndex.class);
        physModel = mock(PhysicsModel.class);
        view = mock(IView.class);
        controller = mock(IController.class);
        cond = new PlayerItemCondition(ITEM_TYPE, false);
        door = new Door(physModel, view, controller, soundEffectIndex, cond);
        player = mock(Player.class);
    }

    @After
    public void myAfter() {
        door = null;
        physModel = null;
        view = null;
        controller = null;
        soundEffectIndex = null;
        cond = null;
        player = null;
    }

    @Test
    public void operateReturnsTrueIfPlayerCanOperate() {
        when(player.hasItem(ITEM_TYPE)).thenReturn(true);

        Assert.assertTrue(door.operate(player));
    }

    @Test
    public void operateReturnsFalseIfPlayerCantOperate() {
        when(player.hasItem(ITEM_TYPE)).thenReturn(false);

        Assert.assertFalse(door.operate(player));
    }

    @Test
    public void operateHidesEntityIfOperated() {
        when(player.hasItem(ITEM_TYPE)).thenReturn(true);

        door.operate(player);

        Assert.assertEquals(GameEntityState.READY_TO_HIDE, door.state);
    }

    @Test
    public void operateShowsSpriteIfOperatedAndAlreadyHidden() {
        door.state = GameEntityState.HIDDEN;
        when(player.hasItem(ITEM_TYPE)).thenReturn(true);

        door.operate(player);

        Assert.assertEquals(GameEntityState.READY_TO_SHOW, door.state);
    }
}
