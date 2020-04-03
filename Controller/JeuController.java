package Application.Controller;

import java.io.File;
import java.util.Optional;

import Application.View.CreditView;
import Application.View.ParametrePartieView;
import Application.View.ReglageView;
import Application.View.ReglementView;
import Application.View.ReprendrePartieView;
import Application.View.StatistiqueView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class JeuController<T extends ActionEvent> implements EventHandler<T> {

	private Stage s;	

	public JeuController(Stage s) {
		super();
		this.s=s;
	}

	@Override
	public void handle(T event) {
		if (event.getSource() instanceof Button) {
	    	File file = new File("src/Application/Ressources/Sons/clic.mp3");  
	    	Media media = new Media(file.toURI().toString());
	    	MediaPlayer mediaPlayer = new MediaPlayer(media); 
	        mediaPlayer.play(); 
			if (((Button) event.getSource()).getId() == "Nouvelle partie") {
				ParametrePartieView ppv = new ParametrePartieView(s);
			}
			else if (((Button) event.getSource()).getId() == "Reprendre partie") {
				ReprendrePartieView pv = new ReprendrePartieView(s);
			}
			else if (((Button) event.getSource()).getId() == "Reglement") {
				ReglementView rv = new ReglementView(s, false);
			}        	
			else if (((Button) event.getSource()).getId() == "Crédits") {
				CreditView rv = new CreditView(s);
			}	
			else if (((Button) event.getSource()).getId() == "Statistiques") {
				StatistiqueView rv = new StatistiqueView(s);
			}        	
			else if (((Button) event.getSource()).getId() == "Reglages") {
				ReglageView rv = new ReglageView(s, false);
			}
			else if (((Button) event.getSource()).getId() == "Quitter") {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmer la sortie");
				alert.setContentText("Voulez vous vraiment quitter le jeu ?");
				ButtonType btnOui = new ButtonType("Quitter");
				ButtonType btnNon = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(btnOui, btnNon);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == btnOui) {
					System.exit(0);
				}			
			}
		}
	}
}
