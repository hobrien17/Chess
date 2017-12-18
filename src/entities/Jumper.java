package entities;

import java.util.ArrayList;
import java.util.List;

import world.Cell;
import world.Move;

/**
 * A piece that has the ability to jump over other pieces
 *
 */
public abstract class Jumper extends AbstractPiece {

	public Jumper(int row, int col, Colour colour) {
		super(row, col, colour);
	}
	
	/**
	 * Returns all possible jumps this piece can make
	 * 
	 * @return a list of jumps
	 */
	public abstract List<Cell> getJumps();
	
	@Override
	public List<Move> calculateMoves() {
		List<Move> result = new ArrayList<>();
		for(Cell cell : getJumps()) {
			if(!cell.isOccupied()) {
				Move move = new Move(getCell(), cell);
				result.add(move);
			}
		}
		return result;
	}
	
	@Override
	public List<Move> calculateKills() {
		List<Move> result = new ArrayList<>();
		for(Cell cell : getJumps()) {
			if(cell.isOccupied()) {
				Move move = new Move(getCell(), cell);
				result.add(move);
			}
		}
		return result;
	}

}
