package Application.Controller;

import Application.Model.Domino;
import Application.View.PartieView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PartieControllerME extends ActionEvent implements EventHandler<MouseEvent> {
    private Stage stage;
    private Domino dominoSelectionne;
    private boolean bool;

    public PartieControllerME() {
        System.out.println(dominoSelectionne);
    }

    public PartieControllerME(Domino d) {
        dominoSelectionne = d;
    }

    public PartieControllerME(Stage s, Domino d) {
        stage = s;
        dominoSelectionne = d;
    }

    @Override
    public void handle(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();

        Object o = event.getSource();

        System.out.println("clic en " + x + "," + y);


        if (o instanceof Domino) {
            Domino dominoGetSource = (Domino) o;

            //si on selectionne un nouveau pion ou deselectionne le pion deja selectionne
                if (dominoSelectionne == null || dominoSelectionne == dominoGetSource) {
                    System.out.println("Test 1");
                    dominoGetSource.switchSelected();

                    if (dominoSelectionne == null) {
                        System.out.println("Test 2");
                        dominoSelectionne = dominoGetSource;
                    } else {
                        System.out.println("Test 3");
                        dominoSelectionne = null;
                    }
                }
                System.out.println(dominoSelectionne);
                System.out.println();
            }
        /*
        else
            if (o instanceof Rectangle && dominoSelectionne != null) {
                System.out.println("Event");
                Rectangle r = (Rectangle) o;

                //le nouveau centre du jeton sera au centre du rectangle sélectionné
                double newX = r.getX();
                double newY = r.getY();

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
                System.out.println("déplacement réussi");
                dominoSelectionne = null;
            }
        */
    }
}

