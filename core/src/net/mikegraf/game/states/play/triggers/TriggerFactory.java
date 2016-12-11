package net.mikegraf.game.states.play.triggers;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;

import net.mikegraf.game.states.play.levels.B2DVars;
import net.mikegraf.game.states.play.logic.PlayerNoCondition;

/* Responsible for creating triggers and adding them to */
public class TriggerFactory {

    // Constants.
    public final String END_TRIGGER_TYPE = "end";

    // Instance variables.
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;

    public ITrigger createTrigger(World world, MapProperties triggerProps) {

        Float x = (Float) triggerProps.get("x") / B2DVars.PPM;
        Float y = (Float) triggerProps.get("y") / B2DVars.PPM;
        Float width = (Float) triggerProps.get("width") / B2DVars.PPM;
        Float height = (Float) triggerProps.get("height") / B2DVars.PPM;

        // Body location is based on center.
        x += (width / 2);
        y += (height / 2);

        String type = (String) triggerProps.get("type");

        // Create body for trigger.
        bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);

        // Add fixture for trigger.
        fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true; // Sensor so no collision.

        PolygonShape pShape = new PolygonShape();
        pShape.setAsBox(width / 2, height / 2);
        ;
        fixtureDef.shape = pShape;
        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(B2DVars.TRIGGER_ID);

        ITrigger trigger = null;
        if (type.equals(END_TRIGGER_TYPE)) {
            int dX = Integer.parseInt((String) (triggerProps.get("destX")));
            int dY = Integer.parseInt((String) (triggerProps.get("destY")));
            trigger = new EndTrigger(new PlayerNoCondition(), dX, dY);
        }

        body.setUserData(trigger);
        return trigger;
    }
}
