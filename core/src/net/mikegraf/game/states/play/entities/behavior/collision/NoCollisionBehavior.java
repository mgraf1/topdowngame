package net.mikegraf.game.states.play.entities.behavior.collision;

import net.mikegraf.game.states.play.contact.CollisionInfo;

public class NoCollisionBehavior implements ICollisionBehavior {

	@Override
	public void handleCollision(CollisionInfo info) { }

	@Override
	public void handleEndCollision(CollisionInfo info) { }

}
