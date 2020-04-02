package Application;

import Application.Model.PartieModel;
import Application.View.PartieView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class Kingdomino extends Application {

	@Override
	public void start(Stage primaryStage) throws SQLException {

// Thibaut, Valentin
      primaryStage.setTitle("KingDomino");
	  PartieView partieView = new PartieView(primaryStage);
	 		 
// Efekan, Kevin, Aymeric		VOIR Connexion.java
 		//PartieModel partieModel = new PartieModel();
		//partieModel.jouer();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
