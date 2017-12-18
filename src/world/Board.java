package world;

public class Board {
	private int rows;
	private int cols;
	private Cell[][] grid;

	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		grid = new Cell[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				grid[i][j] = new Cell(i, j);
			}
		}
	}
	
	public int getWidth() {
		return cols;
	}
	
	public int getHeight() {
		return rows;
	}
	
	public boolean onBoard(int row, int col) {
		return row < rows && col < cols && row >= 0 && col >= 0;
	}
	
	public boolean isEmpty() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(grid[i][j].isOccupied()) {
					return false;
				}
			}
		}
		return true;
	}
		
	public Cell getCell(int row, int col) {
		return grid[row][col];
	}
}
