package Application.Model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TourModel
{
	private PartieModel partieEnCours;
	private int idTour;
	private int TourRestant;
	private int numTour = 1;
	private int nbTourRestant = 11;
	ArrayList<DominoModel> dominoDispo;

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
//		dominoDispo.addAll(partieEnCours.getPioche().getTirageRetourne());
//		System.out.println("DOMINO DISPO : " + dominoDispo.size());
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
                    if(partieEnCours.getListeJoueur().get(i).isJoueurIA())
                    {
                        placerDominoAleatoire(i + 1);
                        partieEnCours.getJoueur(i + 1).getListeDomino().remove(0);
    					placerDominoAleatoire(i + 1);
                        partieEnCours.getJoueur(i + 1).getListeDomino().remove(0);
                    }
                    else
                    {
                        placerDomino(i + 1);
                        partieEnCours.getJoueur(i + 1).getListeDomino().remove(0);
                        placerDomino(i + 1);
                        partieEnCours.getJoueur(i + 1).getListeDomino().remove(0);
                    }
////					placerDominoAleatoire(i + 1);
//                    placerDomino(i + 1);
//                    partieEnCours.getJoueur(i + 1).getListeDomino().remove(0);
////					placerDominoAleatoire(i + 1);
//                    placerDomino(i + 1);
//                    partieEnCours.getJoueur(i + 1).getListeDomino().remove(0);
                }
                if (TourRestant > 0) {
                    if(partieEnCours.getListeJoueur().get(i).isJoueurIA())
                    {
                        selectionDominoAleatoire(i);
                        selectionDominoAleatoire(i);
                    }
                    else
                    {
                        selectionDomino(i);
                        selectionDomino(i);
                    }
//                    selectionDomino(i);
////					selectionDominoAleatoire(i);
//                    selectionDomino(i);
////					selectionDominoAleatoire(i);
                }
            }
            else
            {
                if (idTour > 1) {
                    if(partieEnCours.getListeJoueur().get(i).isJoueurIA())
                    {
                        placerDominoAleatoire(i + 1);
                        partieEnCours.getJoueur(i + 1).getListeDomino().remove(0);
                    }
                    else
                    {
                        placerDomino(i + 1);
                        partieEnCours.getJoueur(i + 1).getListeDomino().remove(0);
                    }
////					placerDominoAleatoire(i + 1);
//                    placerDomino(i + 1);
//                    partieEnCours.getJoueur(i + 1).getListeDomino().remove(0);
                }
                if (TourRestant > 0) {
                    if(partieEnCours.getListeJoueur().get(i).isJoueurIA())
                    {
                        selectionDominoAleatoire(i);
                    }
                    else
                    {
                        selectionDomino(i);
                    }
//                    selectionDomino(i);
//					selectionDominoAleatoire(i);
                }
            }
        }

		/*try {
			PGSimpleDataSource ds = new PGSimpleDataSource();

			ds.setServerName("localhost");
			ds.setDatabaseName("m4106");
			ds.setUser("postgres");
			ds.setPassword("******");//VOTRE MDP!!!
			Connection con = ds.getConnection();

			try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Tour VALUES(?,(SELECT MAX(idPartie) FROM Partie),?);")){
				stmt.setInt(1, this.idTour);
				//stmt.setInt(2, this.partieEnCours.getId());
				stmt.setInt(2, this.nbTourRestant);
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}*/
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
            System.out.println("JOUEUR "  + (idJoueur) + " placez le domino : " + "[Domino n"
                    + partieEnCours.getJoueur(idJoueur).getListeDomino().get(0).getNumDomino()
                    +"] ------ "+ partieEnCours.getJoueur(idJoueur).getListeDomino().get(0).toStringPaysage());
            System.out.println("JOUEUR " + (idJoueur) + " entrez le numéro de la ligne (entre 0 et 8): ");
            int ligne = s.nextInt();
            System.out.println("JOUEUR " + (idJoueur) + " entrez le numéro de la colonne (entre 0 et 8) : ");
            int colonne = s.nextInt();

            String scs = null; //le sens d'orientation du domino
            System.out.println("JOUEUR " + (idJoueur) + " entrez la lettre du sens (h, d, g, b) : ");
            scs = s.next();
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
        int max = 6;    // taille max d'une ligne et colonne
        int x = 0;
        int y = 0;
        int lettre = 0;
        String s = null;
        boolean placementValide = false;
        int cmp = 0;

        while(!placementValide && cmp < 200)
        {
            x = r.nextInt(max)+1;
            y = r.nextInt(max)+1;
            lettre = r.nextInt(3);
            if(lettre == 0)
            {
                if(y == 1)
                {
                    y++;
                }
                s = "G";
            }
            else if(lettre == 1)
            {
                if(x == 1)
                {
                    x++;
                }
                s = "H";
            }
            else if(lettre == 2)
            {
                if(y == 7)
                {
                    y--;
                }
                s = "D";
            }
            else if(lettre == 3)
            {
                if(x == 7)
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
            cmp++;
        }
        if(cmp == 199)
        {
            partieEnCours.getJoueur(idJoueur).getListeDomino().remove(0);
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
