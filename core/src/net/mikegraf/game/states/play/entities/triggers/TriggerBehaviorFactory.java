package net.mikegraf.game.states.play.entities.triggers;

import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.collision.TriggerCollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.DefaultController;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.entities.view.NoRenderView;

public class TriggerBehaviorFactory extends BehaviorFactory {

    @Override
    public IView createView(MapProperties props) {
        return new NoRenderView();
    }

    @Override
    public ICollisionBehavior createCollisionBehavior(MapProperties props) {
        return new TriggerCollisionBehavior();
    }

    @Override
    public IController createController(MapProperties props) {
        return new DefaultController();
    }

}
