package Application.Model;

import java.util.ArrayList;

public class PlateauModel
{
	private int idPlateau;
	private Object[][] plateau;
	private static int nbPlateau = 0;
	private ArrayList<DominoModel> listeDomino;
	protected ArrayList<DominoModel> lesDominosAutour;
//	private int[][] casePrise;
//	private static int nbCasePrise = 0;


	public PlateauModel() { // Constructeur
		if (nbPlateau < 4)
		{
			idPlateau = nbPlateau;
			nbPlateau++;
			plateau = new Object[9][9];
			plateau[4][4] = new ChateauModel();
			listeDomino = new ArrayList<>();
//			casePrise = new int[11][2];
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
			if(sens == "H" && plateau[x-1][y] == null && emplacementValide(e, x, y, sens))
			{
				e.setX1(x);
				e.setY1(y);
				e.setX2(x-1);
				e.setY2(y);
				e.setSens(sens);
				plateau[x][y] = e.getPaysage1();
				plateau[x-1][y] = e.getPaysage2();
//				ajouterCasePrise(x, y);
//				ajouterCasePrise(x-1, y);
				listeDomino.add(e);
				return true;
			}
			else if(sens == "G" && plateau[x][y-1] == null && emplacementValide(e, x, y, sens))
			{
				e.setX1(x);
				e.setY1(y);
				e.setX2(x);
				e.setY2(y-1);
				e.setSens(sens);
				plateau[x][y] = e.getPaysage1();
				plateau[x][y-1] = e.getPaysage2();
//				ajouterCasePrise(x, y);
//				ajouterCasePrise(x, y-1);
				listeDomino.add(e);
				return true;
			}
			else if(sens == "B" && plateau[x+1][y] == null && emplacementValide(e, x, y, sens))
			{
				e.setX1(x);
				e.setY1(y);
				e.setX2(x+1);
				e.setY2(y);
				e.setSens(sens);
				plateau[x][y] = e.getPaysage1();
				plateau[x+1][y] = e.getPaysage2();
//				ajouterCasePrise(x, y);
//				ajouterCasePrise(x+1, y);
				listeDomino.add(e);
				return true;
			}
			else if(sens == "D" && plateau[x][y+1] == null && emplacementValide(e, x, y, sens))
			{
				e.setX1(x);
				e.setY1(y);
				e.setX2(x);
				e.setY2(y+1);
				e.setSens(sens);
				plateau[x][y] = e.getPaysage1();
				plateau[x][y+1] = e.getPaysage2();
//				ajouterCasePrise(x, y);
//				ajouterCasePrise(x, y+1);
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
//						if((getElement(i,j) == d.getPaysage2()) && (d.getPaysage2().getNbCouronne() != 0))
//						{
//							System.out.print("D" + d.getNumDomino() + (((PaysageModel) getElement(i,j)).getNomTerrain()) +
//									d.getPaysage2().getNbCouronne() + " | ");
//						}
//						else if((getElement(i,j) == d.getPaysage2()) && (d.getPaysage2().getNbCouronne() == 0))
//						{
//							System.out.print("D" + d.getNumDomino() + (((PaysageModel) getElement(i,j)).getNomTerrain()) + " | ");
//						}
						if(getElement(i,j) == d.getPaysage1())
						{
							System.out.print("D" + d.getNumDomino() + (((PaysageModel) getElement(i,j)).getNomTerrain()) +
									d.getPaysage1().getNbCouronne() + " | ");
						}
						else if(getElement(i,j) == d.getPaysage2())
						{
							System.out.print("D" + d.getNumDomino() + (((PaysageModel) getElement(i,j)).getNomTerrain()) +
									d.getPaysage2().getNbCouronne() + " | ");
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

	public PaysageModel getPaysage(int x, int y)
	{
		return (PaysageModel) plateau[x][y];
	}

//	public int[][] getCasePrise() {
//		return casePrise;
//	}

//	public void ajouterCasePrise(int x, int y)
//	{
//		//        casePrise[nbCasePrise][0] = x;
//		//        casePrise[nbCasePrise][1] = y;
//		nbCasePrise++;
//	}

	public boolean emplacementValide(DominoModel d, int x, int y, String sens)
	{
//			on regarde si un chateau ou un Domino est présent autour de l'endroit où l'on veut le placer

		return nextToChateau(x, y, sens) || nextToDomino(d, x, y, sens);
	}

	public boolean nextToChateau(int x, int y, String sens)
	{
		boolean bool = false;
		if(sens == "H") // Domino vers le haut
		{
//			on regarde si un chateau est présent autour de l'endroit où l'on veut le placer
			if((plateau[x][y-1] instanceof ChateauModel)
					|| (plateau[x+1][y] instanceof ChateauModel)
					|| (plateau[x][y+1] instanceof ChateauModel)
					|| (plateau[x-1][y+1] instanceof ChateauModel)
					|| (plateau[x-2][y] instanceof ChateauModel)
					|| (plateau[x-1][y-1] instanceof ChateauModel))
			{
				System.out.println("CHATEAAUUUU");
				bool = true;
			}
		}
		else if(sens == "G")
		{
			if((plateau[x-1][y] instanceof ChateauModel)
					|| (plateau[x][y+1] instanceof ChateauModel)
					|| (plateau[x+1][y] instanceof ChateauModel)
					|| (plateau[x+1][y-1] instanceof ChateauModel)
					|| (plateau[x][y-2] instanceof ChateauModel)
					|| (plateau[x-1][y-1] instanceof ChateauModel))
			{
				System.out.println("CHATEAAUUUU");
				bool = true;
			}
		}
		else if(sens == "D")
		{
			if((plateau[x-1][y] instanceof ChateauModel)
					|| (plateau[x][y-1] instanceof ChateauModel)
					|| (plateau[x+1][y] instanceof ChateauModel)
					|| (plateau[x+1][y+1] instanceof ChateauModel)
					|| (plateau[x][y+2] instanceof ChateauModel)
					|| (plateau[x+1][y-1] instanceof ChateauModel))
			{
				System.out.println("CHATEAAUUUU");
				bool = true;
			}
		}
		else if(sens == "B")
		{
			if((plateau[x][y-1] instanceof ChateauModel)
					|| (plateau[x-1][y] instanceof ChateauModel)
					|| (plateau[x][y+1] instanceof ChateauModel)
					|| (plateau[x+1][y+1] instanceof ChateauModel)
					|| (plateau[x+2][y] instanceof ChateauModel)
					|| (plateau[x+1][y-1] instanceof ChateauModel))
			{
				System.out.println("CHATEAAUUUU");
				bool = true;
			}
		}
		return bool;
	}

	public boolean nextToDomino(DominoModel d, int x, int y, String sens)
	{
		String nomPaysage1 = null;
		String nomPaysage2 = null;

		boolean bool = false;
		if(sens == "H") // Domino vers le haut
		{
			nomPaysage1 = d.getPaysage1().getNomTerrain();
			nomPaysage2 = d.getPaysage2().getNomTerrain();

//			if((plateau[x][y-1] instanceof PaysageModel)
//					|| (plateau[x+1][y] instanceof PaysageModel)
//					|| (plateau[x][y+1] instanceof PaysageModel)
//					|| (plateau[x-1][y+1] instanceof PaysageModel)
//					|| (plateau[x-2][y] instanceof PaysageModel)
//					|| (plateau[x-1][y-1] instanceof PaysageModel))
//			{
//				System.out.println("DOMINOOOO");
//				bool = true;
//			}


							//		COMPARAIIIISON PAYSAAAAGEEE
										if(plateau[x][y-1] instanceof PaysageModel)
										{
											if(getPaysage(x, y-1).getNomTerrain() == nomPaysage1) bool = true;
										}
										else if (plateau[x+1][y] instanceof PaysageModel)
										{
											if(getPaysage(x+1, y).getNomTerrain() == nomPaysage1) bool = true;
										}
										else if (plateau[x][y+1] instanceof PaysageModel)
										{
											if(getPaysage(x, y+1).getNomTerrain() == nomPaysage1) bool = true;
										}
										else if (plateau[x-1][y+1] instanceof PaysageModel)
										{
											if(getPaysage(x-1, y+1).getNomTerrain() == nomPaysage2) bool = true;
										}
										else if (plateau[x-2][y] instanceof PaysageModel)
										{
											if(getPaysage(x-2, y).getNomTerrain() == nomPaysage2) bool = true;
										}
										else if (plateau[x-1][y-1] instanceof PaysageModel)
										{
											if(getPaysage(x-1, y-1).getNomTerrain() == nomPaysage2) bool = true;
										}
		}
		else if(sens == "G")
		{
			if((plateau[x-1][y] instanceof PaysageModel)
					|| (plateau[x][y+1] instanceof PaysageModel)
					|| (plateau[x+1][y] instanceof PaysageModel)
					|| (plateau[x+1][y-1] instanceof PaysageModel)
					|| (plateau[x][y-2] instanceof PaysageModel)
					|| (plateau[x-1][y-1] instanceof PaysageModel))
			{
				System.out.println("DOMINOOOO");
				bool = true;
			}
		}
		else if(sens == "D")
		{
			if((plateau[x-1][y] instanceof PaysageModel)
					|| (plateau[x][y-1] instanceof PaysageModel)
					|| (plateau[x+1][y] instanceof PaysageModel)
					|| (plateau[x+1][y+1] instanceof PaysageModel)
					|| (plateau[x][y+2] instanceof PaysageModel)
					|| (plateau[x+1][y-1] instanceof PaysageModel))
			{
				System.out.println("DOMINOOOO");
				bool = true;
			}
		}
		else if(sens == "B")
		{
			if((plateau[x][y-1] instanceof PaysageModel)
					|| (plateau[x-1][y] instanceof PaysageModel)
					|| (plateau[x][y+1] instanceof PaysageModel)
					|| (plateau[x+1][y+1] instanceof PaysageModel)
					|| (plateau[x+2][y] instanceof PaysageModel)
					|| (plateau[x+1][y-1] instanceof PaysageModel))
			{
				System.out.println("DOMINOOOO");
				bool = true;
			}
		}
		return bool;
	}

//	public ArrayList<DominoModel> dominoAutour(int x, int y, String sens)
//	{
//		lesDominosAutour = new ArrayList<>();
//		if(sens == "H")
//		{
//			if(plateau[x][y-1] instanceof PaysageModel)
//			{
//				lesDominosAutour.add(getDomino(x, y-1));
//			}
//			else if (plateau[x+1][y] instanceof PaysageModel)
//			{
//				lesDominosAutour.add(getDomino(x+1, y));
//			}
//			else if (plateau[x][y+1] instanceof PaysageModel)
//			{
//				lesDominosAutour.add(getDomino(x, y+1));
//			}
//			else if (plateau[x-1][y+1] instanceof PaysageModel)
//			{
//				lesDominosAutour.add(getDomino(x-1, y+1));
//			}
//			else if (plateau[x-2][y] instanceof PaysageModel)
//			{
//				lesDominosAutour.add(getDomino(x-2, y));
//			}
//			else if (plateau[x-1][y-1] instanceof PaysageModel)
//			{
//				lesDominosAutour.add(getDomino(x-1, y-1));
//			}
//		}
//		return lesDominosAutour;
//	}
//
////	ici
//	public boolean comparePaysage(DominoModel d, int x, int y)
//	{
//		boolean correcte = false;
//		DominoModel dTmp = null;
//		int i = 0;
//		while(!correcte && i < lesDominosAutour.size())
//		{
//			dTmp = lesDominosAutour.get(i);
//			if((dTmp.getPaysage1().getNomTerrain() == d.getPaysage1().getNomTerrain()
//					&& (dTmp.getAbscisse1() == x-1 || dTmp.getAbscisse1() == x+1 || dTmp.getOrdonnee1() == y-1 || dTmp.getOrdonnee1() == y+1)))
//			{
//
//			}
//		}
//		return correcte;
//	}
}
