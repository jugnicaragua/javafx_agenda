package com.agenda.jfx.pojos;

import java.util.List;

/**
 *
 * @author tayron
 */

public class Departamentos {

    private Long id;
    private String descripcion;
    private List<Empresas> empresasList;
    private List<Personas> personasList;

    public Departamentos() {
    }

    public Departamentos(Long id) {
        this.id = id;
    }

    public Departamentos(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Empresas> getEmpresasList() {
        return empresasList;
    }

    public void setEmpresasList(List<Empresas> empresasList) {
        this.empresasList = empresasList;
    }

    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }
    
}
