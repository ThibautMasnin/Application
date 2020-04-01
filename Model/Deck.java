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

		DominoModel d1 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D01d.jpg");
		DominoModel d2 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D02d.jpg");
		DominoModel d3 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D03d.jpg");
		DominoModel d4 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D04d.jpg");
		DominoModel d5 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D05d.jpg");
		DominoModel d6 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D06d.jpg");
		DominoModel d7 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D07d.jpg");
		DominoModel d8 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D08d.jpg");
		DominoModel d9 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D09d.jpg");
		DominoModel d10 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D10d.jpg");
		DominoModel d11 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D11d.jpg");
		DominoModel d12 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D12d.jpg");
		DominoModel d13 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D13d.jpg");
		DominoModel d14 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D14d.jpg");
		DominoModel d15 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D15d.jpg");
		DominoModel d16 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D16d.jpg");
		DominoModel d17 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D17d.jpg");
		DominoModel d18 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D18d.jpg");
		DominoModel d19 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D19d.jpg");
		DominoModel d20 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D20d.jpg");
		DominoModel d21 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D21d.jpg");
		DominoModel d22 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D22d.jpg");
		DominoModel d23 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D23d.jpg");
		DominoModel d24 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D24d.jpg");
		DominoModel d25 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D25d.jpg");
		DominoModel d26 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D26d.jpg");
		DominoModel d27 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D27d.jpg");
		DominoModel d28 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D28d.jpg");
		DominoModel d29 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D29d.jpg");
		DominoModel d30 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D30d.jpg");
		DominoModel d31 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D31d.jpg");
		DominoModel d32 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D32d.jpg");
		DominoModel d33 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D33d.jpg");
		DominoModel d34 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D34d.jpg");
		DominoModel d35 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D35d.jpg");
		DominoModel d36 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D36d.jpg");
		DominoModel d37 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D37d.jpg");
		DominoModel d38 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D38d.jpg");
		DominoModel d39 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D39d.jpg");
		DominoModel d40 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D40d.jpg");
		DominoModel d41 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D41d.jpg");
		DominoModel d42 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D42d.jpg");
		DominoModel d43 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D43d.jpg");
		DominoModel d44 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D44d.jpg");
		DominoModel d45 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D45d.jpg");
		DominoModel d46 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D46d.jpg");
		DominoModel d47 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D47d.jpg");
		DominoModel d48 = new DominoModel(0, 0, 100, 50, "Application/Ressources/Dominos/D48d.jpg");

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


	public String getFirstDomino(){
		return modifURL(listeDominos.get(listeDominos.size()-1).getUrl());
	}


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


	public ArrayList<DominoModel> triDomino(ArrayList<DominoModel> ld){
		ArrayList<DominoModel> listT = ld;
		boolean good = false;

		while(good==false){
			for(int i = 0 ; i < listT.size() ; i++) {
				DominoModel tmp;
				DominoModel tmp2;

				if (i < listT.size() - 1) {
					if(listT.get(i).getNumDomino() > listT.get(i + 1).getNumDomino()) {
						tmp = listT.get(i);
						tmp2 = listT.get(i + 1);
						listT.remove(i);
						listT.add(i, tmp2);

						listT.remove(i + 1);
						listT.add(i + 1, tmp);
					}
					/*for (int j = 0 ; j<listT.size();j++) {
						System.out.println(listT.get(j).getNumDomino());
					}*/
				}

				else if (i == listT.size() - 1) {
					if (listT.get(i).getNumDomino() < listT.get(0).getNumDomino()) {
						tmp = listT.get(i);
						tmp2 = listT.get(0);

						listT.remove(i);
						listT.add(i, tmp2);

						listT.remove(0);
						listT.add(0, tmp);
						/*for (int j = 0 ; j<listT.size();j++) {
							System.out.println(listT.get(j).getNumDomino());
						}*/
					}
				}

			}
			if (listT.get(0).getNumDomino() < listT.get(1).getNumDomino() && listT.get(1).getNumDomino() < listT.get(2).getNumDomino() && listT.get(2).getNumDomino() < listT.get(3).getNumDomino() && listT.get(0).getNumDomino() < listT.get(3).getNumDomino() ){
				good = true;
			}
		}
		return listT;
	}



}
