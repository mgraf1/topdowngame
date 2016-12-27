package net.mikegraf.game.states.play.entities.bodies;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

public class SensorBodyFactory extends BodyFactory {

    @Inject
    public SensorBodyFactory(ShapeFactory shapeFactory) {
        super(shapeFactory);
    }

    @Override
    public Body createBody(World world, MapObject mapObject) {
        Shape shape = shapeFactory.createShape(mapObject);
        MapProperties props = mapObject.getProperties();
        float x = props.get(BODY_X, Float.class);
        float y = props.get(BODY_Y, Float.class);

        bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        Body body = world.createBody(bodyDef);

        fixtureDef.isSensor = true;
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

}
