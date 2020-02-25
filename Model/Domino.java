package Application.Model;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Domino extends Rectangle {
    private String url;
    private ImagePattern img;
    private DropShadow effectSelected;
    private boolean isSelected;
    private double pivotX;
    private double pivotY;
    private double pivotTX;
    private double pivotTY;
    private int cptRotation;

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

        pivotX = this.getX() + (this.getWidth()/2);
        pivotY = this.getY() + (this.getHeight()/4);

        pivotTX = this.getX();
        pivotTY = this.getY();

        cptRotation = 0;
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

    public double getPivotX() {
        return pivotX;
    }

    public void setPivotX(double pivotX) {
        this.pivotX = pivotX;
    }

    public double getPivotY() {
        return pivotY;
    }

    public void setPivotY(double pivotY) {
        this.pivotY = pivotY;
    }

    public double getPivotTX() {
        return pivotTX;
    }

    public void setPivotTX(double pivotTX) {
        this.pivotTX = pivotTX;
    }

    public double getPivotTY() {
        return pivotTY;
    }

    public void setPivotTY(double pivotTY) {
        this.pivotTY = pivotTY;
    }

    public int getCptRotation() {
        return cptRotation;
    }

    public void setCptRotation(int cptRotation) {
        this.cptRotation = cptRotation;
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