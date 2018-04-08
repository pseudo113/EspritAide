package Service;

import Entity.Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnextionSingleton;

public class CoursDAO {

      Connection myConnection;
    Statement ste ;

    public CoursDAO() {
       myConnection = ConnextionSingleton.getInstance()
                .getConnection();
        try {
            ste = myConnection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CoursDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}


    public void insertCours(Cours cours) {
        String req = "INSERT INTO `fichier`( `id_user`, `id_classe`, `type`, `nom`, `module`, `image_name`) " +
                "VALUES (?,?,?,?,?,?)";
        try {
            System.out.println("CheckPoint : 1 ");
            PreparedStatement ps = myConnection.prepareStatement(req);
            ps.setInt(1, cours.getId_user());
            ps.setInt(2, cours.getId_class());
            ps.setString(3, cours.getType());
            ps.setString(4, cours.getNom());
            ps.setString(5, cours.getModule());
           

            ps.setString(6, cours.getImage_name());//TODO fix files


            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CoursDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean deleteCours(int id) {
        String requete = "DELETE FROM `fichier` WHERE id= ? ";
        try {
            PreparedStatement ps = myConnection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            return  true  ;
        } catch (SQLException ex) {
            Logger.getLogger(CoursDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateCours ( Cours cours ){

        String requete = "UPDATE `fichier` SET `type`=?," +
                "`nom`= ? ," +
                "`module`= ?," +

                "`image_name`= ? " +
                " WHERE id = ? "  ;

        try {

            PreparedStatement ps = myConnection.prepareStatement(requete);
            ps.setString(1, cours.getType());
            ps.setString(2, cours.getNom());
            ps.setString(3, cours.getModule());
 
            ps.setString(4, cours.getImage_name());
            ps.setInt(5 , cours.getId());
            int update = ps.executeUpdate();
            ps.close();
            return (update == 1);
        } catch (SQLException ex) {
            Logger.getLogger(CoursDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
