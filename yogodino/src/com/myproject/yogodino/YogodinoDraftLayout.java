package com.myproject.yogodino;

import com.myproject.cardmanagement.Carta;
import com.myproject.cardmanagement.Coleccion;
import com.myproject.cardmanagement.DraftPack;
import com.myproject.cardmanagement.Mazo;
import com.myproject.logsystem.LogEvents;
import com.vaadin.server.ClassResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class YogodinoDraftLayout {
	Coleccion coleccion;
	Mazo mazo;
	DraftPack draftpack;
	int iteracion;
	LogEvents logevents;
	VerticalLayout cartas = new VerticalLayout();

	public YogodinoDraftLayout(){
		
	}
	
	public VerticalLayout mostrarpantalladraft(LogEvents logevents){
		this.logevents = logevents;
		VerticalLayout screen = new VerticalLayout();
		HorizontalLayout todo = new HorizontalLayout();
		VerticalLayout draft = draftLayout();
		todo.addComponent(draft);		
		todo.addComponent(this.cartas);
		screen.addComponent(todo);
		
		return screen;
	}
	public VerticalLayout draftLayout(){
		VerticalLayout layout = new VerticalLayout();
		VerticalLayout content = new VerticalLayout();
		Label chooseone = new Label("Por favor, elige de que faccion deseas construir el mazo: ");
		content.addComponent(chooseone);
		HorizontalLayout elecciones = new HorizontalLayout();
		VerticalLayout runnerlayout = new VerticalLayout();
		VerticalLayout corplayout = new VerticalLayout();
		Image runner = new Image("Runner",
			    new ClassResource("file//C:/themasque.jpg"));
		Button runnerbutton = new Button("Runner",
			    event -> draftStart(content, layout, true));
		Image corp = new Image("Corp",
			    new ClassResource("file//C:/theshadow.png"));
		Button corpbutton = new Button("Corp",
			    event -> draftStart(content, layout, false));
		runnerlayout.addComponent(runner);
		runnerlayout.addComponent(runnerbutton);
		corplayout.addComponent(corp);
		corplayout.addComponent(corpbutton);
		elecciones.addComponent(runnerlayout);
		elecciones.addComponent(corplayout);
		content.addComponent(elecciones);
		layout.addComponent(content);
		return layout;
	}
	public void draftStart(VerticalLayout layout, boolean isrunner){
		layout.removeAllComponents();
		Label label;
		if(isrunner){
			//CODIGO REAL
			label = new Label("Runner");
		}
		else{
			//CODIGO REAL
			label = new Label ("Corp");
		}
		layout.addComponent(label);
	}
	public void draftStart(VerticalLayout draft, VerticalLayout layout, boolean runner){
		draft.removeAllComponents(); //clear screen
		
		this.coleccion = new Coleccion();
		
		if(runner){this.coleccion.generateRunnerCollection();
		this.mazo = new Mazo("runner", "nuevomazo");
		}
		else{this.coleccion.generateCorpCollection();
		this.mazo = new Mazo("corp", "nuevomazo");
		}
		
		this.draftpack = new DraftPack(coleccion.getAllcards());
		
			this.iteracion =0;
			HorizontalLayout currentcards = cartaSiguiente(layout, draft);
			

	}
	public HorizontalLayout cartaSiguiente(VerticalLayout layout, VerticalLayout draft){
		draft.removeAllComponents();
		HorizontalLayout currentcards = new HorizontalLayout();
		VerticalLayout primerslot = new VerticalLayout();
		VerticalLayout segundoslot = new VerticalLayout();
		VerticalLayout tercerslot = new VerticalLayout();
		
		Label primera =new Label(draftpack.getIniciales().get(iteracion)[0].getNombre());
		Button runnerbutton = new Button(draftpack.getIniciales().get(iteracion)[0].getNombre(),
			    event -> siguiente(iteracion, layout, draft, draftpack.getIniciales().get(iteracion)[0]));
		primerslot.addComponent(primera);
		primerslot.addComponent(runnerbutton);
		
		Label segona =new Label(draftpack.getIniciales().get(iteracion)[1].getNombre());
		Button runnerbutton2 = new Button(draftpack.getIniciales().get(iteracion)[1].getNombre(),
			    event -> siguiente(iteracion, layout, draft, draftpack.getIniciales().get(iteracion)[1]));
		segundoslot.addComponent(segona);
		segundoslot.addComponent(runnerbutton2);

		
		Label tercera =new Label(draftpack.getIniciales().get(iteracion)[2].getNombre());
		Button runnerbutton3 = new Button(draftpack.getIniciales().get(iteracion)[2].getNombre(),
			    event -> siguiente(iteracion, layout, draft, draftpack.getIniciales().get(iteracion)[2]));
		tercerslot.addComponent(tercera);
		tercerslot.addComponent(runnerbutton3);

		
		currentcards.addComponent(primerslot);
		currentcards.addComponent(segundoslot);
		currentcards.addComponent(tercerslot);
		draft.addComponent(currentcards);
		layout.addComponent(draft);
return currentcards;
	}
public void siguiente(int iteracion, VerticalLayout layout, VerticalLayout draft, Carta carta){
		
		if(this.iteracion<45){
			this.mazo.elecciones(carta);
			Label seleccion = new Label(carta.getNombre());
			this.cartas.addComponent(seleccion);
			this.iteracion++;
		cartaSiguiente(layout, draft);
		}
		else{
			guardarMazo(layout, draft);
		}
	}
private void guardarMazo(VerticalLayout layout, VerticalLayout draft) {
	draft.removeAllComponents();
	this.mazo.representacionMazo(this.mazo.getElecciones());
	Label seleccion = new Label("Ahora Introduce Un nombre para el mazo: ");
	TextField inputnombre = new TextField ("Nombre");
	Button button = new Button("Guardar",
		    event -> guardar(layout, inputnombre.getValue()));
	layout.addComponent(seleccion);
	layout.addComponent(inputnombre);
	layout.addComponent(button);
	

}
private void guardar(VerticalLayout layout, String nombre) {
	layout.removeAllComponents();
	this.cartas.removeAllComponents();
	Label exito = new Label("Mazo guardado");
	layout.addComponent(exito);
	muestracontenido(layout);
	this.mazo.setNombremazo(nombre);
	this.mazo.insertarMazo(this.logevents);
	this.mazo.insertarCartas();
}
private void muestracontenido(VerticalLayout layout){
	/* PRUEBAS
for(int i =0;i<this.mazo.getElecciones().size();i++){
	Label label = new Label(this.mazo.getElecciones().get(i).getNombre());
	layout.addComponent(label);
}	*/
	/*for (int i=0; i<this.mazo.getMazoparamostrar().size();i++){
		Label label = new Label(this.mazo.getMazoparamostrar().get(i).getCarta().getNombre()+"  "+this.mazo.getMazoparamostrar().get(i).getInstancias());
		layout.addComponent(label);
	}*/
}
}
