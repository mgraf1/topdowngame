package net.mikegraf.game.testing.states.play.entities.gameObjects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.gameObjects.Door;
import net.mikegraf.game.states.play.entities.gameObjects.Switch;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;

public class SwitchTests {

    private Switch s;
    private PhysicsModel physModel;
    private IView view;
    private IController controller;
    private Player player;
    private Door door;
    private SoundEffectIndex soundEffectIndex;

    @Before
    public void myBefore() {
        player = mock(Player.class);
        physModel = mock(PhysicsModel.class);
        view = mock(IView.class);
        controller = mock(IController.class);
        door = mock(Door.class);
        soundEffectIndex = mock(SoundEffectIndex.class);
        s = new Switch(physModel, view, controller, soundEffectIndex);
        s.setDoor(door);
    }

    @After
    public void myAfter() {
        physModel = null;
        view = null;
        controller = null;
        player = null;
        door = null;
        soundEffectIndex = null;
        s = null;
    }

    @Test
    public void operatePlaysTurnSound() {
        s.operate(player);

        verify(soundEffectIndex, times(1)).playSound(Switch.TURN_SFX);
    }

    @Test
    public void operatePlaysTurnSoundOnEachTurn() {
        s.operate(player);
        s.operate(player);

        verify(soundEffectIndex, times(2)).playSound(Switch.TURN_SFX);
    }

    @Test
    public void operateTurnsSwitchOn() {
        s.operate(player);

        Assert.assertTrue(s.isOn());
    }

    @Test
    public void operateTurnsSwitchBackOff() {
        s.operate(player);
        s.operate(player);

        Assert.assertFalse(s.isOn());
    }

    @Test
    public void operateTogglesDoor() {
        s.operate(player);

        verify(door, times(1)).toggle();
    }

    @Test
    public void operateSetsOnAnimation() {
        s.operate(player);

        verify(view, times(1)).setMode(Switch.ON_ANIMATION_NAME);
    }

    @Test
    public void operateSetsOffAnimation() {
        s.operate(player);
        s.operate(player);

        verify(view, times(1)).setMode(Switch.OFF_ANIMATION_NAME);
    }
}
