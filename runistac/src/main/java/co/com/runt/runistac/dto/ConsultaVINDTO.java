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
public class ConsultaVINDTO {
    
    private String id;
    private String motor;
    private String serie;
    private String chasis;
    private String marca;
    private String linea;
    private String clase;
    private String fthChasis;
    private String fthCarroceria;
    private String vin;
    private String pesoBrutoVehicular;
    private String carroceria;
    private String numeroEjes;
    private String modelo;
    private String configuracion;
    private String estado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getFthCarroceria() {
        return fthCarroceria;
    }

    public void setFthCarroceria(String fthCarroceria) {
        this.fthCarroceria = fthCarroceria;
    }

    public String getFthChasis() {
        return fthChasis;
    }

    public void setFthChasis(String fthChasis) {
        this.fthChasis = fthChasis;
    }
            
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    public String getNumeroEjes() {
        return numeroEjes;
    }

    public void setNumeroEjes(String numeroEjes) {
        this.numeroEjes = numeroEjes;
    }

    public String getPesoBrutoVehicular() {
        return pesoBrutoVehicular;
    }

    public void setPesoBrutoVehicular(String pesoBrutoVehicular) {
        this.pesoBrutoVehicular = pesoBrutoVehicular;
    }

    public String getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
