package net.mikegraf.game.states.play.entities.triggers;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.logic.ICondition;

public abstract class Trigger extends GameEntity {

    private ICondition<Player> condition;

    public Trigger(String id, ICollisionBehavior collisionBehavior, IMovementBehavior movementBehavior,
            IRenderBehavior renderBehavior, Body body, ICondition<Player> condition) {
        super(id, collisionBehavior, movementBehavior, renderBehavior, body);
        this.condition = condition;
    }

    public abstract void execute(CollisionInfo info);

    public boolean canExecute(CollisionInfo info) {
        Player player = (Player) info.getOtherEntity();
        return condition.accepts(player);
    }

}
