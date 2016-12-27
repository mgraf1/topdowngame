package net.mikegraf.game.states.play.entities.behavior.collision;

import net.mikegraf.game.states.play.contact.CollisionInfo;

public interface ICollisionBehavior {

    public void handleCollision(CollisionInfo info);

    public void handleEndCollision(CollisionInfo info);

}
