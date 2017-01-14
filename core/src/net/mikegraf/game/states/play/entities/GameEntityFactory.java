package net.mikegraf.game.states.play.entities;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.behavior.BehaviorFactory;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.bodies.BodyFactory;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.view.IView;

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
        IController controller = behaviorFactory.createController(props);
        IView view = behaviorFactory.createView(props);
        int id = props.get(TiledConstants.ENTITY_ID, Integer.class);
        
        GameEntity entity = constructGameEntity(collisionB, controller, view, body, props);
        entity.setId(id);
        return entity;
    }

    public void finalizeEntities() {
    }

    protected abstract GameEntity constructGameEntity(ICollisionBehavior collisionB, IController controller,
            IView view, Body body, MapProperties props);
}
