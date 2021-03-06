package net.mikegraf.game.states.play.entities.gameObjects;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.audio.SoundEffectFactory;
import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.GameEntityFactory;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.logic.FailCondition;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerItemCondition;

public class GameObjectFactory extends GameEntityFactory {

    private SoundEffectFactory soundEffectFactory;
    private HashMap<Switch, Integer> switchToDoorIdMap;
    private HashMap<Integer, Door> doorIdToDoorMap;

    public GameObjectFactory(BehaviorFactory behaviorFactory, SoundEffectFactory soundEffectFactory) {
        super(behaviorFactory);

        this.soundEffectFactory = soundEffectFactory;
        this.switchToDoorIdMap = new HashMap<Switch, Integer>();
        this.doorIdToDoorMap = new HashMap<Integer, Door>();
    }

    @Override
    public void finalizeEntities() {
        for (Switch s : switchToDoorIdMap.keySet()) {
            int doorId = switchToDoorIdMap.get(s);
            Door door = doorIdToDoorMap.get(doorId);
            s.setDoor(door);
        }
        switchToDoorIdMap.clear();
        doorIdToDoorMap.clear();
    }

    @Override
    protected GameEntity constructGameEntity(PhysicsModel physModel, IView view, IController controller,
            MapProperties props, AssetManager assetManager) {
        String type = props.get(TiledConstants.ENTITY_TYPE, String.class);
        int id = props.get(TiledConstants.ENTITY_ID, Integer.class);
        SoundEffectIndex soundEffectIndex = soundEffectFactory.createSoundEffectIndex(type, assetManager);

        if (type.equals(TiledConstants.ENTITY_TYPE_DOOR)) {
            ICondition<Player> cond = null;
            String key = props.get(TiledConstants.ENTITY_DOOR_PROP_KEY, String.class);
            if (key != null) {
                cond = new PlayerItemCondition(key, true);
            } else {
                cond = new FailCondition<Player>();
            }
            Door door = new Door(physModel, view, controller, soundEffectIndex, cond);
            doorIdToDoorMap.put(id, door);
            return door;

        } else if (type.equals(TiledConstants.ENTITY_TYPE_SWITCH)) {
            int door = Integer.parseInt(props.get(TiledConstants.ENTITY_SWITCH_PROP_DOOR, String.class));
            Switch switchToReturn = new Switch(physModel, view, controller, soundEffectIndex);
            int switchId = props.get(TiledConstants.ENTITY_ID, Integer.class);
            switchToReturn.setId(switchId);

            switchToDoorIdMap.put(switchToReturn, door);
            return switchToReturn;

        } else {
            return new GameObject(physModel, view, controller, soundEffectIndex);
        }
    }
}
