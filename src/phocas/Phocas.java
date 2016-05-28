package phocas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Phocas {

	public static void main(String[] args) throws SQLException {
		PrivateInfo p = new PrivateInfo();
		// TODO Auto-generated method stub
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		System.out.println("start");
		Connection con = DriverManager.getConnection(
				  "jdbc:oracle:thin:@localhost:1522:ug", 
				  p.account, 
				  p.password);
		Statement stmt = con.createStatement();
		int rowCount = stmt.executeUpdate("INSERT INTO branch VALUES (20, 'Richmond Main', " +
                "'18122 No.5 Road', 'Richmond', 5252738)");

		stmt.executeQuery("SELECT branch_id, branch_name FROM branch " +
                "WHERE branch_city = 'Vancouver'");
		
		System.out.println("end");
	}

}
