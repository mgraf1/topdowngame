package net.mikegraf.game.testing.states.play.contact;

import static org.mockito.Mockito.*;
import org.junit.Test;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import net.mikegraf.game.states.play.Play;
import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Item;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.actors.gameobjects.IOperable;
import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.contact.MyContactListener;
import net.mikegraf.game.states.play.triggers.ITrigger;

public class MyContactListenerTests {

	@Test
	public void beginContactNoPlayerTriggerA() {
		
		ITrigger trigger = mock(ITrigger.class);
		
		Body bodyA = mock(Body.class);
		when(bodyA.getUserData()).thenReturn(trigger);

		Body bodyB = mock(Body.class);
		when(bodyB.getUserData()).thenReturn(new Integer(2));
		
		Fixture fixtureA = mock(Fixture.class);
		when(fixtureA.getBody()).thenReturn(bodyA);
		
		Fixture fixtureB = mock(Fixture.class);
		when(fixtureB.getBody()).thenReturn(bodyB);
		
		Contact contact  = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		Play playState = mock(Play.class);
		Player player = mock(Player.class);
		CollisionInfo info = new CollisionInfo();
		info.setPlayer(player);
		info.setPlayState(playState);
		
		MyContactListener listener = new MyContactListener(playState);
		listener.beginContact(contact);
		
		verify(trigger, never()).canExecute(info);
	}
	
	@Test
	public void beginContactNoPlayerTriggerB() {
		
		ITrigger trigger = mock(ITrigger.class);
		
		Body bodyA = mock(Body.class);
		when(bodyA.getUserData()).thenReturn(new Integer(2));

		Body bodyB = mock(Body.class);
		when(bodyB.getUserData()).thenReturn(trigger);
		
		Fixture fixtureA = mock(Fixture.class);
		when(fixtureA.getBody()).thenReturn(bodyA);
		
		Fixture fixtureB = mock(Fixture.class);
		when(fixtureB.getBody()).thenReturn(bodyB);
		
		Contact contact  = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		Play playState = mock(Play.class);
		Player player = mock(Player.class);
		CollisionInfo info = new CollisionInfo();
		info.setPlayer(player);
		info.setPlayState(playState);
		
		MyContactListener listener = new MyContactListener(playState);
		listener.beginContact(contact);
		
		verify(trigger, never()).canExecute(info);
	}
	
	@Test
	public void beginContactPlayerA() {
		
		ITrigger trigger = mock(ITrigger.class);
		Player player = mock(Player.class);
		
		B2DSprite spriteA = mock(B2DSprite.class);
		when(spriteA.getUserData()).thenReturn(player);
		
		String stringA = "player";
		String stringB = "trigger";
		
		Body bodyA = mock(Body.class);
		when(bodyA.getUserData()).thenReturn(spriteA);

		Body bodyB = mock(Body.class);
		when(bodyB.getUserData()).thenReturn(trigger);
		
		Fixture fixtureA = mock(Fixture.class);
		when(fixtureA.getUserData()).thenReturn(stringA);
		when(fixtureA.getBody()).thenReturn(bodyA);
		
		Fixture fixtureB = mock(Fixture.class);
		when(fixtureB.getUserData()).thenReturn(stringB);
		when(fixtureB.getBody()).thenReturn(bodyB);
		
		Contact contact  = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		Play playState = mock(Play.class);
		
		MyContactListener listener = new MyContactListener(playState);
		listener.beginContact(contact);
		
		verify(trigger, times(1)).canExecute(any(CollisionInfo.class));
	}
	
	@Test
	public void pickupItemPlayerA() {
		
		Item item = mock(Item.class);
		Player player = mock(Player.class);
		B2DSprite sprite = mock(B2DSprite.class);
		
		when(sprite.getUserData()).thenReturn(item);
		
		B2DSprite spriteA = mock(B2DSprite.class);
		when(spriteA.getUserData()).thenReturn(player);
		
		String stringA = "player";
		String stringB = "item";
		
		Body bodyA = mock(Body.class);
		when(bodyA.getUserData()).thenReturn(spriteA);

		Body bodyB = mock(Body.class);
		when(bodyB.getUserData()).thenReturn(sprite);
		
		Fixture fixtureA = mock(Fixture.class);
		when(fixtureA.getUserData()).thenReturn(stringA);
		when(fixtureA.getBody()).thenReturn(bodyA);
		
		Fixture fixtureB = mock(Fixture.class);
		when(fixtureB.getUserData()).thenReturn(stringB);
		when(fixtureB.getBody()).thenReturn(bodyB);
		
		Contact contact  = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		Play playState = mock(Play.class);
		
		MyContactListener listener = new MyContactListener(playState);
		listener.beginContact(contact);
		
		verify(player, times(1)).pickupItem(item);
	}
	
	@Test
	public void beginContactPlayerB() {
		
		ITrigger trigger = mock(ITrigger.class);
		Player player = mock(Player.class);
		
		B2DSprite spriteB = mock(B2DSprite.class);
		when(spriteB.getUserData()).thenReturn(player);
		
		String stringA = "trigger";
		String stringB = "player";
		
		Body bodyA = mock(Body.class);
		when(bodyA.getUserData()).thenReturn(trigger);

		Body bodyB = mock(Body.class);
		when(bodyB.getUserData()).thenReturn(spriteB);
		
		Fixture fixtureA = mock(Fixture.class);
		when(fixtureA.getUserData()).thenReturn(stringA);
		when(fixtureA.getBody()).thenReturn(bodyA);
		
		Fixture fixtureB = mock(Fixture.class);
		when(fixtureB.getUserData()).thenReturn(stringB);
		when(fixtureB.getBody()).thenReturn(bodyB);
		
		Contact contact  = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		Play playState = mock(Play.class);
		
		MyContactListener listener = new MyContactListener(playState);
		listener.beginContact(contact);
		
		verify(trigger, times(1)).canExecute(any(CollisionInfo.class));
	}
	
	@Test
	public void pickupItemPlayerB() {
		
		Item item = mock(Item.class);
		Player player = mock(Player.class);
		B2DSprite sprite = mock(B2DSprite.class);

		when(sprite.getUserData()).thenReturn(item);

		B2DSprite spriteB = mock(B2DSprite.class);
		when(spriteB.getUserData()).thenReturn(player);
		
		String stringA = "trigger";
		String stringB = "player";
		
		Body bodyA = mock(Body.class);
		when(bodyA.getUserData()).thenReturn(sprite);

		Body bodyB = mock(Body.class);
		when(bodyB.getUserData()).thenReturn(spriteB);
		
		Fixture fixtureA = mock(Fixture.class);
		when(fixtureA.getUserData()).thenReturn(stringA);
		when(fixtureA.getBody()).thenReturn(bodyA);
		
		Fixture fixtureB = mock(Fixture.class);
		when(fixtureB.getUserData()).thenReturn(stringB);
		when(fixtureB.getBody()).thenReturn(bodyB);
		
		Contact contact  = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		Play playState = mock(Play.class);
		
		MyContactListener listener = new MyContactListener(playState);
		listener.beginContact(contact);
		
		verify(player, times(1)).pickupItem(item);
	}
	
	@Test
	public void beginContactPlayerATriggerCannotExecute() {
		
		Play playState = mock(Play.class);
		Player player = mock(Player.class);
		CollisionInfo info = new CollisionInfo();
		info.setPlayer(player);
		info.setPlayState(playState);
		
		ITrigger trigger = mock(ITrigger.class);
		when(trigger.canExecute(info)).thenReturn(false);
	
		
		Body bodyA = mock(Body.class);
		when(bodyA.getUserData()).thenReturn(player);

		Body bodyB = mock(Body.class);
		when(bodyB.getUserData()).thenReturn(trigger);
		
		Fixture fixtureA = mock(Fixture.class);
		when(fixtureA.getBody()).thenReturn(bodyA);
		
		Fixture fixtureB = mock(Fixture.class);
		when(fixtureB.getBody()).thenReturn(bodyB);
		
		Contact contact  = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		MyContactListener listener = new MyContactListener(playState);
		listener.beginContact(contact);
		
		verify(trigger, never()).execute(info);
	}
	
	@Test
	public void beginContactPlayerBTriggerCannotExecute() {
		
		Play playState = mock(Play.class);
		Player player = mock(Player.class);
		CollisionInfo info = new CollisionInfo();
		info.setPlayer(player);
		info.setPlayState(playState);
		
		ITrigger trigger = mock(ITrigger.class);
		when(trigger.canExecute(info)).thenReturn(false);
		
		Body bodyA = mock(Body.class);
		when(bodyA.getUserData()).thenReturn(trigger);

		Body bodyB = mock(Body.class);
		when(bodyB.getUserData()).thenReturn(player);
		
		Fixture fixtureA = mock(Fixture.class);
		when(fixtureA.getBody()).thenReturn(bodyA);
		
		Fixture fixtureB = mock(Fixture.class);
		when(fixtureB.getBody()).thenReturn(bodyB);
		
		Contact contact  = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		MyContactListener listener = new MyContactListener(playState);
		listener.beginContact(contact);
		
		verify(trigger, never()).execute(info);
	}
	
	@Test
	public void beginContactPlayerATriggerCanExecute() {
		
		Play playState = mock(Play.class);
		Player player = mock(Player.class);

		ITrigger trigger = mock(ITrigger.class);
		when(trigger.canExecute(any(CollisionInfo.class))).thenReturn(true);

		B2DSprite spriteA = mock(B2DSprite.class);
		when(spriteA.getUserData()).thenReturn(player);
		
		String stringA = "player";
		String stringB = "trigger";
		
		Body bodyA = mock(Body.class);
		when(bodyA.getUserData()).thenReturn(spriteA);

		Body bodyB = mock(Body.class);
		when(bodyB.getUserData()).thenReturn(trigger);
		
		Fixture fixtureA = mock(Fixture.class);
		when(fixtureA.getUserData()).thenReturn(stringA);
		when(fixtureA.getBody()).thenReturn(bodyA);
		
		Fixture fixtureB = mock(Fixture.class);
		when(fixtureB.getUserData()).thenReturn(stringB);
		when(fixtureB.getBody()).thenReturn(bodyB);
		
		Contact contact  = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		MyContactListener listener = new MyContactListener(playState);
		listener.beginContact(contact);
		
		verify(trigger, times(1)).execute(any(CollisionInfo.class));
	}
	
	@Test
	public void beginContactPlayerBTriggerCanExecute() {
		
		Play playState = mock(Play.class);
		Player player = mock(Player.class);
		
		ITrigger trigger = mock(ITrigger.class);
		when(trigger.canExecute(any(CollisionInfo.class))).thenReturn(true);
		
		B2DSprite spriteB = mock(B2DSprite.class);
		when(spriteB.getUserData()).thenReturn(player);
		
		String stringA = "trigger";
		String stringB = "player";
		
		Body bodyA = mock(Body.class);
		when(bodyA.getUserData()).thenReturn(trigger);

		Body bodyB = mock(Body.class);
		when(bodyB.getUserData()).thenReturn(spriteB);
		
		Fixture fixtureA = mock(Fixture.class);
		when(fixtureA.getUserData()).thenReturn(stringA);
		when(fixtureA.getBody()).thenReturn(bodyA);
		
		Fixture fixtureB = mock(Fixture.class);
		when(fixtureB.getUserData()).thenReturn(stringB);
		when(fixtureB.getBody()).thenReturn(bodyB);
		
		Contact contact  = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		MyContactListener listener = new MyContactListener(playState);
		listener.beginContact(contact);
		
		verify(trigger, times(1)).execute(any(CollisionInfo.class));
	}
	
	@Test
	public void beginContactPlayerAOperableOperate() {
		
		IOperable operable = mock(IOperable.class);
		
		Player player = mock(Player.class);
		B2DSprite spriteB = mock(B2DSprite.class);
		
		when(spriteB.getUserData()).thenReturn(operable);
		
		B2DSprite spriteA = mock(B2DSprite.class);
		when(spriteA.getUserData()).thenReturn(player);
		
		String stringA = "player";
		String stringB = "object";
		
		Body bodyA = mock(Body.class);
		when(bodyA.getUserData()).thenReturn(spriteA);

		Body bodyB = mock(Body.class);
		when(bodyB.getUserData()).thenReturn(spriteB);
		
		Fixture fixtureA = mock(Fixture.class);
		when(fixtureA.getUserData()).thenReturn(stringA);
		when(fixtureA.getBody()).thenReturn(bodyA);
		
		Fixture fixtureB = mock(Fixture.class);
		when(fixtureB.getUserData()).thenReturn(stringB);
		when(fixtureB.getBody()).thenReturn(bodyB);
		
		Contact contact  = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		Play playState = mock(Play.class);
		
		MyContactListener listener = new MyContactListener(playState);
		listener.beginContact(contact);
		
		verify(operable, times(1)).operate(player);
	}
	
	@Test
	public void beginContactPlayerBGameObjectGetTrigger() {
		
		IOperable operable = mock(IOperable.class);
		
		Player player = mock(Player.class);
		B2DSprite spriteB = mock(B2DSprite.class);
		
		when(spriteB.getUserData()).thenReturn(player);
		
		B2DSprite spriteA = mock(B2DSprite.class);
		when(spriteA.getUserData()).thenReturn(operable);
		
		String stringB = "player";
		String stringA = "object";
		
		Body bodyA = mock(Body.class);
		when(bodyA.getUserData()).thenReturn(spriteA);

		Body bodyB = mock(Body.class);
		when(bodyB.getUserData()).thenReturn(spriteB);
		
		Fixture fixtureA = mock(Fixture.class);
		when(fixtureA.getUserData()).thenReturn(stringA);
		when(fixtureA.getBody()).thenReturn(bodyA);
		
		Fixture fixtureB = mock(Fixture.class);
		when(fixtureB.getUserData()).thenReturn(stringB);
		when(fixtureB.getBody()).thenReturn(bodyB);
		
		Contact contact  = mock(Contact.class);
		when(contact.getFixtureA()).thenReturn(fixtureA);
		when(contact.getFixtureB()).thenReturn(fixtureB);
		
		Play playState = mock(Play.class);
		
		MyContactListener listener = new MyContactListener(playState);
		listener.beginContact(contact);
		
		verify(operable, times(1)).operate(player);
	}

}
