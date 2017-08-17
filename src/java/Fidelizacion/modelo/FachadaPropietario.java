package Fidelizacion.modelo;

import Fidelizacion.entidades.Propietario;
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
public class FachadaPropietario {

    @PersistenceContext(unitName = "AdministratePU")
    private EntityManager em;

    /**
     * Adiciona un propietario. Si la propietario ya existe lanza una excepcion
     *
     * @param propietario Propietario a adicionar
     */
    public void adicionar(Propietario propietario) {
        em.persist(propietario);
    }

    /**
     * Actualiza un propietario. Actualiza los datos del propietario
     *
     * @param propietario Propietario a adicionar
     */
    public void actualizar(Propietario propietario) {
        em.merge(propietario);
    }

    /**
     * Elimina la propietario .
     *
     * @param propietario Propietario a eliminar
     */
    public void eliminar(Propietario propietario) {
        em.remove(em.merge(propietario));
    }

    /**
     * Busca la propietario con el nombre dado. Retorna null si no existe
     *
     * @param nombre Nombre de la propietario
     * @return null si la propietario no existe o el objeto propietario
     */
    public Propietario buscar(String nombre) {
        return em.find(Propietario.class, nombre);
    }

    /**
     * Retorna TODAS las propietario que se han almacenado
     *
     * @return Lista con las propietarios
     */
    public Collection<Propietario> listar() {
        Query query = em.createQuery("SELECT p FROM Propietario p ");
        return (Collection<Propietario>) query.getResultList();
    }

    public Propietario buscarPorCedula(String cedula) {
        try {
            Query consulta = em.createQuery("Select p from Propietario p where p.cedula = ?1");
            consulta.setParameter(1, cedula);
            return (Propietario) consulta.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    public Propietario buscarPorEmail(String email) {
        try {
            Query consulta = em.createQuery("Select p from Propietario p where p.email = ?1");
            consulta.setParameter(1, email);
            return (Propietario) consulta.getResultList().get(0);
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
    public Propietario buscarPorUsuario(String usuario) {
        try {
            Query consulta = em.createQuery("Select p from Propietario p where p.usuario = ?1");
            consulta.setParameter(1, usuario);
            return (Propietario) consulta.getResultList().get(0);
        } catch (Exception ex) {
            System.out.println("Error!");
            return null;
        }
    }

    public Propietario buscarPorNombre(String nombre) {
        try {
            Query consulta = em.createQuery("Select p from Propietario p where p.nombre = ?1");
            consulta.setParameter(1, nombre);
            return (Propietario) consulta.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }
    }
}
