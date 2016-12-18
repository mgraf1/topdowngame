package net.mikegraf.game.states.play.logic;

public class NullCondition<T> implements ICondition<T> {

    @Override
    public boolean accepts(T entity) {
        return entity == null;
    }

}
