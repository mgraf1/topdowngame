package net.mikegraf.game.states.play.actors;

import java.util.HashMap;
import java.util.List;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

import net.mikegraf.game.parsers.models.AnimationData;
import net.mikegraf.game.parsers.models.SpriteData;

/* Responsible for creating sprites. */
public class SpriteFactory {

    // Constants.
    private static final String DYNAMIC_BODY_TYPE = "dynamic";
    private static final String STATIC_BODY_TYPE = "static";

    // Instance variables.
    private AnimationFactory animationFactory;
    private HashMap<String, SpriteData> data;
    private FixtureDef fixtureDef;
    private BodyDef bodyDef;

    @Inject
    public SpriteFactory(AnimationFactory animationFactory, HashMap<String, SpriteData> data) {
        this.animationFactory = animationFactory;
        this.data = data;
    }

    public B2DSprite createSprite(String type, Shape spriteShape, World world, float x, float y) {

        // Find correct SpriteData;
        SpriteData spriteData = data.get(type);

        // Create body for sprite.
        bodyDef = new BodyDef();

        // Set correct body type.
        String spriteBodyType = spriteData.getBodyType();
        if (spriteBodyType.equals(DYNAMIC_BODY_TYPE)) {
            bodyDef.type = BodyType.DynamicBody;
        } else if (spriteBodyType.equals(STATIC_BODY_TYPE)) {
            bodyDef.type = BodyType.StaticBody;
        }

        bodyDef.linearDamping = spriteData.getDamp();

        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);

        // Add fixture for sprite.
        fixtureDef = new FixtureDef();
        fixtureDef.shape = spriteShape;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(type);

        // Get animation.
        List<AnimationData> animationData = spriteData.getAnimationData();
        AnimationIndex animation = null;
        if (animationData != null) {
            animation = animationFactory.createSpriteAnimationIndex(spriteData, animationData);
        } else {
            animation = animationFactory.createDefaultSpriteAnimationIndex(spriteData);
        }

        return new B2DSprite(animation, body);
    }
}
