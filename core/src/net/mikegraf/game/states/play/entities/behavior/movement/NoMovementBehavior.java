package net.mikegraf.game.states.play.entities.behavior.movement;

import com.badlogic.gdx.math.Vector2;

public class NoMovementBehavior implements IMovementBehavior {

    private Vector2 movementVector;

    public NoMovementBehavior() {
        movementVector = new Vector2(0, 0);
    }

    @Override
    public Vector2 createMovementVector() {
        return movementVector;
    }

}
