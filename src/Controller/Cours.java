/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Classe;
import Service.ClasseDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

/**
 *
 * @author LENOVO
 */
public class Cours implements Initializable{
    
    @FXML
    private JFXButton ajoutEnseignant;
    @FXML
    private TableView<?> tv_enseignant;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> tc_username;
    @FXML
    private TableColumn<?, ?> tc_nom;
    @FXML
    private TableColumn<?, ?> tc_prenom;
    @FXML
    private TableColumn<?, ?> tc_mail;
    @FXML
    private JFXButton supprimer;
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
    private WebView id_pdf;
    
     
    
    @FXML
    private void fichier(ActionEvent event) {
    }
        ClasseDAO s = new ClasseDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
          List<Classe> niveau = new ArrayList<Classe> ();
       niveau=  s.displaynavire();	
        niveau.forEach((s)->{
            cb_niveau.getItems().add(s.getNiveau());
            
             
            
        });
                }

    @FXML
    private void ajouter(ActionEvent event) {
    }

    @FXML
    private void archiver(ActionEvent event) {
    }

    

    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void ajout(ActionEvent event) {
    }

    @FXML
    private void combo(ActionEvent event) {
    }
}
