/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.runt.runistac.dto;

/**
 *
 * @author Usuario
 */
public class LocatarioDTO {
    
    private PersonaDTO persona;
    
    private String nombre;

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public String getNombre() {
        return nombre;
    }
    
    
    
}
