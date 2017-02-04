package net.mikegraf.game.states.play.entities.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class NoRenderView implements IView {

    @Override
    public void render(SpriteBatch batch, float totalTime, Vector2 position) {
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public void setMode(String mode) {
    }

    @Override
    public void render(SpriteBatch batch, float x, float y, float scale) {
    }

}
