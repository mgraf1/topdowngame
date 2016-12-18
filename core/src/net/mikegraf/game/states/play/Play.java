package net.mikegraf.game.states.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.google.inject.Guice;
import com.google.inject.Injector;

import net.mikegraf.game.main.MyGdxGame;
import net.mikegraf.game.states.GameState;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.controls.MyInput;
import net.mikegraf.game.states.play.controls.PlayerController;
import net.mikegraf.game.states.play.levels.B2DVars;
import net.mikegraf.game.states.play.levels.Level;
import net.mikegraf.game.states.play.levels.LevelFactory;

public class Play extends GameState {

    // Constants.
    private static final int START_WORLD_X = 0;
    private static final int START_WORLD_Y = 0;
    private static final boolean DEBUG_MODE = false;

    // Instance variables.
    private LevelFactory levelFactory;
    private Level level;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;
    private Player player;
    private PlayerController playerController;
    private boolean levelComplete;
    private int nextLevelX;
    private int nextLevelY;

    public Play(MyGdxGame g) {
        super(g);

        Injector injector = Guice.createInjector(new PlayModule(this));

        this.levelFactory = injector.getInstance(LevelFactory.class);

        // Box 2d render for debug purposes.
        b2dr = new Box2DDebugRenderer();

        // Set up box2d camera.
        b2dCam = new OrthographicCamera();
        b2dCam.setToOrtho(false, MyGdxGame.V_WIDTH / B2DVars.PPM, MyGdxGame.V_HEIGHT / B2DVars.PPM);

        // Get first level.
        setCurrentLevel(START_WORLD_X, START_WORLD_Y);
    }

    // Construct the level at World coordinates (x,y).
    public void setCurrentLevel(int x, int y) {

        if (level != null) {
            level.dispose();
        }

        this.level = levelFactory.buildLevel(x, y);

        player = level.getPlayer();
        playerController = new PlayerController(player);

        // Prepare the level to be rendered.
        level.prepare(b2dr, b2dCam, cam, hudCam, sb, DEBUG_MODE);
        levelComplete = false;
    }

    @Override
    public void handleInput() {

        playerController.handleInput(MyInput.isDown(MyInput.WALK_UP), MyInput.isDown(MyInput.WALK_DOWN),
                MyInput.isDown(MyInput.WALK_LEFT), MyInput.isDown(MyInput.WALK_RIGHT),
                MyInput.isPressed(MyInput.OPERATE));
    }

    @Override
    public void update(float dt) {

        handleInput();

        level.update(dt);

        if (levelComplete) {
            setCurrentLevel(nextLevelX, nextLevelY);
        }
    }

    @Override
    public void render(float totalTime) {

        // Clear screen.
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        level.render(totalTime);
    }

    @Override
    public void dispose() {
        if (level != null) {
            level.dispose();
        }
        b2dr.dispose();
    }

    public void setNextLevel(int x, int y) {
        levelComplete = true;
        nextLevelX = x;
        nextLevelY = y;
    }

}
