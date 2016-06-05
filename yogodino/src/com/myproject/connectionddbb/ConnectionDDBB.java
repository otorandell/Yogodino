package com.myproject.connectionddbb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.myproject.cardmanagement.Coleccion;
import com.myproject.cardmanagement.InstanciasCarta;
import com.myproject.cardmanagement.Mazo;
import com.myproject.logsystem.LogEvents;

public class ConnectionDDBB {
    Connection conn;
    public ConnectionDDBB() {
    }

    public Connection getConn() {
        return conn;
    }

    public void dbConnect(String db_connect_string, String db_userid, String db_password) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(db_connect_string, db_userid, db_password);
            System.out.println("connected");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
public void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {
            String ecode = "--ERROR: closeConnection(): Unable to close connection, probably is already closed.";
            e.printStackTrace();
        }
    }
  
  public List<String[]> getAllRunnerCards(){
    	String query = "select * from runnercartas";
    	Statement stmt;
    	 List<String[]> cartas = new ArrayList<>();
	    try {
	    	
	        stmt = this.conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	cartas.add(new String[]{Integer.toString(rs.getInt(1)), rs.getString(2), Integer.toString(rs.getInt(3)), Integer.toString(rs.getInt(4)), Integer.toString(rs.getInt(5)),}); 
	        }
		    stmt.close();

    }
	    catch (SQLException e ) {
    		e.printStackTrace();
    	} 
	    return cartas;
}
   
 public List<String[]> getAllCorpCards(){
    	String query = "select * from corpcartas";
    	Statement stmt;
    	 List<String[]> cartas = new ArrayList<>();
	    try {
	    	
	        stmt = this.conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	cartas.add(new String[]{Integer.toString(rs.getInt(1)), rs.getString(2), Integer.toString(rs.getInt(3)), Integer.toString(rs.getInt(4)), Integer.toString(rs.getInt(5)),}); 
	        }
		    stmt.close();

    }
	    catch (SQLException e ) {
    		e.printStackTrace();
    	} 
	    return cartas;
}
    //TODO 
    
public List<String[]> getUser(String username){
    	String query = "select * from usuarios where NOMBRE='"+username+"';";
    	Statement stmt;
    	 List<String[]> usuario = new ArrayList<>();
	    try {
	    	
	        stmt = this.conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	usuario.add(new String[]{Integer.toString(rs.getInt(1)), rs.getString(2), rs.getString(3), Integer.toString(rs.getInt(4))}); 
	        }
		    stmt.close();

    }
	    catch (SQLException e ) {
    		e.printStackTrace();
    	} 
	    return usuario;
    }
    
public void subirMazo(String nombremazo, LogEvents logevents, String faccion){
    	String faccionmazo;
    	if(faccion.equals("corp")){
    		faccionmazo = "mazoscorp";
    	}
    	else{
    		faccionmazo = "mazosrunner";
    	}
    	String query = "INSERT INTO `yogodino`.`"+faccionmazo+"` (`NOMBRE`, `USUARIOID`) VALUES ('"+nombremazo+"', "+logevents.getId()+")";
    	try{
            Statement sta=this.getConn().createStatement();
            if (sta.execute(query))
                sta.close();}
        catch(Exception e){
            String ecode = "--ERROR: updateTable(): Unable to update data from DB, please check the status.";
            e.printStackTrace();
        }
    }
    
public void subirCartasMazo(Mazo mazo){
    	String cartasmazo;
    	if(mazo.getFaccion().equals("corp")){
    		cartasmazo = "cartasmazoscorp";
    	}
    	else{
    		cartasmazo = "cartasmazosrunner";
    	}
    	String idmazo = selectIDMazo(mazo).get(0)[0];
    	for(int i =0; i<mazo.getMazoparamostrar().size();i++){
        	String query = "INSERT INTO `yogodino`.`"+cartasmazo+"` (`CARTAID`, `MAZOID`, `COPIAS`, `COPIASACTIVAS`) VALUES ('"+mazo.getMazoparamostrar().get(i).getCarta().getId()+"', '"+idmazo+"', '"+mazo.getMazoparamostrar().get(i).getInstancias()+"', '0')";
        		System.out.println(query);
    	try{
            Statement sta=this.getConn().createStatement();
            if (sta.execute(query))
                sta.close();}
        catch(Exception e){
            String ecode = "--ERROR: updateTable(): Unable to update data from DB, please check the status.";
            e.printStackTrace();
        }
    }}
    
public List<String[]> selectIDMazo(Mazo mazo){

    	String faccionmazo;
    	if(mazo.getFaccion().equals("corp")){
    		faccionmazo = "mazoscorp";
    	}
    	else{
    		faccionmazo = "mazosrunner";
    	}
    	
    	String query = "select ID from "+faccionmazo+" where NOMBRE = '"+mazo.getNombremazo()+"';";
    	Statement stmt;
    	 List<String[]> id = new ArrayList<>();
	    try {
	    	
	        stmt = this.conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	id.add(new String[]{Integer.toString(rs.getInt(1))}); 
	        }
		    stmt.close();

    }
	    catch (SQLException e ) {
    		e.printStackTrace();
    	} 
	    return id;
}


public List<Mazo> seleccionarMazosCorp(LogEvents logevents){
	
	List<Mazo> mazos = new ArrayList();
	String query = "select * from mazoscorp where usuarioid ="+logevents.getId()+";";
	Statement stmt;
    try {
    	
        stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
        mazos.add(new Mazo(Integer.toString(rs.getInt(1)), rs.getString(2), Integer.toString(rs.getInt(3)), "corp"));
        }
	    stmt.close();
	    

}
    catch (SQLException e ) {
		e.printStackTrace();
	} 
    return mazos;
}
public List<Mazo> seleccionarMazosRunner(LogEvents logevents){
	List<Mazo> mazos = new ArrayList();

	String query = "select * from mazosrunner where usuarioid ="+logevents.getId()+";";
	Statement stmt;
try {
    	
        stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
        mazos.add(new Mazo(Integer.toString(rs.getInt(1)), rs.getString(2), Integer.toString(rs.getInt(3)), "runner"));
        }
	    stmt.close();

}

    catch (SQLException e ) {
		e.printStackTrace();
	} 

    return mazos;
}
public List<InstanciasCarta> seleccionarCartasMazoCorp(String mazoid, Coleccion corpcoleccion){
	String query = "select * from cartasmazoscorp where mazoid ="+mazoid+";";
	Statement stmt;
	 List<InstanciasCarta> cartasmazo = new ArrayList<>();
	 //List<InstanciasCarta> cartasmazo = new ArrayList<>();
    try {
    	
        stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
        	cartasmazo.add(new InstanciasCarta(corpcoleccion.getACard(rs.getInt(2)), rs.getInt(4), rs.getInt(5))); 
        }
	    stmt.close();

}
    catch (SQLException e ) {
		e.printStackTrace();
	} 
    
    return cartasmazo;
}
public List<InstanciasCarta> seleccionarCartasMazoRunner(String mazoid, Coleccion runnercoleccion){
	String query = "select * from cartasmazosrunner where mazoid ="+mazoid+";";
	Statement stmt;
	 List<InstanciasCarta> cartasmazo = new ArrayList<>();
	 //List<InstanciasCarta> cartasmazo = new ArrayList<>();
    try {
    	
        stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
        	cartasmazo.add(new InstanciasCarta(runnercoleccion.getACard(rs.getInt(2)), rs.getInt(4), rs.getInt(5))); 
        }
	    stmt.close();

}
    catch (SQLException e ) {
		e.printStackTrace();
	} 
    
    return cartasmazo;
}

public void nuevaCarta(String faccion, String tipo, String subtipo, String nombre, String id){
	String cartasfaccion;
	if(faccion.equals("Corp")){
		cartasfaccion = "corpcartas";
	}
	else{
		cartasfaccion = "runnercartas";
	}
	tipo = settipotoid(tipo);
	subtipo = setsubtipoid(subtipo);
	String query;
	if(!subtipo.equals("")){
    	 query = "INSERT INTO `yogodino`.`"+cartasfaccion+"` (`NOMBRE`, `NUMEROCICLO`, `TIPOID` ,`SUBTIPOID`) VALUES ('"+nombre+"', '"+id+"', '"+tipo+"', '"+subtipo+"');";}
	else{    	
		 query = "INSERT INTO `yogodino`.`"+cartasfaccion+"` (`NOMBRE`, `NUMEROCICLO`, `TIPOID`) VALUES ('"+nombre+"', '"+id+"', '"+tipo+"');";
	}
	try{
        Statement sta=this.getConn().createStatement();
        if (sta.execute(query))
            sta.close();}
    catch(Exception e){
        String ecode = "--ERROR: updateTable(): Unable to update data from DB, please check the status.";
        e.printStackTrace();
    }
}
public void nuevoUsuario(String nombre, String contraseña, String admin){
	
	admin = setadmin(admin);
	 
	 String query = "INSERT INTO `yogodino`.`usuarios` (`NOMBRE`, `PASSWORD`, `ADMIN`) VALUES ('"+nombre+"', '"+contraseña+"', '"+admin+"');";
	
	try{
        Statement sta=this.getConn().createStatement();
        if (sta.execute(query))
            sta.close();}
    catch(Exception e){
        String ecode = "--ERROR: updateTable(): Unable to update data from DB, please check the status.";
        e.printStackTrace();
    }
}

private String setadmin(String admin) {
	if(admin.equals("Si")){admin = "1";}
	else{admin = "0";}
return admin;
}

private String settipotoid(String tipo) {
	if(tipo.equals("Agenda") || tipo.equals("Event")){tipo="1";}
	else if(tipo.equals("Asset") || tipo.equals("Hardware")){tipo="2";}
	else if(tipo.equals("ICE") || tipo.equals("Program")){tipo="3";}
	else if(tipo.equals("Operation") || tipo.equals("Resource")){tipo="4";}
	else if(tipo.equals("Upgrade")){tipo="5";}
	else{tipo = "1";}
	return tipo;
}
private String setsubtipoid(String subtipo) {
	if(subtipo.equals("Icebreaker") || subtipo.equals("Barrier")){subtipo="1";}
	else if(subtipo.equals("Code Gate")){subtipo="2";}
	else if(subtipo.equals("Sentry")){subtipo="3";}
	else{subtipo ="";}
	
	return subtipo;
}
}
