package net.mikegraf.game.testing.states.play.actors.gameobjects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.actors.gameobjects.Door;
import net.mikegraf.game.states.play.actors.gameobjects.Switch;

public class SwitchTests {

    private final String NAME = "name";

    private Switch s;
    private Player player;
    private B2DSprite sprite;
    private Door door;

    @Before
    public void myBefore() {
        sprite = mock(B2DSprite.class);
        player = mock(Player.class);
        door = mock(Door.class);
        s = new Switch(sprite, NAME);
        s.setDoor(door);
    }

    @After
    public void myAfter() {
        sprite = null;
        player = null;
        door = null;
        s = null;
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
    public void operateOperatesDoor() {
        s.operate(player);

        verify(door, times(1)).operate(player);
    }

    @Test
    public void operateSetsOnAnimation() {
        s.operate(player);

        verify(sprite, times(1)).setAnimation(Switch.ON_ANIMATION_NAME);
    }

    @Test
    public void operateSetsOffAnimation() {
        s.operate(player);
        s.operate(player);

        verify(sprite, times(1)).setAnimation(Switch.OFF_ANIMATION_NAME);
    }
}
