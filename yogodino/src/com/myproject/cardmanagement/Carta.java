package com.myproject.cardmanagement;

public class Carta {
	private String tipo;
	private String subtipo;
	private String nombre;
	private int id;
	private int numciclo;
	private String imagenpath;
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getSubtipo() {
		return subtipo;
	}
	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setImagenpath(String imagenpath) {
		this.imagenpath = imagenpath;
	}
	public String getImagenpath() {
		return imagenpath;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumciclo() {
		return numciclo;
	}
	public void setNumciclo(int numciclo) {
		this.numciclo = numciclo;
	}
	public Carta(String tipo, String subtipo, String nombre, int id, int numciclo) {
		this.tipo = tipo;
		this.subtipo = subtipo;
		this.nombre = nombre;
		this.id = id;
		this.numciclo = numciclo;
	}
	
	
}
