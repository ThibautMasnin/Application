package Application.Model;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class DominoModel extends Rectangle
//extends ElementModel
{
	private int numero;
	private PaysageModel paysage1;      //Face 1 du domino
	private PaysageModel paysage2;      //Face 2 du domino
	private String sens = null;
	private int abscisse1;
	private int ordonnee1;
	private int abscisse2;
	private int ordonnee2;

	private static int compteurNumero = 1;         //Compte le nombre de domino existant

	private String url;
	private ImagePattern img;
	private DropShadow effectSelected;
	private boolean isSelected;
	private double pivotX;
	private double pivotY;
	private double pivotTX;
	private double pivotTY;

	private int cptRotation;


	public DominoModel(PaysageModel p1, PaysageModel p2,double centerX, double centerY, double width, double height, String u, int num) {
		super(centerX, centerY, width, height);
		numero = compteurNumero;
		compteurNumero++;
		paysage1 = p1;
		paysage2 = p2;
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

	public DominoModel(PaysageModel p1, PaysageModel p2)
	{
		//		super("D");
		//		super.setIdElement(compteurNumero);
		numero = compteurNumero;
		compteurNumero++;
		paysage1 = p1;
		paysage2 = p2;
	}

	public DominoModel(double centerX, double centerY, double width, double height, String u, int num, Color colorEffect) {
		super(centerX, centerY, width, height);
		isSelected = false;

		url = u;
		img = new ImagePattern(new Image(getUrl()));
		setFill(img);

		numero = num;

		effectSelected = new DropShadow();
		effectSelected.setBlurType(BlurType.GAUSSIAN);
		effectSelected.setSpread(10);
		effectSelected.setColor(colorEffect);
		effectSelected.setRadius(3);

		pivotX = this.getX() + (this.getWidth()/2);
		pivotY = this.getY() + (this.getHeight()/4);

		pivotTX = this.getX();
		pivotTY = this.getY();

		cptRotation = 0;

	}

	public DominoModel(double centerX, double centerY, double width, double height) {
		super(centerX, centerY, width, height);
		isSelected = false;

		setFill(Color.TRANSPARENT);
		setStroke(Color.BLACK);

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

	public int getNumDomino()
	{
		return numero;
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

	public void setEffectSelected(DropShadow dropShadow, Color color) {
		dropShadow.setColor(color);
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

	public PaysageModel getPaysage1() {
		return paysage1;
	}

	public PaysageModel getPaysage2() {
		return paysage2;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
	}

	public int getX1() {
		return abscisse1;
	}

	public void setX1(int x) {
		this.abscisse1 = x;
	}

	public int getX2() {
		return abscisse2;
	}

	public void setX2(int x) {
		this.abscisse2 = x;
	}

	public int getY1() {
		return ordonnee1;
	}

	public void setY1(int y) {
		this.ordonnee1 = y;
	}

	public int getY2() {
		return ordonnee2;
	}

	public void setY2(int y) {
		this.ordonnee2 = y;
	}

	public String toStringPaysage() {
		return  paysage1.toString() +"\t\t"+ paysage2.toString();
	}
}
