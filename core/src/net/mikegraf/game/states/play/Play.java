package net.mikegraf.game.states.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.google.inject.Guice;
import com.google.inject.Injector;

import net.mikegraf.game.main.MyGdxGame;
import net.mikegraf.game.main.constants.B2dConstants;
import net.mikegraf.game.states.GameState;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.player.PlayerProfile;
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
    private int currLevelX;
    private int currLevelY;
    private PlayerProfile playerProfile;

    public Play(MyGdxGame g) {
        super(g);

        Injector injector = Guice.createInjector(new PlayModule(this));
        this.levelFactory = injector.getInstance(LevelFactory.class);

        // Box 2d render for debug purposes.
        this.b2dr = new Box2DDebugRenderer();

        // Set up box2d camera.
        this.b2dCam = new OrthographicCamera();
        this.b2dCam.setToOrtho(false, MyGdxGame.V_WIDTH / B2dConstants.PPM, MyGdxGame.V_HEIGHT / B2dConstants.PPM);

        // Get first level.
        this.playerProfile = new PlayerProfile(Player.STARTING_LIVES);
        setCurrentLevel(START_WORLD_X, START_WORLD_Y);
    }

    // Construct the level at World coordinates (x,y).
    public void setCurrentLevel(int x, int y) {

        if (level != null) {
            level.dispose();
        }

        this.level = levelFactory.buildLevel(x, y);

        // Prepare the level to be rendered.
        level.prepare(playerProfile, b2dr, b2dCam, cam, hudCam, sb, DEBUG_MODE);
        currLevelX = x;
        currLevelY = y;
    }

    @Override
    public void update(float dt) {

        handleInput();

        level.update(dt);

        switch (level.getState()) {
        case COMPLETE:
            Vector2 levelCoords = level.getNextLevel();
            setCurrentLevel((int) levelCoords.x, (int) levelCoords.y);
            break;
        case RESTARTING:
            setCurrentLevel(currLevelX, currLevelY);
            break;
        case ACTIVE:
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

    @Override
    public void handleInput() {
    }
}
