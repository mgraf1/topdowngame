package net.mikegraf.game.states.play.entities.collision;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.GameEntityState;
import net.mikegraf.game.states.play.entities.items.Item;
import net.mikegraf.game.states.play.entities.player.Player;

public class ItemCollisionBehavior implements ICollisionBehavior {

    @Override
    public void handleCollision(CollisionInfo info) {
        GameEntity entity = info.getOtherEntity();
        if (entity instanceof Player) {
        	Player player = (Player) entity;
        	Item item = (Item)info.getThisEntity();
        	if (player.pickupItem(item)) {
        		item.state = GameEntityState.READY_TO_HIDE;
        	}
        }
    }

    @Override
    public void handleEndCollision(CollisionInfo info) { }

}
