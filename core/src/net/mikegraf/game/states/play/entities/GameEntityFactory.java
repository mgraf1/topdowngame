package net.mikegraf.game.states.play.entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.states.play.entities.behavior.BehaviorFactory;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;
import net.mikegraf.game.states.play.entities.bodies.BodyFactory;

public abstract class GameEntityFactory {

    private BodyFactory bodyFactory;
    private BehaviorFactory behaviorFactory;

    public GameEntityFactory(BodyFactory bodyFactory, BehaviorFactory behaviorFactory) {
        this.bodyFactory = bodyFactory;
        this.behaviorFactory = behaviorFactory;
    }

    public GameEntity createGameEntity(World world, MapObject mapObject) {
        MapProperties props = mapObject.getProperties();
        Body body = bodyFactory.createBody(world, mapObject);
        ICollisionBehavior collisionB = behaviorFactory.createCollisionBehavior(props);
        IMovementBehavior moveB = behaviorFactory.createMovementBehavior(props);
        IRenderBehavior renderB = behaviorFactory.createRenderBehavior(props);

        return constructGameEntity(collisionB, moveB, renderB, body, props);
    }

    public void finalizeEntities() {
    }

    protected abstract GameEntity constructGameEntity(ICollisionBehavior collisionB, IMovementBehavior moveB,
            IRenderBehavior renderB, Body body, MapProperties props);
}
