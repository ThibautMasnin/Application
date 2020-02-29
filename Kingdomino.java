package Application;

import Application.Model.PartieModel;
import Application.View.PartieView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Kingdomino extends Application {
	@Override
	public void start(Stage primaryStage) throws SQLException {

// Thibaut, Valentin
      primaryStage.setTitle("KingDomino");
	  PartieView partieView = new PartieView(primaryStage);
		 
// Efekan, Kevin, Aymeric
//		PartieModel partieModel = new PartieModel();
//		partieModel.jouer();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
