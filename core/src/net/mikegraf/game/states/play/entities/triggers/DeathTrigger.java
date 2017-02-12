package net.mikegraf.game.states.play.entities.triggers;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.logic.ICondition;

public class DeathTrigger extends Trigger {

    public DeathTrigger(ICollisionBehavior collisionBehavior, IController controller, IView view, Body body,
            ICondition<Player> condition) {
        super(collisionBehavior, controller, view, body, condition);
    }

    @Override
    public void execute(CollisionInfo info) {
        Player player = (Player) info.getOtherEntity();
        player.die();
    }

}
