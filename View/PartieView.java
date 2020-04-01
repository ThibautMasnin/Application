package Application.View;

import Application.Controller.PartieController;
import Application.Model.DominoModel;
import Application.Model.PiocheModel;
import Application.Model.PlateauModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.*;
import org.postgresql.ds.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class PartieView implements EventHandler<ActionEvent> {
	private DominoModel dominoTMP;
	private PiocheModel pioche;
	private DominoModel d1;
	private DominoModel d2;
	private DominoModel d3;
	private DominoModel d4;
	private DominoModel d5;
	private DominoModel d6;
	private DominoModel d7;
	private DominoModel d8;
	private Group zoneJeu;
	private PlateauModel grille;
	private int cpt;
	
	private int minChrono;
	private int secChrono;
	private int minTemps;
	private int secTemps;
	private int minChronoParametre=1;
	private int secChronoParametre=15;
	private int joueur=1;
	private int nbJoueurs = 4;
	private int nbTour=2;
	private ArrayList<DominoModel> l1;
	private ArrayList<DominoModel> l2;


	public PartieView(Stage partieStage) throws SQLException{
		


		minChrono=minChronoParametre;
		secChrono=secChronoParametre;
		minTemps = nbJoueurs*minChrono*nbTour+secChrono*nbJoueurs*nbTour/60;
		secTemps = secChrono*nbJoueurs*nbTour%60;

		cpt = 0;

		/** BORDERPANE PRINCIPAL **/
		BorderPane bp = new BorderPane();
		bp.setStyle("-fx-background-image: url('Application/Ressources/Images/wallpaper.png');" +
				"-fx-background-position: center center;" +
				"-fx-background-size: cover;");
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		Scene scene = new Scene(bp, screenBounds.getWidth()-20, screenBounds.getHeight()-80); 


		/** MENU EN HAUT **/
		BorderPane bpMenu = new BorderPane();
		

		HBox hbox1 = new HBox();
		hbox1.setSpacing(50);
		hbox1.setMinWidth(600);

		Label lTempsPartie = new Label();
		Label lNbTour = new Label();
		Timeline timelinePartie = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(secTemps>0) {
					secTemps--;					
				}
				else if(secTemps==0) {
					if(minTemps>0) {
						minTemps--;	
						secTemps=59;				
					}
					else if(minTemps==0) {
					}
					else {
						System.out.println("Erreur dans les minutes du chrono");
					}
				}
				else {
					System.out.println("Erreur dans les secondes du chrono");
				}
				lTempsPartie.setText("Temps restants  :  " + minTemps + ":" + secTemps);
				lNbTour.setText("Tours restants : " + nbTour);
			}
		}));
		timelinePartie.setCycleCount(Animation.INDEFINITE);
		timelinePartie.play();
		
		

		lTempsPartie.setTextFill(Color.web("#ffffff"));
		lTempsPartie.setFont(new Font("Viner Hand ITC", 24));
		lNbTour.setTextFill(Color.web("#ffffff"));
		lNbTour.setFont(new Font("Viner Hand ITC", 24));

		hbox1.getChildren().addAll(lTempsPartie, lNbTour);
		hbox1.setAlignment(Pos.CENTER);
		bpMenu.setLeft(hbox1);

		HBox hbox2 = new HBox();
		hbox2.setPadding(new Insets(-40, 0, 0, 0));
		hbox2.setSpacing(25);
		Label lTempsJoueur = new Label();
		Timeline timelineJoueur = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(nbTour>0) {	
					if(secChrono>0) {
						secChrono--;					
					}
					else if(secChrono==0) {
						if(minChrono>0) {
							minChrono--;	
							secChrono=59;				
						}
						else if(minChrono==0) {
							if(nbTour!=0) {
								if(secTemps-secChrono<0) {
									secTemps=60+secTemps-secChrono;
									minTemps--;
								}
								else {
									secTemps-=secChrono;					
								}
								minTemps-=minChrono;
								secTemps=59;		
								if(joueur==nbJoueurs) {
									joueur=1;
									nbTour--;
								}
								else {
									joueur++;
								}
								if(nbTour!=0) {
									minChrono=minChronoParametre;	
									secChrono=secChronoParametre;					
								}			
							}
						}
						else {
							System.out.println("Erreur dans les minutes du chrono");
						}
					}
					else {
						System.out.println("Erreur dans les secondes du chrono");
					}
				}
				else {
					System.out.println("0");
					minChrono=0;
					secChrono=0;
					minTemps=0;
					secTemps=0;
				}
				lTempsJoueur.setText("Joueur " + joueur + "  :  " + minChrono + ":" + secChrono);
			}
		}));
		timelineJoueur.setCycleCount(Animation.INDEFINITE);
		timelineJoueur.play();
		lTempsJoueur.setTextFill(Color.web("#ffffff"));
		lTempsJoueur.setFont(new Font("Viner Hand ITC", 24));

//		Timeline timeline = new Timeline(new KeyFrame(
//				Duration.millis(2500),
//				ae -> doSomething()));
//		timeline.play();

		hbox2.getChildren().addAll(lTempsJoueur);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setMinWidth(650);	
		hbox2.setMaxWidth(650);	
		hbox2.setMinHeight(90);		
		hbox2.setStyle("-fx-background-image: url('Application/Ressources/Images/planche.png');" +
				"-fx-background-position: center center;");
		bpMenu.setCenter(hbox2);

		HBox hbox3 = new HBox();
		hbox3.setSpacing(20);
		hbox3.setMinWidth(600);		
		
		/** BOUTON SAUVEGARDER **/
		Button btnSauvegarder = new Button();
		btnSauvegarder.setId("Sauvegarder");
		btnSauvegarder.setStyle("-fx-background-image: url('Application/Ressources/Images/sauvegarder.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);" +
				"-fx-background-size: 100%");
		btnSauvegarder.setMinWidth(282/2);
		btnSauvegarder.setMinHeight(51/2);
		btnSauvegarder.setOnAction(new PartieController<ActionEvent>(partieStage));

		/** BOUTON REGLEMENT **/
		Button btnReglement = new Button();
		btnReglement.setId("Reglement");
		btnReglement.setStyle("-fx-background-image: url('Application/Ressources/Images/reglement.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);" +
				"-fx-background-size: 100%");
		btnReglement.setMinWidth(266/2);
		btnReglement.setMinHeight(54/2);
        btnReglement.setOnAction(new PartieController<ActionEvent>(partieStage));

		/** BOUTON REGLAGES **/
		Button btnReglages = new Button();
		btnReglages.setId("Reglages");
		btnReglages.setStyle("-fx-background-image: url('Application/Ressources/Images/reglages2.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);" +
				"-fx-background-size: 100%");
		btnReglages.setMinWidth(245/2);
		btnReglages.setMinHeight(54/2);
		btnReglages.setOnAction(new PartieController<ActionEvent>(partieStage));

		/** BOUTON QUITTER **/
		Button btnQuitter = new Button();
		btnQuitter.setId("Quitter");
		btnQuitter.setStyle("-fx-background-image: url('Application/Ressources/Images/quitter.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);" +
				"-fx-background-size: 100%");
		btnQuitter.setMinWidth(245/2);
		btnQuitter.setMinHeight(56/2);
		btnQuitter.setOnAction(new PartieController<ActionEvent>(partieStage));
		
		hbox3.getChildren().addAll(btnSauvegarder, btnReglement, btnReglages, btnQuitter);
		hbox3.setAlignment(Pos.CENTER);
		bpMenu.setRight(hbox3);
		bpMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75);-fx-border-color: #000000; -fx-border-width: 0 0 1 0;");


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

		Button bMelanger = new Button("Melanger");
		bMelanger.setMaxSize(110,20);

		Button bDemarrer = new Button("Demarrer");
		bDemarrer.setMaxSize(110,20);

		/*
		Button bTrier = new Button("Trier");
		bTrier.setMaxSize(110,20);
		*/

		Button bReset= new Button("Reset");
		bReset.setMaxSize(110,20);

		vActionDomino.getChildren().addAll(bRotateDroit, bRotateGauche, bAnnulerCoup, bFinirTour, bRetourner, bMelanger, bDemarrer/*, bTrier*/, bReset);
		vActionDomino.setAlignment(Pos.CENTER);
		vActionDomino.setStyle("-fx-background-color: rgba(0, 0, 0, 0.25);-fx-border-color: #000000; -fx-border-width: 0 1 0 0;");

		VBox vDetailPartie = new VBox();
		vDetailPartie.setMinWidth(210);
		vDetailPartie.setStyle("-fx-background-color: rgba(0, 0, 0, 0.25);-fx-border-color: #000000; -fx-border-width: 0 0 0 1;");


		/** PLATEAU DES JOUEURS **/
		// JOUEUR 1
		grille = new PlateauModel(50, 25, 10, 10, "-fx-background-color: rgba(240, 0, 0, 0.45);", "Application/Ressources/Dominos/C1.jpg");
		grille.dessinerGrille();

		// JOUEUR 2
		PlateauModel grille2 = new PlateauModel(50, 25, 10, 10, "-fx-background-color: rgba(0, 0, 170, 0.28);", "Application/Ressources/Dominos/C2.jpg");
		grille2.dessinerGrille();

		// JOUEUR 3
		PlateauModel grille3 = new PlateauModel(50, 25, 10, 10, "-fx-background-color: rgba(250, 210, 0, 0.43);", "Application/Ressources/Dominos/C3.jpg");
		grille3.dessinerGrille();

		// JOUEUR 4
		PlateauModel grille4 = new PlateauModel(50, 25, 10, 10, "-fx-background-color: rgba(0, 175, 0, 0.35);", "Application/Ressources/Dominos/C4.jpg");
		grille4.dessinerGrille();

		pioche = new PiocheModel();
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
		//d2.setOnMouseClicked(grille);
		//d3.setOnMouseClicked(grille);
		//d4.setOnMouseClicked(grille);
		//d5.setOnMouseClicked(grille);
		//d6.setOnMouseClicked(grille);
		//d7.setOnMouseClicked(grille);
		//d8.setOnMouseClicked(grille);


		zoneJeu = new Group();
		zoneJeu.setStyle("-fx-background-color: #336699;");
		zoneJeu.getChildren().addAll(grille.getPane(), grille2.getPane(), grille3.getPane(), grille4.getPane(), pioche, d1, d2, d3, d4, d5, d6, d7, d8);
		grille2.getPane().setLayoutX(800);
		grille3.getPane().setLayoutY(500);
		grille4.getPane().setLayoutX(800);
		grille4.getPane().setLayoutY(500);
		pioche.setLayoutX(470);




		/** EVENT DOMINO **/
		bRotateDroit.setOnAction(this);

		bRotateGauche.setOnAction(this);

		bAnnulerCoup.setOnAction(this);

		bFinirTour.setOnAction(this);

		bRetourner.setOnAction(this);

		bMelanger.setOnAction(this);

		bDemarrer.setOnAction(this);

		//bTrier.setOnAction(this);

		bReset.setOnAction(e->{
			partieStage.close();
			try {
				PartieView pv = new PartieView(partieStage);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		bFinirTour.setOnAction(this);

		/** AJOUT AU BORDERPANE PRINCIPALE **/
		bp.setTop(bpMenu);
		bp.setLeft(vActionDomino);
		bp.setRight(vDetailPartie);
		bp.setCenter(zoneJeu);


		partieStage.setScene(scene);
		partieStage.setResizable(false);
		partieStage.setFullScreen(true);
		partieStage.setFullScreenExitHint("");
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
				Rotate rotate = new Rotate(90, dominoTMP.getPivotX(), dominoTMP.getPivotY());
				dominoTMP.getTransforms().add(rotate);
				cpt += 1;
				dominoTMP.setCptRotation(cpt);


				if (cpt == 1) {
					dominoTMP.setPivotTX(dominoTMP.getX() + 50);
					dominoTMP.setPivotTY(dominoTMP.getY());
					System.out.println("Position DOMINO : (" + dominoTMP.getX() + " ; " + dominoTMP.getY() + ")");
					System.out.println("Position DOMINO : (" + dominoTMP.getPivotTX() + " ; " + dominoTMP.getPivotTY() + ")");
					System.out.println();
				} else if (cpt == 2) {
					//dominoTMP.setPivotTX(dominoTMP.getX());
					dominoTMP.setPivotTY(dominoTMP.getY() + 50);
					//System.out.println("Position DOMINO : (" + dominoTMP.getX() + " ; " + dominoTMP.getY() + ")");
					System.out.println("Position DOMINO : (" + dominoTMP.getPivotTX() + " ; " + dominoTMP.getPivotTY() + ")");
					System.out.println();
				} else if (cpt == 3) {
					dominoTMP.setPivotTX(dominoTMP.getPivotTX() - 50);
					//dominoTMP.setPivotTY(dominoTMP.getY());
					//System.out.println("Position DOMINO : (" + dominoTMP.getX() + " ; " + dominoTMP.getY() + ")");
					System.out.println("Position DOMINO : (" + dominoTMP.getPivotTX() + " ; " + dominoTMP.getPivotTY() + ")");
					System.out.println();
				} else if (cpt == 4) {
					//dominoTMP.setPivotTX(dominoTMP.getX());
					dominoTMP.setPivotTY(dominoTMP.getPivotTY() - 50);
					//System.out.println("Position DOMINO : (" + dominoTMP.getX() + " ; " + dominoTMP.getY() + ")");
					System.out.println("Position DOMINO : (" + dominoTMP.getPivotTX() + " ; " + dominoTMP.getPivotTY() + ")");
					System.out.println();
				}


				//dominoTMP.setRotate(dominoTMP.getRotate() + 90);
			}

			// EVENT ROTATION A GAUCHE
			else if (((Button) actionEvent.getSource()).getText() == "Rotation gauche" && dominoTMP.isSelected()) {
				Rotate rotate = new Rotate(-90, dominoTMP.getPivotX(), dominoTMP.getPivotY());
				dominoTMP.getTransforms().add(rotate);
				cpt += 1;
				dominoTMP.setCptRotation(cpt);
			}

			// EVENT ANNULER SON COUP
			else if (((Button) actionEvent.getSource()).getText() == "Annuler coup") {
			}

			// EVENT FINIR SON TOUR
			else if (((Button) actionEvent.getSource()).getText() == "Finir tour") {
				if (nbTour > 0) {
					if (secTemps - secChrono < 0) {
						secTemps = 60 + secTemps - secChrono;
						minTemps--;
					} else {
						secTemps -= secChrono;
					}
					minTemps -= minChrono;
					if (joueur == nbJoueurs) {
						joueur = 1;
						nbTour--;
					} else {
						joueur++;
					}
					if (nbTour != 0) {
						minChrono = minChronoParametre;
						secChrono = secChronoParametre;
					}
				}
			}

			// EVENT RETOURNER DOMINO
			else if (((Button) actionEvent.getSource()).getText() == "Retourner") {
			}

			// EVENT MELANGER DOMINO
			else if (((Button) actionEvent.getSource()).getText() == "Melanger") {
				pioche.melangerPioche();
			}

			// EVENT JOUER DOMINO
			else if (((Button) actionEvent.getSource()).getText() == "Demarrer") {

				DominoModel d1_ = new DominoModel(500, 300, 100, 50, pioche.getFirstDomino(), pioche.getNumFirstDomino());
				d1_.setOnMouseClicked(grille);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d1_);
				dominoTMP = d1_;


				DominoModel d2_ = new DominoModel(500, 400, 100, 50, pioche.getFirstDomino(), pioche.getNumFirstDomino());
				d2_.setOnMouseClicked(grille);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d2_);


				DominoModel d3_ = new DominoModel(500, 500, 100, 50, pioche.getFirstDomino(), pioche.getNumFirstDomino());
				d3_.setOnMouseClicked(grille);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d3_);


				DominoModel d4_ = new DominoModel(500, 600, 100, 50, pioche.getFirstDomino(), pioche.getNumFirstDomino());
				d4_.setOnMouseClicked(grille);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d4_);


				DominoModel d5_ = new DominoModel(650, 300, 100, 50, pioche.getFirstDominoD(), pioche.getNumFirstDomino());
				d5_.setOnMouseClicked(grille);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d5_);


				DominoModel d6_ = new DominoModel(650, 400, 100, 50, pioche.getFirstDominoD(), pioche.getNumFirstDomino());
				d6_.setOnMouseClicked(grille);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d6_);


				DominoModel d7_ = new DominoModel(650, 500, 100, 50, pioche.getFirstDominoD(), pioche.getNumFirstDomino());
				d7_.setOnMouseClicked(grille);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d7_);


				DominoModel d8_ = new DominoModel(650, 600, 100, 50, pioche.getFirstDominoD(), pioche.getNumFirstDomino());
				d8_.setOnMouseClicked(grille);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				System.out.println("###" + pioche.getSize());
				if (pioche.getSize() > 0) {
					pioche.setFill(pioche.getFirstDominoD());
				} else {
					pioche.setFill("Application/Ressources/Dominos/ekekan.jpg");
				}

				zoneJeu.getChildren().add(d8_);

				//l1 = null;
				//l2 = null;
			}


			// EVENT TRIER DOMINO
			else if (((Button) actionEvent.getSource()).getText() == "Trier") {
				l1 = new ArrayList<DominoModel>();
				l2 = new ArrayList<DominoModel>();

				for (int i = 0; i < 4; i++) {
					l1.add(i, pioche.getLastDomino());
					pioche.getListeDominos().remove(pioche.getLastDomino());
				}

				for (int j = 0; j < 4; j++) {
					l2.add(j, pioche.getLastDomino());
					pioche.getListeDominos().remove(pioche.getLastDomino());
				}

				pioche.triDomino(l1);
				pioche.triDomino(l2);
			}
		}
	}
}