package entities;

import java.util.HashMap;

/**
 * A king piece
 *
 * TODO: castle moves
 * TODO: prevent moves that put the player in check
 */
public class King extends AbstractPiece {
		
	public King(int row, int col, Colour colour) {
		super(row, col, colour);
		moveMap = new HashMap<>();
		for (Direction direc : Direction.values()) {
			moveMap.put(direc, 1);
		}
		killMap = new HashMap<>(moveMap);
	}

	@Override
	public String toString() {
		return colour.toString() + " King (" + row + ", " + col + ")";
	}

	@Override
	public String getName() {
		return "king";
	}
}
