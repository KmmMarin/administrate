package Fidelizacion.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kavantic S.A.S
 */
@Entity
@Table(name = "PROPIETARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Propietario.findAll", query = "SELECT p FROM Propietario p")
    , @NamedQuery(name = "Propietario.findById", query = "SELECT p FROM Propietario p WHERE p.id = :id")
    , @NamedQuery(name = "Propietario.findByNombre", query = "SELECT p FROM Propietario p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Propietario.findByApellidos", query = "SELECT p FROM Propietario p WHERE p.apellidos = :apellidos")
    , @NamedQuery(name = "Propietario.findByCedula", query = "SELECT p FROM Propietario p WHERE p.cedula = :cedula")
    , @NamedQuery(name = "Propietario.findByTelefono", query = "SELECT p FROM Propietario p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Propietario.findByDireccion", query = "SELECT p FROM Propietario p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "Propietario.findByEmail", query = "SELECT p FROM Propietario p WHERE p.email = :email")
    , @NamedQuery(name = "Propietario.findByRol", query = "SELECT p FROM Propietario p WHERE p.rol = :rol")
    , @NamedQuery(name = "Propietario.findByUsuario", query = "SELECT p FROM Propietario p WHERE p.usuario = :usuario")
    , @NamedQuery(name = "Propietario.findByContrase\u00f1a", query = "SELECT p FROM Propietario p WHERE p.contrase\u00f1a = :contrase\u00f1a")})
public class Propietario implements Serializable {

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
    @OneToMany(mappedBy = "idPropietario")
    private Collection<Propiedad> propiedadCollection;

    public Propietario() {
    }

    public Propietario(Integer id) {
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

    @XmlTransient
    public Collection<Propiedad> getPropiedadCollection() {
        return propiedadCollection;
    }

    public void setPropiedadCollection(Collection<Propiedad> propiedadCollection) {
        this.propiedadCollection = propiedadCollection;
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
        if (!(object instanceof Propietario)) {
            return false;
        }
        Propietario other = (Propietario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre+" "+apellidos;
    }
    
}
