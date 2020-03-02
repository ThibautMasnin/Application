package Application.Model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Deck extends Rectangle {
	private int nbDominos;
	private ArrayList<DominoModel> listeDominos;

	public Deck(){
		super(100, 100, 100, 50);
		nbDominos = 48;
		listeDominos = new ArrayList<DominoModel>();

		DominoModel d1 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D01d.jpg", 1);
		DominoModel d2 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D02d.jpg", 2);
		DominoModel d3 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D03d.jpg", 3);
		DominoModel d4 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D04d.jpg", 4);
		DominoModel d5 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D05d.jpg", 5);
		DominoModel d6 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D06d.jpg", 6);
		DominoModel d7 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D07d.jpg", 7);
		DominoModel d8 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D08d.jpg", 8);
		DominoModel d9 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D09d.jpg", 9);
		DominoModel d10 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D10d.jpg", 10);
		DominoModel d11 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D11d.jpg", 11);
		DominoModel d12 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D12d.jpg", 12);
		DominoModel d13 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D13d.jpg", 13);
		DominoModel d14 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D14d.jpg", 14);
		DominoModel d15 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D15d.jpg", 15);
		DominoModel d16 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D16d.jpg", 16);
		DominoModel d17 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D17d.jpg", 17);
		DominoModel d18 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D18d.jpg", 18);
		DominoModel d19 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D19d.jpg", 19);
		DominoModel d20 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D20d.jpg", 20);
		DominoModel d21 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D21d.jpg", 21);
		DominoModel d22 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D22d.jpg", 22);
		DominoModel d23 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D23d.jpg", 23);
		DominoModel d24 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D24d.jpg", 24);
		DominoModel d25 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D25d.jpg", 25);
		DominoModel d26 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D26d.jpg", 26);
		DominoModel d27 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D27d.jpg", 27);
		DominoModel d28 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D28d.jpg", 28);
		DominoModel d29 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D29d.jpg", 29);
		DominoModel d30 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D30d.jpg", 30);
		DominoModel d31 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D31d.jpg", 31);
		DominoModel d32 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D32d.jpg", 32);
		DominoModel d33 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D33d.jpg", 33);
		DominoModel d34 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D34d.jpg", 34);
		DominoModel d35 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D35d.jpg", 35);
		DominoModel d36 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D36d.jpg", 36);
		DominoModel d37 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D37d.jpg", 37);
		DominoModel d38 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D38d.jpg", 38);
		DominoModel d39 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D39d.jpg", 39);
		DominoModel d40 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D40d.jpg", 40);
		DominoModel d41 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D41d.jpg", 41);
		DominoModel d42 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D42d.jpg", 42);
		DominoModel d43 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D43d.jpg", 43);
		DominoModel d44 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D44d.jpg", 44);
		DominoModel d45 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D45d.jpg", 45);
		DominoModel d46 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D46d.jpg", 46);
		DominoModel d47 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D47d.jpg", 47);
		DominoModel d48 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D48d.jpg", 48);

		listeDominos.add(d1);
		listeDominos.add(d2);
		listeDominos.add(d3);
		listeDominos.add(d4);
		listeDominos.add(d5);
		listeDominos.add(d6);
		listeDominos.add(d7);
		listeDominos.add(d8);
		listeDominos.add(d9);
		listeDominos.add(d10);
		listeDominos.add(d11);
		listeDominos.add(d12);
		listeDominos.add(d13);
		listeDominos.add(d14);
		listeDominos.add(d15);
		listeDominos.add(d16);
		listeDominos.add(d17);
		listeDominos.add(d18);
		listeDominos.add(d19);
		listeDominos.add(d20);
		listeDominos.add(d21);
		listeDominos.add(d22);
		listeDominos.add(d23);
		listeDominos.add(d24);
		listeDominos.add(d25);
		listeDominos.add(d26);
		listeDominos.add(d27);
		listeDominos.add(d28);
		listeDominos.add(d29);
		listeDominos.add(d30);
		listeDominos.add(d31);
		listeDominos.add(d32);
		listeDominos.add(d33);
		listeDominos.add(d34);
		listeDominos.add(d35);
		listeDominos.add(d36);
		listeDominos.add(d37);
		listeDominos.add(d38);
		listeDominos.add(d39);
		listeDominos.add(d40);
		listeDominos.add(d41);
		listeDominos.add(d42);
		listeDominos.add(d43);
		listeDominos.add(d44);
		listeDominos.add(d45);
		listeDominos.add(d46);
		listeDominos.add(d47);
		listeDominos.add(d48);

		this.setFill(new ImagePattern(new Image(getFirstDominoD())));
	}

	public ArrayList<DominoModel> getListeDominos() {
		return listeDominos;
	}

	public int getNbDominos() {
		return nbDominos;
	}

	public int getSize(){
		return listeDominos.size();
	}


	public Deck setFill(String f){
		this.setFill(new ImagePattern(new Image(f)));

		return this;
	}


	public DominoModel getDomino(int i){
		return listeDominos.get(i);
	}

	public DominoModel getLastDomino(){
		return listeDominos.get(listeDominos.size()-1);
	}


	public String getFirstDomino(){
		return modifURL(listeDominos.get(listeDominos.size()-1).getUrl());
	}

	public int getNumFirstDomino() {
		return listeDominos.get(listeDominos.size()-1).getNumDomino();}

	public String getFirstDominoD(){
		return listeDominos.get(listeDominos.size()-1).getUrl();
	}


	public String modifURL(String s){
		String tmp2 = s.substring(0, 34);
		String tmp3 = s.substring(35,39);

		return tmp2 + tmp3;
	}


	public void melangerDeck()
	{
		Random r = new Random();
		int index = 0;
		ArrayList<DominoModel> listeDominosTmp = new ArrayList<>();

		for(int i = 0; i < 48; i++)
		{
			index = r.nextInt(listeDominos.size());
			listeDominosTmp.add(listeDominos.get(index));
			listeDominos.remove(index);
		}
		listeDominos = listeDominosTmp;
		this.setFill(getFirstDominoD());
	}


	public void triDomino(ArrayList<DominoModel> ld){
		ArrayList<DominoModel> listeTemp = new ArrayList<DominoModel>();

		DominoModel dominoTemp = new DominoModel();
		DominoModel dominoTemp2 = new DominoModel();

		int numTemp;
		int j;


		for (int i = 0 ; i < 4 ; i++){
			numTemp = ld.get(i).getNumDomino();
			dominoTemp = ld.get(i);
			j = i;

			while (j > 0 && ld.get(j-1).getNumDomino() > numTemp){
				dominoTemp2 = ld.get(j-1);
				j--;
			}

			listeTemp.add(dominoTemp2);
		}

		ld = listeTemp;

	}



}
