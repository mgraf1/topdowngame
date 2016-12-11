package net.mikegraf.game.main;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class BoundedOrthoCamera extends OrthographicCamera {

    private float minX;
    private float maxX;
    private float minY;
    private float maxY;

    public BoundedOrthoCamera(float vWidth, float vHeight) {
        super();
        this.setToOrtho(false, vWidth, vHeight);
    }

    /**
     * Defines the boundaries for the camera.
     * 
     * @param xMin
     *            The lowest x location the viewport can be.
     * @param yMin
     *            The lowest y location the viewport can be.
     * @param xMax
     *            The greatest x location the viewport can be.
     * @param yMax
     *            The greatest y location the viewport can be.
     */
    public void setBounds(float xMin, float yMin, float xMax, float yMax) {

        if (xMin > xMax) {
            throw new IllegalArgumentException("Minimum X cannot be greater than maximum X.");
        }

        if (yMin > yMax) {
            throw new IllegalArgumentException("Minimum Y cannot be greater than maximum Y.");
        }

        if (xMin < 0) {
            throw new IllegalArgumentException("Minimum X cannot be less than 0.");
        }

        if (yMin < 0) {
            throw new IllegalArgumentException("Minimum Y cannot be less than 0.");
        }

        minX = xMin;
        maxX = xMax;
        minY = yMin;
        maxY = yMax;
    }

    /**
     * Moves camera to desired position up to the point where the viewport meets
     * the camera's bounds.
     * 
     * @param x
     *            The x location to move to.
     * @param y
     *            The y location to move to.
     */
    public void moveTo(float x, float y) {

        if (x < minX + (viewportWidth / 2)) {
            x = minX + (viewportWidth / 2);
        } else if (x > maxX - (viewportWidth / 2)) {
            x = maxX - (viewportWidth / 2);
        }

        if (y < minY + (viewportHeight / 2)) {
            y = minY + (viewportHeight / 2);
        } else if (y > maxY - (viewportHeight / 2)) {
            y = maxY - (viewportHeight / 2);
        }

        this.position.set(x, y, 0);
    }
}
