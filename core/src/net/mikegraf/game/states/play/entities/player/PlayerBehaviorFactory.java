package net.mikegraf.game.states.play.entities.player;

import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.controls.PlayerInputHandler;
import net.mikegraf.game.states.play.entities.behavior.BehaviorFactory;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.collision.PlayerCollisionBehavior;
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
    public IView createView(MapProperties props) {
        String texture = props.get(TiledConstants.ENTITY_TEXTURE, String.class);
        AnimationIndex animationIndex = animationFactory.createAnimationIndex(texture);
        return new AnimationView(animationIndex);
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
