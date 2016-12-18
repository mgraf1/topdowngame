package net.mikegraf.game.states.play.logic;

import net.mikegraf.game.states.play.actors.Player;

public class PlayerNotTouchingCondition implements ICondition<Player> {

    private String objectId;

    public PlayerNotTouchingCondition(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public boolean accepts(Player entity) {
        return !entity.isTouching(objectId);
    }

}
