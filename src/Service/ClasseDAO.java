/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Classe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnextionSingleton;

/**
 *
 * @author LENOVO
 */
public class ClasseDAO {
       Connection myConnection;
    Statement ste ;

    public ClasseDAO() {
       myConnection = ConnextionSingleton.getInstance()
                .getConnection();
        try {
            ste = myConnection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CoursDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
     public void insert(Classe c) {
        String req = "INSERT INTO `classe`( `id_user`, `numero`, `niveau`) " +
                "VALUES (?,?,?)";
        try {
            System.out.println("CheckPoint : 1 ");
            PreparedStatement ps = myConnection.prepareStatement(req);
            ps.setInt(1, c.getIduser());
            ps.setInt(2, c.getNumero());
            ps.setString(3, c.getNiveau());
          


            ps.execute();

        } catch (SQLException ex) {
            Logger.getLogger(CoursDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public ObservableList<Classe> displayc() {
        String sql = "SELECT * FROM `classe` ";
        ObservableList<Classe> cl = FXCollections.observableArrayList() ;

        try {
            PreparedStatement statement = this.myConnection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Classe e = new Classe();
                e.setId(result.getInt("id"));
                 e.setNiveau(result.getString("niveau"));
                e.setNumero(result.getInt("numero"));
             
              
               
                cl.add(e);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return cl;
    }
        
         public void supprimerClasse(int i) {
        try {

            PreparedStatement st = myConnection.prepareStatement("Delete from `classe` where `id`=?");
            st.setInt(1, i);
           
            st.executeUpdate();
        } catch (SQLException ex) {
   javafx.scene.image.Image logo = new javafx.scene.image.Image("images/logo.png");
   
        
    
   
        }
    }
         public List<Classe> displaynavire() {
        String sql = "SELECT * from classe ";
         try {
             PreparedStatement statement = this.myConnection.prepareStatement(sql);
          
             ResultSet result = statement.executeQuery();
             Classe navire;
             ArrayList<Classe> navires = new ArrayList<>();
             
             while (result.next()) {    
                 navire = new Classe();
               
                         
                navire.setId(result.getInt("id"));
                navire.setNumero(result.getInt("numero"));
                navire.setNiveau(result.getString("niveau"));
              
                 navires.add(navire);
             }
             System.out.println(navires.size());
             return navires;
         } catch (SQLException ex) {
             ex.printStackTrace();
         }
         return null; }
         public Classe getClasseById(int id) {
        Classe Navire = new Classe();
        String query = "SELECT * from classe where id=?";
        try {

            PreparedStatement pst = myConnection.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Navire.setId(id);
                Navire.setNiveau(res.getString("niveau"));

                
               

            }
            return Navire;
        } catch (SQLException ex) {
            System.out.println("no" + ex);
            return null;
        }
    }
        
    }
    
   
    

