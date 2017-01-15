package net.mikegraf.game.testing.states.play.logic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.logic.PlayerNotTouchingCondition;

public class PlayerNotTouchingConditionTests {

    private static final int OBJECT_ID = 42;

    @Test
    public void acceptsReturnsTrueIfPlayerNotTouchingOperable() {
        Player player = mock(Player.class);
        when(player.isTouching(OBJECT_ID)).thenReturn(false);
        PlayerNotTouchingCondition cond = new PlayerNotTouchingCondition(OBJECT_ID);

        Assert.assertTrue(cond.accepts(player));
    }

    @Test
    public void acceptsReturnsFalseIfPlayerTouchingOperable() {
        Player player = mock(Player.class);
        when(player.isTouching(OBJECT_ID)).thenReturn(true);
        PlayerNotTouchingCondition cond = new PlayerNotTouchingCondition(OBJECT_ID);

        Assert.assertFalse(cond.accepts(player));
    }
}
