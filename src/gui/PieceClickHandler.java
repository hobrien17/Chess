package gui;

import java.util.List;

import entities.AbstractPiece;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import managers.GameManager;
import world.Move;

public class PieceClickHandler implements EventHandler<MouseEvent> {

	private AbstractPiece piece;
	
	public PieceClickHandler(AbstractPiece piece) {
		super();
		this.piece = piece;
	}
	
	@Override
	public void handle(MouseEvent event) {
		GameManager.instance.getGUI().clearHighlights();
		Controller.select();
		List<Move> moves = piece.getPossible();
		GameManager.instance.getGUI().highlightMoves(moves, piece.getRow(), piece.getCol());
	}

}
