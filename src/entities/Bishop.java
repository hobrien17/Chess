package entities;

import java.util.HashMap;

/**
 * A bishop piece in a game of chess
 * Moves along the diagonals of the board
 *
 */
public class Bishop extends AbstractPiece {

	public Bishop(int row, int col, Colour colour) {
		super(row, col, colour);
		moveMap = new HashMap<>();
		moveMap.put(Direction.FOR_LEFT, -1);
		moveMap.put(Direction.FOR_RIGHT, -1);
		moveMap.put(Direction.BACK_LEFT, -1);
		moveMap.put(Direction.BACK_RIGHT, -1);
		killMap = new HashMap<>(moveMap);
	}
	
	@Override
	public String toString() {
		return colour.toString() + " Bishop (" + row + ", " + col + ")";
	}

	@Override
	public String getName() {
		return "bishop";
	}
}
