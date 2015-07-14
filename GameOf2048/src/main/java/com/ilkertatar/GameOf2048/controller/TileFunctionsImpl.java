package com.ilkertatar.GameOf2048.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ilkertatar.GameOf2048.domain.Tile;
/**
 * @author ilker Tatar
 * 
 */
public class TileFunctionsImpl implements ITileFunctions {

	private int size;
	private int ourTarget;
	private int score;
	private boolean winner;
	private boolean looser;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOurTarget() {
		return ourTarget;
	}

	public void setOurTarget(int ourTarget) {
		this.ourTarget = ourTarget;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public boolean isLooser() {
		return looser;
	}

	public void setLooser(boolean looser) {
		this.looser = looser;
	}

	public Tile tileAt(Tile[] ownTiles, int x, int y) {
		return ownTiles[x + y * size];
	}

	public Tile[] getLine(Tile[] ownTiles, int index) {
		Tile[] result = new Tile[size];
		for (int i = 0; i < size; i++) {
			result[i] = tileAt(ownTiles, i, index);
		}
		return result;
	}

	public void addTile(Tile[] ownTiles) {
		List<Tile> list = availableSpace(ownTiles);
		if (!availableSpace(ownTiles).isEmpty()) {
			int index = (int) (Math.random() * list.size()) % list.size();
			Tile emptyTime = list.get(index);
			emptyTime.setValue(Math.random() < 0.9 ? 2 : 4);
		}
	}

	public Tile[] rotate(Tile[] ownTiles) {
		Tile[] newTiles = new Tile[size * size];
		int offsetX = size - 1, offsetY = 0;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				int newX = offsetX - (y);
				int newY = (x) + offsetY;
				newTiles[(newX) + (newY) * size] = tileAt(ownTiles, x, y);
			}
		}
		return newTiles;
	}

	public Tile[] moveLine(Tile[] oldLine) {
		LinkedList<Tile> l = new LinkedList<Tile>();
		for (int i = 0; i < size; i++) {
			if (!oldLine[i].isEmpty())
				l.addLast(oldLine[i]);
		}
		if (l.size() == 0) {
			return oldLine;
		} else {
			Tile[] newLine = new Tile[size];
			ensureSize(l, size);
			for (int i = 0; i < size; i++) {
				newLine[i] = l.removeFirst();
			}
			return newLine;
		}
	}

	public List<Tile> ensureSize(List<Tile> l, int s) {
		while (l.size() != s) {
			l.add(new Tile());
		}
		return l;
	}

	public List<Tile> availableSpace(Tile[] ownTiles) {
		final List<Tile> list = new ArrayList<Tile>(size * size);
		for (Tile t : ownTiles) {
			if (t.isEmpty()) {
				list.add(t);
			}
		}
		return list;
	}

	public Tile[] mergeLine(Tile[] oldLine) {
		LinkedList<Tile> list = new LinkedList<Tile>();
		for (int i = 0; i < size && !oldLine[i].isEmpty(); i++) {
			int num = oldLine[i].getValue();
			if (i < size - 1
					&& oldLine[i].getValue() == oldLine[i + 1].getValue()) {
				num *= 2;
				score += num;
				winner = false;
				if (num == ourTarget) {
					winner = true;
				}
				i++;
			}
			list.add(new Tile(num));
		}
		if (list.size() == 0) {
			return oldLine;
		} else {
			ensureSize(list, size);
			return list.toArray(new Tile[size]);
		}
	}

	public void slide(Tile[] ownTiles) {
		boolean needAddTile = false;
		for (int i = 0; i < size; i++) {
			Tile[] line = getLine(ownTiles, i);
			Tile[] merged = mergeLine(moveLine(line));
			ownTiles = setLine(i, merged, ownTiles);
			if (!needAddTile && !compare(line, merged)) {
				needAddTile = true;
			}
		}

		if (needAddTile) {
			addTile(ownTiles);
		}
	}

	public Tile[] setLine(int index, Tile[] re, Tile[] ownTiles) {
		System.arraycopy(re, 0, ownTiles, index * size, size);
		return ownTiles;
	}

	public boolean compare(Tile[] line1, Tile[] line2) {
		if (line1 == line2) {
			return true;
		} else if (line1.length != line2.length) {
			return false;
		}

		for (int i = 0; i < line1.length; i++) {
			if (line1[i].getValue() != line2[i].getValue()) {
				return false;
			}
		}
		return true;
	}

	public Tile[] left(Tile[] ownTiles) {
		slide(ownTiles);
		return ownTiles;
	}

	public Tile[] right(Tile[] ownTiles) {
		ownTiles = rotate(ownTiles);
		ownTiles = rotate(ownTiles);
		slide(ownTiles);

		ownTiles = rotate(ownTiles);
		ownTiles = rotate(ownTiles);
		return ownTiles;
	}

	public Tile[] up(Tile[] ownTiles) {
		ownTiles = rotate(ownTiles);
		ownTiles = rotate(ownTiles);
		ownTiles = rotate(ownTiles);
		slide(ownTiles);
		ownTiles = rotate(ownTiles);
		return ownTiles;
	}

	public Tile[] down(Tile[] ownTiles) {
		ownTiles = rotate(ownTiles);
		slide(ownTiles);
		ownTiles = rotate(ownTiles);
		ownTiles = rotate(ownTiles);
		ownTiles = rotate(ownTiles);
		return ownTiles;
	}

	public boolean isFull(Tile[] ownTiles) {
		return availableSpace(ownTiles).size() == 0;
	}

	public boolean canMove(Tile[] ownTiles) {
		if (!isFull(ownTiles)) {
			return true;
		}
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				Tile t = tileAt(ownTiles, x, y);
				if ((x < 3 && t.getValue() == tileAt(ownTiles, x + 1, y)
						.getValue())
						|| ((y < 3) && t.getValue() == tileAt(ownTiles, x,
								y + 1).getValue())) {
					return true;
				}
			}
		}
		return false;
	}
}
