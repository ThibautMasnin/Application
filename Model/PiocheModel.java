package Application.Model;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.sql.*;
import org.postgresql.ds.*;

public class PiocheModel
{
	private PartieModel partieEnCours;
	private ArrayList<DominoModel> pioche = null;
	private ArrayList<DominoModel> tirageCache;     //Les dominos pour le tour d'apres, on les enverra dans tirageRetournee
	private ArrayList<DominoModel> tirageRetourne;      //Le joueur chosit un domino parmis celle propose ici

	//On crée la pioche avec getPioche car c'est un singleton car on ne peut avoir plusieurs pioches
	public ArrayList<DominoModel> getPioche(PartieModel partieModel)
	{
		if(pioche == null)
		{
			partieEnCours = partieModel;
			pioche = new ArrayList<>();
			tirageCache = new ArrayList<>();
			tirageRetourne = new ArrayList<>();
		}
		else
		{
			System.out.println("deja une pioche");
		}
		return pioche;
	}

	//M�thode pour lire le mdp dans un fichier txt, plutot que de le mettre en clair dans le code
	public String getPwd() {
		String chemin = "C:\\Users\\kevin\\eclipse-workspace\\Kingdomino\\password.txt"; // Mettre ici le chemin du fichier txt ou se trouve le mdp pour se co a la bdd
		String password = "";
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(chemin)));
			StringWriter out = new StringWriter();
			int b;
			while ((b=in.read()) != -1)
				out.write(b);
			out.flush();
			password = out.toString();
			out.close();
			in.close();
		} catch (Exception ex){
			System.err.println("Error. "+ex.getMessage());
		}
		return password;
	}

	public void creerPioche() throws SQLException
	{
		String password = getPwd(); // regarder la methode getPwd, sinon mettre directement en clair le mdp pour se co a la bdd (pour les flemmards :p)
		//Variables n�cessaires pour r�cup�rer les donn�es des requ�tes
		List<Integer> idPaysage = new ArrayList<>(); // Liste avec tous les ID des paysages
		int idPaysage1;
		int idPaysage2;

		List<Integer> id_CouPaysages = new ArrayList<>(); // Liste avec les couronnes et les id des terrains pour chaques paysages
		int nbCouronne;
		int idTerrainType;

		List<String> terrainType = new ArrayList<>(); // Liste avec tous les ID des terrains pour chaque paysage
		String terrain;

		//Connexion � la BDD
		try {	
			PGSimpleDataSource ds = new PGSimpleDataSource();

			ds.setServerName("localhost");
			ds.setDatabaseName("m4106");
			ds.setUser("postgres");
			ds.setPassword(password);
			Connection con = ds.getConnection();

			//Requ�te pour r�cup�rer ce que contient la table Domino
			try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Domino;")){
				try (ResultSet rs = stmt.executeQuery()){
					while (rs.next()) { // R�cup�ration pour tout les dominos dans la BDD
						idPaysage1 = rs.getInt("idPaysage1"); // R�cup�ration de l'id du paysage1 de chaque domino � la liste des ID
						idPaysage2 = rs.getInt("idPaysage2"); // R�cup�ration de l'id du paysage2 de chaque domino � la liste des ID
						idPaysage.add(idPaysage1); // Ajout de l'id du paysage1 de chaque domino � la liste des ID
						idPaysage.add(idPaysage2); // Ajout de l'id du paysage2 de chaque domino � la liste des ID
					}
				}
			}

			//Requ�te pour r�cup�rer tout les paysages ET les couronnes des paysages dans la table Paysage
			try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM TerrainType JOIN Paysage USING(idTerrainType) WHERE idPaysage = ?;")){
				for (int i=0 ; i<96 ; i++) { //R�cup�ration de tous les paysages
					stmt.setInt(1, idPaysage.get(i));// ? = l'index de la liste idPaysages
					try (ResultSet rs = stmt.executeQuery()){
						while(rs.next()) {
							idTerrainType = rs.getInt("idTerrainType");// R�cup�ration de l'id du terrain associ� au paysage
							nbCouronne = rs.getInt("nbCouronne");// R�cup�ration ds couronnes associ� au paysage
							id_CouPaysages.add(idTerrainType);//Ajout � la liste concernant les paysages
							id_CouPaysages.add(nbCouronne);//Ajout � la liste concernant les paysages
						}
					}
				}
			}

			/*for(int j = 0; j < 192; j = j+4) {
				if(j % 4 == 0) {
					System.out.println("Domino " + ((j/4) + 1) + " : ");
				}
				System.out.println("Paysage 1 : " + id_CouPaysages.get(j) + "\t nbCouronnes : " + id_CouPaysages.get(j+1));
				System.out.println("Paysage 2 : " + id_CouPaysages.get(j+2) + "\t nbCouronnes : " + id_CouPaysages.get(j+3) + "\n\n");
			}*/

			//Requ�te pour r�cup�rer les terrains
			try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM TerrainType WHERE idTerrainType = ? ; ")){
				for (int i=0 ; i<96 ; i++) {//96 pour r�cup�rer tous les terrains des 96 paysages
					int j = i*2;
					stmt.setInt(1, id_CouPaysages.get(j));
					try (ResultSet rs = stmt.executeQuery()){
						while(rs.next()) {
							terrain = rs.getString("terrain");
							terrainType.add(terrain);
						}
					}
					//System.out.println(terrainType.get(i));
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}

		for (int i=0 ; i<192 ; i = i + 4) {
			//Variables pour la cr�ation d'un domino
			PaysageModel paysage1 = null ;
			PaysageModel paysage2 = null ;
			//Pour le paysage 1 du domino
			if(terrainType.get(i/2).equals("CHAMP")) {
				paysage1 = new PaysageModel(TerrainType.CHAMP,id_CouPaysages.get(i+1));
			}
			else if(terrainType.get(i/2).equals("FORET")) {
				paysage1 = new PaysageModel(TerrainType.FORET,id_CouPaysages.get(i+1));
			}
			else if(terrainType.get(i/2).equals("LAC")) {
				paysage1 = new PaysageModel(TerrainType.LAC,id_CouPaysages.get(i+1));
			}
			else if(terrainType.get(i/2).equals("MARAIS")) {
				paysage1 = new PaysageModel(TerrainType.MARAIS,id_CouPaysages.get(i+1));
			}
			else if(terrainType.get(i/2).equals("MINE")) {
				paysage1 = new PaysageModel(TerrainType.MINE,id_CouPaysages.get(i+1));
			}
			else if(terrainType.get(i/2).equals("PRAIRIE")) {
				paysage1 = new PaysageModel(TerrainType.PRAIRIE,id_CouPaysages.get(i+1));
			}

			//Pour le paysage 2 du domino
			if(terrainType.get(i/2+1).equals("CHAMP")) {
				paysage2 = new PaysageModel(TerrainType.CHAMP,id_CouPaysages.get(i+3));
			}
			else if(terrainType.get(i/2+1).equals("FORET")) {
				paysage2 = new PaysageModel(TerrainType.FORET,id_CouPaysages.get(i+3));
			}
			else if(terrainType.get(i/2+1).equals("LAC")) {
				paysage2 = new PaysageModel(TerrainType.LAC,id_CouPaysages.get(i+3));
			}
			else if(terrainType.get(i/2+1).equals("MARAIS")) {
				paysage2 = new PaysageModel(TerrainType.MARAIS,id_CouPaysages.get(i+3));
			}
			else if(terrainType.get(i/2+1).equals("MINE")) {
				paysage2 = new PaysageModel(TerrainType.MINE,id_CouPaysages.get(i+3));
			}
			else if(terrainType.get(i/2+1).equals("PRAIRIE")){
				paysage2 = new PaysageModel(TerrainType.PRAIRIE,id_CouPaysages.get(i+3));
			}

			/*if(i % 4 == 0) {
				System.out.println("Domino " + ((i/4) + 1) + " : ");
			}

			System.out.print("Paysage 1 : " + paysage1.toString());
			System.out.println("\t Paysage 2 : " + paysage2.toString() + "\n");
			 */
			pioche.add(new DominoModel(paysage1,paysage2));
		}
		System.out.println("\n");
		melangerPioche();
	}

	public void affichePioche()
	{
		System.out.println("-------------------------------------------------------------------");
		System.out.println("pioche :"+pioche.size() + "\n");
		for(int i=0; i < pioche.size(); i++)
		{
			System.out.println("Domino " + (i+1) + "\n paysage 1 " + pioche.get(i).getPaysage1().getNomTerrain()+" | Nb couronne : "+pioche.get(i).getPaysage1().getNbCouronne()+
					"\n paysage 2 " + pioche.get(i).getPaysage2().getNomTerrain()+" | Nb couronne : "+pioche.get(i).getPaysage2().getNbCouronne()+ "\n\n");
		}
	}

	public void tirage() {

		if (tirageRetourne.size() == 0) {
			if (partieEnCours.getListeJoueur().size() != 2) {
				for (int i = 0; i < partieEnCours.getListeJoueur().size(); i++)      //On prend les 4 premiers de la pioche melange et on la met dans tirageRetournee
				{
					tirageRetourne.add(pioche.get(0));
					pioche.remove(0);
				}
				for (int i = 0; i < partieEnCours.getListeJoueur().size(); i++)      //On fait de meme pour tirageCachee
				{
					tirageCache.add(pioche.get(0));
					pioche.remove(0);
				}
			} else {
				for (int i = 0; i < 4; i++)      //On prend les 4 premiers de la pioche melange et on la met dans tirageRetournee
				{
					tirageRetourne.add(pioche.get(0));
					pioche.remove(0);
				}
				for (int i = 0; i < 4; i++)      //On fait de meme pour tirageCachee
				{
					tirageCache.add(pioche.get(0));
					pioche.remove(0);
				}
			}

		} else {

			tirageRetourne.removeAll(tirageRetourne);           //Les dominos ont ete choisis donc on vide la liste
			tirageRetourne.addAll(tirageCache);                 //On deplace les dominos de tirageCachee dans retournee
			tirageCache.removeAll(tirageCache);                 //...
			if (pioche.size() != 0)              //S'il reste encore des dominos on prepare le tour d'apres donc on remplis tirageCachee
			{
				if (partieEnCours.getListeJoueur().size() != 2) {
					for (int i = 0; i < partieEnCours.getListeJoueur().size(); i++) {
						tirageCache.add(pioche.get(0));
						pioche.remove(0);
					}
				} else {
					for (int i = 0; i < 4; i++) {
						tirageCache.add(pioche.get(0));
						pioche.remove(0);
					}
				}
			}
		}
	}

	public void afficheTirageRetournee()
	{
		triDomino(tirageRetourne);
		System.out.println("|||||Les retourn�s|||||");
		for(int i=0; i < tirageRetourne.size(); i++)
		{
			if(tirageRetourne.get(i).getNumDomino()<10) {
				System.out.println("[Domino n�0" + tirageRetourne.get(i).getNumDomino() +"] ------ "+ tirageRetourne.get(i).toStringPaysage());
			}
			else {
				System.out.println("[Domino n�" + tirageRetourne.get(i).getNumDomino() +"] ------ "+ tirageRetourne.get(i).toStringPaysage());
			}
		}
		System.out.println("\n\n");
	}

	public void afficheTirageCachee()
	{
		triDomino(tirageCache);
		System.out.println("|||||Les cach�s|||||");
		for(int i=0; i < tirageCache.size(); i++)
		{
			System.out.println("[Domino n�" + tirageCache.get(i).getNumDomino()+ "]") ;
		}
		System.out.println("\n\n");
	}

	public ArrayList<DominoModel> getTirageRetourne()
	{
		return tirageRetourne;
	}

	public ArrayList<DominoModel> getTirageCache()
	{
		return tirageCache;
	}

	public ArrayList<DominoModel> triDomino(ArrayList<DominoModel> ld){
		ArrayList<DominoModel> listT = ld;
		boolean good = false;
		while(good==false){
			for(int i = 0 ; i<listT.size();i++) {
				DominoModel tmp;
				DominoModel tmp2;
				if (i<listT.size()-1) {
					if(listT.get(i).getNumDomino() > listT.get(i+1).getNumDomino()) {
						tmp = listT.get(i);
						tmp2 = listT.get(i+1);
						listT.remove(i);
						listT.add(i, tmp2);

						listT.remove(i+1);
						listT.add(i+1, tmp);
					}
					/*for (int j = 0 ; j<listT.size();j++) {
						System.out.println(listT.get(j).getNumDomino());
					}*/
				}

				else if (i == listT.size()-1) {
					if (listT.get(i).getNumDomino()<listT.get(0).getNumDomino()) {
						tmp = listT.get(i);
						tmp2 = listT.get(0);

						listT.remove(i);
						listT.add(i,tmp2);

						listT.remove(0);
						listT.add(0, tmp);
						/*for (int j = 0 ; j<listT.size();j++) {
							System.out.println(listT.get(j).getNumDomino());
						}*/
					}
				}

			}
			if (listT.get(0).getNumDomino() < listT.get(1).getNumDomino() && listT.get(1).getNumDomino() < listT.get(2).getNumDomino() && listT.get(2).getNumDomino() < listT.get(3).getNumDomino() && listT.get(0).getNumDomino() < listT.get(3).getNumDomino() ){
				good = true;
			}
		}
		return listT;
	}

	public void melangerPioche()
	{
		Random r = new Random();
		int var = 0;
		ArrayList<DominoModel> piocheTmp = new ArrayList<>();
		for(int i = 0; i < 48; i++)
		{
			var = r.nextInt(pioche.size());
			piocheTmp.add(pioche.get(var));
			pioche.remove(var);
		}
		pioche = piocheTmp;
	}

}
