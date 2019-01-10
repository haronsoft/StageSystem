package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import stagearea.FXMLDocumentController;

public class ConnectionClass {
public Connection connection;
    public  Connection getConnection(){

        String dbName="Fxdataa";
        String userName="root";
        String password="";

        try {
           // Class.forName("com.mysql.jdbc.Driver").newInstance();   
           Connection connection;
        connection= DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
        JOptionPane.showMessageDialog(null, "Connected to database server");
           // FXMLDocumentController myMainController = new FXMLDocumentController();
            //JOptionPane.showMessageDialog(null,myMainController.getClass());
        } catch (Exception e) {
            // e.printStackTrace();
            JOptionPane.showInternalMessageDialog(null, e.getMessage());
        }


        return connection;
    }

}
