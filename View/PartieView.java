package Application.View;

import Application.Controller.PartieController;
import Application.Model.DominoModel;
import Application.Model.Grille;
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

public class PartieView implements EventHandler<ActionEvent> {
	private DominoModel dominoTMP;
	private int cpt;


	public PartieView(Stage partieStage) {

		//dominoTMP = new Domino(0, 0, 50, 100, "Application/Ressources/Dominos/D1.jpg");
		DominoModel d1 = new DominoModel(-50, 125, 50, 100, "Application/Ressources/Dominos/D1.jpg");
		DominoModel d2 = new DominoModel(-150, 125, 50, 100, "Application/Ressources/Dominos/D2.jpg");
		DominoModel d3 = new DominoModel(-250, 125, 50, 100, "Application/Ressources/Dominos/D3.jpg");
		DominoModel d4 = new DominoModel(-350, 125, 50, 100, "Application/Ressources/Dominos/D4.jpg");
		DominoModel d5 = new DominoModel(-50, 275, 50, 100, "Application/Ressources/Dominos/D5.jpg");
		DominoModel d6 = new DominoModel(-150, 275, 50, 100, "Application/Ressources/Dominos/D6.jpg");
		DominoModel d7 = new DominoModel(-250, 275, 50, 100, "Application/Ressources/Dominos/D7.jpg");
		DominoModel d8 = new DominoModel(-350, 275, 50, 100, "Application/Ressources/Dominos/D8.jpg");

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

		String lastTime = new String("27:42");
		Label lTempsPartie = new Label("Temps restants : " + lastTime);
		String nbTour = new String("8");
		Label lNbTour = new Label("Tours restants : " + nbTour);
		

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
		Label lJoueur = new Label("Joueur 1 :");
		Label lTempsTour = new Label("01:29");
		lJoueur.setTextFill(Color.web("#ffffff"));
		lJoueur.setFont(new Font("Viner Hand ITC", 24));
		lTempsTour.setTextFill(Color.web("#ffffff"));
		lTempsTour.setFont(new Font("Viner Hand ITC", 24));
		hbox2.getChildren().addAll(lJoueur, lTempsTour);
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
		btnReglement.setId("Réglement");
		btnReglement.setStyle("-fx-background-image: url('Application/Ressources/Images/reglement.png');" +
				"-fx-background-color: rgba(0, 0, 0, 0);" +
				"-fx-background-size: 100%");
		btnReglement.setMinWidth(266/2);
		btnReglement.setMinHeight(54/2);
        btnReglement.setOnAction(new PartieController<ActionEvent>(partieStage));

		/** BOUTON REGLAGES **/
		Button btnReglages = new Button();
		btnReglages.setId("Réglages");
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

		Button bDemarrer = new Button("Demarrer");
		bRetourner.setMaxSize(110,20);

		vActionDomino.getChildren().addAll(bRotateDroit, bRotateGauche, bAnnulerCoup, bFinirTour, bRetourner, bDemarrer);
		vActionDomino.setAlignment(Pos.CENTER);
		vActionDomino.setStyle("-fx-background-color: rgba(0, 0, 0, 0.25);-fx-border-color: #000000; -fx-border-width: 0 1 0 0;");

		VBox vDetailPartie = new VBox();
		vDetailPartie.setMinWidth(210);
		vDetailPartie.setStyle("-fx-background-color: rgba(0, 0, 0, 0.25);-fx-border-color: #000000; -fx-border-width: 0 0 0 1;");


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


		/** AJOUT AU BORDERPANE PRINCIPALE **/
		bp.setTop(bpMenu);
		bp.setLeft(vActionDomino);
		bp.setRight(vDetailPartie);
		//BorderPane.setAlignment(dominoTMP, Pos.CENTER_RIGHT);
		bp.setCenter(zoneJeu);


		partieStage.setScene(scene);
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

		}
	}
}