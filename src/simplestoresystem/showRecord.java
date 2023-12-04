/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplestoresystem;

import javax.swing.JTable;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class showRecord extends db {
    
    public void showRecord(JTable recordTable){
        try{
            PreparedStatement stmt = con().prepareStatement("Select productID, name, brand, variation, retailPrice, stocks from products");
            ResultSet rs = stmt.executeQuery();
            String [] header = {"Product ID","Name","Price", "Stocks"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            recordTable.setModel(dtm);
            String name;
            while(rs.next()){
                name = rs.getString(2)+ " "+ rs.getString(3)+" "+rs.getString(4);
                Object[] records = {rs.getInt(1),name,rs.getDouble(5),rs.getInt(6)};
                dtm.addRow(records);
                }
            recordTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            recordTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            recordTable.getColumnModel().getColumn(2).setPreferredWidth(10);
            recordTable.getColumnModel().getColumn(3).setPreferredWidth(10);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            recordTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
            recordTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
            recordTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
            

            stmt.close();
            con().close();
            }catch(Exception e){
                System.out.println(e+" showRecord");
            }
    }
    
    public void searchRecord(JTable recordTable, String text){
        try{
            PreparedStatement stmt = con().prepareStatement("Select productID, name, brand, variation, retailPrice, stocks from products where productID like ? or name like ? or brand like ? or variation like ?");
            int id;
            try{
                id = Integer.parseInt(text);
            }catch(Exception e){
                id = 0;
            }    
            stmt.setInt(1, id);
            String query = text+"%";
            stmt.setString(2, query);
            stmt.setString(3, query);
            stmt.setString(4, query);
            ResultSet rs = stmt.executeQuery();
            String [] header = {"Product ID","Name","Price", "Stocks"};
            DefaultTableModel dtm = new DefaultTableModel(header,0);
            recordTable.setModel(dtm);
            String name;
            while(rs.next()){
                name = rs.getString(2)+ " "+ rs.getString(3)+" "+rs.getString(4);
                Object[] records = {rs.getInt(1),name,rs.getDouble(5),rs.getInt(6)};
                dtm.addRow(records);
                }
            
            recordTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            recordTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            recordTable.getColumnModel().getColumn(2).setPreferredWidth(10);
            recordTable.getColumnModel().getColumn(3).setPreferredWidth(10);
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            recordTable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
            recordTable.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
            recordTable.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
            stmt.close();
            con().close();
            }catch(Exception e){
                System.out.println(e+" searchRecord");
            }
    }
            
    
    
    
}
