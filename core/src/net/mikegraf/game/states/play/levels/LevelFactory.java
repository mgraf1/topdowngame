package net.mikegraf.game.states.play.levels;

import java.util.HashMap;

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
import com.badlogic.gdx.physics.box2d.World;
import com.google.inject.Inject;

import net.mikegraf.game.main.constants.B2dConstants;
import net.mikegraf.game.main.constants.TiledConstants;
import net.mikegraf.game.parsers.models.LevelData;
import net.mikegraf.game.states.play.contact.MyContactListener;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.GameEntityBuilding;
import net.mikegraf.game.states.play.entities.GameEntityFactory;
import net.mikegraf.game.states.play.entities.player.Player;

public class LevelFactory {

    public static final String INVENTORY_TEXTURE_PATH = "textures/hud/inventory_slot.png";
    public static final Vector2 GRAVITY_VECTOR = new Vector2(0f, 0f);

    private LevelData[][] levelData;
    private GameEntityBuilding gameEntityBuilding;

    @Inject
    public LevelFactory(LevelData[][] data, GameEntityBuilding gameEntityBuilding) {
        this.levelData = data;
        this.gameEntityBuilding = gameEntityBuilding;
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
        World world = new World(GRAVITY_VECTOR, true);
        LevelData data = levelData[y][x];
        String name = data.getName();

        // TiledMapRenderer for the level.
        TiledMap map = new TmxMapLoader().load(data.getFileName());

        // Place all game entities.
        HashMap<Integer, GameEntity> idToEntityMap = new HashMap<Integer, GameEntity>();
        MapLayers layers = map.getLayers();
        for (MapLayer layer : layers) {
            if (!(layer instanceof TiledMapTileLayer)) {
                GameEntityFactory factory = gameEntityBuilding.getGameEntityFactory(layer);
                for (MapObject mo : layer.getObjects()) {
                    GameEntity entity = factory.createGameEntity(world, mo);
                    int id = entity.getId();
                    idToEntityMap.put(id, entity);
                }
                factory.finalizeEntities();
            }
        }

        // Create bodies for map structures.
        MapProperties mapProps = map.getProperties();
        float tileWidth = mapProps.get(TiledConstants.MAP_TILE_WIDTH, Integer.class) / B2dConstants.PPM;
        float tileHeight = mapProps.get(TiledConstants.MAP_TILE_HEIGHT, Integer.class) / B2dConstants.PPM;
        int mapHeight = mapProps.get(TiledConstants.MAP_HEIGHT, Integer.class);
        int mapWidth = mapProps.get(TiledConstants.MAP_WIDTH, Integer.class);
        BodyDef bDef = new BodyDef();
        FixtureDef fDef = new FixtureDef();

        // Create walls within the map.
        TiledMapTileLayer wallLayer = (TiledMapTileLayer) layers.get(TiledConstants.LAYER_WALL);
        placeWalls(wallLayer, tileWidth, tileHeight, bDef, fDef, world);

        // Create border around the level.
        placeBorder(mapWidth, mapHeight, tileWidth, tileHeight, world, bDef, fDef);

        // Create HUD.
        Player player = null;
        for (int id : idToEntityMap.keySet()) {
        	GameEntity entity = idToEntityMap.get(id);
        	if (entity instanceof Player) {
        		player = (Player)entity;
        	}
        }
        PlayHud hud = createHud(player);
        
        Level level = new Level(name, player, map, world, hud, idToEntityMap);        
        MyContactListener contactListener = new MyContactListener(level);
        world.setContactListener(contactListener);

        return level;
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
