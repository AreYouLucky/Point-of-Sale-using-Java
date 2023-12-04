/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplestoresystem;

import java.sql.*;
import javax.swing.*;
import java.sql.Timestamp;    
import java.util.Date;  

public class addEditProduct extends db{
    
    private String name, type, brand, variation;
    private double originalPrice, retailPrice;
    private int stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.toUpperCase();
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
     public String getBrand() {
        return brand.toUpperCase();
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        this.variation = variation.toUpperCase();
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }
    
    
    public void addProduct(){
        
        try{
            PreparedStatement stmt = con().prepareStatement("Insert into products(name,brand,variation,type,originalPrice,retailPrice,stocks,created_at) "
                    + "values(?,?,?,?,?,?,?,?)");
            stmt.setString(1, getName());
            stmt.setString(2,getBrand());
            stmt.setString(3, getVariation());
            stmt.setString(4, getType());
            stmt.setDouble(5, getOriginalPrice());
            stmt.setDouble(6, getRetailPrice());
            stmt.setInt(7, getStock());
            Date date = new Date();
            Timestamp created = new Timestamp(date.getTime());
            stmt.setTimestamp(8, created);
            stmt.execute();
            stmt.close();
            con().close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public void findProduct(int id){
        
        try {
            PreparedStatement stmt;
            stmt = con().prepareStatement("select * from products where productID = ?");
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                setName(rs.getString(2));
                setBrand(rs.getString(3));
                setVariation(rs.getString(4));
                setType(rs.getString(5));
                setOriginalPrice(rs.getDouble(6));
                setRetailPrice(rs.getDouble(7));              
            }
            stmt.close();
            con().close();
            
        } catch (Exception e) {
            System.out.println(e+"findID");
        }
        
        
    }
    
    public void updateProduct(int id){
        try{
            PreparedStatement stmt = con().prepareStatement("Update products set name = ?, brand = ?,"
                    + "variation = ?, type = ?, originalPrice = ?, retailPrice = ? where productID = ?");
            stmt.setString(1, getName());
            stmt.setString(2,getBrand());
            stmt.setString(3, getVariation());
            stmt.setString(4, getType());
            stmt.setDouble(5, getOriginalPrice());
            stmt.setDouble(6, getRetailPrice());
            stmt.setInt(7, id);
            stmt.execute();
            
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e+"update");
        }
    }
    
    public void showStock(int id){ //getting the current stocks pero try hahaha
        try{
            PreparedStatement stmt = con().prepareStatement("select stocks from products where productID = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                setStock(rs.getInt(1));
            }
            
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e + " show Stocks");
        }
        
        
    }
    public void updateStock(int id, int stock){
        try{
            PreparedStatement stmt = con().prepareStatement("Update products set stocks = ? where productID = ? ");
            int updatedStock =  getStock() + stock;
            stmt.setInt(1, updatedStock);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();
            con().close();
            
        }catch(Exception e){
            System.out.println(e + " update Stocks");
            
        }
    }
    
    
    
    

   
    
    
    
}
