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


        // ZiiCTO
        //Domino dmn = new Domino(0, 0, 50, 50, "Application/Images/chateau.png");
        //ImageView dmn = new ImageView(new Image("Application/Images/dominoFaceRecto.jpg"));
        //PartieView partieView = new PartieView(primaryStage, dmn, true);



        // TheNordix
        //primaryStage.setTitle("KingDomino");
        //JeuView primaryScreen = new JeuView(primaryStage);


        /*
        // Efekan, kevin, aymeric
        PGSimpleDataSource ds = new PGSimpleDataSource();

		ds.setServerName("localhost");
		ds.setDatabaseName("m4106");
		ds.setUser("postgres");
		ds.setPassword("MON MDP BDD");

        //On dÃemarre une partie et on la lance
        PartieModel partieModel = new PartieModel();
        partieModel.jouer();
         */

    }
}
