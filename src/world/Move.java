package world;

import entities.AbstractPiece;

public class Move {
	private AbstractPiece piece;
	private AbstractPiece temp;
	private Cell src;
	private Cell dest;
	private boolean complete;
	private boolean killMove;
	
	public Move(Cell src, Cell dest) {
		this.src = src;
		this.dest = dest;
		piece = src.getPiece();
		killMove = dest.isOccupied() ? true : false;
		complete = false;
	}
	
	public boolean moveComplete() {
		return complete;
	}
	
	public boolean isKillMove() {
		return killMove;
	}
	
	public AbstractPiece getPiece() {
		return piece;
	}
	
	public Cell getSrc() {
		return src;
	}
	
	public Cell getDest() {
		return dest;
	}
	
	public void makeMove() {
		AbstractPiece killed = killMove ? dest.getPiece() : null;
		if(killMove) {
			killed.kill();
			dest.removePiece();
		}
		src.removePiece();
		dest.setPiece(piece);
		complete = true;
	}
	
	public void makeTempMove() {
		temp = dest.getPiece();
		dest.removePiece();
		src.removePiece();
		dest.setPiece(piece);
	}
	
	public void resetTempMove() {
		dest.removePiece();
		src.setPiece(piece);
		dest.setPiece(temp);
		temp = null;
	}
	
	@Override
	public String toString() {
		return "Move from " + src.toString() + " to " + dest.toString();
	}
}
