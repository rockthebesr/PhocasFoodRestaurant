package phocas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Phocas {
	public Connection start(){
		Connection con = null;
	try{
	con = DriverManager.getConnection(
			  "jdbc:oracle:thin:@localhost:1522:ug", 
			  "ora_n1a0b", 
			  "a32109134" );}
	catch(SQLException e){
		System.out.println("Connection to data base cannot be established.");
		}
	
	return con;
	}
	public static void main(String[] args) throws SQLException {
		PrivateInfo p = new PrivateInfo();
		Phocas Po = new Phocas();
		// TODO Auto-generated method stub
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		System.out.println("start");
		Connection con = Po.start();
		Statement stmt = con.createStatement();
		int rowCount = stmt.executeUpdate("INSERT INTO branch VALUES (20, 'Richmond Main', " +
                "'18122 No.5 Road', 'Richmond', 5252738)");

		stmt.executeQuery("SELECT branch_id, branch_name FROM branch " +
                "WHERE branch_city = 'Vancouver'");
		
		System.out.println("end");
	}

	
	public String AddItem(String itemName, int stock, int price){
		String query= null;
		
		return query;
	}
	
	public String SearchItem(String itemName, int stock, int price){
		String query= null;
		
		return query;
	}
	
	public String ProjectItem(boolean itemName){
		String query= null;
		
		return query;
	}
}
