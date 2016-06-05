package com.myproject.yogodino;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.myproject.cardmanagement.Carta;
import com.myproject.cardmanagement.Coleccion;
import com.myproject.cardmanagement.DraftPack;
import com.myproject.cardmanagement.InstanciasCarta;
import com.myproject.cardmanagement.Mazo;
import com.myproject.connectionddbb.ConnectionDDBB;
import com.myproject.logsystem.LogEvents;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("yogodino")
public class YogodinoUI extends UI {
	LogEvents logevents =new LogEvents();
	Coleccion coleccion;
	DraftPack draftpack;
	InstanciasCarta instanciascarta;
	YogodinoMazosLayout yogodinomazoslayout;
	YogodinoDraftLayout yogodinodraftlayout;
	YogodinoAdminLayout yogodinoadminlayout;
	Mazo mazo;
	int iteracion =0;
	

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = YogodinoUI.class)

	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);
		logIn(layout);

		
		
		

	}

	private void checkLogIn(VerticalLayout loginscreen,VerticalLayout layout, LogEvents logevents, TextField inputnombre, PasswordField inputpass) {
		boolean login;
		logevents.setUsername(inputnombre.getValue());
		logevents.setPassword(inputpass.getValue());
		if(logevents.logIn(logevents.getUsername(), logevents.getPassword())){
			loginscreen.removeAllComponents(); //clear screen
			loggedIn(layout, logevents);
		}
		else{
			layout.addComponent(new Label("Mala Contraseña"));
		}
}
	private void logIn(VerticalLayout layout){
		VerticalLayout loginscreen = new VerticalLayout();
		TextField inputnombre = new TextField ("Nombre");
		PasswordField inputpass = new PasswordField ("Contraseña");
		Label welcome = new Label("Bienvenido, identificate para entrar ");
		loginscreen.addComponent(welcome);
		loginscreen.addComponent(inputnombre);
		loginscreen.addComponent(inputpass);
		Button button = new Button("Entra",
			    event -> checkLogIn(loginscreen, layout, logevents, inputnombre, inputpass));
			
		VerticalLayout novedades = novedades();
		
		loginscreen.addComponent(button);
		loginscreen.addComponent(novedades);
		setContent(loginscreen);
		layout.addComponent(loginscreen);
		setContent(layout);

		
	}
	private VerticalLayout novedades() {
		VerticalLayout retorno = new VerticalLayout();
		Label novedades = new Label("Novedades: ");
		retorno.addComponent(novedades);
		 ConnectionDDBB connection = new ConnectionDDBB();
			connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
		List<Label> listanovedades= connection.todasLasNovedades();
		for(int i =0; i<listanovedades.size();i++){
			retorno.addComponent(listanovedades.get(i));
		}
		connection.closeConnection();
		return retorno;
	}

	private void loggedIn(VerticalLayout layout, LogEvents logevents){
		HorizontalLayout logs = new HorizontalLayout();
		Label welcome = new Label("Usuario actual: "+logevents.getUsername());
		Button logoutbutton = new Button("LogOut",
			    event -> logout(layout));
		logs.addComponent(welcome);
		logs.addComponent(logoutbutton);
		layout.addComponent(logs);
		setContent(layout);
		VerticalLayout second = new VerticalLayout();
		MenuBar barmenu = new MenuBar();
		MenuBar.Command mycommand = new MenuBar.Command() {
		    public void menuSelected(MenuItem selectedItem) {
		    	if (selectedItem.getText().equals("Tus Barajas")){
		    		mazosLayout(second);
		    	}
		    	if (selectedItem.getText().equals("Draft!")){
		    		newdraftLayout(second);
		    	}
		    	if (selectedItem.getText().equals("Admin")){
		    		adminLayout(second);
		    	}
		    	}};
		MenuItem Editor = barmenu.addItem("Tus Barajas", null, mycommand);
		MenuItem draft = barmenu.addItem("Draft!", null, mycommand);
		if(logevents.isAdmin()){
		MenuItem Admin = barmenu.addItem("Admin", null, mycommand);}
		
		layout.addComponent(barmenu);
		layout.addComponent(second);
	}
	private void logout(VerticalLayout layout) {
		layout.removeAllComponents();
		logIn(layout);
	}

	public void adminLayout(VerticalLayout layout){
		layout.removeAllComponents();
		this.yogodinoadminlayout = new YogodinoAdminLayout();
		layout.addComponent(this.yogodinoadminlayout.mostrarpaneladmin(this.logevents));
	}
	public void mazosLayout(VerticalLayout layout){
		layout.removeAllComponents();
		this.yogodinomazoslayout = new YogodinoMazosLayout();
		layout.addComponent(this.yogodinomazoslayout.mostrarlistamazos(this.logevents));
	}
	public void newdraftLayout(VerticalLayout layout){
		layout.removeAllComponents();
		this.yogodinodraftlayout = new YogodinoDraftLayout();
		layout.addComponent(this.yogodinodraftlayout.mostrarpantalladraft(this.logevents));
	}
	
}

	
	