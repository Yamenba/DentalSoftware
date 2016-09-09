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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "AJUSTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ajustes.findAll", query = "SELECT a FROM Ajustes a"),
    @NamedQuery(name = "Ajustes.findByRecordatorioActivado", query = "SELECT a FROM Ajustes a WHERE a.recordatorioActivado = :recordatorioActivado"),
    @NamedQuery(name = "Ajustes.findByRecordatorioTitulo", query = "SELECT a FROM Ajustes a WHERE a.recordatorioTitulo = :recordatorioTitulo"),
    @NamedQuery(name = "Ajustes.findByRecordatorioDescripcion", query = "SELECT a FROM Ajustes a WHERE a.recordatorioDescripcion = :recordatorioDescripcion"),
    @NamedQuery(name = "Ajustes.findByIdAjustes", query = "SELECT a FROM Ajustes a WHERE a.idAjustes = :idAjustes"),
    @NamedQuery(name = "Ajustes.findByCorreo", query = "SELECT a FROM Ajustes a WHERE a.correo = :correo")})
public class Ajustes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "RECORDATORIO_ACTIVADO")
    private Boolean recordatorioActivado;
    @Basic(optional = false)
    @Column(name = "RECORDATORIO_TITULO")
    private String recordatorioTitulo;
    @Basic(optional = false)
    @Column(name = "RECORDATORIO_DESCRIPCION")
    private String recordatorioDescripcion;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_AJUSTES")
    private Integer idAjustes;
    @Column(name = "CORREO")
    private String correo;

    public Ajustes() {
    }

    public Ajustes(Integer idAjustes) {
        this.idAjustes = idAjustes;
    }

    public Ajustes(Integer idAjustes, Boolean recordatorioActivado, String recordatorioTitulo, String recordatorioDescripcion) {
        this.idAjustes = idAjustes;
        this.recordatorioActivado = recordatorioActivado;
        this.recordatorioTitulo = recordatorioTitulo;
        this.recordatorioDescripcion = recordatorioDescripcion;
    }

    public Boolean getRecordatorioActivado() {
        return recordatorioActivado;
    }

    public void setRecordatorioActivado(Boolean recordatorioActivado) {
        this.recordatorioActivado = recordatorioActivado;
    }

    public String getRecordatorioTitulo() {
        return recordatorioTitulo;
    }

    public void setRecordatorioTitulo(String recordatorioTitulo) {
        this.recordatorioTitulo = recordatorioTitulo;
    }

    public String getRecordatorioDescripcion() {
        return recordatorioDescripcion;
    }

    public void setRecordatorioDescripcion(String recordatorioDescripcion) {
        this.recordatorioDescripcion = recordatorioDescripcion;
    }

    public Integer getIdAjustes() {
        return idAjustes;
    }

    public void setIdAjustes(Integer idAjustes) {
        this.idAjustes = idAjustes;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAjustes != null ? idAjustes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ajustes)) {
            return false;
        }
        Ajustes other = (Ajustes) object;
        if ((this.idAjustes == null && other.idAjustes != null) || (this.idAjustes != null && !this.idAjustes.equals(other.idAjustes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Ajustes[ idAjustes=" + idAjustes + " ]";
    }
    
}
