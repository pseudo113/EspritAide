/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author LENOVO
 */
public class PDF extends Application {
    
   
    public void start(Stage primaryStage,String a) {
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        Scene scene = new Scene(new BorderPane(view), 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
Cours c = new Cours();
        System.out.println(c.pdfname);
        browser.loadURL(a);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }
    
}
