package net.mikegraf.game.states.play.actors.gameobjects;

import net.mikegraf.game.states.play.actors.Player;

public interface IOperable {

    public boolean operate(Player player);

    public String getId();
}
