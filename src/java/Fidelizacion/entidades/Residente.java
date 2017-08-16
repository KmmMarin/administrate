/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fidelizacion.entidades;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Desarrollo
 */
@Entity
@Table(name = "RESIDENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Residente.findAll", query = "SELECT r FROM Residente r")
    , @NamedQuery(name = "Residente.findById", query = "SELECT r FROM Residente r WHERE r.id = :id")
    , @NamedQuery(name = "Residente.findByNombre", query = "SELECT r FROM Residente r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "Residente.findByApellidos", query = "SELECT r FROM Residente r WHERE r.apellidos = :apellidos")
    , @NamedQuery(name = "Residente.findByCedula", query = "SELECT r FROM Residente r WHERE r.cedula = :cedula")
    , @NamedQuery(name = "Residente.findByTelefono", query = "SELECT r FROM Residente r WHERE r.telefono = :telefono")
    , @NamedQuery(name = "Residente.findByDireccion", query = "SELECT r FROM Residente r WHERE r.direccion = :direccion")
    , @NamedQuery(name = "Residente.findByEmail", query = "SELECT r FROM Residente r WHERE r.email = :email")
    , @NamedQuery(name = "Residente.findByRol", query = "SELECT r FROM Residente r WHERE r.rol = :rol")
    , @NamedQuery(name = "Residente.findByUsuario", query = "SELECT r FROM Residente r WHERE r.usuario = :usuario")
    , @NamedQuery(name = "Residente.findByContrase\u00f1a", query = "SELECT r FROM Residente r WHERE r.contrase\u00f1a = :contrase\u00f1a")})
public class Residente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 100)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Size(max = 20)
    @Column(name = "CEDULA")
    private String cedula;
    @Size(max = 20)
    @Column(name = "TELEFONO")
    private String telefono;
    @Size(max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 20)
    @Column(name = "ROL")
    private String rol;
    @Size(max = 50)
    @Column(name = "USUARIO")
    private String usuario;
    @Size(max = 50)
    @Column(name = "CONTRASE\u00d1A")
    private String contraseña;
    @JoinColumn(name = "ID_PROPIEDAD", referencedColumnName = "ID")
    @ManyToOne
    private Propiedad idPropiedad;

    public Residente() {
    }

    public Residente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Propiedad getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(Propiedad idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Residente)) {
            return false;
        }
        Residente other = (Residente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Fidelizacion.entidades.Residente[ id=" + id + " ]";
    }
    
}
