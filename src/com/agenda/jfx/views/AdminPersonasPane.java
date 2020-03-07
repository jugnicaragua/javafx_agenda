package com.agenda.jfx.views;

import com.agenda.jfx.daoimpl.DepartamentosDAOImpl;
import com.agenda.jfx.daoimpl.PersonasDAOImpl;
import com.agenda.jfx.pojos.Departamentos;
import com.agenda.jfx.pojos.Personas;
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
import javafx.scene.control.ComboBox;
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
public class AdminPersonasPane extends AnchorPane {
    private ObservableList<AdminPersonasDataGridViewModel> oblist = FXCollections.observableArrayList();
    private ObservableList<AdminDepartamentosComboBoxModel> cbxlist = FXCollections.observableArrayList();
    
    @FXML
    private Button idVolver;

    @FXML
    private TextField txt_telefono;

    @FXML
    private TableColumn<AdminPersonasDataGridViewModel, String> col_id;

    @FXML
    private Label lbl_id;

    @FXML
    private TextField txt_email;

    @FXML
    private Button btn_cancelar;

    @FXML
    private TableColumn<AdminPersonasDataGridViewModel, String> col_apellidos;

    @FXML
    private Button btn_guardar;

    @FXML
    private TextField txt_cedula;

    @FXML
    private TableColumn<AdminPersonasDataGridViewModel, String> col_nombres;

    @FXML
    private TableColumn<AdminPersonasDataGridViewModel, String> col_cedula;

    @FXML
    private TextField txt_apellidos;

    @FXML
    private TableView<AdminPersonasDataGridViewModel> gv_personas;

    @FXML
    private TableColumn<AdminPersonasDataGridViewModel, String> col_departamento;

    @FXML
    private Button btn_borrar;

    @FXML
    private Label lbl_mensaje;

    @FXML
    private TextField txt_nombres;

    @FXML
    private ComboBox<AdminDepartamentosComboBoxModel> cbx_departamentos;

    @FXML
    private TextField txt_direccion;

    @FXML
    void guardar(ActionEvent event) {
        if(lbl_id.getText().isEmpty()){
            //insertar
            if(validarCampos()){
                try{
                     DepartamentosDAOImpl depdaoimpl = new DepartamentosDAOImpl();
                     PersonasDAOImpl perdaoimpl = new PersonasDAOImpl();
                     Personas persona = new Personas();
                     persona.setNombres(txt_nombres.getText());
                     persona.setApellidos(txt_apellidos.getText());
                     persona.setCedula(txt_cedula.getText());
                     persona.setTelefono(txt_telefono.getText());
                     persona.setEmail(txt_email.getText());
                     persona.setDireccion(txt_direccion.getText());
                     persona.setDepartamentos(depdaoimpl.getOne(Long.valueOf(cbx_departamentos.getSelectionModel().getSelectedItem().getId())));
                     perdaoimpl.add(persona);
                }catch(Exception ex){
                    lbl_mensaje.setText(ex.getMessage());
                }finally{
                    limpiarCampos();
                    oblist.clear();
                    cargarDatosGridView();
                    lbl_mensaje.setText("Registro creado satisfactoriamente!");
                }
            }else{
                lbl_mensaje.setText("Los campos nombres, apellidos, cédula y ubicación son requeridos!");
            }
        }else{
            //actualizar
            if(validarCampos()){
                try{
                     DepartamentosDAOImpl depdaoimpl = new DepartamentosDAOImpl();
                     PersonasDAOImpl perdaoimpl = new PersonasDAOImpl();
                     Personas persona = perdaoimpl.getOne(Long.valueOf(lbl_id.getText()));
                     persona.setNombres(txt_nombres.getText());
                     persona.setApellidos(txt_apellidos.getText());
                     persona.setCedula(txt_cedula.getText());
                     persona.setTelefono(txt_telefono.getText());
                     persona.setEmail(txt_email.getText());
                     persona.setDireccion(txt_direccion.getText());
                     persona.setDepartamentos(depdaoimpl.getOne(Long.valueOf(cbx_departamentos.getSelectionModel().getSelectedItem().getId())));
                     perdaoimpl.add(persona);
                }catch(Exception ex){
                    lbl_mensaje.setText(ex.getMessage());
                }finally{
                    limpiarCampos();
                    oblist.clear();
                    cargarDatosGridView();
                    lbl_mensaje.setText("Registro actualizado satisfactoriamente!");
                }
            }else{
                lbl_mensaje.setText("Los campos nombres, apellidos, cédula y ubicación son requeridos!");
            }
        }
    }

    @FXML
    void borrar(ActionEvent event) {
        if(lbl_id.getText().isEmpty()){
            lbl_mensaje.setText("Debe seleccionar un registro para borrarlo!");
        }else{
            PersonasDAOImpl perdaoimpl = new PersonasDAOImpl();
            try{
                 perdaoimpl.delete(Long.valueOf(lbl_id.getText()));
                 limpiarCampos();
                 oblist.clear();
                 cargarDatosGridView();
                 lbl_mensaje.setText("Registro borrado satisfactoriamente!");
            }catch(Exception ex){
                lbl_mensaje.setText(ex.getMessage());
            }
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
    
    public void cargarDatosDepartamentosComboBox(){
        //Cargar departamentos en el combobox del formulario
        DepartamentosDAOImpl depdaoimpl = new DepartamentosDAOImpl();
        try{
             List<Departamentos> deplist = depdaoimpl.getAll();
             cbxlist.add(new AdminDepartamentosComboBoxModel("0",""));
             for(Departamentos item:deplist){
                 cbxlist.add(new AdminDepartamentosComboBoxModel(String.valueOf(item.getId()),item.getDescripcion()));
             }
             cbx_departamentos.setItems(cbxlist);
        }catch(Exception ex){
            lbl_mensaje.setText(ex.getMessage());
        }
    }
    
    public void cargarDatosGridView(){
        //Cargar departamentos dao
        PersonasDAOImpl perdaoimpl = new PersonasDAOImpl();
        try{
            List<Personas> perlist =  perdaoimpl.getAll();
            for(Personas per:perlist){
                oblist.add(new AdminPersonasDataGridViewModel(String.valueOf(per.getId()),per.getNombres(),per.getApellidos(),per.getCedula(),per.getDepartamentos().getDescripcion()));
            }
        }catch(Exception e){
            lbl_mensaje.setText(e.getMessage());
        }
        //Enlazar fxml gridview columns con la clase modelo para departamentos
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_nombres.setCellValueFactory(new PropertyValueFactory("nombres"));
        col_apellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        col_cedula.setCellValueFactory(new PropertyValueFactory("cedula"));
        col_departamento.setCellValueFactory(new PropertyValueFactory("departamento"));
        gv_personas.setItems(oblist);
        
        //Crear evento onclick en cada fila del fxml gridview y cargue datos en el formulario
        gv_personas.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {                                
                lbl_id.setText(gv_personas.getSelectionModel().getSelectedItem().getId());
                //@todo load persona record//
                try{
                    PersonasDAOImpl perdaoimpl_2 = new PersonasDAOImpl();
                    Personas p1 = perdaoimpl_2.getOne(Long.valueOf(lbl_id.getText()));
                    if(p1 != null){
                        txt_nombres.setText(p1.getNombres());
                        txt_apellidos.setText(p1.getApellidos());
                        txt_cedula.setText(p1.getCedula());
                        txt_telefono.setText(p1.getTelefono());
                        txt_email.setText(p1.getEmail());
                        txt_direccion.setText(p1.getDireccion());
                        cbx_departamentos.getSelectionModel().select(new AdminDepartamentosComboBoxModel(String.valueOf(p1.getDepartamentos().getId()),p1.getDepartamentos().getDescripcion()));
                        lbl_mensaje.setText("Registro cargado correctamente!");
                    }                    
                }catch(Exception ex){
                    lbl_mensaje.setText(ex.getMessage());
                    lbl_mensaje.setText("Registro no fue cargado correctamente!");
                }
                //------------------------//                
            }            
        });
    }
    
    public void limpiarCampos(){
        lbl_id.setText("");
        lbl_mensaje.setText("");
        txt_nombres.setText("");
        txt_apellidos.setText("");
        txt_cedula.setText("");
        cbx_departamentos.getSelectionModel().clearSelection();
        cbx_departamentos.getSelectionModel().select(0);
        txt_telefono.setText("");
        txt_email.setText("");
        txt_direccion.setText("");
    }
    
    public Boolean validarCampos(){
        Boolean listo = true;
        if(txt_nombres.getText().isEmpty()){
            listo = false;
        }
        if(txt_apellidos.getText().isEmpty()){
            listo = false;
        }
        if(txt_cedula.getText().isEmpty()){
            listo = false;
        }
        if(cbx_departamentos.getSelectionModel().selectedItemProperty().getValue() == null){
            listo = false;
        }
        return listo;
    }
    
    public AdminPersonasPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminPersonasPane.fxml"));
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
        //al inicio del formulario ejecutar actividades
        cargarDatosGridView();
        cargarDatosDepartamentosComboBox();
        //-------------------------------------------//
    }
}
