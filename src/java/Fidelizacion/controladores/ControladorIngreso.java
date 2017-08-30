package Fidelizacion.controladores;

import Fidelizacion.entidades.Ingreso;
import Fidelizacion.modelo.FachadaIngreso;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Kavantic S.A.S
 */
@ManagedBean(name = "controladorIngreso")
@SessionScoped
public class ControladorIngreso implements Serializable{

    //Ingreso en creación o actualización
    private Ingreso ingreso;

    /**
     * Fachada de acceso a los datos
     */
    @EJB
    private FachadaIngreso fachadaIngreso;

    /**
     * Constructor vacio
     */
    public ControladorIngreso() {
    }

    public FachadaIngreso getFachadaIngreso() {
        return fachadaIngreso;
    }

    public void setFachadaIngreso(FachadaIngreso fachadaIngreso) {
        this.fachadaIngreso = fachadaIngreso;
    }

    /**
     * Devuelve el ingreso en creación o edición
     *
     * @return ingreso a crear o actualizar
     */
    public Ingreso getIngreso() {

        // Si la ingreso esta vacio crear uno 
        if (ingreso == null) {
            ingreso = new Ingreso();
        }

        // Retorna el ingreso actual
        return ingreso;
    }

    /**
     * Actualiza el ingreso en edición o creación
     *
     * @param ingreso ingreso actual
     */
    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }

    /**
     * Devuelve todos las ingresos registradas en el sistema
     *
     * @return Lista de ingresos
     */
    public Collection<Ingreso> getIngresos() {
        return fachadaIngreso.listaPorPagador(ingreso.getPagador().getId());
    }

    /**
     * Adiciona a la unidad de persistencia la ingreso actual
     *
     * @return Nombre de la página despues de crear
     */
    public String crear() {
        ingreso.setEstado(true);
        fachadaIngreso.adicionar(ingreso);
        return "listarPorPagador";
    }

    /**
     * Elimina el ingreso especificado y retorna a la lista
     *
     * @param ingreso Ingreso a eliminar
     * @return Página a la que se devuelve
     */
    public String eliminar(Ingreso ingreso) {
        fachadaIngreso.eliminar(ingreso);
        return"listaIngresos";
    }

    /**
     * Elimina el ingreso especificado y retorna a la lista
     *
     * @param ingreso Ingreso a eliminar
     * @return Página a la que se devuelve
     */
    public String eliminarCopia(Ingreso ingreso) {
        fachadaIngreso.eliminar(ingreso);
        return"listaIngresos";
    }

    /**
     * Asigna el ingreso especificado como el ingreso actual y llama a la página
     * de actualizar
     *
     * @param ingreso Ingreso a actualizar
     * @return devuelve a la página de actualización
     */
    public String prepararActualizar(Ingreso ingreso) {
        //Asigna el ingreso seleccionado desde la lista como  el actual 
        this.ingreso = ingreso;
        return "actualizarIngreso";
    }

    /**
     * Actualiza los datos del ingreso actual
     *
     * @return devuelve a la lista
     */
    public String actualizar() {
        fachadaIngreso.actualizar(ingreso);
        return"listaIngresos";
    }
    
    /**
     * Devuelve todos las ingresos registradas en el sistema
     *
     * @return Lista de ingresos
     */
    public Collection<Ingreso> listaIngresosPorPagador() {
        
        return fachadaIngreso.listar();
    }
}
