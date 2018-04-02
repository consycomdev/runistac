package co.com.runt.runistac.dto;

import co.com.runt.runistac.persistencia.entity.TipoTituloValor;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public class PolizaCaucionDTO {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private TipoTituloValor tipo;

    private String tomador;

    private String asegurado;

    private String numero;

    private String numeroFactura;

    private Double valorVehiculo;

    private Double valorAsegurado;

    /**
     * @generated 0-1-false
     */
    private PostulacionDTO postulacion;
    
    /**
     * @generated 1-1-false
     */
    private String fechaExpedicion;

    /**
     * @generated 1-1-false
     */
    private String fechaInicioVigencia;

    /**
     * @generated 1-1-false
     */
    private String fechaFinVigencia;

    /**
     * @generated 0-1-false
     */
    private EntidadFinancieraDTO entidadFinanciera;
    
    private TipoGarantiaBancariaDTO tipoGarantia;
    
    private List<AnexoDTO> anexo;
    

    public TipoTituloValor getTipo() {
        return tipo;
    }

    public void setTipo(TipoTituloValor tipo) {
        this.tipo = tipo;
    }

    public String getTomador() {
        return tomador;
    }

    public void setTomador(String tomador) {
        this.tomador = tomador;
    }

    public String getAsegurado() {
        return asegurado;
    }

    public void setAsegurado(String asegurado) {
        this.asegurado = asegurado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Double getValorVehiculo() {
        return valorVehiculo;
    }

    public void setValorVehiculo(Double valorVehiculo) {
        this.valorVehiculo = valorVehiculo;
    }

    public Double getValorAsegurado() {
        return valorAsegurado;
    }

    public void setValorAsegurado(Double valorAsegurado) {
        this.valorAsegurado = valorAsegurado;
    }

    /**
     * @generated
     */
    public PostulacionDTO getPostulacion() {
        return this.postulacion;
    }

    /**
     * @generated
     */
    public void setPostulacion(PostulacionDTO postulacion) {
        this.postulacion = postulacion;
    }

    /**
     * @generated
     */
    public String getFechaExpedicion() {
        return this.fechaExpedicion;
    }

    /**
     * @generated
     */
    public void setFechaExpedicion(String fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    /**
     * @generated
     */
    public String getFechaInicioVigencia() {
        return this.fechaInicioVigencia;
    }

    /**
     * @generated
     */
    public void setFechaInicioVigencia(String fechaInicioVigencia) {
        this.fechaInicioVigencia = fechaInicioVigencia;
    }

    /**
     * @generated
     */
    public String getFechaFinVigencia() {
        return this.fechaFinVigencia;
    }

    /**
     * @generated
     */
    public void setFechaFinVigencia(String fechaFinVigencia) {
        this.fechaFinVigencia = fechaFinVigencia;
    }

    public TipoGarantiaBancariaDTO getTipoGarantia() {
        return tipoGarantia;
    }

    public void setTipoGarantia(TipoGarantiaBancariaDTO tipoGarantia) {
        this.tipoGarantia = tipoGarantia;
    }

    public EntidadFinancieraDTO getEntidadFinanciera() {
        return entidadFinanciera;
    }

    public void setEntidadFinanciera(EntidadFinancieraDTO entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    public List<AnexoDTO> getAnexo() {
        if(anexo==null){
            this.anexo=new ArrayList<>();
        }
        return anexo;
    }

    public void setAnexo(List<AnexoDTO> anexo) {
        this.anexo = anexo;
    }
    
    
    
}
