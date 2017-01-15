package net.mikegraf.game.states.play.entities.triggers;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.logic.ICondition;

public abstract class Trigger extends GameEntity {

    private ICondition<Player> condition;

    public Trigger(ICollisionBehavior collisionBehavior, IController movementBehavior,
            IView renderBehavior, Body body, ICondition<Player> condition) {
        super(collisionBehavior, movementBehavior, renderBehavior, body);
        this.condition = condition;
    }

    public abstract void execute(CollisionInfo info);

    public boolean canExecute(CollisionInfo info) {
        Player player = (Player) info.getOtherEntity();
        return condition.accepts(player);
    }

}
