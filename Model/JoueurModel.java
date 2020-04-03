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

	public boolean checkArround(int i, int j) {
		boolean caseAutour = false;
		PaysageModel paysage = (PaysageModel) plateau.getElement(i, j);
		String terrain = paysage.getNomTerrain(); // On met le nom du paysage dans une variable
		if (i != 0 && j != 0) { // Dans le cas où on est pas au bord du plateau
			// Pour la case du haut
			if (plateau.getElement(i-1, j) instanceof PaysageModel && caseAutour == false) {
				PaysageModel paysageHaut = (PaysageModel) plateau.getElement(i-1, j);
				String terrainHaut = paysageHaut.getNomTerrain(); // On met le nom du paysage dans une variable
				boolean checkHaut = paysageHaut.checked();
				if (terrainHaut == terrain && checkHaut == false) {
					caseAutour = true;
				}
			}
			// Pour la case du bas
			if (plateau.getElement(i+1, j) instanceof PaysageModel && caseAutour == false) {
				PaysageModel paysageBas = (PaysageModel) plateau.getElement(i+1, j);
				String terrainBas = paysageBas.getNomTerrain(); // On met le nom du paysage dans une variable
				boolean checkBas = paysageBas.checked();
				if (terrainBas == terrain && checkBas == false) {
					caseAutour = true;
				}
			}
			//Pour la case de droite
			if (plateau.getElement(i, j+1) instanceof PaysageModel && caseAutour == false) {
				PaysageModel paysageDroite = (PaysageModel) plateau.getElement(i, j+1);
				String terrainDroite = paysageDroite.getNomTerrain(); // On met le nom du paysage dans une variable
				boolean checkDroite = paysageDroite.checked();
				if (terrainDroite == terrain && checkDroite == false) {
					caseAutour = true;
				}
			}
			//Pour la case de gauche 
			if (plateau.getElement(i, j-1) instanceof PaysageModel && caseAutour == false) {
				PaysageModel paysageGauche = (PaysageModel) plateau.getElement(i, j-1);
				String terrainGauche = paysageGauche.getNomTerrain(); // On met le nom du paysage dans une variable
				boolean checkGauche = paysageGauche.checked();
				if (terrainGauche == terrain && checkGauche == false) {
					caseAutour = true;
				}
			}
		}
		if (i == 0 && j==0) { // Dans le cas où on est dans le coin haut gauche
			// Pour la case du bas
			if (plateau.getElement(i+1, j) instanceof PaysageModel && caseAutour == false) {
				PaysageModel paysageBas = (PaysageModel) plateau.getElement(i+1, j);
				String terrainBas = paysageBas.getNomTerrain(); // On met le nom du paysage dans une variable
				boolean checkBas = paysageBas.checked();
				if (terrainBas == terrain && checkBas == false) {
					caseAutour = true;
				}
			}
			//Pour la case de droite
			if (plateau.getElement(i, j+1) instanceof PaysageModel && caseAutour == false) {
				PaysageModel paysageDroite = (PaysageModel) plateau.getElement(i, j+1);
				String terrainDroite = paysageDroite.getNomTerrain(); // On met le nom du paysage dans une variable
				boolean checkDroite = paysageDroite.checked();
				if (terrainDroite == terrain && checkDroite == false) {
					caseAutour = true;
				}
			}
		}
		if (i == 8 && j==8) { // Dans le cas où on est dans le coin bas gauche
			//Pour la case de droite
			if (plateau.getElement(i, j+1) instanceof PaysageModel && caseAutour == false) {
				PaysageModel paysageDroite = (PaysageModel) plateau.getElement(i, j+1);
				String terrainDroite = paysageDroite.getNomTerrain(); // On met le nom du paysage dans une variable
				boolean checkDroite = paysageDroite.checked();
				if (terrainDroite == terrain && checkDroite == false) {
					caseAutour = true;
				}
			}
			// Pour la case du haut
			if (plateau.getElement(i-1, j) instanceof PaysageModel && caseAutour == false) {
				PaysageModel paysageHaut = (PaysageModel) plateau.getElement(i-1, j);
				String terrainHaut = paysageHaut.getNomTerrain(); // On met le nom du paysage dans une variable
				boolean checkHaut = paysageHaut.checked();
				if (terrainHaut == terrain && checkHaut == false) {
					caseAutour = true;
				}
			}
		}
		if (i == 8 && j==0) { // Dans le cas où on est dans le coin haut droite
			// Pour la case du bas
			if (plateau.getElement(i+1, j) instanceof PaysageModel && caseAutour == false) {
				PaysageModel paysageBas = (PaysageModel) plateau.getElement(i+1, j);
				String terrainBas = paysageBas.getNomTerrain(); // On met le nom du paysage dans une variable
				boolean checkBas = paysageBas.checked();
				if (terrainBas == terrain && checkBas == false) {
					caseAutour = true;
				}
			}
			//Pour la case de gauche 
			if (plateau.getElement(i, j-1) instanceof PaysageModel && caseAutour == false) {
				PaysageModel paysageGauche = (PaysageModel) plateau.getElement(i, j-1);
				String terrainGauche = paysageGauche.getNomTerrain(); // On met le nom du paysage dans une variable
				boolean checkGauche = paysageGauche.checked();
				if (terrainGauche == terrain && checkGauche == false) {
					caseAutour = true;
				}
			}
		}
		if (i == 8 && j==0) { // Dans le cas où on est dans le coin bas droite
			//Pour la case de gauche 
			if (plateau.getElement(i, j-1) instanceof PaysageModel && caseAutour == false) {
				PaysageModel paysageGauche = (PaysageModel) plateau.getElement(i, j-1);
				String terrainGauche = paysageGauche.getNomTerrain(); // On met le nom du paysage dans une variable
				boolean checkGauche = paysageGauche.checked();
				if (terrainGauche == terrain && checkGauche == false) {
					caseAutour = true;
				}
			}
		}
		// Pour la case du haut
		if (plateau.getElement(i-1, j) instanceof PaysageModel && caseAutour == false) {
			PaysageModel paysageHaut = (PaysageModel) plateau.getElement(i-1, j);
			String terrainHaut = paysageHaut.getNomTerrain(); // On met le nom du paysage dans une variable
			boolean checkHaut = paysageHaut.checked();
			if (terrainHaut == terrain && checkHaut == false) {
				caseAutour = true;
			}
		}
		return caseAutour;
	}

	public int calculePoint() // Calcul des couronnes
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

	public int calculePoint2() { // Calcul des points
		int point = 0;
		for (int i = 0; i <= 8; i++)
		{
			int cmpCouronne = 0; // Compteur pour les couronnes
			int cmpPoint = 0; // Compteur pour les points
			for (int j = 0; j <= 8; j++) {
				if (plateau.getElement(i, j) instanceof PaysageModel && ((PaysageModel) plateau.getElement(i, j)).checked()==false) { // Si la case contient un paysage
					String terrain = ((PaysageModel) plateau.getElement(i, j)).getNomTerrain();
					cmpPoint = cmpPoint+1;
					cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(i, j)).getNbCouronne();
					((PaysageModel) plateau.getElement(i, j)).setCheck(true);
					int iRestant;
					int jRestant;
					//  -----------Pour la case du haut-----------
					if (plateau.getElement(i-1, j) instanceof PaysageModel && ((PaysageModel) plateau.getElement(i-1, j)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(i-1, j)).checked()==false) {
						cmpPoint = cmpPoint+1;
						cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(i-1, j)).getNbCouronne();
						iRestant = i-1;
						jRestant = j;
						((PaysageModel) plateau.getElement(i-1, j)).setCheck(true);
						while(checkArround(iRestant,jRestant) == true) {
							// Pour la case du haut
							if (plateau.getElement(iRestant-1, jRestant) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant-1, jRestant)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant-1, jRestant)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant-1, jRestant)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant-1, jRestant)).setCheck(true);
								iRestant = iRestant-1;
							}
							// Pour la case du bas
							if (plateau.getElement(iRestant+1, jRestant) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant+1, jRestant)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant+1, jRestant)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant+1, jRestant)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant+1, jRestant)).setCheck(true);
								iRestant = iRestant+1;
							}
							// Pour la case de droite
							if (plateau.getElement(iRestant, jRestant+1) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant, jRestant+1)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant, jRestant+1)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant, jRestant+1)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant, jRestant+1)).setCheck(true);
								jRestant = jRestant+1;
							}
							// Pour la case de gauche
							if (plateau.getElement(iRestant, jRestant-1) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant, jRestant-1)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant, jRestant-1)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant, jRestant-1)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant, jRestant-1)).setCheck(true);
								jRestant = jRestant-1;
							}
						}
					}
					//  -----------Pour la case du bas-----------
					if (plateau.getElement(i+1, j) instanceof PaysageModel && ((PaysageModel) plateau.getElement(i+1, j)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(i+1, j)).checked()==false) {
						cmpPoint = cmpPoint+1;
						cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(i+1, j)).getNbCouronne();
						iRestant = i+1;
						jRestant = j;
						((PaysageModel) plateau.getElement(i+1, j)).setCheck(true);
						while(checkArround(iRestant,jRestant) == true) {
							// Pour la case du haut
							if (plateau.getElement(iRestant-1, jRestant) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant-1, jRestant)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant-1, jRestant)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant-1, jRestant)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant-1, jRestant)).setCheck(true);
								iRestant = iRestant-1;
							}
							// Pour la case du bas
							if (plateau.getElement(iRestant+1, jRestant) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant+1, jRestant)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant+1, jRestant)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant+1, jRestant)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant+1, jRestant)).setCheck(true);
								iRestant = iRestant+1;
							}
							// Pour la case de droite
							if (plateau.getElement(iRestant, jRestant+1) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant, jRestant+1)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant, jRestant+1)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant, jRestant+1)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant, jRestant+1)).setCheck(true);
								jRestant = jRestant+1;
							}
							// Pour la case de gauche
							if (plateau.getElement(iRestant, jRestant-1) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant, jRestant-1)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant, jRestant-1)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant, jRestant-1)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant, jRestant-1)).setCheck(true);
								jRestant = jRestant-1;
							}
						}
					}
					//  -----------Pour la case de gauche-----------
					if (plateau.getElement(i, j-1) instanceof PaysageModel && ((PaysageModel) plateau.getElement(i, j-1)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(i, j-1)).checked()==false) {
						cmpPoint = cmpPoint+1;
						cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(i, j-1)).getNbCouronne();
						jRestant = j-1;
						iRestant = i;
						((PaysageModel) plateau.getElement(i, j-1)).setCheck(true);
						while(checkArround(iRestant,jRestant) == true) {
							// Pour la case du haut
							if (plateau.getElement(iRestant-1, jRestant) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant-1, jRestant)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant-1, jRestant)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant-1, jRestant)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant-1, jRestant)).setCheck(true);
								iRestant = iRestant-1;
							}
							// Pour la case du bas
							if (plateau.getElement(iRestant+1, jRestant) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant+1, jRestant)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant+1, jRestant)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant+1, jRestant)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant+1, jRestant)).setCheck(true);
								iRestant = iRestant+1;
							}
							// Pour la case de droite
							if (plateau.getElement(iRestant, jRestant+1) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant, jRestant+1)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant, jRestant+1)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant, jRestant+1)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant, jRestant+1)).setCheck(true);
								jRestant = jRestant+1;
							}
							// Pour la case de gauche
							if (plateau.getElement(iRestant, jRestant-1) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant, jRestant-1)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant, jRestant-1)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant, jRestant-1)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant, jRestant-1)).setCheck(true);
								jRestant = jRestant-1;
							}
						}
					}
					//  -----------Pour la case de droite-----------
					if (plateau.getElement(i, j+1) instanceof PaysageModel && ((PaysageModel) plateau.getElement(i, j+1)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(i, j+1)).checked()==false) {
						cmpPoint = cmpPoint+1;
						cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(i, j+1)).getNbCouronne();
						jRestant = j+1;
						iRestant = i;
						((PaysageModel) plateau.getElement(i, j+1)).setCheck(true);
						while(checkArround(iRestant,jRestant) == true) {
							// Pour la case du haut
							if (plateau.getElement(iRestant-1, jRestant) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant-1, jRestant)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant-1, jRestant)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant-1, jRestant)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant-1, jRestant)).setCheck(true);
								iRestant = iRestant-1;
							}
							// Pour la case du bas
							if (plateau.getElement(iRestant+1, jRestant) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant+1, jRestant)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant+1, jRestant)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant+1, jRestant)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant+1, jRestant)).setCheck(true);
								iRestant = iRestant+1;
							}
							// Pour la case de droite
							if (plateau.getElement(iRestant, jRestant+1) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant, jRestant+1)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant, jRestant+1)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant, jRestant+1)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant, jRestant+1)).setCheck(true);
								jRestant = jRestant+1;
							}
							// Pour la case de gauche
							if (plateau.getElement(iRestant, jRestant-1) instanceof PaysageModel && ((PaysageModel) plateau.getElement(iRestant, jRestant-1)).getNomTerrain() == terrain && ((PaysageModel) plateau.getElement(iRestant, jRestant-1)).checked()==false) {
								cmpPoint = cmpPoint+1;
								cmpCouronne = cmpCouronne + ((PaysageModel) plateau.getElement(iRestant, jRestant-1)).getNbCouronne();
								((PaysageModel) plateau.getElement(iRestant, jRestant-1)).setCheck(true);
								jRestant = jRestant-1;
							}
						}
					}
				}
			}
			point = point + (cmpPoint * cmpCouronne);
		}
		return point;
	}
}
