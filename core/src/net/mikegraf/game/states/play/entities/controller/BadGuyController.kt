package net.mikegraf.game.states.play.entities.controller

import com.badlogic.gdx.ai.steer.SteeringAcceleration
import com.badlogic.gdx.ai.steer.behaviors.FollowPath
import com.badlogic.gdx.ai.steer.utils.paths.LinePath
import com.badlogic.gdx.ai.steer.utils.paths.LinePath.LinePathParam
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array

import net.mikegraf.game.states.play.entities.GameEntity
import net.mikegraf.game.states.play.entities.physics.PhysicsModel

class BadGuyController(private val waypoints: Array<Vector2>) : IController {

    lateinit var steeringBehavior: FollowPath<Vector2, LinePathParam>
    val STEERING_OUTPUT = SteeringAcceleration(Vector2())

    fun setPhysicsModel(physModel: PhysicsModel) {
        val path = LinePath(waypoints)
        steeringBehavior = FollowPath(physModel, path, 0f, .2f)
    }

    override fun update(entity: GameEntity, deltaTime: Float): Vector2 {
        steeringBehavior.calculateSteering(STEERING_OUTPUT)
        return entity.applySteering(STEERING_OUTPUT.linear, deltaTime)
    }

    fun addWaypoint(waypoint: Vector2) {
        waypoints.insert(0, waypoint)
    }
}
