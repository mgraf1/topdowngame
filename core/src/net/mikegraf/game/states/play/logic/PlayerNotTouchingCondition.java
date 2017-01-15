package net.mikegraf.game.states.play.logic;

import net.mikegraf.game.states.play.entities.player.Player;

public class PlayerNotTouchingCondition implements ICondition<Player> {

    private int objectId;

    public PlayerNotTouchingCondition(int objectId) {
        this.objectId = objectId;
    }

    @Override
    public boolean accepts(Player entity) {
        return !entity.isTouching(objectId);
    }

}
