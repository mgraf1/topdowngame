package net.mikegraf.game.states.play.actors.gameobjects;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.logic.ICondition;

public class Door extends GameObject implements IOperable {

    private ICondition<Player> condition;

    public Door(B2DSprite sprite, String name, ICondition<Player> condition) {
        super(sprite, name);
        this.condition = condition;
    }

    @Override
    public boolean operate(Player player) {
        if (condition.accepts(player)) {
            toggle();
            return true;
        }
        return false;
    }

    @Override
    public String getId() {
        return name;
    }

    private void toggle() {
        if (sprite.isHidden()) {
            sprite.readyForShowing = true;
        } else {
            sprite.readyForHiding = true;
        }
    }
}
