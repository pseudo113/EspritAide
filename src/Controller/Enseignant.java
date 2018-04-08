/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Entity.Classe;
import Entity.User;
import Service.ClasseDAO;
import Service.UserDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Password;

/**
 *
 * @author LENOVO
 */
public class Enseignant implements Initializable {

    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXTextField prenom;
    @FXML
    private TableColumn<?, ?> tc_prenom;
    @FXML
    private TableColumn<?, ?> tc_nom;
    @FXML
    private TableColumn<?, ?> tc_mail;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton ajoutEnseignant;
    @FXML
    private TableView<User> tv_enseignant;
    @FXML
    private TableColumn<?, ?> tc_username;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField tf_image;
    @FXML
    private JFXButton ajoutphoto;
    
      File file;
      Stage stage ;
    @FXML
    private ImageView image_view;
    
    @FXML
    private TableColumn<?, ?> tc_image;
     @FXML
    private void ajouterphoto(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                
                new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif")
        );
        

        file = fileChooser.showOpenDialog(stage);
        
        
        if (file!=null) {
            try {
                String img = file.toURI().toURL().toString();
                System.out.println(img);
                System.out.println("jawek béhi");
                tf_image.setText(img);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Enseignant.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
      
    }
    private String md5(String s) {
    try {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(s.getBytes(), 0, s.length());
        BigInteger i = new BigInteger(1,m.digest());
        return String.format("%1$032x", i);         
    } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
    }
    return null;
}   

    @FXML
    private void ajouter(ActionEvent event) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserDAO ud = new UserDAO();
        Password p = new Password();
        

        User u = new User(username.getText(), nom.getText(), prenom.getText(), "a:1:{i:0;s:16:\"ROLE_SUPER_ADMIN\";}", mail.getText(),p.hashPassword(password.getText()), tf_image.getText());
        
        System.out.println("1");
        ud.insertUser(u);
         System.out.println("3");
         username.clear();
         nom.clear();
         prenom.clear();
         mail.clear();
         password.clear();
         tf_image.clear();
         tableUpdateC();
        
    }
    public void tableUpdateC() {
        UserDAO cs = new UserDAO();
        ObservableList<User> list = cs.displayc();
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tc_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
      tc_username.setCellValueFactory(new PropertyValueFactory<>("username"));
      tc_mail.setCellValueFactory(new PropertyValueFactory<>("email"));
       
      tc_image.setCellValueFactory(new PropertyValueFactory<>("image_name"));

        //cl_shipname.setCellValueFactory(new PropertyValueFactory<>("id_navire"));
        //  comisss.setCellValueFactory(new PropertyValueFactory<>("mise_service"));
          //cl_cabine.setCellValueFactory(new PropertyValueFactory<>("nb_cabine"));
         
          
        
      
      
        
     
        
       tv_enseignant.setItems(list);
        tv_enseignant.setTableMenuButtonVisible(true);
        tv_enseignant.refresh();
        
       

    }


    @FXML
    private void supprimer(ActionEvent event) {
         User xp = new User();
        UserDAO xpdao = new  UserDAO();
        int i = tv_enseignant.getSelectionModel().getSelectedItem().getId();
        xpdao.supprimerClasse(i);
        //tab_xp.getItems().clear();
     

        tableUpdateC();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableUpdateC();
        
    }

   void setStage(Stage primaryStage) {
        this.stage = primaryStage;  }

    @FXML
    private void afficheImage(MouseEvent event) {
        String a = tv_enseignant.getSelectionModel().getSelectedItem().getImagename();
        image_view.setImage(new Image(a));
    }
    
  
}
