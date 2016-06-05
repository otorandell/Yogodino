package com.myproject.logsystem;

import java.util.ArrayList;
import java.util.List;

import com.myproject.connectionddbb.ConnectionDDBB;

public class LogEvents {
	String username;
	String id;
	String password;
	boolean admin=false;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public LogEvents() {
		
	}
	
	public boolean logIn(String user, String password){
		
		ConnectionDDBB connection = new ConnectionDDBB();
		connection.dbConnect("jdbc:mysql://localhost:3306/yogodino","root","admin");
		
		List<String[]> usuario = connection.getUser(user);
if(usuario.size()>0){
		if(usuario.get(0)[2].equals(password)){
			setId(usuario.get(0)[0].toString());
			setUsername(usuario.get(0)[1].toString());
			setPassword(usuario.get(0)[2].toString());
			if(!usuario.get(0)[3].equals("0")){setAdmin(true);}
			connection.closeConnection();
			return true;
			
		}
		else{
			System.out.println(usuario.get(0)[2]+" no coincide con "+password);
		}
		}
		connection.closeConnection();
		return false;
	}
}
