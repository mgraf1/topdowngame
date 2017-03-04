package net.mikegraf.game.states.play.entities.physics;

import java.util.HashMap;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.parsers.models.BodyData;

public class SolidBodyFactory extends BodyFactory {

    private static final String BODYTYPE_DYNAMIC = "dynamic";
    private static final String BODYTYPE_STATIC = "static";

    private HashMap<String, BodyData> typeToBodyDataMap;

    @Inject
    public SolidBodyFactory(ShapeFactory shapeFactory, HashMap<String, BodyData> typeToBodyDataMap) {
        super(shapeFactory);
        this.typeToBodyDataMap = typeToBodyDataMap;
    }

    @Override
    public Body createBody(World world, MapObject mapObject) {
        MapProperties props = mapObject.getProperties();
        Shape shape = shapeFactory.createShape(mapObject);
        BodyData data = getBodyDataFromMapObject(mapObject);
        bodyDef = new BodyDef();

        setBodyDefPosition(props);
        String bodyType = data.getBodyType();
        if (bodyType.equals(BODYTYPE_DYNAMIC)) {
            bodyDef.type = BodyType.DynamicBody;
        } else if (bodyType.equals(BODYTYPE_STATIC)) {
            bodyDef.type = BodyType.StaticBody;
        }
        bodyDef.linearDamping = data.getDamp();
        Body body = world.createBody(bodyDef);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
        shape.dispose();
        return body;
    }

    @Override
    public float getVelocity(MapObject mapObject) {
        BodyData bodyData = getBodyDataFromMapObject(mapObject);
        return bodyData.getVelocity();
    }

    private BodyData getBodyDataFromMapObject(MapObject mapObject) {
        MapProperties props = mapObject.getProperties();
        String type = props.get(TiledConstants.ENTITY_TYPE, String.class);
        return typeToBodyDataMap.get(type);
    }
}
