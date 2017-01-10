package net.mikegraf.game.states.play.contact;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;

import com.badlogic.gdx.physics.box2d.Manifold;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.levels.Level;

public class MyContactListener implements ContactListener {

    private Level level;

    public MyContactListener(Level level) {
        this.level = level;
    }

    @Override
    public void beginContact(Contact contact) {
        GameEntity aEntity = getEntityAFromContact(contact);
        GameEntity bEntity = getEntityBFromContact(contact);
        if (aEntity != null && bEntity != null) {
            CollisionInfo info = new CollisionInfo(level, aEntity, bEntity);
            aEntity.handleCollision(info);
        }
    }

    @Override
    public void endContact(Contact contact) {
        GameEntity aEntity = getEntityAFromContact(contact);
        GameEntity bEntity = getEntityBFromContact(contact);
        if (aEntity != null && bEntity != null) {
            CollisionInfo info = new CollisionInfo(level, aEntity, bEntity);
            aEntity.handleEndCollision(info);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    private GameEntity getEntityAFromContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Object aData = a.getBody().getUserData();
        if (aData != null && aData instanceof GameEntity) {
            GameEntity aEntity = (GameEntity) aData;
            return aEntity;
        } else {
            return null;
        }
    }

    private GameEntity getEntityBFromContact(Contact contact) {
        Fixture b = contact.getFixtureB();
        Object bData = b.getBody().getUserData();
        if (bData != null && bData instanceof GameEntity) {
            GameEntity bEntity = (GameEntity) bData;
            return bEntity;
        } else {
            return null;
        }
    }
}
