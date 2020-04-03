package Application.Model;

public class ChateauModel
{
	private int idChateau;
	private static int compteur = 1;

	public ChateauModel()
	{

		idChateau = compteur;
		compteur++;
	}

	public int getIdChateau()
	{
		return idChateau;
	}
}
