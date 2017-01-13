package net.mikegraf.game.states.play.entities.gameObjects;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;

public abstract class OperableGameEntity extends GameObject {

    public OperableGameEntity(ICollisionBehavior collisionBehavior, IController controller,
            IView view, Body body, SoundEffectIndex soundEffectIndex) {
        super(collisionBehavior, controller, view, body, soundEffectIndex);
    }

    public abstract boolean operate(Player player);
}
