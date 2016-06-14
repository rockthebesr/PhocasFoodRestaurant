package phocas;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Phocas {

	public static void main(String[] args) throws SQLException {
		//ssh CSID@remote.ugrad.cs.ubc.ca -L 1522:dbhost.ugrad.cs.ubc.ca:1522
		Database db = new Database();
		PhocasGUI gui = new PhocasGUI(db);
		gui.run();
		
	}

}
