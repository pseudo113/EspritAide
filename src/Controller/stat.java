/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import animation.FadeInUpTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Service.CoursDAO;


/**
 * FXML Controller class
 *
 * @author User
 */
public class stat extends Application implements Initializable {

    private BarChart<String, Integer> barchart;

    private AnchorPane commBar;

    private ComboBox<String> CBChoseStat;

    private AnchorPane UserComm;


    @FXML
    private PieChart pieChart;
    
    
    
   
    
    

CoursDAO service  = new CoursDAO();

public void StatCours() {

//        new FadeInUpTransition(commBar).play();
       // new FadeInDowntransition(UserComm).play();
        
       // pieChart.getData().clear();
        pieChart.setData(service.Statnb_cabine());
        pieChart.setAnimated(true);
        pieChart.setLegendVisible(true);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StatCours();
         pieChart.setLegendVisible(true);
          pieChart.setVisible(true);
        // TODO
        
        
      
    }
    @Override
     public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Controller/statistiqueFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         ;       
    }
  
      public static void main(String[] args) {
        launch(args);
    }
    
}  