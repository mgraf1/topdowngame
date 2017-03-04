package net.mikegraf.game.states.play.entities.triggers;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.levels.Level;
import net.mikegraf.game.states.play.logic.ICondition;

public class EndTrigger extends Trigger {

    private int destX;
    private int destY;

    public EndTrigger(PhysicsModel physModel, IView view, IController controller, ICondition<Player> condition,
            int destX, int destY) {
        super(physModel, view, controller, condition);
        this.destX = destX;
        this.destY = destY;
    }

    @Override
    public void execute(CollisionInfo info) {
        Level level = info.getLevel();
        level.setNextLevel(destX, destY);
    }

}
