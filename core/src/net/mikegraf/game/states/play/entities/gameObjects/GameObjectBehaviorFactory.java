package net.mikegraf.game.states.play.entities.gameObjects;

import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.behavior.BehaviorFactory;
import net.mikegraf.game.states.play.entities.behavior.collision.OperableCollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.collision.NoCollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.NoMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.AnimationFactory;
import net.mikegraf.game.states.play.entities.behavior.rendering.AnimationIndex;
import net.mikegraf.game.states.play.entities.behavior.rendering.AnimationRenderBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;

public class GameObjectBehaviorFactory extends BehaviorFactory {

    private AnimationFactory animationFactory;

    public GameObjectBehaviorFactory(AnimationFactory animationFactory) {
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
    	String type = props.get(TiledConstants.ENTITY_TYPE, String.class); 
    	if (type.equals(TiledConstants.ENTITY_TYPE_DOOR)) {
    		return new OperableCollisionBehavior();
    	} else if (type.equals(TiledConstants.ENTITY_TYPE_SWITCH)) {
    		return new OperableCollisionBehavior();
    	}
        return new NoCollisionBehavior();
    }

    @Override
    public IMovementBehavior createMovementBehavior(MapProperties props) {
        return new NoMovementBehavior();
    }

}
