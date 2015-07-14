package com.ilkertatar.GameOf2048;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ilkertatar.GameOf2048.controller.ITileFunctions;
import com.ilkertatar.GameOf2048.domain.Tile;
/**
 * @author ilker Tatar
 * 
 */
@RunWith(EasyMockRunner.class)
public class TileFunctionsImplTester {

	private static Tile[] testTiles;
	private static Tile[] testTiles2;
	// @TestSubject annotation is used to identify class which is going to use
	// the mock object
	@TestSubject
	TileFunctions tileFunctions = new TileFunctions();

	// @Mock annotation is used to create the mock object to be injected
	@Mock
	ITileFunctions iTileFunctions;

	@Test
	public void test() {
		testTiles2 = new Tile[4 * 4];
		testTiles = new Tile[4 * 4];
		for (int i = 0; i < 16; i++) {
			testTiles[i] = new Tile();
			testTiles2[i] = new Tile();
		}
		EasyMock.expect(iTileFunctions.left(testTiles)).andReturn(testTiles2);

		// activate the mock
		EasyMock.replay(iTileFunctions);

		// test the add functionality
		Assert.assertArrayEquals(tileFunctions.left(testTiles), testTiles2);
	}

}
