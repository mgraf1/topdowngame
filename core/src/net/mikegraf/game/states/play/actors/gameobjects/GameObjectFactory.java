package net.mikegraf.game.states.play.actors.gameobjects;

import com.badlogic.gdx.maps.MapProperties;

import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.logic.ICondition;
import net.mikegraf.game.states.play.logic.PlayerItemCondition;

/* Responsible for the creation of GameObjects. */
public class GameObjectFactory {

    private final String DOOR_TYPE = "door";
    private final String DOOR_KEY_PROP = "key";

    public GameObject createGameObject(B2DSprite sprite, MapProperties props) {

        String type = props.get("type", String.class);

        if (type.equals(DOOR_TYPE)) {

            String key = props.get(DOOR_KEY_PROP, String.class);
            ICondition<Player> cond = new PlayerItemCondition(key);
            return new Door(sprite, cond, key);
        } else {
            return new GameObject(sprite);
        }
    }
}
