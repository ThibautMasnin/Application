package Application.Controller;

import Application.Model.DominoModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class PartieController<T extends ActionEvent> implements EventHandler<T> {
	private Stage stage;
	private DominoModel dominoTMP;


	public PartieController(Stage s) {
		stage = s;
	}

	public PartieController(Stage s, DominoModel d) {
		stage = s;
		dominoTMP = d;
	}


	/**
	 * ACTION EVENT
	 **/
	 @Override
	 public void handle(T event) {

		 if (event.getSource() instanceof Button) {

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

			 if (((Button) event.getSource()).getText() == "Quitter") {
				 stage.close();
			 }
		 }
	 }
}