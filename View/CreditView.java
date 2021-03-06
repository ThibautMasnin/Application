package Application.View;

import java.awt.Dimension;
import Application.Controller.ReglageController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class CreditView {

	final VBox vb = new VBox();

	public CreditView(Stage stage) {
		BorderPane bp = new BorderPane();
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		Scene scene = new Scene(bp, screenBounds.getWidth()-20, screenBounds.getHeight()-80);  
		bp.setStyle("-fx-background-image: url('Application/Ressources/Images/wallpaper.png');" +
				"-fx-background-position: center center;" +
				"-fx-background-size: cover;");

		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int ht  = (int)dimension.getHeight();

		Label title = new Label("Credits");
		title.setFont(new Font("Viner Hand ITC", 64));
		title.setTextFill(Color.web("#ffffff"));

		Text devs = new Text("Jeu développé par :\n\nBAJARD Kevin\nDI BENEDETTO Valentin\nGOCER Efekan\nMASNIN Thibaut\nSULEK Aymeric");
		devs.setFont(Font.font(36));
		devs.setTextAlignment(TextAlignment.CENTER);

		Button btnRetour = new Button();
		btnRetour.setId("Retour");
		btnRetour.setStyle("-fx-background-image: url('Application/Ressources/Images/retour.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);");
		btnRetour.setMinWidth(140);
		btnRetour.setMinHeight(54);
		btnRetour.setOnAction(new ReglageController<ActionEvent>(stage));

		vb.getChildren().addAll(title, devs, btnRetour);
		vb.setSpacing(ht*0.215);
		vb.setAlignment(Pos.CENTER);

		bp.setCenter(vb);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("");
		stage.show();
	}
}