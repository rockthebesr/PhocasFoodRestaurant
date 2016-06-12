package phocas;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Phocas {

    Connection con;
    Statement stmt;

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

    public void connect() {
        PrivateInfo p = new PrivateInfo();
        // TODO Auto-generated method stub
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("start");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1522:ug",
                    p.account,
                    p.password);
        } catch (SQLException e) {
            System.out.println("Failed to connect");
        }
    }

    public void update(String s) {
        try {
            stmt.executeUpdate(s);
        } catch (SQLException e) {
            System.out.println("Update failed");
        }
    }

    public ResultSet query(String s) {
        ResultSet rs = null;
        try {
            stmt.executeQuery(s);
        } catch (SQLException e) {
            System.out.print("Query failed");
        }
        return rs;
    }

}
