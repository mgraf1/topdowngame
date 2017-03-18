package net.mikegraf.game.states.play.entities.badGuys;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.main.helpers.Box2dHelper;
import net.mikegraf.game.states.play.entities.BehaviorFactory;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.GameEntityFactory;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.IView;

public class BadGuyFactory extends GameEntityFactory {

    private HashMap<Integer, BadGuy> idToBadGuyMap;
    private HashMap<Vector2, Integer> waypointToBadGuyIdMap;

    public BadGuyFactory(BehaviorFactory behaviorFactory) {
        super(behaviorFactory);
        this.idToBadGuyMap = new HashMap<Integer, BadGuy>();
        this.waypointToBadGuyIdMap = new HashMap<Vector2, Integer>();
    }

    @Override
    protected GameEntity constructGameEntity(PhysicsModel physModel, IView view, IController controller,
            MapProperties props, AssetManager assetManager) {

        String type = props.get(TiledConstants.ENTITY_TYPE, String.class);
        int id = props.get(TiledConstants.ENTITY_ID, Integer.class);

        if (type.equals(TiledConstants.BADGUY_TYPE_BADGUY)) {
            BadGuy badGuy = new BadGuy(physModel, view, controller);
            idToBadGuyMap.put(id, badGuy);
            return badGuy;
        } else if (type.equals(TiledConstants.BADGUY_WAYPOINT)) {
            float x = props.get(TiledConstants.ENTITY_X, Float.class);
            float y = props.get(TiledConstants.ENTITY_Y, Float.class);
            int badGuyId = Integer.parseInt(props.get(TiledConstants.BADGUY_WAYPOINT_BADGUY_ID, String.class));
            Vector2 waypoint = new Vector2(x, y);
            waypointToBadGuyIdMap.put(waypoint, badGuyId);
        }

        return null;
    }

    @Override
    public void finalizeEntities() {
        for (Vector2 wp : waypointToBadGuyIdMap.keySet()) {
            int badGuyId = waypointToBadGuyIdMap.get(wp);
            BadGuy badGuy = idToBadGuyMap.get(badGuyId);

            Vector2 b2dWaypoint = new Vector2();
            Box2dHelper.toBox2dCoords(b2dWaypoint, wp, 0, 0);
            badGuy.addWaypoint(b2dWaypoint);
        }

        for (int id : idToBadGuyMap.keySet()) {
            BadGuy badGuy = idToBadGuyMap.get(id);
            badGuy.finalizeWaypoints();
        }
    }
}
