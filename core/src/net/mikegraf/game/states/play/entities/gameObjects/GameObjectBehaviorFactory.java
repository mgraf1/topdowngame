package net.mikegraf.game.states.play.entities.gameObjects;

import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.behavior.BehaviorFactory;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.collision.NoCollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.collision.OperableCollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.DefaultController;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.view.AnimationFactory;
import net.mikegraf.game.states.play.entities.view.AnimationIndex;
import net.mikegraf.game.states.play.entities.view.AnimationView;
import net.mikegraf.game.states.play.entities.view.IView;

public class GameObjectBehaviorFactory extends BehaviorFactory {

    private AnimationFactory animationFactory;

    public GameObjectBehaviorFactory(AnimationFactory animationFactory) {
        this.animationFactory = animationFactory;
    }

    @Override
    public IView createView(MapProperties props) {
        String texture = props.get(TiledConstants.ENTITY_TEXTURE, String.class);
        AnimationIndex animationIndex = animationFactory.createAnimationIndex(texture);
        return new AnimationView(animationIndex);
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
    public IController createController(MapProperties props) {
        return new DefaultController();
    }

}
