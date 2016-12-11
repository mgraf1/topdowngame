package net.mikegraf.game.states.play.triggers;

import net.mikegraf.game.states.play.contact.CollisionInfo;

/* Interface for all triggered events. */
public interface ITrigger {

    /*
     * Perform the desired action. Return true if the trigger should be
     * discarded
     */
    public boolean execute(CollisionInfo info);

    /* Return true if the trigger can execute. */
    public boolean canExecute(CollisionInfo info);

}
