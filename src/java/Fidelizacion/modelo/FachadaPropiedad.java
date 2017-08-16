/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fidelizacion.modelo;

import Fidelizacion.entidades.Propiedad;
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
public class FachadaPropiedad {

    @PersistenceContext(unitName = "AdministratePU")
    private EntityManager em;

    /**
     * Adiciona un propiedad. Si la propiedad ya existe lanza una excepcion
     *
     * @param propiedad Propiedad a adicionar
     */
    public void adicionar(Propiedad propiedad) {
        em.persist(propiedad);
    }

    /**
     * Actualiza un propiedad. Actualiza los datos del propiedad
     *
     * @param propiedad Propiedad a adicionar
     */
    public void actualizar(Propiedad propiedad) {
        em.merge(propiedad);
    }

    /**
     * Elimina la propiedad .
     *
     * @param propiedad Propiedad a eliminar
     */
    public void eliminar(Propiedad propiedad) {
        em.remove(em.merge(propiedad));
    }

    /**
     * Busca la propiedad con el nombre dado. Retorna null si no existe
     *
     * @param nombre Nombre de la propiedad
     * @return null si la propiedad no existe o el objeto propiedad
     */
    public Propiedad buscar(String nombre) {
        return em.find(Propiedad.class, nombre);
    }

    public Propiedad buscarPorDescripcion(String descripcion) {
        try {
            Query consulta = em.createQuery("Select p from Propiedad p where p.descripcion = ?1");
            consulta.setParameter(1, descripcion);
            return (Propiedad) consulta.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Retorna TODAS las propiedad que se han almacenado
     *
     * @return Lista con las propiedads
     */
    public Collection<Propiedad> listar() {
        Query query = em.createQuery("SELECT p FROM Propiedad p ");
        return (Collection<Propiedad>) query.getResultList();
    }

    public int contarPropietarios(int propietario) {
        try {
            Query consulta = em.createQuery("select p count(*) FROM Propiedad p WHERE p.idPropietario=1?");
            consulta.setParameter(1, propietario);
            return (int) consulta.getResultList().get(0);
        } catch (Exception ex) {
            return 0;
        }
    }
}
