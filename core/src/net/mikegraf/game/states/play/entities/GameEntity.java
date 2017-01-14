package net.mikegraf.game.states.play.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.collision.ICollisionBehavior;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.view.IView;

public abstract class GameEntity {

    public GameEntityState state;

    private int id;
    protected ICollisionBehavior collisionBehavior;
    protected IView view;
    protected IController controller;
    protected Body body;
    protected float velocity;

    public GameEntity(ICollisionBehavior collisionBehavior, IController controller,
            IView view, Body body) {
        this.collisionBehavior = collisionBehavior;
        this.view = view;
        this.controller = controller;
        this.state = GameEntityState.VISIBLE;
        this.body = body;
        body.setUserData(this);
    }

    public void update(float deltaTime) {
        Vector2 movementVector = controller.update(this, deltaTime);
        afterUpdate(movementVector);
    }

    public void render(SpriteBatch batch, float totalTime) {
        if (body.isActive()) {
            beforeRender(totalTime);
            Vector2 position = body.getPosition();
            view.render(batch, totalTime, position);
        }
    }
    
    public void move(Vector2 movementVector) {
    	body.setLinearVelocity(movementVector.x * velocity, movementVector.y * velocity);
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

    public int getId() {
        return id;
    }

    public void afterUpdate(Vector2 movementVector) {
    }

    public void beforeRender(float totalTime) {
    }

    public void setVelocity(float value) {
        velocity = value;
    }
    
    public void setId(int id) {
    	this.id = id;
    }

	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}
}
