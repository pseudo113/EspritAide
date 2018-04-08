//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Service;

import Entity.Classe;
import utils.*;
import Entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO {
     Connection myConnection;
    Statement ste ;

    public UserDAO() {
       myConnection = ConnextionSingleton.getInstance()
                .getConnection();
        try {
            ste = myConnection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CoursDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}

    public void insertUser(User user) {
        String req = "INSERT INTO `user`(`username`, `email`, `password`, `roles`, `Nom`, `Prenom`,  `image_name`,`username_canonical`,`email_canonical`,`enabled`) VALUES (?,?,?,?,?,?,?,?,?,?) ";

        try {
            PreparedStatement ps = this.myConnection.prepareStatement(req);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getNom());
            ps.setString(6, user.getPrenom());
            ps.setString(7,user.getImagename());
            ps.setString(8, user.getUsername());
            ps.setString(9, user.getEmail());
            ps.setInt(10, 1);
          
            ps.execute();
            System.out.println("2");
        } catch (SQLException var5) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, (String)null, var5);
        }

    }

  public ObservableList<User> displayc() {
        String sql = "SELECT * FROM `user` ";
        ObservableList<User> cl = FXCollections.observableArrayList() ;

        try {
            PreparedStatement statement = this.myConnection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                User e = new User();
                e.setId(result.getInt("id"));
                 e.setUsername(result.getString("username"));
                e.setNom(result.getString("nom"));      
                e.setPrenom(result.getString("prenom"));
                e.setEmail(result.getString("email"));
                e.setImagename(result.getString("image_name"));

              
               
                cl.add(e);
                System.out.println(e.getImagename());
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return cl;
    }
        
         public void supprimerClasse(int i) {
        try {

            PreparedStatement st = myConnection.prepareStatement("Delete from `user` where `id`=?");
            st.setInt(1, i);
           
            st.executeUpdate();
        } catch (SQLException ex) {
   javafx.scene.image.Image logo = new javafx.scene.image.Image("images/logo.png");
   
        
    
   
        }
    }
}
