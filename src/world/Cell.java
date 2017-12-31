package world;

import entities.AbstractPiece;
import entities.Colour;

public class Cell {
	private AbstractPiece piece;
	private int row;
	private int col;
	private Colour colour;
	
	public Cell(int row, int col, Colour colour) {
		piece = null;
		this.colour = colour;
		this.row = row;
		this.col = col;
	}
	
	public Cell(int row, int col, Colour colour, AbstractPiece piece) {
		this(row, col, colour);
		this.piece = piece;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
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
	
	public Colour getColour() {
		return colour;
	}
	
	public String toString() {
		return "C@(" + row + ", " + col + ")";
	}
}
