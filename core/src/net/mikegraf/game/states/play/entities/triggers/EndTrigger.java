package net.mikegraf.game.states.play.entities.triggers;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.states.play.Play;
import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.logic.ICondition;

public class EndTrigger extends Trigger {

    private int destX;
    private int destY;

    public EndTrigger(String id, ICollisionBehavior collisionBehavior, IMovementBehavior movementBehavior,
            IRenderBehavior renderBehavior, Body body, ICondition<Player> condition, int destX, int destY) {
        super(id, collisionBehavior, movementBehavior, renderBehavior, body, condition);
        this.destX = destX;
        this.destY = destY;
    }

    @Override
    public void execute(CollisionInfo info) {
        Play playState = info.getPlayState();
        playState.setNextLevel(destX, destY);
    }

}
