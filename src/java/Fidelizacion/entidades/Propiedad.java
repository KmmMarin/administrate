/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fidelizacion.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Desarrollo
 */
@Entity
@Table(name = "PROPIEDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Propiedad.findAll", query = "SELECT p FROM Propiedad p")
    , @NamedQuery(name = "Propiedad.findById", query = "SELECT p FROM Propiedad p WHERE p.id = :id")
    , @NamedQuery(name = "Propiedad.findByArea", query = "SELECT p FROM Propiedad p WHERE p.area = :area")
    , @NamedQuery(name = "Propiedad.findByTipoPropiedad", query = "SELECT p FROM Propiedad p WHERE p.tipoPropiedad = :tipoPropiedad")
    , @NamedQuery(name = "Propiedad.findByNumResidentes", query = "SELECT p FROM Propiedad p WHERE p.numResidentes = :numResidentes")
    , @NamedQuery(name = "Propiedad.findByDescripcion", query = "SELECT p FROM Propiedad p WHERE p.descripcion = :descripcion")})
public class Propiedad implements Serializable {

    @OneToMany(mappedBy = "idPropiedad")
    private Collection<Residente> residenteCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AREA")
    private Float area;
    @Size(max = 30)
    @Column(name = "TIPO_PROPIEDAD")
    private String tipoPropiedad;
    @Column(name = "NUM_RESIDENTES")
    private Integer numResidentes;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinColumn(name = "ID_PROPIETARIO", referencedColumnName = "ID")
    @ManyToOne
    private Propietario idPropietario;

    public Propiedad() {
    }

    public Propiedad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public String getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(String tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public Integer getNumResidentes() {
        return numResidentes;
    }

    public void setNumResidentes(Integer numResidentes) {
        this.numResidentes = numResidentes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Propietario getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Propietario idPropietario) {
        this.idPropietario = idPropietario;
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
        if (!(object instanceof Propiedad)) {
            return false;
        }
        Propiedad other = (Propiedad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    @XmlTransient
    public Collection<Residente> getResidenteCollection() {
        return residenteCollection;
    }

    public void setResidenteCollection(Collection<Residente> residenteCollection) {
        this.residenteCollection = residenteCollection;
    }
    
}
