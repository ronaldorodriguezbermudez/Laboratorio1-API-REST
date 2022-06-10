/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *

 */
public class dbConexion {
   private static Connection con=null;
   public static Connection getConnection(){
      try{
         if( con == null ){
            String driver="oracle.jdbc.driver.OracleDriver"; 
            Class.forName(driver);
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "system", "system");
            System.out.println("Conection Succesfull");
         }
     }
     catch(ClassNotFoundException | SQLException ex){
        ex.printStackTrace();
     }
     return con;
   }
}
