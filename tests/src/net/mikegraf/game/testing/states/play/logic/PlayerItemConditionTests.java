package net.mikegraf.game.testing.states.play.logic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerItemCondition;

public class PlayerItemConditionTests {

    private final String ITEM_TYPE = "itemType";

    @Test
    public void acceptsConsumesItemIfConsumesTrue() {
        ICondition<Player> cond = new PlayerItemCondition(ITEM_TYPE, true);
        Player player = mock(Player.class);
        when(player.hasItem(ITEM_TYPE)).thenReturn(true);

        cond.accepts(player);

        verify(player, times(1)).removeItem(ITEM_TYPE);
    }

    @Test
    public void acceptsDoesntConsumeItemIfConsumesFalse() {
        ICondition<Player> cond = new PlayerItemCondition(ITEM_TYPE, false);
        Player player = mock(Player.class);
        when(player.hasItem(ITEM_TYPE)).thenReturn(true);

        cond.accepts(player);

        verify(player, times(0)).removeItem(ITEM_TYPE);
    }

    @Test
    public void acceptsReturnsTrueIfPlayerHasItem() {
        ICondition<Player> cond = new PlayerItemCondition(ITEM_TYPE, false);
        Player player = mock(Player.class);
        when(player.hasItem(ITEM_TYPE)).thenReturn(true);

        Assert.assertTrue(cond.accepts(player));
    }

    @Test
    public void acceptsReturnsFalseIfPlayerDoesntHaveItem() {
        ICondition<Player> cond = new PlayerItemCondition(ITEM_TYPE, false);
        Player player = mock(Player.class);
        when(player.hasItem(ITEM_TYPE)).thenReturn(false);

        Assert.assertFalse(cond.accepts(player));
    }

    @Test
    public void acceptsDoesntConsumeItemIfPlayerDoesntHave() {
        ICondition<Player> cond = new PlayerItemCondition(ITEM_TYPE, true);
        Player player = mock(Player.class);
        when(player.hasItem(ITEM_TYPE)).thenReturn(false);

        cond.accepts(player);

        verify(player, times(0)).removeItem(ITEM_TYPE);
    }
}
