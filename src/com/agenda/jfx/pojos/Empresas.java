package com.agenda.jfx.pojos;

import javax.persistence.Basic;

/**
 *
 * @author tayron
 */

public class Empresas {

    private Long id;
    private String razonsocial;
    private String ruc;
    private String telefono;
    private String email;
    private String direccion;
    private Departamentos departamentos;

    public Empresas() {
    }

    public Empresas(Long id) {
        this.id = id;
    }

    public Empresas(Long id, String razonsocial, String ruc) {
        this.id = id;
        this.razonsocial = razonsocial;
        this.ruc = ruc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }

}
