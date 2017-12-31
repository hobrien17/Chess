package gui;

import entities.AbstractPiece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceImage extends ImageView {

	private AbstractPiece piece;
	
	public PieceImage(Image img, AbstractPiece piece) {
		super(img);
		this.piece = piece;
	}
	
	public AbstractPiece getPiece() {
		return piece;
	}

	public static PieceImage getImage(AbstractPiece piece, int width, int height) {
		return new PieceImage(new Image(String.format("file:resources/pieces/%s/%s.png", 
				piece.getColour().toString().toLowerCase(), piece.getName()), 
				width, height, false, false), piece);
	}

}
