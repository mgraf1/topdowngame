package net.mikegraf.game.states.play.entities.gameObjects;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;

public class Switch extends OperableGameEntity {

    public static final String ON_ANIMATION_NAME = "on";
    public static final String OFF_ANIMATION_NAME = "off";
    public static final String TURN_SFX = "turn";

    private boolean on;
    private Door door;

    public Switch(PhysicsModel physModel, IView view, IController controller, SoundEffectIndex soundEffectIndex) {
        super(physModel, view, controller, soundEffectIndex);
        this.on = false;
    }

    @Override
    public boolean operate(Player player) {
        if (on) {
            view.setMode(OFF_ANIMATION_NAME);
            on = false;
        } else {
            view.setMode(ON_ANIMATION_NAME);
            on = true;
        }
        soundEffectIndex.playSound(TURN_SFX);
        door.toggle();

        return true;
    }

    public boolean isOn() {
        return on;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}
