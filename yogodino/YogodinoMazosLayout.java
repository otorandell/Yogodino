package com.myproject.yogodino;

import java.util.ArrayList;
import java.util.List;

import com.myproject.cardmanagement.Coleccion;
import com.myproject.cardmanagement.Mazo;
import com.myproject.connectionddbb.ConnectionDDBB;
import com.myproject.logsystem.LogEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class YogodinoMazosLayout {
	
	List<Mazo> mazos;
	Mazo mazoactual;
	Coleccion corpcoleccion = new Coleccion();
	Coleccion runnercoleccion= new Coleccion();
	
	public YogodinoMazosLayout() {
	}
	public VerticalLayout mostrarlistamazos(LogEvents logevents){
		corpcoleccion.generateCorpCollection();
		runnercoleccion.generateRunnerCollection();
		VerticalLayout screen = new VerticalLayout();
		HorizontalLayout subscreen = new HorizontalLayout();
		HorizontalLayout content = new HorizontalLayout();

		ComboBox listademazos = getComboBox(logevents);
		Button examinar = new Button("Examinar",
			    event -> mostrarMazo(listademazos, content));
		subscreen.addComponent(listademazos);
		subscreen.addComponent(examinar);
		subscreen.addComponent(content);
		screen.addComponent(subscreen);
		
		return screen;
	}
	private VerticalLayout contenidoporpantalla() {
		VerticalLayout todoelbloque = new VerticalLayout();
		HorizontalLayout separaciondecontenido = new HorizontalLayout();
		VerticalLayout cartasdisponibles = listaDeCartasDisponibles();
		VerticalLayout cartasenuso = new VerticalLayout();
		separaciondecontenido.addComponent(cartasdisponibles);
		separaciondecontenido.addComponent(cartasenuso);
		todoelbloque.addComponent(separaciondecontenido);
		return todoelbloque;
	}
	private VerticalLayout listaDeCartasDisponibles() {
		VerticalLayout retorno = new VerticalLayout();
				if(this.mazoactual.getFaccion().equals("corp")){
					retorno = setVistaCorp();
				}
				else{ retorno = setVistaRunner();}
			
		return retorno;
	}
	private VerticalLayout setVistaCorp() {
		VerticalLayout retorno = new VerticalLayout();
		List<Label> agenda = new ArrayList<>();
		List<Label> asset= new ArrayList<>();
		List<Label> ice= new ArrayList<>();
		List<Label> operation= new ArrayList<>();
		List<Label> upgrade= new ArrayList<>();
		Label nombreagenda = new Label("Agendas: ");
		agenda.add(nombreagenda);
		Label nombreasset = new Label("Asset: ");
		asset.add(nombreasset);
		Label nombreice = new Label("ICE: ");
		ice.add(nombreice);
		Label nombreoperation = new Label("Operations: ");
		operation.add(nombreoperation);
		Label nombreupgrade = new Label("Upgrades: ");
		upgrade.add(nombreupgrade);

		for(int i = 0; i< this.mazoactual.getMazoparamostrar().size();i++){
			System.out.println(this.mazoactual.getMazoparamostrar().get(i).getCarta().getTipo());
			if(this.mazoactual.getMazoparamostrar().get(i).getCarta().getTipo().equals("1")){
				agenda.add( new Label(this.mazoactual.getMazoparamostrar().get(i).getInstancias()+" "+this.mazoactual.getMazoparamostrar().get(i).getCarta().getNombre()));
			}
			if(this.mazoactual.getMazoparamostrar().get(i).getCarta().getTipo().equals("2")){
				asset.add( new Label(this.mazoactual.getMazoparamostrar().get(i).getInstancias()+" "+this.mazoactual.getMazoparamostrar().get(i).getCarta().getNombre()));
			}
			if(this.mazoactual.getMazoparamostrar().get(i).getCarta().getTipo().equals("3")){
				ice.add( new Label(this.mazoactual.getMazoparamostrar().get(i).getInstancias()+" "+this.mazoactual.getMazoparamostrar().get(i).getCarta().getNombre()));
			}
			if(this.mazoactual.getMazoparamostrar().get(i).getCarta().getTipo().equals("4")){
				operation.add( new Label(this.mazoactual.getMazoparamostrar().get(i).getInstancias()+" "+this.mazoactual.getMazoparamostrar().get(i).getCarta().getNombre()));
			}
			if(this.mazoactual.getMazoparamostrar().get(i).getCarta().getTipo().equals("5")){
				upgrade.add( new Label(this.mazoactual.getMazoparamostrar().get(i).getInstancias()+" "+this.mazoactual.getMazoparamostrar().get(i).getCarta().getNombre()));
			}
			for(int j =0;j<agenda.size();j++){
				retorno.addComponent(agenda.get(j));
			}
			for(int k =0;k<asset.size();k++){
				retorno.addComponent(asset.get(k));
			}
			for(int l =0;l<ice.size();l++){
				retorno.addComponent(ice.get(l));
			}
			for(int m =0;m<operation.size();m++){
				retorno.addComponent(operation.get(m));
			}
			for(int n =0;n<upgrade.size();n++){
				retorno.addComponent(upgrade.get(n));
			}
		}
		return retorno;
	}
	
	private VerticalLayout setVistaRunner() {
		VerticalLayout retorno = new VerticalLayout();
		List<Label> event = new ArrayList<>();
		List<Label> hardware= new ArrayList<>();
		List<Label> program= new ArrayList<>();
		List<Label> resource= new ArrayList<>();
		Label nombreevents = new Label("Events: ");
		event.add(nombreevents);
		Label nombrehardware = new Label("Hardwares: ");
		hardware.add(nombrehardware);
		Label nombreprogram = new Label("Programs: ");
		program.add(nombreprogram);
		Label nombreresource = new Label("Resources: ");
		resource.add(nombreresource);
		

		for(int i = 0; i< this.mazoactual.getMazoparamostrar().size();i++){
			if(this.mazoactual.getMazoparamostrar().get(i).getCarta().getTipo().equals("1")){
				event.add( new Label(this.mazoactual.getMazoparamostrar().get(i).getInstancias()+" "+this.mazoactual.getMazoparamostrar().get(i).getCarta().getNombre()));
			}
			if(this.mazoactual.getMazoparamostrar().get(i).getCarta().getTipo().equals("2")){
				hardware.add( new Label(this.mazoactual.getMazoparamostrar().get(i).getInstancias()+" "+this.mazoactual.getMazoparamostrar().get(i).getCarta().getNombre()));
			}
			if(this.mazoactual.getMazoparamostrar().get(i).getCarta().getTipo().equals("3")){
				program.add( new Label(this.mazoactual.getMazoparamostrar().get(i).getInstancias()+" "+this.mazoactual.getMazoparamostrar().get(i).getCarta().getNombre()));
			}
			if(this.mazoactual.getMazoparamostrar().get(i).getCarta().getTipo().equals("4")){
				resource.add( new Label(this.mazoactual.getMazoparamostrar().get(i).getInstancias()+" "+this.mazoactual.getMazoparamostrar().get(i).getCarta().getNombre()));
			}
			
			for(int j =0;j<event.size();j++){
				retorno.addComponent(event.get(j));
			}
			for(int j =0;j<hardware.size();j++){
				retorno.addComponent(hardware.get(j));
			}
			for(int j =0;j<program.size();j++){
				retorno.addComponent(program.get(j));
			}
			for(int j =0;j<resource.size();j++){
				retorno.addComponent(resource.get(j));
			}
		
		}
		return retorno;
	}
	private void mostrarMazo(ComboBox listademazos, HorizontalLayout content) {
		content.removeAllComponents();
		String actual = listademazos.getValue().toString();
		for (int i = 0; i<this.mazos.size();i++){
			if(this.mazos.get(i).getNombremazo().equals(actual)){
				this.mazoactual = this.mazos.get(i);
				content.addComponent(listaDeCartasDisponibles());

			}
			
		}
	}
	/*private void poblarMazoActual(ComboBox listademazos) {
		this.mazoactual = new Mazo("", listademazos.getValue().toString());		
	}*/
	private ComboBox getComboBox(LogEvents logevents) {
	    ComboBox comboBox = new ComboBox();
	    ConnectionDDBB connection = new ConnectionDDBB();
		connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
		this.mazos = connection.seleccionarMazosCorp(logevents);
		rellenarMazoCorp(this.mazos);
		List<Mazo> mazosrunner = connection.seleccionarMazosRunner(logevents);
		rellenarMazoRunner(mazosrunner);
		System.out.println("Lingitud"+mazosrunner.size());
		this.mazos.addAll(mazosrunner);
	    for (int i = 0; i < this.mazos.size(); i++) {
	        comboBox.addItem(this.mazos.get(i).getNombremazo());
	    }

		connection.closeConnection();
	    return comboBox;
	}
	
	public void rellenarMazoCorp(List<Mazo> mazos){
		ConnectionDDBB connection = new ConnectionDDBB();
		connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
		for (int i =0;i<mazos.size();i++){
			mazos.get(i).setMazoparamostrar(connection.seleccionarCartasMazoCorp(mazos.get(i).getId(), this.corpcoleccion));
		}
	}
	public void rellenarMazoRunner(List<Mazo> mazos){
		ConnectionDDBB connection = new ConnectionDDBB();
		connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
		for (int i =0;i<mazos.size();i++){
			mazos.get(i).setMazoparamostrar(connection.seleccionarCartasMazoRunner(mazos.get(i).getId(), this.runnercoleccion));
		}
	}
}
