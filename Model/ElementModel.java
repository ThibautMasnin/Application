package Application.Model;

public abstract class ElementModel
{
	private String nom;
	private int idElement;

	ElementModel(String n)
	{
		nom = n;
	}

	public void setIdElement(int x)
	{
		idElement = x;
	}

	public int getIdElement()
	{
		return idElement;
	}

	public String getNom() {
		return nom;
	}
}