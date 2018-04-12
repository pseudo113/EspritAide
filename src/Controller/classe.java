/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Classe;
import Service.ClasseDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.ConnextionSingleton;

/**
 *
 * @author LENOVO
 */
public class classe implements Initializable{
 Stage stage ;
    @FXML
    private JFXComboBox<String> niveau;
    @FXML
    private JFXTextField numero;
    @FXML
    private JFXButton ajoutClasse;
    @FXML
    private TableColumn<?, ?> tc_niveau;
    @FXML
    private TableColumn<?, ?> tc_num;
    @FXML
    private TableView<Classe> tv_classe;
    @FXML
    private JFXButton supprimer;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private Label lab1;
    
void setStage(Stage primaryStage) {
        this.stage = primaryStage;  }

  ObservableList<String> listt =  FXCollections.observableArrayList("3A","3B","4GL","4SIM","4INFOA","4TWIN","4INFINI","4DS","5INFO");

    @FXML
    private void ajouter(ActionEvent event) {
        
        ClasseDAO cd = new ClasseDAO();
        
        int a = Integer.parseInt(numero.getText()) ;
        
        Classe cc = new Classe(1,a,niveau.getSelectionModel().getSelectedItem().toString());
        cd.insert(cc);
         tv_classe.refresh();
       numero.clear();
      
       
         
         TrayNotification tray = new TrayNotification("succes", "succes ",NotificationType.SUCCESS);
   javafx.scene.image.Image logo = new javafx.scene.image.Image("Ressources/logo.png");
   
        
    
        tray.setTitle("EspritAide.");
        tray.setImage(logo);
        tray.setMessage("Une classe a été ajoutée ");
        tray.setRectangleFill(Paint.valueOf("#990000"));
        tray.setAnimationType(AnimationType.SLIDE);
        tray.showAndDismiss(Duration.seconds(4));
        
       
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableUpdateC();
      niveau.setItems(listt);


    }
    public void tableUpdateC() {
        ClasseDAO cs = new ClasseDAO();
        ObservableList<Classe> list = cs.displayc();
         id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc_niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
        //cl_shipname.setCellValueFactory(new PropertyValueFactory<>("id_navire"));
        //  comisss.setCellValueFactory(new PropertyValueFactory<>("mise_service"));
          //cl_cabine.setCellValueFactory(new PropertyValueFactory<>("nb_cabine"));
         
           tc_num.setCellValueFactory(value -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            Classe nav = (Classe) value.getValue();
            property.set(nav.getNumero());
            return property;
        });
        
      
      
        
     
        
       tv_classe.setItems(list);
        tv_classe.setTableMenuButtonVisible(true);
        tv_classe.refresh();
        
       

    }

    @FXML
    private void supprimer(ActionEvent event) {
        Classe xp = new Classe();
        ClasseDAO xpdao = new ClasseDAO();
        int i = tv_classe.getSelectionModel().getSelectedItem().getId();
        xpdao.supprimerClasse(i);
        //tab_xp.getItems().clear();
     

        tableUpdateC();
        
    }

  


    @FXML
    private void controle_numero(KeyEvent event) {
         String u=numero.getText();
   
    if((numero.getText().equals("")))
            {lab1.setText("Vous devez ecrire un numero de classe!");
            
            ajoutClasse.setDisable(true);
            }
     else{ lab1.setText("");
              
    }
    
        String pattern = "^[0-9]$";
    String uu = numero.getText();
        if(!uu.matches(pattern))
      {lab1.setText("Vous devez ecrire un Chiffre ");
      
            
      ajoutClasse.setDisable(true);}
  
        else{ lab1.setText("");
        ajoutClasse.setDisable(false);
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
    private void goCours(ActionEvent event)  throws IOException {
           Parent p1 = FXMLLoader.load(getClass().getResource("/Controller/Cours.fxml"));
        Scene test1 = new Scene(p1);
        Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App1.setScene(test1);
        App1.show();
    }

}
