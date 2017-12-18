package managers;

import entities.Colour;
import world.Move;

public class GameLoop extends Thread {
	private boolean running;
	
	@Override
	public void run() {
		running = true;
		while(running) {
			turn(Colour.WHITE);
			turn(Colour.BLACK);
		}
	}
	
	/*
	 * 1) get move
	 * 2) make move
	 * 3) update new moves
	 * 4) check if king in check
	 * 5) check if king in checkmate
	 */
	private void turn(Colour player) {
		Move move = null; //get move
		move.makeMove();
		GameManager.pieceManager.updatePieceMoves();
		KingManager km = GameManager.getKM(player.opposite());
		switch(km.getCheckState()) {
		case CHECKMATE:
			running = false;
			return;
		case CHECK:
			//handle this
		default:
			return;
		}
	}
}
