package Application.Model;

import java.util.ArrayList;

public class JoueurModel
{
	//    private Color couleur;
	//    private String pseudo;
	    private boolean joueurIA;
	//    private int point;
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
		
		/*try {
			PGSimpleDataSource ds = new PGSimpleDataSource();

			ds.setServerName(CBDD.getServerName());
			ds.setDatabaseName(CBDD.getDatabaseName());
			ds.setUser(CBDD.getUser());
			ds.setPassword(CBDD.getPassword());
			Connection con = ds.getConnection();

			try (PreparedStatement stmt = con.prepareStatement("INSERT INTO Joueur VALUES(?,?,?,?);")){
				stmt.setInt(1, this.idJoueur);
				stmt.setString(2, this.couleur);
				stmt.setBoolean(3, false);
				stmt.setInt(4, this.point);
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}*/
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

	public boolean isJoueurIA() {
		return joueurIA;
	}

	public void setJoueurIA(boolean joueurIA) {
		this.joueurIA = joueurIA;
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
