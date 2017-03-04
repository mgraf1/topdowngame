package net.mikegraf.game.testing.states.play.entities.bodies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.main.constants.B2dConstants;
import net.mikegraf.game.states.play.entities.physics.ShapeFactory;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class ShapeFactoryTests {

    private final float DELTA = .001f;
    private final float WIDTH = 50f;
    private final float HEIGHT = 60f;
    private ShapeFactory shapeFactory;
    private Shape shape;
    private World world;

    @Before
    public void MyBefore() {
        world = new World(new Vector2(0, 0), false);
        world.clearForces();
        shapeFactory = new ShapeFactory();
    }

    @After
    public void MyAfter() {
        if (shape != null) {
            shape.dispose();
            shape = null;
        }

    }

    private void createProps(MapObject mapObject) {
        MapProperties props = mapObject.getProperties();
        props.put("width", WIDTH);
        props.put("height", HEIGHT);
    }

    @Test
    public void createElipse() {
        EllipseMapObject mapObject = new EllipseMapObject();
        createProps(mapObject);

        shape = shapeFactory.createShape(mapObject);

        assertTrue(shape instanceof CircleShape);
    }

    @Test
    public void createElipseWidthCorrect() {
        ;
        EllipseMapObject mapObject = new EllipseMapObject();
        createProps(mapObject);

        shape = shapeFactory.createShape(mapObject);

        assertEquals(WIDTH / B2dConstants.PPM, shape.getRadius() * 2, DELTA);
    }

    @Test
    public void createElipseWidthCorrectWithHeight() {
        EllipseMapObject mapObject = new EllipseMapObject();
        createProps(mapObject);

        shape = shapeFactory.createShape(mapObject);

        assertEquals(WIDTH / B2dConstants.PPM, shape.getRadius() * 2, DELTA);
    }

    @Test
    public void createRectangle() {
        RectangleMapObject mapObject = new RectangleMapObject();
        createProps(mapObject);

        shape = shapeFactory.createShape(mapObject);

        assertTrue(shape instanceof PolygonShape);
    }
}
