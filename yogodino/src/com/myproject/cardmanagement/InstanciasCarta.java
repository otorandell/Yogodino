package com.myproject.cardmanagement;

public class InstanciasCarta {
	Carta carta;
	int instancias =0;
	int instanciasactivas =0;
	
	public int getInstanciasactivas() {
		return instanciasactivas;
	}
	public void setInstanciasactivas(int instanciasactivas) {
		this.instanciasactivas = instanciasactivas;
	}
	public Carta getCarta() {
		return carta;
	}
	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	public int getInstancias() {
		return instancias;
	}
	public void setInstancias(int instancias) {
		this.instancias = instancias;
	}
	public InstanciasCarta(Carta carta, int instancias, int instanciasactivas) {
		super();
		this.carta = carta;
		this.instancias = instancias;
		this.instanciasactivas = instanciasactivas;
	}
	
	
}
