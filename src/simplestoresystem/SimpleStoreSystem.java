package simplestoresystem;

import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class SimpleStoreSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        loginForm login = new loginForm();
        login.setVisible(true);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        
    }
    
}
