package net.mikegraf.game.states.play.entities.physics;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;

public class PhysicsModel implements Steerable<Vector2> {

    private Body body;
    private ICollisionBehavior collisionBehavior;

    private float orientation;
    private float maxLinearSpeed;
    private float maxLinearAcceleration;
    private float maxAngularSpeed;
    private float maxAngularAcceleration;
    private float zeroLinearSpeedThreshold;
    private float velocity;
    private boolean tagged;

    public PhysicsModel(Body body, ICollisionBehavior collisionBehavior) {
        this.body = body;
        this.collisionBehavior = collisionBehavior;
    }

    public PhysicsModel(Body body, ICollisionBehavior collisionBehavior, float maxVelocity) {
        this.body = body;
        this.collisionBehavior = collisionBehavior;
        this.velocity = maxVelocity;
    }

    public void handleCollision(CollisionInfo info) {
        collisionBehavior.handleCollision(info);
    }

    public void handleEndCollision(CollisionInfo info) {
        collisionBehavior.handleEndCollision(info);
    }

    @Override
    public Vector2 getPosition() {
        return body.getPosition();
    }

    @Override
    public float getOrientation() {
        return orientation;
    }

    @Override
    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    @Override
    public float vectorToAngle(Vector2 vector) {
        return (float) Math.atan2(-vector.x, vector.y);
    }

    @Override
    public Vector2 angleToVector(Vector2 outVector, float angle) {
        outVector.x = -(float) Math.sin(angle);
        outVector.y = (float) Math.cos(angle);
        return outVector;
    }

    @Override
    public Location<Vector2> newLocation() {
        return new PhysicsModelLocation();
    }

    @Override
    public float getZeroLinearSpeedThreshold() {
        return zeroLinearSpeedThreshold;
    }

    @Override
    public void setZeroLinearSpeedThreshold(float value) {
        this.zeroLinearSpeedThreshold = value;
    }

    @Override
    public float getMaxLinearSpeed() {
        return maxLinearSpeed;
    }

    @Override
    public void setMaxLinearSpeed(float maxLinearSpeed) {
        this.maxLinearSpeed = maxLinearSpeed;
    }

    @Override
    public float getMaxLinearAcceleration() {
        return maxLinearAcceleration;
    }

    @Override
    public void setMaxLinearAcceleration(float maxLinearAcceleration) {
        this.maxLinearAcceleration = maxLinearAcceleration;
    }

    @Override
    public float getMaxAngularSpeed() {
        return maxAngularSpeed;
    }

    @Override
    public void setMaxAngularSpeed(float maxAngularSpeed) {
        this.maxAngularSpeed = maxAngularSpeed;
    }

    @Override
    public float getMaxAngularAcceleration() {
        return maxAngularAcceleration;
    }

    @Override
    public void setMaxAngularAcceleration(float maxAngularAcceleration) {
        this.maxAngularAcceleration = maxAngularAcceleration;
    }

    @Override
    public Vector2 getLinearVelocity() {
        return body.getLinearVelocity();
    }

    @Override
    public float getAngularVelocity() {
        return body.getAngularVelocity();
    }

    @Override
    public float getBoundingRadius() {
        return 0;
    }

    @Override
    public boolean isTagged() {
        return tagged;
    }

    @Override
    public void setTagged(boolean tagged) {
        this.tagged = tagged;
    }

    public void setBodyUserData(Object data) {
        body.setUserData(data);
    }

    public boolean isActive() {
        return body.isActive();
    }

    public void setActive(boolean flag) {
        body.setActive(flag);
    }

    public void move(Vector2 movementVector) {
        body.setLinearVelocity(movementVector.x * velocity, movementVector.y * velocity);
    }

    public void dispose(World world) {
        world.destroyBody(body);
    }

    public Vector2 applySteering(Vector2 steeringVector, float time) {
        Vector2 linearVelocity = body.getLinearVelocity();

        // Update position and linear velocity. Velocity is trimmed to maximum
        // speed
        body.getPosition().mulAdd(linearVelocity, time);
        linearVelocity.mulAdd(steeringVector, time).limit(this.getMaxLinearSpeed());

        float newOrientation = calculateOrientationFromLinearVelocity(linearVelocity);
        if (newOrientation != this.orientation) {
            body.setAngularVelocity((newOrientation - this.orientation) * time);
            this.orientation = newOrientation;
        }

        body.setLinearVelocity(linearVelocity);
        return linearVelocity;
    }

    private float calculateOrientationFromLinearVelocity(Vector2 linearVelocity) {
        // If we haven't got any velocity, then we can do nothing.
        if (linearVelocity.isZero(this.getZeroLinearSpeedThreshold()))
            return this.getOrientation();

        return this.vectorToAngle(linearVelocity);
    }
}
