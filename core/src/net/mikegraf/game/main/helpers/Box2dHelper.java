package net.mikegraf.game.main.helpers;

import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.main.constants.B2dConstants;

public class Box2dHelper {

    public static void toRenderCoords(Vector2 vectorToSet, Vector2 box2dCoords, float width, float height) {
        vectorToSet.x = box2dCoords.x * B2dConstants.PPM - width / 2;
        vectorToSet.y = box2dCoords.y * B2dConstants.PPM - height / 2;
    }

    public static void toBox2dCoords(Vector2 vectorToSet, float renderX, float renderY) {
        vectorToSet.x = renderX / B2dConstants.PPM;
        vectorToSet.y = renderY / B2dConstants.PPM;
    }
}
