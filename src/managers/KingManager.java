package managers;

import java.util.ArrayList;
import java.util.List;

import entities.AbstractPiece;
import entities.Colour;
import entities.King;
import world.Move;
import world.State;

public class KingManager {
	private AbstractPiece king;
	private Colour colour;
	private boolean check;
	
	public KingManager(Colour colour) {
		this.colour = colour;
		check = false;
	}

	public void setKing(AbstractPiece king) {
		this.king = king;
	}
	
	public AbstractPiece getKing() {
		return king;
	}
	
	public void setCheck() {
		List<AbstractPiece> toCheck = GameManager.pieceManager.getPieces(colour.opposite());
		for(AbstractPiece piece : toCheck) {
			for(Move move : piece.calculateKills()) {
				if(move.isKillMove() && move.getDest().getPiece().equals(king)) {
					check = true;
					return;
				}
			}
		}
		check = false;
	}
	
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	public boolean inCheck() {
		return check;
	}
	
	public State getCheckState() {
		if(inCheck()) {
			if(king.canMove()) {
				return State.CHECK;
			}
			if(GameManager.pieceManager.getAllMoves(colour).isEmpty()) {
				System.out.println("cm");
				return State.CHECKMATE;
			}
		}
		return State.NORMAL;
	}
}
