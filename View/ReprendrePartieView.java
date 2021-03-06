package Application.View;

import java.awt.Dimension;
import Application.Controller.ReglageController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ReprendrePartieView {

	final VBox vb = new VBox();
	final HBox hb = new HBox();

	public ReprendrePartieView(Stage stage) {
		BorderPane bp = new BorderPane();
		TableView tab = new TableView<>();

		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		Scene scene = new Scene(bp, screenBounds.getWidth()-20, screenBounds.getHeight()-80);  
		bp.setStyle("-fx-background-image: url('Application/Ressources/Images/wallpaper.png');" +
				"-fx-background-position: center center;" +
				"-fx-background-size: cover;");

		Label title = new Label("Reprendre une sauvegarde");
		title.setFont(new Font("Viner Hand ITC", 64));
		title.setTextFill(Color.web("#ffffff"));

		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int ht  = (int)dimension.getHeight();
		int wd  = (int)dimension.getWidth();

		tab.setMinHeight(ht*0.66);
		tab.setMaxWidth(wd-40);
		tab.setStyle("-fx-background-color: rgba(0, 0, 0, 0.25);");

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

		hb.getChildren().addAll(btnRetour, btnValider);   
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(25);
		vb.getChildren().addAll(title, tab, hb);   
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(45);
		vb.setPadding(new Insets(0, 0,10,0));

        tab.setMinHeight(ht*0.66);
        tab.setMaxWidth(wd-40);
        tab.setStyle("-fx-background-color: rgba(0, 0, 0, 0.25);");

        bp.setCenter(vb);
		bp.setCenter(vb);       

		stage.setScene(scene);
		stage.setResizable(false);
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		stage.show();
	}
}