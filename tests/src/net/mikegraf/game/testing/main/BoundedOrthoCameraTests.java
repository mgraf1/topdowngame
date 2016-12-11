package net.mikegraf.game.testing.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;

import net.mikegraf.game.main.BoundedOrthoCamera;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class BoundedOrthoCameraTests {

    @Test
    public void setBoundsNegativeXMin() {
        BoundedOrthoCamera camera = new BoundedOrthoCamera(480, 240);
        try {
            camera.setBounds(-1, 0, 2, 2);
        } catch (IllegalArgumentException e) {
            return;
        }

        fail();
    }

    @Test
    public void setBoundsNegativeYMin() {
        BoundedOrthoCamera camera = new BoundedOrthoCamera(480, 240);
        try {
            camera.setBounds(0, -1, 2, 2);
        } catch (IllegalArgumentException e) {
            return;
        }

        fail();
    }

    @Test
    public void setBoundsXMinGreaterThanXMax() {
        BoundedOrthoCamera camera = new BoundedOrthoCamera(480, 240);
        try {
            camera.setBounds(3, 0, 2, 2);
        } catch (IllegalArgumentException e) {
            return;
        }

        fail();
    }

    @Test
    public void setBoundsYMinGreaterThanYMax() {
        BoundedOrthoCamera camera = new BoundedOrthoCamera(480, 240);
        try {
            camera.setBounds(0, 3, 2, 2);
        } catch (IllegalArgumentException e) {
            return;
        }

        fail();
    }

    @Test
    public void moveToWithinBoundsX() {
        BoundedOrthoCamera camera = new BoundedOrthoCamera(240, 240);
        camera.setBounds(0, 0, 1000, 1000);

        camera.moveTo(500, 700);

        assertEquals(500, camera.position.x, 0.1);
    }

    @Test
    public void moveToWithinBoundsY() {
        BoundedOrthoCamera camera = new BoundedOrthoCamera(240, 240);
        camera.setBounds(0, 0, 1000, 1000);

        camera.moveTo(500, 700);

        assertEquals(700, camera.position.y, 0.1);
    }

    @Test
    public void moveToOutsideBoundsX() {
        BoundedOrthoCamera camera = new BoundedOrthoCamera(240, 240);
        camera.setBounds(0, 0, 1000, 1000);

        camera.moveTo(950, 700);

        assertEquals(880, camera.position.x, 0.1);
    }

    @Test
    public void moveToOutsideBoundsY() {
        BoundedOrthoCamera camera = new BoundedOrthoCamera(240, 240);
        camera.setBounds(0, 0, 1000, 1000);

        camera.moveTo(600, 950);

        assertEquals(880, camera.position.y, 0.1);
    }

}
