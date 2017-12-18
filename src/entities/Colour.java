package entities;

public enum Colour {
	BLACK,
	WHITE,
	NONE;
	
	@Override
	public String toString() {
		switch(this) {
		case BLACK:
			return "Black";
		case WHITE:
			return "White";
		default:
			return "Colourless";
		}
	}
	
	public Colour opposite() {
		switch(this) {
		case BLACK:
			return Colour.WHITE;
		case WHITE:
			return Colour.BLACK;
		default:
			return this;
		}
	}
}
