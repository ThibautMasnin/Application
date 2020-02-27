package Application.View;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import Application.Controller.ReglementController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ReglementView implements Observer {

	final VBox vb1 = new VBox();
	final VBox vb2 = new VBox();

	public ReglementView(Stage stage) {
		BorderPane bp = new BorderPane();
		ScrollPane sp = new ScrollPane();

		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		Scene scene = new Scene(bp, screenBounds.getWidth(), screenBounds.getHeight()-80);  
		bp.setStyle("-fx-background-image: url('Application/Ressources/Images/wallpaper.png');" +
				"-fx-background-position: center center;" +
				"-fx-background-size: cover;");

		Label title = new Label("Réglement");
		title.setFont(new Font("Viner Hand ITC", 64));
		title.setTextFill(Color.web("#ffffff"));

		String langue = new String("fr");

		BorderPane imageView1 = new BorderPane();
		BorderPane imageView2 = new BorderPane();
		BorderPane imageView3 = new BorderPane();
		BorderPane imageView4 = new BorderPane();

		Image src1 = null;
		Image src2 = null;
		Image src3 = null;
		Image src4 = null;
		src1 = new Image("Application/Ressources/Images/rules/rules1-" + langue + ".png");
		src2 = new Image("Application/Ressources/Images/rules/rules2-" + langue + ".png");
		src3 = new Image("Application/Ressources/Images/rules/rules3-" + langue + ".png");
		src4 = new Image("Application/Ressources/Images/rules/rules4.png");

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int wd  = (int)dimension.getWidth();
        int ht  = (int)dimension.getHeight();
        
        ImageView image1 = new ImageView(src1);
        ImageView image2 = new ImageView(src2);
        ImageView image3 = new ImageView(src3);
        ImageView image4 = new ImageView(src4);
        image1.setFitWidth(wd-55);
        image2.setFitWidth(wd-55);
        image3.setFitWidth(wd-55);
        image4.setFitWidth(wd-55);    
        image1.setFitHeight(wd-55);
        image2.setFitHeight(wd-55);
        image3.setFitHeight(wd-55);
        image4.setFitHeight(wd-55);    

        Button btnRetour = new Button();
        btnRetour.setId("Retour");
        btnRetour.setStyle("-fx-background-image: url('Application/Ressources/Images/retour.png');" +
				   "-fx-background-color: rgba(0, 0, 0, 0);");
        btnRetour.setMinWidth(140);
        btnRetour.setMinHeight(54);
        btnRetour.setOnAction(new ReglementController<ActionEvent>(stage));

        imageView1.setCenter(image1);  
        imageView2.setCenter(image2); 
        imageView3.setCenter(image3);  
        imageView4.setCenter(image4);
        
        vb1.getChildren().addAll(imageView1, imageView2, imageView3, imageView4); 
        vb2.getChildren().addAll(title, sp, btnRetour);   
        vb1.setAlignment(Pos.CENTER);
        vb2.setAlignment(Pos.CENTER);
        vb2.setSpacing(10);
        vb2.setPadding(new Insets(0, 20,10,20));
        
        sp.setContent(vb1);  
        bp.setCenter(vb2);       

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
