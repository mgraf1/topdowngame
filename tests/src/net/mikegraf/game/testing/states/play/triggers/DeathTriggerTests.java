package net.mikegraf.game.testing.states.play.triggers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.triggers.DeathTrigger;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.levels.Level;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerNoCondition;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class DeathTriggerTests {

    private PhysicsModel physModel;
    private IView view;
    private IController controller;

    private Level level;
    private Player player;
    private CollisionInfo info;
    private ICondition<Player> condition;
    private DeathTrigger trigger;

    @Before
    public void myBefore() {
        physModel = mock(PhysicsModel.class);
        controller = mock(IController.class);
        view = mock(IView.class);

        level = mock(Level.class);
        player = mock(Player.class);
        condition = mock(PlayerNoCondition.class);
        trigger = new DeathTrigger(physModel, view, controller, condition);
        info = new CollisionInfo(level, trigger, player);
    }

    @After
    public void myAfter() {
        physModel = null;
        view = null;
        controller = null;
        level = null;
        player = null;
        info = null;
        condition = null;
        trigger = null;
    }

    @Test
    public void executeDieCalled() {
        trigger.execute(info);

        verify(player, times(1)).die();
    }
}
