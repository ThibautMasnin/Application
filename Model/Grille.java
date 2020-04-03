package Application.Model;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Grille implements EventHandler<MouseEvent> {
	private int tailleCase;
	private int decalage;
	private int nbLignes;
	private int nbColonnes;
	private DominoModel dominoSelectionne;
	private Pane pane;
	private String colorLine;
	private String chateau;

	private int tmpX;
	private int tmpY;


	public Grille(int tc, int d, int l, int c, String cl, String ch, int x, int y) {
		tailleCase = tc;
		decalage = d;
		nbLignes = l;
		nbColonnes = c;
		dominoSelectionne = null;
		colorLine = cl;
		chateau = ch;

		tmpX = x;
		tmpY = y;

		pane = new Pane();
	}


	/** CREATION DE LA GRILLE **/
	public void dessinerGrille() {

		/** Creation des lignes verticales et horizontales **/
		for (int i = 0; i <= nbLignes-1; i++) {
			for (int j = 0; j <= nbColonnes-1; j++) {

				/** lignes verticales **/
				Line ligne = new Line((tailleCase * j), 0, (tailleCase * j), (9 * tailleCase));
				ligne.setStrokeWidth(1.5);
				ligne.setStroke(Color.WHITE);
				pane.getChildren().add(ligne);

				if (i < nbLignes-1 && j < nbColonnes-1) {
					if (i == 4 && j == 4){
						Rectangle caseGrille = new Rectangle((tailleCase * i), (tailleCase * j), tailleCase, tailleCase);
						caseGrille.setFill(new ImagePattern(new Image(chateau)));
						pane.getChildren().add(caseGrille);
					}
					else {
						Rectangle caseGrille = new Rectangle((tailleCase * i), (tailleCase * j), tailleCase, tailleCase);
						caseGrille.setFill(Color.TRANSPARENT);
						caseGrille.setStroke(Color.TRANSPARENT);
						pane.setStyle(colorLine);
						caseGrille.setOnMouseClicked(this);
						pane.getChildren().add(caseGrille);
					}
				}
			}

			/** Lignes horizontales **/
			Line ligne = new Line(0, (tailleCase * i), (9 * tailleCase), (tailleCase * i));
			ligne.setStrokeWidth(1.5);
			ligne.setStroke(Color.WHITE);
			pane.getChildren().add(ligne);
		}
	}
 

	public Pane getPane() {
		return pane;
	}

	public void setPane(Node e) {
		pane.getChildren().add(e);
	}

	public int getTailleCase() {
		return tailleCase;
	}

	public int getDecalage() {
		return decalage;
	}

	public int getNbLignes() {
		return nbLignes;
	}

	public int getNbColonnes() {
		return nbColonnes;
	}

	public DominoModel getDominoSelectionne() {
		return dominoSelectionne;
	}


	@Override
	public void handle(MouseEvent event) {

		Object o = event.getSource();

		if (o instanceof DominoModel)
		{
			DominoModel d = (DominoModel)o;

			/** Si on sélectionne un nouveau domino ou déselectionne le domino deja selectionné **/
			if (dominoSelectionne == null || dominoSelectionne == d)
			{
				d.switchSelected();

				if (dominoSelectionne == null) {
					dominoSelectionne = d;
				}

				else {
					dominoSelectionne = null;
				}
			}
		}

		else if (o instanceof Rectangle && dominoSelectionne != null)
		{
			Rectangle r = (Rectangle)o;

			/** On récupère le centre de la case selectionné **/
			double newX =  r.getX() + tmpX;
			double newY =  r.getY() + tmpY;

			/** Temps de l'animation **/
			int tps = 200;

			dominoSelectionne.switchSelected();

			Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                    new KeyFrame(new Duration(tps),
                            new KeyValue(dominoSelectionne.xProperty(), newX),
                            new KeyValue(dominoSelectionne.yProperty(), newY),
                            new KeyValue(dominoSelectionne.fillProperty(), dominoSelectionne.getImg())
                    ));
            timeline.play();

			dominoSelectionne.setPivotX(newX + (tailleCase/2));
			dominoSelectionne.setPivotY(newY + (tailleCase/2));
			if (dominoSelectionne.getCptRotation() == 2){
				dominoSelectionne.setX(newX);
				dominoSelectionne.setY(newY);
			} else
			{
				dominoSelectionne.setX(newX);
				dominoSelectionne.setY(newY);
			}

			dominoSelectionne = null;
		}
	}
}
