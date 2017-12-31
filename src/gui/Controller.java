package gui;

public class Controller {
		
	private static boolean selected = false;
	
	public static boolean pieceSelected() {
		return selected;
	}
	
	public static void select() {
		selected = true;
	}
	
	public static void clearSelect() {
		selected = false;
	}
}
