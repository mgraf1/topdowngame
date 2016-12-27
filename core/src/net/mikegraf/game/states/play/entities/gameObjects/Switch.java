package net.mikegraf.game.states.play.entities.gameObjects;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;
import net.mikegraf.game.states.play.entities.player.Player;

public class Switch extends OperableGameEntity {

    public static final String ON_ANIMATION_NAME = "on";
    public static final String OFF_ANIMATION_NAME = "off";
    public static final String TURN_SFX = "turn";

    private boolean on;
    private Door door;

    public Switch(String id, ICollisionBehavior collisionBehavior, IMovementBehavior movementBehavior,
            IRenderBehavior renderBehavior, Body body, SoundEffectIndex soundEffectIndex) {
        super(id, collisionBehavior, movementBehavior, renderBehavior, body, soundEffectIndex);
        this.on = false;
    }

    @Override
    public boolean operate(Player player) {
        if (on) {
            renderBehavior.setMode(OFF_ANIMATION_NAME);
            on = false;
        } else {
            renderBehavior.setMode(ON_ANIMATION_NAME);
            on = true;
        }
        soundEffectIndex.playSound(TURN_SFX);
        return door.operate(null);
    }

    public boolean isOn() {
        return on;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}
