package net.mikegraf.game.testing.states.play.entities.collision;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.collision.PlayerCollisionBehavior;
import net.mikegraf.game.states.play.entities.gameObjects.OperableGameEntity;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.levels.Level;

public class PlayerCollisionBehaviorTests {
	
	private PlayerCollisionBehavior pcb;
	private Level level;
	private OperableGameEntity entity;
	private Player player;
	
	@Before
	public void myBefore() {
		pcb = new PlayerCollisionBehavior();
		entity = mock(OperableGameEntity.class);
		player = mock(Player.class);
		level = mock(Level.class);
	}
	
	@After
	public void myAfter() {
		pcb = null;
		entity = null;
		player = null;
		level = null;
	}

	@Test
	public void handleCollisionCallsHandleCollision() {
		CollisionInfo info = new CollisionInfo(level, player, entity);
		
		pcb.handleCollision(info);
		
		verify(entity).handleCollision(info);
	}
	
	@Test
	public void handleEndCollisionDoesNothingIfOtherNotOperableGameEntity() {
		GameEntity notAnOperableGameEntity = null;
		CollisionInfo info = new CollisionInfo(level, player, (GameEntity)notAnOperableGameEntity);
		
		pcb.handleEndCollision(info);
		
		verify(entity, times(0)).handleEndCollision(info);
	}
}
