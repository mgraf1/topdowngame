package net.mikegraf.game.states.play.logic;

/* Condition to determine if game entity is in some same. */
public interface ICondition<T> {

    /* Returns true if entity is acceptable to the condition */
    public boolean accepts(T entity);
}
