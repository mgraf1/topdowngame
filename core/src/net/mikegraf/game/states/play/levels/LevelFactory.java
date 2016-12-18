package net.mikegraf.game.states.play.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

import net.mikegraf.game.parsers.models.LevelData;
import net.mikegraf.game.states.play.actors.B2DSprite;
import net.mikegraf.game.states.play.actors.Item;
import net.mikegraf.game.states.play.actors.Player;
import net.mikegraf.game.states.play.actors.ShapeFactory;
import net.mikegraf.game.states.play.actors.SpriteFactory;
import net.mikegraf.game.states.play.actors.gameobjects.GameObjectFactory;
import net.mikegraf.game.states.play.contact.MyContactListener;
import net.mikegraf.game.states.play.triggers.TriggerFactory;

public class LevelFactory {

    // Constants.
    public static final String ACTOR_LAYER = "actors";
    public static final String WALL_LAYER = "wall";
    public static final String TRIGGER_LAYER = "triggers";
    public static final String PLAYER_TYPE = "player";
    public static final String PLAYER_LAYER = "player";
    public static final String ITEM_LAYER = "items";
    public static final String GAME_OBJECT_LAYER = "objects";
    public static final String INVENTORY_TEXTURE_PATH = "textures/hud/inventory_slot.png";

    // Instance variables.
    private LevelData[][] levelData;
    private SpriteFactory spriteFactory;
    private TriggerFactory triggerFactory;
    private GameObjectFactory gameObjectFactory;
    private ShapeFactory shapeFactory;
    private MyContactListener contactListener;

    @Inject
    public LevelFactory(LevelData[][] data, SpriteFactory af, TriggerFactory tf, GameObjectFactory gf, ShapeFactory sf,
            MyContactListener cl) {
        levelData = data;
        spriteFactory = af;
        triggerFactory = tf;
        gameObjectFactory = gf;
        shapeFactory = sf;
        contactListener = cl;
    }

    // Create the level at the given coordinates.
    public Level buildLevel(int x, int y) throws IllegalArgumentException {

        // Validate arguments.
        if (x < 0) {
            throw new IllegalArgumentException("Cannot build level at invalid" + "x: " + x);
        } else if (y < 0) {
            throw new IllegalArgumentException("Cannot build level at invalid" + "y: " + y);
        } else if (y > levelData.length - 1) {
            throw new IllegalArgumentException("Cannot build level at invalid" + "y: " + y);
        } else if (x > levelData[y].length - 1) {
            throw new IllegalArgumentException("Cannot build level at invalid" + "x: " + x);
        }

        // Build the level based on the data.
        World world = new World(new Vector2(0f, 0f), true);
        LevelData data = levelData[y][x];

        // TiledMapRenderer for the level.
        TiledMap map = new TmxMapLoader().load(data.getFileName());
        MapLayers layers = map.getLayers();

        // Create all the actors including the player.
        Player player = placePlayer(layers.get(PLAYER_LAYER), world);
        placeItems(layers.get(ITEM_LAYER), world);
        placeObjects(layers.get(GAME_OBJECT_LAYER), world);

        // Create bodies for map structures.
        MapProperties mapProps = map.getProperties();
        float tileWidth = mapProps.get("tilewidth", Integer.class) / B2DVars.PPM;
        float tileHeight = mapProps.get("tileheight", Integer.class) / B2DVars.PPM;
        int mapHeight = mapProps.get("height", Integer.class);
        int mapWidth = mapProps.get("width", Integer.class);
        BodyDef bDef = new BodyDef();
        FixtureDef fDef = new FixtureDef();

        // Create walls within the map.
        TiledMapTileLayer wallLayer = (TiledMapTileLayer) layers.get(WALL_LAYER);
        placeWalls(wallLayer, tileWidth, tileHeight, bDef, fDef, world);

        // Create border around the level.
        placeBorder(mapWidth, mapHeight, tileWidth, tileHeight, world, bDef, fDef);

        // Create triggers.
        placeTriggers(layers.get(TRIGGER_LAYER), world);

        // Create hud.
        PlayHud hud = createHud(player);

        return new Level(data.getName(), map, world, player, hud, contactListener);
    }

    // Get the triggers defined in the TiledMap and add them to the world.
    private void placeTriggers(MapLayer triggerLayer, World world) {

        for (MapObject mo : triggerLayer.getObjects()) {
            MapProperties triggerProps = mo.getProperties();
            triggerFactory.createTrigger(world, triggerProps);
        }
    }

    // Create the player sprite.
    private Player placePlayer(MapLayer playerLayer, World world) {

        MapObject mapObject = playerLayer.getObjects().get(0);
        B2DSprite sprite = createSpriteFromMapObject(mapObject, world);
        return new Player(sprite);
    }

    // Create the level's items.
    private void placeItems(MapLayer itemLayer, World world) {

        for (MapObject mo : itemLayer.getObjects()) {
            B2DSprite sprite = createSpriteFromMapObject(mo, world);
            new Item(sprite, mo.getProperties().get("type", String.class));
        }
    }

    // Create the level's objects.
    private void placeObjects(MapLayer objectLayer, World world) {

        for (MapObject mo : objectLayer.getObjects()) {
            B2DSprite sprite = createSpriteFromMapObject(mo, world);
            gameObjectFactory.createGameObject(sprite, mo.getProperties());
        }
        gameObjectFactory.finalizeObjects();
    }

    // Helper method to build sprite.
    private B2DSprite createSpriteFromMapObject(MapObject mo, World world) {
        MapProperties actorProps = mo.getProperties();
        Float x = (Float) actorProps.get("x") / B2DVars.PPM;
        Float y = (Float) actorProps.get("y") / B2DVars.PPM;
        String sprite = (String) actorProps.get("sprite");
        Shape shape = shapeFactory.createShape(mo);
        return spriteFactory.createSprite(sprite, shape, world, x.floatValue(), y.floatValue());
    }

    // Create the PlayState's HUD.
    private PlayHud createHud(Player player) {
        Texture texture = new Texture(Gdx.files.internal(INVENTORY_TEXTURE_PATH));
        return new PlayHud(new TextureRegion(texture), player);
    }

    // Create bodies for tiles within the wall layer.
    private void placeWalls(TiledMapTileLayer wallLayer, float tileWidth, float tileHeight, BodyDef bDef,
            FixtureDef fDef, World world) {

        // Create body used for each wall.
        bDef.type = BodyType.StaticBody;
        PolygonShape pShape = new PolygonShape();
        pShape.setAsBox(tileWidth / 2, tileHeight / 2);
        fDef.shape = pShape;

        // Place the defined walls.
        int wallLayerH = wallLayer.getHeight();
        int wallLayerW = wallLayer.getWidth();
        for (int j = 0; j < wallLayerH; j++) {
            for (int i = 0; i < wallLayerW; i++) {

                Cell cell = wallLayer.getCell(i, j);
                if (cell == null || cell.getTile() == null) {
                    continue;
                }

                // Adjust for the fact that body positions are their center.
                bDef.position.set((i + 0.5f) * tileWidth, (j + 0.5f) * tileHeight);

                Body body = world.createBody(bDef);
                body.createFixture(fDef);
            }
        }
    }

    // Place border around map to prevent player from leaving.
    private void placeBorder(int mapWidth, int mapHeight, float tileWidth, float tileHeight, World world, BodyDef bDef,
            FixtureDef fDef) {

        EdgeShape eShape = new EdgeShape();
        fDef.shape = eShape;

        // Left wall.
        eShape.set(0, 0, 0, mapHeight * tileHeight);
        bDef.position.set(0, 0);
        Body leftWall = world.createBody(bDef);
        leftWall.createFixture(fDef);

        // Bottom wall.
        eShape.set(0, 0, mapWidth * tileWidth, 0);
        bDef.position.set(0, 0);
        Body bottomWall = world.createBody(bDef);
        bottomWall.createFixture(fDef);

        // Right wall.
        eShape.set(mapWidth * tileWidth, 0, mapWidth * tileWidth, mapHeight * tileHeight);
        bDef.position.set(0, 0);
        Body rightWall = world.createBody(bDef);
        rightWall.createFixture(fDef);

        // Top wall.
        eShape.set(0, mapHeight * tileHeight, mapWidth * tileWidth, mapHeight * tileHeight);
        bDef.position.set(0, 0);
        Body topWall = world.createBody(bDef);
        topWall.createFixture(fDef);
    }
}
