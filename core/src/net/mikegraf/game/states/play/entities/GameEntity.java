package net.mikegraf.game.states.play.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.behavior.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.behavior.movement.IMovementBehavior;
import net.mikegraf.game.states.play.entities.behavior.rendering.IRenderBehavior;

public abstract class GameEntity {

    public GameEntityState state;

    private String id;
    protected ICollisionBehavior collisionBehavior;
    protected IRenderBehavior renderBehavior;
    protected IMovementBehavior movementBehavior;
    protected Body body;
    protected float velocity;

    public GameEntity(String id, ICollisionBehavior collisionBehavior, IMovementBehavior movementBehavior,
            IRenderBehavior renderBehavior, Body body) {
        this.collisionBehavior = collisionBehavior;
        this.renderBehavior = renderBehavior;
        this.movementBehavior = movementBehavior;
        this.state = GameEntityState.VISIBLE;
        this.body = body;
        body.setUserData(this);
    }

    public void update(float deltaTime) {
        Vector2 movementVector = movementBehavior.createMovementVector();
        beforeMove(movementVector);
        body.setLinearVelocity(movementVector.x * velocity, movementVector.y * velocity);
    }

    public void render(SpriteBatch batch, float totalTime) {
        if (body.isActive()) {
            beforeRender(totalTime);
            Vector2 position = body.getPosition();
            renderBehavior.render(batch, totalTime, position);
        }
    }

    public void handleCollision(CollisionInfo info) {
        collisionBehavior.handleCollision(info);
    }

    public void handleEndCollision(CollisionInfo info) {
        collisionBehavior.handleEndCollision(info);
    }

    public void hide() {
        body.setActive(false);
        this.state = GameEntityState.HIDDEN;
    }

    public void show() {
        body.setActive(true);
        this.state = GameEntityState.VISIBLE;
    }

    public void dispose(World world) {
        this.state = GameEntityState.DISPOSED;
    }

    public String getId() {
        return id;
    }

    public void setState(GameEntityState state) {
        this.state = state;
    }

    public void beforeMove(Vector2 movementVector) {
    }

    public void beforeRender(float totalTime) {
    }

    public void setVelocity(float value) {
        velocity = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameEntity other = (GameEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
