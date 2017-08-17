package Fidelizacion.modelo;

import Fidelizacion.entidades.Propiedad;
import Fidelizacion.entidades.Residente;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kavantic S.A.S
 */
@Stateless
public class FachadaResidente {

    @PersistenceContext(unitName = "AdministratePU")
    private EntityManager em;

    /**
     * Adiciona un residente. Si la residente ya existe lanza una excepcion
     *
     * @param residente Residente a adicionar
     */
    public void adicionar(Residente residente) {
        em.persist(residente);
    }

    /**
     * Actualiza un residente. Actualiza los datos del residente
     *
     * @param residente Residente a adicionar
     */
    public void actualizar(Residente residente) {
        em.merge(residente);
    }

    /**
     * Elimina la residente .
     *
     * @param residente Residente a eliminar
     */
    public void eliminar(Residente residente) {
        em.remove(em.merge(residente));
    }

    /**
     * Busca la residente con el nombre dado. Retorna null si no existe
     *
     * @param nombre Nombre de la residente
     * @return null si la residente no existe o el objeto residente
     */
    public Residente buscar(String nombre) {
        return em.find(Residente.class, nombre);
    }

    /**
     * Retorna TODAS las residente que se han almacenado
     *
     * @return Lista con las residentes
     */
    public Collection<Residente> listar() {
        Query query = em.createQuery("SELECT r FROM Residente r ");
        return (Collection<Residente>) query.getResultList();
    }

    public Collection<Residente> listarResidentesPorPropiedad(Propiedad propiedad) {
        Query query = em.createQuery("SELECT r FROM Residente r WHERE r.idPropiedad=?1");
        query.setParameter(1, propiedad);
        return (Collection<Residente>) query.getResultList();
    }

    public Residente buscarPorCedula(String cedula) {
        try {
            Query consulta = em.createQuery("Select r from Residente r where r.cedula = ?1");
            consulta.setParameter(1, cedula);
            return (Residente) consulta.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    public Residente buscarPorEmail(String email) {
        try {
            Query consulta = em.createQuery("Select r from Residente r where r.email = ?1");
            consulta.setParameter(1, email);
            return (Residente) consulta.getResultList().get(0);
        } catch (Exception ex) {
            System.out.println("Error!");
            return null;
        }
    }
    
    /**
     * Busca el administrador con el usuario dado. 
     * Retorna null si no existe 
     * 
     * @param usuario Email de el administrador 
     * @return null si el administrador no existe o el objeto administrador 
     */
    public Residente buscarPorUsuario(String usuario) {
        try {
            Query consulta = em.createQuery("Select r from Residente r where r.usuario = ?1");
            consulta.setParameter(1, usuario);
            return (Residente) consulta.getResultList().get(0);
        } catch (Exception ex) {
            System.out.println("Error!");
            return null;
        }
    }
}
