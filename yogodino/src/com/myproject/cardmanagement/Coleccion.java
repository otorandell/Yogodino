package com.myproject.cardmanagement;

import java.util.ArrayList;
import java.util.List;

import com.myproject.connectionddbb.ConnectionDDBB;

public class Coleccion {

	List<Carta> allcards = new ArrayList<>();

	

	public List<Carta> getAllcards() {
		return allcards;
	}

	public void setAllcards(List<Carta> allcards) {
		this.allcards = allcards;
	}

	public Coleccion() {
		super();
	}
	
	public void generateRunnerCollection(){
		ConnectionDDBB connection = new ConnectionDDBB();
		connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
	List<String[]> todaslascartas = new ArrayList<>();
	todaslascartas = connection.getAllRunnerCards();
	for(int i=0;i<todaslascartas.size();i++){
		Carta carta = new Carta(todaslascartas.get(i)[3],todaslascartas.get(i)[4],todaslascartas.get(i)[1],Integer.parseInt(todaslascartas.get(i)[0]),Integer.parseInt(todaslascartas.get(i)[2]));	
	this.allcards.add(carta);
	}
	connection.closeConnection();
}
	public void generateCorpCollection(){
		ConnectionDDBB connection = new ConnectionDDBB();
		connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
		List<String[]> todaslascartas = new ArrayList<>();
		todaslascartas = connection.getAllCorpCards();
		for(int i=0;i<todaslascartas.size();i++){
			Carta carta = new Carta(todaslascartas.get(i)[3],todaslascartas.get(i)[4],todaslascartas.get(i)[1],Integer.parseInt(todaslascartas.get(i)[0]),Integer.parseInt(todaslascartas.get(i)[2]));	
			this.allcards.add(carta);
		}
		connection.closeConnection();

	}
	public Carta getACard(int cardid){
		for (int i=0;i<this.allcards.size();i++){
				if(this.allcards.get(i).getId() == cardid){
					return this.allcards.get(i);

				}
		}
		return null;
	}
	
}
