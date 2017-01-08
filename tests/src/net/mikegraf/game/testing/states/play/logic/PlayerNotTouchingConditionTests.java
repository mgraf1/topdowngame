package net.mikegraf.game.testing.states.play.logic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.logic.PlayerNotTouchingCondition;

public class PlayerNotTouchingConditionTests {

    private static final String OBJECT_NAME = "name";

    @Test
    public void acceptsReturnsTrueIfPlayerNotTouchingOperable() {
        Player player = mock(Player.class);
        when(player.isTouching(OBJECT_NAME)).thenReturn(false);
        PlayerNotTouchingCondition cond = new PlayerNotTouchingCondition(OBJECT_NAME);

        Assert.assertTrue(cond.accepts(player));
    }

    @Test
    public void acceptsReturnsFalseIfPlayerTouchingOperable() {
        Player player = mock(Player.class);
        when(player.isTouching(OBJECT_NAME)).thenReturn(true);
        PlayerNotTouchingCondition cond = new PlayerNotTouchingCondition(OBJECT_NAME);

        Assert.assertFalse(cond.accepts(player));
    }
}
