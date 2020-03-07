package com.agenda.jfx.views;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author tayron
 */
public class MainAppPane extends AnchorPane {
    
    @FXML
    private ImageView id_img_pers;

    @FXML
    private ImageView id_img_emps;

    @FXML
    private ImageView id_img_dep;

    @FXML
    void admin_deps(MouseEvent event) {
        AdminDepartamentosPane root = new AdminDepartamentosPane();
        Scene scene = new Scene(root);
        Stage stage =  (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void admin_pers(MouseEvent event) {
        AdminPersonasPane root = new AdminPersonasPane();
        Scene scene = new Scene(root);
        Stage stage =  (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void admin_emps(MouseEvent event) {
        AdminEmpresasPane root = new AdminEmpresasPane();
        Scene scene = new Scene(root);
        Stage stage =  (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    
    public MainAppPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainAppPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    @FXML
    private void initialize() {
    }
}
