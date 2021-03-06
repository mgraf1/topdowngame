package net.mikegraf.game.states.play.levels;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import net.mikegraf.game.main.BoundedOrthoCamera;
import net.mikegraf.game.main.constants.B2dConstants;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.player.PlayerProfile;
import net.mikegraf.game.states.play.hud.PlayHud;

public class Level {

    // Instance variables.
    private String name;
    private World world;
    private TiledMap map;
    private Array<Body> actorBodies;
    private OrthogonalTiledMapRenderer tmr;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;
    private BoundedOrthoCamera cam;
    private OrthographicCamera hudCam;
    private SpriteBatch sb;
    private boolean debugMode;
    private PlayHud hud;
    private Player player;
    private HashMap<Integer, GameEntity> idToEntityMap;
    private int nextLevelX;
    private int nextLevelY;
    private AssetManager assetManager;
    private LevelState state;

    public Level(String name, Player player, TiledMap tMap, World w, PlayHud h,
            HashMap<Integer, GameEntity> idToEntityMap, AssetManager assetManager) {
        this.map = tMap;
        this.player = player;
        this.name = name;
        this.world = w;
        this.hud = h;
        this.idToEntityMap = idToEntityMap;
        this.nextLevelX = -1;
        this.nextLevelY = -1;
        this.assetManager = assetManager;
    }

    /* Passes level all required objects to begin rendering. */
    public void prepare(PlayerProfile playerProfile, Box2DDebugRenderer bdr, OrthographicCamera bc,
            BoundedOrthoCamera c, OrthographicCamera hudC, SpriteBatch batch, boolean debug) {

        this.tmr = new OrthogonalTiledMapRenderer(map);
        this.debugMode = debug;
        this.b2dr = bdr;
        this.b2dCam = bc;
        this.sb = batch;
        this.actorBodies = new Array<Body>();
        this.cam = c;
        this.hudCam = hudC;
        this.state = LevelState.ACTIVE;

        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);
        int tileWidth = map.getProperties().get("tilewidth", Integer.class);
        int tileHeight = map.getProperties().get("tileheight", Integer.class);
        this.cam.setBounds(0, 0, mapWidth * tileWidth, mapHeight * tileHeight);

        this.player.setProfile(playerProfile);
    }

    public void update(float dt) {
        if (player.isDead()) {
            state = LevelState.RESTARTING;
        }

        world.step(dt, B2dConstants.VEL_INTEGRATIONS, B2dConstants.POS_INTEGRATIONS);

        world.getBodies(actorBodies);
        for (Body b : actorBodies) {
            Object bodyData = b.getUserData();
            if (bodyData instanceof GameEntity) {
                GameEntity entity = (GameEntity) bodyData;
                if (entity != null) {
                    entity.update(dt);
                    switch (entity.state) {
                    case READY_TO_DISPOSE:
                        entity.dispose(world);
                        break;
                    case READY_TO_HIDE:
                        entity.hide();
                        break;
                    case READY_TO_SHOW:
                        entity.show();
                        break;
                    default:
                        break;
                    }
                }
            }
        }
    }

    public void render(float totalTime) {

        // Move the cameras.
        Vector2 pos = player.getPosition();
        cam.moveTo(pos.x * B2dConstants.PPM, pos.y * B2dConstants.PPM);
        b2dCam.position.set(cam.position.x / B2dConstants.PPM, cam.position.y / B2dConstants.PPM, 0);
        cam.update();

        if (debugMode) {
            b2dCam.update();
        }

        // Render TiledMap.
        tmr.setView(cam);
        tmr.render();

        sb.setProjectionMatrix(cam.combined);

        // Render the actors.
        world.getBodies(actorBodies);
        for (Body b : actorBodies) {
            Object bodyData = b.getUserData();
            if (bodyData instanceof GameEntity) {
                GameEntity entity = (GameEntity) bodyData;
                if (entity != null)
                    entity.render(sb, totalTime);
            }

        }
        actorBodies.clear();

        // Render HUD.
        sb.setProjectionMatrix(hudCam.combined);

        // Draw box2d world.
        if (debugMode) {
            b2dr.render(world, b2dCam.combined);
        }

        sb.setProjectionMatrix(hudCam.combined);
        hud.render(sb);
    }

    public void setNextLevel(int x, int y) {
        nextLevelX = x;
        nextLevelY = y;
        state = LevelState.COMPLETE;
    }

    public Vector2 getNextLevel() {
        return new Vector2(nextLevelX, nextLevelY);
    }

    public LevelState getState() {
        return state;
    }

    public void dispose() {
        world.dispose();
        map.dispose();
        assetManager.dispose();

        world = null;
        map = null;
        assetManager = null;
    }

    public String getName() {
        return name;
    }

    public GameEntity getEntity(String id) {
        if (idToEntityMap.containsKey(id)) {
            return idToEntityMap.get(id);
        }
        return null;
    }
}
