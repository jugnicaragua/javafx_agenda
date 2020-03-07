package com.agenda.jfx.views;

import com.agenda.jfx.daoimpl.DepartamentosDAOImpl;
import com.agenda.jfx.daoimpl.EmpresasDAOImpl;
import com.agenda.jfx.pojos.Departamentos;
import com.agenda.jfx.pojos.Empresas;
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
public class AdminEmpresasPane extends AnchorPane {
    private ObservableList<AdminEmpresasDataGridViewModel> oblist = FXCollections.observableArrayList();
    private ObservableList<AdminDepartamentosComboBoxModel> cbxlist = FXCollections.observableArrayList();
    
    @FXML
    private Button idVolver;

    @FXML
    private TextField txt_telefono;

    @FXML
    private TableView<AdminEmpresasDataGridViewModel> gv_empresas;
        
    @FXML
    private TableColumn<AdminEmpresasDataGridViewModel, String> col_id;

    @FXML
    private TableColumn<AdminEmpresasDataGridViewModel, String> col_razonsocial;

    @FXML
    private TableColumn<AdminEmpresasDataGridViewModel, String> col_ruc;

    @FXML
    private TableColumn<AdminEmpresasDataGridViewModel, String> col_ubicacion;

    @FXML
    private TableColumn<AdminEmpresasDataGridViewModel, String> col_telefono;

    @FXML
    private TextField txt_razonsocial;

    @FXML
    private Label lbl_id;

    @FXML
    private TextField txt_email;

    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_guardar;

    @FXML
    private TextField txt_ruc;

    @FXML
    private Button btn_borrar;

    @FXML
    private Label lbl_mensaje;

    @FXML
    private ComboBox<AdminDepartamentosComboBoxModel> cbx_departamentos;

    @FXML
    private TextField txt_direccion;

    @FXML
    void cancelar(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void borrar(ActionEvent event) {
        if(lbl_id.getText().isEmpty()){
            lbl_mensaje.setText("Debe seleccionar un registro para borrarlo!");
        }else{
            EmpresasDAOImpl empdaoimpl = new EmpresasDAOImpl();
            try{
                 empdaoimpl.delete(Long.valueOf(lbl_id.getText()));
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
    void guardar(ActionEvent event) {
        if(lbl_id.getText().isEmpty()){
            //insertar
            if(validarCampos()){
                try{
                     DepartamentosDAOImpl depdaoimpl = new DepartamentosDAOImpl();
                     EmpresasDAOImpl empdaoimpl = new EmpresasDAOImpl();
                     Empresas emp = new Empresas();
                     emp.setRazonsocial(txt_razonsocial.getText());
                     emp.setRuc(txt_ruc.getText());
                     emp.setDepartamentos(depdaoimpl.getOne(Long.valueOf(cbx_departamentos.getSelectionModel().getSelectedItem().getId())));
                     emp.setTelefono(txt_telefono.getText());
                     emp.setEmail(txt_email.getText());
                     emp.setDireccion(txt_direccion.getText());
                     empdaoimpl.add(emp);
                }catch(Exception ex){
                    lbl_mensaje.setText(ex.getMessage());
                }finally{
                    limpiarCampos();
                    oblist.clear();
                    cargarDatosGridView();
                    lbl_mensaje.setText("Registro creado satisfactoriamente!");
                }
            }else{
                lbl_mensaje.setText("Los campos razon social, ruc, ubicación y teléfono son requeridos!");
            }
        }else{
            //actualizar
            if(validarCampos()){
                try{
                     DepartamentosDAOImpl depdaoimpl = new DepartamentosDAOImpl();
                     EmpresasDAOImpl empdaoimpl = new EmpresasDAOImpl();
                     Empresas emp = empdaoimpl.getOne(Long.valueOf(lbl_id.getText()));
                     emp.setRazonsocial(txt_razonsocial.getText());
                     emp.setRuc(txt_ruc.getText());
                     emp.setDepartamentos(depdaoimpl.getOne(Long.valueOf(cbx_departamentos.getSelectionModel().getSelectedItem().getId())));
                     emp.setTelefono(txt_telefono.getText());
                     emp.setEmail(txt_email.getText());
                     emp.setDireccion(txt_direccion.getText());
                     empdaoimpl.update(emp);
                }catch(Exception ex){
                    lbl_mensaje.setText(ex.getMessage());
                }finally{
                    limpiarCampos();
                    oblist.clear();
                    cargarDatosGridView();
                    lbl_mensaje.setText("Registro actualizado satisfactoriamente!");
                }
            }else{
                lbl_mensaje.setText("Los campos razon social, ruc, ubicación y teléfono son requeridos!");
            }
        }
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
        EmpresasDAOImpl empdaoimpl = new EmpresasDAOImpl();
        try{
            List<Empresas> emplist =  empdaoimpl.getAll();
            for(Empresas emp:emplist){
                oblist.add(new AdminEmpresasDataGridViewModel(String.valueOf(emp.getId()),emp.getRazonsocial(),emp.getRuc(),emp.getDepartamentos().getDescripcion(),emp.getTelefono()));
            }
        }catch(Exception e){
            lbl_mensaje.setText(e.getMessage());
        }
        //Enlazar fxml gridview columns con la clase modelo para departamentos
        col_id.setCellValueFactory(new PropertyValueFactory("id"));
        col_razonsocial.setCellValueFactory(new PropertyValueFactory("razonsocial"));
        col_ruc.setCellValueFactory(new PropertyValueFactory("ruc"));        
        col_ubicacion.setCellValueFactory(new PropertyValueFactory("ubicacion"));
        col_telefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        gv_empresas.setItems(oblist);
        
        //Crear evento onclick en cada fila del fxml gridview y cargue datos en el formulario
        gv_empresas.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {                                
                lbl_id.setText(gv_empresas.getSelectionModel().getSelectedItem().getId());
                //@todo load persona record//
                try{
                    EmpresasDAOImpl empdaoimpl_2 = new EmpresasDAOImpl();
                    Empresas emp = empdaoimpl_2.getOne(Long.valueOf(lbl_id.getText()));
                    if(emp != null){
                        txt_razonsocial.setText(emp.getRazonsocial());                        
                        txt_ruc.setText(emp.getRuc());
                        txt_telefono.setText(emp.getTelefono());
                        txt_email.setText(emp.getEmail());
                        txt_direccion.setText(emp.getDireccion());
                        cbx_departamentos.getSelectionModel().select(new AdminDepartamentosComboBoxModel(String.valueOf(emp.getDepartamentos().getId()),emp.getDepartamentos().getDescripcion()));
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
        txt_razonsocial.setText("");        
        txt_ruc.setText("");
        cbx_departamentos.getSelectionModel().clearSelection();
        cbx_departamentos.getSelectionModel().select(0);
        txt_telefono.setText("");
        txt_email.setText("");
        txt_direccion.setText("");
    }
    
    public Boolean validarCampos(){
        Boolean listo = true;
        if(txt_razonsocial.getText().isEmpty()){
            listo = false;
        }
        if(txt_ruc.getText().isEmpty()){
            listo = false;
        }
        if(txt_telefono.getText().isEmpty()){
            listo = false;
        }
        if(cbx_departamentos.getSelectionModel().selectedItemProperty().getValue() == null){
            listo = false;
        }
        return listo;
    }

    
    public AdminEmpresasPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminEmpresasPane.fxml"));
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
