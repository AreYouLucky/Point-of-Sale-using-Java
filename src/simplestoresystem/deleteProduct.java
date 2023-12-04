/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplestoresystem;

/**
 *
 * @author User
 */
import java.sql.*;

public class deleteProduct extends db{
    
    public void deleteProduct(int productID){
        try{
            PreparedStatement stmt= con().prepareStatement("Delete from products where productID = ?");
            stmt.setInt(1, productID);
            stmt.execute();
            stmt.close();
            con().close();
  
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    
}
