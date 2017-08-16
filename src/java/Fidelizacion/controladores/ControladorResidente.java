package Fidelizacion.controladores;

import Fidelizacion.entidades.Propiedad;
import Fidelizacion.entidades.Residente;
import Fidelizacion.modelo.FachadaPropiedad;
import Fidelizacion.modelo.FachadaResidente;
import Fidelizacion.modelo.SessionUtils;
import java.io.Serializable;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kavantic S.A.S
 */
@ManagedBean(name = "controladorResidente")
@SessionScoped
public class ControladorResidente implements Serializable {

    //Residente en creación o actualización
    private Residente residente;

    //Propiedad en creación o actualización
    private Propiedad propiedad;

    /**
     * Fachada de acceso a los datos
     */
    @EJB
    private FachadaResidente fachadaResidente;

    /**
     * Fachada de acceso a los datos
     */
    @EJB
    private FachadaPropiedad fachadaPropiedad;

    /**
     * Constructor vacio
     */
    public ControladorResidente() {
    }

    /**
     * Retorna la fachada de las propiedades
     *
     * @return fachada de las propiedades
     */
    public FachadaPropiedad getFachadaPropiedad() {
        return fachadaPropiedad;
    }

    /**
     * Cambia la fachada de las propiedades
     *
     * @param fachadaPropiedad
     */
    public void setFachadaPropiedad(FachadaPropiedad fachadaPropiedad) {
        this.fachadaPropiedad = fachadaPropiedad;
    }

    /**
     * Devuelve la propiedad en creación o edición
     *
     * @return propiedad a crear o actualizar
     */
    public Propiedad getPropiedad() {
        return propiedad;
    }

    /**
     * Actualiza la propiedad en edición o creación
     *
     * @param propiedad
     */
    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }

    /**
     * Devuelve el residente en creación o edición
     *
     * @return residente a crear o actualizar
     */
    public Residente getResidente() {

        // Si la residente esta vacio crear uno 
        if (residente == null) {
            residente = new Residente();
        }

        // Retorna el residente actual
        return residente;
    }

    /**
     * Actualiza el residente en edición o creación
     *
     * @param residente residente actual
     */
    public void setResidente(Residente residente) {
        this.residente = residente;
    }

    /**
     * Devuelve todos las residentes registradas en el sistema
     *
     * @return Lista de residentes
     */
    public Collection<Residente> getResidentes() {
        return fachadaResidente.listar();
    }

    /**
     * Devuelve todos las residentes registradas en el sistema asociados a una
     * propiedad determinada
     *
     * @param propiedad
     * @return Lista de residentes por propiedad
     */
    public String prepararResidentesPorPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
        return "listaResidentesPorPropiedad";
    }

    /**
     * Asigna a la propiedad actual el residente que se va a crear
     *
     * @return Página para crear residente
     */
    public String prepararCrearDesdePropiedad() {
        return "/propiedades/crearResidente";
    }

    /**
     * Devuelve todos las residentes registradas en el sistema asociados a una
     * propiedad determinada
     *
     * @return lista de residentes por propiedad
     */
    public Collection<Residente> getResidentesPorPropiedad() {
        return fachadaResidente.listarResidentesPorPropiedad(propiedad);
    }

    /**
     * Adiciona a la unidad de persistencia la residente actual
     *
     * @return Nombre de la página despues de crear
     */
    public String crear() {
        if (fachadaResidente.buscarPorCedula(residente.getCedula()) == null) {
            if (validarCorreo() == true) {
                residente.setRol("Residente");
                propiedad = residente.getIdPropiedad();
                fachadaResidente.adicionar(residente);
                propiedad.setNumResidentes(propiedad.getNumResidentes() + 1);
                fachadaPropiedad.actualizar(propiedad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Residente registrado con éxito", ""));
                residente = null;
                return "listaResidentes";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Formato de correo incorrecto", ""));
                return "crearResidente";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "La cédula ingesada ya esta registrada en el sistema", ""));
            return "crearResidente";
        }
    }

    /**
     * Adiciona a la unidad de persistencia la residente actual
     *
     * @return Nombre de la página despues de crear
     */
    public String crearDesdePropiedad() {
        if (fachadaResidente.buscarPorCedula(residente.getCedula()) == null) {
            if (validarCorreo() == true) {
                residente.setRol("Residente");
                propiedad = residente.getIdPropiedad();
                fachadaResidente.adicionar(residente);
                propiedad.setNumResidentes(propiedad.getNumResidentes() + 1);
                fachadaPropiedad.actualizar(propiedad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Residente registrado con éxito", ""));
                residente = null;
                return "listaResidentesPorPropiedad";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Formato de correo incorrecto", ""));
                return "crearResidente";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "La cédula ingesada ya esta registrada en el sistema", ""));
            return "crearResidente";
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
        Matcher mather = pattern.matcher(residente.getEmail());

        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Elimina el residente especificado y retorna a la lista
     *
     * @param residente Residente a eliminar
     * @return Página a la que se devuelve
     */
    public String eliminar(Residente residente) {
        propiedad = residente.getIdPropiedad();
        propiedad.setNumResidentes(propiedad.getNumResidentes() - 1);
        fachadaPropiedad.actualizar(propiedad);
        fachadaResidente.eliminar(residente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Residente eliminado.", ""));
        return "listaResidentesCopia";
    }

    /**
     * Elimina el residente especificado y retorna a la lista
     *
     * @param residente Residente a eliminar
     * @return Página a la que se devuelve
     */
    public String eliminarCopia(Residente residente) {
        propiedad = residente.getIdPropiedad();
        propiedad.setNumResidentes(propiedad.getNumResidentes() - 1);
        fachadaPropiedad.actualizar(propiedad);
        fachadaResidente.eliminar(residente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Residente eliminado.", ""));
        return "listaResidentes";
    }

    /**
     * Elimina el residente especificado y retorna a la lista
     *
     * @param residente Residente a eliminar
     * @return Página a la que se devuelve
     */
    public String eliminarDesdePropiedad(Residente residente) {
        propiedad = residente.getIdPropiedad();
        propiedad.setNumResidentes(propiedad.getNumResidentes() - 1);
        fachadaPropiedad.actualizar(propiedad);
        fachadaResidente.eliminar(residente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Residente eliminado.", ""));
        return "listaResidentesPorPropiedadCopia";
    }

    /**
     * Elimina el residente especificado y retorna a la lista
     *
     * @param residente Residente a eliminar
     * @return Página a la que se devuelve
     */
    public String eliminarCopiaDesdePropiedad(Residente residente) {
        propiedad = residente.getIdPropiedad();
        propiedad.setNumResidentes(propiedad.getNumResidentes() - 1);
        fachadaPropiedad.actualizar(propiedad);
        fachadaResidente.eliminar(residente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Residente eliminado.", ""));
        return "listaResidentesPorPropiedad";
    }

    /**
     * Asigna el residente especificado como el residente actual y llama a la
     * página de actualizar
     *
     * @param residente Residente a actualizar
     * @return devuelve a la página de actualización
     */
    public String prepararActualizar(Residente residente) {
        //Asigna el residente seleccionado desde la lista como  el actual 
        this.residente = residente;
        return "actualizarResidente";
    }

    /**
     * Asigna el residente especificado como el residente actual y llama a la
     * página de actualizar
     *
     * @param residente Residente a actualizar
     * @return devuelve a la página de actualización
     */
    public String prepararActualizarDesdePropiedad(Residente residente) {
        //Asigna el residente seleccionado desde la lista como  el actual 
        this.residente = residente;
        return "actualizarResidente";
    }

    /**
     * Asigna el residente especificado como el residente actual y llama a la
     * página de generar credenciales
     *
     * @param residente Residente a actualizar
     * @return devuelve a la página de actualización
     */
    public String prepararGenerarCredenciales(Residente residente) {
        //Asigna el residente seleccionado desde la lista como  el actual 
        this.residente = residente;
        return "/administrador/generarCredencialesResidente";
    }

    /**
     * Asigna el residente especificado como el residente actual y llama a la
     * página de generar credenciales
     *
     * @param residente Residente a actualizar
     * @return devuelve a la página de actualización
     */
    public String prepararGenerarCredencialesDesdePropiedad(Residente residente) {
        //Asigna el residente seleccionado desde la lista como  el actual 
        this.residente = residente;
        return "/propiedades/generarCredencialesResidente";
    }

    /**
     * Actualiza los datos del residente actual
     *
     * @return devuelve a la lista
     */
    public String actualizar() {
        if (validarCorreo() == true) {
            fachadaResidente.actualizar(residente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil actualizado.", ""));
            residente = null;
            return "listaResidentes";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Formato de correo inválido."));
            return "actualizarResidente";
        }
    }

    /**
     * Actualiza los datos del residente actual
     *
     * @return devuelve a la lista
     */
    public String actualizarDesdePropiedad() {
        if (validarCorreo() == true) {
            fachadaResidente.actualizar(residente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil actualizado.", ""));
            residente = null;
            return "/propiedades/listaResidentesPorPropiedad";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Formato de correo inválido."));
            return "/propiedades/actualizarResidente";
        }
    }

    /**
     * Genera credenciales del residente actual
     *
     * @return devuelve a la lista
     */
    public String generarCredenciales() {
        fachadaResidente.actualizar(residente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Credenciales Generadas.", ""));
        residente = null;
        return "listaResidentes";
    }

    
    /**
     * Genera credenciales del residente actual
     *
     * @return devuelve a la lista
     */
    public String generarCredencialesDesdePropiedad() {
        fachadaResidente.actualizar(residente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Credenciales Generadas.", ""));
        residente = null;
        return "/propiedades/listaResidentesPorPropiedad";
    }

    /**
     * Retorna la fachada de los residentes
     *
     * @return fachada de los residentes
     */
    public FachadaResidente getFachadaResidente() {
        return fachadaResidente;
    }

    /**
     * Guarda en el sistema el usuario y la contraseña del propietario
     * actual.
     * @param residente
     */
    public void guardarUsuarioContraseña(Residente residente) {
        fachadaResidente.actualizar(residente);
    }

    /**
    * Cancelar y volver a la página anterior
    * 
    * @return página de lista de residentes
    */
    public String cancelar() {
        residente = null;
        return "listaResidentesPorPropiedad";
    }
    
    /**
     * Cerrar sesión actual
     *
     * @return página inicial
     */
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sesión Finalizada.", ""));
        return "/index";
    }
}
