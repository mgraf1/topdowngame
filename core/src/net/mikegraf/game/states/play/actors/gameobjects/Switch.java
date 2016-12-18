package net.mikegraf.game.states.play.actors.gameobjects;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;

public class Switch extends GameObject implements IOperable {

    public static final String ON_ANIMATION_NAME = "on";
    public static final String OFF_ANIMATION_NAME = "off";
    public static final String TURN_SFX = "turn";

    private Door door;
    private boolean on;

    public Switch(B2DSprite sprite, SoundEffectIndex soundEffectIndex, String name) {
        super(sprite, soundEffectIndex, name);
        this.on = false;
    }

    @Override
    public boolean operate(Player player) {
        if (on) {
            sprite.setAnimation(OFF_ANIMATION_NAME);
            on = false;
        } else {
            sprite.setAnimation(ON_ANIMATION_NAME);
            on = true;
        }
        soundEffectIndex.playSound(TURN_SFX);
        return door.operate(null);
    }

    @Override
    public String getId() {
        return name;
    }

    public boolean isOn() {
        return on;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}
