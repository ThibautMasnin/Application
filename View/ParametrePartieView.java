package Application.View;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import Application.Controller.ParametreController;
import Application.Controller.ReglageController;
import Application.Controller.StatistiqueController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ParametrePartieView implements Observer {

	final VBox vb = new VBox();
	final VBox vb1 = new VBox();
	final VBox vb2 = new VBox();
	final HBox hb1 = new HBox();
	final HBox hb2 = new HBox();

	public ParametrePartieView(Stage stage) {
		BorderPane bp = new BorderPane();
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		Scene scene = new Scene(bp, screenBounds.getWidth()-20, screenBounds.getHeight()-80);  
		bp.setStyle("-fx-background-image: url('Application/Ressources/Images/wallpaper.png');" +
				"-fx-background-position: center center;" +
				"-fx-background-size: cover;");

		Label title = new Label("Réglages de la partie");
		title.setFont(new Font("Viner Hand ITC", 64));
		title.setTextFill(Color.web("#ffffff"));

		Text nbJ = new Text("Nombre de joueurs :");
		Text nbI = new Text("Nombre d'IA :");
		Text temps = new Text("Secondes par tour :");
		nbJ.setFont(Font.font(36));
		nbI.setFont(Font.font(36));
		temps.setFont(Font.font(36));

		Spinner<Integer> nbJoueur = new Spinner<>(0,4,0);
		nbJoueur.setMaxWidth(100);
		nbJoueur.setEditable(true);		
		Spinner<Integer> nbIA = new Spinner<>(0,4,0);
		nbIA.setMaxWidth(100);
		nbIA.setEditable(true);
		Spinner<Integer> minuteur = new Spinner<>(0,300,0);
		minuteur.setMaxWidth(100);
		minuteur.setEditable(true);

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int ht  = (int)dimension.getHeight();
		
        Button btnRetour = new Button();
        btnRetour.setId("Retour");
        btnRetour.setStyle("-fx-background-image: url('Application/Ressources/Images/retour.png');" +
				   "-fx-background-color: rgba(0, 0, 0, 0);");
        btnRetour.setMinWidth(140);
        btnRetour.setMinHeight(54);
        btnRetour.setOnAction(new ReglageController<ActionEvent>(stage));
        Button btnValider = new Button();
        btnValider.setId("Valider");
        btnValider.setStyle("-fx-background-image: url('Application/Ressources/Images/valider.png');" +
				   "-fx-background-color: rgba(0, 0, 0, 0);");
        btnValider.setMinWidth(140);
        btnValider.setMinHeight(54);
        btnValider.setOnAction(new ReglageController<ActionEvent>(stage));
        
        vb1.getChildren().addAll(nbJ, nbI, temps);
        vb1.setSpacing(50);
        vb1.setAlignment(Pos.CENTER);
        vb2.getChildren().addAll(nbJoueur, nbIA, minuteur);
        vb2.setSpacing(50);
        vb2.setAlignment(Pos.CENTER);
        hb1.getChildren().addAll(vb1, vb2);
        hb1.setSpacing(50);
        hb1.setAlignment(Pos.CENTER);
        hb2.getChildren().addAll(btnRetour, btnValider);
        hb2.setAlignment(Pos.CENTER);
        hb2.setSpacing(50);
        vb.getChildren().addAll(title, hb1, hb2);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(ht*0.2575);

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