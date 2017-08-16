package Fidelizacion.controladores;

import Fidelizacion.entidades.Propietario;
import Fidelizacion.modelo.FachadaPropietario;
import java.io.Serializable;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Kavantic S.A.S
 */
@ManagedBean(name = "controladorPropietario")
@SessionScoped
public class ControladorPropietario implements Serializable {

    //Propietario en creación o actualización
    private Propietario propietario;

    //Fachada de acceso a los datos
    @EJB
    private FachadaPropietario fachadaPropietario;

    /**
     * Constructor vacio
     */
    public ControladorPropietario() {
    }

    /**
     * Devuelve el propietario en creación o edición
     *
     * @return propietario a crear o actualizar
     */
    public Propietario getPropietario() {

        // Si el propietario esta vacio crear uno 
        if (propietario == null) {
            propietario = new Propietario();
        }

        // Retorna el propietario actual
        return propietario;
    }

    /**
     * Actualiza el propietario en edición o creación
     *
     * @param propietario propietario actual
     */
    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    /**
     * Devuelve todos los propietarios registrados en el sistema
     *
     * @return Lista de propietarios
     */
    public Collection<Propietario> getPropietarios() {
        return fachadaPropietario.listar();
    }

    /**
     * Retorna la fachada de los propietarios
     *
     * @return fachada de los propietarios
     */
    public FachadaPropietario getFachadaPropietario() {
        return fachadaPropietario;
    }

    /**
     * Adiciona a la unidad de persistencia el propietario actual
     *
     * @return Nombre de la página despues de crear
     */
    public String crear() {
        if (fachadaPropietario.buscarPorCedula(propietario.getCedula()) == null) {
            if (validarCorreo() == true) {
                propietario.setRol("Propietario");
                fachadaPropietario.adicionar(propietario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Propietario registrado con éxito", ""));
                propietario = null;
                return "listaPropietarios";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Formato de correo incorrecto", ""));
                return "crearPropietario";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "La cédula ingesada ya esta registrada en el sistema", ""));
            return "crearPropietario";
        }

    }

    /**
     * Válida el formato de un correo ingresado
     *
     * @return devuelve verdadero o falso al validar el correo
     */
    public boolean validarCorreo() {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(propietario.getEmail());

        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina el propietario especificado y retorna a la lista
     *
     * @param propietario Propietario a eliminar
     * @return Página a la que se devuelve
     */
    public String eliminar(Propietario propietario) {
        fachadaPropietario.eliminar(propietario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Propietario eliminado.", ""));
        return "listaPropietariosCopia";
    }

    /**
     * Elimina el propietario especificado y retorna a la lista
     *
     * @param propietario Propietario a eliminar
     * @return Página a la que se devuelve
     */
    public String eliminarCopia(Propietario propietario) {
        fachadaPropietario.eliminar(propietario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Propietario eliminado.", ""));
        return "listaPropietarios";
    }

    /**
     * Asigna el propietario especificado como el propietario actual y llama a
     * la página de actualizar
     *
     * @param propietario Propietario a actualizar
     * @return devuelve a la página de actualización
     */
    public String prepararActualizar(Propietario propietario) {
        //Asigna el propietario seleccionado desde la lista como  el actual 
        this.propietario = propietario;
        return "/administrador/actualizarPropietario";
    }

    /**
     * Asigna el propietario especificado como el propietario actual y llama a
     * la página de actualizar
     *
     * @param propietario Propietario a actualizar
     * @return devuelve a la página de actualización
     */
    public String prepararActualizarDesdePropiedades(Propietario propietario) {
        //Asigna el propietario seleccionado desde la lista como  el actual 
        this.propietario = propietario;
        return "/propiedades/actualizarPropietario";
    }

    /**
     * Asigna el propietario especificado como el propietario actual y llama a
     * la página de general credenciales
     *
     * @param propietario Propietario a actualizar
     * @return devuelve a la página de generación de credenciales
     */
    public String prepararGenerarCredenciales(Propietario propietario) {
        //Asigna el propietario seleccionado desde la lista como  el actual 
        this.propietario = propietario;
        return "generarCredencialesPropietario";
    }

    /**
     * Actualiza los datos del propietario actual
     *
     * @return devuelve a la lista
     */
    public String actualizar() {
        if (validarCorreo() == true) {
            fachadaPropietario.actualizar(propietario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil actualizado.", ""));
            propietario = null;
            return "listaPropietarios";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Formato de correo inválido."));
            return "actualizarPropietario";
        }
    }

    /**
     * Actualiza los datos del propietario actual desde cierta propiedad
     *
     * @return devuelve a la lista
     */
    public String actualizarDesdePropiedad() {
        if (validarCorreo() == true) {
            fachadaPropietario.actualizar(propietario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil actualizado.", ""));
            propietario = null;
            return "listaPropiedades";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Formato de correo inválido."));
            return "/propiedades/actualizarPropietario";
        }
    }

    /**
     * Genera las credenciales para el propietario actual
     *
     * @return devuelve a la lista
     */
    public String generarCredenciales() {
        if (fachadaPropietario.buscarPorUsuario(propietario.getUsuario())== null) {
            fachadaPropietario.actualizar(propietario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Credenciales Generadas.", ""));
            propietario = null;
            return "listaPropietarios";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario ya registrado en el sistema.", ""));
            return null;
        }

    }

    /**
     * Guarda en el sistema el usuario y la contraseña del propietario actual.
     *
     * @param propietario
     */
    public void guardarUsuarioContraseña(Propietario propietario) {
        fachadaPropietario.actualizar(propietario);
    }
}
