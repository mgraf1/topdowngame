package net.mikegraf.game.states.play.entities;

import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.view.IView;

public abstract class BehaviorFactory {

    public abstract IView createView(MapProperties props);

    public abstract ICollisionBehavior createCollisionBehavior(MapProperties props);

    public abstract IController createController(MapProperties props);
}
