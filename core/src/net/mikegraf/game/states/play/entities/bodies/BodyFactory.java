package net.mikegraf.game.states.play.entities.bodies;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

public abstract class BodyFactory {

    protected static final String BODY_X = "x";
    protected static final String BODY_Y = "y";

    protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;
    protected ShapeFactory shapeFactory;

    @Inject
    public BodyFactory(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
        this.bodyDef = new BodyDef();
        this.fixtureDef = new FixtureDef();
    }

    protected Vector2 getPosition(MapProperties props) {
        float x = props.get(BODY_X, Float.class);
        float y = props.get(BODY_Y, Float.class);
        return new Vector2(x, y);
    }

    public abstract Body createBody(World world, MapObject mapObject);

}
