package com.ilkertatar.GameOf2048;

import com.ilkertatar.GameOf2048.controller.ITileFunctions;
import com.ilkertatar.GameOf2048.domain.Tile;
/**
 * @author ilker Tatar
 * 
 */
public class TileFunctions {
	private ITileFunctions tileFunctions;

	public ITileFunctions getTileFunctions() {
		return tileFunctions;
	}

	public void setTileFunctions(ITileFunctions tileFunctions) {
		this.tileFunctions = tileFunctions;
	}
	
	Tile[] left(Tile[] ownTiles){
		return tileFunctions.left(ownTiles);		
	}
}
