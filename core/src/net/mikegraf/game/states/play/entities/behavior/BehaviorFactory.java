package net.mikegraf.game.states.play.entities.behavior;

import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;

public abstract class BehaviorFactory {

    public abstract IRenderBehavior createRenderBehavior(MapProperties props);

    public abstract ICollisionBehavior createCollisionBehavior(MapProperties props);

    public abstract IMovementBehavior createMovementBehavior(MapProperties props);
}
