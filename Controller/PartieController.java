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


    public PartieController(Stage s) {
        stage = s;
    }

    public PartieController(Stage s, Domino d) {
        stage = s;
        dominoTMP = d;
    }


    /**
     * ACTION EVENT
     **/
    @Override
    public void handle(T event) {

        if (event.getSource() instanceof Button) {

            // EVENT ROTATION A DROITE
            if (((Button) event.getSource()).getText() == "Rotation droite" && dominoTMP.isSelected()) {

                dominoTMP.setRotate(dominoTMP.getRotate() + 90);
                //PartieView partieView = new PartieView(stage, dominoTMP);
            }

            // EVENT ROTATION A GAUCHE
            if (((Button) event.getSource()).getText() == "Rotation gauche" && dominoTMP.isSelected()) {

                dominoTMP.setRotate(dominoTMP.getRotate() - 90);
                //PartieView partieView = new PartieView(stage, dominoTMP);
            }

            // EVENT ANNULER SON COUP
            if (((Button) event.getSource()).getText() == "Annuler coup") {
            }

            // EVENT FINIR SON TOUR
            if (((Button) event.getSource()).getText() == "Finir tour") {
            }


            // EVENT RETOURNER DOMINO
            if (((Button) event.getSource()).getText() == "Retourner") {
            }

            if (((Button) event.getSource()).getText() == "Quitter") {
                stage.close();
            }
        }
    }
}