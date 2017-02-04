package net.mikegraf.game.states.play.entities.triggers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.GameEntityFactory;
import net.mikegraf.game.states.play.entities.bodies.BodyFactory;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerNoCondition;

public class TriggerFactory extends GameEntityFactory {

    private final String END_TRIGGER_TYPE = "end";
    private final String END_TRIGGER_DEST_X = "destX";
    private final String END_TRIGGER_DEST_Y = "destY";

    public TriggerFactory(BodyFactory bodyFactory, BehaviorFactory behaviorFactory) {
        super(bodyFactory, behaviorFactory);
    }

    @Override
    protected GameEntity constructGameEntity(ICollisionBehavior collisionB, IController moveB, IView renderB, Body body,
            MapProperties props, AssetManager assetManager) {
        String type = props.get(TiledConstants.ENTITY_TYPE, String.class);
        ICondition<Player> condition = new PlayerNoCondition();

        if (type.equals(END_TRIGGER_TYPE)) {
            int dX = Integer.parseInt(props.get(END_TRIGGER_DEST_X, String.class));
            int dY = Integer.parseInt(props.get(END_TRIGGER_DEST_Y, String.class));
            return new EndTrigger(collisionB, moveB, renderB, body, condition, dX, dY);
        } else {
            throw new IllegalArgumentException("No type: " + type);
        }
    }

}
