package com.myproject.cardmanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DraftPack {
	
	List<Carta[]> iniciales = new ArrayList<>();
	int indiceactual = 0;
	
	
	
	public int getIndiceactual() {
		return indiceactual;
	}



	public void setIndiceactual(int indiceactual) {
		this.indiceactual = indiceactual;
	}



	public List<Carta[]> getIniciales() {
		return iniciales;
	}



	public void setIniciales(List<Carta[]> iniciales) {
		this.iniciales = iniciales;
	}


	public Carta[] tressiguientes( int indiceactual){
		Carta[] siguientes = iniciales.get(indiceactual);
		return null;
		
	}
	

	public DraftPack(List<Carta> allcards){
		Random random = new Random();
		for(int i = 0; i<allcards.size();i++){
			int randone = random.nextInt(((allcards.size()-1) - 0) + 1) + 0;
			int randtwo = random.nextInt(((allcards.size()-1) - 0) + 1) + 0;
			int randthree = random.nextInt(((allcards.size()-1) - 0) + 1) + 0;
		iniciales.add(new Carta[]{allcards.get(randone), allcards.get(randtwo), allcards.get(randthree)});
		}

	}
	
	
}
