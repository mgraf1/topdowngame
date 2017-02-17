package net.mikegraf.game.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.mikegraf.game.states.GameStateFactory;
import net.mikegraf.game.states.GameStateManager;
import net.mikegraf.game.states.play.controls.MyInput;
import net.mikegraf.game.states.play.controls.MyInputHandler;

public class MyGdxGame extends ApplicationAdapter {

    // Constants.
    public static final float STEP = 1 / 60f;
    public static final int V_WIDTH = 320;
    public static final int V_HEIGHT = 240;
    public static final int SCALE = 2;

    private float accum;
    private GameStateManager gsm;
    private SpriteBatch sb;
    private BoundedOrthoCamera cam;
    private OrthographicCamera hudCam;

    @Override
    public void create() {

        // Set up input handling.
        Gdx.input.setInputProcessor(new MyInputHandler());

        // Rendering objects.
        sb = new SpriteBatch();
        cam = new BoundedOrthoCamera(V_WIDTH, V_HEIGHT);
        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);

        ShaderManager.createShaders();

        // Set up state manager.
        gsm = new GameStateManager(new GameStateFactory(this));
    }

    @Override
    public void render() {
        accum += Gdx.graphics.getDeltaTime();
        while (accum >= STEP) {
            accum -= STEP;
            gsm.update(STEP);
            gsm.render(STEP);
            MyInput.update();
        }
    }

    public GameStateManager getGameStateManager() {
        return gsm;
    }

    public BoundedOrthoCamera getCamera() {
        return cam;
    }

    public OrthographicCamera getHUDCamera() {
        return hudCam;
    }

    public SpriteBatch getSpriteBatch() {
        return sb;
    }
}
