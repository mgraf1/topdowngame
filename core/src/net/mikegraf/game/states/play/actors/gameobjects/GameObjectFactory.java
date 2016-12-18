package net.mikegraf.game.states.play.actors.gameobjects;

import java.util.HashMap;

import com.badlogic.gdx.maps.MapProperties;
import com.google.inject.Inject;

import net.mikegraf.game.audio.SoundEffectFactory;
import net.mikegraf.game.audio.SoundEffectIndex;
import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.NullCondition;
import net.mikegraf.game.states.play.logic.PlayerItemCondition;

/* Responsible for the creation of GameObjects. */
public class GameObjectFactory {

    private final String DOOR_TYPE = "door";
    private final String DOOR_KEY_PROP = "key";
    private final String SWITCH_DOOR_PROP = "door";
    private final String SWITCH_TYPE = "switch";

    private HashMap<Switch, String> switchToDoorNameMap;
    private HashMap<String, Door> doorNameToDoorMap;
    private SoundEffectFactory soundEffectFactory;

    @Inject
    public GameObjectFactory(SoundEffectFactory soundEffectFactory) {
        this.soundEffectFactory = soundEffectFactory;
        this.switchToDoorNameMap = new HashMap<Switch, String>();
        this.doorNameToDoorMap = new HashMap<String, Door>();
    }

    public GameObject createGameObject(B2DSprite sprite, MapProperties props) {

        String type = props.get("type", String.class);
        String name = props.get("id", String.class);
        SoundEffectIndex soundEffectIndex = soundEffectFactory.createSoundEffectIndex(type);

        if (type.equals(DOOR_TYPE)) {
            ICondition<Player> cond = null;
            String key = props.get(DOOR_KEY_PROP, String.class);
            if (key != null) {
                cond = new PlayerItemCondition(key, true);
            } else {
                cond = new NullCondition<Player>();
            }
            Door door = new Door(sprite, soundEffectIndex, name, cond);
            doorNameToDoorMap.put(name, door);
            return door;

        } else if (type.equals(SWITCH_TYPE)) {
            String door = props.get(SWITCH_DOOR_PROP, String.class);
            Switch switchToReturn = new Switch(sprite, soundEffectIndex, name);
            switchToDoorNameMap.put(switchToReturn, door);
            return switchToReturn;

        } else {
            return new GameObject(sprite, soundEffectIndex, name);
        }
    }

    public void finalizeObjects() {
        for (Switch s : switchToDoorNameMap.keySet()) {
            String doorName = switchToDoorNameMap.get(s);
            Door door = doorNameToDoorMap.get(doorName);
            s.setDoor(door);
        }
        switchToDoorNameMap.clear();
        doorNameToDoorMap.clear();
    }
}
