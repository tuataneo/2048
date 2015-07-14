package com.ilkertatar.GameOf2048.controller;

import java.util.List;

import com.ilkertatar.GameOf2048.domain.Tile;

/**
 * @author ilker Tatar
 * 
 */
public interface ITileFunctions {
	/*
	 * The provision also one-dimensional array from a two-dimensional array
	 */
	Tile tileAt(Tile[] ownTiles, int x, int y);

	/*
	 * get line array by index
	 */
	Tile[] getLine(Tile[] ownTiles, int index);

	/*
	 * Moves a tile from one given position to another
	 */
	Tile[] moveLine(Tile[] oldLine);

	List<Tile> ensureSize(List<Tile> l, int s);

	/*
	 * return available spaces(0)
	 */
	List<Tile> availableSpace(Tile[] ownTiles);

	/*
	 * merging the two tiles that before and last.
	 */
	Tile[] mergeLine(Tile[] oldLine);

	void slide(Tile[] ownTiles);

	Tile[] setLine(int index, Tile[] re, Tile[] ownTiles);

	/*
	 * Compare lines between before and after direction
	 */
	boolean compare(Tile[] line1, Tile[] line2);

	/*
	 * Add number in Array(Game Board)
	 */
	void addTile(Tile[] ownTiles);

	/*
	 * Array is rotated by 90 degrees
	 */
	Tile[] rotate(Tile[] ownTiles);

	/*
	 * Left Direction
	 */
	Tile[] left(Tile[] ownTiles);

	/*
	 * Right Direction
	 */
	Tile[] right(Tile[] ownTiles);

	/*
	 * Up Direction
	 */
	Tile[] up(Tile[] ownTiles);

	/*
	 * Down Direction
	 */
	Tile[] down(Tile[] ownTiles);

	/*
	 * return Array is full or not
	 */
	boolean isFull(Tile[] ownTiles);

	/*
	 * return is direction possible
	 */
	boolean canMove(Tile[] ownTiles);

}
