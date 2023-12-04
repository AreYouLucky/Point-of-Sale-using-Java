/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplestoresystem;

/**
 *
 * @author User
 */
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;

public class showProduct extends db{
    

    
    public void showProducts(JTable productTable){
        try{
            PreparedStatement stmt = con().prepareStatement("Select productID,name,brand,variation,type,"
                    + "originalPrice,retailPrice,stocks from products");
            ResultSet rs = stmt.executeQuery();
            
            String[] header = {"Product ID","Product Name","Product Brand","Product Variation","Product Type","originalPrice","retailPrice","Number of Stocks"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            productTable.setModel(dtm);
            while(rs.next()){
                Object[] products = {rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getDouble(7),rs.getInt(8)};
                dtm.addRow(products);
            }
            
            stmt.close();
            con().close();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for(int i = 0; i<8;i++){
                productTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            }
            
            
            
        }catch(Exception e){
            System.out.println(e+"show");
        }
        
    }
    
    public void searchProducts(JTable productTable,String text){
        try{
            String query = text+"%";
            PreparedStatement stmt = con().prepareStatement("Select * from products where (name) like ? or (type) like ?");
            stmt.setString(1, query);
            stmt.setString(2, query);
            ResultSet rs = stmt.executeQuery();
            
            String[] header = {"Product ID","Product Name","Product Brand","Product Variation","Product Type","originalPrice","retailPrice","Number of Stocks"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            productTable.setModel(dtm);
            while(rs.next()){
                Object[] products = {rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getDouble(7),rs.getInt(8)};
                dtm.addRow(products);
            }
            
            stmt.close();
            con().close();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for(int i = 0; i<8;i++){
                productTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            }
            
            
        }catch(Exception e){
            System.out.println(e+"search");
        }
        
    }
    
    public void outOfStocks(JTable productTable){
        try{
            PreparedStatement stmt = con().prepareStatement("Select * from products where (stocks) <= 0");
            ResultSet rs = stmt.executeQuery();
            
            String[] header = {"Product ID","Product Name","Product Brand","Product Variation","Product Type","originalPrice","retailPrice","Number of Stocks"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            productTable.setModel(dtm);
            while(rs.next()){
                Object[] products = {rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getDouble(7),rs.getInt(8)};
                dtm.addRow(products);
            }
            
            stmt.close();
            con().close();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            for(int i = 0; i<8;i++){
                productTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
            }
            
            
        }catch(Exception e){
            System.out.println(e+"outofStocks");
        }
        
    }
    


    
    
}
