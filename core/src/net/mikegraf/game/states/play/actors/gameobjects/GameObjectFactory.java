package net.mikegraf.game.states.play.actors.gameobjects;

import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerItemCondition;
import net.mikegraf.game.states.play.logic.PlayerNoCondition;

/* Responsible for the creation of GameObjects. */
public class GameObjectFactory {

    private final String DOOR_TYPE = "door";
    private final String DOOR_KEY_PROP = "key";
    private final String SWITCH_DOOR_PROP = "door";
    private final String SWITCH_TYPE = "switch";

    public GameObject createGameObject(B2DSprite sprite, MapProperties props) {

        String type = props.get("type", String.class);

        if (type.equals(DOOR_TYPE)) {
            ICondition<Player> cond = null;
            String key = props.get(DOOR_KEY_PROP, String.class);
            if (key != null) {
                cond = new PlayerItemCondition(key, true);
            } else {
                cond = new PlayerNoCondition();
            }
            return new Door(sprite, cond);
        } else if (type.equals(SWITCH_TYPE)) {
            String door = props.get(SWITCH_DOOR_PROP, String.class);
            return new Switch(sprite);
        } else {
            return new GameObject(sprite);
        }
    }
}
