package Application.Model;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.sql.*;
import org.postgresql.ds.*;

public class PiocheModel
{
	private ArrayList<DominoModel> pioche = null;
	private ArrayList<DominoModel> tirageCache;     //Les dominos pour le tour d'apres, on les enverra dans tirageRetournee
	private ArrayList<DominoModel> tirageRetourne;      //Le joueur chosit un domino parmis celle propose ici

	//On crée la pioche avec getPioche car c'est un singleton car on ne peut avoir plusieurs pioches
	public ArrayList<DominoModel> getPioche()
	{
		if(pioche == null)
		{
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

	public void creerPioche() throws SQLException
	{
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
			ds.setPassword("TON MDP");
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

		//TerrainType TT = 

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

			if(i % 4 == 0) {
				System.out.println("Domino " + ((i/4) + 1) + " : ");
			}

			System.out.print("Paysage 1 : " + paysage1.toString());
			System.out.println("\t Paysage 2 : " + paysage2.toString() + "\n");

			pioche.add(new DominoModel(paysage1,paysage2));
		}
		System.out.println("\n");
		//melangerPioche();
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

	public void tirage()
	{
		if(tirageRetourne.size() == 0)
		{
			for(int i = 0; i < 4; i++)      //On prend les 4 premiers de la pioche melange et on la met dans tirageRetournee
			{
				tirageRetourne.add(pioche.get(0));
				pioche.remove(0);
			}
			for(int i = 0; i < 4; i++)      //On fait de meme pour tirageCachee
			{
				tirageCache.add(pioche.get(0));
				pioche.remove(0);
			}
		}
		else
		{
			tirageRetourne.removeAll(tirageRetourne);           //Les dominos ont ete choisis donc on vide la liste
			tirageRetourne.addAll(tirageCache);                 //On deplace les dominos de tirageCachee dans retournee
			tirageCache.removeAll(tirageCache);                 //...
			if(pioche.size() != 0)              //S'il reste encore des dominos on prepare le tour d'apres donc on remplis tirageCachee
			{
				for(int i = 0; i < 4; i++)
				{
					tirageCache.add(pioche.get(0));
					pioche.remove(0);
				}
			}
		}
	}

	public void afficheTirageRetournee()
	{
		System.out.println("retournee : ");
		for(int i=0; i < tirageRetourne.size(); i++)
		{
			System.out.println(tirageRetourne.get(i).getIdElement() + " " + tirageRetourne.get(i).getPaysage1().getNomTerrain()
					+ " " +tirageRetourne.get(i).getPaysage1().getNbCouronne() +" "+tirageRetourne.get(i).getPaysage2().getNomTerrain() +" "+ tirageRetourne.get(i).getPaysage2().getNbCouronne());
		}
		System.out.println("\n\n");
	}

	public void afficheTirageCachee()
	{
		System.out.println("cachee : " + tirageCache.size());
		for(int i=0; i < tirageCache.size(); i++)
		{
			System.out.println(tirageCache.get(i).getIdElement() + " " + tirageCache.get(i).getPaysage1().getNomTerrain()
					+ " " + tirageCache.get(i).getPaysage1().getNbCouronne()+ " " +tirageCache.get(i).getPaysage2().getNomTerrain()+" " + tirageCache.get(i).getPaysage2().getNbCouronne());
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
