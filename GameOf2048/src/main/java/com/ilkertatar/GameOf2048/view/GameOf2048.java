package com.ilkertatar.GameOf2048.view;

import java.io.IOException;
import java.io.InputStreamReader;

import com.ilkertatar.GameOf2048.controller.TileFunctionsImpl;
import com.ilkertatar.GameOf2048.domain.Tile;
import com.ilkertatar.GameOf2048.enums.Direction;
/**
 * @author ilker Tatar
 * 
 */
public class GameOf2048 {

	private static Tile[] myTiles;
	
	public static int printSizeMenu() {
		System.out.println("Choose board size, minumum 4 maximum 7 :");
		char input = 0;
		try {
			InputStreamReader reader = new InputStreamReader(System.in, "UTF8");
			input = (char) reader.read();
			//int boardSize=Integer.parseInt(String.valueOf(input));
			while (Character.getNumericValue(input) > 7 || Character.getNumericValue(input) < 4) {
				System.out
						.println("Invalid key! Press enter to submit your choice. min :4 max :7");
				reader = new InputStreamReader(System.in, "UTF8");
				input = (char) reader.read();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Character.getNumericValue(input);
	}
	
	public static void printBoard(Tile[] ownTiles,int size){
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				System.out.print((ownTiles[x + y * size] == null ? 0 : ownTiles[x + y
						* size].getValue())
						+ "\t");
			}			
			System.out.println("\n");			
		}		
		printLine(size);
	}
	
	public static void printLine(int size){
		for (int i = 0; i < size; i++) {
			System.out.print("------");			
		}
		System.out.print("------\n");
	}
	
	public static void printGameOver(int score){
		System.out.println("!!!!!!*******GAME OVER*******!!!!!!");
		System.out.println("!!!!!!*******YOUR SCORE : "+ score+"*******!!!!!!");
	}

	public static void main(String[] args) {
		TileFunctionsImpl app = new TileFunctionsImpl();
		int boardSize = printSizeMenu();
		app.setSize(boardSize);
		app.setScore(0);
		app.setOurTarget(2048);
		app.setWinner(false);
		app.setLooser(false);
		myTiles = new Tile[app.getSize() * app.getSize()];
		for (int i = 0; i < myTiles.length; i++) {
			myTiles[i] = new Tile();
		}

		app.addTile(myTiles);
		app.addTile(myTiles);
		printBoard(myTiles,boardSize);
		System.out
				.println("Use W(w) for UP, D(d) for RIGHT, S(s) for DOWN, A(a) for LEFT and Q(q) for exit game. Press enter to submit your choice. \n");
		if (!app.canMove(myTiles)) {
			app.setLooser(true);
			printGameOver(app.getScore());
		}
		try {
			InputStreamReader unbuffered = new InputStreamReader(System.in,
					"UTF8");
			char inputChar;
			while (!app.isWinner() && !app.isLooser()) {
				inputChar = (char) unbuffered.read();
				inputChar = Character.toLowerCase(inputChar);
				if (inputChar == '\n' || inputChar == '\r') {
					continue;
				} else if (inputChar == Direction.UP.getCode().charAt(0)) {
					myTiles = app.up(myTiles);
				} else if (inputChar == Direction.LEFT.getCode().charAt(0)) {
					myTiles = app.left(myTiles);
				} else if (inputChar == Direction.DOWN.getCode().charAt(0)) {
					myTiles = app.down(myTiles);
				} else if (inputChar == Direction.RIGHT.getCode().charAt(0)) {
					myTiles = app.right(myTiles);
				} else if (inputChar == 'q') {
					System.out.println("Game ended, user quit.");
					break;
				} else {
					System.out
							.println("Invalid key! Use W(w) for UP, D(d) for RIGHT, S(s) for DOWN and A(a) for LEFT. Type a to play automatically and Q(q) to exit. Press enter to submit your choice.");
					continue;
				}
				if (!app.isWinner() && !app.canMove(myTiles)) {
					app.setLooser(true);
					printGameOver(app.getScore());
				}
				printBoard(myTiles,boardSize);
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

}
