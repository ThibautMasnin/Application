package Application.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.postgresql.ds.PGSimpleDataSource;

public class PartieModel
{
	private int nbJoueurs;
	private int nbIAs;
	private int joueur;
	private int nbTour;

	private ArrayList<JoueurModel> listeJoueur;     //On y trouvera les joueurs qu'on aura ajouté dans le constructeur
	private PiocheModel pioche;

	public PartieModel() throws SQLException
	{
		Scanner s = new Scanner(System.in);
		nbJoueurs = 0;
		nbIAs = 5;
		while(!(nbJoueurs >= 2 && nbJoueurs <= 4))
		{
			System.out.println("Veuillez entrer le nombre de joueur total (entre 2 et 4) : ");
			nbJoueurs = s.nextInt();
		}

		while(!(nbIAs >= 0 && nbIAs <= nbJoueurs))
		{
			System.out.println("Veuillez entrer le nombre de joueur IA (entre 0 et " + nbJoueurs + ") : ");
			nbIAs = s.nextInt();
		}


								//On crée la pioche vide pour l'instant avec getPioche car c'est un singleton car on ne peut avoir plusieurs pioche
		pioche = new PiocheModel();
		pioche.getPioche(this);
								//On crée les joueur et les ajoute dans la liste qu'on utilisera
		listeJoueur = new ArrayList<>();
		for(int i = 0; i < nbJoueurs; i++)
		{
			listeJoueur.add(new JoueurModel(i+1));
		}
		for(int i = 0; i < nbIAs; i++)
		{
			listeJoueur.get(i).setJoueurIA(true);
		}
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public void setNbIAs(int nbIAs) {
		this.nbIAs = nbIAs;
	}

	public void setJoueur(int joueur) {
		this.joueur = joueur;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
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
		}
		for(int j = 0; j < listeJoueur.size(); j++)
		{
			System.out.println("Le joueur " + (j+1) + " a " + listeJoueur.get(j).calculePoint() + " couronnes");
			System.out.println("Le joueur " + (j+1) + " a " + listeJoueur.get(j).calculePoint2() + " points \n");
		}

		this.sauvegarderPartie();
	}

	public PiocheModel getPioche() {
		return pioche;
	}
	
	public ArrayList<JoueurModel> getListeJoueur()
	{
		return listeJoueur;
	}

	public void sauvegarderPartie() {
		try {
			Connexion CBDD = new Connexion();

			PGSimpleDataSource ds = new PGSimpleDataSource();

			ds.setServerName(CBDD.getServerName());
			ds.setDatabaseName(CBDD.getDatabaseName());
			ds.setUser(CBDD.getUser());
			ds.setPassword(CBDD.getPassword());
			Connection con = ds.getConnection();

			try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Partie VALUES(DEFAULT,?,?,?,?,?);")){
				stmt.setInt(1, this.nbJoueurs);
				stmt.setInt(2, this.nbIAs);
				stmt.setInt(3, this.joueur);
				stmt.setInt(4, this.nbTour);
				stmt.setInt(5, 0);
				stmt.executeQuery();
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
