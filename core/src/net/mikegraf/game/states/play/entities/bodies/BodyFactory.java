package net.mikegraf.game.states.play.entities.bodies;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.main.helpers.Box2dHelper;

public abstract class BodyFactory {

    protected static final String BODY_X = "x";
    protected static final String BODY_Y = "y";

    protected BodyDef bodyDef;
    protected FixtureDef fixtureDef;
    protected ShapeFactory shapeFactory;

    public BodyFactory(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
        this.bodyDef = new BodyDef();
        this.fixtureDef = new FixtureDef();
    }

    protected void setBodyDefPosition(MapProperties props) {
        float x = props.get(BODY_X, Float.class);
        float y = props.get(BODY_Y, Float.class);
        float width = props.get(TiledConstants.ENTITY_WIDTH, Float.class);
        float height = props.get(TiledConstants.ENTITY_HEIGHT, Float.class);
        Box2dHelper.toBox2dCoords(bodyDef.position, new Vector2(x, y), width, height);
    }

    public abstract Body createBody(World world, MapObject mapObject);

}
