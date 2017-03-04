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
import net.mikegraf.game.states.play.entities.triggers.DamageTrigger;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.levels.Level;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerNoCondition;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class DamageTriggerTests {

    private PhysicsModel physModel;
    private IView view;
    private IController controller;

    private Level level;
    private Player player;
    private CollisionInfo info;
    private ICondition<Player> condition;
    private DamageTrigger trigger;

    @Before
    public void myBefore() {
        physModel = mock(PhysicsModel.class);
        view = mock(IView.class);
        controller = mock(IController.class);
        level = mock(Level.class);
        player = mock(Player.class);
        condition = mock(PlayerNoCondition.class);
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
    }

    @Test
    public void executePlayerDamagedFor1WhenAmountIs1() {
        trigger = new DamageTrigger(physModel, view, controller, condition, 1);

        trigger.execute(info);

        verify(player, times(1)).damage(1);
    }

    @Test
    public void executePlayerDamagedFor2WhenAmountIs2() {
        trigger = new DamageTrigger(physModel, view, controller, condition, 2);

        trigger.execute(info);

        verify(player, times(1)).damage(2);
    }
}
