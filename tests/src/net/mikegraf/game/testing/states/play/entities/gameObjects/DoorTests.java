package net.mikegraf.game.testing.states.play.entities.gameObjects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.GameEntityState;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.gameObjects.Door;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerItemCondition;

public class DoorTests {

    private final String ITEM_TYPE = "itemType";

    private Door door;
    private ICollisionBehavior collB;
    private IController controller;
    private IView view;
    private Body body;
    private ICondition<Player> cond;
    private SoundEffectIndex soundEffectIndex;
    private Player player;

    @Before
    public void myBefore() {
        soundEffectIndex = mock(SoundEffectIndex.class);
        collB = mock(ICollisionBehavior.class);
        controller = mock(IController.class);
        view = mock(IView.class);
        body = mock(Body.class);
        cond = new PlayerItemCondition(ITEM_TYPE, false);
        door = new Door(collB, controller, view, body, soundEffectIndex, cond);
        player = mock(Player.class);
    }

    @After
    public void myAfter() {
        door = null;
        collB = null;
        controller = null;
        view = null;
        body = null;
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
