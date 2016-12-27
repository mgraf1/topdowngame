package net.mikegraf.game.states.play.entities.player;

import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.behavior.BehaviorFactory;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.collision.PlayerCollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.PlayerMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.AnimationFactory;
import net.mikegraf.game.states.play.entities.behavior.rendering.AnimationIndex;
import net.mikegraf.game.states.play.entities.behavior.rendering.AnimationRenderBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;

public class PlayerBehaviorFactory extends BehaviorFactory {

    private AnimationFactory animationFactory;

    public PlayerBehaviorFactory(AnimationFactory animationFactory) {
        this.animationFactory = animationFactory;
    }

    @Override
    public IRenderBehavior createRenderBehavior(MapProperties props) {
        String type = props.get(TiledConstants.ENTITY_TYPE, String.class);
        AnimationIndex animationIndex = animationFactory.createAnimationIndex(type);
        return new AnimationRenderBehavior(animationIndex);
    }

    @Override
    public ICollisionBehavior createCollisionBehavior(MapProperties props) {
        return new PlayerCollisionBehavior();
    }

    @Override
    public IMovementBehavior createMovementBehavior(MapProperties props) {
        return new PlayerMovementBehavior();
    }

}
