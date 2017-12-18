package entities;

import java.util.ArrayList;
import java.util.List;

import world.Cell;

/**
 * A knight piece
 */
public class Knight extends Jumper {

	public Knight(int row, int col, Colour colour) {
		super(row, col, colour);
	}

	@Override
	public List<Cell> getJumps() {
		List<Cell> jumps = new ArrayList<>();
		for(int i = -2; i <= 2; i++) {
			for(int j = -2; j <= 2; j++) {
				if(i != 0 && j != 0 && i != j && i != j*-1) {
					jumps.add(new Cell(row + i, col + j));
				}
			}
		}
		return jumps;
	}
	
	@Override
	public String toString() {
		return colour.toString() + " Knight (" + row + ", " + col + ")";
	}

}
