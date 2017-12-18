package world;

import entities.AbstractPiece;

public class Cell {
	private AbstractPiece piece;
	private int row;
	private int col;
	
	public Cell(int row, int col) {
		piece = null;
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public Cell(int row, int col, AbstractPiece piece) {
		this.piece = piece;
	}
	
	public boolean isOccupied() {
		return piece != null;
	}
	
	public AbstractPiece getPiece() {
		return piece;
	}
	
	public void addPiece(AbstractPiece obj) {
		if(!isOccupied()) {
			piece = obj;
		}
	}
	
	public void setPiece(AbstractPiece obj) {
		piece = obj;
	}
	
	public void removePiece() {
		piece = null;
	}
	
	public String toString() {
		return "C@(" + row + ", " + col + ")";
	}
}
