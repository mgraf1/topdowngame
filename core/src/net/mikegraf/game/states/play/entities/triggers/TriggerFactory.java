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
    private final String DEATH_TRIGGER_TYPE = "death";
    private final String DAMAGE_TRIGGER_TYPE = "damage";
    private final String END_TRIGGER_DEST_X = "destX";
    private final String END_TRIGGER_DEST_Y = "destY";
    private final String DAMAGE_TRIGGER_AMOUNT = "amount";

    public TriggerFactory(BodyFactory bodyFactory, BehaviorFactory behaviorFactory) {
        super(bodyFactory, behaviorFactory);
    }

    @Override
    protected GameEntity constructGameEntity(ICollisionBehavior collisionB, IController controller, IView view,
            Body body, MapProperties props, AssetManager assetManager) {
        String type = props.get(TiledConstants.ENTITY_TYPE, String.class);
        ICondition<Player> condition = new PlayerNoCondition();

        if (type.equals(END_TRIGGER_TYPE)) {
            int dX = Integer.parseInt(props.get(END_TRIGGER_DEST_X, String.class));
            int dY = Integer.parseInt(props.get(END_TRIGGER_DEST_Y, String.class));
            return new EndTrigger(collisionB, controller, view, body, condition, dX, dY);

        } else if (type.equals(DEATH_TRIGGER_TYPE)) {
            return new DeathTrigger(collisionB, controller, view, body, condition);

        } else if (type.equals(DAMAGE_TRIGGER_TYPE)) {
            int amount = Integer.parseInt(props.get(DAMAGE_TRIGGER_AMOUNT, String.class));
            return new DamageTrigger(collisionB, controller, view, body, condition, amount);

        } else {
            throw new IllegalArgumentException("No type: " + type);
        }
    }

}
