package Application.Model;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Grille implements EventHandler<MouseEvent> {
	private int tailleCase;
	private int decalage;
	private int nbLignes;
	private int nbColonnes;
	private DominoModel dominoSelectionne;
	private Line ligne;
	private Rectangle caseGrille;
	private Group groupe;
	private Pane pane;
	private Paint colorLine;
	private String chateau;


	public Grille(int tc, int d, int l, int c, Paint cl, String ch) {
		tailleCase = tc;
		decalage = d;
		nbLignes = l;
		nbColonnes = c;
		dominoSelectionne = null;
		colorLine = cl;
		chateau = ch;

//		ligne = new Line();
//		ligne.setStrokeWidth(1);
//		ligne.setStroke(Color.DARKGREY);

//		caseGrille = new Rectangle();
//		caseGrille.setFill(Color.RED);
//		caseGrille.setStroke(Color.TRANSPARENT);

		groupe = new Group();
		pane = new Pane();
		pane.setStyle("-fx-background-image: url('Application/Ressources/Images/wallpaper.png');" +
				"-fx-background-position: center center;" +
				"-fx-background-size: cover;");
		//pane.setPrefSize(1000,1000);
	}


	/** CREATION DE LA GRILLE **/
	public void dessinerGrille() {

		// CREATION DES LIGNES HORIZONTALES ET VERTICALES
		for (int i = 0; i <= nbLignes-1; i++) {
			for (int j = 0; j <= nbColonnes-1; j++) {

				// LIGNES VERTICALES
				Line ligne = new Line((tailleCase * j), 0, (tailleCase * j), (9 * tailleCase));
				ligne.setStrokeWidth(1.5);
				ligne.setStroke(colorLine);
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
						caseGrille.setOnMouseClicked(this);
						pane.getChildren().add(caseGrille);
					}
				}
			}

			// LIGNES HORIZONTALES
			Line ligne = new Line(0, (tailleCase * i), (9 * tailleCase), (tailleCase * i));
			ligne.setStrokeWidth(1.5);
			ligne.setStroke(colorLine);
			pane.getChildren().add(ligne);
		}
	}


	public Group getGroupe() {
		return groupe;
	}

	public Pane getPane() {
		return pane;
	}

	public void setGroupe(Node e) {
		groupe.getChildren().add(e);
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
		@SuppressWarnings("unused")
		double x = event.getX();
		@SuppressWarnings("unused")
		double y = event.getY();

		//System.out.println("clic en " + x + "," + y);

		Object o = event.getSource();

		if(o instanceof DominoModel)
		{
			DominoModel d = (DominoModel)o;

			//si on selectionne un nouveau pion ou deselectionne le pion deja selectionne
			if(dominoSelectionne == null || dominoSelectionne == d)
			{
				d.switchSelected();

				if(dominoSelectionne == null) {
					dominoSelectionne = d;
					System.out.println("Le domino est selectionné");
				}
				else {
					dominoSelectionne = null;
				}
			}
		}
		else if(o instanceof Rectangle && dominoSelectionne != null)
		{
			Rectangle r = (Rectangle)o;

			// On récupère le centre de la case selectionné
			double newX =  r.getX();
			double newY =  r.getY();

			System.out.println("Position RECTANGLE : (" + newX + " ; " + newY + ")");

			// temps de l'animation
			int tps = 1000;

			dominoSelectionne.switchSelected();

			//            if (dominoSelectionne.getCptRotation() == 2){
			//                dominoSelectionne.setPivotTX(dominoSelectionne.getX() - 50);
			//                dominoSelectionne.setPivotTY(dominoSelectionne.getY() - 100);
			//            }
			//
			//            dominoSelectionne.xProperty().set(dominoSelectionne.getPivotTX());
			//            dominoSelectionne.yProperty().set(dominoSelectionne.getPivotTY());

			/*Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                    new KeyFrame(new Duration(tps),
                            new KeyValue(dominoSelectionne.xProperty(), newX),
                            new KeyValue(dominoSelectionne.yProperty(), newY),
                            new KeyValue(dominoSelectionne.fillProperty(), dominoSelectionne.getImg())
                    ));
            timeline.play();*/

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

			//            dominoSelectionne.setPivotTX(newX);
			//            dominoSelectionne.setPivotTY(newY);

			System.out.println("Compteur : " + dominoSelectionne.getCptRotation());
			System.out.println("Position DOMINO : (" + dominoSelectionne.getX() + " ; " + dominoSelectionne.getY() + ")");
			System.out.println("Position DOMINO2 : (" + dominoSelectionne.xProperty().get() + " ; " + dominoSelectionne.yProperty().get() + ")");

			/** Position du pivot**/
			Circle circle1 = new Circle(dominoSelectionne.getX(), dominoSelectionne.getY(), 5);
			circle1.setFill(Color.RED);

			Circle circle2 = new Circle(dominoSelectionne.getX() + 50, dominoSelectionne.getY(), 5);
			circle2.setFill(Color.GREEN);

			Circle circle3 = new Circle(dominoSelectionne.getX() + 50, dominoSelectionne.getY() + 100, 5);
			circle3.setFill(Color.BLUE);

			Circle circle4 = new Circle(dominoSelectionne.getX(), dominoSelectionne.getY() + 100, 5);
			circle4.setFill(Color.BLACK);

			Circle circle5 = new Circle(dominoSelectionne.getPivotTX(), dominoSelectionne.getPivotTY(), 5);
			circle5.setFill(Color.YELLOW);

			//pane.getChildren().addAll(circle1, circle2, circle3, circle4, circle5);
			dominoSelectionne = null;
		}




		System.out.println("Fin d'event");
		System.out.println();
	}
}
