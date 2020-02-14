package Application.Model;

import java.util.ArrayList;

public class DominoModel extends ElementModel
{
	//    private int numero;
	private PaysageModel paysage1;      //Face 1 du domino
	private PaysageModel paysage2;      //Face 2 du domino
	private String sens = null;
	private int abscisse1;
	private int ordonnee1;
	private int abscisse2;
	private int ordonnee2;

	//    Les boolean sont les etats mais pas utile pour l'instant
	private boolean choisi = false;
	private boolean joue = false;
	private boolean ecarte = false;
	private static int compteurNumero = 1;         //Compte le nombre de domino existant

	public DominoModel(PaysageModel p1, PaysageModel p2)
	{
		super("D");
		super.setIdElement(compteurNumero);
		//        numero = compteurNumero;
		compteurNumero++;
		paysage1 = p1;
		paysage2 = p2;
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
}
