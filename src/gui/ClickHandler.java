package gui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import managers.GameManager;

/**
 * Clears 
 * 
 * @author Henry
 *
 */
public class ClickHandler implements EventHandler<MouseEvent> {
	
	@Override
	public void handle(MouseEvent event) {
		GameManager.instance.getGUI().clearHighlights();
		Controller.clearSelect();
	}
}
