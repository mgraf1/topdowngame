package net.mikegraf.game.states.play.entities.controller;

import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.states.play.entities.GameEntity;

public class DefaultController implements IController {

    private Vector2 movementVector;

    public DefaultController() {
        this.movementVector = new Vector2();
    }

    @Override
    public Vector2 update(GameEntity entity, float deltaTime) {
        return movementVector;
    }
}
