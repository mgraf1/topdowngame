package net.mikegraf.game.states.play.contact;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.google.inject.Inject;

import net.mikegraf.game.states.play.Play;
import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Item;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.actors.gameobjects.IOperable;
import net.mikegraf.game.states.play.triggers.ITrigger;

public class MyContactListener implements ContactListener {

    private static final String PLAYER_TYPE = "player";
    private Play playState;

    @Inject
    public MyContactListener(Play playState) {
        this.playState = playState;
    }

    @Override
    public void beginContact(Contact contact) {

        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        Object aData = a.getUserData();
        Object bData = b.getUserData();
        Object otherObject = null;
        Body otherBody = null;
        Player p = null;

        if (aData == null || bData == null) {
            return;
        }

        // Determine if a player is part of the collision.
        CollisionInfo info = new CollisionInfo();
        info.setPlayState(playState);

        if (aData.equals(PLAYER_TYPE)) {

            B2DSprite sprite = (B2DSprite) a.getBody().getUserData();
            p = (Player) sprite.getUserData();
            otherBody = b.getBody();
            otherObject = otherBody.getUserData();
        } else if (bData.equals(PLAYER_TYPE)) {

            B2DSprite sprite = (B2DSprite) b.getBody().getUserData();
            p = (Player) sprite.getUserData();
            otherBody = a.getBody();
            otherObject = otherBody.getUserData();
        }

        if (p != null) {
            info.setPlayer(p);

            if (otherObject instanceof ITrigger) {
                ITrigger trigger = (ITrigger) otherObject;

                if (trigger.canExecute(info)) {
                    trigger.execute(info);
                }
            } else if (otherObject instanceof B2DSprite) {

                B2DSprite otherSprite = (B2DSprite) otherObject;
                info.setOtherSprite(otherSprite);
                Object spriteData = otherSprite.getUserData();

                if (spriteData instanceof Item) {

                    Item item = (Item) spriteData;

                    if (p.pickupItem(item)) {
                        otherSprite.prepareForDisposal();
                    }
                }

                else if (spriteData instanceof IOperable) {

                    IOperable operable = (IOperable) spriteData;
                    operable.operate(p);
                }
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

}
