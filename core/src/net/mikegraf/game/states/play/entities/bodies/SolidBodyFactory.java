package net.mikegraf.game.states.play.entities.bodies;

import java.util.HashMap;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
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
    }

    @Override
    public Body createBody(World world, MapObject mapObject) {
        String type = mapObject.getProperties().get(TiledConstants.ENTITY_TYPE, String.class);
        BodyData data = typeToBodyDataMap.get(type);

        String bodyType = data.getBodyType();
        if (bodyType.equals(BODYTYPE_DYNAMIC)) {
            bodyDef.type = BodyType.DynamicBody;
        } else if (bodyType.equals(BODYTYPE_STATIC)) {
            bodyDef.type = BodyType.DynamicBody;
        }

        bodyDef.linearDamping = data.getDamp();

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        return body;
    }

}
