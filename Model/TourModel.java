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
		if(partie.getListeJoueur().size() == 2 && nbTourRestant == 11)
		{
			nbTourRestant = 5;
		}
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
		//		pioche.affichePioche();
		dominoDispo.addAll(partieEnCours.getPioche().getTirageRetourne());

		for(int i = 0; i < partieEnCours.getListeJoueur().size(); i++)
		{
			if(partieEnCours.getListeJoueur().size() == 2) {
				if (idTour > 1) {
//					placerDominoAleatoire(i + 1);
										placerDomino(i + 1);
					partieEnCours.getJoueur(i + 1).getListeDomino().remove(0);
//					placerDominoAleatoire(i + 1);
										placerDomino(i + 1);
					partieEnCours.getJoueur(i + 1).getListeDomino().remove(0);
				}
				if (TourRestant > 0) {
										selectionDomino(i);
//					selectionDominoAleatoire(i);
										selectionDomino(i);
//					selectionDominoAleatoire(i);
				}
			}
			else
			{
				if (idTour > 1) {
//					placerDominoAleatoire(i + 1);
										placerDomino(i + 1);
					partieEnCours.getJoueur(i + 1).getListeDomino().remove(0);
				}
				if (TourRestant > 0) {
										selectionDomino(i);
//					selectionDominoAleatoire(i);
				}
			}
		}
	}

	public void afficheTour()
	{
		System.out.println("tour : " + idTour + " et tour restant : " + TourRestant);
	}

	public void selectionDomino(int idJ)
	{
		Scanner s = new Scanner(System.in);
		System.out.println("JOUEUR " + (idJ + 1) + " choisissez votre domino : ");
		int numDominoChoisi = s.nextInt();
		partieEnCours.getJoueur(idJ+1).choixDomino(partieEnCours.getPioche().getTirageRetourne().get(numDominoChoisi-1));
	}

	public void placerDomino(int idJoueur)
	{
		boolean placementValide = false;
		Scanner s = new Scanner(System.in);

		while(!placementValide)
		{
			System.out.println("JOUEUR " + (idJoueur) + " entrer le numéro de la ligne : ");
			int ligne = s.nextInt();
			System.out.println("JOUEUR " + (idJoueur) + " entrer le numéro de la colonne : ");
			int colonne = s.nextInt();
			System.out.println("JOUEUR " + (idJoueur) + " entrer la lettre du sens (h, d, g, b) : ");
			String scs = s.next();
			String sens = null;
			if(scs.equalsIgnoreCase("D"))
			{
				sens = "D";
			}
			else if(scs.equalsIgnoreCase("H"))
			{
				sens = "H";
			}
			else if(scs.equalsIgnoreCase("B"))
			{
				sens = "B";
			}
			else if(scs.equalsIgnoreCase("G"))
			{
				sens = "G";
			}

			placementValide = partieEnCours.getJoueur(idJoueur).getPlateau().ajouteDomino(partieEnCours.getJoueur(idJoueur).getListeDomino().get(0), ligne, colonne, sens);
			if(!placementValide)
			{
				System.out.println("Placement invalide, veuillez réessayer : ");
			}
		}
	}

	public void placerDominoAleatoire(int idJoueur)
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
