package Application.Controller;

import java.io.File;
import java.sql.SQLException;
import java.util.Optional;

import Application.Model.DominoModel;
import Application.Model.PartieModel;
import Application.View.JeuView;
import Application.View.ReglageView;
import Application.View.ReglementView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class PartieController<T extends ActionEvent> implements EventHandler<T> {
	private Stage stage;
	private DominoModel dominoTMP;

	private int nbJoueurs;
	private int nbIAs;
	private String langue="fr"; 
	private int joueur;
	private int nbTour;


	public PartieController(Stage s) {
		stage = s;
	}

	public PartieController() {
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public void setNbIAs(int nbIAs) {
		this.nbIAs = nbIAs;
	}

	public void setJoueur(int joueur) {
		this.joueur = joueur;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}

	public PartieController(Stage s, DominoModel d) {
		stage = s;
		dominoTMP = d;
	}


	// ACTION EVENT
	 @Override
	 public void handle(T event) {

		 if (event.getSource() instanceof Button) {
		    	File file = new File("src/Application/Ressources/Sons/clic.mp3");  
		    	Media media = new Media(file.toURI().toString());
		    	MediaPlayer mediaPlayer = new MediaPlayer(media); 
		        mediaPlayer.play(); 
			 // EVENT ROTATION A DROITE
			 if (((Button) event.getSource()).getText() == "Rotation droite" && dominoTMP.isSelected()) {

				 dominoTMP.setRotate(dominoTMP.getRotate() + 90);
				 //PartieView partieView = new PartieView(stage, dominoTMP);
			 }

			 // EVENT ROTATION A GAUCHE
			 if (((Button) event.getSource()).getText() == "Rotation gauche" && dominoTMP.isSelected()) {

				 dominoTMP.setRotate(dominoTMP.getRotate() - 90);
				 //PartieView partieView = new PartieView(stage, dominoTMP);
			 }

			 // EVENT ANNULER SON COUP
			 if (((Button) event.getSource()).getText() == "Annuler coup") {
			 }

			 // EVENT FINIR SON TOUR
			 if (((Button) event.getSource()).getText() == "Finir tour") {
			 }

			 // EVENT RETOURNER DOMINO
			 if (((Button) event.getSource()).getText() == "Retourner") {
			 }
		 
			if (((Button) event.getSource()).getId() == "Reglement") {
				Stage secondStage = new Stage();
				ReglementView rv = new ReglementView(secondStage, true);
			}       
			else if (((Button) event.getSource()).getId() == "Reglages") {
				Stage secondStage = new Stage();
				ReglageView rv = new ReglageView(secondStage, true);
			}			
			else if (((Button) event.getSource()).getId() == "Quitter") {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmer la sortie");
				alert.setContentText("Voulez vous vraiment quitter la partie ?");
				ButtonType btnOui = new ButtonType("Quitter");
				ButtonType btnNon = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(btnOui, btnNon);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == btnOui) {
					JeuView primaryScreen = new JeuView(stage);
				}				
			}			
			else if (((Button) event.getSource()).getId() == "Sauvegarder") {
				try {
					PartieModel sauvegarde = new PartieModel(2);
					sauvegarde.setNbJoueurs(this.nbJoueurs);
					sauvegarde.setNbIAs(this.nbIAs);
					sauvegarde.setJoueur(this.joueur);
					sauvegarde.setNbTour(this.nbTour);
					sauvegarde.sauvegarderPartie();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		 }
	 }
}