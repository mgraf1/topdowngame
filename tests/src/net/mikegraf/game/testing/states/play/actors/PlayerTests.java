package net.mikegraf.game.testing.states.play.actors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Item;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.actors.gameobjects.IOperable;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class PlayerTests {

    private B2DSprite sprite;
    private Player player;

    @Before
    public void myBefore() {
        sprite = mock(B2DSprite.class);
        player = new Player(sprite);
    }

    @After
    public void myAfter() {
        sprite = null;
        player = null;
    }

    @Test
    public void moveSpriteMoveCalled() {
        Vector2 moveVector = new Vector2(0, 0);

        player.move(moveVector);

        verify(sprite).move(0.0f, 0.0f);
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
    public void moveLeftAnimationSet() {
        String animationName = "moveLeft";
        Vector2 movementVector = new Vector2(-1, 0);

        player.move(movementVector);

        verify(sprite, times(1)).setAnimation(animationName);
    }

    @Test
    public void moveUpAnimationSet() {
        String animationName = "moveUp";
        Vector2 movementVector = new Vector2(0, 1);

        player.move(movementVector);

        verify(sprite, times(1)).setAnimation(animationName);
    }

    @Test
    public void moveRightAnimationSet() {
        String animationName = "moveRight";
        Vector2 movementVector = new Vector2(1, 0);

        player.move(movementVector);

        verify(sprite, times(1)).setAnimation(animationName);
    }

    @Test
    public void moveDownAnimationSet() {
        String animationName = "moveDown";
        Vector2 movementVector = new Vector2(0, -1);

        player.move(movementVector);

        verify(sprite, times(1)).setAnimation(animationName);
    }

    @Test
    public void noMoveAnimationSetLeft() {
        String animationName = "moveLeft";
        Vector2 movementVector = new Vector2(-1, 0);
        Vector2 movementVector2 = new Vector2(0, 0);

        player.move(movementVector);
        player.move(movementVector2);

        verify(sprite, times(1)).setAnimation(animationName);
    }

    @Test
    public void noMoveAnimationSetUp() {
        String animationName = "moveUp";
        Vector2 movementVector = new Vector2(0, 1);
        Vector2 movementVector2 = new Vector2(0, 0);

        player.move(movementVector);
        player.move(movementVector2);

        verify(sprite, times(1)).setAnimation(animationName);
    }

    @Test
    public void noMoveAnimationSetRight() {
        String animationName = "moveRight";
        Vector2 movementVector = new Vector2(1, 0);
        Vector2 movementVector2 = new Vector2(0, 0);

        player.move(movementVector);
        player.move(movementVector2);

        verify(sprite, times(1)).setAnimation(animationName);
    }

    @Test
    public void noMoveAnimationSetDown() {
        String animationName = "moveDown";
        Vector2 movementVector = new Vector2(0, -1);
        Vector2 movementVector2 = new Vector2(0, 0);

        player.move(movementVector);
        player.move(movementVector2);

        verify(sprite, times(1)).setAnimation(animationName);
    }

    @Test
    public void isTouchingReturnsTrueIfPlayerTouchedObject() {
        String opId = "id";
        IOperable operable = mock(IOperable.class);
        when(operable.getId()).thenReturn(opId);
        player.touch(operable);

        assertTrue(player.isTouching(opId));
    }

    @Test
    public void isTouchingReturnsFalseIfPlayerDidntTouchObject() {
        String opId = "id";
        IOperable operable = mock(IOperable.class);
        when(operable.getId()).thenReturn(opId);
        player.touch(operable);
        player.stopTouching(operable);

        assertFalse(player.isTouching(opId));
    }

    @Test
    public void operableTouchedObjectsCallsOperateOnAll() {
        String opId = "id";
        String opId2 = "id2";
        IOperable operable = mock(IOperable.class);
        when(operable.getId()).thenReturn(opId);
        IOperable operable2 = mock(IOperable.class);
        when(operable.getId()).thenReturn(opId2);
        player.touch(operable);
        player.touch(operable2);

        player.operateTouchedObjects();

        verify(operable, times(1)).operate(player);
        verify(operable2, times(1)).operate(player);
    }
}