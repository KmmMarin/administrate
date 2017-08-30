package Fidelizacion.modelo;

import Fidelizacion.entidades.Ingreso;
import Fidelizacion.entidades.Propietario;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kavantic
 */
@Stateless
public class FachadaIngreso {
    @PersistenceContext(unitName="AdministratePU")
    private EntityManager em;
    
    /**
     * Adiciona un ingreso.
     * Si el ingreso ya existe lanza una excepcion 
     * 
     * @param ingreso Ingreso a adicionar 
     */
    public void adicionar(Ingreso ingreso) {        
        em.persist(ingreso);
    }
    
    /**
     * Actualiza un ingreso.
     * Actualiza los datos del ingreso 
     * 
     * @param ingreso Ingreso a adicionar 
     */
    public void actualizar(Ingreso ingreso) {        
        em.merge(ingreso);
    }

    /**
     * Elimina el ingreso .
     * 
     * @param ingreso Ingreso a eliminar 
     */
    public void eliminar(Ingreso ingreso) {        
        em.remove(em.merge(ingreso));
    }
    
    /**
     * Busca el ingreso con el nombre dado. 
     * Retorna null si no existe 
     * 
     * @param nombre Nombre de el ingreso 
     * @return null si el ingreso no existe o el objeto ingreso 
     */
    public Ingreso buscar(String nombre) {
        return em.find(Ingreso.class, nombre);
    }
    
    /**
     * Retorna TODOS los ingreso que se han almacenado 
     * 
     * @return Lista con los ingresoes 
     */
    public Collection<Ingreso> listar() {        
        Query query = em.createQuery("SELECT i FROM Ingreso i ");
        return (Collection<Ingreso>) query.getResultList();        
    }
    
    /**
     * Retorna TODOS los ingresos que se han almacenado a un pagador en específico
     * 
     * @param propietario
     * @return Lista con los ingresoes 
     */
    public Collection<Ingreso> listaPorPagador(int propietario) {        
        try {
            Query consulta = em.createQuery("select i FROM Ingreso i WHERE i.pagador=1?");
            consulta.setParameter(1, propietario);
            return (Collection<Ingreso>) consulta.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }       
    }
}
