package com.agenda.jfx.views;

import com.agenda.jfx.daoimpl.DepartamentosDAOImpl;
import com.agenda.jfx.pojos.Departamentos;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author tayron
 */
public class AdminDepartamentosPane extends AnchorPane {
    private ObservableList<AdminDepartamentosDataGridViewModel> oblist = FXCollections.observableArrayList();
    
    @FXML
    private Button idVovler;
    
    @FXML
    private Button btn_borrar;

    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_guardar;

    @FXML
    private TextField txt_descripcion;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_descripcion;

    @FXML
    private Label lbl_mensaje;
    
    
    @FXML
    private TableView<AdminDepartamentosDataGridViewModel> gv_deps;

    @FXML
    private TableColumn<AdminDepartamentosDataGridViewModel, String> col_id;
    
    @FXML
    private TableColumn<AdminDepartamentosDataGridViewModel, String> col_descripcion;

    @FXML
    void guardar(ActionEvent event) {
        if(validarCampos()){
            DepartamentosDAOImpl depsdaoimpl = new DepartamentosDAOImpl();
            if(lbl_id.getText().isEmpty()){
                //Insertar
                try{
                    Departamentos dp = new Departamentos();
                    dp.setDescripcion(txt_descripcion.getText());
                    depsdaoimpl.add(dp);
                }catch(Exception e){
                    lbl_mensaje.setText(e.getMessage());
                }
            }else{
                try {
                    //Actualizar
                    Departamentos dp = depsdaoimpl.getOne(Long.valueOf(lbl_id.getText()));
                    dp.setDescripcion(txt_descripcion.getText());
                    depsdaoimpl.update(dp);
                } catch (Exception e) {
                    lbl_mensaje.setText(e.getMessage());
                }
            }
            limpiarCampos();
            oblist.clear();
            cargarDatosGridView();
            lbl_mensaje.setText("Registro actualizado correctamente!");
        }else{
            lbl_mensaje.setText("Todos los campos son requeridos!");
        }
    }

    @FXML
    void borrar(ActionEvent event) {
         if(!lbl_id.getText().isEmpty()){
             try {
                 DepartamentosDAOImpl depsdaoimpl = new DepartamentosDAOImpl();
                 depsdaoimpl.delete(Long.valueOf(lbl_id.getText()));
                 limpiarCampos();
                 oblist.clear();
                 cargarDatosGridView();
                 lbl_mensaje.setText("Registro borraado correctamente!");
             } catch (Exception e) {
                 lbl_mensaje.setText(e.getMessage());
             }
         }else{
             lbl_mensaje.setText("Debes seleccionar un registro de la cuadrícula para borrar.");
         }
    }

    @FXML
    void cancelar(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void volver(ActionEvent event) {
        MainAppPane root = new MainAppPane();
        Scene scene = new Scene(root);
        Stage stage =  (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void cargarDatosGridView(){
        //Cargar departamentos dao
        DepartamentosDAOImpl depdaoimpl = new DepartamentosDAOImpl();
        try{
            List<Departamentos> deplist =  depdaoimpl.getAll();
            for(Departamentos dep:deplist){
                oblist.add(new AdminDepartamentosDataGridViewModel(String.valueOf(dep.getId()),dep.getDescripcion()));
            }
        }catch(Exception e){
            lbl_mensaje.setText(e.getMessage());
        }
        //Enlazar fxml gridview columns con la clase modelo para departamentos
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_descripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        gv_deps.setItems(oblist);
        
        //Crear evento onclick en cada fila del fxml gridview y cargue datos en el formulario
        gv_deps.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {                                
                lbl_id.setText(gv_deps.getSelectionModel().getSelectedItem().getId());
                txt_descripcion.setText(gv_deps.getSelectionModel().getSelectedItem().getDescripcion());
                lbl_mensaje.setText("Registro cargado correctamente!");
            }            
        });
    }
    
    public void limpiarCampos(){
        lbl_id.setText("");
        txt_descripcion.setText("");
    }
    
    public Boolean validarCampos(){
        Boolean listo = true;
        if(txt_descripcion.getText().isEmpty()){
            listo = false;
        }
        return listo;
    }
    
    public AdminDepartamentosPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminDepartamentosPane.fxml"));
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
        //Al iniciar el formulario hay que cargar los departamentos en BD.
        cargarDatosGridView();        
    }
}
