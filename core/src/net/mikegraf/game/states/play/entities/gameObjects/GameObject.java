package net.mikegraf.game.states.play.entities.gameObjects;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.IView;

public class GameObject extends GameEntity {

    protected SoundEffectIndex soundEffectIndex;

    public GameObject(PhysicsModel physModel, IView view, IController controller, SoundEffectIndex soundEffectIndex) {
        super(physModel, view, controller);
        this.soundEffectIndex = soundEffectIndex;
    }

}
