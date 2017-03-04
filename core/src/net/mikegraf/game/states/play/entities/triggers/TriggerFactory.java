package net.mikegraf.game.states.play.entities.triggers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.GameEntityFactory;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
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

    public TriggerFactory(BehaviorFactory behaviorFactory) {
        super(behaviorFactory);
    }

    @Override
    protected GameEntity constructGameEntity(PhysicsModel physModel, IView view, IController controller,
            MapProperties props, AssetManager assetManager) {
        String type = props.get(TiledConstants.ENTITY_TYPE, String.class);
        ICondition<Player> condition = new PlayerNoCondition();

        if (type.equals(END_TRIGGER_TYPE)) {
            int dX = Integer.parseInt(props.get(END_TRIGGER_DEST_X, String.class));
            int dY = Integer.parseInt(props.get(END_TRIGGER_DEST_Y, String.class));
            return new EndTrigger(physModel, view, controller, condition, dX, dY);

        } else if (type.equals(DEATH_TRIGGER_TYPE)) {
            return new DeathTrigger(physModel, view, controller, condition);

        } else if (type.equals(DAMAGE_TRIGGER_TYPE)) {
            int amount = Integer.parseInt(props.get(DAMAGE_TRIGGER_AMOUNT, String.class));
            return new DamageTrigger(physModel, view, controller, condition, amount);

        } else {
            throw new IllegalArgumentException("No type: " + type);
        }
    }

}
