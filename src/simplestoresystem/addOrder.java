/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplestoresystem;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.sql.Timestamp; 




public class addOrder extends db{
    
    ArrayList<Integer> recordID = new ArrayList<>();
    ArrayList<String> recordName = new ArrayList<>();
    ArrayList<Integer> quantity = new ArrayList<>();
    ArrayList<Double> price = new ArrayList<>();
       
    private double totalPrice = 0;
    private Date datenow;
    private String now;
    
    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public Date getDatenow() {
        return datenow;
    }

    public void setDatenow(Date datenow) {
        this.datenow = datenow;
    }
    
    public void addRecord(int recordID,String recordName, int quantity, double price){
        this.recordID.add(recordID);
        this.recordName.add(recordName);
        this.quantity.add(quantity);
        this.price.add(price);
    }
    
    public void showRecord(JTable orderTable){
        
        String[] header = {"Name","Quantity","Price"};
        DefaultTableModel dtm = new DefaultTableModel(header , 0);
        orderTable.setModel(dtm);
        double total = 0;
        for(int i = 0; i<this.recordID.size();i++){
            Object[] order = {this.recordName.get(i),this.quantity.get(i),this.price.get(i)};
            dtm.addRow(order);
            total = total + this.price.get(i);
            
        }
        setTotalPrice(total);
        orderTable.getColumnModel().getColumn(2).setPreferredWidth(5);
        orderTable.getColumnModel().getColumn(1).setPreferredWidth(5);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        orderTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
        orderTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
    }

    public void removeOrder(int selectedRow){
        this.recordID.remove(selectedRow);
        this.recordName.remove(selectedRow);
        this.quantity.remove(selectedRow);
        this.price.remove(selectedRow);
    }
    
    public void editQuantity(int selectedRow, int quantity, double price){
        this.quantity.set(selectedRow, quantity);
        this.price.set(selectedRow, price);
    }
    
    public void clearOrder(){
        this.recordID.clear();
        this.recordName.clear();
        this.price.clear();
        this.quantity.clear();
    }
    
    public void now(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);
        setNow(dateString);
    }
    
    public void saveOrder(){
        System.out.println(this.recordID.get(0));
        try{
            int lastID = 0;
            PreparedStatement stmt = con().prepareStatement("Insert into orders (totalPrice,orderDate) values (?,?)",Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, getTotalPrice());
            Date date = new Date();
            Timestamp created = new Timestamp(date.getTime());
            stmt.setTimestamp(2, created);
            stmt.execute();
            
                ResultSet rs = stmt.getGeneratedKeys();
                while(rs.next())
                {
                    lastID = rs.getInt(1);
                }
                
            for(int i = 0; i<this.quantity.size();i++){
                stmt = con().prepareStatement("Insert into orderdata (orderId,productID,quantity) values (?,?,?)");
                stmt.setInt(1, lastID);
                stmt.setInt(2, this.recordID.get(i));
                stmt.setInt(3, this.quantity.get(i));
                stmt.execute();
                
                stmt = con().prepareStatement("UPDATE products SET stocks = stocks - ? WHERE productID = ?");
                stmt.setInt(1, this.quantity.get(i));
                stmt.setInt(2, this.recordID.get(i));
                stmt.execute();
            }
            
            stmt.close();
            con().close();
        }catch(SQLException e){
            System.out.println(e+" saveOrder");
        }
    }



    


    
    
    
    
    
    
}
