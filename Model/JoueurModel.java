package Application.Model;

import java.util.ArrayList;

public class JoueurModel {
	//    private Color couleur;
	//    private String pseudo;
	//    private boolean ia = false;
	//    private int point;
	private int idJoueur;
	private ArrayList<DominoModel> listeDomino;
	private PlateauModel plateau;

	public JoueurModel(int id)
	{
		idJoueur = id;
		listeDomino = new ArrayList<>();
		plateau = new PlateauModel();
	}

	public boolean choixDomino(DominoModel domino)
	{
		listeDomino.add(domino);
		return true;
	}

	public ArrayList<DominoModel> getListeDomino() {
		return listeDomino;
	}

	public PlateauModel getPlateau()
	{

		return plateau;
	}

	public void choixCouleur() {
	}

	public void choixChateau() {
	}

	public DominoModel choixDomino() {
		return null;
	}

	public void placerDominos() {
	}

	public void calculerPoint() {
	}

	public void terminerTour() {
	}

	public void annulerCoup() {
	}


}
