/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplestoresystem;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.table.*;

public class orders extends db {
    
    private double sales;
    private double revenue;
    private double cost;
    
    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
    public void showSale(JTable saleHistory){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT * FROM orders ORDER BY orderID DESC");
            ResultSet rs = stmt.executeQuery();
            String[] header = {"Sales ID","Total Price","Date"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            saleHistory.setModel(dtm);
            while(rs.next()){
                Object[] sale = {rs.getInt(1),rs.getDouble(2),rs.getDate(3)};
                dtm.addRow(sale);
            }
            stmt.close();
            con().close();
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for(int i = 0; i<3;i++){
                saleHistory.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            }
            
        }catch(Exception e){
            System.out.print("sale" + e);
        }
    }
    
    public void showPurchase(int orderID, JTable purchaseHistory){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT products.name,orderdata.quantity,products.retailPrice FROM products \n" +
            "INNER JOIN orderdata ON products.productID = orderdata.productID \n" +
            "INNER JOIN orders ON orderdata.orderID = orders.orderID WHERE orderdata.orderID = ?");
            stmt.setInt(1,orderID);
            ResultSet rs = stmt.executeQuery();
            String[] header = {"Name", "Quantity", "Price"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            purchaseHistory.setModel(dtm);
            double price;
            while(rs.next()){
                price = rs.getInt(2)*rs.getDouble(3);
                Object[] purchase = {rs.getString(1),rs.getInt(2),price};
                dtm.addRow(purchase);
            }
            stmt.close();
            con().close();
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for(int i = 0; i<3;i++){
                purchaseHistory.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            }
            
        }catch(Exception e){
            System.out.print("showPurchase "+e);
        }
    }
    
    
    public void totalSales(){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT totalPrice FROM orders");
            ResultSet rs =  stmt.executeQuery();
            double compSales = 0;
            while(rs.next()){
                compSales = compSales + rs.getDouble(1);
            }
            this.setSales(compSales);
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+ " totalSales");
        }
    }
    
    public void totalCost(){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT orderdata.quantity,products.originalPrice FROM products \n" +
                "INNER JOIN orderdata ON products.productID = orderdata.productID \n" +
                "INNER JOIN orders ON orderdata.orderID = orders.orderID;");
            ResultSet rs =  stmt.executeQuery();
            double compCost = 0;
            while(rs.next()){
                compCost = compCost + (rs.getInt(1)*rs.getDouble(2));
            }
            this.setCost(compCost);
            
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+ " totalCost");
        }
    }
    
    public void totalRevenue(){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT orderdata.quantity,products.originalPrice,products.retailPrice FROM products \n" +
                "INNER JOIN orderdata ON products.productID = orderdata.productID \n" +
                "INNER JOIN orders ON orderdata.orderID = orders.orderID;");
            ResultSet rs =  stmt.executeQuery();
            double compRev = 0;
            while(rs.next()){
                compRev = compRev + ((rs.getInt(1)*rs.getDouble(3))-(rs.getInt(1)*rs.getDouble(2)));
            }
            this.setRevenue(compRev);
            
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+ " totalRevenue");
        }
    }
    
    public void eachSales(int ID){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT totalPrice FROM orders WHERE orderID = ?");
            stmt.setInt(1, ID);
            ResultSet rs =  stmt.executeQuery();
            while(rs.next()){
                this.setSales(rs.getDouble(1));
            }
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+ " eachSales");
        }
    }
    
    public void eachCost(int ID){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT orderdata.quantity,products.originalPrice FROM products \n" +
                "INNER JOIN orderdata ON products.productID = orderdata.productID \n" +
                "INNER JOIN orders ON orderdata.orderID = orders.orderID where orderdata.orderID = ?;");
            stmt.setInt(1, ID);
            ResultSet rs =  stmt.executeQuery();
            double compCost = 0;
            while(rs.next()){
                compCost = compCost + (rs.getInt(1)*rs.getDouble(2));
            }
            this.setCost(compCost);
            
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+ " eachCost");
        }
    }
    
    public void eachRevenue(int ID){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT orderdata.quantity,products.originalPrice,products.retailPrice FROM products \n" +
                "INNER JOIN orderdata ON products.productID = orderdata.productID \n" +
                "INNER JOIN orders ON orderdata.orderID = orders.orderID where orderdata.orderID = ?;");
            stmt.setInt(1, ID);
            ResultSet rs =  stmt.executeQuery();
            double compRev = 0;
            while(rs.next()){
                compRev = compRev + ((rs.getInt(1)*rs.getDouble(3))-(rs.getInt(1)*rs.getDouble(2)));
            }
            this.setRevenue(compRev);
            
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+ " eachRevenue");
        }
    }
    
    public void dateSales(String date){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT totalPrice FROM orders WHERE DATE(orderDate) = ?");
            stmt.setString(1, date);
            ResultSet rs =  stmt.executeQuery();
            double compSales = 0;
            while(rs.next()){
                compSales = compSales + rs.getDouble(1);
            }
            this.setSales(compSales);
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+ " eachSales");
        }
    }
    
    public void dateCost(String date){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT orderdata.quantity,products.originalPrice,products.retailPrice FROM products \n" +
                "INNER JOIN orderdata ON products.productID = orderdata.productID \n" +
                "INNER JOIN orders ON orderdata.orderID = orders.orderID where DATE(orders.orderDate) = ?");
            stmt.setString(1, date);
            ResultSet rs =  stmt.executeQuery();
            double compCost = 0, compRev = 0;
            while(rs.next()){
                compCost = compCost + (rs.getInt(1)*rs.getDouble(2));
                compRev = compRev + ((rs.getInt(1)*rs.getDouble(3))-(rs.getInt(1)*rs.getDouble(2)));
            }
            this.setCost(compCost);
            this.setRevenue(compRev);
            
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+ " dateCost");
        }
    }
        
    public void showDateSale(JTable saleHistory, String Date){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT * FROM orders where DATE(orderDate) = ? ORDER BY orderID DESC");
            stmt.setString(1, Date);
            ResultSet rs = stmt.executeQuery();
            String[] header = {"Sales ID","Total Price","Date"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            saleHistory.setModel(dtm);
            while(rs.next()){
                Object[] sale = {rs.getInt(1),rs.getDouble(2),rs.getDate(3)};
                dtm.addRow(sale);
            }
            stmt.close();
            con().close();
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for(int i = 0; i<3;i++){
                saleHistory.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            }
            
        }catch(Exception e){
            System.out.print("sale" + e);
        }
    }  
      
    public void monthSales(String month, String year){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT totalPrice FROM orders WHERE month(orderDate) = ? and YEAR(orderDate) = ?");
            stmt.setString(1, month);
            stmt.setString(2, year);
            ResultSet rs =  stmt.executeQuery();
            double compSales = 0;
            while(rs.next()){
                compSales = compSales + rs.getDouble(1);
            }
            this.setSales(compSales);
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+ " eachSales");
        }
    }
    
    public void monthCost(String month, String year){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT orderdata.quantity,products.originalPrice,products.retailPrice FROM products \n" +
                "INNER JOIN orderdata ON products.productID = orderdata.productID \n" +
                "INNER JOIN orders ON orderdata.orderID = orders.orderID where MONTH(orders.orderDate) = ? AND YEAR(orders.orderDate) = ?");
            stmt.setString(1, month);
            stmt.setString(2, year);
            ResultSet rs =  stmt.executeQuery();
            double compCost = 0, compRev = 0;
            while(rs.next()){
                compCost = compCost + (rs.getInt(1)*rs.getDouble(2));
                compRev = compRev + ((rs.getInt(1)*rs.getDouble(3))-(rs.getInt(1)*rs.getDouble(2)));
            }
            this.setCost(compCost);
            this.setRevenue(compRev);
            
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+ " dateCost");
        }
    }
    
    public void yearSales(String year){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT totalPrice FROM orders WHERE YEAR(orderDate) = ?");
            stmt.setString(1, year);
            ResultSet rs =  stmt.executeQuery();
            double compSales = 0;
            while(rs.next()){
                compSales = compSales + rs.getDouble(1);
            }
            this.setSales(compSales);
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+ " eachSales");
        }
    }
      
    public void showMonthSale(JTable saleHistory,String month, String year){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT * FROM orders where MONTH(orderDate) = ? AND YEAR(orderDate) = ? ORDER BY orderID DESC");
            stmt.setString(1, month);
            stmt.setString(2, year);
            ResultSet rs = stmt.executeQuery();
            String[] header = {"Sales ID","Total Price","Date"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            saleHistory.setModel(dtm);
            while(rs.next()){
                Object[] sale = {rs.getInt(1),rs.getDouble(2),rs.getDate(3)};
                dtm.addRow(sale);
            }
            stmt.close();
            con().close();
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for(int i = 0; i<3;i++){
                saleHistory.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            }
            
        }catch(Exception e){
            System.out.print("sale" + e);
        }
    } 
    
    public void showYearSale(JTable saleHistory, String year){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT * FROM orders where YEAR(orderDate) = ? ORDER BY orderID DESC");
            stmt.setString(1, year);
            ResultSet rs = stmt.executeQuery();
            String[] header = {"Sales ID","Total Price","Date"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            saleHistory.setModel(dtm);
            while(rs.next()){
                Object[] sale = {rs.getInt(1),rs.getDouble(2),rs.getDate(3)};
                dtm.addRow(sale);
            }
            stmt.close();
            con().close();
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for(int i = 0; i<3;i++){
                saleHistory.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            }
            
        }catch(Exception e){
            System.out.print("sale" + e);
        }
    }
    
    public void yearCost(String year){
        try{
            PreparedStatement stmt = con().prepareStatement("SELECT orderdata.quantity,products.originalPrice,products.retailPrice FROM products \n" +
                "INNER JOIN orderdata ON products.productID = orderdata.productID \n" +
                "INNER JOIN orders ON orderdata.orderID = orders.orderID where YEAR(orders.orderDate) = ?");
            stmt.setString(1, year);
            ResultSet rs =  stmt.executeQuery();
            double compCost = 0, compRev = 0;
            while(rs.next()){
                compCost = compCost + (rs.getInt(1)*rs.getDouble(2));
                compRev = compRev + ((rs.getInt(1)*rs.getDouble(3))-(rs.getInt(1)*rs.getDouble(2)));
            }
            this.setCost(compCost);
            this.setRevenue(compRev);
            
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+ " dateCost");
        }
    }
    
}
      

