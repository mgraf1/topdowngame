package net.mikegraf.game.testing.states.play.actors.gameobjects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.actors.gameobjects.Door;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerItemCondition;

public class DoorTests {

    private final String ITEM_TYPE = "itemType";
    private final String NAME = "name";

    private Door door;
    private B2DSprite sprite;
    private ICondition<Player> cond;
    private SoundEffectIndex soundEffectIndex;
    private Player player;

    @Before
    public void myBefore() {
        soundEffectIndex = mock(SoundEffectIndex.class);
        sprite = mock(B2DSprite.class);
        cond = new PlayerItemCondition(ITEM_TYPE, false);
        door = new Door(sprite, soundEffectIndex, NAME, cond);
        player = mock(Player.class);
    }

    @After
    public void myAfter() {
        door = null;
        sprite = null;
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
    public void operateHidesSpriteIfOperated() {
        when(sprite.isHidden()).thenReturn(false);
        when(player.hasItem(ITEM_TYPE)).thenReturn(true);

        door.operate(player);

        Assert.assertTrue(sprite.readyForHiding);
    }

    @Test
    public void operateShowsSpriteIfOperatedAndAlreadyHidden() {
        when(sprite.isHidden()).thenReturn(true);
        when(player.hasItem(ITEM_TYPE)).thenReturn(true);

        door.operate(player);

        Assert.assertTrue(sprite.readyForShowing);
    }
}
