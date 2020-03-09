package Application.Controller;

import java.sql.SQLException;

import Application.View.JeuView;
import Application.View.PartieView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ParametreController<T extends ActionEvent> implements EventHandler<T> {

	private Stage s;	

	public ParametreController(Stage s) {
		super();
		this.s=s;
	}

	@Override
	public void handle(T event) {
		if (event.getSource() instanceof Button) {
			if (((Button) event.getSource()).getId() == "Retour") {
				JeuView ppv = new JeuView(s);
			}
        	else if (((Button) event.getSource()).getId() == "Valider") {
				try {
					PartieView partieView = new PartieView(s);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
		}
	}
}
