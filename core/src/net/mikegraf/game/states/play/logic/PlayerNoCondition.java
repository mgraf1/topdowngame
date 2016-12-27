package net.mikegraf.game.states.play.logic;

import net.mikegraf.game.states.play.entities.player.Player;

/* This condition will accept any player. */
public class PlayerNoCondition implements ICondition<Player> {

    @Override
    public boolean accepts(Player entity) {
        return entity != null;
    }

}
