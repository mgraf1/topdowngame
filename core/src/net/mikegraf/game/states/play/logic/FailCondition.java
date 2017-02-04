package net.mikegraf.game.states.play.logic;

public class FailCondition<T> implements ICondition<T> {

    @Override
    public boolean accepts(T entity) {
        return false;
    }

}
