package net.mikegraf.game.main.constants;

public final class TiledConstants {

    public final static String ENTITY_ID = "id";
    public final static String ENTITY_TYPE = "type";
    public final static String ENTITY_BODYTYPE = "bodytype";
    public final static String ENTITY_TYPE_DOOR = "door";
    public final static String ENTITY_TYPE_SWITCH = "switch";
    public final static String ENTITY_DOOR_PROP_KEY = "key";
    public final static String ENTITY_SWITCH_PROP_DOOR = "door";

    public final static String ID_PLAYER = "player";

    public final static String LAYER_ITEM = "items";
    public final static String LAYER_OBJECT = "objects";
    public static final String LAYER_PLAYER = "player";
    public final static String LAYER_TRIGGER = "triggers";
    public static final String LAYER_WALL = "wall";

    public static final String MAP_HEIGHT = "height";
    public static final String MAP_WIDTH = "width";
    public static final String MAP_TILE_HEIGHT = "tileheight";
    public static final String MAP_TILE_WIDTH = "tilewidth";

    /* This class should never be instantiated. */
    private TiledConstants() {
        throw new AssertionError("Do not instantiate this class!");
    }
}
