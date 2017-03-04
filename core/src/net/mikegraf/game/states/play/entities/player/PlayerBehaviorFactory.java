package net.mikegraf.game.states.play.entities.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.controls.PlayerInputHandler;
import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.collision.PlayerCollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.controller.PlayerController;
import net.mikegraf.game.states.play.entities.physics.BodyFactory;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.AnimationFactory;
import net.mikegraf.game.states.play.entities.view.AnimationIndex;
import net.mikegraf.game.states.play.entities.view.AnimationView;
import net.mikegraf.game.states.play.entities.view.IView;

public class PlayerBehaviorFactory extends BehaviorFactory {

    private AnimationFactory animationFactory;
    private BodyFactory bodyFactory;
    private PlayerInputHandler inputHandler;

    public PlayerBehaviorFactory(AnimationFactory animationFactory, BodyFactory bodyFactory,
            PlayerInputHandler inputHandler) {
        this.animationFactory = animationFactory;
        this.bodyFactory = bodyFactory;
        this.inputHandler = inputHandler;
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
        PlayerController controller = new PlayerController(inputHandler);
        return controller;
    }

    @Override
    public PhysicsModel createPhysicsModel(World world, MapObject mapObject) {
        Body body = bodyFactory.createBody(world, mapObject);
        float velocity = bodyFactory.getVelocity(mapObject);
        ICollisionBehavior collisionBehavior = new PlayerCollisionBehavior();
        return new PhysicsModel(body, collisionBehavior, velocity);
    }

}
