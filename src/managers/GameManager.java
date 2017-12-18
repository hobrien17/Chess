package managers;

import java.util.List;

import entities.AbstractPiece;
import entities.Colour;
import entities.Knight;
import world.Board;

public class GameManager {
	public static GameManager instance = new GameManager();
	public static KingManager blackManager = new KingManager(Colour.BLACK);
	public static KingManager whiteManager = new KingManager(Colour.WHITE);
	public static PieceManager pieceManager = new PieceManager();
	
	private Board board;
	private Colour winner;
	
	public Board getBoard() {
		return board;
	}
	
	public static KingManager getKM(Colour colour) {
		switch(colour) {
		case BLACK:
			return blackManager;
		case WHITE:
			return whiteManager;
		default:
			return null;
		}
	}
	
	public void newGame(Board board, List<AbstractPiece> white, List<AbstractPiece> black, 
			AbstractPiece whiteKing, AbstractPiece blackKing) {
		{
	}
		this.board = board;
		if(!board.isEmpty()) {
			throw new IllegalArgumentException("Please supply an empty board");
		}
		pieceManager.setBlackPieces(black);
		pieceManager.setWhitePieces(white);
		for(AbstractPiece piece : pieceManager.getPieces()) {
			if(piece.getRow() < 0 || piece.getCol() < 0 || 
					piece.getRow() >= board.getHeight() || piece.getCol() >= board.getWidth()) {
				throw new IllegalArgumentException("Invalid piece location: " + piece.toString());
			}
			board.getCell(piece.getRow(), piece.getCol()).setPiece(piece);
		}
		blackManager.setKing(blackKing);
		whiteManager.setKing(whiteKing);
	}
	
	public void setWinner(Colour colour) {
		winner = colour;
	}
	
	public boolean gameOver() {
		return winner != null;
	}
	
	public Colour getWinner() {
		return winner;
	}
	
	public static void main(String[] args) {
		GameManager gm = GameManager.instance;
		Board board = gm.getBoard();
		Knight p = new Knight(2, 2, Colour.WHITE);
		board.getCell(2, 2).setPiece(p);
		System.out.println(p.getMoves());
		System.out.println(p.getKills());
	}
}
