package net.mikegraf.game.states.play.entities.player;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.controls.PlayerInputHandler;
import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.collision.PlayerCollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.controller.PlayerController;
import net.mikegraf.game.states.play.entities.view.AnimationFactory;
import net.mikegraf.game.states.play.entities.view.AnimationIndex;
import net.mikegraf.game.states.play.entities.view.AnimationView;
import net.mikegraf.game.states.play.entities.view.IView;

public class PlayerBehaviorFactory extends BehaviorFactory {

    private AnimationFactory animationFactory;
    private PlayerInputHandler inputHandler;

    public PlayerBehaviorFactory(AnimationFactory animationFactory, PlayerInputHandler inputHandler) {
        this.animationFactory = animationFactory;
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
    public ICollisionBehavior createCollisionBehavior(MapProperties props) {
        return new PlayerCollisionBehavior();
    }

    @Override
    public IController createController(MapProperties props) {
        PlayerController controller = new PlayerController(inputHandler);
        return controller;
    }

}
