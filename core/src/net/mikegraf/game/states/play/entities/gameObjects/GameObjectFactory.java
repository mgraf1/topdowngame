package net.mikegraf.game.states.play.entities.gameObjects;

import java.util.HashMap;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;

import net.mikegraf.game.audio.SoundEffectFactory;
import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.GameEntityFactory;
import net.mikegraf.game.states.play.entities.behavior.BehaviorFactory;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.bodies.BodyFactory;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.IView;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.NullCondition;
import net.mikegraf.game.states.play.logic.PlayerItemCondition;

public class GameObjectFactory extends GameEntityFactory {

    private SoundEffectFactory soundEffectFactory;
    private HashMap<Switch, String> switchToDoorIdMap;
    private HashMap<String, Door> doorIdToDoorMap;

    public GameObjectFactory(BodyFactory bodyFactory, BehaviorFactory behaviorFactory,
            SoundEffectFactory soundEffectFactory) {
        super(bodyFactory, behaviorFactory);

        this.soundEffectFactory = soundEffectFactory;
        this.switchToDoorIdMap = new HashMap<Switch, String>();
        this.doorIdToDoorMap = new HashMap<String, Door>();
    }

    @Override
    protected GameEntity constructGameEntity(ICollisionBehavior collisionB, IController controller,
            IView view, Body body, MapProperties props) {
        String type = props.get("type", String.class);
        String id = props.get("id", String.class);
        SoundEffectIndex soundEffectIndex = soundEffectFactory.createSoundEffectIndex(type);

        if (type.equals(TiledConstants.ENTITY_TYPE_DOOR)) {
            ICondition<Player> cond = null;
            String key = props.get(TiledConstants.ENTITY_DOOR_PROP_KEY, String.class);
            if (key != null) {
                cond = new PlayerItemCondition(key, true);
            } else {
                cond = new NullCondition<Player>();
            }
            Door door = new Door(collisionB, controller, view, body, soundEffectIndex, cond);
            doorIdToDoorMap.put(id, door);
            return door;

        } else if (type.equals(TiledConstants.ENTITY_TYPE_SWITCH)) {
            String door = props.get(TiledConstants.ENTITY_SWITCH_PROP_DOOR, String.class);
            Switch switchToReturn = new Switch(collisionB, controller, view, body, soundEffectIndex);
            switchToDoorIdMap.put(switchToReturn, door);
            return switchToReturn;

        } else {
            return new GameObject(collisionB, controller, view, body, soundEffectIndex);
        }
    }

    @Override
    public void finalizeEntities() {
        for (Switch s : switchToDoorIdMap.keySet()) {
            String doorName = switchToDoorIdMap.get(s);
            Door door = doorIdToDoorMap.get(doorName);
            s.setDoor(door);
        }
        switchToDoorIdMap.clear();
        doorIdToDoorMap.clear();
    }
}
