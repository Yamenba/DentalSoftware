/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "PACIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByIdpaciente", query = "SELECT p FROM Paciente p WHERE p.idpaciente = :idpaciente"),
    @NamedQuery(name = "Paciente.findByNombre", query = "SELECT p FROM Paciente p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Paciente.findByDireccion", query = "SELECT p FROM Paciente p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Paciente.findByCiudad", query = "SELECT p FROM Paciente p WHERE p.ciudad = :ciudad"),
    @NamedQuery(name = "Paciente.findByFechanac", query = "SELECT p FROM Paciente p WHERE p.fechanac = :fechanac"),
    @NamedQuery(name = "Paciente.findByOcupacion", query = "SELECT p FROM Paciente p WHERE p.ocupacion = :ocupacion"),
    @NamedQuery(name = "Paciente.findByTutor", query = "SELECT p FROM Paciente p WHERE p.tutor = :tutor"),
    @NamedQuery(name = "Paciente.findByMail", query = "SELECT p FROM Paciente p WHERE p.mail = :mail"),
    @NamedQuery(name = "Paciente.findByTelefono", query = "SELECT p FROM Paciente p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Paciente.findByRutaImagen", query = "SELECT p FROM Paciente p WHERE p.rutaImagen = :rutaImagen")})
public class Paciente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDPACIENTE")
    private Integer idpaciente;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "DIRECCION")
    private String direccion;
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "FECHANAC")
    @Temporal(TemporalType.DATE)
    private Date fechanac;
    @Column(name = "OCUPACION")
    private String ocupacion;
    @Column(name = "TUTOR")
    private String tutor;
    @Column(name = "MAIL")
    private String mail;
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "RUTA_IMAGEN")
    private String rutaImagen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaciente")
    private Collection<Citas> citasCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "paciente")
    private CostoTratamiento costoTratamiento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpaciente")
    private Collection<DatosMedicosPaciente> datosMedicosPacienteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPaciente")
    private Collection<Pago> pagoCollection;
    @OneToMany(mappedBy = "idPaciente")
    private Collection<Imagen> imagenCollection;

    public Paciente() {
    }

    public Paciente(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }

    public Integer getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @XmlTransient
    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
    }

    public CostoTratamiento getCostoTratamiento() {
        return costoTratamiento;
    }

    public void setCostoTratamiento(CostoTratamiento costoTratamiento) {
        this.costoTratamiento = costoTratamiento;
    }

    @XmlTransient
    public Collection<DatosMedicosPaciente> getDatosMedicosPacienteCollection() {
        return datosMedicosPacienteCollection;
    }

    public void setDatosMedicosPacienteCollection(Collection<DatosMedicosPaciente> datosMedicosPacienteCollection) {
        this.datosMedicosPacienteCollection = datosMedicosPacienteCollection;
    }

    @XmlTransient
    public Collection<Pago> getPagoCollection() {
        return pagoCollection;
    }

    public void setPagoCollection(Collection<Pago> pagoCollection) {
        this.pagoCollection = pagoCollection;
    }

    @XmlTransient
    public Collection<Imagen> getImagenCollection() {
        return imagenCollection;
    }

    public void setImagenCollection(Collection<Imagen> imagenCollection) {
        this.imagenCollection = imagenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaciente != null ? idpaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.idpaciente == null && other.idpaciente != null) || (this.idpaciente != null && !this.idpaciente.equals(other.idpaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
}
