package net.mikegraf.game.states.play.entities.items;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.GameEntityFactory;
import net.mikegraf.game.states.play.entities.behavior.BehaviorFactory;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;
import net.mikegraf.game.states.play.entities.bodies.BodyFactory;

public class ItemFactory extends GameEntityFactory {

    public ItemFactory(BodyFactory bodyFactory, BehaviorFactory behaviorFactory) {
        super(bodyFactory, behaviorFactory);
    }

    @Override
    protected GameEntity constructGameEntity(ICollisionBehavior collisionB, IMovementBehavior moveB,
            IRenderBehavior renderB, Body body, MapProperties props) {
        String id = props.get(TiledConstants.ENTITY_ID, String.class);
        String type = props.get(TiledConstants.ENTITY_TYPE, String.class);
        return new Item(id, collisionB, moveB, renderB, body, type);
    }

}
