/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "COSTO_TRATAMIENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CostoTratamiento.findAll", query = "SELECT c FROM CostoTratamiento c"),
    @NamedQuery(name = "CostoTratamiento.findByPagoInicial", query = "SELECT c FROM CostoTratamiento c WHERE c.pagoInicial = :pagoInicial"),
    @NamedQuery(name = "CostoTratamiento.findByNumeroMensualidades", query = "SELECT c FROM CostoTratamiento c WHERE c.numeroMensualidades = :numeroMensualidades"),
    @NamedQuery(name = "CostoTratamiento.findByMontoMensualidad", query = "SELECT c FROM CostoTratamiento c WHERE c.montoMensualidad = :montoMensualidad"),
    @NamedQuery(name = "CostoTratamiento.findByIdPaciente", query = "SELECT c FROM CostoTratamiento c WHERE c.idPaciente = :idPaciente")})
public class CostoTratamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "PAGO_INICIAL")
    private double pagoInicial;
    @Basic(optional = false)
    @Column(name = "NUMERO_MENSUALIDADES")
    private double numeroMensualidades;
    @Basic(optional = false)
    @Column(name = "MONTO_MENSUALIDAD")
    private double montoMensualidad;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PACIENTE")
    private Integer idPaciente;
    @JoinColumn(name = "ID_PACIENTE", referencedColumnName = "IDPACIENTE", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Paciente paciente;

    public CostoTratamiento() {
    }

    public CostoTratamiento(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public CostoTratamiento(Integer idPaciente, double pagoInicial, double numeroMensualidades, double montoMensualidad) {
        this.idPaciente = idPaciente;
        this.pagoInicial = pagoInicial;
        this.numeroMensualidades = numeroMensualidades;
        this.montoMensualidad = montoMensualidad;
    }

    public double getPagoInicial() {
        return pagoInicial;
    }

    public void setPagoInicial(double pagoInicial) {
        this.pagoInicial = pagoInicial;
    }

    public double getNumeroMensualidades() {
        return numeroMensualidades;
    }

    public void setNumeroMensualidades(double numeroMensualidades) {
        this.numeroMensualidades = numeroMensualidades;
    }

    public double getMontoMensualidad() {
        return montoMensualidad;
    }

    public void setMontoMensualidad(double montoMensualidad) {
        this.montoMensualidad = montoMensualidad;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaciente != null ? idPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CostoTratamiento)) {
            return false;
        }
        CostoTratamiento other = (CostoTratamiento) object;
        if ((this.idPaciente == null && other.idPaciente != null) || (this.idPaciente != null && !this.idPaciente.equals(other.idPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.CostoTratamiento[ idPaciente=" + idPaciente + " ]";
    }
    
}
