package Application.Controller;

import Application.Model.Domino;
import Application.Model.Grille;
import Application.View.PartieView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class PartieController<T extends ActionEvent> implements EventHandler<T> {
    private Stage stage;
    private Domino dominoTMP;
    private ImageView frontCard;
    private ImageView backCard;
    private ImageView tmp;
    private boolean bool;

    public PartieController(){}

    public PartieController(Stage s) {
        stage = s;
    }

    public PartieController(Stage s, ImageView i, boolean b) {
        stage = s;
        tmp = i;
        bool = b;
        frontCard = new ImageView(new Image("Application/Images/dominoFaceRecto.jpg"));
        backCard = new ImageView(new Image("Application/Images/dominoFaceVerso.jpg"));
    }


    /**
     * ACTION EVENT
     **/
    @Override
    public void handle(T event) {

        if (event.getSource() instanceof Button) {

            // EVENT ROTATION A DROITE
            if (((Button) event.getSource()).getText() == "Rotation droite") {
                tmp.setRotate(tmp.getRotate() + 90);
                PartieView partieView = new PartieView(stage, tmp, true);
            }

            // EVENT ROTATION A GAUCHE
            if (((Button) event.getSource()).getText() == "Rotation gauche") {
                tmp.setRotate(tmp.getRotate() - 90);
                PartieView partieView = new PartieView(stage, tmp, true);
            }

            // EVENT ANNULER SON COUP
            if (((Button) event.getSource()).getText() == "Annuler coup") {
            }

            // EVENT FINIR SON TOUR
            if (((Button) event.getSource()).getText() == "Finir tour") {
            }


            // EVENT RETOURNER DOMINO
            if (((Button) event.getSource()).getText() == "Retourner") {
                if (bool) {
                    tmp = new ImageView(new Image("Application/Images/dominoFaceVerso.jpg"));
                    bool = false;
                    PartieView partieView = new PartieView(stage, tmp, bool);
                } else {
                    tmp = new ImageView(new Image("Application/Images/dominoFaceRecto.jpg"));
                    bool = true;
                    PartieView partieView = new PartieView(stage, tmp, bool);
                }
            }

            if (((Button) event.getSource()).getText() == "Quitter") {
                stage.close();
            }
        }
    }
}