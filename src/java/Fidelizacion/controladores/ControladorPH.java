package Fidelizacion.controladores;

import Fidelizacion.entidades.Ph;
import Fidelizacion.modelo.FachadaPH;
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
@ManagedBean(name = "controladorPH")
@SessionScoped
public class ControladorPH implements Serializable {

    //Ph en creación o actualización
    private Ph ph;

    //Fachada de acceso a los datos
    @EJB
    private FachadaPH fachadaPH;
    ControladorSesion sesion;

    public FachadaPH getFachadaPH() {
        return fachadaPH;
    }

    public void setFachadaPH(FachadaPH fachadaPH) {
        this.fachadaPH = fachadaPH;
    }

    public ControladorSesion getSesion() {
        return sesion;
    }

    public void setSesion(ControladorSesion sesion) {
        this.sesion = sesion;
    }

    /*
    * Constructor vacio
     */
    public ControladorPH() {
    }

    /*
    * Devuelve la ph en creación o edición   
    * @return ph a crear o actualizar
     */
    public Ph getPH() {

        // Si la ph esta vacio crear uno 
        if (ph == null) {
            ph = new Ph();
        }

        // Retorna el ph actual
        return ph;
    }

    /**
     * Actualiza la ph en edición o creación
     *
     * @param ph ph actual
     */
    public void setPh(Ph ph) {
        this.ph = ph;
    }

    /**
     * Devuelve todos las phs registradas en el sistema
     *
     * @return Lista de phs
     */
    public Collection<Ph> getPhs() {
        return fachadaPH.listar();
    }

    /**
     * Retorna la fachada de las phes
     *
     * @return fachada de los phs
     */
    public FachadaPH getFachadaPh() {
        return fachadaPH;
    }

    /**
     * Adiciona a la unidad de persistencia la ph actual
     *
     * @return Nombre de la página despues de crear
     */
    public String crear() {
        System.out.println(12&13);
//        System.out.println(""+sesion.getAdministrador());
//        fachadaPH.adicionar(ph);
//        ph = null;
        return "listaPH";
    }

    /**
     * Elimina la ph especificada y retorna a la lista
     *
     * @param ph a eliminar
     * @return Pagina a la que se devuelve
     */
    public String eliminar(Ph ph) {
        fachadaPH.eliminar(ph);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "PH eliminada.", ""));
        return "listaPHCopia";
    }

    /**
     * Elimina la ph especificada y retorna a la lista
     *
     * @param ph a eliminar
     * @return Pagina a la que se devuelve
     */
    public String eliminarCopia(Ph ph) {
        fachadaPH.eliminar(ph);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Propiedad eliminada.", ""));
        return "listaPH";
    }

    /**
     * Asigna la ph especificada como la ph actual y llama a la página de
     * actualizar
     *
     * @param ph a actualizar
     * @return devuelve a la página de actualización
     */
    public String prepararActualizar(Ph ph) {
        //Asigna la ph seleccionado desde la lista como la actual 
        this.ph = ph;
        return "actualizarPH";
    }

    /**
     * Actualiza los datos de la ph actual
     *
     * @return devuelve a la lista
     */
    public String actualizar() {
        fachadaPH.actualizar(ph);
        ph = null;
        return "listaPH";
    }

    /**
     * Volver a la página anterior
     *
     * @return página de inicio
     */
    public String volver() {
        ph = null;
        return "listaPH";
    }
}
