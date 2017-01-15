package net.mikegraf.game.states.play.entities.bodies;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

import net.mikegraf.game.main.constants.B2dConstants;

public class ShapeFactory {

    public Shape createShape(MapObject mapObject) {
        Shape shape = null;
        MapProperties props = mapObject.getProperties();
        float width = props.get("width", float.class);
        float height = props.get("height", float.class);

        if (mapObject instanceof EllipseMapObject) {
            shape = new CircleShape();
            shape.setRadius((width / 2) / B2dConstants.PPM);
        } else if (mapObject instanceof RectangleMapObject) {
            PolygonShape pShape = new PolygonShape();
            pShape.setAsBox((width / 2) / B2dConstants.PPM, (height / 2) / B2dConstants.PPM);
            shape = pShape;
        }

        return shape;
    }
}
