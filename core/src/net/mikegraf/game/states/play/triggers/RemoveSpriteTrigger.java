package net.mikegraf.game.states.play.triggers;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.logic.ICondition;

/* Trigger to remove a sprite from the level. */
public class RemoveSpriteTrigger implements ITrigger {

    private ICondition<Player> condition;

    public RemoveSpriteTrigger(ICondition<Player> c) {
        condition = c;
    }

    @Override
    public boolean execute(CollisionInfo info) {
        B2DSprite sprite = info.getOtherSprite();
        sprite.prepareForDisposal();

        return true;
    }

    @Override
    public boolean canExecute(CollisionInfo info) {
        Player player = info.getPlayer();
        return (condition.accepts(player));
    }

}
