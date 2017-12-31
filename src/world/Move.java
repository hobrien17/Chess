package world;

import entities.AbstractPiece;
import entities.Colour;
import managers.GameManager;

public class Move {
	private AbstractPiece piece;
	private AbstractPiece temp;
	private Cell src;
	private Cell dest;
	private boolean complete;
	private boolean killMove;
	private boolean oldCheck;
	
	public Move(Cell src, Cell dest) {
		this.src = src;
		this.dest = dest;
		piece = src.getPiece();
		killMove = dest.isOccupied() && 
				dest.getPiece().getColour().equals(piece.getColour().opposite()) ? true : false;
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
			GameManager.pieceManager.removePiece(killed);
			killed.kill();
			dest.removePiece();
		}
		src.removePiece();
		dest.setPiece(piece);
		piece.move(dest.getRow(), dest.getCol());
		GameManager.instance.getGUI().movePiece(src, dest);
		complete = true;
	}
	
	private void makeTempMove() {
		oldCheck = GameManager.getKM(piece.getColour()).inCheck();
		temp = dest.getPiece();
		dest.removePiece();
		src.removePiece();
		dest.setPiece(piece);
		piece.tempMove(dest.getRow(), dest.getCol());
		GameManager.getKM(piece.getColour()).setCheck();
	}
	
	private void resetTempMove() {
		dest.removePiece();
		src.setPiece(piece);
		piece.tempMove(src.getRow(), src.getCol());
		dest.setPiece(temp);
		GameManager.getKM(piece.getColour()).setCheck(oldCheck);
		temp = null;
	}
	
	public boolean causesCheck(Colour colour) {
		makeTempMove();
		boolean result = GameManager.getKM(colour).inCheck();
		resetTempMove();
		return result;
	}
	
	@Override
	public String toString() {
		return "Move from " + src.toString() + " to " + dest.toString();
	}
}
