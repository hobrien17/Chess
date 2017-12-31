package gui;

import games.GameLibrary;
import javafx.application.Application;
import javafx.stage.Stage;
import managers.GameManager;

public class App extends Application {

	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {        
        stage.setTitle("Chess game");
        GameLibrary.loadStandardGame();		
        stage.setScene(GameManager.instance.getGUI().getScene());
        stage.show();
    }
	
}
