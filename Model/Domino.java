package Application.Model;

import Application.Controller.PartieControllerME;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Domino extends Rectangle {
    private String url;
    private ImagePattern img;
    private DropShadow effectSelected;
    private boolean isSelected;

    public Domino() {
    }

    public Domino(double centerX, double centerY, double width, double height, String u) {
        super(centerX, centerY, width, height);
        isSelected = false;

        url = u;
        img = new ImagePattern(new Image(getUrl()));
        setFill(img);



        this.setOnMouseClicked(event -> {
            PartieControllerME pcME = new PartieControllerME(this);
            pcME.handle(event);
        });


        effectSelected = new DropShadow();
        effectSelected.setBlurType(BlurType.GAUSSIAN);
        effectSelected.setSpread(10);
        effectSelected.setColor(Color.RED);
        effectSelected.setRadius(1);
    }


    public String getUrl() {
        return this.url;
    }

    public ImagePattern getImg() {
        return img;
    }

    public DropShadow getEffectSelectedJ1() {
        return effectSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void switchSelected()
    {
        isSelected = !isSelected;
        if (isSelected) this.setEffect(effectSelected); else this.setEffect(null);
    }
}