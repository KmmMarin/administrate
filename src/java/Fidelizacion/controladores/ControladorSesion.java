package Fidelizacion.controladores;

import Fidelizacion.entidades.Administrador;
import Fidelizacion.entidades.Propietario;
import Fidelizacion.entidades.Residente;
import Fidelizacion.modelo.FachadaAdministrador;
import Fidelizacion.modelo.FachadaPropietario;
import Fidelizacion.modelo.FachadaResidente;
import Fidelizacion.modelo.FachadaSesion;
import Fidelizacion.modelo.SessionUtils;
import java.io.Serializable;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kavantic S.A.S
 */
@ManagedBean(name = "controladorSesion")
@SessionScoped
public class ControladorSesion implements Serializable {

    //Usuario ingresado para iniciar sesión
    private String usuario;
    
    //Contraseña ingresada para iniciar sesión
    private String contraseña;
    
    //Rol ingresado para iniciar sesión
    private String rol;
    
    /**
     * Fachada de acceso a los datos de sesión
     */
    @EJB
    private FachadaSesion fachadaSesion;
    
    /**
     * Fachada de acceso a los datos de administrador
     */
    @EJB
    private FachadaAdministrador fachadaAdministrador;
    
    /**
     * Fachada de acceso a los datos de propietario
     */
    @EJB
    private FachadaPropietario fachadaPropietario;
    
    /**
     * Fachada de acceso a los datos de residente
     */
    @EJB
    private FachadaResidente fachadaResidente;
    
    //Acceso a datos del controlador administrador
    private ControladorAdministrador controladorAdministrador;
    
    //Administrador actual de la sesión
    private Administrador administrador;
    
    //Propietario actual de la sesión
    private Propietario propietario;
    
    //Residente actual de la sesión
    private Residente residente;
    
    //Email actual de la sesión
    private String email;

    /**
     * Retorna el controlador de un administrador
     *
     * @return controlador de administrador
     */
    public ControladorAdministrador getControladorAdministrador() {
        return controladorAdministrador;
    }

    /**
     * Actualiza el controlador de un administrador
     *
     * @param controladorAdministrador
     */
    public void setControladorAdministrador(ControladorAdministrador controladorAdministrador) {
        this.controladorAdministrador = controladorAdministrador;
    }

    /**
     * Retorna el email de recuperación de contraseña
     *
     * @return email para recuperar
     */
    public String getEmail() {
        return email;
    }

    /**
     * Actualiza el email de recuperación de contraseña
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna la fachada del administrador
     *
     * @return fachada de los administradores
     */
    public FachadaAdministrador getFachadaAdministrador() {
        return fachadaAdministrador;
    }

    /**
     * Actualiza la fachada del administrador
     *
     * @param fachadaAdministrador
     */
    public void setFachadaAdministrador(FachadaAdministrador fachadaAdministrador) {
        this.fachadaAdministrador = fachadaAdministrador;
    }

    /**
     * Retorna la fachada del propietario
     *
     * @return fachada del propietario
     */
    public FachadaPropietario getFachadaPropietario() {
        return fachadaPropietario;
    }

    /**
     * Actualiza la fachada del propietario
     *
     * @param fachadaPropietario
     */
    public void setFachadaPropietario(FachadaPropietario fachadaPropietario) {
        this.fachadaPropietario = fachadaPropietario;
    }

    /**
     * Retorna la fachada del residente
     *
     * @return fachada del residente
     */
    public FachadaResidente getFachadaResidente() {
        return fachadaResidente;
    }

    /**
     * Actualiza la fachada del propietario
     *
     * @param fachadaResidente
     */
    public void setFachadaResidente(FachadaResidente fachadaResidente) {
        this.fachadaResidente = fachadaResidente;
    }

    /**
     * Retorna el residente actual
     *
     * @return residente
     */
    public Residente getResidente() {
        return residente;
    }

    /**
     * Actualiza el residente
     *
     * @param residente
     */
    public void setResidente(Residente residente) {
        this.residente = residente;
    }

    /**
     * Retorna el prpietario actual
     *
     * @return propietario
     */
    public Propietario getPropietario() {
        return propietario;
    }

    /**
     * Actualiza el propietario
     *
     * @param propietario
     */
    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    /**
     * Retorna el administrador actual
     *
     * @return administrador
     */
    public Administrador getAdministrador() {
        return administrador;
    }

    /**
     * Actualiza el administrador
     *
     * @param administrador
     */
    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    /**
     * Retorna la fachada de la sesión
     *
     * @return fachada de la sesión
     */
    public FachadaSesion getFachadaSesion() {
        return fachadaSesion;
    }

    /**
     * Actualiza la fachada de la sesión
     *
     * @param fachadaSesion
     */
    public void setFachadaSesion(FachadaSesion fachadaSesion) {
        this.fachadaSesion = fachadaSesion;
    }

    /**
     * Retorna el rol de la sesión actual
     *
     * @return rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * Actualiza el rol de la sesión actual
     *
     * @param rol
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Retorna el uusuario de la sesión actual
     *
     * @return rol
     */ 
    public String getUsuario() {
        return usuario;
    }

    /**
     * Actualiza el usuario de la sesión actual
     *
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Retorna la contraseña de la sesión actual
     *
     * @return contraseña
     */ 
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Actualiza la contraseña de la sesión actual
     *
     * @param contraseña
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Valida el inicio de sesión con los atributos especificos.
     *
     * @return página principal
     */
    public String validarSesion() {
        if (rol.equals("administrador")){
            administrador = fachadaSesion.sesionAdminsitrador(usuario, contraseña);
            if (administrador != null) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("usuario", usuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inicio de sesión exitoso.", ""));
                return "/administrador/indexAdministrador";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Usuario o contraseña incorrectos",
                                "Ingrese los datos de nuevo"));
                return "index";
            }
        }
        if (rol.equals("propietario")) {
            propietario = fachadaSesion.sesionPropietario(
                    usuario, contraseña);
            if (propietario != null) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("usuario", usuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inicio de sesión exitoso.", ""));
                return "/propietario/indexPropietario"; 
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Usuario o contraseña incorrecta",
                                "Ingrese los datos de nuevo"));
                return "index";
            }
        }
        if (rol.equals("residente")) {
            residente = fachadaSesion.sesionResidente(
                    usuario, contraseña);
            if (residente != null) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("usuario", usuario);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inicio de sesión exitoso.", ""));
                return "/residente/indexResidente";
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Usuario o contraseña incorrecta",
                                "Ingrese los datos de nuevo"));
                return "index";
            }
        }
        return "index";
    }

    /**
    * Cerrar sesión actual
    * @return página inicial
    */
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sesión Finalizada.", ""));
        return "/index";
    }

    /**
    * Recuperar la contraseña de un usuario cuando esta sea olvidada
    * @return página con instrucciones
    */
    public String recuperarContraseña() {
        if (validarCorreo() == true) {
            if (fachadaAdministrador.buscarPorEmail(email) != null
                    || fachadaPropietario.buscarPorEmail(email) != null
                    || fachadaResidente.buscarPorEmail(email) != null) {
                enviarEmail();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Instrucciones para recuperar contraseña han sidoo enviadas al correo electrónico."));
                email=null;
                return "/envioCorreo";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Correo no registrado en el sistema."));
                email = null;
                return "recuperarContraseña";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Formato de correo inválido."));
            email = null;
            return "recuperarContraseña";
        }
    }

    /**
    * Válida el formato de un correo ingresado
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
    * Envia el correo de recuperación de contraseña cuando fue olvidada
    * @return devuelve si fue enviado o no el correo
    */
    private boolean enviarEmail() {
        try {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            //Recoger los datos
            String email_Remitente = "marindiegofernando@gmail.com";
            String str_PwRemitente = "rolokarla21";
            String str_Para = email;
            String str_Asunto = "Recuperar Contraseña";
            String str_Mensaje = "Su contraseña de acceso es: " + generarContraseñaAleatorio();
            //Obtenemos los destinatarios
//            String destinos[] = str_Para.split(",");
            String destinos = email;
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email_Remitente));

            //Otra forma de especificar las direcciones de email 
            //a quienes se enviar el correo electronico
            //Forma 1
            Address[] receptores = new Address[]{
                new InternetAddress(email)
            };
            //Forma 2
            //  Address [] receptores = new Address []{
            //      new InternetAddress ( email_Remitente )
            // };
            //Forma 3
//            Address[] receptores = new Address[destinos.length];
//            int j = 0;
//            while (j < destinos.length) {
//                receptores[j] = new InternetAddress(destinos[j]);
//                j++;
//            }
            //receptores.
            message.addRecipients(Message.RecipientType.TO, receptores);
            message.setSubject(str_Asunto);
            message.setText(str_Mensaje);
            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect(email_Remitente, str_PwRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            // Cierre de la conexion.
            t.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String generarContraseñaAleatorio() {
        char[] caracteres;
        caracteres = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'};
        int longitud = 20;
        String password = "";
        for (int i = 0; i < longitud; i++) {
            password += caracteres[new Random().nextInt(62)];
        }
        return password;
    }
}
