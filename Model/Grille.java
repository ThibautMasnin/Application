package Application.Model;

import Application.Controller.PartieController;
import Application.Controller.PartieControllerME;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Grille implements EventHandler<MouseEvent> {
    private int tailleCase;
    private int decalage;
    private int nbLignes;
    private int nbColonnes;
    private Domino dominoSelectionne;
    private Domino dm;
    private Domino[][] dominos;
    private Line ligne;
    private Rectangle caseGrille;
    private Group groupe;
    private PartieControllerME partieControllerME;


    public Grille(int tc, int d, int l, int c) {
        tailleCase = tc;
        decalage = d;
        nbLignes = l;
        nbColonnes = c;
        dominoSelectionne = null;
        dominos = new Domino[1][6];

        ligne = new Line();
        ligne.setStrokeWidth(1);
        ligne.setStroke(Color.DARKGREY);

        caseGrille = new Rectangle();
        caseGrille.setFill(Color.RED);
        caseGrille.setStroke(Color.TRANSPARENT);

        groupe = new Group();

        partieControllerME = new PartieControllerME();
    }


    /** CREATION DE LA GRILLE **/
    public void dessinerGrille() {

        // CREATION DES LIGNES HORIZONTALES ET VERTICALES
        for (int i = 0; i <= nbLignes-1; i++) {
            for (int j = 0; j <= nbColonnes-1; j++) {

                // LIGNES VERTICALES
                Line ligne = new Line((decalage + tailleCase * j), decalage, (decalage + tailleCase * j), (decalage + 9 * tailleCase));
                ligne.setStrokeWidth(1);
                ligne.setStroke(Color.BLACK);
                groupe.getChildren().add(ligne);

                if (i < nbLignes-1 && j < nbColonnes-1) {
                    Rectangle caseGrille = new Rectangle((decalage + tailleCase * i), (decalage + tailleCase * j), tailleCase, tailleCase);
                    caseGrille.setFill(Color.TRANSPARENT);
                    caseGrille.setStroke(Color.TRANSPARENT);
                    /*caseGrille.setOnMouseClicked(event -> {
                        Application.Controller.PartieControllerME pcME = new PartieControllerME();
                        pcME.handle(event);
                    });*/
                    caseGrille.setOnMouseClicked(this);
                    groupe.getChildren().add(caseGrille);
                }
            }
            // LIGNES HORIZONTALES
            Line ligne = new Line(decalage, (decalage + tailleCase * i), (decalage + 9 * tailleCase), (decalage + tailleCase * i));
            ligne.setStrokeWidth(1);
            ligne.setStroke(Color.BLACK);
            groupe.getChildren().add(ligne);
        }
    }

    // CREATION DU BOARD
    public void dessinerBoard() {
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 6; j++) {
                int x = decalage + j * tailleCase + j * decalage;
                int y = nbLignes * tailleCase + decalage;

                dm = new Domino(x, y, 50, 50, "Application/Images/chateau.png");
                groupe.getChildren().add(dm);
                dm.setSmooth(true);
                dm.setOnMouseClicked(this);
                dominos[i][j] = dm;
            }
        }
    }

    public Domino getDm(){
        return dm;
    }

    public Group getGroupe() {
        return groupe;
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

    public Domino getDominoSelectionne() {
        return dominoSelectionne;
    }


    @Override
    public void handle(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        System.out.println("clic en " + x + "," + y);

        Object o = event.getSource();

        if(o instanceof Domino)
        {
            Domino d = (Domino)o;

            //si on selectionne un nouveau pion ou deselectionne le pion deja selectionne
            if(dominoSelectionne == null || dominoSelectionne == d)
            {
                d.switchSelected();

                if(dominoSelectionne == null) {
                    dominoSelectionne = d;
                    System.out.println("Je suis selectionné");
                }
                else {
                    dominoSelectionne = null;
                }
            }
        }
        else if(o instanceof Rectangle && dominoSelectionne != null)
        {
            Rectangle r = (Rectangle)o;

            //le nouveau centre du jeton sera au centre du rectangle sélectionné
            double newX =  r.getX();
            double newY =  r.getY();

            int tps = 10;

            dominoSelectionne.switchSelected();

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(
                    new KeyFrame(new Duration(tps),
                            new KeyValue(dominoSelectionne.xProperty(), newX),
                            new KeyValue(dominoSelectionne.yProperty(), newY),
                            new KeyValue(dominoSelectionne.fillProperty(), dominoSelectionne.getImg())
                    ));
            timeline.play();
            dominoSelectionne = null;
        }
        System.out.println("fin event");
    }

}
