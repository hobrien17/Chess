package entities;

import java.util.HashMap;

/**
 * A queen piece
 */
public class Queen extends AbstractPiece {

	public Queen(int row, int col, Colour colour) {
		super(row, col, colour);
		moveMap = new HashMap<>();
		for(Direction direc : Direction.values()) {
			moveMap.put(direc, -1);
		}
		killMap = new HashMap<>(moveMap);
	}

	@Override
	public String toString() {
		return colour.toString() + " Queen (" + row + ", " + col + ")";
	}

	@Override
	public String getName() {
		return "queen";
	}
}
