package Application.Model;

import java.util.ArrayList;

public class PlateauModel
{
	private int idPlateau;
	private Object[][] plateau;
	private static int nbPlateau = 0;
	private ArrayList<DominoModel> listeDomino;
	private int[][] casePrise;
	private static int nbCasePrise = 0;


	public PlateauModel() { // Constructeur
		if (nbPlateau < 4)
		{
			idPlateau = nbPlateau;
			nbPlateau++;
			plateau = new Object[9][9];
			plateau[4][4] = new ChateauModel();
			listeDomino = new ArrayList<>();
			casePrise = new int[11][2];
		}
	}

	public int getIdPlateau()               // Pour avoir l'ID du tableau
	{
		return this.idPlateau;
	}

	public boolean ajouteDomino(DominoModel e, int x, int y, String sens)              //M�thode pour ajouter un �l�ment au plateau
	{
		if (plateau[x][y] == null)
		{
			if(sens == "H" && plateau[x-1][y] == null)
			{
				e.setX1(x);
				e.setY1(y);
				e.setX2(x-1);
				e.setY2(y);
				e.setSens(sens);
				plateau[x][y] = e.getPaysage1();
				plateau[x-1][y] = e.getPaysage2();
				ajouterCasePrise(x, y);
				ajouterCasePrise(x-1, y);
				listeDomino.add(e);
				return true;
			}
			else if(sens == "G" && plateau[x][y-1] == null)
			{
				e.setX1(x);
				e.setY1(y);
				e.setX2(x);
				e.setY2(y-1);
				e.setSens(sens);
				plateau[x][y] = e.getPaysage1();
				plateau[x][y-1] = e.getPaysage2();
				ajouterCasePrise(x, y);
				ajouterCasePrise(x, y-1);
				listeDomino.add(e);
				return true;
			}
			else if(sens == "B" && plateau[x+1][y] == null)
			{
				e.setX1(x);
				e.setY1(y);
				e.setX2(x+1);
				e.setY2(y);
				e.setSens(sens);
				plateau[x][y] = e.getPaysage1();
				plateau[x+1][y] = e.getPaysage2();
				ajouterCasePrise(x, y);
				ajouterCasePrise(x+1, y);
				listeDomino.add(e);
				return true;
			}
			else if(sens == "D" && plateau[x][y+1] == null)
			{
				e.setX1(x);
				e.setY1(y);
				e.setX2(x);
				e.setY2(y+1);
				e.setSens(sens);
				plateau[x][y] = e.getPaysage1();
				plateau[x][y+1] = e.getPaysage2();
				ajouterCasePrise(x, y);
				ajouterCasePrise(x, y+1);
				listeDomino.add(e);
				return true;
			}
			else
			{
				//                System.out.println("Cas 2 non disponible ou erreur sens");
				return false;
			}
		}
		else
		{
			//            System.out.println(" case : " + x + ", " + y + " non disponible");
			return false;
		}
	}

	public Object getElement(int x, int y)
	{
		return plateau[x][y];
	}

	public void affichePlateau()
	{
		for(int i = 0; i <9; i++)
		{
			for(int j = 0; j <9; j++)
			{
				if(getElement(i,j) != null)
				{
					if (getElement(i,j) instanceof PaysageModel)
					{
						DominoModel d = getDomino(i, j);
						if((getElement(i,j) == d.getPaysage2()) && (d.getPaysage2().getNbCouronne() != 0))
						{
							System.out.print("D" + d.getNumDomino() + (((PaysageModel) getElement(i,j)).getNomTerrain()) +
									d.getPaysage2().getNbCouronne() + " | ");
						}
						else if((getElement(i,j) == d.getPaysage2()) && (d.getPaysage2().getNbCouronne() == 0))
						{
							System.out.print("D" + d.getNumDomino() + (((PaysageModel) getElement(i,j)).getNomTerrain()) + " | ");
						}
						else
						{
							System.out.print("D" + d.getNumDomino() + (((PaysageModel) getElement(i,j)).getNomTerrain()) + " | ");
						}
					}
					else
					{
						System.out.print(" ____C"+(((ChateauModel)getElement(i, j)).getIdChateau()) + "____ | ");
					}
				}
				else
				{
					System.out.print(" __________ | ");
				}
			}
			System.out.println();
		}
	}

	public DominoModel getDomino(int x, int y)
	{
		DominoModel d = null;
		for(int i = 0; i < listeDomino.size(); i++)
		{
			if(((listeDomino.get(i).getX1() == x) && (listeDomino.get(i).getY1() == y)
					|| (listeDomino.get(i).getX2() == x) && (listeDomino.get(i).getY2() == y)))
			{
				d = listeDomino.get(i);
			}
		}
		if(d == null)
		{
			System.out.println("ERREUR GET DOMINO");
		}
		return d;
	}

	public int[][] getCasePrise() {
		return casePrise;
	}

	public void ajouterCasePrise(int x, int y)
	{
		//        casePrise[nbCasePrise][0] = x;
		//        casePrise[nbCasePrise][1] = y;
		nbCasePrise++;
	}

	public boolean caseDisponible(int x, int y, String s)
	{
		if(plateau[x][y] == null)
		{
			if(s == "G" && plateau[x][y-1] == null)
			{
				return true;
			}
			else if(s == "D" && plateau[x][y+1] == null)
			{
				return true;
			}
			else if(s == "H" && plateau[x-1][y] == null)
			{
				return true;
			}
			else if(s == "B" && plateau[x+1][y] == null)
			{
				return true;
			}
			else return false;
		}
		else
		{
			return false;
		}
	}
}
