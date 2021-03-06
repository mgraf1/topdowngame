package net.mikegraf.game.main.constants;

public final class TiledConstants {

    public final static String BADGUY_TYPE_BADGUY = "badguy";
    public final static String BADGUY_WAYPOINT = "waypoint";
    public final static String BADGUY_WAYPOINT_BADGUY_ID = "badGuyId";

    public final static String ENTITY_ID = "id";
    public final static String ENTITY_TYPE = "type";
    public final static String ENTITY_TEXTURE = "texture";
    public final static String ENTITY_TYPE_DOOR = "door";
    public final static String ENTITY_TYPE_SWITCH = "switch";
    public final static String ENTITY_DOOR_PROP_KEY = "key";
    public final static String ENTITY_SWITCH_PROP_DOOR = "door";
    public static final String ENTITY_HEIGHT = "height";
    public static final String ENTITY_WIDTH = "width";
    public static final String ENTITY_X = "x";
    public static final String ENTITY_Y = "y";

    public final static String ID_PLAYER = "player";

    public final static String LAYER_BADGUYS = "badguys";
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
