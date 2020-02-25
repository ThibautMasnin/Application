package Application.View;

import Application.Model.Domino;
import Application.Model.Grille;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class PartieView implements EventHandler<ActionEvent> {
    private Domino dominoTMP;
    private int cpt;


    public PartieView(Stage partieStage) {
        partieStage.setTitle("Partie en cours");

        //dominoTMP = new Domino(0, 0, 50, 100, "Application/Ressources/Dominos/D1.jpg");
        Domino d1 = new Domino(-50, 125, 50, 100, "Application/Ressources/Dominos/D1.jpg");
        Domino d2 = new Domino(-150, 125, 50, 100, "Application/Ressources/Dominos/D2.jpg");
        Domino d3 = new Domino(-250, 125, 50, 100, "Application/Ressources/Dominos/D3.jpg");
        Domino d4 = new Domino(-350, 125, 50, 100, "Application/Ressources/Dominos/D4.jpg");
        Domino d5 = new Domino(-50, 275, 50, 100, "Application/Ressources/Dominos/D5.jpg");
        Domino d6 = new Domino(-150, 275, 50, 100, "Application/Ressources/Dominos/D6.jpg");
        Domino d7 = new Domino(-250, 275, 50, 100, "Application/Ressources/Dominos/D7.jpg");
        Domino d8 = new Domino(-350, 275, 50, 100, "Application/Ressources/Dominos/D8.jpg");

        cpt = 0;

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


        /** PLATEAU DES JOUEURS **/
        // JOUEUR 1
        Grille grille = new Grille(50, 25, 10, 10);
        grille.dessinerGrille();
        //dominoTMP.setOnMouseClicked(grille);
        d1.setOnMouseClicked(grille);
        d2.setOnMouseClicked(grille);
        d3.setOnMouseClicked(grille);
        d4.setOnMouseClicked(grille);
        d5.setOnMouseClicked(grille);
        d6.setOnMouseClicked(grille);
        d7.setOnMouseClicked(grille);
        d8.setOnMouseClicked(grille);


        // JOUEUR 2

        // JOUEUR 3

        // JOUEUR 4

        Group zoneJeu = new Group();
        grille.setPane(d1);
        grille.setPane(d2);
        grille.setPane(d3);
        grille.setPane(d4);
        grille.setPane(d5);
        grille.setPane(d6);
        grille.setPane(d7);
        grille.setPane(d8);
        zoneJeu.getChildren().add(grille.getPane());

        dominoTMP = d1;


        /** EVENT DOMINO **/
        bRotateDroit.setOnAction(this);

        bRotateGauche.setOnAction(this);

        bRetourner.setOnAction(this);

        bQuitter.setOnAction(e->{
            partieStage.close();
        });


        /** AJOUT AU BORDERPANE PRINCIPALE **/
        borderPane.setTop(bpMenu);
        borderPane.setLeft(vActionDomino);
        borderPane.getLeft().setStyle("-fx-background-color: #336699;");
        borderPane.setRight(vDetailPartie);
        borderPane.getRight().setStyle("-fx-background-color: #336699;");
        //BorderPane.setAlignment(dominoTMP, Pos.CENTER_RIGHT);
        borderPane.setCenter(zoneJeu);

        partieStage.setScene(root);
        //partieStage.sizeToScene();
        partieStage.setResizable(false);
        //partieStage.setMaximized(true);
        partieStage.setFullScreen(true);
        partieStage.show();


        /** LE CURSEUR CHANGE QUAND ON SE PLACE SUR LA CARTE POUR MONTRER QU'ON PEUT REALISER DES ACTIONS DESSUS **/
        dominoTMP.setCursor(Cursor.HAND);

    }

    public void setDominoTMP(Domino d){
        dominoTMP = d;
    }

    public int getCpt(){
        return cpt;
    }



    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof Button) {

            // EVENT ROTATION A DROITE
            if (((Button) actionEvent.getSource()).getText() == "Rotation droite" && dominoTMP.isSelected()) {
                Rotate rotate = new Rotate(90,dominoTMP.getPivotX(), dominoTMP.getPivotY());
                dominoTMP.getTransforms().add(rotate);
                cpt += 1;
                dominoTMP.setCptRotation(cpt);



                if (cpt == 1){
                    dominoTMP.setPivotTX(dominoTMP.getX() + 50);
                    dominoTMP.setPivotTY(dominoTMP.getY());
                    System.out.println("Position DOMINO : (" + dominoTMP.getX() + " ; " + dominoTMP.getY() + ")");
                    System.out.println("Position DOMINO : (" + dominoTMP.getPivotTX() + " ; " + dominoTMP.getPivotTY() + ")");
                    System.out.println();
                }
                else if (cpt == 2){
                    //dominoTMP.setPivotTX(dominoTMP.getX());
                    dominoTMP.setPivotTY(dominoTMP.getY() + 50);
                    //System.out.println("Position DOMINO : (" + dominoTMP.getX() + " ; " + dominoTMP.getY() + ")");
                    System.out.println("Position DOMINO : (" + dominoTMP.getPivotTX() + " ; " + dominoTMP.getPivotTY() + ")");
                    System.out.println();
                }
                else if (cpt == 3){
                    dominoTMP.setPivotTX(dominoTMP.getPivotTX() - 50);
                    //dominoTMP.setPivotTY(dominoTMP.getY());
                    //System.out.println("Position DOMINO : (" + dominoTMP.getX() + " ; " + dominoTMP.getY() + ")");
                    System.out.println("Position DOMINO : (" + dominoTMP.getPivotTX() + " ; " + dominoTMP.getPivotTY() + ")");
                    System.out.println();
                }
                else if (cpt == 4){
                    //dominoTMP.setPivotTX(dominoTMP.getX());
                    dominoTMP.setPivotTY(dominoTMP.getPivotTY() - 50);
                    //System.out.println("Position DOMINO : (" + dominoTMP.getX() + " ; " + dominoTMP.getY() + ")");
                    System.out.println("Position DOMINO : (" + dominoTMP.getPivotTX() + " ; " + dominoTMP.getPivotTY() + ")");
                    System.out.println();
                }


                //dominoTMP.setRotate(dominoTMP.getRotate() + 90);
            }

            // EVENT ROTATION A GAUCHE
            if (((Button) actionEvent.getSource()).getText() == "Rotation gauche" && dominoTMP.isSelected()) {
                Rotate rotate = new Rotate(-90,dominoTMP.getPivotX(), dominoTMP.getPivotY());
                dominoTMP.getTransforms().add(rotate);
                cpt += 1;
                dominoTMP.setCptRotation(cpt);
            }

            // EVENT ANNULER SON COUP
            if (((Button) actionEvent.getSource()).getText() == "Annuler coup") {
            }

            // EVENT FINIR SON TOUR
            if (((Button) actionEvent.getSource()).getText() == "Finir tour") {
            }

            // EVENT RETOURNER DOMINO
            if (((Button) actionEvent.getSource()).getText() == "Retourner") {
            }

        }
    }
}
