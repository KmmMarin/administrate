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
 * @author Kavantic S.A.S
 */
@Entity
@Table(name = "PH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ph.findAll", query = "SELECT p FROM Ph p")
    , @NamedQuery(name = "Ph.findById", query = "SELECT p FROM Ph p WHERE p.id = :id")
    , @NamedQuery(name = "Ph.findByNombre", query = "SELECT p FROM Ph p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Ph.findByNit", query = "SELECT p FROM Ph p WHERE p.nit = :nit")
    , @NamedQuery(name = "Ph.findByDescripcion", query = "SELECT p FROM Ph p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Ph.findByDireccion", query = "SELECT p FROM Ph p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "Ph.findByTelefono", query = "SELECT p FROM Ph p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Ph.findByNumPropiedades", query = "SELECT p FROM Ph p WHERE p.numPropiedades = :numPropiedades")})
public class Ph implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 50)
    @Column(name = "NIT")
    private String nit;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 100)
    @Column(name = "DIRECCION")
    private String direccion;
    @Size(max = 20)
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "NUM_PROPIEDADES")
    private Integer numPropiedades;
    @JoinColumn(name = "ADMINISTRADOR", referencedColumnName = "ID")
    @ManyToOne
    private Administrador administrador;

    public Ph() {
    }

    public Ph(Integer id) {
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getNumPropiedades() {
        return numPropiedades;
    }

    public void setNumPropiedades(Integer numPropiedades) {
        this.numPropiedades = numPropiedades;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
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
        if (!(object instanceof Ph)) {
            return false;
        }
        Ph other = (Ph) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Fidelizacion.entidades.Ph[ id=" + id + " ]";
    }
    
}
