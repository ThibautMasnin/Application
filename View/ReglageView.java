package Application.View;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import Application.Controller.ReglageController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ReglageView implements Observer {

	final VBox vb = new VBox();
	final HBox hb = new HBox();


	public ReglageView(Stage stage) {
		BorderPane bp = new BorderPane();
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		Scene scene = new Scene(bp, screenBounds.getWidth()-20, screenBounds.getHeight()-80);  
		bp.setStyle("-fx-background-image: url('Application/Ressources/Images/wallpaper.png');" +
				"-fx-background-position: center center;" +
				"-fx-background-size: cover;");

		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int ht  = (int)dimension.getHeight();

		Label espace1 = new Label();
		espace1.setMinHeight(ht*0.025);
		Label espace2 = new Label();
		espace2.setMinHeight(ht*0.025);
		Label title = new Label("Reglages du jeu");
		title.setFont(new Font("Viner Hand ITC", 64));
		title.setTextFill(Color.web("#ffffff"));

		Text t1 = new Text("Sons :");
		Text t2 = new Text("Musique :");
		Text t3 = new Text("Langue :");
		t1.setFont(Font.font(36));
		t2.setFont(Font.font(36));
		t3.setFont(Font.font(36));

		Slider son = new Slider(0, 1, 0.5);
		son.setMaxWidth(150);
		Slider musique = new Slider(0, 1, 0.5);
		musique.setMaxWidth(150);
		ComboBox<String> langue = new ComboBox<>();
		langue.getItems().addAll("Français", "English", "Italiano", "Espanol", "Deutsch", "Nederlands", "Português", "русский");
		langue.getSelectionModel().select(0);
		langue.setMinWidth(150);
		langue.setMinHeight(25);
		
		
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

        title.setPadding(new Insets(0, 0,ht*0.15,0));
        hb.getChildren().addAll(btnRetour, btnValider);
        vb.getChildren().addAll(title, t1, son, espace1, t2, musique, espace2, t3, langue, hb);
        vb.setSpacing(25);
        hb.setSpacing(25);
        hb.setPadding(new Insets(ht*0.15, 0,0,0));
        vb.setAlignment(Pos.CENTER);
        hb.setAlignment(Pos.CENTER);

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


