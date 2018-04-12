/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Service.ClasseDAO;
import com.jfoenix.controls.JFXDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ConnextionSingleton;

/**
 *
 * @author LENOVO
 */
public class cours_archive implements Initializable{

    @FXML
    private Button cours_archive;
    @FXML
    private TableView<Entity.Cours> tv_enseignant;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> tc_nom;
    @FXML
    private TableColumn<?, ?> tc_module;
    @FXML
    private TableColumn<?, ?> tc_type;
    @FXML
    private TableColumn<?, ?> tc_date_archive;
    @FXML
    private TableColumn<?, ?> tc_emplacement;
    @FXML
    private JFXDialog dialog;
    @FXML
    private AnchorPane an;
    
 private ObservableList<Entity.Cours> data;
 
    private void LoadData() {
          ClasseDAO cd = new ClasseDAO();
       //   cd.getClasseById(rs.getInt("id_classe")).getNiveau();
        data.clear();
        try {
            Connection myconnection = ConnextionSingleton.getInstance().getConnection();

            ResultSet rs = myconnection.createStatement().executeQuery("SELECT * FROM archivefichier");
            while (rs.next()) {
                data.add(new Entity.Cours(rs.getInt("id"), 0, rs.getString("type"), rs.getString("nom"), rs.getString("image_name"),  rs.getString("module"),rs.getDate("Datecreation")));
                

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
    tc_emplacement.setCellValueFactory(new PropertyValueFactory<>("image_name"));
   // tc_niveau.setCellValueFactory(new PropertyValueFactory<>("id_classe"));
  tc_date_archive.setCellValueFactory(new PropertyValueFactory<>("Datecreation"));

     
          
        
    }
    
    @FXML
    private void showpdf(MouseEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = FXCollections.observableArrayList();
        LoadData();
        setCellTable();
        tv_enseignant.refresh();
    }

    private void goEnsegnant(ActionEvent event) throws IOException {
           Parent p1 = FXMLLoader.load(getClass().getResource("/Controller/Enseignant.fxml"));
        Scene test1 = new Scene(p1);
        Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App1.setScene(test1);
        App1.show();
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
    private void gostat(ActionEvent event) throws Exception {
          Controller.stat chart = new Controller.stat();
          
        Node source = (Node) event.getSource();
        chart.start(new Stage());
    
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
