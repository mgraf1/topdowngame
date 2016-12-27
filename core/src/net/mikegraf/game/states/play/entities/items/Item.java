package net.mikegraf.game.states.play.entities.items;

import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;

public class Item extends GameEntity {

    private String type;

    public Item(String id, ICollisionBehavior collisionBehavior, IMovementBehavior movementBehavior,
            IRenderBehavior renderBehavior, Body body, String type) {
        super(id, collisionBehavior, movementBehavior, renderBehavior, body);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
