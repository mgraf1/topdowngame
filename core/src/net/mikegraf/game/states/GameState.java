package net.mikegraf.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.mikegraf.game.main.BoundedOrthoCamera;
import net.mikegraf.game.main.MyGdxGame;

public abstract class GameState {

    protected GameStateManager gsm;
    protected MyGdxGame game;

    protected SpriteBatch sb;
    protected BoundedOrthoCamera cam;
    protected OrthographicCamera hudCam;

    protected GameState(MyGdxGame g) {
        game = g;
        sb = game.getSpriteBatch();
        cam = game.getCamera();
        hudCam = game.getHUDCamera();
    }

    public abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(float totalTime);

    public abstract void dispose();
}
