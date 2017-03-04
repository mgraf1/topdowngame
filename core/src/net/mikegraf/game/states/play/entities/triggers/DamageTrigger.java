package net.mikegraf.game.states.play.entities.triggers;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.logic.ICondition;

public class DamageTrigger extends Trigger {

    private int damageAmount;

    public DamageTrigger(PhysicsModel physModel, IView view, IController controller, ICondition<Player> condition,
            int damageAmount) {
        super(physModel, view, controller, condition);
        this.damageAmount = damageAmount;
    }

    @Override
    public void execute(CollisionInfo info) {
        Player player = (Player) info.getOtherEntity();
        player.damage(damageAmount);
    }

}
