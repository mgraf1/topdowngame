package net.mikegraf.game.states.play.entities.collision;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.gameObjects.OperableGameEntity;
import net.mikegraf.game.states.play.entities.player.Player;

public class PlayerCollisionBehavior implements ICollisionBehavior {

    @Override
    public void handleCollision(CollisionInfo info) {
        GameEntity other = info.getOtherEntity();
        GameEntity player = info.getThisEntity();
        info.setOtherEntity(player);
        other.handleCollision(info);
    }

    @Override
    public void handleEndCollision(CollisionInfo info) {
        GameEntity other = info.getOtherEntity();
        if (other instanceof OperableGameEntity) {
            Player player = (Player) info.getThisEntity();
            player.touch((OperableGameEntity) other);
        }
    }

}
