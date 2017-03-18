package net.mikegraf.game.states.play.entities.badGuys;

import java.util.HashMap;

import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.controller.BadGuyController;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.IView;

public class BadGuy extends GameEntity {

    public static final String MOVE_LEFT_ANIMATION_NAME = "moveLeft";
    public static final String MOVE_UP_ANIMATION_NAME = "moveUp";
    public static final String MOVE_RIGHT_ANIMATION_NAME = "moveRight";
    public static final String MOVE_DOWN_ANIMATION_NAME = "moveDown";

    private HashMap<String, String> vectorHashToAnimationMap;

    public BadGuy(PhysicsModel physModel, IView view, IController controller) {
        super(physModel, view, controller);
        this.vectorHashToAnimationMap = createMovementAnimationMap();
    }

    public void addWaypoint(Vector2 waypoint) {
        BadGuyController controller = (BadGuyController) this.controller;
        controller.addWaypoint(waypoint);
    }

    public void finalizeWaypoints() {
        BadGuyController controller = (BadGuyController) this.controller;
        controller.setPhysicsModel(physModel);
    }

    @Override
    public void afterUpdate(Vector2 movementVector) {
        smoothVector(movementVector);
        String animation = vectorHashToAnimationMap.get(movementVector.toString());
        if (animation != null) {
            view.setMode(animation);
        }
    }

    private void smoothVector(Vector2 vector) {
        if (Math.abs(vector.x) > Math.abs(vector.y)) {
            if (vector.x > 0) {
                vector.x = 1;
                vector.y = 0;
            } else {
                vector.x = -1;
                vector.y = 0;
            }
        } else {
            if (vector.y > 0) {
                vector.y = 1;
                vector.x = 0;
            } else {
                vector.y = -1;
                vector.x = 0;
            }
        }
    }

    private HashMap<String, String> createMovementAnimationMap() {
        HashMap<String, String> movementAnimationMap = new HashMap<String, String>();
        movementAnimationMap.put(new Vector2(-1, 0).toString(), MOVE_LEFT_ANIMATION_NAME);
        movementAnimationMap.put(new Vector2(0, 1).toString(), MOVE_UP_ANIMATION_NAME);
        movementAnimationMap.put(new Vector2(1, 0).toString(), MOVE_RIGHT_ANIMATION_NAME);
        movementAnimationMap.put(new Vector2(0, -1).toString(), MOVE_DOWN_ANIMATION_NAME);
        return movementAnimationMap;
    }
}
