package net.mikegraf.game.testing.states.play.logic;

import org.junit.Assert;
import org.junit.Test;

import net.mikegraf.game.states.play.logic.NullCondition;

public class NullConditionTests {

    @Test
    public void acceptsReturnsTrueIfArgNull() {
        NullCondition<Object> cond = new NullCondition<Object>();

        Assert.assertTrue(cond.accepts(null));
    }

    @Test
    public void acceptsReturnsFalseIfArgNotNull() {
        NullCondition<Object> cond = new NullCondition<Object>();

        Assert.assertFalse(cond.accepts(new Object()));
    }
}
