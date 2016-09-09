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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "CITAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c"),
    @NamedQuery(name = "Citas.findByIdcita", query = "SELECT c FROM Citas c WHERE c.idcita = :idcita"),
    @NamedQuery(name = "Citas.findByAno", query = "SELECT c FROM Citas c WHERE c.ano = :ano"),
    @NamedQuery(name = "Citas.findByDia", query = "SELECT c FROM Citas c WHERE c.dia = :dia"),
    @NamedQuery(name = "Citas.findByMes", query = "SELECT c FROM Citas c WHERE c.mes = :mes"),
    @NamedQuery(name = "Citas.findBySummary", query = "SELECT c FROM Citas c WHERE c.summary = :summary"),
    @NamedQuery(name = "Citas.findByDescription", query = "SELECT c FROM Citas c WHERE c.description = :description"),
    @NamedQuery(name = "Citas.findByHoraFin", query = "SELECT c FROM Citas c WHERE c.horaFin = :horaFin"),
    @NamedQuery(name = "Citas.findByHoraInicio", query = "SELECT c FROM Citas c WHERE c.horaInicio = :horaInicio"),
    @NamedQuery(name = "Citas.findByMinutoFin", query = "SELECT c FROM Citas c WHERE c.minutoFin = :minutoFin"),
    @NamedQuery(name = "Citas.findByMinutoInicio", query = "SELECT c FROM Citas c WHERE c.minutoInicio = :minutoInicio"),
    @NamedQuery(name = "Citas.findByGrupo", query = "SELECT c FROM Citas c WHERE c.grupo = :grupo"),
    @NamedQuery(name = "Citas.findByTratamientoDeLaSesion", query = "SELECT c FROM Citas c WHERE c.tratamientoDeLaSesion = :tratamientoDeLaSesion"),
    @NamedQuery(name = "Citas.findByIdCalendar", query = "SELECT c FROM Citas c WHERE c.idCalendar = :idCalendar")})
public class Citas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCITA")
    private Integer idcita;
    @Column(name = "ANO")
    private Integer ano;
    @Column(name = "DIA")
    private Integer dia;
    @Column(name = "MES")
    private Integer mes;
    @Column(name = "SUMMARY")
    private String summary;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "HORA_FIN")
    private Integer horaFin;
    @Column(name = "HORA_INICIO")
    private Integer horaInicio;
    @Column(name = "MINUTO_FIN")
    private Integer minutoFin;
    @Column(name = "MINUTO_INICIO")
    private Integer minutoInicio;
    @Column(name = "GRUPO")
    private String grupo;
    @Column(name = "TRATAMIENTO_DE_LA_SESION")
    private String tratamientoDeLaSesion;
    @Column(name = "ID_CALENDAR")
    private String idCalendar;
    @JoinColumn(name = "IDPACIENTE", referencedColumnName = "IDPACIENTE")
    @ManyToOne(optional = false)
    private Paciente idpaciente;

    public Citas() {
    }

    public Citas(Integer idcita) {
        this.idcita = idcita;
    }

    public Integer getIdcita() {
        return idcita;
    }

    public void setIdcita(Integer idcita) {
        this.idcita = idcita;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Integer horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getMinutoFin() {
        return minutoFin;
    }

    public void setMinutoFin(Integer minutoFin) {
        this.minutoFin = minutoFin;
    }

    public Integer getMinutoInicio() {
        return minutoInicio;
    }

    public void setMinutoInicio(Integer minutoInicio) {
        this.minutoInicio = minutoInicio;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTratamientoDeLaSesion() {
        return tratamientoDeLaSesion;
    }

    public void setTratamientoDeLaSesion(String tratamientoDeLaSesion) {
        this.tratamientoDeLaSesion = tratamientoDeLaSesion;
    }

    public String getIdCalendar() {
        return idCalendar;
    }

    public void setIdCalendar(String idCalendar) {
        this.idCalendar = idCalendar;
    }

    public Paciente getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Paciente idpaciente) {
        this.idpaciente = idpaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcita != null ? idcita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.idcita == null && other.idcita != null) || (this.idcita != null && !this.idcita.equals(other.idcita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datos.Citas[ idcita=" + idcita + " ]";
    }
    
}
