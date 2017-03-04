package net.mikegraf.game.states.play.entities.gameObjects;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.collision.NoCollisionBehavior;
import net.mikegraf.game.states.play.entities.collision.OperableCollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.DefaultController;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.BodyFactory;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.AnimationFactory;
import net.mikegraf.game.states.play.entities.view.AnimationIndex;
import net.mikegraf.game.states.play.entities.view.AnimationView;
import net.mikegraf.game.states.play.entities.view.IView;

public class GameObjectBehaviorFactory extends BehaviorFactory {

    private AnimationFactory animationFactory;
    private BodyFactory bodyFactory;

    public GameObjectBehaviorFactory(AnimationFactory animationFactory, BodyFactory bodyFactory) {
        this.animationFactory = animationFactory;
        this.bodyFactory = bodyFactory;
    }

    @Override
    public IView createView(MapProperties props, AssetManager assetManager) {
        String texture = props.get(TiledConstants.ENTITY_TEXTURE, String.class);
        AnimationIndex animationIndex = animationFactory.createAnimationIndex(texture, assetManager);
        ShaderProgram flashShader = animationFactory.createShader(AnimationFactory.SHADER_FLASH);
        return new AnimationView(animationIndex, flashShader);
    }

    @Override
    public IController createController(MapProperties props) {
        return new DefaultController();
    }

    @Override
    public PhysicsModel createPhysicsModel(World world, MapObject mapObject) {
        Body body = bodyFactory.createBody(world, mapObject);

        MapProperties props = mapObject.getProperties();
        String type = props.get(TiledConstants.ENTITY_TYPE, String.class);

        ICollisionBehavior collisionBehavior;
        if (type.equals(TiledConstants.ENTITY_TYPE_DOOR) || type.equals(TiledConstants.ENTITY_TYPE_SWITCH)) {
            collisionBehavior = new OperableCollisionBehavior();
        } else {
            collisionBehavior = new NoCollisionBehavior();
        }
        return new PhysicsModel(body, collisionBehavior);
    }

}
