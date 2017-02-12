package net.mikegraf.game.states.play.levels;

import java.util.HashMap;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import net.mikegraf.game.menus.FontFactory;
import net.mikegraf.game.parsers.models.LevelData;
import net.mikegraf.game.states.play.contact.MyContactListener;
import net.mikegraf.game.states.play.entities.GameEntity;
import net.mikegraf.game.states.play.entities.GameEntityBuilding;
import net.mikegraf.game.states.play.entities.GameEntityFactory;
import net.mikegraf.game.states.play.entities.player.Player;
import net.mikegraf.game.states.play.entities.view.AnimationIndex;
import net.mikegraf.game.states.play.hud.HudAssetHelper;
import net.mikegraf.game.states.play.hud.PlayHud;

public class LevelFactory {

    public static final String INVENTORY_TEXTURE_PATH = "textures/hud/inventory_slot.png";
    public static final Vector2 GRAVITY_VECTOR = new Vector2(0f, 0f);

    private LevelData[][] levelData;
    private GameEntityBuilding gameEntityBuilding;
    private FontFactory fontFactory;
    private LevelAssetLoader levelAssetLoader;
    private HudAssetHelper hudHelper;

    @Inject
    public LevelFactory(LevelData[][] data, GameEntityBuilding gameEntityBuilding, FontFactory fontFactory,
            LevelAssetLoader levelAssetLoader, HudAssetHelper hudHelper) {
        this.levelData = data;
        this.gameEntityBuilding = gameEntityBuilding;
        this.fontFactory = fontFactory;
        this.levelAssetLoader = levelAssetLoader;
        this.hudHelper = hudHelper;
    }

    // Create the level at the given coordinates.
    public Level buildLevel(int x, int y) throws IllegalArgumentException {

        if (!levelArgsAreValid(x, y)) {
            String errString = String.format("Cannot build level at invalid coods" + "x: %s, y: %s", x, y);
            throw new IllegalArgumentException(errString);
        }

        AssetManager assetManager = new AssetManager();

        World world = new World(GRAVITY_VECTOR, true);
        LevelData data = levelData[y][x];
        String name = data.getName();

        TiledMap map = new TmxMapLoader().load(data.getFileName());
        MapLayers layers = map.getLayers();
        MapProperties mapProps = map.getProperties();

        levelAssetLoader.loadAssets(layers, assetManager);
        hudHelper.loadAssets(assetManager);
        assetManager.finishLoading();
        HashMap<Integer, GameEntity> idToEntityMap = placeGameEntities(layers, world, assetManager);

        placeMapStructures(layers, mapProps, world);

        Player player = getPlayerReference(idToEntityMap);
        PlayHud hud = createHud(player, assetManager);

        Level level = new Level(name, player, map, world, hud, idToEntityMap, assetManager);
        MyContactListener contactListener = new MyContactListener(level);
        world.setContactListener(contactListener);

        return level;
    }

    private Player getPlayerReference(HashMap<Integer, GameEntity> idToEntityMap) {
        for (int id : idToEntityMap.keySet()) {
            GameEntity entity = idToEntityMap.get(id);
            if (entity instanceof Player) {
                return (Player) entity;
            }
        }
        throw new IllegalStateException("No player found");
    }

    private boolean levelArgsAreValid(int x, int y) {
        if (x < 0 || y < 0 || x > levelData[y].length - 1 || y > levelData.length - 1) {
            return false;
        } else {
            return true;
        }
    }

    private void placeMapStructures(MapLayers layers, MapProperties mapProps, World world) {
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
    }

    private HashMap<Integer, GameEntity> placeGameEntities(MapLayers layers, World world, AssetManager assetManager) {
        HashMap<Integer, GameEntity> idToEntityMap = new HashMap<Integer, GameEntity>();
        for (MapLayer layer : layers) {
            if (!(layer instanceof TiledMapTileLayer)) {
                GameEntityFactory factory = gameEntityBuilding.getGameEntityFactory(layer);
                for (MapObject mo : layer.getObjects()) {
                    GameEntity entity = factory.createGameEntity(world, mo, assetManager);
                    int id = entity.getId();
                    idToEntityMap.put(id, entity);
                }
                factory.finalizeEntities();
            }
        }
        return idToEntityMap;
    }

    // Create the PlayState's HUD.
    private PlayHud createHud(Player player, AssetManager assetManager) {
        TextureRegion inventoryTex = hudHelper.getTextureRegion(HudAssetHelper.INVENTORY_SLOT_TYPE, assetManager);
        BitmapFont hudFont = fontFactory.createFont(FontFactory.NOVEMBER, assetManager);
        AnimationIndex healthAnimation = hudHelper.getAnimationIndex(HudAssetHelper.HEALTH_TYPE, assetManager);
        return new PlayHud(player, hudFont, inventoryTex, healthAnimation);
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
