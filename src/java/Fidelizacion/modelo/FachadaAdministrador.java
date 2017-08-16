package Fidelizacion.modelo;

import Fidelizacion.entidades.Administrador;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Desarrollo
 */
@Stateless
public class FachadaAdministrador {
    @PersistenceContext(unitName="AdministratePU")
    private EntityManager em;
    
    public Administrador buscar(String usuario, String contraseña) {
        try {
            Query consulta = em.createQuery("Select a from Administrador a where a.usuario = ?1 and a.contraseña = ?2");
            consulta.setParameter(1, usuario);
            consulta.setParameter(2, contraseña);
            return (Administrador) consulta.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }
    }
    /**
     * Adiciona un administrador.
     * Si el administrador ya existe lanza una excepcion 
     * 
     * @param administrador Administrador a adicionar 
     */
    public void adicionar(Administrador administrador) {        
        em.persist(administrador);
    }
    
    /**
     * Actualiza un administrador.
     * Actualiza los datos del administrador 
     * 
     * @param administrador Administrador a adicionar 
     */
    public void actualizar(Administrador administrador) {        
        em.merge(administrador);
    }

    /**
     * Elimina el administrador .
     * 
     * @param administrador Administrador a eliminar 
     */
    public void eliminar(Administrador administrador) {        
        em.remove(em.merge(administrador));
    }
    
    /**
     * Busca el administrador con el nombre dado. 
     * Retorna null si no existe 
     * 
     * @param nombre Nombre de el administrador 
     * @return null si el administrador no existe o el objeto administrador 
     */
    public Administrador buscar(String nombre) {
        return em.find(Administrador.class, nombre);
    }
    
    /**
     * Busca el administrador con el email dado. 
     * Retorna null si no existe 
     * 
     * @param email Email de el administrador 
     * @return null si el administrador no existe o el objeto administrador 
     */
    public Administrador buscarPorEmail(String email) {
        try {
            Query consulta = em.createQuery("Select a from Administrador a where a.email = ?1");
            consulta.setParameter(1, email);
            return (Administrador) consulta.getResultList().get(0);
        } catch (Exception ex) {
            System.out.println("Error!");
            return null;
        }
    }

    /**
     * Retorna TODOS los administrador que se han almacenado 
     * 
     * @return Lista con los administradores 
     */
    public Collection<Administrador> listar() {        
        Query query = em.createQuery("SELECT a FROM Administrador a ");
        return (Collection<Administrador>) query.getResultList();        
    }
}
