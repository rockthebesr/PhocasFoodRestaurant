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
            JOptionPane.showMessageDialog(null, "start");
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

    private ResultSet query(String queryString) {
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
            JOptionPane.showMessageDialog(null, "Update failed");
            e.printStackTrace();
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
                JOptionPane.showMessageDialog(null, "There are 0 " + itemName + " in stock. Order declined.");
                return;
            }
            rs = this.query("Select count(*) from allOrder");
            int orderID = 0;
            if (rs.next()) {
                orderID = rs.getInt(1) + 1;
            }
            rs = this.query("Select price from item where itemName = '" + itemName + "'");
            double price = 0;
            if (rs.next()) {
                price = rs.getInt(1) + 1;
            }
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss a");
            java.util.Date date = new java.util.Date();
            String str = "TO_DATE('" + dateFormat.format(date) + "', 'DD/MON/YY HH:MI:SSAM')";
            this.update("insert into allOrder values(" + orderID + ", " + storeID + ", " + str + ", " + price +
                    ", 'in preparation', " + empID + ")");
            this.update("insert into inStoreOrder values(" + orderID + " )");
            this.update("insert into orders values(" + orderID + ", '" + itemName + "')");
            JOptionPane.showMessageDialog(null, "The order has been added. The orderID is: " + orderID);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Order declined");
        }
    }

    public void fulfillInStoreOrder(int orderID) throws SQLException {
        
        this.update("Update allOrder set orderStatus = 'finished' where orderID = " + orderID);
        JOptionPane.showMessageDialog(null, "Order Finished");
    }

    public void cancelInStoreOrder(int orderID) throws SQLException {
            
            this.update("Update allOrder set orderStatus = 'cancelled' where orderID = " + orderID);
            JOptionPane.showMessageDialog(null, "Order Cancelled");

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
                JOptionPane.showMessageDialog(null, "There are 0 " + itemName + " in stock. Order declined.");
                return;
            }
            rs = this.query("Select count(*) from allOrder");
            int orderID = 0;
            if (rs.next()) {
                orderID = rs.getInt(1) + 1;
            }
            rs = this.query("Select price from item where itemName = '" + itemName + "'");
            double price = 0;
            if (rs.next()) {
                price = rs.getInt(1) + 1;
            }
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss a");
            java.util.Date date = new java.util.Date();
            String str = "TO_DATE('" + dateFormat.format(date) + "', 'DD/MON/YY HH:MI:SSAM')";
            this.update("insert into allOrder values(" + orderID + ", " + storeID + ", " + str + ", " + price +
                    ", 'in preparation', " + empID + ")");
            this.update("insert into orders values(" + orderID + ", '" + itemName + "')");
            this.update("insert into onlineOrder values(" + orderID + ", " +
                    "'" + address + "', '" + cName + "' ," + phone + ")");
            JOptionPane.showMessageDialog(null, "The order has been added. The orderID is: " + orderID);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Order declined");
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
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss a");
            java.util.Date date = new java.util.Date();
            String str = "TO_DATE('" + dateFormat.format(date) + "', 'DD/MON/YY HH:MI:SSAM')";
            this.update("Insert into delivery values(" + deliveryID + ", " + str + ", 'out on delivery')");
            this.update("Insert into delivers values(" + deliveryID + ", " + orderID + ")");
            rs = this.query("Select itemName from orders where orderID = " + orderID);
            String itemName = null;
            if (rs.next()) {
                rs.getString(1);
            }
            this.update(("Insert into deliveryHasItem(" + deliveryID + ", '" + itemName + "')"));
            JOptionPane.showMessageDialog(null, "The delivery has been added. The deliveryID is: " + deliveryID);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Declined");
        }
    }

    public void fulfillDelivery(int deliveryID) {
        try {
            
            this.update("Update delivery set deliveryStatus = 'delivered' where deliveryID = " + deliveryID);
            ResultSet rs = this.query("Select orderID from delivers where deliveryID = " + deliveryID);
            int orderID = 0;
            if (rs.next()) {
                orderID = rs.getInt(1);
            }
            this.update("Update allOrder set orderStatus = 'delivered' where orderID = " + orderID);
            JOptionPane.showMessageDialog(null, "Delivery has been delivered");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Declined");
        }
    }

    public void updateOrderStatus(int orderID, String new_status) throws SQLException {
        try {


            ResultSet rs = this.query("Select orderStatus from allOrder where orderID = " + orderID);


            String check = null;


            if (!new_status.equals("in preparation") && !new_status.equals("cancelled") && !new_status.equals("finished") &&
                    !new_status.equals("delivered") && !new_status.equals("out on delivery")) {


                throw new SQLException();
            }
            if (rs.next()) {
                check = rs.getString(1);
            }

            this.update("update allOrder set orderStatus = '" + new_status + "' where orderID = " + orderID);


            JOptionPane.showMessageDialog(null, "Order " + orderID + " has been updated to " + new_status);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Declined");
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
            
            ResultSet rs = this.query("Select orderStatus from allOrder where orderID = " + orderID);
            String orderStatus = null;
             try {
                if (rs.next()) {
                    orderStatus = rs.getString(1);
                }
            JOptionPane.showMessageDialog(null, "The order status is : " + orderStatus);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Order not found");
        }
    }

    public void addItem(String itemName, double cost) {
        
        this.update("Insert into item values('" + itemName + "', 0," + cost + ")");
        JOptionPane.showMessageDialog(null, itemName + " has been added");
    }

    public void changeStock(String itemName, int stock) {
        
        this.update("Update item set stock = " + stock + " where itemName = '" + itemName + "'");
        JOptionPane.showMessageDialog(null, "The stock of " + itemName + " has been updated");
    }

    public void addStore(int storeID, String city, String province, String location, int managerID, int menuID) {
        
        this.update("Insert into store values(" + storeID +
                ", '" + city + "', '" + province + "', '" + location + "'," + managerID + ")");
        this.update("Insert into manages values(" + managerID + ", " + storeID + ")");
        this.update("Insert into storeHasMenus values(" + storeID + ", " + menuID + ")");
        JOptionPane.showMessageDialog(null, "Store has been added");
    }

    public void addRegularEmployee(String name, String gender, int storeID, int managerID, int empID) {
         try {
            
            ResultSet rs = this.query("Select storeID from store where storeID = " + storeID);
            rs.next();
            rs = this.query("Select empID from manager where empID = " + managerID);
            rs.next();
             this.update("Insert into employee values(" + empID + " ,'" + name + "' ,'" + gender + "')");
             this.update("Insert into regularEmployee values(" + empID + " ," + storeID + " ," + managerID + ")");
             JOptionPane.showMessageDialog(null, "Employee " + empID + " added.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Store or manager doesn't exist.");
        }
    }
    
    public Boolean existEmployee(String name, String empID) {
    	String q = "SELECT * FROM employee WHERE ename = '" + name + "' AND empID = " + empID;
    	ResultSet rs = this.query(q);
    	Boolean b = false;
    	try {
    		while(rs.next()) {
    			JOptionPane.showMessageDialog(null, "Welcome, " + rs.getString("ename"));
    			b = true;
    		}
    	} catch(SQLException e) {
    		b = false;
    		return b;
    	}
    	return b;
    }
    
    public Boolean isManager(String empID) {
    	String q = "SELECT * FROM manager WHERE empID = " + empID;
    	ResultSet rs = this.query(q);
    	Boolean b = false;
    		try {
				if(rs.next()) {
					b = true;
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "emp ID not found in managers");
				b = false;
			}
    	return b;
    }
    
    public Boolean isManager(int empID) {
    	return isManager(Integer.toString(empID));
    }
    
    public int getStoreIdForEmp(String empID) {
    	String q;
    	if (isManager(empID)) {
        	q = "SELECT storeID FROM manages WHERE empID = " + empID;
    	} else {
    		q = "SELECT storeID FROM regularEmployee WHERE empID = " + empID;
    	}
    	ResultSet rs = this.query(q);
    	try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "can not find storeID for this employee");
		}
    	return 0;
    }
    
    public Boolean existEmployee(String name, int empID) {
    	return existEmployee(name, Integer.toString(empID));
    }
    
    public void deleteEmployee(String Condition){
		String query= "DELETE FROM employee WHERE "+Condition;
		update(query);
		JOptionPane.showMessageDialog(null, Condition + " has been deleted");
	}
    
	public ResultSet generalQuery(String select,String from, String condition){
		String query=project(select)+" FROM " + from;
		if(!condition.equals(""))
			query = query+" "+select(condition);
		ResultSet rs = query(query);
		return rs;
		//need to output this
	}
	
	private String select(String condition){
		String query="";
		if(!(condition.equals(""))){
			query= "WHERE "+condition;
		}
		return query;
	}
	
	private String project(String select){
		String query= "SELECT "+select;
		return query;
	}
	//show sales of each store
	public ResultSet SaleOfEachStore(){
		ResultSet rs = null;
		String query ="Select storeID, SUM(price) From allOrder Group By storeID";
		rs=query(query);
		return rs;
	}
	//sales of each province
	public ResultSet SaleOfEachProvince(){
		ResultSet rs = null;
		String query ="Select Province, SUM(price) From allOrder a, store s where a.storeID = s.storeID Group By Province";
		rs=query(query);
		return rs;
	}
	//find max or min priced-item
	public ResultSet MaxMinPrice(String condition){
		ResultSet rs = null;
		if(condition.equalsIgnoreCase("max")){
			rs=query("Select i1.itemName, i1.price From item i1 where price = (select max(i2.price) from item i2)");
		}else if(condition.equalsIgnoreCase("min")){
			rs=query("Select i1.itemName, i1.price From item i1 where price = (select min(i2.price) from item i2)");
		}
		return rs;
	}
	    //find all employees who work at storeID
    public ResultSet findEmpIDForStore (int storeID) {
        String query = "Select e.empID from regularemployee e, store s where e.storeID = s.storeID and e.storeID = " + storeID;
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

    //toString method of ResultSet
    public String resultSetToString(ResultSet rs){
    	String result = "";
    	try{
    		ResultSetMetaData rsmd = rs.getMetaData();
    		int columnsNumber = rsmd.getColumnCount();
    		while (rs.next()) {
    			for (int i = 1; i <= columnsNumber; i++) {
    				String columnValue = rs.getString(i);
    				result += rsmd.getColumnName(i)  + ": " + columnValue+" " ;
    			}
    			result+="\n";
    		}
    	}catch(SQLException e){
    		result ="Query Failed";
    	}
    	return result;
    }

    
        public void addMenu(int menuID, int serveStartTime, int serveEndTime) {
        String query = "Insert into menu values(" + menuID + ", " + serveStartTime + ", " + serveEndTime + ")";
        this.update(query);
        JOptionPane.showMessageDialog(null, "Menu " + menuID + " has been added");
    }

    public void deleteMenu(int menuID) {
        String query = "delete from menu where menuID = " + menuID;
        this.update(query);
        String query2 = "delete from storeHasmenus where menuID = " + menuID;
        this.update(query2);
        JOptionPane.showMessageDialog(null, "Menu " + menuID + " has been deleted");
    }
}

