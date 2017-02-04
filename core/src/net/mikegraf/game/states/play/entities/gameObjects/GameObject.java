package net.mikegraf.game.states.play.entities.gameObjects;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.view.IView;

public class GameObject extends GameEntity {

    protected SoundEffectIndex soundEffectIndex;

    public GameObject(ICollisionBehavior collisionBehavior, IController controller, IView view, Body body,
            SoundEffectIndex soundEffectIndex) {
        super(collisionBehavior, controller, view, body);
        this.soundEffectIndex = soundEffectIndex;
    }

}
