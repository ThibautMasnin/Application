package Application.Controller;

import Application.View.JeuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReprendrePartieController<T extends ActionEvent> implements EventHandler<T> {

	private Stage s;	

	public ReprendrePartieController(Stage s) {
		super();
		this.s=s;
	}

	@Override
	public void handle(T event) {
		if (event.getSource() instanceof Button) {
			if (((Button) event.getSource()).getId() == "Retour") {
				JeuView ppv = new JeuView(s);
			}
		}
	}
}
