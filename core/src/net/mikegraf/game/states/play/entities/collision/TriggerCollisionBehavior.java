package net.mikegraf.game.states.play.entities.collision;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.triggers.Trigger;

public class TriggerCollisionBehavior implements ICollisionBehavior {

    @Override
    public void handleCollision(CollisionInfo info) {
        GameEntity otherEntity = info.getOtherEntity();
        if (otherEntity instanceof Player) {
            Trigger trigger = (Trigger) info.getThisEntity();
            if (trigger.canExecute(info)) {
                trigger.execute(info);
            }
        }
    }

    @Override
    public void handleEndCollision(CollisionInfo info) {
    }

}
