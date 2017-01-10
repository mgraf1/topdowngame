package net.mikegraf.game.testing.states.play.contact;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.contact.MyContactListener;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.levels.Level;

public class MyContactListenerTests {

    private MyContactListener cl;
    private Contact contact;
    private Level level;
    private Fixture fixA;
    private Fixture fixB;
    private Body bodA;
    private Body bodB;

    @Before
    public void myBefore() {
        fixA = mock(Fixture.class);
        fixB = mock(Fixture.class);
        bodA = mock(Body.class);
        bodB = mock(Body.class);
        contact = mock(Contact.class);
        level = mock(Level.class);
        cl = new MyContactListener(level);

        when(contact.getFixtureA()).thenReturn(fixA);
        when(contact.getFixtureB()).thenReturn(fixB);
        when(fixA.getBody()).thenReturn(bodA);
        when(fixB.getBody()).thenReturn(bodB);
    }

    @After
    public void myAfter() {
        fixA = null;
        fixB = null;
        bodA = null;
        bodB = null;
        contact = null;
        level = null;
        cl = null;
    }

    @Test
    public void beginContactBothEntitiesHandleCollisionCalled() {
        GameEntity geA = mock(GameEntity.class);
        GameEntity geB = mock(GameEntity.class);
        when(bodA.getUserData()).thenReturn(geA);
        when(bodB.getUserData()).thenReturn(geB);

        cl.beginContact(contact);

        verify(geA, times(1)).handleCollision(any(CollisionInfo.class));
    }

    @Test
    public void beginContactOneNotEntityHandleCollisionNotCalled() {
        GameEntity geA = mock(GameEntity.class);
        when(bodA.getUserData()).thenReturn(geA);
        when(bodB.getUserData()).thenReturn(new Object());

        cl.beginContact(contact);

        verify(geA, times(0)).handleCollision(any(CollisionInfo.class));
    }

    @Test
    public void endContactBothEntitiesHandleEndCollisionCalled() {
        GameEntity geA = mock(GameEntity.class);
        GameEntity geB = mock(GameEntity.class);
        when(bodA.getUserData()).thenReturn(geA);
        when(bodB.getUserData()).thenReturn(geB);

        cl.endContact(contact);

        verify(geA, times(1)).handleEndCollision(any(CollisionInfo.class));
    }

    @Test
    public void endContactOneNotEntityHandleEndCollisionNotCalled() {
        GameEntity geA = mock(GameEntity.class);
        when(bodA.getUserData()).thenReturn(geA);
        when(bodB.getUserData()).thenReturn(new Object());

        cl.beginContact(contact);

        verify(geA, times(0)).handleEndCollision(any(CollisionInfo.class));
    }
}
