package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Salma
 */
public class ConnextionSingleton {
  static Connection myconnection;
  static String url = "jdbc:mysql://localhost:3306/espritaide";
  static String login = "root";
  static String pwd = "";
  static ConnextionSingleton instance;

    private ConnextionSingleton() {
         try {
       myconnection = DriverManager.getConnection(url,
                   login, pwd);
           System.out.println("connexion etablie");
       } catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
    }
    
    public static ConnextionSingleton getInstance(){
        if(instance== null)
            instance = new ConnextionSingleton();
        return instance;
    }
    
    public Connection getConnection(){
        return myconnection;
    }
    
    
    
}
