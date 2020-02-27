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

	//    public int getX() {
	//        return x;
	//    }
	//
	//    public void setX(int x) {
	//        this.x = x;
	//    }
	//
	//    public int getY() {
	//        return y;
	//    }
	//
	//    public void setY(int y) {
	//        this.y = y;
	//    }

}
