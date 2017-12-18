package entities;

import java.util.HashMap;
import java.util.Map;

/**
 * A pawn piece
 * 
 * TODO: queen generation
 */
public class Pawn extends AbstractPiece {

	public Pawn(int row, int col, Colour colour) {
		super(row, col, colour);
		moveMap = new HashMap<>();
		moveMap.put(Direction.FOR, 1);
		killMap = new HashMap<>();
		killMap.put(Direction.FOR_LEFT, 1);
		killMap.put(Direction.FOR_RIGHT, 1);
	}

	@Override
	public Map<Direction, Integer> getMoveMap() {
		Map<Direction, Integer> copy = new HashMap<>(moveMap);
		if(moveCount == 0) {
			copy.put(Direction.FOR, 2);
		}
		return copy;
	}
	
	@Override
	public String toString() {
		return colour.toString() + " Pawn (" + row + ", " + col + ")";
	}

}
