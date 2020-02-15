package Application.View;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import Application.Controller.JeuController;
import Application.Controller.ReglageController;
import Application.Controller.ReglementController;
import Application.Controller.StatistiqueController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StatistiqueView implements Observer {

	final VBox vb = new VBox();
	final VBox vbt = new VBox();
	
    public StatistiqueView(Stage stage) {
    	BorderPane bp = new BorderPane();
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(bp, screenBounds.getWidth()-20, screenBounds.getHeight()-80);  
        bp.setStyle("-fx-background-image: url('Application/Resources/Images/wallpaper.png');" +
				"-fx-background-position: center center;" +
	            "-fx-background-size: cover;");

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int ht  = (int)dimension.getHeight();
        
		Label title = new Label("Statistiques");
		title.setFont(new Font("Viner Hand ITC", 64));
		title.setTextFill(Color.web("#ffffff"));
		
		Text parties = new Text("Parties jouées : " + "");
		Text joueurs = new Text("Joueurs créés : " + "");
		Text temps = new Text("Temps joué : " + "" + " heures");
		parties.setFont(Font.font(36));
		joueurs.setFont(Font.font(36));
		temps.setFont(Font.font(36));

        Button btnRetour = new Button();
        btnRetour.setId("Retour");
        btnRetour.setStyle("-fx-background-image: url('Application/Resources/Images/retour.png');" +
				   "-fx-background-color: rgba(0, 0, 0, 0);");
        btnRetour.setMinWidth(140);
        btnRetour.setMinHeight(54);
        btnRetour.setMaxHeight(54);
        btnRetour.setOnAction(new ReglageController<ActionEvent>(stage));
        btnRetour.setOnAction(new StatistiqueController<ActionEvent>(stage));

        vbt.getChildren().addAll(parties, joueurs, temps);
        vb.getChildren().addAll(title, vbt, btnRetour);
        vb.setSpacing(ht*0.2575);
        vbt.setSpacing(50);
        vbt.setAlignment(Pos.CENTER);
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
