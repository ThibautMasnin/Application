package Application.Model;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PlateauModel implements EventHandler<MouseEvent> {
	// Les attributs de Plateau
	private int idPlateau;
	private Object[][] plateau;
	private static int nbPlateau = 0;


	private ArrayList<DominoModel> listeDomino;
	protected ArrayList<DominoModel> lesDominosAutour;

	// Les attribut de Grille
	private int tailleCase;
	private int decalage;
	private int nbLignes;
	private int nbColonnes;
	private DominoModel dominoSelectionne;
	private Group groupe;
	private Pane pane;
	private String colorLine;
	private String chateau;

	private int tmpX;
	private int tmpY;

	public PlateauModel() { // Constructeur
		if (nbPlateau < 4) {
			idPlateau = nbPlateau;
			nbPlateau++;
			plateau = new Object[9][9];
			plateau[4][4] = new ChateauModel();
			listeDomino = new ArrayList<>();
//			casePrise = new int[11][2];
		}
	}

	// Constructeur de la view
	public PlateauModel(int tc, int d, int l, int c, String cl, String ch, int x, int y) {
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

	public int getIdPlateau()               // Pour avoir l'ID du tableau
	{
		return this.idPlateau;
	}

	public boolean ajouteDomino(DominoModel e, int x, int y, String sens)              //M�thode pour ajouter un �l�ment au plateau
	{
		if (plateau[x][y] == null) {
			if (sens == "H" && plateau[x - 1][y] == null && emplacementValide(e, x, y, sens)) {
				e.setX1(x);
				e.setY1(y);
				e.setX2(x - 1);
				e.setY2(y);
				e.setSens(sens);
				plateau[x][y] = e.getPaysage1();
				plateau[x - 1][y] = e.getPaysage2();
				listeDomino.add(e);
				return true;
			} else if (sens == "G" && plateau[x][y - 1] == null && emplacementValide(e, x, y, sens)) {
				e.setX1(x);
				e.setY1(y);
				e.setX2(x);
				e.setY2(y - 1);
				e.setSens(sens);
				plateau[x][y] = e.getPaysage1();
				plateau[x][y - 1] = e.getPaysage2();
				listeDomino.add(e);
				return true;
			} else if (sens == "B" && plateau[x + 1][y] == null && emplacementValide(e, x, y, sens)) {
				e.setX1(x);
				e.setY1(y);
				e.setX2(x + 1);
				e.setY2(y);
				e.setSens(sens);
				plateau[x][y] = e.getPaysage1();
				plateau[x + 1][y] = e.getPaysage2();
				listeDomino.add(e);
				return true;
			} else if (sens == "D" && plateau[x][y + 1] == null && emplacementValide(e, x, y, sens)) {
				e.setX1(x);
				e.setY1(y);
				e.setX2(x);
				e.setY2(y + 1);
				e.setSens(sens);
				plateau[x][y] = e.getPaysage1();
				plateau[x][y + 1] = e.getPaysage2();
				listeDomino.add(e);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public Object getElement(int x, int y) {
		return plateau[x][y];
	}

	public void affichePlateau() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (getElement(i, j) != null) {
					if (getElement(i, j) instanceof PaysageModel) {
						DominoModel d = getDomino(i, j);
						if (getElement(i, j) == d.getPaysage1()) {
							System.out.print("D" + d.getNumDomino() + (((PaysageModel) getElement(i, j)).getNomTerrain()) +
									d.getPaysage1().getNbCouronne() + " | ");
						} else if (getElement(i, j) == d.getPaysage2()) {
							System.out.print("D" + d.getNumDomino() + (((PaysageModel) getElement(i, j)).getNomTerrain()) +
									d.getPaysage2().getNbCouronne() + " | ");
						} else {
							System.out.print("D" + d.getNumDomino() + (((PaysageModel) getElement(i, j)).getNomTerrain()) + " | ");
						}
					} else {
						System.out.print(" ____C" + (((ChateauModel) getElement(i, j)).getIdChateau()) + "____ | ");
					}
				} else {
					System.out.print(" __________ | ");
				}
			}
			System.out.println();
		}
	}

	public DominoModel getDomino(int x, int y) {
		DominoModel d = null;
		for (int i = 0; i < listeDomino.size(); i++) {
			if (((listeDomino.get(i).getX1() == x) && (listeDomino.get(i).getY1() == y)
					|| (listeDomino.get(i).getX2() == x) && (listeDomino.get(i).getY2() == y))) {
				d = listeDomino.get(i);
			}
		}
		if (d == null) {
			System.out.println("ERREUR GET DOMINO");
		}
		return d;
	}

	public PaysageModel getPaysage(int x, int y) {
		return (PaysageModel) plateau[x][y];
	}

	public boolean emplacementValide(DominoModel d, int x, int y, String sens) {
//			on regarde si un chateau ou un Domino est présent autour de l'endroit où l'on veut le placer

		return nextToChateau(x, y, sens) || nextToDomino(d, x, y, sens);
	}

	public boolean nextToChateau(int x, int y, String sens) {
		boolean bool = false;
		if (sens == "H") // Domino vers le haut
		{
//			on regarde si un chateau est présent autour de l'endroit où l'on veut le placer
			if ((plateau[x][y - 1] instanceof ChateauModel)
					|| (plateau[x + 1][y] instanceof ChateauModel)
					|| (plateau[x][y + 1] instanceof ChateauModel)
					|| (plateau[x - 1][y + 1] instanceof ChateauModel)
					|| (plateau[x - 2][y] instanceof ChateauModel)
					|| (plateau[x - 1][y - 1] instanceof ChateauModel)) {
				bool = true;
			}
		} else if (sens == "G") {
			if ((plateau[x - 1][y] instanceof ChateauModel)
					|| (plateau[x][y + 1] instanceof ChateauModel)
					|| (plateau[x + 1][y] instanceof ChateauModel)
					|| (plateau[x + 1][y - 1] instanceof ChateauModel)
					|| (plateau[x][y - 2] instanceof ChateauModel)
					|| (plateau[x - 1][y - 1] instanceof ChateauModel)) {
				bool = true;
			}
		} else if (sens == "D") {
			if ((plateau[x - 1][y] instanceof ChateauModel)
					|| (plateau[x][y - 1] instanceof ChateauModel)
					|| (plateau[x + 1][y] instanceof ChateauModel)
					|| (plateau[x + 1][y + 1] instanceof ChateauModel)
					|| (plateau[x][y + 2] instanceof ChateauModel)
					|| (plateau[x + 1][y - 1] instanceof ChateauModel)) {
				bool = true;
			}
		} else if (sens == "B") {
			if ((plateau[x][y - 1] instanceof ChateauModel)
					|| (plateau[x - 1][y] instanceof ChateauModel)
					|| (plateau[x][y + 1] instanceof ChateauModel)
					|| (plateau[x + 1][y + 1] instanceof ChateauModel)
					|| (plateau[x + 2][y] instanceof ChateauModel)
					|| (plateau[x + 1][y - 1] instanceof ChateauModel)) {
				bool = true;
			}
		}
		return bool;
	}

	public boolean nextToDomino(DominoModel d, int x, int y, String sens) {
		String nomPaysage1 = null;
		String nomPaysage2 = null;

		boolean bool = false;
//		COMPARAIIIISON PAYSAAAAGEEE
		if (sens == "H") // Domino vers le haut
		{
			nomPaysage1 = d.getPaysage1().getNomTerrain();
			nomPaysage2 = d.getPaysage2().getNomTerrain();

			if (plateau[x][y - 1] instanceof PaysageModel) {
				if (getPaysage(x, y - 1).getNomTerrain() == nomPaysage1) bool = true;
			} else if (plateau[x + 1][y] instanceof PaysageModel) {
				if (getPaysage(x + 1, y).getNomTerrain() == nomPaysage1) bool = true;
			} else if (plateau[x][y + 1] instanceof PaysageModel) {
				if (getPaysage(x, y + 1).getNomTerrain() == nomPaysage1) bool = true;
			} else if (plateau[x - 1][y + 1] instanceof PaysageModel) {
				if (getPaysage(x - 1, y + 1).getNomTerrain() == nomPaysage2) bool = true;
			} else if (plateau[x - 2][y] instanceof PaysageModel) {
				if (getPaysage(x - 2, y).getNomTerrain() == nomPaysage2) bool = true;
			} else if (plateau[x - 1][y - 1] instanceof PaysageModel) {
				if (getPaysage(x - 1, y - 1).getNomTerrain() == nomPaysage2) bool = true;
			}
		} else if (sens == "G") {
			if (plateau[x - 1][y] instanceof PaysageModel) {
				if (getPaysage(x - 1, y).getNomTerrain() == nomPaysage1) bool = true;
			} else if (plateau[x][y + 1] instanceof PaysageModel) {
				if (getPaysage(x, y + 1).getNomTerrain() == nomPaysage1) bool = true;
			} else if (plateau[x + 1][y] instanceof PaysageModel) {
				if (getPaysage(x + 1, y).getNomTerrain() == nomPaysage1) bool = true;
			} else if (plateau[x + 1][y - 1] instanceof PaysageModel) {
				if (getPaysage(x + 1, y - 1).getNomTerrain() == nomPaysage2) bool = true;
			} else if (plateau[x][y - 2] instanceof PaysageModel) {
				if (getPaysage(x, y - 2).getNomTerrain() == nomPaysage2) bool = true;
			} else if (plateau[x - 1][y - 1] instanceof PaysageModel) {
				if (getPaysage(x - 1, y - 1).getNomTerrain() == nomPaysage2) bool = true;
			}
		} else if (sens == "D") {
			if (plateau[x - 1][y] instanceof PaysageModel) {
				if (getPaysage(x - 1, y).getNomTerrain() == nomPaysage1) bool = true;
			} else if (plateau[x][y - 1] instanceof PaysageModel) {
				if (getPaysage(x, y - 1).getNomTerrain() == nomPaysage1) bool = true;
			} else if (plateau[x + 1][y] instanceof PaysageModel) {
				if (getPaysage(x + 1, y).getNomTerrain() == nomPaysage1) bool = true;
			} else if (plateau[x + 1][y + 1] instanceof PaysageModel) {
				if (getPaysage(x + 1, y + 1).getNomTerrain() == nomPaysage2) bool = true;
			} else if (plateau[x][y + 2] instanceof PaysageModel) {
				if (getPaysage(x, y + 2).getNomTerrain() == nomPaysage2) bool = true;
			} else if (plateau[x - 1][y + 1] instanceof PaysageModel) {
				if (getPaysage(x - 1, y + 1).getNomTerrain() == nomPaysage2) bool = true;
			}
		} else if (sens == "B") {
			if (plateau[x][y - 1] instanceof PaysageModel) {
				if (getPaysage(x, y - 1).getNomTerrain() == nomPaysage1) bool = true;
			} else if (plateau[x - 1][y] instanceof PaysageModel) {
				if (getPaysage(x - 1, y).getNomTerrain() == nomPaysage1) bool = true;
			} else if (plateau[x][y + 1] instanceof PaysageModel) {
				if (getPaysage(x, y + 1).getNomTerrain() == nomPaysage1) bool = true;
			} else if (plateau[x + 1][y + 1] instanceof PaysageModel) {
				if (getPaysage(x + 1, y + 1).getNomTerrain() == nomPaysage2) bool = true;
			} else if (plateau[x + 2][y] instanceof PaysageModel) {
				if (getPaysage(x + 2, y).getNomTerrain() == nomPaysage2) bool = true;
			} else if (plateau[x + 1][y - 1] instanceof PaysageModel) {
				if (getPaysage(x + 1, y - 1).getNomTerrain() == nomPaysage2) bool = true;
			}

		}
		if (bool == true) ;
		return bool;
	}

	@Override
	public void handle(MouseEvent event) {

		Object o = event.getSource();

		if (o instanceof DominoModel) {
			DominoModel d = (DominoModel) o;

			//si on selectionne un nouveau pion ou deselectionne le pion deja selectionne
			if (dominoSelectionne == null || dominoSelectionne == d) {
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
			Rectangle r = (Rectangle) o;

			// On récupère le centre de la case selectionné
			double newX = r.getX() + tmpX;
			double newY = r.getY() + tmpY;

			System.out.println("Position RECTANGLE : (" + newX + " ; " + newY + ")");

			// temps de l'animation
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

			dominoSelectionne.setPivotX(newX + (tailleCase / 2));
			dominoSelectionne.setPivotY(newY + (tailleCase / 2));
			if (dominoSelectionne.getCptRotation() == 2) {
				dominoSelectionne.setX(newX);
				dominoSelectionne.setY(newY);
			} else {
				dominoSelectionne.setX(newX);
				dominoSelectionne.setY(newY);
			}

			dominoSelectionne = null;
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

	/**
	 * CREATION DE LA GRILLE -- Méthode de la view
	 **/
	public void dessinerGrille() {

		/** Creation des lignes verticales et horizontales **/
		for (int i = 0; i <= nbLignes - 1; i++) {
			for (int j = 0; j <= nbColonnes - 1; j++) {

				/** lignes verticales **/
				Line ligne = new Line((tailleCase * j), 0, (tailleCase * j), (9 * tailleCase));
				ligne.setStrokeWidth(1.5);
				ligne.setStroke(Color.WHITE);
				pane.getChildren().add(ligne);

				if (i < nbLignes - 1 && j < nbColonnes - 1) {
					if (i == 4 && j == 4) {
						Rectangle caseGrille = new Rectangle((tailleCase * i), (tailleCase * j), tailleCase, tailleCase);
						caseGrille.setFill(new ImagePattern(new Image(chateau)));
						pane.getChildren().add(caseGrille);
					} else {
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
}