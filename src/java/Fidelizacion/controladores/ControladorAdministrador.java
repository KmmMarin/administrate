package Fidelizacion.controladores;

import Fidelizacion.entidades.Administrador;
import Fidelizacion.modelo.FachadaAdministrador;
import Fidelizacion.modelo.SessionUtils;
import java.io.Serializable;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kavantic S.A.S
 */
@ManagedBean(name = "controladorAdministrador")
@SessionScoped
public class ControladorAdministrador implements Serializable {

    //Administrador en creación o actualización
    private Administrador administrador;

    //Fachada de acceso a los datos
    @EJB
    private FachadaAdministrador fachadaAdministrador;

    //Controlador de sesiones
    private ControladorSesion controladorSesion;

    //Validador de correo
    private boolean validarCorreo;

    //Email de administrador
    private String email;

    //Usuario para inicio de sesión
    private String usuario;

    //Contraseña para inicio de sesión
    private String contraseña;

    //Obtener controlador de sesión
    public ControladorSesion getControladorSesion() {
        return controladorSesion;
    }

    //Devuelve controlador de sesión
    public void setControladorSesion(ControladorSesion controladorSesion) {
        this.controladorSesion = controladorSesion;
    }

    //Obtener usuario en uso
    public String getUsuario() {
        return usuario;
    }

    //Cambiar de usuario
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    //Obtener contraseña de administrador
    public String getContraseña() {
        return contraseña;
    }

    //Cambiar contraseña de administrador
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    //Obtener email de administrador
    public String getEmail() {
        return email;
    }

    //Cambiar email de administrador
    public void setEmail(String email) {
        this.email = email;
    }

    //Obtener valor de validación del correo
    public boolean isValidarCorreo() {
        return validarCorreo;
    }

    //Cambiar validación del correo
    public void setValidarCorreo(boolean validarCorreo) {
        this.validarCorreo = validarCorreo;
    }

    //Constructor vacio
    public ControladorAdministrador() {
    }

    /*
     * Devuelve el administrador en creación o edición
     * @return administrador a crear o actualizar
     */
    public Administrador getAdministrador() {
        // Si el administrador esta vacio crear uno 
        if (administrador == null) {
            administrador = new Administrador();
        }

        // Retorna el administrador actual
        return administrador;
    }

    /*
    * Actualiza el administrador en edición o creación
    * @param administrador administrador actual
     */
    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    /*
    * Devuelve todos los administradores registrados en el sistema
    * @return Lista de administradors
     */
    public Collection<Administrador> getAdministradors() {
        return fachadaAdministrador.listar();
    }

    /**
     * Retorna la fachada de los administradors
     *
     * @return fachada de los administradors
     */
    public FachadaAdministrador getFachadaAdministrador() {
        return fachadaAdministrador;
    }

    /**
     * Adicióna a la unidad de persistencia el administrador actual
     *
     * @return Nombre de la página despues de crear
     */
    public String crear() {
        fachadaAdministrador.adicionar(administrador);
        return "listar";
    }

    /**
     * Elimina el administrador especificada y retorna a la lista
     *
     * @param administrador Administrador a eliminar
     * @return Pagina a la que se devuelve
     */
    public String eliminar(Administrador administrador) {
        fachadaAdministrador.eliminar(administrador);
        return "listar";
    }

    /**
     * Asigna el administrador especificado como el administrador actual y llama
     * a la página de actualizar
     *
     * @param administrador Administrador a actualizar
     * @return devuelve a la página de actualización
     */
    public String prepararActualizar(Administrador administrador) {
        //Asigna la administrador seleccionado desde la lista como  el actual 
        this.administrador = administrador;
        return "actualizar";
    }

    /**
     * Actualiza los datos del administrador actual, validando que los datos no
     * esten duplicados en el sistema
     *
     * @return devuelve a la lista
     */
    public String actualizar() {
        System.out.println("Actualizare: ");
//        email = administrador.getEmail();
//        if (validarCorreo() == true) {
//            fachadaAdministrador.actualizar(administrador);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Perfil actualizado."));
//            return "perfil";
//        } else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Formato de correo inválido."));
            return "perfilAdministrador";
//        }
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
        Matcher mather = pattern.matcher(email);

        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Buscar el administrador de la sesión.
     *
     * @return administrador de la sesión actual
    */
    public Administrador sesion() {
        Administrador admin = fachadaAdministrador.buscar(administrador.getUsuario(), administrador.getContraseña());
        return admin;
    }

    /**
     * Volver a la página anterior
     *
     * @return página de inicio
     */
    public String volver() {
        return "index";
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


