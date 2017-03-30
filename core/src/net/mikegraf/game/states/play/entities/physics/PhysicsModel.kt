package net.mikegraf.game.states.play.entities.physics

import com.badlogic.gdx.ai.steer.Steerable
import com.badlogic.gdx.ai.utils.Location
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.World

import net.mikegraf.game.states.play.contact.CollisionInfo
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior

open class PhysicsModel(val body: Body, val collisionBehavior: ICollisionBehavior, var velocity: Float) : Steerable<Vector2> {

    private var orientation: Float = 0.toFloat()
    private var maxLinearSpeed: Float = 0.toFloat()
    private var maxLinearAcceleration: Float = 0.toFloat()
    private var maxAngularSpeed: Float = 0.toFloat()
    private var maxAngularAcceleration: Float = 0.toFloat()
    private var zeroLinearSpeedThreshold: Float = 0.toFloat()
    private var tagged: Boolean = false

    fun handleCollision(info: CollisionInfo) {
        collisionBehavior.handleCollision(info)
    }

    fun handleEndCollision(info: CollisionInfo) {
        collisionBehavior.handleEndCollision(info)
    }

    override fun getPosition(): Vector2 {
        return body.position
    }

    override fun getOrientation(): Float {
        return orientation
    }

    override fun setOrientation(orientation: Float) {
        this.orientation = orientation
    }

    override fun vectorToAngle(vector: Vector2): Float {
        return Math.atan2((-vector.x).toDouble(), vector.y.toDouble()).toFloat()
    }

    override fun angleToVector(outVector: Vector2, angle: Float): Vector2 {
        outVector.x = -Math.sin(angle.toDouble()).toFloat()
        outVector.y = Math.cos(angle.toDouble()).toFloat()
        return outVector
    }

    override fun newLocation(): Location<Vector2> {
        return PhysicsModelLocation()
    }

    override fun getZeroLinearSpeedThreshold(): Float {
        return zeroLinearSpeedThreshold
    }

    override fun setZeroLinearSpeedThreshold(value: Float) {
        this.zeroLinearSpeedThreshold = value
    }

    override fun getMaxLinearSpeed(): Float {
        return maxLinearSpeed
    }

    override fun setMaxLinearSpeed(maxLinearSpeed: Float) {
        this.maxLinearSpeed = maxLinearSpeed
    }

    override fun getMaxLinearAcceleration(): Float {
        return maxLinearAcceleration
    }

    override fun setMaxLinearAcceleration(maxLinearAcceleration: Float) {
        this.maxLinearAcceleration = maxLinearAcceleration
    }

    override fun getMaxAngularSpeed(): Float {
        return maxAngularSpeed
    }

    override fun setMaxAngularSpeed(maxAngularSpeed: Float) {
        this.maxAngularSpeed = maxAngularSpeed
    }

    override fun getMaxAngularAcceleration(): Float {
        return maxAngularAcceleration
    }

    override fun setMaxAngularAcceleration(maxAngularAcceleration: Float) {
        this.maxAngularAcceleration = maxAngularAcceleration
    }

    override fun getLinearVelocity(): Vector2 {
        return body.linearVelocity
    }

    override fun getAngularVelocity(): Float {
        return body.angularVelocity
    }

    override fun getBoundingRadius(): Float {
        return 0f
    }

    override fun isTagged(): Boolean {
        return tagged
    }

    override fun setTagged(tagged: Boolean) {
        this.tagged = tagged
    }

    open fun setBodyUserData(data: Any) {
        body.userData = data
    }

    var isActive: Boolean
        get() = body!!.isActive
        set(flag) {
            body.isActive = flag
        }

    fun move(movementVector: Vector2) {
        MOVEMENT_VECTOR.x = (movementVector.x * velocity) - body.linearVelocity.x
        MOVEMENT_VECTOR.y = (movementVector.y * velocity) - body.linearVelocity.y

        body.applyLinearImpulse(MOVEMENT_VECTOR.scl(body.mass), body.worldCenter, true)
    }

    fun push(sourceLocation: Vector2, force: Float) {
        val vec = sourceLocation.setLength(1f).scl(10f)
        body.applyLinearImpulse(vec, body.position, false)
    }

    fun dispose(world: World) {
        world.destroyBody(body)
    }

    fun applySteering(steeringVector: Vector2, time: Float): Vector2 {
        val linearVelocity = body.linearVelocity

        // Update position and linear velocity. Velocity is trimmed to maximum speed
        body.position.mulAdd(linearVelocity, time)
        linearVelocity.mulAdd(steeringVector, time).limit(this.getMaxLinearSpeed())

        val newOrientation = calculateOrientationFromLinearVelocity(linearVelocity)
        if (newOrientation != this.orientation) {
            body.angularVelocity = (newOrientation - this.orientation) * time
            this.orientation = newOrientation
        }

        body.linearVelocity = linearVelocity
        return linearVelocity
    }

    private fun calculateOrientationFromLinearVelocity(linearVelocity: Vector2): Float {
        // If we haven't got any velocity, then we can do nothing.
        if (linearVelocity.isZero(this.getZeroLinearSpeedThreshold()))
            return this.getOrientation()

        return this.vectorToAngle(linearVelocity)
    }

    companion object {

        private val MOVEMENT_VECTOR = Vector2()
    }
}
