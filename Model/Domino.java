package Application.Model;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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


        effectSelected = new DropShadow();
        effectSelected.setBlurType(BlurType.GAUSSIAN);
        effectSelected.setSpread(10);
        effectSelected.setColor(Color.RED);
        effectSelected.setRadius(2);
    }


    public String getUrl() {
        return this.url;
    }

    public void setUrl(String u){
        url = u;
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