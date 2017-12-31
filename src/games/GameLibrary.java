package games;

import entities.Bishop;
import entities.Colour;
import entities.King;
import entities.Knight;
import entities.Pawn;
import entities.Queen;
import entities.Rook;
import world.Board;

public final class GameLibrary {
	
	public static void loadStandardGame() {
		GameTemplate temp = new GameTemplate(new Board(8, 8));
		for(int i = 0; i < 8; i++) {
			temp.addBlack(new Pawn(1, i, Colour.BLACK));
			temp.addWhite(new Pawn(6, i, Colour.WHITE));
		}
		temp.addBlack(new Rook(0, 0, Colour.BLACK));
		temp.addBlack(new Rook(0, 7, Colour.BLACK));
		temp.addBlack(new Knight(0, 1, Colour.BLACK));
		temp.addBlack(new Knight(0, 6, Colour.BLACK));
		temp.addBlack(new Bishop(0, 2, Colour.BLACK));
		temp.addBlack(new Bishop(0, 5, Colour.BLACK));
		temp.addBlack(new Queen(0, 3, Colour.BLACK));
		
		temp.addWhite(new Rook(7, 0, Colour.WHITE));
		temp.addWhite(new Rook(7, 7, Colour.WHITE));
		temp.addWhite(new Knight(7, 1, Colour.WHITE));
		temp.addWhite(new Knight(7, 6, Colour.WHITE));
		temp.addWhite(new Bishop(7, 2, Colour.WHITE));
		temp.addWhite(new Bishop(7, 5, Colour.WHITE));
		temp.addWhite(new Queen(7, 3, Colour.WHITE));
		
		King black = new King(0, 4, Colour.BLACK);
		King white = new King(7, 4, Colour.WHITE);
		temp.addBlack(black);
		temp.addWhite(white);
		temp.setBlackKing(black);
		temp.setWhiteKing(white);
		
		temp.load();
	}
}
