package com.myproject.yogodino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.myproject.cardmanagement.Coleccion;
import com.myproject.cardmanagement.DraftPack;
import com.myproject.cardmanagement.InstanciasCarta;
import com.myproject.cardmanagement.Mazo;
import com.myproject.connectionddbb.ConnectionDDBB;
import com.myproject.logsystem.LogEvents;

public class Main {

	public static void main(String[] args) {
		//testing a mogollon
		ConnectionDDBB connection = new ConnectionDDBB();
		LogEvents logevents = new LogEvents();
		
	boolean testing =logevents.logIn("Oscar","Hola1234");
	System.out.println(testing);
		/*List<String[]> cartas = new ArrayList<>();
		List<String[]> usuario = new ArrayList<>();
		try{
		connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
		cartas = connection.getAllRunnerCards();
		cartas = connection.getAllCorpCards();
		usuario = connection.getUser("Oscar");
		Mazo mazo = new Mazo("corp", "Primermazo");
		connection.subirCartasMazo(mazo);
		
		}
		
		catch(Exception e){
			System.out.println("Esto falla");
		}
		Coleccion coleccion = new Coleccion();
		coleccion.generateCorpCollection(connection);
		//System.out.println(coleccion.getAllcards().size());
		System.out.println(usuario.get(0)[2].toString());
		DraftPack draftpack = new DraftPack(coleccion.getAllcards());
		
	}*/
	}
}
