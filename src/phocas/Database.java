package phocas;

import java.awt.*;

import javax.swing.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class Database {

    public Connection con;
    public Statement stmt;
    
    public Database() {
        PrivateInfo p = new PrivateInfo();
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("start");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1522:ug",
                    p.account,
                    p.password);
            stmt = con.createStatement();
            JOptionPane.showMessageDialog(null, "connected");
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "failed to connect");
        }
    }
    
    public void disconnect() {
    	try {
			con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "connection failed to close");
		}
    }

    public ResultSet query(String queryString) {
    	  ResultSet rs = null;
          try {
              rs = stmt.executeQuery(queryString);
              stmt.clearBatch();
          } catch (SQLException e) {
              System.out.print("Query failed");
          }

          return rs;
    }
    
    public void update(String s) {
        try {
            stmt.executeUpdate(s);
            stmt.clearBatch();
        } catch (SQLException e) {
            System.out.println("Update failed");
        }
    }
    
    
    public void makeInStoreOrder(String itemName, int storeID, int empID) throws SQLException {
        try {
            
            ResultSet rs = this.query("Select stock from item where itemName = '" + itemName + "'");
            int stock = 0;
            if (rs.next()) {
                stock = rs.getInt(1);
            }
            if (stock == 0) {
                System.out.println("There are 0 " + itemName + " in stock. Order declined.");
                return;
            }
            rs = this.query("Select count(*) from allOrder");
            int orderID = 0;
            if (rs.next()) {
                orderID = rs.getInt(1) + 1;
            }
            this.update("insert into orders values(" + orderID + ", " + itemName + ")");
            rs = this.query("Select i.price from item i, orders o " +
                    "where i.itemName = o.itemName and o.itemName = '" + itemName + "'");
            double price = 0;
            if (rs.next()) {
                price = rs.getInt(1) + 1;
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            this.update("insert into allOrder values(" + orderID + ", " + storeID + ", " + dateFormat + ", " + price +
                    " in preparation " + empID + ")");
            this.update("insert into inStoreOrder values(" + orderID + " )");
            this.update("insert into orders values(" + orderID + ", '" + itemName + "')");
            System.out.println("The order has been added. The orderID is: " + orderID);
        } catch (SQLException e) {
            System.out.println("Order declined");
        }

    }

    public void fulfillInStoreOrder(int orderID) throws SQLException {
        
        this.update("Update allOrders set orderStatus = 'finished' where orderID = " + orderID);
        System.out.println("Order Finished");
    }

    public void cancelInStoreOrder(int orderID) throws SQLException {
            
            this.update("Update allOrders set orderStatus = 'cancelled' where orderID = " + orderID);
            System.out.println("Order Cancelled");

    }

    public void makeOnlineOrder(String itemName, int storeID, int empID, String address, String cName, int phone)
            throws SQLException {
        try {
            
            ResultSet rs = this.query("Select stock from item where itemName = '" + itemName + "'");
            int stock = 0;
            if (rs.next()) {
                stock = rs.getInt(1);
            }
            if (stock == 0) {
                System.out.println("There are 0 " + itemName + " in stock. Order declined.");
                return;
            }
            rs = this.query("Select count(*) from allOrder");
            int orderID = 0;
            if (rs.next()) {
                orderID = rs.getInt(1) + 1;
            }
            this.update("insert into orders values(" + orderID + ", " + itemName + ")");
            rs = this.query("Select i.price from item i, orders o " +
                    "where i.itemName = o.itemName and o.itemName = '" + itemName + "'");
            double price = 0;
            if (rs.next()) {
                price = rs.getInt(1) + 1;
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            this.update("insert into allOrder values(" + orderID + ", " + storeID + ", " + dateFormat + ", " + price +
                    " in preparation " + empID + ")");
            this.update("insert into orders values(" + orderID + ", '" + itemName + "')");
            this.update("insert into onlineOrder values(" + orderID + ", " +
                    "'" + address + "', '" + cName + "' ," + phone + ")");
            System.out.println("The order has been added. The orderID is: " + orderID);
        } catch (SQLException e) {
            System.out.println("Order declined");
        }

    }

    public void fulfillOnlineOrder(int orderID) {
        try {
            
            this.update("Update allOrder set orderStatus = 'out on delivery' where orderID =" + orderID);
            ResultSet rs = this.query("Select count(*) from delivery");
            int deliveryID = 0;
            if (rs.next()) {
                deliveryID = rs.getInt(1) + 1;
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            this.update("Insert into delivery values(" + deliveryID + ", " + dateFormat + ", 'out on delivery')");
            this.update("Insert into delivers values(" + deliveryID + ", " + orderID + ")");
            rs = this.query("Select itemName from orders where orderID = " + orderID);
            String itemName = null;
            if (rs.next()) {
                rs.getString(1);
            }
            this.update(("Insert into deliveryHasItem(" + deliveryID + ", '" + itemName + "')"));
            System.out.println("The delivery has been added. The deliveryID is: " + deliveryID);
        } catch (SQLException e) {
            System.out.println("Declined");
        }
    }

    public void fulfillDelivery(int deliveryID) {
        try {
            
            this.update("Update delivery set deliveryStatus = 'delivered' where deliveryID = " + deliveryID);
            ResultSet rs = this.query("Select orderID from deliver where deliveryID = " + deliveryID);
            int orderID = 0;
            if (rs.next()) {
                orderID = rs.getInt(1);
            }
            this.update("Update allOrder set orderStatus = 'delivered' where orderID = " + orderID);
            System.out.println("Delivery has been delivered");

        } catch (SQLException e) {
            System.out.println("Declined");
        }
    }

    public void updateOrderStatus(int orderID, String new_status) throws SQLException {
        try {
            
            ResultSet rs = this.query("Select orderStatus from allOrder where orderID = " + orderID);
            String check = null;
            if (rs.next()) {
                check = rs.getString(1);
            }
            if (check == "Delivered" || check == "Finished" || check == "Cancelled") {
                System.out.println("Declined");
                return;
            }
            String checkInStore = null;
            rs = this.query("Select * from inStoreOrder where orderID = " + orderID);
            if (rs.next()) {
                checkInStore = rs.getString(1);
            }
            if (checkInStore != null && new_status == "Delivered") {
                System.out.println("Declined");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Declined");
        }
    }

    public void deleteOnlinePurchase(int orderID) {
        
        this.update("delete from onlineOrder where orderID = " + orderID);
        this.update("delete from allOrder where orderID = " + orderID);
        this.update("delete from orders where orderID = " + orderID);
        this.update("delete from delivers where orderID = " + orderID);
        System.out.print("Order Cancelled");
    }

    public void checkOrderStatus(int orderID) {
        try {
            
            ResultSet rs = this.query("Select orderStatus from allOrders where orderID = " + orderID);
            String orderStatus = null;
            if (rs.next()) {
                orderStatus = rs.getString(1);
            }
            System.out.println("The order status is : " + orderStatus);
        } catch (SQLException e) {
            System.out.println("Order not found");
        }
    }

    public void addItem(String itemName, double cost) {
        
        this.update("Insert into item values('" + itemName + "', 0," + cost + ")");
        System.out.println(itemName + " has been added");
    }

    public void changeStock(String itemName, int stock) {
        
        this.update("Update item set stock = " + stock + " where itemName = '" + itemName + "'");
        System.out.println("The stock of " + itemName + " has been updated");
    }

    public void addStore(int storeID, String city, String province, String location, int managerID, int menuID) {
        
        this.update("Insert into store values(" + storeID +
                ", '" + city + "', '" + province + "', '" + location + "')");
        this.update("Insert into manages values(" + managerID + ", " + storeID + ")");
        this.update("Insert into manages values(" + storeID + ", " + menuID + ")");
        System.out.println("Store has been added");
    }

    public void addRegularEmployee(String name, String gender, int storeID, int managerID, int empID) {
         try {
            
            ResultSet rs = this.query("Select storeID from store where storeID = " + storeID);
            rs.next();
            rs = this.query("Select managerID from manager where storeID = " + storeID);
            rs.next();
             this.update("Insert into employee values(" + empID + " ,'" + name + "' ,'" + gender + "')");
             this.update("Insert into regularEmployee values(" + empID + " ," + storeID + " ," + managerID + ")");
        } catch (SQLException e) {
            System.out.println("Store or manager doesn't exist.");
        }
    }
    
    public Boolean existEmployee(String name, String empID) {
    	String q = "SELECT * FROM employee WHERE ename = '" + name + "' AND empID = " + empID;
    	ResultSet rs = this.query(q);
    	Boolean b = false;
    	try {
    		while(rs.next()) {
    			JOptionPane.showMessageDialog(null, "Welcome, " + rs.getString("ename"));
    		}
    	} catch(SQLException e) {
    		b = false;
    		return b;
    	}
    	return b;
    }
    
    public Boolean existEmployee(String name, int empID) {
    	return existEmployee(name, Integer.toString(empID));
    }
    
    public void deleteEmployee(String Condition){
		String query= "DELETE FROM employee WHERE "+Condition;
		update(query);
	}
    
	public ResultSet queryItem(boolean itemName, boolean stock, boolean price, String condition){
		String query=projectItem(itemName, stock, price)+" FROM item ";
		if(!condition.equals(""))
			query = query+selectItem(condition);
		ResultSet rs = query(query);
		return rs;
		//need to output this
	}
	
	private String selectItem(String condition){
		String query="";
		if(!(condition.equals(""))){
			query= "WHERE "+condition;
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
	
	public ResultSet SaleOfEachStore(){
		ResultSet rs = null;
		String query ="Select storeID, SUM(price) From allOrder Group By storeID";
		rs=query(query);
		return rs;
	}
	
	public ResultSet MaxMinPrice(){
		ResultSet rs = null;
		
		return rs;
	}
	    //find all employees who work at storeID
    public ResultSet joinEmpStore (int storeID) {
        String query = "Select e.empID from regularemployee e, store s " +
                "where e.storeID = s.storeID and e.storeID = " + storeID;
        ResultSet rs = this.query(query);
        return rs;
    }

    //divison query
    //find all store which have all menus
    public ResultSet findStoreAllMenu() {
        String query = "Select s.storeID from store s where not exists" +
                " ((select m.menuID from menu m) " +
                "minus (select h.menuID from storehasmenus h where h.storeID=s.storeID))";
        ResultSet rs = this.query(query);
        return rs;
    }
}

