package net.mikegraf.game.testing.states.play.triggers;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerItemCondition;
import net.mikegraf.game.states.play.triggers.RemoveSpriteTrigger;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class RemoveSpriteTriggerTests {

	@Test
	public void canExecuteConditionFail() {
		
		Player player = mock(Player.class);
		ICondition<Player> condition = mock(PlayerItemCondition.class);
		B2DSprite sprite = mock(B2DSprite.class);
		when(condition.accepts(player)).thenReturn(false);
		
		CollisionInfo info = new CollisionInfo();
		info.setPlayer(player);
		info.setOtherSprite(sprite);
		
		RemoveSpriteTrigger trigger = new RemoveSpriteTrigger(condition);
		
		assertFalse(trigger.canExecute(info));
	}
	
	@Test
	public void canExecuteConditionPass() {
		
		Player player = mock(Player.class);
		ICondition<Player> condition = mock(PlayerItemCondition.class);
		B2DSprite sprite = mock(B2DSprite.class);
		when(condition.accepts(player)).thenReturn(true);
		
		CollisionInfo info = new CollisionInfo();
		info.setPlayer(player);
		info.setOtherSprite(sprite);
		
		RemoveSpriteTrigger trigger = new RemoveSpriteTrigger(condition);
		
		assertTrue(trigger.canExecute(info));
	}
	
	@Test
	public void executeReadyForDisposalSet() {
		Player player = mock(Player.class);
		ICondition<Player> condition = mock(PlayerItemCondition.class);
		B2DSprite sprite = mock(B2DSprite.class);
		when(condition.accepts(player)).thenReturn(true);
		
		CollisionInfo info = new CollisionInfo();
		info.setPlayer(player);
		info.setOtherSprite(sprite);
		
		RemoveSpriteTrigger trigger = new RemoveSpriteTrigger(condition);
		
		trigger.execute(info);
		
		verify(sprite, times(1)).prepareForDisposal();
	}
}
