package net.mikegraf.game.states.play.triggers;

import net.mikegraf.game.states.play.contact.CollisionInfo;

/* This trigger can never execute. */
public class NullTrigger implements ITrigger {

    @Override
    public boolean execute(CollisionInfo info) {
        return false;
    }

    @Override
    public boolean canExecute(CollisionInfo info) {
        return false;
    }

}
