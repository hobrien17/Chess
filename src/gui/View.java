package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import entities.AbstractPiece;
import entities.Colour;
import entities.Pawn;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import managers.GameManager;
import world.Board;
import world.Cell;
import world.Move;

public class View {
	// constants
	private static final int CELL_WIDTH = 50;
	private static final int CELL_HEIGHT = 50;
	private int boardWidth;
	private int boardHeight;
	
	// panes
	private Scene scene;
	private GridPane main;
	private Group gamePane;
	private GridPane grid;
	
	// images
	private PieceImage[][] pieces;
	private ImageView[][] boardImages;

	public View() {
		main = new GridPane();
		gamePane = new Group();
		main.add(gamePane, 0, 0);
		scene = new Scene(main, 400, 400);
		createBoard(GameManager.instance.getBoard());
		addPieces(GameManager.instance.getBoard());
		clearHandlers();
	}

	public Scene getScene() {
		return scene;
	}

	public void createBoard(Board board) {
		boardWidth = board.getWidth();
		boardHeight = board.getHeight();
		boardImages = new ImageView[boardHeight][boardWidth];
		grid = new GridPane();
		gamePane.getChildren().add(grid);
		Image black = new Image("file:resources/board/black.png", CELL_WIDTH, CELL_HEIGHT, false, false);
		Image white = new Image("file:resources/board/white.png", CELL_WIDTH, CELL_HEIGHT, false, false);
		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				ImageView img;
				switch(board.getCell(i, j).getColour()) {
				case BLACK:
					img = new ImageView(black);
					break;
				default:
					img = new ImageView(white);
					break;
				}
				boardImages[i][j] = img;
				grid.add(img, j, i);
			}
		}
	}
	
	public void addPieces(Board board) {
		pieces = new PieceImage[board.getHeight()][board.getWidth()];
		for(int i = 0; i < board.getHeight(); i++) {
			for(int j = 0; j < board.getWidth(); j++) {
				Cell cell = board.getCell(i, j);
				if(cell.isOccupied()) {
					PieceImage img = PieceImage.getImage(cell.getPiece(), CELL_WIDTH, CELL_HEIGHT);
					gamePane.getChildren().add(img);
					pieces[i][j] = img;
					img.setX(j * CELL_WIDTH);
					img.setY(i * CELL_HEIGHT);
				}
			}
		}
	}
	
	public void movePiece(Cell oldCell, Cell newCell) {
		PieceImage piece = pieces[oldCell.getRow()][oldCell.getCol()];
		piece.setX(newCell.getCol() * CELL_WIDTH);
		piece.setY(newCell.getRow() * CELL_HEIGHT);
		if(pieces[newCell.getRow()][newCell.getCol()] != null) {
			gamePane.getChildren().remove(pieces[newCell.getRow()][newCell.getCol()]);
		}
		pieces[newCell.getRow()][newCell.getCol()] = piece;
		pieces[oldCell.getRow()][oldCell.getCol()] = null;
	}
	
	public void setPieceClicks(Colour colour) {
		for(int i = 0; i < boardHeight; i++) {
			for(int j = 0; j < boardWidth; j++) {
				if(pieces[i][j] != null && pieces[i][j].getPiece().getColour().equals(colour)) {
					pieces[i][j].setOnMouseClicked(new PieceClickHandler(pieces[i][j].getPiece()));
				}
			}
		}
	}
	
	public void removePieceClicks(Colour colour) {
		for(int i = 0; i < boardHeight; i++) {
			for(int j = 0; j < boardWidth; j++) {
				if(pieces[i][j] != null && pieces[i][j].getPiece().getColour().equals(colour)) {
					pieces[i][j].setOnMouseClicked(null);
				}
			}
		}
	}
	
	public void highlightMoves(List<Move> moves, int row, int col) {
		ColorAdjust blue = new ColorAdjust();
		blue.setHue(1);
		blue.setBrightness(0.2);
		blue.setSaturation(0.5);
		ColorAdjust red = new ColorAdjust();
		red.setHue(-0.1);
		red.setBrightness(0.2);
		red.setSaturation(0.5);
		ColorAdjust green = new ColorAdjust();
		green.setHue(0.5);
		green.setBrightness(0.2);
		green.setSaturation(0.5);
		boardImages[row][col].setEffect(green);
		for(Move move : moves) {
			int destRow = move.getDest().getRow();
			int destCol = move.getDest().getCol();
			if(move.isKillMove()) {
				boardImages[destRow][destCol].setEffect(red);
				pieces[destRow][destCol].setOnMouseClicked(new CellClickHandler(move));
			} else {
				boardImages[destRow][destCol].setEffect(blue);
			}
			boardImages[destRow][destCol].setOnMouseClicked(new CellClickHandler(move));
		}
	}
	
	public void clearHighlights() {
		for(int i = 0; i < boardHeight; i++) {
			for(int j = 0; j < boardWidth; j++) {
				if(boardImages[i][j].getEffect() != null) {
					boardImages[i][j].setEffect(null);
					boardImages[i][j].setOnMouseClicked(new ClickHandler());
				}
			}
		}
	}
	
	public void clearHandlers() {
		for(int i = 0; i < boardHeight; i++) {
			for(int j = 0; j < boardWidth; j++) {
				boardImages[i][j].setOnMouseClicked(new ClickHandler());
				if(pieces[i][j] != null) {
					pieces[i][j].setOnMouseClicked(new ClickHandler());
				}
			}
		}
	}

}
