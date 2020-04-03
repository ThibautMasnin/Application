package Application.Model;

public class PaysageModel
{
	private TerrainType terrainType;
	private int nbCouronne;
	private boolean isCheck;
	public PaysageModel(TerrainType terrain, int nbC)         //Constructeur
	{
		terrainType = terrain;
		nbCouronne = nbC;
		this.isCheck = false;
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
		return "Paysage[Terrain=" + terrainType + ", nbCouronne=" + nbCouronne + "]";
	}
	
	public boolean checked() {
		return this.isCheck;
	}
	
	public void setCheck(boolean b) {
		this.isCheck = b;
	}
}
