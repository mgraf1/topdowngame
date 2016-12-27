package net.mikegraf.game.states.play.entities.gameObjects;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;
import net.mikegraf.game.states.play.entities.player.Player;

public abstract class OperableGameEntity extends GameObject {

    public OperableGameEntity(String id, ICollisionBehavior collisionBehavior, IMovementBehavior movementBehavior,
            IRenderBehavior renderBehavior, Body body, SoundEffectIndex soundEffectIndex) {
        super(id, collisionBehavior, movementBehavior, renderBehavior, body, soundEffectIndex);
    }

    public abstract boolean operate(Player player);
}
