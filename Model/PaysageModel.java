package Application.Model;

public class PaysageModel
{
	private TerrainType terrainType;
	private int nbCouronne;

	public PaysageModel(TerrainType terrain, int nbC)         //Constructeur
	{
		terrainType = terrain;
		nbCouronne = nbC;
	}

	public TerrainType getTerrainType() {
		return terrainType;
	}

	public String getNomTerrain()
	{
		return String.valueOf(terrainType);
	}

	public int getNbCouronne() {
		return nbCouronne;
	}

	public void setNbCouronne(int nbCouronne) {
		this.nbCouronne = nbCouronne;
	}

	public String toString() {
		return "PaysageModel [terrainType=" + terrainType + ", nbCouronne=" + nbCouronne + "]";
	}


}
