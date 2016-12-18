package net.mikegraf.game.testing.parsers;
import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;

import net.mikegraf.game.exceptions.ConfigFormatException;
import net.mikegraf.game.parsers.WorldParser;
import net.mikegraf.game.parsers.models.LevelData;
import net.mikegraf.game.testing.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class WorldCreationTests {

	@Test
	public void parseWorldSimple() {
		WorldParser wp = new WorldParser();
		LevelData[][] data = null;
		try {
			data = wp.parseFile("worldDef.xml");
		} catch (ConfigFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			fail();
		}
		assertNotNull(data);
	}

	@Test
	public void parseWorldAllLevelsCreated() {
		WorldParser wp = new WorldParser();
		LevelData[][] data = null;
		try {
			data = wp.parseFile("worldDef.xml");
		} catch (ConfigFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			fail();
		}
		
		for (int y = 0 ; y < data.length; y++) {
			for (int x = 0; x < data[0].length; x++) {
				if (data[y][x] == null) {
					fail();
				}
			}
		}
		assertNotNull(data);
	}
	
	@Test
	public void parseWorldNotWideEnough() {
		WorldParser wp = new WorldParser();
		try{
			wp.parseFile("worldDef2.xml");
		} catch (ConfigFormatException ex) {
			return;
		} catch (IOException e) {
			fail();
		}
		
		fail();
	}
	
	@Test
	public void parseWorldNotTallEnough() {
		WorldParser wp = new WorldParser();
		try{
			wp.parseFile("worldDef3.xml");
		} catch (ConfigFormatException ex) {
			return;
		} catch (IOException e) {
			fail();
		}
		
		fail();
	}
	
	@Test
	public void parseWorldNotCompletelyPopulated() {
		WorldParser wp = new WorldParser();
		try{
			wp.parseFile("worldDef4.xml");
		} catch (ConfigFormatException ex) {
			return;
		} catch (IOException e) {
			fail();
		}
		
		fail();
	}
	
	@Test
	public void parseWorldTwoLevelsSameCoord() {
		WorldParser wp = new WorldParser();
		try{
			wp.parseFile("worldDef5.xml");
		} catch (ConfigFormatException ex) {
			return;
		} catch (IOException e) {
			fail();
		}
		
		fail();
	}
}
