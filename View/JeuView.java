package Application.View;

import java.util.Observable;
import java.util.Observer;

import Application.Controller.JeuController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JeuView implements Observer {

	final VBox vb = new VBox();
	final HBox hb = new HBox();

	public JeuView(Stage stage) {
		BorderPane bp = new BorderPane();
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		Scene scene = new Scene(bp, screenBounds.getWidth()-20, screenBounds.getHeight()-80);  
		bp.setStyle("-fx-background-image: url('Application/Ressources/Images/background.png');" +
				"-fx-background-position: center center;" +
				"-fx-background-size: cover;");

		Label espace = new Label("");
		espace.setMinHeight(300);

		/** BOUTON NOUVELLE PARTIE **/
		Button btnNouvellePartie = new Button();
		btnNouvellePartie.setId("Nouvelle partie");
		btnNouvellePartie.setMinWidth(267);
		btnNouvellePartie.setMinHeight(64);
		btnNouvellePartie.setStyle("-fx-background-image: url('Application/Ressources/Images/nouvellePartie.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);");
		btnNouvellePartie.setOnAction(new JeuController<ActionEvent>(stage));

		/** BOUTON REPRENDRE PARTIE **/
		Button btnReprendrePartie = new Button();
		btnReprendrePartie.setId("Reprendre partie");
		btnReprendrePartie.setStyle("-fx-background-image: url('Application/Ressources/Images/reprendrePartie.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);");
		btnReprendrePartie.setMinWidth(246);
		btnReprendrePartie.setMinHeight(54);
		btnReprendrePartie.setOnAction(new JeuController<ActionEvent>(stage));

		/** BOUTON REGLEMENT **/
		Button btnReglement = new Button();
		btnReglement.setId("Reglement");
		btnReglement.setStyle("-fx-background-image: url('Application/Ressources/Images/reglement.png');" +
				   "-fx-background-color: rgba(0, 0, 0, 0);");
		btnReglement.setMinWidth(266);
		btnReglement.setMinHeight(54);
        btnReglement.setOnAction(new JeuController<ActionEvent>(stage));


		/** BOUTON STATS **/
		Button btnStatistiques = new Button();
		btnStatistiques.setId("Statistiques");
		btnStatistiques.setStyle("-fx-background-image: url('Application/Ressources/Images/statistiques.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);");
		btnStatistiques.setMinWidth(283);
		btnStatistiques.setMinHeight(51);
		btnStatistiques.setOnAction(new JeuController<ActionEvent>(stage));

		/** BOUTON CREDITS **/
		Button btnCredits = new Button();
		btnCredits.setId("Crédits");
		btnCredits.setStyle("-fx-background-image: url('Application/Ressources/Images/credits2.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);");
		btnCredits.setMinWidth(140);
		btnCredits.setMinHeight(54);
		btnCredits.setOnAction(new JeuController<ActionEvent>(stage));

		/** BOUTON REGLAGES **/
		Button btnReglages = new Button();
		btnReglages.setId("Reglages");
		btnReglages.setStyle("-fx-background-image: url('Application/Ressources/Images/reglages.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);");
		btnReglages.setMinWidth(140);
		btnReglages.setMinHeight(54);
		btnReglages.setOnAction(new JeuController<ActionEvent>(stage));

		/** BOUTON QUITTER **/
		Button btnQuitter = new Button();
		btnQuitter.setId("Quitter");
		btnQuitter.setStyle("-fx-background-image: url('Application/Ressources/Images/quitter.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);");
		btnQuitter.setMinWidth(245);
		btnQuitter.setMinHeight(56);
		btnQuitter.setOnAction(new JeuController<ActionEvent>(stage));


		hb.getChildren().addAll(btnCredits, btnReglages);
		vb.getChildren().addAll(espace, btnNouvellePartie, btnReprendrePartie, btnReglement, btnStatistiques, hb, btnQuitter);
		hb.setSpacing(10);
		vb.setSpacing(10);
		hb.setAlignment(Pos.CENTER);
		vb.setAlignment(Pos.CENTER);

		bp.setCenter(vb);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.setFullScreen(true);
		stage.show();
	}

	@Override
	public void update(Observable o, Object arg) {

	}
}