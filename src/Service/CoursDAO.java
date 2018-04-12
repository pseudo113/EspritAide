package Service;

import Entity.Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
            ps.setInt(2, cours.getId_class().getId());
            ps.setString(3, cours.getType());
            ps.setString(4, cours.getNom());
            ps.setString(5, cours.getModule());
            ps.setString(6, cours.getImage_name());


            ps.execute();

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

   public void modifierCours(int id, Entity.Cours e) {
        try {
            PreparedStatement st = myConnection.prepareStatement("UPDATE `fichier` SET `nom`=?,`module`=?,`image_name`=? ,`type`=?,`id_classe`=?  WHERE `id`='"+id+"'");


            st.setString(1, e.getNom());
            st.setString(2, e.getModule());
            st.setString(3, e.getImage_name());
            st.setString(4, e.getType());
int a = e.getId_class().getId();
             st.setInt(5, a);

            

          
           // st.setInt(4, e.getUser().getId_u());
            
           // st.setInt(5, e.getPropriete().getId());
            
            st.executeUpdate();
            System.out.println("Modifier ");
        } catch (SQLException ex) {
            System.out.println("no" + ex);
        }
   }
    
    public ObservableList<PieChart.Data> Statnb_cabine() {
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        try {
            PreparedStatement st = myConnection.prepareStatement("SELECT COUNT(user.id),user.username  FROM fichier,user WHERE user.id=fichier.id_user GROUP BY user.username");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new PieChart.Data(rs.getString(2), rs.getDouble(1)));
            }
            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
            //System.out.println("ici" + observableList.size());    
            return observableList;

        } catch (SQLException ex) {
            Logger.getLogger(CoursDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public  void archiver(int id, Entity.Cours cours)
    {
        String req = "INSERT INTO `archivefichier`( `id_user`, `id_classe`, `type`, `nom`, `module`, `image_name`) " +
                "VALUES (?,?,?,?,?,?)";
        try {
            System.out.println("CheckPoint : 1 ");
            PreparedStatement ps = myConnection.prepareStatement(req);
            ps.setInt(1, cours.getId_user());
            ps.setInt(2, 3);
            ps.setString(3, cours.getType());
            ps.setString(4, cours.getNom());
            ps.setString(5, cours.getModule());
            ps.setString(6, cours.getImage_name());


            ps.execute();
            PreparedStatement st = myConnection.prepareStatement("Delete from `fichier` where `id`=?");
            st.setInt(1, id);
           
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CoursDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}
