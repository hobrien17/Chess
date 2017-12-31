package gui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import managers.GameManager;
import world.Move;

public class CellClickHandler implements EventHandler<MouseEvent> {
	
	private Move move;
	
	public CellClickHandler(Move move) {
		this.move = move;
	}
	
	@Override
	public void handle(MouseEvent event) {
		move.makeMove();
		GameManager.instance.endTurn();
		GameManager.instance.getGUI().clearHighlights();
		GameManager.instance.getGUI().clearHandlers();
		Controller.clearSelect();
		GameManager.instance.startTurn();
	}
}
