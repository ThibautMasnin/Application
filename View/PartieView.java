package Application.View;

import Application.Controller.PartieController;
import Application.Model.DominoModel;
import Application.Model.PartieModel;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.sql.SQLException;


public class PartieView implements EventHandler<ActionEvent> {

	private PiocheModel pioche;

	private DominoModel d1;
	private DominoModel d2;
	private DominoModel d3;
	private DominoModel d4;
	private DominoModel d5;
	private DominoModel d6;
	private DominoModel d7;
	private DominoModel d8;

	private DominoModel d1_tmp;
	private DominoModel d2_tmp;
	private DominoModel d3_tmp;
	private DominoModel d4_tmp;
	private DominoModel d5_tmp;
	private DominoModel d6_tmp;
	private DominoModel d7_tmp;
	private DominoModel d8_tmp;

	private Group zoneJeu;

	private PlateauModel grille1;
	private PlateauModel grille2;
	private PlateauModel grille3;
	private PlateauModel grille4;

	private int minChrono;
	private int secChrono;
	private int minTemps;
	private int secTemps;
	private int joueur = 1;
	private int nbJoueurs;
	private int nbIAs;
	private int nbTour;


	public PartieView(Stage partieStage, int nbJoueurs, int nbIAs, int minChronoParametre, int secChronoParametre) throws SQLException{
		if(nbJoueurs+nbIAs==2) {
			nbTour=6;
		}
		else {
			nbTour=12;
		}

		this.nbJoueurs=nbJoueurs;
		this.nbIAs=nbIAs;
		minChrono=minChronoParametre;
		secChrono=secChronoParametre;
		minTemps = (nbJoueurs+nbIAs)*minChrono*nbTour+secChrono*(nbJoueurs+nbIAs)*nbTour/60;
		secTemps = secChrono*(nbJoueurs+nbIAs)*nbTour%60;

		PartieModel partieModel = new PartieModel(2);
		partieModel.setNbJoueurs(this.nbJoueurs);
		partieModel.setNbIAs(this.nbIAs);

		/** Borderpane principale **/
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
				if (secTemps > 0) {
					secTemps--;
				} else if (secTemps == 0) {
					if (minTemps > 0) {
						minTemps--;
						secTemps = 59;
					} else if (minTemps == 0) {
					} else {
						System.out.println("Erreur dans les minutes du chrono");
					}
				} else {
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
								if(joueur==nbJoueurs+nbIAs) {
									joueur=1;
									nbTour--;
								}
								else {
									joueur++;
								}
								if(nbTour!=0) {
									jouerTour();
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


		/** Bouton Sauvegarder **/
		Button btnSauvegarder = new Button();
		btnSauvegarder.setId("Sauvegarder");
		btnSauvegarder.setStyle("-fx-background-image: url('Application/Ressources/Images/sauvegarder.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);" +
				"-fx-background-size: 100%");
		btnSauvegarder.setMinWidth(282/2);
		btnSauvegarder.setMinHeight(51/2);
		btnSauvegarder.setOnAction((ActionEvent e) -> {
			PartieController evt = new PartieController<ActionEvent>();
			evt.setNbJoueurs(this.nbJoueurs);
			evt.setNbIAs(this.nbIAs);
			evt.setJoueur(this.joueur);
			evt.setNbTour(this.nbTour);
			evt.handle(e);
		});


		/** Bouton "Réglement" **/
		Button btnReglement = new Button();
		btnReglement.setId("Reglement");
		btnReglement.setStyle("-fx-background-image: url('Application/Ressources/Images/reglement.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);" +
				"-fx-background-size: 100%");
		btnReglement.setMinWidth(266/2);
		btnReglement.setMinHeight(54/2);
		btnReglement.setOnAction(new PartieController<ActionEvent>(partieStage));


		/** Bouton "Réglages" **/
		Button btnReglages = new Button();
		btnReglages.setId("Reglages");
		btnReglages.setStyle("-fx-background-image: url('Application/Ressources/Images/reglages2.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);" +
				"-fx-background-size: 100%");
		btnReglages.setMinWidth(245/2);
		btnReglages.setMinHeight(54/2);
		btnReglages.setOnAction(new PartieController<ActionEvent>(partieStage));


		/** Bouton "Quitter" **/
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


		/** Menu à gauche **/
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

		Button bMelanger = new Button("Melanger");
		bMelanger.setMaxSize(110,20);

		Button bDemarrer = new Button("Demarrer");
		bDemarrer.setMaxSize(110,20);

		Button bReset= new Button("Reset");
		bReset.setMaxSize(110,20);

		vActionDomino.getChildren().addAll(bRotateDroit, bRotateGauche, bAnnulerCoup, bFinirTour, bMelanger, bDemarrer, bReset);
		vActionDomino.setAlignment(Pos.CENTER);
		vActionDomino.setStyle("-fx-background-color: rgba(0, 0, 0, 0.25);-fx-border-color: #000000; -fx-border-width: 0 1 0 0;");

		VBox vDetailPartie = new VBox();
		vDetailPartie.setMinWidth(210);
		vDetailPartie.setStyle("-fx-background-color: rgba(0, 0, 0, 0.25);-fx-border-color: #000000; -fx-border-width: 0 0 0 1;");


		/** Plateau des joueurs **/

		// Joueur 1
		grille1 = new PlateauModel(50, 25, 10, 10, "-fx-background-color: rgba(240, 0, 0, 0.45);", "Application/Ressources/Dominos/C1.jpg",0, 0);
		grille1.dessinerGrille();

		// Joueur 2
		grille2 = new PlateauModel(50, 25, 10, 10, "-fx-background-color: rgba(0, 0, 170, 0.28);", "Application/Ressources/Dominos/C2.jpg",800, 0);
		grille2.dessinerGrille();

		// Joueur 3
		grille3 = new PlateauModel(50, 25, 10, 10, "-fx-background-color: rgba(250, 210, 0, 0.43);", "Application/Ressources/Dominos/C3.jpg",0, 500);
		grille3.dessinerGrille();

		// Joueur 4
		grille4 = new PlateauModel(50, 25, 10, 10, "-fx-background-color: rgba(0, 175, 0, 0.35);", "Application/Ressources/Dominos/C4.jpg", 800, 500);
		grille4.dessinerGrille();

		/** Création de la pioche **/
		pioche = new PiocheModel();

		/** Création des emplacements des dominos dans la zone de jeu **/
		d1 = new DominoModel(500, 300, 100, 50);
		d2 = new DominoModel(500, 400, 100, 50);
		d3 = new DominoModel(500, 500, 100, 50);
		d4 = new DominoModel(500, 600, 100, 50);
		d5 = new DominoModel(650, 300, 100, 50);
		d6 = new DominoModel(650, 400, 100, 50);
		d7 = new DominoModel(650, 500, 100, 50);
		d8 = new DominoModel(650, 600, 100, 50);

		/** PLATEAU DES JOUEURS **/
		zoneJeu = new Group();
		zoneJeu.setStyle("-fx-background-color: #336699;");


		/** Placement des éléments dans la zone de jeu **/
		zoneJeu = new Group();
		zoneJeu.setStyle("-fx-background-color: #336699;");
		zoneJeu.getChildren().addAll(grille1.getPane(), grille2.getPane(), grille3.getPane(), grille4.getPane(), pioche, d1, d2, d3, d4, d5, d6, d7, d8);
		grille2.getPane().setLayoutX(800);
		grille3.getPane().setLayoutY(500);
		grille4.getPane().setLayoutX(800);
		grille4.getPane().setLayoutY(500);
		pioche.setLayoutX(470);


		/** Event sur les boutons **/
		bRotateDroit.setOnAction(this);

		bRotateGauche.setOnAction(this);

		bAnnulerCoup.setOnAction(this);

		bFinirTour.setOnAction(e -> {
			File file = new File("src/Application/Ressources/Sons/clic.mp3");
			Media media = new Media(file.toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();
			if (nbTour > 0) {
				if (secTemps - secChrono < 0) {
					secTemps = 60 + secTemps - secChrono;
					minTemps--;
				} else {
					secTemps -= secChrono;
				}
				minTemps -= minChrono;
				if (joueur == nbJoueurs+nbIAs) {
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

			/** Permet au joueur suivant de jouer son tour lorsque le joueur précédent clique sur "Finir tour" **/
			jouerTour();
		});

		bMelanger.setOnAction(this);

		bDemarrer.setOnAction(this);

		bReset.setOnAction(e->{
			File file = new File("src/Application/Ressources/Sons/clic.mp3");
			Media media = new Media(file.toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();
			partieStage.close();
			try {
				PartieView pv = new PartieView(partieStage, nbJoueurs, nbIAs, minChronoParametre, secChronoParametre);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});


		/** Ajout au borderpane principale **/
		bp.setTop(bpMenu);
		bp.setLeft(vActionDomino);
		bp.setRight(vDetailPartie);
		bp.setCenter(zoneJeu);

		partieStage.setScene(scene);
		partieStage.setResizable(false);
		partieStage.setFullScreen(true);
		partieStage.setFullScreenExitHint("");
		partieStage.show();
	}


	@Override
	public void handle(ActionEvent actionEvent) {
		if (actionEvent.getSource() instanceof Button) {
			File file = new File("src/Application/Ressources/Sons/clic.mp3");
			Media media = new Media(file.toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			mediaPlayer.play();

			/** Event "Rotation droite" **/
			if (((Button) actionEvent.getSource()).getText() == "Rotation droite") {

				if (d1_tmp.isSelected()) {
					Rotate rotate = new Rotate(90, d1_tmp.getPivotX(), d1_tmp.getPivotY());
					d1_tmp.getTransforms().add(rotate);
					d1_tmp.switchSelected();
				}

				else if (d2_tmp.isSelected()) {
					Rotate rotate = new Rotate(90, d2_tmp.getPivotX(), d2_tmp.getPivotY());
					d2_tmp.getTransforms().add(rotate);
					d2_tmp.switchSelected();
				}

				else if (d3_tmp.isSelected()) {
					Rotate rotate = new Rotate(90, d3_tmp.getPivotX(), d3_tmp.getPivotY());
					d3_tmp.getTransforms().add(rotate);
					d3_tmp.switchSelected();
				}

				else if (d4_tmp.isSelected()) {
					Rotate rotate = new Rotate(90, d4_tmp.getPivotX(), d4_tmp.getPivotY());
					d4_tmp.getTransforms().add(rotate);
					d4_tmp.switchSelected();
				}
			}

			/** Event "Rotation gauche" **/
			else if (((Button) actionEvent.getSource()).getText() == "Rotation gauche") {

				if (d1_tmp.isSelected()) {
					Rotate rotate = new Rotate(-90, d1_tmp.getPivotX(), d1_tmp.getPivotY());
					d1_tmp.getTransforms().add(rotate);
					d1_tmp.switchSelected();
				}

				else if (d2_tmp.isSelected()) {
					Rotate rotate = new Rotate(-90, d2_tmp.getPivotX(), d2_tmp.getPivotY());
					d2_tmp.getTransforms().add(rotate);
					d2_tmp.switchSelected();
				}

				else if (d3_tmp.isSelected()) {
					Rotate rotate = new Rotate(-90, d3_tmp.getPivotX(), d3_tmp.getPivotY());
					d3_tmp.getTransforms().add(rotate);
					d3_tmp.switchSelected();
				}

				else if (d4_tmp.isSelected()) {
					Rotate rotate = new Rotate(-90, d4_tmp.getPivotX(), d4_tmp.getPivotY());
					d4_tmp.getTransforms().add(rotate);
					d4_tmp.switchSelected();
				}
			}


			/** Event "Annuler coup" **/
			else if (((Button) actionEvent.getSource()).getText() == "Annuler coup") {
			}


			/** Event "Melanger" **/
			else if (((Button) actionEvent.getSource()).getText() == "Melanger") {
				pioche.melangerPioche();
			}


			/** Event "Demarrer" **/
			else if (((Button) actionEvent.getSource()).getText() == "Demarrer") {
				/** On récupére le premier domino de la pioche (deck) **/
				d1_tmp = new DominoModel(500, 300, 100, 50, pioche.getFirstDomino(), pioche.getNumFirstDomino(), getColorJoueur());
				/** On change le curseur pour montrer qu'il peut avoir une interaction avec le domino **/
				d1_tmp.setCursor(Cursor.HAND);
				/** Puisque le domino a été pioché, on le retire de la pioche (deck) **/
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				/** On change le visuel le visuel de la pioche puisque le premier domino n'est plus le même **/
				pioche.setFill(pioche.getFirstDominoD());
				/** On ajoute le domino pioché dans la zone de jeu **/
				zoneJeu.getChildren().add(d1_tmp);
				/** On fait en sorte que le domino sélectionné puisse être ajouté au bon plateau donc en fonction du joueur qui joue **/
				setDominoGrille(d1_tmp, getColorJoueur());


				/** On fait la même chose pour le reste des dominos **/
				d2_tmp = new DominoModel(500, 400, 100, 50, pioche.getFirstDomino(), pioche.getNumFirstDomino(), getColorJoueur());
				d2_tmp.setCursor(Cursor.HAND);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d2_tmp);
				setDominoGrille(d2_tmp, getColorJoueur());


				d3_tmp = new DominoModel(500, 500, 100, 50, pioche.getFirstDomino(), pioche.getNumFirstDomino(), getColorJoueur());
				d3_tmp.setCursor(Cursor.HAND);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d3_tmp);
				setDominoGrille(d3_tmp, getColorJoueur());


				d4_tmp = new DominoModel(500, 600, 100, 50, pioche.getFirstDomino(), pioche.getNumFirstDomino(), getColorJoueur());
				d4_tmp.setCursor(Cursor.HAND);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d4_tmp);
				setDominoGrille(d4_tmp, getColorJoueur());


				d5_tmp = new DominoModel(650, 300, 100, 50, pioche.getFirstDominoD(), pioche.getNumFirstDomino(), null);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d5_tmp);


				d6_tmp = new DominoModel(650, 400, 100, 50, pioche.getFirstDominoD(), pioche.getNumFirstDomino(), null);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d6_tmp);


				d7_tmp = new DominoModel(650, 500, 100, 50, pioche.getFirstDominoD(), pioche.getNumFirstDomino(), null);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				pioche.setFill(pioche.getFirstDominoD());
				zoneJeu.getChildren().add(d7_tmp);


				d8_tmp = new DominoModel(650, 600, 100, 50, pioche.getFirstDominoD(), pioche.getNumFirstDomino(), null);
				pioche.getListeDominos().remove(pioche.getSize() - 1);
				if (pioche.getSize() > 0) {
					pioche.setFill(pioche.getFirstDominoD());
				} else {
					pioche.setFill(Color.TRANSPARENT);
					pioche.setStroke(Color.BLACK);
				}
				zoneJeu.getChildren().add(d8_tmp);
			}
		}
	}


	/** Méthode qui permet de mettre en relation le domino avec un plateau (si la couleur de selection du domino est rouge alors il peut UNIQUEMENT être placé sur le plateau rouge **/
	public void setDominoGrille(DominoModel domino, Color color) {

		if (color.equals(Color.RED)) {
			domino.setOnMouseClicked(grille1);
		}
		else if (color.equals(Color.BLUE)) {
			domino.setOnMouseClicked(grille2);
		}
		else if (color.equals(Color.YELLOW)) {
			domino.setOnMouseClicked(grille3);
		}
		else {
			domino.setOnMouseClicked(grille4);
		}
	}


	/** Méthode qui permet de "simuler" un tour de jeu **/
	public void jouerTour() {

		/** On change la couleur de selection en fonction du joueur qui joue **/
		d1_tmp.setEffectSelected(d1_tmp.getEffectSelectedJ1(), getColorJoueur());
		setDominoGrille(d1_tmp, getColorJoueur());

		d2_tmp.setEffectSelected(d2_tmp.getEffectSelectedJ1(), getColorJoueur());
		setDominoGrille(d2_tmp, getColorJoueur());

		d3_tmp.setEffectSelected(d3_tmp.getEffectSelectedJ1(), getColorJoueur());
		setDominoGrille(d3_tmp, getColorJoueur());

		d4_tmp.setEffectSelected(d4_tmp.getEffectSelectedJ1(), getColorJoueur());
		setDominoGrille(d4_tmp, getColorJoueur());
	}


	/** Méthode qui permet de changer la couleur de selection en fonction du joueur **/
	public Color getColorJoueur(){
		switch (joueur) {
			case 1:
				return Color.RED;
			case 2:
				return Color.BLUE;
			case 3:
				return Color.YELLOW;
			case 4:
				return Color.GREEN;
			default:
				return Color.TRANSPARENT;
		}
	}
}