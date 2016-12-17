package net.mikegraf.game.states.play.actors.gameobjects;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.logic.ICondition;

public class Door extends GameObject implements IOperable {

    private ICondition<Player> hasKeyCondition;

    public Door(B2DSprite b2dSprite, ICondition<Player> cond) {
        super(b2dSprite);

        // Create trigger so door can be unlocked.
        hasKeyCondition = cond;
    }

    @Override
    public boolean operate(Player player) {

        if (hasKeyCondition.accepts(player)) {
            toggle();
            return true;
        }
        return false;
    }

    private void toggle() {
        if (sprite.isHidden()) {
            sprite.show();
        } else {
            sprite.hide();
        }
    }

}
