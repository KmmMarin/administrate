package Fidelizacion.controladores;

import Fidelizacion.entidades.Propiedad;
import Fidelizacion.modelo.FachadaPropiedad;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Kavantic S.A.S
 */
@ManagedBean(name = "controladorPropiedad")
@SessionScoped
public class ControladorPropiedad implements Serializable {

    //Propiedad en creación o actualización
    private Propiedad propiedad;

    //Fachada de acceso a los datos
    @EJB
    private FachadaPropiedad fachadaPropiedad;

    /*
    * Constructor vacio
    */
    public ControladorPropiedad() {
    }

    /*
    * Devuelve la propiedad en creación o edición   
    * @return propiedad a crear o actualizar
    */
    public Propiedad getPropiedad() {

        // Si la propiedad esta vacio crear uno 
        if (propiedad == null) {
            propiedad = new Propiedad();
        }

        // Retorna el propiedad actual
        return propiedad;
    }

    /**
     * Actualiza la propiedad en edición o creación
     *
     * @param propiedad propiedad actual
     */
    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    /**
     * Devuelve todos las propiedads registradas en el sistema
     *
     * @return Lista de propiedads
     */
    public Collection<Propiedad> getPropiedades() {
        return fachadaPropiedad.listar();
    }

    /**
     * Retorna la fachada de las propiedades
     *
     * @return fachada de los propiedads
     */
    public FachadaPropiedad getFachadaPropiedad() {
        return fachadaPropiedad;
    }
    
    /**
     * Adiciona a la unidad de persistencia la propiedad actual
     *
     * @return Nombre de la página despues de crear
     */
    public String crear() {
        fachadaPropiedad.adicionar(propiedad);
        propiedad = null;
        return "listaPropiedades";
    }

    /**
     * Elimina la propiedad especificada y retorna a la lista
     *
     * @param propiedad Propiedad a eliminar
     * @return Pagina a la que se devuelve
     */
    public String eliminar(Propiedad propiedad) {
        fachadaPropiedad.eliminar(propiedad);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Propiedad eliminada.", ""));
        return "listaPropiedadesCopia";
    }

    /**
     * Elimina la propiedad especificada y retorna a la lista
     *
     * @param propiedad Propiedad a eliminar
     * @return Pagina a la que se devuelve
     */
    public String eliminarCopia(Propiedad propiedad) {
        fachadaPropiedad.eliminar(propiedad);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Propiedad eliminada.", ""));
        return "listaPropiedades";
    }

    /**
     * Asigna la propiedad especificada como la propiedad actual y llama a la
     * página de actualizar
     * @param propiedad Propiedad a actualizar
     * @return devuelve a la página de actualización
     */
    public String prepararActualizar(Propiedad propiedad) {
        //Asigna la propiedad seleccionado desde la lista como la actual 
        this.propiedad = propiedad;
        return "actualizarPropiedad";
    }

    /**
     * Actualiza los datos de la propiedad actual
     *
     * @return devuelve a la lista
     */
    public String actualizar() {
        fachadaPropiedad.actualizar(propiedad);
        propiedad = null;
        return "listaPropiedades";
    }

    /**
    * Volver a la página anterior
    * 
    * @return página de inicio
    */
    public String volver() {
        propiedad = null;
        return "listaPropiedades";
    }
}
