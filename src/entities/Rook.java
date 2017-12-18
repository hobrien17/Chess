package entities;

import java.util.HashMap;

/**
 * A rook piece
 */
public class Rook extends AbstractPiece {

	public Rook(int row, int col, Colour colour) {
		super(row, col, colour);
		moveMap = new HashMap<>();
		moveMap.put(Direction.FOR, -1);
		moveMap.put(Direction.BACK, -1);
		moveMap.put(Direction.LEFT, -1);
		moveMap.put(Direction.RIGHT, -1);
		killMap = new HashMap<>(moveMap);
	}
	
	@Override
	public String toString() {
		return colour.toString() + " Rook (" + row + ", " + col + ")";
	}
}
