package Application.Model;

import java.util.ArrayList;

public class JoueurModel
{
	private boolean joueurIA;
	private int point;
	private int idJoueur;
	private ArrayList<DominoModel> listeDomino;
	private PlateauModel plateau;

	Connexion CBDD = new Connexion();

	public JoueurModel(int id)
	{
		idJoueur = id;
		listeDomino = new ArrayList<>();
		plateau = new PlateauModel();
		joueurIA = false;
		point = 0;
	}

	public boolean choixDomino(DominoModel domino)
	{
		listeDomino.add(domino);
		return true;
	}

	public ArrayList<DominoModel> getListeDomino() {
		return listeDomino;
	}

	public PlateauModel getPlateau() { return plateau; }

	public boolean isJoueurIA() {
		return joueurIA;
	}

	public void setJoueurIA(boolean joueurIA) {
		this.joueurIA = joueurIA;
	}

	public int calculePoint()
	{
		int nbCouronnes = 0;
		for (int i = 1; i < 8; i++)
		{
			for (int j = 1; j < 8; j++)
			{
				if (plateau.getElement(i, j) instanceof PaysageModel)
				{
					nbCouronnes += ((PaysageModel) plateau.getElement(i, j)).getNbCouronne();
				}
			}
		}
		return nbCouronnes;
	}
}
