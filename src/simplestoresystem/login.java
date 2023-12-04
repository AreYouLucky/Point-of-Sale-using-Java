package simplestoresystem;
import java.sql.*;

public class login extends db{
    
    public boolean logAuth(String username, String password){
        boolean auth = false;
        try{
            String query = "Select * from users where username = ? and password = ?";
            PreparedStatement stmt;
            stmt = con().prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, new String(password));
            ResultSet rs = stmt.executeQuery();
            auth = rs.next();
            stmt.close();
            
        }catch(Exception e){
            System.out.println(e+"login");
        }
        return auth;
    }
    
}
