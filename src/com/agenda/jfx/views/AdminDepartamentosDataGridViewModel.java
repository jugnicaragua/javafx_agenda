package com.agenda.jfx.views;

/**
 *
 * @author tayron
 */
public class AdminDepartamentosDataGridViewModel {
    private String id;
    private String descripcion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public AdminDepartamentosDataGridViewModel(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    
}
