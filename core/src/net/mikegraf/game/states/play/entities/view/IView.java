package net.mikegraf.game.states.play.entities.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface IView {

    public void render(SpriteBatch batch, float totalTime, Vector2 position);

    public void render(SpriteBatch batch, float x, float y);

    public void render(SpriteBatch batch, float x, float y, float scale);

    public void dispose();

    public void setMode(String mode);

}
