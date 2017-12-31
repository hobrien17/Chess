package world;

import entities.Colour;

public class Board {
	private int rows;
	private int cols;
	private Cell[][] grid;

	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		grid = new Cell[rows][cols];
		
		boolean prev = false;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(prev) {
					grid[i][j] = new Cell(i, j, Colour.BLACK);
				} else {
					grid[i][j] = new Cell(i, j, Colour.WHITE);
				}
				prev = !prev;
			}
			if(cols % 2 == 0) {
				prev = !prev;
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
