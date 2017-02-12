package net.mikegraf.game.testing.states.play.triggers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.triggers.DamageTrigger;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.levels.Level;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerNoCondition;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class DamageTriggerTests {

    private ICollisionBehavior collisionBehavior;
    private IController controller;
    private IView view;
    private Body body;

    private Level level;
    private Player player;
    private CollisionInfo info;
    private ICondition<Player> condition;
    private DamageTrigger trigger;

    @Before
    public void myBefore() {
        collisionBehavior = mock(ICollisionBehavior.class);
        controller = mock(IController.class);
        view = mock(IView.class);
        body = mock(Body.class);

        level = mock(Level.class);
        player = mock(Player.class);
        condition = mock(PlayerNoCondition.class);
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
    }

    @Test
    public void executePlayerDamagedFor1WhenAmountIs1() {
        trigger = new DamageTrigger(collisionBehavior, controller, view, body, condition, 1);

        trigger.execute(info);

        verify(player, times(1)).damage(1);
    }

    @Test
    public void executePlayerDamagedFor2WhenAmountIs2() {
        trigger = new DamageTrigger(collisionBehavior, controller, view, body, condition, 2);

        trigger.execute(info);

        verify(player, times(1)).damage(2);
    }
}
