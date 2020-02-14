package Application.Controller;

import Application.View.CreditView;
import Application.View.ParametrePartieView;
import Application.View.PartieView;
import Application.View.ReglageView;
import Application.View.ReglementView;
import Application.View.ReprendrePartieView;
import Application.View.StatistiqueView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
        	if (((Button) event.getSource()).getId() == "Nouvelle partie") {
        		ParametrePartieView ppv = new ParametrePartieView(s);
        	}
        	else if (((Button) event.getSource()).getId() == "Reprendre partie") {
        		ReprendrePartieView pv = new ReprendrePartieView(s);
        	}
        	else if (((Button) event.getSource()).getId() == "Réglement") {
        		ReglementView rv = new ReglementView(s);
        	}        	
        	else if (((Button) event.getSource()).getId() == "Crédits") {
        		CreditView rv = new CreditView(s);
        	}	
        	else if (((Button) event.getSource()).getId() == "Statistiques") {
        		StatistiqueView rv = new StatistiqueView(s);
        	}        	
        	else if (((Button) event.getSource()).getId() == "Réglages") {
        		ReglageView rv = new ReglageView(s);
        	}
        	else if (((Button) event.getSource()).getId() == "Quitter") {
        		System.exit(0);
        	}
        }
	}
}
