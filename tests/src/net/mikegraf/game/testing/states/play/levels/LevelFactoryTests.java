package net.mikegraf.game.testing.states.play.levels;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;

import net.mikegraf.game.exceptions.ConfigFormatException;
import net.mikegraf.game.parsers.LevelData;
import net.mikegraf.game.parsers.WorldParser;
import net.mikegraf.game.states.play.levels.LevelFactory;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class LevelFactoryTests {

    @Test
    public void createLevelBadX() {
        LevelData[][] data;
        WorldParser wp = new WorldParser();
        try {
            data = wp.parseFile("worldDef.xml");
            LevelFactory lf = new LevelFactory(data, null, null, null, null, null);
            assertNotNull(lf.buildLevel(-1, 0));
        } catch (ConfigFormatException e) {
            fail();
        } catch (IllegalArgumentException iae) {
            return;
        } catch (IOException e) {
            fail();
        }
        fail();
    }

    @Test
    public void createLevelBadY() {
        LevelData[][] data;
        WorldParser wp = new WorldParser();
        try {
            data = wp.parseFile("worldDef.xml");
            LevelFactory lf = new LevelFactory(data, null, null, null, null, null);
            assertNotNull(lf.buildLevel(0, -1));
        } catch (ConfigFormatException e) {
            fail();
        } catch (IllegalArgumentException iae) {
            return;
        } catch (IOException e) {
            fail();
        }
        fail();
    }

    @Test
    public void createLevelBadX2() {
        LevelData[][] data;
        WorldParser wp = new WorldParser();
        try {
            data = wp.parseFile("worldDef.xml");
            LevelFactory lf = new LevelFactory(data, null, null, null, null, null);
            assertNotNull(lf.buildLevel(2, 0));
        } catch (ConfigFormatException e) {
            fail();
        } catch (IllegalArgumentException iae) {
            return;
        } catch (IOException e) {
            fail();
        }
        fail();
    }

    @Test
    public void createLevelBadY2() {
        LevelData[][] data;
        WorldParser wp = new WorldParser();
        try {
            data = wp.parseFile("worldDef.xml");
            LevelFactory lf = new LevelFactory(data, null, null, null, null, null);
            assertNotNull(lf.buildLevel(0, 2));
        } catch (ConfigFormatException e) {
            fail();
        } catch (IllegalArgumentException iae) {
            return;
        } catch (IOException e) {
            fail();
        }
        fail();
    }
}
