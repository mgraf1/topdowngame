package net.mikegraf.game.testing.states.play.entities.player;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;
import net.mikegraf.game.states.play.entities.gameObjects.OperableGameEntity;
import net.mikegraf.game.states.play.entities.items.Item;
import net.mikegraf.game.states.play.entities.player.Player;

public class PlayerTests {

    private static final String ID = "player";

    private Player player;
    private ICollisionBehavior collB;
    private IMovementBehavior moveB;
    private IRenderBehavior rendB;
    private Body body;

    @Before
    public void myBefore() {
        collB = mock(ICollisionBehavior.class);
        moveB = mock(IMovementBehavior.class);
        rendB = mock(IRenderBehavior.class);
        body = mock(Body.class);
        player = new Player(ID, collB, moveB, rendB, body);
    }

    @After
    public void myAfter() {
        collB = null;
        moveB = null;
        rendB = null;
        body = null;
        player = null;
    }

    @Test
    public void beforeMoveWalkUpAnimationCalled() {
        Vector2 moveVector = new Vector2(0, 1);

        player.beforeMove(moveVector);

        verify(rendB).setMode(Player.MOVE_UP_ANIMATION_NAME);
    }

    @Test
    public void beforeMoveWalkDownAnimationCalled() {
        Vector2 moveVector = new Vector2(0, -1);

        player.beforeMove(moveVector);

        verify(rendB).setMode(Player.MOVE_DOWN_ANIMATION_NAME);
    }

    @Test
    public void beforeMoveWalkLeftAnimationCalled() {
        Vector2 moveVector = new Vector2(-1, 0);

        player.beforeMove(moveVector);

        verify(rendB).setMode(Player.MOVE_LEFT_ANIMATION_NAME);
    }

    @Test
    public void beforeMoveWalkRightAnimationCalled() {
        Vector2 moveVector = new Vector2(1, 0);

        player.beforeMove(moveVector);

        verify(rendB).setMode(Player.MOVE_RIGHT_ANIMATION_NAME);
    }

    @Test
    public void pickupItemFullInventory() {
        Item item = mock(Item.class);

        player.setInventorySize(0);

        assertFalse(player.pickupItem(item));
    }

    @Test
    public void pickupItem() {
        Item item = mock(Item.class);

        player.setInventorySize(2);

        assertTrue(player.pickupItem(item));
    }

    @Test
    public void isTouchingReturnsTrueIfPlayerTouchedObject() {
        String opId = "id";
        OperableGameEntity operable = mock(OperableGameEntity.class);
        when(operable.getId()).thenReturn(opId);
        player.touch(operable);

        assertTrue(player.isTouching(opId));
    }

    @Test
    public void isTouchingReturnsFalseIfPlayerDidntTouchObject() {
        String opId = "id";
        OperableGameEntity operable = mock(OperableGameEntity.class);
        when(operable.getId()).thenReturn(opId);
        player.touch(operable);
        player.stopTouching(operable);

        assertFalse(player.isTouching(opId));
    }

    @Test
    public void operableTouchedObjectsCallsOperateOnAll() {
        String opId = "id";
        String opId2 = "id2";
        OperableGameEntity operable = mock(OperableGameEntity.class);
        when(operable.getId()).thenReturn(opId);
        OperableGameEntity operable2 = mock(OperableGameEntity.class);
        when(operable.getId()).thenReturn(opId2);
        player.touch(operable);
        player.touch(operable2);

        player.operateTouchedObjects();

        verify(operable, times(1)).operate(player);
        verify(operable2, times(1)).operate(player);
    }
}
