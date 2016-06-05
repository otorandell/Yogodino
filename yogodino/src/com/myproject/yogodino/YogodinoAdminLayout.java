package com.myproject.yogodino;

import java.util.List;

import com.myproject.cardmanagement.Mazo;
import com.myproject.connectionddbb.ConnectionDDBB;
import com.myproject.logsystem.LogEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class YogodinoAdminLayout {

public YogodinoAdminLayout(){}
	
	public VerticalLayout mostrarpaneladmin(){
		VerticalLayout screen = new VerticalLayout();
		VerticalLayout addcarta = nuevacarta();
		VerticalLayout addusuario = nuevousuario();
		screen.addComponent(addusuario);
		screen.addComponent(addcarta);		
		return screen;
	}

	private VerticalLayout nuevacarta() {
		VerticalLayout retorno = new VerticalLayout();
		Label ayuda = new Label("Puedes añadir una nueva carta a la BDD aqui: ");
		HorizontalLayout cadenatextbox = new HorizontalLayout();
		ComboBox faccion = ComboFaccion();
		ComboBox tipos = ComboTipos();
		ComboBox subtipos = ComboSubtipos();
		TextField nombrecarta = new TextField("Nombre: ");
		TextField id = new TextField("ID (ciclo): ");
		Button confirma = new Button("Añadir",
			    event -> insertarCartaBDD(retorno, faccion, tipos, subtipos, nombrecarta, id));
		
		cadenatextbox.addComponent(faccion);
		cadenatextbox.addComponent(tipos);
		cadenatextbox.addComponent(subtipos);
		cadenatextbox.addComponent(nombrecarta);
		cadenatextbox.addComponent(id);
		cadenatextbox.addComponent(confirma);
		retorno.addComponent(ayuda);
		retorno.addComponent(cadenatextbox);
		
		
		return retorno;
	}
	
	private VerticalLayout nuevousuario() {
		VerticalLayout retorno = new VerticalLayout();
		Label ayuda = new Label("Puedes añadir un nuevo usuario a la BDD aqui: ");
		HorizontalLayout cadenatextbox = new HorizontalLayout();
		ComboBox admin = ComboAdmin();
		TextField nombreusuario = new TextField("Nombre: ");
		TextField contraseña = new TextField("Contraseña: ");
		Button confirma = new Button("Añadir",
			    event -> insertarUsuarioBDD(retorno, nombreusuario, contraseña, admin));
		
		cadenatextbox.addComponent(nombreusuario);
		cadenatextbox.addComponent(contraseña);
		cadenatextbox.addComponent(admin);
		cadenatextbox.addComponent(confirma);
		retorno.addComponent(ayuda);
		retorno.addComponent(cadenatextbox);
		
		
		return retorno;
	}
	private void insertarUsuarioBDD(VerticalLayout retorno, TextField nombreusuario, TextField contraseña, ComboBox admin) {
		ConnectionDDBB connection = new ConnectionDDBB();
		connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
		connection.nuevoUsuario(nombreusuario.getValue().toString(), contraseña.getValue().toString(), admin.getValue().toString());
		connection.closeConnection();
	}

	private ComboBox ComboAdmin() {
		ComboBox combobox = new ComboBox("Admin? ");
	    combobox.addItem("Si");
	    combobox.addItem("No");
	    return combobox;
	}

	private ComboBox ComboFaccion() {
	    ComboBox combobox = new ComboBox("Faccion: ");
	    combobox.addItem("Runner");
	    combobox.addItem("Corp");
	    return combobox;

	}
	private ComboBox ComboSubtipos() {
	    ComboBox combobox = new ComboBox("Subtipo: ");
        combobox.addItem("N/A");
	    combobox.addItem("Barrier");
        combobox.addItem("Code Gate");
        combobox.addItem("Sentry");
        combobox.addItem("Icebreaker");
        combobox.setValue("N/A");
	    return combobox;

	}
	private ComboBox ComboTipos() {
	    ComboBox combobox = new ComboBox("Tipo: ");
	    
        combobox.addItem("Agenda");
        combobox.addItem("Asset");
        combobox.addItem("ICE");
        combobox.addItem("Operation");
        combobox.addItem("Upgrade");
        combobox.addItem("Event");
        combobox.addItem("Hardware");
        combobox.addItem("Program");
        combobox.addItem("Resource");
	    
	    return combobox;
	}
	private void insertarCartaBDD(VerticalLayout layout, ComboBox faccion, ComboBox tipos, ComboBox subtipos, TextField nombrecarta, TextField id){
		Label test = new Label("Has elegido: "+ faccion.getValue().toString()+" "+ tipos.getValue().toString()+" "+subtipos.getValue().toString()+" "+nombrecarta.getValue()+" "+id.getValue());
		layout.addComponent(test);
		ConnectionDDBB connection = new ConnectionDDBB();
		connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
		connection.nuevaCarta(faccion.getValue().toString(), tipos.getValue().toString(), subtipos.getValue().toString(), nombrecarta.getValue(), id.getValue().toString());
		connection.closeConnection();
		
	}

}
