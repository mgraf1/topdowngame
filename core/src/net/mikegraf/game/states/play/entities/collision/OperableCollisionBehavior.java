package net.mikegraf.game.states.play.entities.collision;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.gameObjects.OperableGameEntity;
import net.mikegraf.game.states.play.entities.player.Player;

public class OperableCollisionBehavior implements ICollisionBehavior {

    @Override
    public void handleCollision(CollisionInfo info) {
        GameEntity entity = info.getOtherEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            player.touch((OperableGameEntity) info.getThisEntity());
        }
    }

    @Override
    public void handleEndCollision(CollisionInfo info) {
        GameEntity entity = info.getOtherEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            player.stopTouching((OperableGameEntity) info.getThisEntity());
        }
    }

}
