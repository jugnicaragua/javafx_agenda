package com.agenda.jfx.views;

/**
 *
 * @author tayron
 */
public class AdminEmpresasDataGridViewModel {
    private String id;
    private String razonsocial;
    private String ruc;
    private String ubicacion;
    private String telefono;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public AdminEmpresasDataGridViewModel(String id, String razonsocial, String ruc, String ubicacion, String telefono) {
        this.id = id;
        this.razonsocial = razonsocial;
        this.ruc = ruc;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
    }
    
}
