package net.mikegraf.game.states.play.entities.triggers;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.levels.Level;
import net.mikegraf.game.states.play.logic.ICondition;

public class EndTrigger extends Trigger {

    private int destX;
    private int destY;

    public EndTrigger(ICollisionBehavior collisionBehavior, IController movementBehavior,
            IView renderBehavior, Body body, ICondition<Player> condition, int destX, int destY) {
        super(collisionBehavior, movementBehavior, renderBehavior, body, condition);
        this.destX = destX;
        this.destY = destY;
    }

    @Override
    public void execute(CollisionInfo info) {
        Level level = info.getLevel();
        level.setNextLevel(destX, destY);
    }

}
