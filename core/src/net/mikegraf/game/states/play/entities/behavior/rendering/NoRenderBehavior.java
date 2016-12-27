package net.mikegraf.game.states.play.entities.behavior.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class NoRenderBehavior implements IRenderBehavior {

    @Override
    public void render(SpriteBatch batch, float totalTime, Vector2 position) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public void setMode(String mode) {
    }

}
