package net.mikegraf.game.states.play.entities.triggers;

import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.states.play.entities.behavior.BehaviorFactory;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.collision.TriggerCollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.NoMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.NoRenderBehavior;

public class TriggerBehaviorFactory extends BehaviorFactory {

    @Override
    public IRenderBehavior createRenderBehavior(MapProperties props) {
        return new NoRenderBehavior();
    }

    @Override
    public ICollisionBehavior createCollisionBehavior(MapProperties props) {
        return new TriggerCollisionBehavior();
    }

    @Override
    public IMovementBehavior createMovementBehavior(MapProperties props) {
        return new NoMovementBehavior();
    }

}
