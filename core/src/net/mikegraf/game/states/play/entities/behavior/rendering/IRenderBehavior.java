package net.mikegraf.game.states.play.entities.behavior.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface IRenderBehavior {

    public void render(SpriteBatch batch, float totalTime, Vector2 position);

    public void dispose();

    public void setMode(String mode);

}
