package Application.Controller;

import java.io.File;
import java.sql.SQLException;

import Application.View.JeuView;
import Application.View.PartieView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ParametreController<T extends ActionEvent> implements EventHandler<T> {

	private Stage s;	
	private int nbJoueurs;
	private int minChrono;
	private int secChrono;

	public ParametreController(Stage s) {
		super();
		this.s=s;
	}
	
	public ParametreController(Stage s, int nb, int min, int sec) {
		super();
		this.s=s;
		this.nbJoueurs=nb;
		this.minChrono=min;
		this.secChrono=sec;
	}

	@Override
	public void handle(T event) {
		if (event.getSource() instanceof Button) {
	    	File file = new File("src/Application/Ressources/Sons/clic.wav");  
	    	Media media = new Media(file.toURI().toString());
	    	MediaPlayer mediaPlayer = new MediaPlayer(media); 
	        mediaPlayer.play(); 
			if (((Button) event.getSource()).getId() == "Retour") {
				JeuView ppv = new JeuView(s);
			}
        	else if (((Button) event.getSource()).getId() == "Valider") {
            	if(nbJoueurs==2||nbJoueurs==3||nbJoueurs==4) {
    				try {
    					PartieView partieView = new PartieView(s, nbJoueurs, minChrono, secChrono);
    				} catch (SQLException e) {
    					e.printStackTrace();
    				}
            	}
        	}
		}
	}
}
