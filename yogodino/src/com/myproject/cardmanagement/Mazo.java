package com.myproject.cardmanagement;

import java.util.ArrayList;
import java.util.List;

import com.myproject.connectionddbb.ConnectionDDBB;
import com.myproject.logsystem.LogEvents;

public class Mazo {
	String nombremazo;
	String faccion;
	String id;
	String usuarioid;
	Coleccion runnercoleccion = new Coleccion();
	Coleccion corpcoleccion = new Coleccion();
	List<Carta> elecciones = new ArrayList<>();
	List<InstanciasCarta> mazoparamostrar = new ArrayList<>();

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

 
public String getNombremazo() {
	return nombremazo;
}

public void setNombremazo(String nombremazo) {
	this.nombremazo = nombremazo;
}

public String getFaccion() {
	return faccion;
}

public void setFaccion(String faccion) {
	this.faccion = faccion;
}

public List<Carta> getElecciones() {
	return elecciones;
}

public void setElecciones(List<Carta> elecciones) {
	this.elecciones = elecciones;
}

public List<InstanciasCarta> getMazoparamostrar() {
	return mazoparamostrar;
}

public void setMazoparamostrar(List<InstanciasCarta> mazoparamostrar) {
	this.mazoparamostrar = mazoparamostrar;
}

public Mazo(String faccion, String nombremazo) {
	this.corpcoleccion.getAllcards();
	this.runnercoleccion.getAllcards();
	setFaccion(faccion);
	setNombremazo(nombremazo);
}


public Mazo(String id, String nombremazo, String usuarioid, String faccion) {
	this.corpcoleccion.getAllcards();
	this.runnercoleccion.getAllcards();
	this.usuarioid = usuarioid;
	this.id = id;
	this.nombremazo = nombremazo;
	this.faccion = faccion;
}

//Revisar, puede que no sea posible
	public void elecciones(Carta eleccion){
		this.elecciones.add(eleccion);
	}
 
	public void representacionMazo(List<Carta> elecciones){
		int cantidad =0;
		boolean exist=false;
		for(int i =0;i<getElecciones().size();i++){
			for(int j =0;j<getMazoparamostrar().size();j++){
				exist = false;
				if(getElecciones().get(i).getNombre().equals(getMazoparamostrar().get(j).getCarta().getNombre())){
					getMazoparamostrar().get(j).setInstancias(getMazoparamostrar().get(j).getInstancias()+1);
					 exist=true;
					break;
				}
			}
			if(!exist){
				InstanciasCarta nuevacarta = new InstanciasCarta(getElecciones().get(i),1,0);
				getMazoparamostrar().add(nuevacarta);
			}
		}
		
	}
	public void insertarMazo(LogEvents logevents){
		ConnectionDDBB connection = new ConnectionDDBB();
		connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
		connection.subirMazo(getNombremazo(), logevents, getFaccion());
		connection.closeConnection();
	}
	public void insertarCartas(){
		ConnectionDDBB connection = new ConnectionDDBB();
		connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
		connection.subirCartasMazo(this);
		connection.closeConnection();
	}
	
	public void recogerCartasCorp(){
		
		ConnectionDDBB connection = new ConnectionDDBB();
		connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
		connection.seleccionarCartasMazoCorp(this.getId(), corpcoleccion);		
		connection.closeConnection();
	}

	public String getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(String usuarioid) {
		this.usuarioid = usuarioid;
	}

	public Coleccion getRunnercoleccion() {
		return runnercoleccion;
	}

	public void setRunnercoleccion(Coleccion runnercoleccion) {
		this.runnercoleccion = runnercoleccion;
	}

	public Coleccion getCorpcoleccion() {
		return corpcoleccion;
	}

	public void setCorpcoleccion(Coleccion corpcoleccion) {
		this.corpcoleccion = corpcoleccion;
	}
 
}

