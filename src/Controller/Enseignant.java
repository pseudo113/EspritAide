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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
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
    private Label mail_c;
    @FXML
    private Label mdp_c1;
    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    @FXML
    private Label lab3;
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
         TrayNotification tray = new TrayNotification("succes", "succes ",NotificationType.SUCCESS);
   javafx.scene.image.Image logo = new javafx.scene.image.Image("Ressources/logo.png");
   
        
    
        tray.setTitle("EspritAide.");
        tray.setImage(logo);
        tray.setMessage("Enseignant ajouté ");
        tray.setRectangleFill(Paint.valueOf("#990000"));
        tray.setAnimationType(AnimationType.SLIDE);
        tray.showAndDismiss(Duration.seconds(4));
        
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

    @FXML
    private void controle_mail(KeyEvent event) {
        String pattern = "^(.+)@(.+)$";
    String mdp = mail.getText();
    
     if((mail.getText().equals("")))
            {mail_c.setText("Vous devez ecrire une Adresse-mail !");
            mail_c.setTextFill(Color.web("#C70039  "));
            ajoutEnseignant.setDisable(true);
            }
     else{ lab1.setText("");
     }
    
        if(!mdp.matches(pattern))
        {
            mail_c.setText("Email invalide !!!");
            ajoutEnseignant.setDisable(true);
            mail_c.setTextFill(Color.web("#C70039 "));
        }
        
        else
        {
         
         mail_c.setText("");}
        

                    
  
       
        
        
        
    }
    
    
    @FXML
    private void controle_m(MouseEvent event) {
          String u=mail.getText();
   
    if((mail.getText().equals("")))
            {mail_c.setText("Vous devez ecrire une Adresse-mail !");
            mail_c.setTextFill(Color.web("#C70039  "));
            ajoutEnseignant.setDisable(true);
            }
   
    
    
        
     
    }

    @FXML
    private void controle_mpd(KeyEvent event) {
    String pattern1 = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
    String pattern2 = "^[a-zA-Z]*$";
 String pattern3 = "^(.+)@(.+)$";
 
    String mdp = password.getText();
     String uu=username.getText();
   String uuu = nom.getText();
String uuuu = prenom.getText();
 String mdpp = mail.getText();
 
 
    
     if((password.getText().equals("")))
            {mdp_c1.setText("Vous devez ecrire un Mot de passe !");
            mdp_c1.setTextFill(Color.web("#C70039  "));
            ajoutEnseignant.setDisable(true);
            }
     else{ lab1.setText("");
    }
        if(!mdp.matches(pattern1))
        {
            mdp_c1.setText("Chiffres + Lettres(Maj/Min)+ @,#,$,%,^,&,+,= !!!");
            mdp_c1.setTextFill(Color.web("#C70039  "));
            ajoutEnseignant.setDisable(true);
           // ajoutEnseignant.setVisible(false);
        }
        else
        {
            
    mdp_c1.setText("");}
        
    
    if(mdp.matches(pattern1)&&
(uu.matches(pattern2))&&
     (uuu.matches(pattern2))&&               
    (uuuu.matches(pattern2))&&
    (mdpp.matches(pattern3)))
            
    {
        ajoutEnseignant.setDisable(false);}
    else{
        ajoutEnseignant.setDisable(true);}
    }
    
     @FXML
    private void controle_md(MouseEvent event) {
           String u=password.getText();
   
    if((password.getText().equals("")))
            {mdp_c1.setText("Vous devez ecrire un Mot de passe !");
            mdp_c1.setTextFill(Color.web("#C70039  "));
            ajoutEnseignant.setDisable(true);
            }
    else{ ajoutEnseignant.setDisable(false);}
    }
    
    

   

    
    
    @FXML
    private void controle_username(MouseEvent event) {       
    String u=username.getText();
   
    if((username.getText().equals("")))
            {lab1.setText("Vous devez choisir un username !");
            lab1.setTextFill(Color.web("#C70039  "));
            ajoutEnseignant.setDisable(true);
            }
     else                  
        {lab1.setText("");
         }
    }
            
        
    
        @FXML
    private void controle_user(KeyEvent event) {
          String u=username.getText();
   
    if((username.getText().equals("")))
            {lab1.setText("Vous devez choisir un username !");
            lab1.setTextFill(Color.web("#C70039  "));
            ajoutEnseignant.setDisable(true);
            }
     else{ lab1.setText("");
              
    }
    
        String pattern = "^[a-zA-Z]*$";
    String uu = username.getText();
        if(!uu.matches(pattern))
      {lab1.setText("Vous ne devez pas saisir des caractères speciaux");
            lab1.setTextFill(Color.web("#C70039  "));
      ajoutEnseignant.setDisable(true);}
  
        else{ lab1.setText("");
        }
    }
        
    
    
  @FXML
    private void controle_nom(MouseEvent event) {
 String u=nom.getText();
   
    if((nom.getText().equals("")))
            {lab2.setText("Vous devez choisir un nom !");
            lab2.setTextFill(Color.web("#C70039  "));
            ajoutEnseignant.setDisable(true);
            }
     else                  
        {lab2.setText("");
       }
    }
    
    
    
    
    
    
    @FXML
    private void controle_prenom(MouseEvent event) {
       String u=prenom.getText();
   
    if((prenom.getText().equals("")))
            {lab3.setText("Vous devez choisir un prenom !");
            lab3.setTextFill(Color.web("#C70039  "));
            ajoutEnseignant.setDisable(true);
            }
     else                  
        {lab3.setText("");
         }
    
    
    }


    @FXML
    private void controle_n(KeyEvent event) {
                  String u=nom.getText();
   
    if((nom.getText().equals("")))
            {lab2.setText("Vous devez choisir un nom !");
            lab2.setTextFill(Color.web("#C70039  "));
            ajoutEnseignant.setDisable(true);
            }
     else{ lab2.setText("");
     }
    
        String pattern = "^[a-zA-Z]*$";
    String uu = nom.getText();
        if(!uu.matches(pattern))
      {lab2.setText("Vous ne devez pas saisir des caractères speciaux");
            lab2.setTextFill(Color.web("#C70039  "));
      ajoutEnseignant.setDisable(true);}
  
        else{ lab2.setText("");
       }
    }

    @FXML
    private void controle_p(KeyEvent event) {
          String u=prenom.getText();
   
    if((prenom.getText().equals("")))
            {lab3.setText("Vous devez choisir un prenom !");
            lab3.setTextFill(Color.web("#C70039  "));
            ajoutEnseignant.setDisable(true);
            }
     else{ lab3.setText("");
     }
    
        String pattern = "^[a-zA-Z]*$";
    String uu = prenom.getText();
        if(!uu.matches(pattern))
      {lab3.setText("Vous ne devez pas saisir des caractères speciaux");
            lab3.setTextFill(Color.web("#C70039  "));
      ajoutEnseignant.setDisable(true);}
  
        else{ lab3.setText("");
         }
    }

    @FXML
    private void gostat(ActionEvent event) throws Exception {
          Controller.stat chart = new Controller.stat();
        Node source = (Node) event.getSource();
        chart.start(new Stage());
    
    }

    @FXML
    private void goArchive(ActionEvent event)  throws IOException {
           Parent p1 = FXMLLoader.load(getClass().getResource("/Controller/cours_archive.fxml"));
        Scene test1 = new Scene(p1);
        Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App1.setScene(test1);
        App1.show();
    }

    @FXML
    private void goEnseignant(ActionEvent event)  throws IOException {
           Parent p1 = FXMLLoader.load(getClass().getResource("/Controller/Enseignant.fxml"));
        Scene test1 = new Scene(p1);
        Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App1.setScene(test1);
        App1.show();
    }

    @FXML
    private void goClasse(ActionEvent event)  throws IOException {
           Parent p1 = FXMLLoader.load(getClass().getResource("/Controller/classe.fxml"));
        Scene test1 = new Scene(p1);
        Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App1.setScene(test1);
        App1.show();
    }

   @FXML
    private void goCours(ActionEvent event) throws IOException {
           Parent p1 = FXMLLoader.load(getClass().getResource("/Controller/Cours.fxml"));
        Scene test1 = new Scene(p1);
        Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App1.setScene(test1);
        App1.show();
    }

   


 
    

  



   
}
