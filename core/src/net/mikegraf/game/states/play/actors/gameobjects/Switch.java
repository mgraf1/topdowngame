package net.mikegraf.game.states.play.actors.gameobjects;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;

public class Switch extends GameObject implements IOperable {

    private Door door;

    public Switch(B2DSprite b2dSprite) {
        super(b2dSprite);
    }

    @Override
    public boolean operate(Player player) {
        return false;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}
