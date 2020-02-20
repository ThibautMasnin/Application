package Application;

import Application.Model.Domino;
import Application.View.PartieView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Kingdomino extends Application {

    @Override
    public void start(Stage primaryStage) throws SQLException {

        // Valentin
        PartieView partieView = new PartieView(primaryStage);


        /*
        // Thibaut
        primaryStage.setTitle("KingDomino");
        JeuView primaryScreen = new JeuView(primaryStage);
         */


        
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
