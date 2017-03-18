package net.mikegraf.game.states.play.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import net.mikegraf.game.states.play.contact.CollisionInfo;
import net.mikegraf.game.states.play.entities.controller.IController;
import net.mikegraf.game.states.play.entities.physics.PhysicsModel;
import net.mikegraf.game.states.play.entities.view.IView;

public abstract class GameEntity {

    public GameEntityState state;

    private int id;
    protected PhysicsModel physModel;
    protected IView view;
    protected IController controller;

    public GameEntity(PhysicsModel physModel, IView view, IController controller) {
        this.view = view;
        this.controller = controller;
        this.state = GameEntityState.VISIBLE;
        this.physModel = physModel;
        this.physModel.setBodyUserData(this);
    }

    public void update(float deltaTime) {
        Vector2 movementVector = controller.update(this, deltaTime);
        afterUpdate(movementVector);
    }

    public void render(SpriteBatch batch, float totalTime) {
        if (physModel.isActive()) {
            beforeRender(totalTime);
            Vector2 position = physModel.getPosition();
            view.render(batch, totalTime, position);
        }
    }

    public void move(Vector2 movementVector) {
        physModel.move(movementVector);
    }

    public Vector2 applySteering(Vector2 steeringVector, float time) {
        return physModel.applySteering(steeringVector, time);
    }

    public void renderHud(SpriteBatch batch, float x, float y, float scale) {
        view.render(batch, x, y, scale);
    }

    public void handleCollision(CollisionInfo info) {
        physModel.handleCollision(info);
    }

    public void handleEndCollision(CollisionInfo info) {
        physModel.handleEndCollision(info);
    }

    public void hide() {
        physModel.setActive(false);
        state = GameEntityState.HIDDEN;
    }

    public void show() {
        physModel.setActive(true);
        state = GameEntityState.VISIBLE;
    }

    public void dispose(World world) {
        state = GameEntityState.DISPOSED;
        physModel.dispose(world);
        view.dispose();
    }

    public void afterUpdate(Vector2 movementVector) {
    }

    public void beforeRender(float totalTime) {
    }

    public int getId() {
        return id;
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
