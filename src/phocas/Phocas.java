package phocas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
		ResultSet row = stmt.executeQuery("select * from allOrder where empId = 3");

		while(row.next())
		{
			System.out.println(row.getString("orderStatus"));
			System.out.println(row.getInt(1));
		}
		System.out.println("end");
	}

}
