package managers;

import java.util.ArrayList;
import java.util.List;

import entities.AbstractPiece;
import entities.Colour;
import world.Move;

public class PieceManager {
	private List<AbstractPiece> black;
	private List<AbstractPiece> white;
	
	public void setBlackPieces(List<AbstractPiece> pieces) {
		black = new ArrayList<>(pieces);
	}
	/**
	 * 
	 * @param pieces
	 */
	public void setWhitePieces(List<AbstractPiece> pieces) {
		white = new ArrayList<>(pieces);
	}
	
	public List<AbstractPiece> getPieces(Colour colour) {
		switch(colour) {
		case BLACK:
			return black;
		case WHITE:
			return white;
		default:
			return null;
		}
	}
	
	public List<AbstractPiece> getPieces() {
		List<AbstractPiece> result = new ArrayList<>(white);
		result.addAll(black);
		return result;
	}
	
	public void updatePieceMoves() {
		List<AbstractPiece> all = new ArrayList<>(white);
		all.addAll(black);
		for(AbstractPiece piece : all) {
			piece.update();
		}
	}
	
	public List<Move> getAllMoves(Colour colour) {
		List<AbstractPiece> pieces = null;
		switch(colour) {
		case BLACK:
			pieces = new ArrayList<>(black);
			break;
		case WHITE:
			pieces = new ArrayList<>(white);
			break;
		default:
			pieces = new ArrayList<>(white);
			pieces.addAll(black);
			break;
		}
		List<Move> result = new ArrayList<>();
		for(AbstractPiece piece : pieces) {
			result.addAll(piece.getPossible());
		}
		return result;
	}
}
