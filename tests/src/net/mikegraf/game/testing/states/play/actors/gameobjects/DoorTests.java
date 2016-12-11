package net.mikegraf.game.testing.states.play.actors.gameobjects;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.actors.gameobjects.Door;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerItemCondition;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class DoorTests {

	@Test
	public void operatePlayerNoKeyRemoveItem() {
		
		String key = "key";
		B2DSprite sprite = mock(B2DSprite.class);
		
		Player player = mock(Player.class);
		
		ICondition<Player> cond = mock(PlayerItemCondition.class);
		when(cond.accepts(player)).thenReturn(false);
		
		Door door = new Door(sprite, cond, key);
		
		door.operate(player);
		
		verify(player, times(0)).removeItem(key);
	}
	
	@Test
	public void operatePlayerNoKeyReadyForDisposal() {
		
		String key = "key";
		B2DSprite sprite = mock(B2DSprite.class);
		
		Player player = mock(Player.class);
		
		ICondition<Player> cond = mock(PlayerItemCondition.class);
		when(cond.accepts(player)).thenReturn(false);
		
		Door door = new Door(sprite, cond, key);
		
		door.operate(player);
		
		verify(sprite, times(0)).prepareForDisposal();
	}
	
	@Test
	public void operatePlayerHasKeyRemoveItem() {
		
		String key = "key";
		B2DSprite sprite = mock(B2DSprite.class);
		
		Player player = mock(Player.class);
		
		ICondition<Player> cond = mock(PlayerItemCondition.class);
		when(cond.accepts(player)).thenReturn(true);
		
		Door door = new Door(sprite, cond, key);
		
		door.operate(player);
		
		verify(player, times(1)).removeItem(key);
	}
	
	@Test
	public void operatePlayerHasKeyReadyForDisposal() {
		
		String key = "key";
		B2DSprite sprite = mock(B2DSprite.class);
		
		Player player = mock(Player.class);
		
		ICondition<Player> cond = mock(PlayerItemCondition.class);
		when(cond.accepts(player)).thenReturn(true);
		
		Door door = new Door(sprite, cond, key);
		
		door.operate(player);
		
		verify(sprite, times(1)).prepareForDisposal();
	}
}
