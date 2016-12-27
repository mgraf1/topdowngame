package net.mikegraf.game.states.play.entities.gameObjects;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;

public class GameObject extends GameEntity {

    protected SoundEffectIndex soundEffectIndex;

    public GameObject(String id, ICollisionBehavior collisionBehavior, IMovementBehavior movementBehavior,
            IRenderBehavior renderBehavior, Body body, SoundEffectIndex soundEffectIndex) {
        super(id, collisionBehavior, movementBehavior, renderBehavior, body);
        this.soundEffectIndex = soundEffectIndex;
    }

}
