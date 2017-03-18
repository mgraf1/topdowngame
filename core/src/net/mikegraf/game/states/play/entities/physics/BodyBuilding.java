package net.mikegraf.game.states.play.entities.physics;

import java.util.HashMap;

import com.google.inject.Inject;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.parsers.models.BodyData;

public class BodyBuilding {

    private ShapeFactory shapeFactory;
    private HashMap<String, BodyData> typeToBodyDataMap;

    @Inject
    public BodyBuilding(ShapeFactory shapeFactory, HashMap<String, BodyData> typeToBodyDataMap) {
        this.shapeFactory = shapeFactory;
        this.typeToBodyDataMap = typeToBodyDataMap;
    }

    public BodyFactory getBodyFactory(String layerName) {

        if (layerName.equals(TiledConstants.LAYER_TRIGGER)) {
            return new SensorBodyFactory(shapeFactory);
        } else if (layerName.equals(TiledConstants.LAYER_ITEM)) {
            return new SensorBodyFactory(shapeFactory);
        } else if (layerName.equals(TiledConstants.LAYER_OBJECT)) {
            return new SolidBodyFactory(shapeFactory, typeToBodyDataMap);
        } else if (layerName.equals(TiledConstants.LAYER_PLAYER)) {
            return new SolidBodyFactory(shapeFactory, typeToBodyDataMap);
        } else if (layerName.equals(TiledConstants.LAYER_BADGUYS)) {
            return new SolidBodyFactory(shapeFactory, typeToBodyDataMap);
        } else {
            throw new IllegalArgumentException("No layer named: " + layerName);
        }
    }

}
