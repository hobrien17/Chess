package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import managers.GameManager;
import world.Board;
import world.Cell;
import world.Move;

/**
 * A piece in a game of chess
 */
public abstract class AbstractPiece {
	protected int row; //the piece's current row
	protected int col; //the piece's current column
	protected Colour colour; //the colour of the piece
	protected int mod; //set automatically by the colour of the piece to determine movement direction
	protected int moveCount; //the amount of moves this piece has made
	protected boolean alive; //whether this piece is alive of not
	
	protected Map<Direction, Integer> moveMap; //stores the moves that can be made by this piece
	protected Map<Direction, Integer> killMap; //stores the kills that his piece can make
	protected List<Move> moves;
	protected List<Move> kills;
	
	protected int id; //the id of this piece
	protected static int idGen = 0; //generates this piece's id
	
	public AbstractPiece(int row, int col, Colour colour) {
		this.row = row;
		this.col = col;
		this.colour = colour;
		switch(colour) {
		case WHITE:
			mod = -1;
			break;
		case BLACK:
			mod = 1;
			break;
		default:
			mod = 0;
			break;
		}
		moveCount = 0;
		alive = true;
		id = idGen++;
	}
	
	/**
	 * Returns the row of the piece
	 * 
	 * @return the piece's current row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Returns the column of the piece
	 * 
	 * @return the piece's current column
	 */
	public int getCol() {
		return col;
	}
	
	public Cell getCell() {
		return GameManager.instance.getBoard().getCell(row, col);
	}
	
	/**
	 * Returns the colour of the piece
	 * 
	 * @return black, white or colourless
	 */
	public Colour getColour() {
		return colour;
	}
	
	/**
	 * Generates a list of possible moves this piece can make based on the current game state
	 * 
	 * @return a list of moves
	 */
	protected List<Move> calculateMoves() {
		List<Move> result = new ArrayList<>();
		Board board = GameManager.instance.getBoard();
		for(Direction direc : Direction.values()) {
			Integer get = getMoveMap().get(direc);
			if(get != null && get != 0) {
				int r = row + direc.getYMod(mod);
				int c = col + direc.getXMod();
				if(get < 0) {
					while(board.onBoard(r, c) && !board.getCell(r, c).isOccupied()) {
						Move move = new Move(getCell(), board.getCell(r, c));
						result.add(move);
						r += direc.getYMod(mod);
						c += direc.getXMod();
					}
				} else {
					while((row + get*direc.getYMod(mod) - r)*mod >= 0 && c <= col + get*direc.getXMod() && 
							board.onBoard(r, c) && !board.getCell(r, c).isOccupied()) {
						Move move = new Move(getCell(), board.getCell(r, c));
						result.add(move);
						r += direc.getYMod(mod);
						c += direc.getXMod();
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Generates a list of pieces that can be killed by this piece in the current game state
	 * 
	 * @return a list of kills
	 */
	protected List<Move> calculateKills() {
		List<Move> result = new ArrayList<>();
		Board board = GameManager.instance.getBoard();
		for(Direction direc : Direction.values()) {
			Integer get = getKillMap().get(direc);
			if(get != null && get != 0) {
				int r = row + direc.getYMod(mod);
				int c = col + direc.getXMod();
				if(get < 0) {
					while(board.onBoard(r, c)) {
						if(board.getCell(r, c).isOccupied()) {
							Move move = new Move(getCell(), board.getCell(r, c));
							result.add(move);
							break;
						}
						r += direc.getYMod(mod);
						c += direc.getXMod();
					}
				} else {
					while((row + get*direc.getYMod(mod) - r)*mod >= 0 && c <= col + get*direc.getXMod()) {
						if(board.getCell(r, c).isOccupied()) {
							Move move = new Move(getCell(), board.getCell(r, c));
							result.add(move);
							break;
						}
						r += direc.getYMod(mod);
						c += direc.getXMod();
					}
				}
			}
		}
		return result;
	}
	
	public void update() {
		moves = calculateMoves();
		kills = calculateKills();
	}
	
	public List<Move> getMoves() {
		return moves;
	}
	
	public List<Move> getKills() {
		return kills;
	}
	
	public List<Move> getPossible() {
		List<Move> result = new ArrayList<>(moves);
		moves.addAll(kills);
		return result;
	}
	
	public boolean canMove() {
		return getPossible().size() > 0;
	}
	
	/**
	 * Returns a map containing all possible moves this piece can make
	 * 
	 * @return a map of all possible moves
	 */
	public Map<Direction, Integer> getMoveMap() {
		return moveMap;
	}
	
	/**
	 * Returns a map containing all possible kills this piece can make
	 * 
	 * @return a map of all possible kills
	 */
	public Map<Direction, Integer> getKillMap() {
		return killMap;
	}
	
	/**
	 * Moves this piece to another location
	 * 
	 * @param row
	 * 			the piece's new row
	 * @param col
	 * 			the piece's new column
	 */
	public void move(int row, int col) {
		this.row = row;
		this.col = col;
		moveCount++;
	}
	
	/**
	 * Kills this piece
	 */
	public void kill() {
		this.alive = false;
	}
	
	/**
	 * Returns whether this piece is alive
	 * 
	 * @return true if this piece is alive, otherwise false
	 */
	public boolean isAlive() {
		return alive;
	}
	
	public int getID() {
		return id;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof AbstractPiece && ((AbstractPiece)o).getID() == id;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode()*id;
	}
}
