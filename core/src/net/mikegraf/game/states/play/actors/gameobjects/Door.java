package net.mikegraf.game.states.play.actors.gameobjects;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.logic.ICondition;

public class Door extends GameObject implements IOperable {

    private ICondition<Player> hasKeyCondition;
    private String keyName;

    public Door(B2DSprite b2dSprite, ICondition<Player> cond, String key) {
        super(b2dSprite);
        keyName = key;

        // Create trigger so door can be unlocked.
        hasKeyCondition = cond;
    }

    @Override
    public void operate(Player player) {

        if (hasKeyCondition.accepts(player)) {

            sprite.prepareForDisposal();
            player.removeItem(keyName);
        }
    }

}
