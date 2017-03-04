package net.mikegraf.game.states.play.entities.gameObjects;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;

public abstract class OperableGameEntity extends GameObject {

    public OperableGameEntity(PhysicsModel physModel, IView view, IController controller,
            SoundEffectIndex soundEffectIndex) {
        super(physModel, view, controller, soundEffectIndex);
    }

    public abstract boolean operate(Player player);
}
