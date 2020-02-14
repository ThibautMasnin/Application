package Application.View;

import Application.Controller.PartieController;
import Application.Controller.PartieControllerME;
import Application.Model.Deck;
import Application.Model.Domino;
import Application.Model.Grille;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PartieView implements Observer {
    private Domino dominoTMP;
    private ImageView dmn;
    private double initXCarte;
    private double initYCarte;
    private Point2D dragAnchor;
    private boolean bool;


    public PartieView(Stage partieStage, ImageView i, boolean b) {
        partieStage.setTitle("Partie en cours");

        //dominoTMP = d;
        dmn = i;
        //initXCarte = posX;
        //initYCarte = posY;
        bool = b;


        /** BORDERPANE PRINCIPAL **/
        BorderPane borderPane = new BorderPane();
        Scene root = new Scene(borderPane, 1250, 700);


        /** MENU EN HAUT **/
        BorderPane bpMenu = new BorderPane();

        HBox hbox1 = new HBox();
        hbox1.setPadding(new Insets(10));
        hbox1.setSpacing(25);
        hbox1.setMinWidth(600);

        HBox hbox11 = new HBox();
        hbox11.setSpacing(5);
        Label lTempsPartie = new Label("Temps restants :");
        Label lTempsPartie2 = new Label("27:42");
        hbox11.getChildren().addAll(lTempsPartie, lTempsPartie2);
        hbox11.setAlignment(Pos.CENTER);

        HBox hbox12 = new HBox();
        hbox12.setSpacing(5);
        Label lNbTour = new Label("Nombre de tours restants :");
        Label lNbTour2 = new Label("8");
        hbox12.getChildren().addAll(lNbTour, lNbTour2);
        hbox12.setAlignment(Pos.CENTER);

        hbox1.getChildren().addAll(hbox11, hbox12);
        bpMenu.setLeft(hbox1);
        bpMenu.getLeft().setStyle("-fx-background-color: #80543a;");

        HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(10));
        hbox2.setSpacing(5);
        Label lJoueur = new Label("Joueur 1 :");
        Label lTempsTour = new Label("01:29");
        hbox2.getChildren().addAll(lJoueur, lTempsTour);
        hbox2.setAlignment(Pos.CENTER);
        bpMenu.setCenter(hbox2);

        HBox hbox3 = new HBox();
        hbox3.setPadding(new Insets(10));
        hbox3.setSpacing(25);
        hbox3.setMinWidth(600);
        Button bSauvegarder = new Button("Sauvegarder");
        Button bReglement = new Button("Réglement");
        Button bReglage = new Button("Réglages");
        Button bQuitter = new Button("Quitter");
        hbox3.getChildren().addAll(bSauvegarder, bReglement, bReglage, bQuitter);
        hbox3.setAlignment(Pos.CENTER);
        bpMenu.setRight(hbox3);
        bpMenu.getRight().setStyle("-fx-background-color: #80543a;");


        /** MENU A GAUCHE **/
        VBox vActionDomino = new VBox();
        vActionDomino.setMinWidth(210);
        vActionDomino.setSpacing(50);

        Button bRotateDroit = new Button("Rotation droite");
        bRotateDroit.setMaxSize(110,20);

        Button bRotateGauche = new Button("Rotation gauche");
        bRotateGauche.setMaxSize(110,20);

        Button bAnnulerCoup = new Button("Annuler coup");
        bAnnulerCoup.setMaxSize(110,20);

        Button bFinirTour = new Button("Finir tour");
        bFinirTour.setMaxSize(110,20);

        Button bRetourner = new Button("Retourner");
        bRetourner.setMaxSize(110,20);

        Button bDemarrer = new Button("Demarrer");
        bRetourner.setMaxSize(110,20);

        vActionDomino.getChildren().addAll(bRotateDroit, bRotateGauche, bAnnulerCoup, bFinirTour, bRetourner, bDemarrer);
        vActionDomino.setAlignment(Pos.CENTER);

        VBox vDetailPartie = new VBox();
        vDetailPartie.setMinWidth(210);


        /** PLlateau des joueurs **/

        // JOUEUR 1
        Grille grille = new Grille(50, 25, 10, 10);
        grille.dessinerGrille();
        grille.dessinerBoard();
        //dominoTMP = grille.getDm();

        // JOUEUR 2

        // JOUEUR 3

        // JOUEUR 4

        Group test = new Group();
        test.minWidth(1000);
        test.getChildren().addAll(dmn, grille.getGroupe());

        /*
        // Zone dominos à selectionner
        Group dominoSelection = new Group();
        int j = 0;
        ArrayList<Domino> arrayList = new ArrayList<Domino>();
        for (int i = 0 ; i < 8 ; i++){
            if (i < 4) {
                Domino domino = new Domino(50, 100 * i, 50, 50, "Application/Images/chateau.png");
                dominoSelection.getChildren().add(domino);
                arrayList.add(domino);
            }
            else {
                Domino domino = new Domino(150, 100 * j, 50, 50, "Application/Images/chateau.png");
                dominoSelection.getChildren().add(domino);
                arrayList.add(domino);
                j++;
            }
        }
        */



        /** Event dominos **/
        bRotateDroit.setOnAction(e -> {
            PartieController partieController = new PartieController(partieStage, dmn, bool);
            partieController.handle(e);
        });


        bRotateGauche.setOnAction(e->{
            PartieController partieController = new PartieController(partieStage, dmn, bool);
            partieController.handle(e);
        });

        bRetourner.setOnAction(e->{
            PartieController partieController = new PartieController(partieStage, dmn, bool);
            partieController.handle(e);
        });

        bQuitter.setOnAction(e->{
            PartieController partieController = new PartieController(partieStage);
            partieController.handle(e);
        });


        // Ajout au borderpane principal
        borderPane.setTop(bpMenu);
        borderPane.setLeft(vActionDomino);
        borderPane.getLeft().setStyle("-fx-background-color: #336699;");
        borderPane.setRight(vDetailPartie);
        borderPane.getRight().setStyle("-fx-background-color: #336699;");
        //BorderPane.setAlignment(dominoTMP, Pos.CENTER_RIGHT);
        borderPane.setCenter(test);

        partieStage.setScene(root);
        //partieStage.sizeToScene();
        partieStage.setResizable(false);
        partieStage.setFullScreen(true);
        partieStage.show();



        // DEPLACER LES CARTES DANS LA LIMITE DE LA FENETRE
            // LE CURSEUR CHANGE QUAND ON SE PLACE SUR LA CARTE POUR MONTRER QU'ON PEUT REALISER DES ACTIONS DESSUS
        dmn.setCursor(Cursor.HAND);


            // ON AJOUTE L'OMBRE QUAND ON DEPLACE LA CARTE
        DropShadow dropShadow = new DropShadow();
        dropShadow.setBlurType(BlurType.GAUSSIAN);
        dropShadow.setColor(Color.BLACK);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setRadius(10.0);

            // ON CREE UN GLOW AUTOUR DE LA CARTE QUI MONTRE QU'ON PEUT LA SELECTIONNER
        DropShadow dropShadow2 = new DropShadow();
        dropShadow2.setSpread(0.5);
        dropShadow2.setColor(Color.RED);
        dropShadow2.setOffsetX(0);
        dropShadow2.setOffsetY(0);
        dropShadow2.setRadius(10.0);


        // ON CREE LES DIFFERENTS MOUSE EVENT POUR LA CARTE
            // EVENT DE DRAG
        dmn.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double dragX = mouseEvent.getSceneX() - dragAnchor.getX();
                double dragY = mouseEvent.getSceneY() - dragAnchor.getY();

                double newXPosition = initXCarte + dragX;
                double newYPosition = initYCarte + dragY;

                if ( newXPosition >= -643 && newXPosition <= 643){
                    dmn.setTranslateX(newXPosition);
                }

                if ( newYPosition >= -415 && newYPosition <= 415) {
                    dmn.setTranslateY(newYPosition);
                }

                //imageView.setTranslateX(newXPosition);
                //imageView.setTranslateY(newYPosition);
                System.out.println(newXPosition + " " + newYPosition);
            }
        });

            // QUAND ON RENTRE DANS L'EVENT
        dmn.setOnMouseEntered(mouseEvent -> { dmn.setEffect(dropShadow2); dmn.toFront();});

            // QUAND ON SORT DE L'EVENT
        dmn.setOnMouseExited(mouseEvent -> dmn.setEffect(null));

            // QUAND ON APPUYE SUR LE BOUTON DE LA SOURIS
        dmn.setOnMousePressed(mouseEvent -> {
            dmn.toFront();
            initXCarte = dmn.getTranslateX();
            initYCarte = dmn.getTranslateY();
            dragAnchor = new Point2D(mouseEvent.getSceneX(), mouseEvent.getSceneY());
            dmn.setEffect(dropShadow);
        });

            // QUAND ON RELACHE LE BOUTON DE LA SOURIS
        dmn.setOnMouseReleased(mouseEvent -> dmn.setEffect(null));

    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
