package net.mikegraf.game.testing.states.play.triggers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import net.mikegraf.game.states.play.Play;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerNoCondition;
import net.mikegraf.game.states.play.triggers.EndTrigger;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class EndTriggerTests {

	@Test
	public void canExecuteConditionFail() {
		
		Play playState = mock(Play.class);
		Player player = mock(Player.class);
		CollisionInfo info = new CollisionInfo();
		info.setPlayer(player);
		info.setPlayState(playState);
		ICondition<Player> condition = mock(PlayerNoCondition.class);
		when(condition.accepts(player)).thenReturn(false);
		
		EndTrigger trigger = new EndTrigger(condition, 0, 0);
		assertFalse(trigger.canExecute(info));
	}
	
	@Test
	public void canExecuteConditionPass() {
		
		Play playState = mock(Play.class);
		Player player = mock(Player.class);
		CollisionInfo info = new CollisionInfo();
		info.setPlayer(player);
		info.setPlayState(playState);
		ICondition<Player> condition = mock(PlayerNoCondition.class);
		when(condition.accepts(player)).thenReturn(true);
		
		EndTrigger trigger = new EndTrigger(condition, 0, 0);
		assertTrue(trigger.canExecute(info));
	}
	
	@Test
	public void executeSetNextLevelCalled() {
		
		Play playState = mock(Play.class);
		Player player = mock(Player.class);
		CollisionInfo info = new CollisionInfo();
		info.setPlayer(player);
		info.setPlayState(playState);
		ICondition<Player> condition = mock(PlayerNoCondition.class);
		when(condition.accepts(player)).thenReturn(true);
		
		EndTrigger trigger = new EndTrigger(condition, 0, 0);
		trigger.execute(info);
		verify(playState, times(1)).setNextLevel(anyInt(), anyInt());
	}
	
	@Test
	public void executeSetNextLevelCalledX() {
		
		Play playState = mock(Play.class);
		Player player = mock(Player.class);
		CollisionInfo info = new CollisionInfo();
		info.setPlayer(player);
		info.setPlayState(playState);
		ICondition<Player> condition = mock(PlayerNoCondition.class);
		when(condition.accepts(player)).thenReturn(true);
		
		EndTrigger trigger = new EndTrigger(condition, 3, 0);
		trigger.execute(info);
		verify(playState, times(1)).setNextLevel(eq(3), anyInt());
	}
	
	@Test
	public void executeSetNextLevelCalledY() {
		
		Play playState = mock(Play.class);
		Player player = mock(Player.class);
		CollisionInfo info = new CollisionInfo();
		info.setPlayer(player);
		info.setPlayState(playState);
		ICondition<Player> condition = mock(PlayerNoCondition.class);
		when(condition.accepts(player)).thenReturn(true);
		
		EndTrigger trigger = new EndTrigger(condition, 3, 7);
		trigger.execute(info);
		verify(playState, times(1)).setNextLevel(anyInt(), eq(7));
	}
}
