package com.agenda.jfx;

import com.agenda.jfx.views.MainAppPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tayron
 */
public class MainApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        MainAppPane root = new MainAppPane();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
