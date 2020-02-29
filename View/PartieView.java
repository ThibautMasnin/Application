package Application.View;

import Application.Controller.PartieController;
import Application.Model.Deck;
import Application.Model.DominoModel;
import Application.Model.Grille;
import javafx.animation.Timeline;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PartieView implements EventHandler<ActionEvent> {
	private DominoModel dominoTMP;
	private Deck deck;
	private DominoModel d1;
	private DominoModel d2;
	private DominoModel d3;
	private DominoModel d4;
	private DominoModel d5;
	private DominoModel d6;
	private DominoModel d7;
	private DominoModel d8;
	private Group zoneJeu;
	private Grille grille;
	private int cpt;


	public PartieView(Stage partieStage) {
		partieStage.setTitle("Partie en cours");

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

//		Timeline timeline = new Timeline(new KeyFrame(
//				Duration.millis(2500),
//				ae -> doSomething()));
//		timeline.play();

		hbox2.getChildren().addAll(lJoueur, lTempsTour);
		hbox2.setAlignment(Pos.CENTER);
		bpMenu.setCenter(hbox2);

		HBox hbox3 = new HBox();
		hbox3.setPadding(new Insets(10));
		hbox3.setSpacing(25);
		hbox3.setMinWidth(600);
		Button bSauvegarder = new Button("Sauvegarder");
		Button bReglement = new Button("Réglement");
		bReglement.setOnAction(e->{
			PartieController partieController = new PartieController(partieStage);
			partieController.handle(e);
		});

		Button bReglage = new Button("Réglages");
		bReglage.setOnAction(e->{
			PartieController partieController = new PartieController(partieStage);
			partieController.handle(e);
		});

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
		bDemarrer.setMaxSize(110,20);

		Button bTrier = new Button("Trier");
		bTrier.setMaxSize(110,20);

		Button bReset= new Button("Reset");
		bReset.setMaxSize(110,20);

		vActionDomino.getChildren().addAll(bRotateDroit, bRotateGauche, bAnnulerCoup, bFinirTour, bRetourner, bDemarrer, bTrier, bReset);
		vActionDomino.setAlignment(Pos.CENTER);

		VBox vDetailPartie = new VBox();
		vDetailPartie.setMinWidth(210);


		/** PLATEAU DES JOUEURS **/
		// JOUEUR 1
		grille = new Grille(50, 25, 10, 10, Color.RED, "Application/Ressources/Dominos/C1.jpg");
		grille.dessinerGrille();

		// JOUEUR 2
		Grille grille2 = new Grille(50, 25, 10, 10, Color.BLUE, "Application/Ressources/Dominos/C2.jpg");
		grille2.dessinerGrille();

		// JOUEUR 3
		Grille grille3 = new Grille(50, 25, 10, 10, Color.YELLOW, "Application/Ressources/Dominos/C3.jpg");
		grille3.dessinerGrille();

		// JOUEUR 4
		Grille grille4 = new Grille(50, 25, 10, 10, Color.GREEN, "Application/Ressources/Dominos/C4.jpg");
		grille4.dessinerGrille();

		deck = new Deck();
		//dominoTMP = new Domino(0, 0, 50, 100, "Application/Ressources/Dominos/D1.jpg");
		d1 = new DominoModel(500, 300, 100, 50);
		d2 = new DominoModel(500, 400, 100, 50);
		d3 = new DominoModel(500, 500, 100, 50);
		d4 = new DominoModel(500, 600, 100, 50);
		d5 = new DominoModel(650, 300, 100, 50);
		d6 = new DominoModel(650, 400, 100, 50);
		d7 = new DominoModel(650, 500, 100, 50);
		d8 = new DominoModel(650, 600, 100, 50);
		dominoTMP = d1;

		//dominoTMP.setOnMouseClicked(grille);
		//d1.setOnMouseClicked(grille);
		d2.setOnMouseClicked(grille);
		d3.setOnMouseClicked(grille);
		d4.setOnMouseClicked(grille);
		d5.setOnMouseClicked(grille);
		d6.setOnMouseClicked(grille);
		d7.setOnMouseClicked(grille);
		d8.setOnMouseClicked(grille);


		zoneJeu = new Group();
		zoneJeu.getChildren().addAll(grille.getPane(), grille2.getPane(), grille3.getPane(), grille4.getPane(), deck, d1, d2, d3, d4, d5, d6, d7, d8);
		grille2.getPane().setLayoutX(800);
		grille3.getPane().setLayoutY(500);
		grille4.getPane().setLayoutX(800);
		grille4.getPane().setLayoutY(500);
		deck.setLayoutX(470);








		/** EVENT DOMINO **/
		bRotateDroit.setOnAction(this);

		bRotateGauche.setOnAction(this);

		bRetourner.setOnAction(this);

		bDemarrer.setOnAction(this);

		bTrier.setOnAction(this);

		bReset.setOnAction(e->{
			partieStage.close();
			PartieView pv = new PartieView(partieStage);
		});

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

	public void setDominoTMP(DominoModel d){
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

			// EVENT TRIER DOMINO
			if (((Button) actionEvent.getSource()).getText() == "Trier") {
				deck.melangerDeck();
			}

			// EVENT JOUER DOMINO
			if (((Button) actionEvent.getSource()).getText() == "Demarrer") {

				DominoModel d1_ = new DominoModel(500, 300, 100, 50, deck.getFirstDomino());
				d1_.setOnMouseClicked(grille);
				deck.getListeDominos().remove(deck.getSize()-1);
				deck.setFill(deck.getFirstDominoD());
				zoneJeu.getChildren().add(d1_);
				dominoTMP = d1_;


				DominoModel d2_ = new DominoModel(500, 400, 100, 50, deck.getFirstDomino());
				d2_.setOnMouseClicked(grille);
				deck.getListeDominos().remove(deck.getSize()-1);
				deck.setFill(deck.getFirstDominoD());
				zoneJeu.getChildren().add(d2_);


				DominoModel d3_ = new DominoModel(500, 500, 100, 50, deck.getFirstDomino());
				d3_.setOnMouseClicked(grille);
				deck.getListeDominos().remove(deck.getSize()-1);
				deck.setFill(deck.getFirstDominoD());
				zoneJeu.getChildren().add(d3_);


				DominoModel d4_ = new DominoModel(500, 600, 100, 50, deck.getFirstDomino());
				d4_.setOnMouseClicked(grille);
				deck.getListeDominos().remove(deck.getSize()-1);
				deck.setFill(deck.getFirstDominoD());
				zoneJeu.getChildren().add(d4_);


				DominoModel d5_ = new DominoModel(650, 300, 100, 50, deck.getFirstDominoD());
				d5_.setOnMouseClicked(grille);
				deck.getListeDominos().remove(deck.getSize()-1);
				deck.setFill(deck.getFirstDominoD());
				zoneJeu.getChildren().add(d5_);


				DominoModel d6_ = new DominoModel(650, 400, 100, 50, deck.getFirstDominoD());
				d6_.setOnMouseClicked(grille);
				deck.getListeDominos().remove(deck.getSize()-1);
				deck.setFill(deck.getFirstDominoD());
				zoneJeu.getChildren().add(d6_);


				DominoModel d7_ = new DominoModel(650, 500, 100, 50, deck.getFirstDominoD());
				d7_.setOnMouseClicked(grille);
				deck.getListeDominos().remove(deck.getSize()-1);
				deck.setFill(deck.getFirstDominoD());
				zoneJeu.getChildren().add(d7_);


				DominoModel d8_ = new DominoModel(650, 600, 100, 50, deck.getFirstDominoD());
				d8_.setOnMouseClicked(grille);
				deck.getListeDominos().remove(deck.getSize()-1);
				System.out.println("###" + deck.getSize());
				if (deck.getSize() > 0)
				{
					deck.setFill(deck.getFirstDominoD());
				} else {
					deck.setFill("Application/Ressources/Dominos/ekekan.jpg");
				}

				zoneJeu.getChildren().add(d8_);
			}

		}
	}
}