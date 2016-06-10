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
		stmt.executeUpdate(Po.addItem("cheeseburger", 5, 6));
		/*int rowCount = stmt.executeUpdate("INSERT INTO branch VALUES (20, 'Richmond Main', " +
                "'18122 No.5 Road', 'Richmond', 5252738)");

		stmt.executeQuery("SELECT branch_id, branch_name FROM branch " +
                "WHERE branch_city = 'Vancouver'");*/
		
		System.out.println("end");
	}

	
	public String addItem(String itemName, int stock, int price){
		String query= "INSERT INTO item values( '"+itemName +"', "+ stock+", "+price+ ");";
		return query;
	}
	
	public String deleteItem(String Condition){
		String query= "DELETE FROM item WHERE "+Condition;
		return query;
	}
	
	
	public String queryItem(boolean itemName, boolean stock, boolean price, String itemNameCondition, String stockCondition, String priceCondition){
		String query=projectItem(itemName, stock, price)+" FROM item "+selectItem(itemNameCondition, stockCondition, priceCondition);
		return query;
	}
	
	private String selectItem(String itemNameCondition, String stockCondition, String priceCondition){
		String query="";
		if(!(itemNameCondition.equals("")||stockCondition.equals("")||priceCondition.equals(""))){
			query= "WHERE "+itemNameCondition+stockCondition+priceCondition;
		}
		return query;
	}
	
	private String projectItem(boolean itemName, boolean stock, boolean price){
		String itemNameStr=" ", stockStr= " ", priceStr = " ";
		if(itemName) itemNameStr = "itemName ";
		if(stock) {
			if(itemName)
			stockStr =",stock ";
			else{
				stockStr= "stock ";
			}
		}
		if(price) {
			if(itemName||stock){
				priceStr=",price ";
			}else{
			priceStr ="price ";
			}
		}
		String query= "SELECT "+itemNameStr+stockStr+priceStr;
		return query;
	}
}
