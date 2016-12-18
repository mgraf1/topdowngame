package net.mikegraf.game.states.play.levels;

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
import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.contact.MyContactListener;

public class Level {

    // Instance variables.
    private String name;
    private World world;
    private TiledMap map;
    private Array<Body> actorBodies;
    private OrthogonalTiledMapRenderer tmr;
    private Player player;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera b2dCam;
    private BoundedOrthoCamera cam;
    private OrthographicCamera hudCam;
    private SpriteBatch sb;
    private MyContactListener contactListener;
    private boolean debugMode;
    private PlayHud hud;

    public Level(String name, TiledMap tMap, World w, Player p, PlayHud h, MyContactListener contactListener) {
        this.map = tMap;
        this.name = name;
        this.world = w;
        this.player = p;
        this.hud = h;
        this.contactListener = contactListener;
    }

    /* Passes level all required objects to begin rendering. */
    public void prepare(Box2DDebugRenderer bdr, OrthographicCamera bc, BoundedOrthoCamera c, OrthographicCamera hudC,
            SpriteBatch batch, boolean debug) {

        tmr = new OrthogonalTiledMapRenderer(map);
        debugMode = debug;
        b2dr = bdr;
        b2dCam = bc;
        sb = batch;
        actorBodies = new Array<Body>();
        cam = c;
        hudCam = hudC;
        world.setContactListener(contactListener);

        int mapWidth = map.getProperties().get("width", Integer.class);
        int mapHeight = map.getProperties().get("height", Integer.class);
        int tileWidth = map.getProperties().get("tilewidth", Integer.class);
        int tileHeight = map.getProperties().get("tileheight", Integer.class);
        cam.setBounds(0, 0, mapWidth * tileWidth, mapHeight * tileHeight);
    }

    public void update(float dt) {
        world.step(dt, B2DVars.VEL_INTEGRATIONS, B2DVars.POS_INTEGRATIONS);

        world.getBodies(actorBodies);
        for (Body b : actorBodies) {
            Object bodyData = b.getUserData();
            if (bodyData instanceof B2DSprite) {
                B2DSprite sprite = (B2DSprite) bodyData;
                if (sprite != null) {
                    if (sprite.readyForDisposal) {
                        sprite.dispose(world);
                    } else if (sprite.readyForHiding) {
                        sprite.hide();
                    } else if (sprite.readyForShowing) {
                        sprite.show();
                    }
                }
            }
        }
    }

    public void render(float totalTime) {

        // Move the cameras.
        Vector2 pos = player.getPosition();
        cam.moveTo(pos.x * B2DVars.PPM, pos.y * B2DVars.PPM);
        b2dCam.position.set(cam.position.x / B2DVars.PPM, cam.position.y / B2DVars.PPM, 0);
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
            if (bodyData instanceof B2DSprite) {
                B2DSprite a = (B2DSprite) bodyData;
                if (a != null)
                    a.render(sb, totalTime);
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

    public void dispose() {
        world.dispose();
        map.dispose();
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
    }
}
