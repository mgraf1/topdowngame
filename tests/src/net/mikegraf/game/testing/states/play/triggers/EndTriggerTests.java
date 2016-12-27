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

import net.mikegraf.game.states.play.Play;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;
import net.mikegraf.game.states.play.entities.triggers.EndTrigger;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerNoCondition;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class EndTriggerTests {

    private static final String ID = "id";
    private static final int DEST_X = 2;
    private static final int DEST_Y = 1;

    private ICollisionBehavior collisionBehavior;
    private IMovementBehavior movementBehavior;
    private IRenderBehavior renderBehavior;
    private Body body;

    private Play playState;
    private Player player;
    private CollisionInfo info;
    private ICondition<Player> condition;
    private EndTrigger trigger;

    @Before
    public void myBefore() {
        collisionBehavior = mock(ICollisionBehavior.class);
        movementBehavior = mock(IMovementBehavior.class);
        renderBehavior = mock(IRenderBehavior.class);
        body = mock(Body.class);

        playState = mock(Play.class);
        player = mock(Player.class);
        info = new CollisionInfo();
        info.setPlayer(player);
        info.setPlayState(playState);
        condition = mock(PlayerNoCondition.class);
        trigger = new EndTrigger(ID, collisionBehavior, movementBehavior, renderBehavior, body, condition, DEST_X,
                DEST_Y);
    }

    @After
    public void myAfter() {
        collisionBehavior = null;
        movementBehavior = null;
        renderBehavior = null;
        body = null;
        playState = null;
        player = null;
        info = null;
        condition = mock(PlayerNoCondition.class);
        trigger = new EndTrigger(ID, collisionBehavior, movementBehavior, renderBehavior, body, condition, DEST_X,
                DEST_Y);
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

        verify(playState, times(1)).setNextLevel(DEST_X, DEST_Y);
    }
}
