package Application.Model;

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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Grille implements EventHandler<MouseEvent> {
    private int tailleCase;
    private int decalage;
    private int nbLignes;
    private int nbColonnes;
    private Domino dominoSelectionne;
    private Line ligne;
    private Rectangle caseGrille;
    private Group groupe;
    private Pane pane;


    public Grille(int tc, int d, int l, int c) {
        tailleCase = tc;
        decalage = d;
        nbLignes = l;
        nbColonnes = c;
        dominoSelectionne = null;

        ligne = new Line();
        ligne.setStrokeWidth(1);
        ligne.setStroke(Color.DARKGREY);

        caseGrille = new Rectangle();
        caseGrille.setFill(Color.RED);
        caseGrille.setStroke(Color.TRANSPARENT);

        groupe = new Group();
        pane = new Pane();
        //pane.setPrefSize(1000,1000);
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
                pane.getChildren().add(ligne);

                if (i < nbLignes-1 && j < nbColonnes-1) {
                    if (i == 4 && j == 4){
                        Rectangle caseGrille = new Rectangle((decalage + tailleCase * i), (decalage + tailleCase * j), tailleCase, tailleCase);
                        caseGrille.setFill(new ImagePattern(new Image("Application/Ressources/Dominos/C1.jpg")));
                        pane.getChildren().add(caseGrille);
                    }
                    else {
                        Rectangle caseGrille = new Rectangle((decalage + tailleCase * i), (decalage + tailleCase * j), tailleCase, tailleCase);
                        caseGrille.setFill(Color.TRANSPARENT);
                        caseGrille.setStroke(Color.TRANSPARENT);
                        caseGrille.setOnMouseClicked(this);
                        pane.getChildren().add(caseGrille);
                    }
                }
            }
            // LIGNES HORIZONTALES
            Line ligne = new Line(decalage, (decalage + tailleCase * i), (decalage + 9 * tailleCase), (decalage + tailleCase * i));
            ligne.setStrokeWidth(1);
            ligne.setStroke(Color.BLACK);
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
        System.out.println();
    }
}
