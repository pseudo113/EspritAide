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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    
void setStage(Stage primaryStage) {
        this.stage = primaryStage;  }

  ObservableList<String> listt =  FXCollections.observableArrayList("A","B","C","D");

    @FXML
    private void ajouter(ActionEvent event) {
        ClasseDAO cd = new ClasseDAO();
        
        int a = Integer.parseInt(numero.getText()) ;
        
        Classe cc = new Classe(1,a,niveau.getSelectionModel().getSelectedItem().toString());
        cd.insert(cc);
        
       
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

}
