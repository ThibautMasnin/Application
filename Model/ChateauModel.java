package Application.Model;

public class ChateauModel extends ElementModel
{
	//    private int idChateau;
	private static int compteur = 1;
	//    private int x;
	//    private int y;

	public ChateauModel()
	{
		super("C");
		super.setIdElement(compteur);
		compteur++;
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
