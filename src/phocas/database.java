package phocas;

import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class database {

    Phocas p;

    public void makeInStoreOrder(String itemName, int storeID, int empID) throws SQLException {
        try {
            p.connect();
            ResultSet rs = p.query("Select stock from item where itemName = '" + itemName + "'");
            int stock = 0;
            if (rs.next()) {
                stock = rs.getInt(1);
            }
            if (stock == 0) {
                System.out.println("There are 0 " + itemName + " in stock. Order declined.");
                return;
            }
            rs = p.query("Select count(*) from allOrder");
            int orderID = 0;
            if (rs.next()) {
                orderID = rs.getInt(1) + 1;
            }
            p.update("insert into orders values(" + orderID + ", " + itemName + ")");
            rs = p.query("Select i.price from item i, orders o " +
                    "where i.itemName = o.itemName and o.itemName = '" + itemName + "'");
            double price = 0;
            if (rs.next()) {
                price = rs.getInt(1) + 1;
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            p.update("insert into allOrder values(" + orderID + ", " + storeID + ", " + dateFormat + ", " + price +
                    " in preparation " + empID + ")");
            p.update("insert into inStoreOrder values(" + orderID + " )");
            p.update("insert into orders values(" + orderID + ", '" + itemName + "')");
            System.out.println("The order has been added. The orderID is: " + orderID);
        } catch (SQLException e) {
            System.out.println("Order declined");
        }

    }

    public void fulfillInStoreOrder(int orderID) throws SQLException {
        p.connect();
        p.update("Update allOrders set orderStatus = 'finished' where orderID = " + orderID + ")");
        System.out.println("Order Finished");
    }

    public void cancelInStoreOrder(int orderID) throws SQLException {
            p.connect();
            p.update("Update allOrders set orderStatus = 'cancelled' where orderID = " + orderID + " )");
            System.out.println("Order Cancelled");

    }

    public void makeOnlineOrder(String itemName, int storeID, int empID, String address, String cName, int phone)
            throws SQLException {
        try {
            p.connect();
            ResultSet rs = p.query("Select stock from item where itemName = '" + itemName + "'");
            int stock = 0;
            if (rs.next()) {
                stock = rs.getInt(1);
            }
            if (stock == 0) {
                System.out.println("There are 0 " + itemName + " in stock. Order declined.");
                return;
            }
            rs = p.query("Select count(*) from allOrder");
            int orderID = 0;
            if (rs.next()) {
                orderID = rs.getInt(1) + 1;
            }
            p.update("insert into orders values(" + orderID + ", " + itemName + ")");
            rs = p.query("Select i.price from item i, orders o " +
                    "where i.itemName = o.itemName and o.itemName = '" + itemName + "'");
            double price = 0;
            if (rs.next()) {
                price = rs.getInt(1) + 1;
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            p.update("insert into allOrder values(" + orderID + ", " + storeID + ", " + dateFormat + ", " + price +
                    " in preparation " + empID + ")");
            p.update("insert into orders values(" + orderID + ", '" + itemName + "')");
            p.update("insert into onlineOrder values(" + orderID + ", " +
                    "'" + address + "', '" + cName + "' ," + phone + ")");
            System.out.println("The order has been added. The orderID is: " + orderID);
        } catch (SQLException e) {
            System.out.println("Order declined");
        }

    }

    public void fulfillOnlineOrder(int orderID) {
        try {
            p.connect();
            p.update("Update allOrder set orderStatus = 'out on delivery' where orderID =" + orderID);
            ResultSet rs = p.query("Select count(*) from delivery");
            int deliveryID = 0;
            if (rs.next()) {
                deliveryID = rs.getInt(1) + 1;
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            p.update("Insert into delivery values(" + deliveryID + ", " + dateFormat + ", 'out on delivery')");
            p.update("Insert into delivers values(" + deliveryID + ", " + orderID);
            rs = p.query("Select itemName from orders where orderID = " + orderID);
            String itemName = null;
            if (rs.next()) {
                rs.getString(1);
            }
            p.update(("Insert into deliveryHasItem(" + deliveryID + ", '" + itemName + "')"));
            System.out.println("The delivery has been added. The deliveryID is: " + deliveryID);
        } catch (SQLException e) {
            System.out.println("Declined");
        }
    }

    public void fulfillDelivery(int deliveryID) {
        try {
            p.connect();
            p.update("Update delivery set deliveryStatus = 'delivered' where deliveryID = " + deliveryID);
            ResultSet rs = p.query("Select orderID from deliver where deliveryID = " + deliveryID);
            int orderID = 0;
            if (rs.next()) {
                orderID = rs.getInt(1);
            }
            p.update("Update allOrder set orderStatus = 'delivered' where orderID = " + orderID);
            System.out.println("Delivery has been delivered");

        } catch (SQLException e) {
            System.out.println("Declined");
        }
    }

    public void updateOrderStatus(int orderID, String new_status) throws SQLException {
        try {
            p.connect();
            ResultSet rs = p.query("Select orderStatus from allOrder where orderID = " + orderID);
            String check = null;
            if (rs.next()) {
                check = rs.getString(1);
            }
            if (check == "Delivered" || check == "Finished" || check == "Cancelled") {
                System.out.println("Declined");
                return;
            }
            String checkInStore = null;
            rs = p.query("Select * from inStoreOrder where orderID = " + orderID);
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
        p.connect();
        p.update("delete from onlineOrder where orderID = " + orderID);
        p.update("delete from allOrder where orderID = " + orderID);
        p.update("delete from orders where orderID = " + orderID);
        p.update("delete from delivers where orderID = " + orderID);
        System.out.print("Order Cancelled");
    }

    public void checkOrderStatus(int orderID) {
        try {
            p.connect();
            ResultSet rs = p.query("Select orderStatus from allOrders where orderID = " + orderID);
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
        p.connect();
        p.update("Insert into item values('" + itemName + "', 0," + cost);
        System.out.println(itemName + " has been added");
    }

    public void changeStock(String itemName, int stock) {
        p.connect();
        p.update("Update item set stock = " + stock + " where itemName = '" + itemName + "'");
        System.out.println("The stock of " + itemName + " has been updated");
    }

    public void addStore(int storeID, String city, String province, String location, int managerID, int menuID) {
        p.connect();
        p.update("Insert into store values(" + storeID +
                ", '" + city + "', '" + province + "', '" + location + "'");
        p.update("Insert into manages values(" + managerID + ", " + storeID);
        p.update("Insert into manages values(" + storeID + ", " + menuID);
        System.out.println("Store has been added");
    }

    public void addRegularEmployee(String name, String gender, int storeID, int managerID, int empID) {
         try {
            p.connect();
            ResultSet rs = p.query("Select storeID from store where storeID = " + storeID);
            rs.next();
            rs = p.query("Select managerID from manager where storeID = " + storeID);
            rs.next();
             p.update("Insert into employee values(" + empID + " ,'" + name + "' ,'" + gender + "'");
             p.update("Insert into regularEmployee values(" + empID + " ," + storeID + " ," + managerID);
        } catch (SQLException e) {
            System.out.println("Store or manager doesn't exist.");
        }
    }
}

