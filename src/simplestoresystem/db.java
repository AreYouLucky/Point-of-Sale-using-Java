package simplestoresystem;

import java.sql.*;

public class db {
    public Connection con(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost/simplestore?user=root&password=");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println(e.toString());
            return null;           
        }
    }
}
