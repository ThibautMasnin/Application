package Application.Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Deck extends Rectangle {
	private int nbDominos;
	private ArrayList<DominoModel> listeDominos;

	public Deck(){
		super(0, 0, 200, 100);
		this.setFill(new ImagePattern(new Image("Application/Images/dominoFaceVerso.jpg")));
		nbDominos = 48;
		listeDominos = new ArrayList<DominoModel>();

		for (int i = 0 ; i < 8 ; i++) {
			DominoModel domino = new DominoModel(0,0,50,50,"Application/Images/chateau.png");
			domino.setSmooth(true);
			/*domino.setOnMouseClicked(event -> {
                PartieControllerME pcME;
                pcME = new PartieControllerME(domino);
                pcME.handle(event);
            });*/
			listeDominos.add(domino);
		}
	}

	public ArrayList<DominoModel> getListeDominos() {
		return listeDominos;
	}

	public int getNbDominos() {
		return nbDominos;
	}


}
