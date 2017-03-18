package net.mikegraf.game.states.play.entities.controller;

import com.badlogic.gdx.ai.steer.SteeringAcceleration;
import com.badlogic.gdx.ai.steer.behaviors.FollowPath;
import com.badlogic.gdx.ai.steer.utils.Path;
import com.badlogic.gdx.ai.steer.utils.paths.LinePath;
import com.badlogic.gdx.ai.steer.utils.paths.LinePath.LinePathParam;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;

public class BadGuyController implements IController {

    private static final SteeringAcceleration<Vector2> STEERING_OUTPUT = new SteeringAcceleration<Vector2>(
            new Vector2());

    private FollowPath<Vector2, LinePathParam> steeringBehavior;
    private Array<Vector2> waypoints;

    public BadGuyController(Array<Vector2> waypoints) {
        this.waypoints = waypoints;
    }

    public void setPhysicsModel(PhysicsModel physModel) {
        Path<Vector2, LinePathParam> path = new LinePath<Vector2>(waypoints);
        steeringBehavior = new FollowPath<Vector2, LinePathParam>(physModel, path, 0, .2f);
    }

    @Override
    public Vector2 update(GameEntity entity, float deltaTime) {
        steeringBehavior.calculateSteering(STEERING_OUTPUT);
        return entity.applySteering(STEERING_OUTPUT.linear, deltaTime);
    }

    public void addWaypoint(Vector2 waypoint) {
        waypoints.insert(0, waypoint);
    }
}
