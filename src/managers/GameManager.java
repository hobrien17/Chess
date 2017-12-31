package managers;

import java.util.ArrayList;
import java.util.List;

import entities.AbstractPiece;
import entities.Colour;
import entities.Knight;
import entities.Pawn;
import gui.Controller;
import gui.View;
import world.Board;

public class GameManager {
	public static KingManager blackManager = new KingManager(Colour.BLACK);
	public static KingManager whiteManager = new KingManager(Colour.WHITE);
	public static PieceManager pieceManager = new PieceManager();
	public static Controller controller = new Controller();
	public static GameManager instance = new GameManager();
	
	private Board board;
	private Colour winner;
	private Colour turn;
	private View gui;
		
	public GameManager() {
		
		
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void startGUI() {
		gui = new View();
	}
	
	public View getGUI() {
		return gui;
	}
	
	public Colour getTurn() {
		return turn;
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
		pieceManager.updatePieceMoves();
		turn = Colour.BLACK;
	}
	
	public void startTurn() {
		turn = turn.opposite();
		gui.setPieceClicks(turn);
	}
	
	public void endTurn() {
		blackManager.setCheck();
		whiteManager.setCheck();
		pieceManager.updatePieceMoves();
		KingManager km = getKM(turn.opposite());
		switch(km.getCheckState()) {
		case CHECKMATE:
			return;
		case CHECK:
			return;
		default:
			break;
		}
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
}
