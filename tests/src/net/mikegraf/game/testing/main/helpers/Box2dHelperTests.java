package net.mikegraf.game.testing.main.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

import net.mikegraf.game.main.helpers.Box2dHelper;

public class Box2dHelperTests {

	@Test
	public void toRenderCoordsOriginReturnsOrigin() {
		Vector2 origin = new Vector2(.16f, .16f);
		Vector2 vectorToSet = new Vector2();
		float width = 32;
		float height = 32;
		
		Box2dHelper.toRenderCoords(vectorToSet, origin, width, height);
		
		assertEquals(0, vectorToSet.x, .0001f);
		assertEquals(0, vectorToSet.y, .0001f);
	}
	
	@Test
	public void toRenderCoordsSimple() {
		Vector2 vector = new Vector2(10, 20);
		Vector2 vectorToSet = new Vector2();
		float width = 32;
		float height = 32;
		
		Box2dHelper.toRenderCoords(vectorToSet, vector, width, height);
		
		assertEquals(984, vectorToSet.x, .0001f);
		assertEquals(1984, vectorToSet.y, .0001f);
	}
	
	@Test
	public void toRenderCoordsSimple2() {
		Vector2 vector = new Vector2(20, 10);
		Vector2 vectorToSet = new Vector2();
		float width = 32;
		float height = 32;
		
		Box2dHelper.toRenderCoords(vectorToSet, vector, width, height);
		
		assertEquals(1984, vectorToSet.x, .0001f);
		assertEquals(984, vectorToSet.y, .0001f);
	}
	
	@Test
	public void toRenderCoordsShortWidth() {
		Vector2 vector = new Vector2(10, 20);
		Vector2 vectorToSet = new Vector2();
		float width = 16;
		float height = 32;
		
		Box2dHelper.toRenderCoords(vectorToSet, vector, width, height);
		
		assertEquals(992, vectorToSet.x, .0001f);
		assertEquals(1984, vectorToSet.y, .0001f);
	}
	
	@Test
	public void toRenderCoordsShortHeight() {
		Vector2 vector = new Vector2(10, 20);
		Vector2 vectorToSet = new Vector2();
		float width = 32;
		float height = 16;
		
		Box2dHelper.toRenderCoords(vectorToSet, vector, width, height);
		
		assertEquals(984, vectorToSet.x, .0001f);
		assertEquals(1992, vectorToSet.y, .0001f);
	}
	
	@Test
	public void toBox2dCoordsOriginReturnsOrigin() {
		Vector2 origin = new Vector2(0, 0);
		Vector2 vectorToSet = new Vector2();
		float width = 32;
		float height = 32;
		
		Box2dHelper.toBox2dCoords(vectorToSet, origin, width, height);
		
		assertEquals(.16, vectorToSet.x, .0001f);
		assertEquals(.16, vectorToSet.y, .0001f);
	}
	
	@Test
	public void toBox2dCoordsCoordsSimple() {
		Vector2 vector = new Vector2(1000, 2000);
		Vector2 vectorToSet = new Vector2();
		float width = 32;
		float height = 32;
		
		Box2dHelper.toBox2dCoords(vectorToSet, vector, width, height);
		
		assertEquals(10.16, vectorToSet.x, .0001f);
		assertEquals(20.16, vectorToSet.y, .0001f);
	}
	
	@Test
	public void toBox2dCoordsCoordsSimple2() {
		Vector2 vector = new Vector2(2000, 1000);
		Vector2 vectorToSet = new Vector2();
		float width = 32;
		float height = 32;
		
		Box2dHelper.toBox2dCoords(vectorToSet, vector, width, height);
		
		assertEquals(20.16, vectorToSet.x, .0001f);
		assertEquals(10.16, vectorToSet.y, .0001f);
	}
	
	@Test
	public void toBox2dCoordsCoordsShortWidth() {
		Vector2 vector = new Vector2(1000, 2000);
		Vector2 vectorToSet = new Vector2();
		float width = 16;
		float height = 32;
		
		Box2dHelper.toBox2dCoords(vectorToSet, vector, width, height);
		
		assertEquals(10.08, vectorToSet.x, .0001f);
		assertEquals(20.16, vectorToSet.y, .0001f);
	}
	
	@Test
	public void toBox2dCoordsCoordsShortHeight() {
		Vector2 vector = new Vector2(1000, 2000);
		Vector2 vectorToSet = new Vector2();
		float width = 32;
		float height = 16;
		
		Box2dHelper.toBox2dCoords(vectorToSet, vector, width, height);
		
		assertEquals(10.16, vectorToSet.x, .0001f);
		assertEquals(20.08, vectorToSet.y, .0001f);
	}
}
