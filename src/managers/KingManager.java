package managers;

import java.util.ArrayList;
import java.util.List;

import entities.AbstractPiece;
import entities.Colour;
import world.Move;
import world.State;

public class KingManager {
	private AbstractPiece king;
	private Colour colour;
	
	public KingManager(Colour colour) {
		this.colour = colour;
	}

	public void setKing(AbstractPiece king) {
		this.king = king;
	}
	
	public AbstractPiece getKing() {
		return king;
	}
	
	public boolean inCheck() {
		List<AbstractPiece> toCheck = new ArrayList<>(GameManager.pieceManager.getPieces(colour.opposite()));
		for(AbstractPiece piece : toCheck) {
			for(Move move : piece.getKills()) {
				if(move.getDest().getPiece().equals(king)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean moveCausesCheck(Move move) {
		move.makeTempMove();
		boolean result = inCheck();
		move.resetTempMove();
		return result;
	}
	
	public State getCheckState() {
		if(inCheck()) {
			if(king.canMove()) {
				return State.CHECK;
			}
			for(Move move : GameManager.pieceManager.getAllMoves(colour)) {
				move.makeTempMove();
				if(!inCheck()) {
					return State.CHECK;
				}
				move.resetTempMove();
			}
			return State.CHECKMATE;
		}
		return State.NORMAL;
	}
}
