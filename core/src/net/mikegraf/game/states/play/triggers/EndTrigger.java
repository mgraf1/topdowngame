package net.mikegraf.game.states.play.triggers;

import net.mikegraf.game.states.play.Play;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.logic.ICondition;

/* Trigger for ending the current level. */
public class EndTrigger implements ITrigger {

    private int destX;
    private int destY;
    private ICondition<Player> condition;

    public EndTrigger(ICondition<Player> c, int dX, int dY) {
        condition = c;
        destX = dX;
        destY = dY;
    }

    @Override
    public boolean execute(CollisionInfo info) {
        Play playState = info.getPlayState();
        playState.setNextLevel(destX, destY);
        return true;
    }

    @Override
    public boolean canExecute(CollisionInfo info) {
        Player player = info.getPlayer();
        return condition.accepts(player);
    }

}
