package net.mikegraf.game.states.play.entities.triggers;

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
import net.mikegraf.game.states.play.entities.player.Player;
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
    protected GameEntity constructGameEntity(ICollisionBehavior collisionB, IMovementBehavior moveB,
            IRenderBehavior renderB, Body body, MapProperties props) {
        String type = props.get(TiledConstants.ENTITY_TYPE, String.class);
        String id = props.get(TiledConstants.ENTITY_ID, String.class);
        ICondition<Player> condition = new PlayerNoCondition();

        if (type.equals(END_TRIGGER_TYPE)) {
            int dX = props.get(END_TRIGGER_DEST_X, Integer.class);
            int dY = props.get(END_TRIGGER_DEST_Y, Integer.class);
            return new EndTrigger(id, collisionB, moveB, renderB, body, condition, dX, dY);
        } else {
            throw new IllegalArgumentException("No type: " + type);
        }
    }

}
