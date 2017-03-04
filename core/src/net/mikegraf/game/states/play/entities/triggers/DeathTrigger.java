package net.mikegraf.game.states.play.entities.triggers;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.logic.ICondition;

public class DeathTrigger extends Trigger {

    public DeathTrigger(PhysicsModel physModel, IView view, IController controller, ICondition<Player> condition) {
        super(physModel, view, controller, condition);
    }

    @Override
    public void execute(CollisionInfo info) {
        Player player = (Player) info.getOtherEntity();
        player.die();
    }

}
