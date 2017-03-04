package net.mikegraf.game.states.play.entities.gameObjects;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.GameEntityState;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.logic.ICondition;

public class Door extends OperableGameEntity {

    private static final String UNLOCK_SFX = "unlock";

    private ICondition<Player> condition;

    public Door(PhysicsModel physModel, IView view, IController controller, SoundEffectIndex soundEffectIndex,
            ICondition<Player> condition) {
        super(physModel, view, controller, soundEffectIndex);
        this.condition = condition;
    }

    @Override
    public boolean operate(Player player) {
        if (condition.accepts(player)) {
            toggle();
            soundEffectIndex.playSound(UNLOCK_SFX);
            return true;
        }
        return false;
    }

    public void toggle() {
        if (state == GameEntityState.HIDDEN) {
            state = GameEntityState.READY_TO_SHOW;
        } else if (state == GameEntityState.VISIBLE) {
            state = GameEntityState.READY_TO_HIDE;
        } else {
            throw new IllegalStateException("Tried to toggle a GameEntity in state: " + state.toString());
        }
    }
}
