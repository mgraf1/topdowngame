package net.mikegraf.game.states.play.levels;

/**
 * Class contains constants for the Box2D physics engine.
 * 
 * @author Graf
 *
 */
public final class B2DVars {

    /**
     * Number of pixels per meter.
     */
    public static final float PPM = 100;

    public static final int VEL_INTEGRATIONS = 6;

    public static final int POS_INTEGRATIONS = 2;

    public static final String TRIGGER_ID = "trigger";

    /* This class should never be instantiated. */
    private B2DVars() {
        throw new AssertionError("Do not instantiate this class!");
    }
}
