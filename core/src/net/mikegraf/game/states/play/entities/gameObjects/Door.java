package net.mikegraf.game.states.play.entities.gameObjects;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.GameEntityState;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.logic.ICondition;

public class Door extends OperableGameEntity {

    private ICondition<Player> condition;

    public Door(String id, ICollisionBehavior collisionBehavior, IMovementBehavior movementBehavior,
            IRenderBehavior renderBehavior, Body body, SoundEffectIndex soundEffectIndex,
            ICondition<Player> condition) {
        super(id, collisionBehavior, movementBehavior, renderBehavior, body, soundEffectIndex);
        this.condition = condition;
    }

    @Override
    public boolean operate(Player player) {
        if (condition.accepts(player)) {
            toggle();
            return true;
        }
        return false;
    }

    private void toggle() {
        if (state == GameEntityState.HIDDEN) {
            state = GameEntityState.READY_TO_SHOW;
        } else if (state == GameEntityState.VISIBLE) {
            state = GameEntityState.READY_TO_HIDE;
        } else {
            throw new IllegalStateException("Tried to toggle a GameEntity in state: " + state.toString());
        }
    }
}
