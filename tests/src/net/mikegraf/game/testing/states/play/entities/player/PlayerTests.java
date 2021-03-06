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

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.gameObjects.OperableGameEntity;
import net.mikegraf.game.states.play.entities.items.Item;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.player.PlayerProfile;
import net.mikegraf.game.states.play.entities.view.IView;

public class PlayerTests {

    private Player player;
    private PhysicsModel physModel;
    private IView view;
    private IController controller;
    private SoundEffectIndex soundEffectIndex;

    @Before
    public void myBefore() {
        physModel = mock(PhysicsModel.class);
        view = mock(IView.class);
        controller = mock(IController.class);
        soundEffectIndex = mock(SoundEffectIndex.class);
        player = new Player(physModel, view, controller, soundEffectIndex);
    }

    @After
    public void myAfter() {
        physModel = null;
        view = null;
        controller = null;
        player = null;
    }

    @Test
    public void afterUpdateWalkUpAnimationCalled() {
        Vector2 moveVector = new Vector2(0, 1);

        player.afterUpdate(moveVector);

        verify(view).setMode(Player.MOVE_UP_ANIMATION_NAME);
    }

    @Test
    public void afterUpdateWalkDownAnimationCalled() {
        Vector2 moveVector = new Vector2(0, -1);

        player.afterUpdate(moveVector);

        verify(view).setMode(Player.MOVE_DOWN_ANIMATION_NAME);
    }

    @Test
    public void afterUpdateWalkLeftAnimationCalled() {
        Vector2 moveVector = new Vector2(-1, 0);

        player.afterUpdate(moveVector);

        verify(view).setMode(Player.MOVE_LEFT_ANIMATION_NAME);
    }

    @Test
    public void afterUpdateWalkRightAnimationCalled() {
        Vector2 moveVector = new Vector2(1, 0);

        player.afterUpdate(moveVector);

        verify(view).setMode(Player.MOVE_RIGHT_ANIMATION_NAME);
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
        int opId = 6;
        OperableGameEntity operable = mock(OperableGameEntity.class);
        when(operable.getId()).thenReturn(opId);
        player.touch(operable);

        assertTrue(player.isTouching(opId));
    }

    @Test
    public void isTouchingReturnsFalseIfPlayerDidntTouchObject() {
        int opId = 6;
        OperableGameEntity operable = mock(OperableGameEntity.class);
        when(operable.getId()).thenReturn(opId);
        player.touch(operable);
        player.stopTouching(operable);

        assertFalse(player.isTouching(opId));
    }

    @Test
    public void operableTouchedObjectsCallsOperateOnAll() {
        int opId = 6;
        int opId2 = 7;
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

    @Test
    public void damageKillsPlayerIfNoHealthLeft() {
        PlayerProfile profile = new PlayerProfile(2, 1);
        player.setProfile(profile);

        player.damage(1);

        assertTrue(player.isDead());
    }

    @Test
    public void damageDoesntKillPlayerIfHealthLeft() {
        PlayerProfile profile = new PlayerProfile(2, 2);
        player.setProfile(profile);

        player.damage(1);

        assertFalse(player.isDead());
    }

    @Test
    public void damageKillsPlayerHitSfxNotPlayed() {
        PlayerProfile profile = new PlayerProfile(2, 1);
        player.setProfile(profile);

        player.damage(1);

        verify(soundEffectIndex, times(0)).playSound(Player.HIT_SFX);
    }

    @Test
    public void damageDoesntKillPlayerHitSfxPlayed() {
        PlayerProfile profile = new PlayerProfile(2, 2);
        player.setProfile(profile);

        player.damage(1);

        verify(soundEffectIndex, times(1)).playSound(Player.HIT_SFX);
    }
}
