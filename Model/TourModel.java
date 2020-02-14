package Application.Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TourModel
{
	private PartieModel partieEnCours;
	private int idTour;
	private int TourRestant;
	private static int numTour = 1;
	private static int nbTourRestant = 11;
	ArrayList<DominoModel> dominoDispo = new ArrayList<>();

	public TourModel(PartieModel partie)
	{
		partieEnCours = partie;
		idTour = numTour;
		TourRestant = nbTourRestant;
		numTour++;
		nbTourRestant--;
		dominoDispo = new ArrayList<>();
	}

	public JoueurModel choixJoueur() {
		return null;
	}


	public void jouerTour(PiocheModel pioche)
	{
		afficheTour();
		//On tire les dominos et les place dans tirageCachee (ces dominos sont cach�s et seront envoy� � tirageRetournee le tour suivant)
		// et ensuite dans tirageRetournee (le joueur doit choisir parmis ces dominos)
		pioche.tirage();
		if(TourRestant > 0)
		{
			if(TourRestant > 1)
			{
				pioche.afficheTirageCachee();
			}
			pioche.afficheTirageRetournee();
		}
		pioche.affichePioche();
		dominoDispo.addAll(partieEnCours.getPioche().getTirageRetourne());

		for(int i = 0; i < partieEnCours.getListeJoueur().size(); i++)
		{
			if(idTour > 1)
			{
				placementDominoAleatoire(i+1);
				partieEnCours.getJoueur(i+1).getListeDomino().remove(0);
			}
			if(TourRestant > 0)
			{
				//                selectionDomino(i);
				selectionDominoAleatoire(i);
			}
		}
	}

	public void afficheTour()
	{
		System.out.println("tour : " + idTour + " et tour restant : " + TourRestant);
	}

	public void selectionDomino(int i)
	{
		Scanner s = new Scanner(System.in);
		System.out.println("JOUEUR " + (i+1) + " choisissez");
		int numDominoChoisi = s.nextInt();
		partieEnCours.getJoueur(i+1).choixDomino(partieEnCours.getPioche().getTirageRetourne().get(numDominoChoisi-1));
	}

	public void placementDominoAleatoire(int idJoueur)
	{
		Random r = new Random();
		int max = 8;    // taille max d'une ligne et colonne
		int x = 0;
		int y = 0;
		int lettre = 0;
		String s = null;
		boolean placementValide = false;

		while(!placementValide)
		{
			x = r.nextInt(max);
			y = r.nextInt(max);
			lettre = r.nextInt(3);
			if(lettre == 0)
			{
				if(y == 0)
				{
					y++;
				}
				s = "G";
			}
			else if(lettre == 1)
			{
				if(x == 0)
				{
					x++;
				}
				s = "H";
			}
			else if(lettre == 2)
			{
				if(y == 8)
				{
					y--;
				}
				s = "D";
			}
			else if(lettre == 3)
			{
				if(x == 8)
				{
					x--;
				}
				s = "B";
			}
			else
			{
				System.out.println("ERREUR LETTRE SENS");
			}
			placementValide = partieEnCours.getJoueur(idJoueur).getPlateau().ajouteDomino(partieEnCours.getJoueur(idJoueur).getListeDomino().get(0), x, y, s);
		}
	}

	public void selectionDominoAleatoire(int i)
	{
		Random r = new Random();
		int max = dominoDispo.size();
		int var = 0;
		boolean dominoValide = false;
		while(!dominoValide)
		{
			var = r.nextInt(max);
			dominoValide = partieEnCours.getJoueur(i+1).choixDomino(dominoDispo.get(var));
		}
		dominoDispo.remove(var);
	}

}
