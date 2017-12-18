package entities;

public enum Direction {
	FOR,
	BACK,
	LEFT,
	RIGHT,
	FOR_LEFT,
	FOR_RIGHT,
	BACK_LEFT,
	BACK_RIGHT;
	
	public int getXMod() {
		switch(this) {
		case FOR:
		case BACK:
			return 0;
		case LEFT:
		case FOR_LEFT:
		case BACK_LEFT:
			return -1;
		case RIGHT:
		case FOR_RIGHT:
		case BACK_RIGHT:
			return 1;
		}
		return 0;
	}
	
	public int getYMod() {
		switch(this) {
		case LEFT:
		case RIGHT:
			return 0;
		case FOR:
		case FOR_LEFT:
		case FOR_RIGHT:
			return 1;
		case BACK:
		case BACK_LEFT:
		case BACK_RIGHT:
			return -1;
		}
		return 0;
	}
	
	public int getYMod(int mod) {
		return getYMod() * mod;
	}
	
	public boolean isVert() {
		return this != LEFT && this != RIGHT;
	}
	
	public boolean isHorz() {
		return this != FOR && this != BACK;
	}
}
