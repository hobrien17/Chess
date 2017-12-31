package games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entities.AbstractPiece;
import managers.GameManager;
import world.Board;

public class GameTemplate {
	
	private Board board;
	private List<AbstractPiece> white;
	private List<AbstractPiece> black;
	private AbstractPiece whiteKing;
	private AbstractPiece blackKing;
	
	public GameTemplate(Board board) {
		this.board = board;
		white = new ArrayList<>();
		black = new ArrayList<>();
	}
	
	public void setWhite(AbstractPiece... pieces) {
		white = Arrays.asList(pieces);
	}
	
	public void setBlack(AbstractPiece... pieces) {
		black = Arrays.asList(pieces);
	}
	
	public void addWhite(AbstractPiece piece) {
		white.add(piece);
	}
	
	public void addBlack(AbstractPiece piece) {
		black.add(piece);
	}
	
	public void setWhiteKing(AbstractPiece piece) {
		whiteKing = piece;
	}
	
	public void setBlackKing(AbstractPiece piece) {
		blackKing = piece;
	}
	
	public void load() {
		GameManager.instance.newGame(board, white, black, whiteKing, blackKing);
        GameManager.instance.startGUI();
        GameManager.instance.startTurn();
	}
}
