package Fidelizacion.modelo;

import Fidelizacion.entidades.Administrador;
import Fidelizacion.entidades.Propietario;
import Fidelizacion.entidades.Residente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kavantic S.A.S
 */
@Stateless
public class FachadaSesion {

    @PersistenceContext(unitName = "AdministratePU")
    private EntityManager em;
    Administrador admin;

    public Administrador sesionAdminsitrador(String usuario, String contraseña) {
        try {
            Query consulta = em.createQuery("Select a from Administrador a where a.usuario = ?1 and a.contraseña = ?2");
            consulta.setParameter(1, usuario);
            consulta.setParameter(2, contraseña);
            return (Administrador) consulta.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    public Propietario sesionPropietario(String usuario, String contraseña) {
        try {
            Query consulta = em.createQuery("Select p from Propietario p where p.usuario = ?1 and p.contraseña = ?2");
            consulta.setParameter(1, usuario);
            consulta.setParameter(2, contraseña);
            return (Propietario) consulta.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    public Residente sesionResidente(String usuario, String contraseña) {
        try {
            Query consulta = em.createQuery("Select r from Residente r where r.usuario = ?1 and r.contraseña = ?2");
            consulta.setParameter(1, usuario);
            consulta.setParameter(2, contraseña);
            return (Residente) consulta.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }
    }
}
