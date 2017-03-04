package net.mikegraf.game.states.play.entities.items;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.GameEntityFactory;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.IView;

public class ItemFactory extends GameEntityFactory {

    public ItemFactory(BehaviorFactory behaviorFactory) {
        super(behaviorFactory);
    }

    @Override
    protected GameEntity constructGameEntity(PhysicsModel physModel, IView view, IController controller,
            MapProperties props, AssetManager assetManager) {
        String type = props.get(TiledConstants.ENTITY_TYPE, String.class);
        return new Item(physModel, view, controller, type);
    }

}
