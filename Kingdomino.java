package Application;

import Application.Model.Deck;
import Application.Model.Domino;
import Application.Model.PartieModel;
import Application.View.JeuView;
import Application.View.PartieView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.postgresql.ds.PGSimpleDataSource;
import java.sql.SQLException;

public class Kingdomino extends Application {

    @Override
    public void start(Stage primaryStage) throws SQLException {

        // Valentin
/*
    	Domino dmn = new Domino(0, 0, 50, 50, "Application/Resources/Images/chateau.png");
        ImageView dmn = new ImageView(new Image("Application/Resources/Images/dominoFaceRecto.jpg"));
        PartieView partieView = new PartieView(primaryStage, dmn, true);
*/


        // Thibaut
    	
        primaryStage.setTitle("KingDomino");
        JeuView primaryScreen = new JeuView(primaryStage);


        
        // Efekan, Kevin, Aymeric
/*        
    	PGSimpleDataSource ds = new PGSimpleDataSource();
		ds.setServerName("localhost");
		ds.setDatabaseName("m4106");
		ds.setUser("postgres");
		ds.setPassword("MON MDP BDD");

        PartieModel partieModel = new PartieModel();
        partieModel.jouer();
*/
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
