package net.mikegraf.game.testing.states.play.actors.gameobjects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.actors.gameobjects.Door;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerItemCondition;

public class DoorTests {

    private final String ITEM_TYPE = "itemType";

    @Test
    public void operateReturnsTrueIfPlayerCanOperate() {
        B2DSprite sprite = mock(B2DSprite.class);
        ICondition<Player> cond = new PlayerItemCondition(ITEM_TYPE, false);
        Player player = mock(Player.class);
        when(player.hasItem(ITEM_TYPE)).thenReturn(true);
        Door door = new Door(sprite, cond);

        Assert.assertTrue(door.operate(player));
    }

    @Test
    public void operateReturnsFalseIfPlayerCantOperate() {
        B2DSprite sprite = mock(B2DSprite.class);
        ICondition<Player> cond = new PlayerItemCondition(ITEM_TYPE, false);
        Player player = mock(Player.class);
        when(player.hasItem(ITEM_TYPE)).thenReturn(false);
        Door door = new Door(sprite, cond);

        Assert.assertFalse(door.operate(player));
    }

    @Test
    public void operateHidesSpriteIfOperated() {
        B2DSprite sprite = mock(B2DSprite.class);
        when(sprite.isHidden()).thenReturn(false);
        ICondition<Player> cond = new PlayerItemCondition(ITEM_TYPE, false);
        Player player = mock(Player.class);
        when(player.hasItem(ITEM_TYPE)).thenReturn(true);
        Door door = new Door(sprite, cond);

        door.operate(player);

        verify(sprite, times(1)).hide();
    }

    @Test
    public void operateShowsSpriteIfOperatedAndAlreadyHidden() {
        B2DSprite sprite = mock(B2DSprite.class);
        when(sprite.isHidden()).thenReturn(true);
        ICondition<Player> cond = new PlayerItemCondition(ITEM_TYPE, false);
        Player player = mock(Player.class);
        when(player.hasItem(ITEM_TYPE)).thenReturn(true);
        Door door = new Door(sprite, cond);

        door.operate(player);

        verify(sprite, times(1)).show();
    }
}
