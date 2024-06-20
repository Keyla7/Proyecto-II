/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Keyla
 */
public class UserJpaController implements Serializable {
   
    private EntityManagerFactory emf = null;
    List<User> listaUsuarios;
    
    public UserJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ucr_Proyecto_jar_1.0-SNAPSHOTPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String create(User usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            return "Guardado";
        } catch (Exception ex) {
            if (findUser(usuario.getUser()) != null) {
                return "El nombre de usuario " + usuario + " ya existe";
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public String edit(User usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
            return "Modificado correctamente";
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String user = usuario.getUser();
                if (findUser(user) == null) {
                    return "El nombre de usuario " + user + " no existe";
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public String destroy(String user) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User usuario;
            try {
                usuario = em.getReference(User.class, user);
                usuario.getUser();
            } catch (EntityNotFoundException enfe) {
                return "No se puede eliminar, no se encuentra este nombre de usuario " + user;
            }
            em.remove(usuario);
            em.getTransaction().commit();
            return "Eliminado con exito";
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> consultarLista() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
            Query q = em.createQuery(cq);

            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public User findUser(String user) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, user);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<User> rt = cq.from(User.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
