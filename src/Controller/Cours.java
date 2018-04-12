/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Classe;
import Service.ClasseDAO;
import Service.CoursDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserContext;
import com.teamdev.jxbrowser.chromium.BrowserContextParams;
import com.teamdev.jxbrowser.chromium.StorageType;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.ConnextionSingleton;
import utils.SendSMS2;

/**
 *
 * @author LENOVO
 */
public class Cours  implements Initializable {
    
    @FXML
    private JFXButton ajoutEnseignant;
    @FXML
    private TableView<Entity.Cours> tv_enseignant;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> tc_nom;
    @FXML
    private JFXTextField module;
    @FXML
    private JFXTextField fichier;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXTextField nomCours;
    @FXML
    private Button btn_ajout;
    @FXML
    private ComboBox<String> cb_niveau;
    @FXML
    private ComboBox<String> cb_type;
    
  private ObservableList<Entity.Cours> data;
    File file;
      Stage stage ;
   public Stage stage1 ;
    @FXML
    private TableColumn<?, ?> tc_type;
    @FXML
    private TableColumn<?, ?> tc_module;
    @FXML
    private TableColumn<?, ?> tc_file;
    @FXML
    private AnchorPane an;
    @FXML
    private JFXDialog dialog;
    @FXML
    private Button btn_archiver;
    @FXML
    private Button archive;
    @FXML
    private Button stat;
    @FXML
    private JFXDialog dialog1;
    @FXML
    private AnchorPane an1;
    @FXML
    private Label lab1;
    @FXML
    private Label lab2;
    @FXML
    private Label lab3;
   
    @FXML
    private void fichier(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                
                new FileChooser.ExtensionFilter("PDF Files","*.pdf")
        );
        

        file = fileChooser.showOpenDialog(stage);
        
        
        if (file!=null) {
            try {
                String img = file.toURI().toURL().toString();
                System.out.println(img);
                System.out.println("jawek béhi");
                fichier.setText(img);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Enseignant.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
    }
        ClasseDAO s = new ClasseDAO();
ObservableList<String> listt =  FXCollections.observableArrayList("Cours","TD");
List<Classe> niveau = new ArrayList<Classe> ();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
           data = FXCollections.observableArrayList();
        LoadData();
        setCellTable();
        
       niveau=  s.displaynavire();	
        niveau.forEach((s)->{
            cb_niveau.getItems().add(s.getNiveau());
            
             
            
        });
         cb_type.setItems(listt);
                }

    @FXML
    private void ajouter(ActionEvent event) {
        Entity.Cours  cc = new Entity.Cours(1, niveau.get(cb_niveau.getSelectionModel().getSelectedIndex()), cb_type.getSelectionModel().getSelectedItem(), nomCours.getText(), fichier.getText(), module.getText());
        CoursDAO cd = new CoursDAO();
        cd.insertCours(cc);
        SendSMS2 sm = new SendSMS2();
        sm.SendSms("Bonjour"+" | "+"un nouveau cours a été ajouté"+nomCours.getText(),"55734415");
        LoadData();
        setCellTable();
        tv_enseignant.refresh();
       nomCours.clear();
       module.clear();
       fichier.clear();
        
         TrayNotification tray = new TrayNotification("succes", "succes ",NotificationType.SUCCESS);
   javafx.scene.image.Image logo = new javafx.scene.image.Image("Ressources/logo.png");
   
        
    
        tray.setTitle("EspritAide.");
        tray.setImage(logo);
        tray.setMessage("Un cours ajouté ");
        tray.setRectangleFill(Paint.valueOf("#990000"));
        tray.setAnimationType(AnimationType.SLIDE);
        tray.showAndDismiss(Duration.seconds(4));
       
       
    }
      private void LoadData() {
          ClasseDAO cd = new ClasseDAO();
       //   cd.getClasseById(rs.getInt("id_classe")).getNiveau();
        data.clear();
        try {
            Connection myconnection = ConnextionSingleton.getInstance().getConnection();

            ResultSet rs = myconnection.createStatement().executeQuery("SELECT * FROM fichier");
            while (rs.next()) {
                data.add(new Entity.Cours(rs.getInt("id"), 0, rs.getString("type"), rs.getString("nom"), rs.getString("image_name"),  rs.getString("module")));
                

            }

        } catch (SQLException ex) {
            System.err.println("ERROR" + ex);

        }
        tv_enseignant.setItems(data);
    }
      private void setCellTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
     tc_module.setCellValueFactory(new PropertyValueFactory<>("module"));
     tc_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    tc_file.setCellValueFactory(new PropertyValueFactory<>("image_name"));
   // tc_niveau.setCellValueFactory(new PropertyValueFactory<>("id_classe"));



        //  comisss.setCellValueFactory(new PropertyValueFactory<>("mise_service"));

     
          
        
    }
    
    
    
    
    
    
    
    

    @FXML
    private void archiver(ActionEvent event) {
        Entity.Cours xp = new Entity.Cours();
        xp = tv_enseignant.getSelectionModel().getSelectedItem();
      //  Entity.Classe c = new Entity.Classe();
//        c.setId(xp.getId_class().getId());
    Entity.Cours  cc = new Entity.Cours(1,2,xp.getType(),xp.getNom(),xp.getImage_name(),xp.getModule());
    
    CoursDAO xpDAO = new CoursDAO();
        
        xpDAO.archiver(xp.getId(), cc);
         LoadData();
        setCellTable();
        tv_enseignant.refresh();
       nomCours.clear();
       module.clear();
       fichier.clear();
        
    }

    

    @FXML
    private void modifier(ActionEvent event) {
         Entity.Cours xp = new Entity.Cours();
        Entity.Cours xp1 = new Entity.Cours();
        CoursDAO xpDAO = new CoursDAO();
        
        xp1.setNom(nomCours.getText());
        xp1.setModule(module.getText());
        Entity.Classe c = new Entity.Classe();
        c.setId(cb_niveau.getSelectionModel().getSelectedIndex());
        xp1.setId_class(c);
        xp1.setType(cb_type.getSelectionModel().getSelectedItem());
       xp1.setImage_name(fichier.getText());
        xp = tv_enseignant.getSelectionModel().getSelectedItem();

        if (xp1 == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("please select a cours!");

            alert.showAndWait();
            LoadData();
            setCellTable();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Comfirm ur choice");
            alert.setHeaderText(null);
            alert.setContentText("Success");
            Optional<ButtonType> answer = alert.showAndWait();

            if (answer.get() == ButtonType.OK) {

                xpDAO.modifierCours(xp.getId(), xp1);
                System.out.println(xp.getId());
                //  dao.deleteDemande();
                LoadData();
                setCellTable();
            } else {
                LoadData();
                setCellTable();
            }
        }

       tv_enseignant.refresh();
       nomCours.clear();
       module.clear();
       fichier.clear();
    }


    @FXML
    private void combo(ActionEvent event) {
    }

    WebView a = new WebView();
   
    
    Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);
        public String pdfname;
    @FXML
    private void showpdf(MouseEvent event) throws Exception {
  pdfname = tv_enseignant.getSelectionModel().getSelectedItem().getImage_name();
  /*WebEngine webEngine = wv_pdf.getEngine();
  webEngine.load("http://localhost/a.pdf");
  */
        System.out.println(pdfname);
                         PDF pdf = new PDF();
        Node source = (Node) event.getSource();
        pdf.start(new Stage(),pdfname);  
        
            
 //     browser.loadURL("http://localhost/a.pdf");
 
       Entity.Cours ex = (Entity.Cours) tv_enseignant.getSelectionModel().getSelectedItem();
        int i = tv_enseignant.getSelectionModel().getSelectedItem().getId();
        System.out.println(i);
        nomCours.setText(ex.getNom());
        

        module.setText(ex.getModule());
        cb_type.setValue(ex.getType());
        fichier.setText(ex.getImage_name());
        
 

    }


    public String getPdfname() {
        return pdfname;
    }

    public void setPdfname(String pdfname) {
        this.pdfname = pdfname;
    }

    public Stage getStage1() {
        return stage1;
    }

    public void setStage1(Stage stage1) {
        this.stage1 = stage1;
    }

  

    @FXML
    private void gostat(ActionEvent event) throws Exception {
          Controller.stat chart = new Controller.stat();
        Node source = (Node) event.getSource();
        chart.start(new Stage());
    }

   
    
    @FXML
    private void contole_module(KeyEvent event) {
               String u=module.getText();
   
    if((module.getText().equals("")))
            {lab2.setText("Vous devez ecrire le Module du cours !");
            
            
            }
     else{ lab2.setText("");
              
    }
    
        String pattern = "^[a-zA-Z]*$";
    
        if(!u.matches(pattern))
      {lab2.setText("Vous devez ecrire seulement des lettres");
         
     }
  
        else{ lab2.setText("");
        }
    }

   

    @FXML
    private void controle_nom(KeyEvent event) {
        String uu=module.getText();
        String u=nomCours.getText();
   
    if((nomCours.getText().equals("")))
            {lab1.setText("Vous devez ecrire le Nom du cours !");
            
            
            }
     else{ lab1.setText("");
              
    }
    
        String pattern = "^[a-zA-Z]*$";
   
        if(!u.matches(pattern))
      {lab1.setText("Vous devez ecrire seulement des lettres");
         
      }
  
        else{ lab1.setText("");
        
        }


if((u.matches(pattern))&&
(uu.matches(pattern)))
{
ajoutEnseignant.setDisable(false);
    }
else{ajoutEnseignant.setDisable(true);}
    }

    @FXML
    private void goCoursArchiver(ActionEvent event)  throws IOException {
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
    private void goCours(ActionEvent event)  throws IOException {
           Parent p1 = FXMLLoader.load(getClass().getResource("/Controller/Cours.fxml"));
        Scene test1 = new Scene(p1);
        Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App1.setScene(test1);
        App1.show();
    }
    


}