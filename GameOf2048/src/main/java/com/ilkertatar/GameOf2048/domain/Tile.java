package com.ilkertatar.GameOf2048.domain;
/**
 * @author ilker Tatar
 * 
 */
public class Tile {
	private int value;
	
	public Tile() {
	      this(0);
	    }

	public Tile(int num) {
	      value = num;
	    }

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean isEmpty() {
		return value == 0;
	}
}
