package Fidelizacion.modelo;

import Fidelizacion.entidades.Ph;
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
public class FachadaPH {
    @PersistenceContext(unitName = "AdministratePU")
    private EntityManager em;

    /**
     * Adiciona un ph. Si la ph ya existe lanza una excepcion
     *
     * @param ph a adicionar
     */
    public void adicionar(Ph ph) {
        em.persist(ph);
    }

    /**
     * Actualiza un ph. Actualiza los datos del ph
     *
     * @param ph Ph a adicionar
     */
    public void actualizar(Ph ph) {
        em.merge(ph);
    }

    /**
     * Elimina la ph .
     *
     * @param ph Ph a eliminar
     */
    public void eliminar(Ph ph) {
        em.remove(em.merge(ph));
    }

    /**
     * Busca la ph con el nombre dado. Retorna null si no existe
     *
     * @param nombre Nombre de la ph
     * @return null si la ph no existe o el objeto ph
     */
    public Ph buscar(String nombre) {
        return em.find(Ph.class, nombre);
    }

    /**
     * Retorna TODAS las ph que se han almacenado
     *
     * @return Lista con las ph
     */
    public Collection<Ph> listar() {
        Query query = em.createQuery("SELECT p FROM Ph p ");
        return (Collection<Ph>) query.getResultList();
    }
}
