package net.mikegraf.game.states.play.entities.badGuys;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.main.helpers.Box2dHelper;
import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.collision.DamagePlayerCollisionBehavior;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.collision.KillPlayerCollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.BadGuyController;
import net.mikegraf.game.states.play.entities.controller.DefaultController;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.BodyFactory;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.AnimationFactory;
import net.mikegraf.game.states.play.entities.view.AnimationIndex;
import net.mikegraf.game.states.play.entities.view.AnimationView;
import net.mikegraf.game.states.play.entities.view.IView;

public class BadGuyBehaviorFactory extends BehaviorFactory {

    private final int DAMAGE_AMOUNT = 1;

    private AnimationFactory animationFactory;
    private BodyFactory bodyFactory;

    public BadGuyBehaviorFactory(AnimationFactory animationFactory, BodyFactory bodyFactory) {
        this.animationFactory = animationFactory;
        this.bodyFactory = bodyFactory;
    }

    @Override
    public IView createView(MapProperties props, AssetManager assetManager) {
        if (isWaypoint(props)) {
            return null;
        }

        String texture = props.get(TiledConstants.ENTITY_TEXTURE, String.class);
        AnimationIndex animationIndex = animationFactory.createAnimationIndex(texture, assetManager);
        ShaderProgram flashShader = animationFactory.createShader(AnimationFactory.SHADER_FLASH);
        return new AnimationView(animationIndex, flashShader);
    }

    @Override
    public PhysicsModel createPhysicsModel(World world, MapObject mapObject) {
        MapProperties props = mapObject.getProperties();
        if (isWaypoint(props)) {
            return null;
        }

        Body body = bodyFactory.createBody(world, mapObject);
        ICollisionBehavior collisionBehavior = new DamagePlayerCollisionBehavior(DAMAGE_AMOUNT);
        PhysicsModel physModel = new PhysicsModel(body, collisionBehavior, 1);
        physModel.setMaxLinearAcceleration(1f);
        physModel.setMaxLinearSpeed(1f);
        return physModel;
    }

    @Override
    public IController createController(MapProperties props) {
        if (isWaypoint(props)) {
            return new DefaultController();
        }

        Array<Vector2> waypoints = new Array<Vector2>();

        float x = props.get(TiledConstants.ENTITY_X, Float.class);
        float y = props.get(TiledConstants.ENTITY_Y, Float.class);
        Vector2 origWaypointRender = new Vector2(x, y);
        Vector2 origWaypoint = new Vector2();
        Box2dHelper.toBox2dCoords(origWaypoint, origWaypointRender, 0, 0);
        waypoints.add(origWaypoint);

        return new BadGuyController(waypoints);
    }

    private boolean isWaypoint(MapProperties props) {
        return props.get(TiledConstants.ENTITY_TYPE, String.class).equals(TiledConstants.BADGUY_WAYPOINT);
    }
}
