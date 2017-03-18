package net.mikegraf.game.states.play.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.IView;

public abstract class GameEntityFactory {

    private BehaviorFactory behaviorFactory;

    public GameEntityFactory(BehaviorFactory behaviorFactory) {
        this.behaviorFactory = behaviorFactory;
    }

    public GameEntity createGameEntity(World world, MapObject mapObject, AssetManager assetManager) {
        MapProperties props = mapObject.getProperties();
        PhysicsModel physModel = behaviorFactory.createPhysicsModel(world, mapObject);
        IController controller = behaviorFactory.createController(props);
        IView view = behaviorFactory.createView(props, assetManager);

        int id = props.get(TiledConstants.ENTITY_ID, Integer.class);

        GameEntity entity = constructGameEntity(physModel, view, controller, props, assetManager);
        if (entity != null) {
            entity.setId(id);
        }
        return entity;
    }

    public void finalizeEntities() {
    }

    protected abstract GameEntity constructGameEntity(PhysicsModel physModel, IView view, IController controller,
            MapProperties props, AssetManager assetManager);
}
