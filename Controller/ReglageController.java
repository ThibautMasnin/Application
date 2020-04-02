package Application.Controller;

import java.io.File;

import Application.View.JeuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ReglageController<T extends ActionEvent> implements EventHandler<T> {

	private Stage s;	

	public ReglageController(Stage s) {
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
			if (((Button) event.getSource()).getId() == "Retour") {
				JeuView ppv = new JeuView(s);
			}
		}
	}
}
