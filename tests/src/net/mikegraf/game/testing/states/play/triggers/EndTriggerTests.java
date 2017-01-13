package net.mikegraf.game.testing.states.play.triggers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.triggers.EndTrigger;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.levels.Level;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerNoCondition;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class EndTriggerTests {

    private static final int DEST_X = 2;
    private static final int DEST_Y = 1;

    private ICollisionBehavior collisionBehavior;
    private IController controller;
    private IView view;
    private Body body;

    private Level level;
    private Player player;
    private CollisionInfo info;
    private ICondition<Player> condition;
    private EndTrigger trigger;

    @Before
    public void myBefore() {
        collisionBehavior = mock(ICollisionBehavior.class);
        controller = mock(IController.class);
        view = mock(IView.class);
        body = mock(Body.class);

        level = mock(Level.class);
        player = mock(Player.class);
        condition = mock(PlayerNoCondition.class);
        trigger = new EndTrigger(collisionBehavior, controller, view, body, condition, DEST_X,
                DEST_Y);
        info = new CollisionInfo(level, trigger, player);
    }

    @After
    public void myAfter() {
        collisionBehavior = null;
        controller = null;
        view = null;
        body = null;
        level = null;
        player = null;
        info = null;
        condition = null;
        trigger = null;
    }

    @Test
    public void canExecuteConditionFail() {
        when(condition.accepts(player)).thenReturn(false);

        boolean result = trigger.canExecute(info);

        assertFalse(result);
    }

    @Test
    public void canExecuteConditionPass() {
        when(condition.accepts(player)).thenReturn(true);

        boolean result = trigger.canExecute(info);

        assertTrue(result);
    }

    @Test
    public void executeSetNextLevelCalled() {
        when(condition.accepts(player)).thenReturn(true);

        trigger.execute(info);

        verify(level, times(1)).setNextLevel(DEST_X, DEST_Y);
    }
}
