package Application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import org.postgresql.ds.*;

import org.postgresql.ds.PGSimpleDataSource;

public class PartieModel
{
	//    private int idPartie;
	//    private int nbJoueur;
	//    private int nbIA;
	//    private int minuteurPartie;
	//    private int minuteurTour;
	//    private int nbDominos;

	private ArrayList<JoueurModel> listeJoueur;     //On y trouvera les joueurs qu'on aura ajouté dans le constructeur
	private PiocheModel pioche;

	public PartieModel() throws SQLException
	{
		Scanner s = new Scanner(System.in);
		int NbJoueur = 0;
		while(!(NbJoueur >= 2 && NbJoueur <= 4))
		{
			System.out.println("Veuillez entrer le nombre de joueur (entre 2 et 4) : ");
			NbJoueur = s.nextInt();
		}
		//On crée la pioche vide pour l'instant avec getPioche car c'est un singleton car on ne peut avoir plusieurs pioche
		pioche = new PiocheModel();
		pioche.getPioche(this);
		//On crée les dominos et les ajoute dans la meme pioche
//		pioche.creerPioche();
		//On crée les joueur et les ajoute dans la liste qu'on utilisera
		listeJoueur = new ArrayList<>();
		for(int i = 0; i < NbJoueur; i++)
		{
			listeJoueur.add(new JoueurModel(i+1));
		}
	}

	public JoueurModel getJoueur(int idJoueur)
	{
		return listeJoueur.get(idJoueur-1);
	}

	public void jouer()
	{
		int nbTour = 12;
		if(listeJoueur.size() == 2)
		{
			nbTour = 6;
		}
		//On crée les tours, 12 tours d'après les règles du jeu donc 12 boucles
		for(int i = 0; i < nbTour; i++)
		{
			//On crée le tour concerné de cette partie et on la fait jouer
			TourModel tour = new TourModel(this);
			tour.jouerTour(pioche);

			for(int j = 0; j < listeJoueur.size(); j++)
			{
				System.out.println("||||||||||||||||||||||| JOUEUR " + (j+1) + " : |||||||||||||||||||||||");
				listeJoueur.get(j).getPlateau().affichePlateau();
				System.out.println();
			}

			//            System.out.println("joueur 1 :");
			//            listeJoueur.get(0).getPlateau().affichePlateau();
			//            System.out.println("joueur 2 :");
			//            listeJoueur.get(1).getPlateau().affichePlateau();
			//            System.out.println("joueur 3 :");
			//            listeJoueur.get(2).getPlateau().affichePlateau();
			//            System.out.println("joueur 4 :");
			//            listeJoueur.get(3).getPlateau().affichePlateau();
		}
		//        pioche.afficheTirageRetournee();
		//        System.out.println("Carte joueur 1 :");
		//        listeJoueur.get(0).afficheListeDomino();
		//        System.out.println("Carte joueur 2 :");
		//        listeJoueur.get(1).afficheListeDomino();
		//        System.out.println("Carte joueur 3 :");
		//        listeJoueur.get(2).afficheListeDomino();
		//        System.out.println("Carte joueur 4 :");
		//        listeJoueur.get(3).afficheListeDomino();

		//        System.out.println("joueur 1 :");
		//        listeJoueur.get(0).getPlateau().affichePlateau();
		//        System.out.println("joueur 2 :");
		//        listeJoueur.get(1).getPlateau().affichePlateau();
		//        System.out.println("joueur 3 :");
		//        listeJoueur.get(2).getPlateau().affichePlateau();
		//        System.out.println("joueur 4 :");
		//        listeJoueur.get(3).getPlateau().affichePlateau();
	}

	public PiocheModel getPioche() {
		return pioche;
	}

	public ArrayList<JoueurModel> getListeJoueur()
	{
		return listeJoueur;
	}

	public void ajouterJoueur() {
	}

	public void parametrerPartie() {
	}

	public void sauvegarderPartie() {
//		try {
//			PGSimpleDataSource ds = new PGSimpleDataSource();
//
//			ds.setServerName("localhost");
//			ds.setDatabaseName("m4106");
//			ds.setUser("postgres");
//			ds.setPassword("postgres");//VOTRE MDP!!!
//			Connection con = ds.getConnection();
//
//			try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Partie VALUES(?,?,?,?,?,?);")){
//				stmt.setInt(1, /*this.idPartie*/1);
//				stmt.setInt(2, /*this.nbJoueur*/4);
//				stmt.setInt(3, /*this.nbIA*/0);
//				stmt.setInt(4, /*this.minuteurPartie*/1200);
//				stmt.setInt(5, /*this.minuteurTour*/30);
//				stmt.setInt(6, /*this.nbDominos*/48);
//			}

			/*try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Joueur VALUES(?,(SELECT MAX(idPartie) FROM Partie),?);")){
				stmt.setInt(1, this.idTour);
				//stmt.setInt(2, this.partieEnCours.getId());
				stmt.setInt(2, this.nbTourRestant);
			}

			try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Tour VALUES(?,(SELECT MAX(idPartie) FROM Partie),?);")){
				stmt.setInt(1, this.idTour);
				//stmt.setInt(2, this.partieEnCours.getId());
				stmt.setInt(2, this.nbTourRestant);
			}

			try (PreparedStatement stmt = con.prepareStatement("INSERT INTO StatJeu VALUES(?,(SELECT MAX(idPartie) FROM Partie),?);")){
				stmt.setInt(1, this.idTour);
				//stmt.setInt(2, this.partieEnCours.getId());
				stmt.setInt(2, this.nbTourRestant);
			}

			try (PreparedStatement stmt = con.prepareStatement("INSERT INTO TerrainType VALUES(?,(SELECT MAX(idPartie) FROM Partie),?);")){
				stmt.setInt(1, this.idTour);
				//stmt.setInt(2, this.partieEnCours.getId());
				stmt.setInt(2, this.nbTourRestant);
			}

			try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Paysage VALUES(?,(SELECT MAX(idPartie) FROM Partie),?);")){
				stmt.setInt(1, this.idTour);
				//stmt.setInt(2, this.partieEnCours.getId());
				stmt.setInt(2, this.nbTourRestant);
			}*/
//
//		} catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
	}

	public void rejouerPartie() {
	}

	public void revoirPartie() {
	}

	public void reprendrePartie() {
	}

	public void quitterPartie() {
	}

}
