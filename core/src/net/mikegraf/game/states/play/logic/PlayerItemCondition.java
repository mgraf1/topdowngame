package net.mikegraf.game.states.play.logic;

import net.mikegraf.game.states.play.actors.Player;

/* This condition will accept the player if a specific item is in the inventory. */
public class PlayerItemCondition implements ICondition<Player> {

    private String type;

    public PlayerItemCondition(String itemType) {
        type = itemType;
    }

    @Override
    public boolean accepts(Player entity) {
        if (entity != null) {
            if (entity.hasItem(type)) {
                return true;
            }
        }

        return false;
    }

}
