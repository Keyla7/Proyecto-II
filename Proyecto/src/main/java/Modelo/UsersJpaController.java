/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

/**
 *
 * @author Keyla
 */
public class UsersJpaController implements Serializable {

    private EntityManagerFactory emf = null;
    List<Users> listaUsuarios;

    public UsersJpaController() {
        this.emf = Persistence.createEntityManagerFactory("Proyecto");
        this.listaUsuarios = consultarLista();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String create(Users users) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(users);
            em.getTransaction().commit();
            return "Usuario guardado con exito";
        } catch (Exception ex) {
            if (findUsers(users.getIdUser()) != null) {
                return "El usuario " + users + " ya existe";
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public String edit(Users users) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            users = em.merge(users);
            em.getTransaction().commit();
            return "Usuario modificado correctamente";
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getIdUser();
                if (findUsers(id) == null) {
                    return "El usuario con el id " + id + " no existe";
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public String destroy(Integer id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getIdUser();
            } catch (EntityNotFoundException enfe) {
                return "No se puede eliminar, no se encuentra el usuario con el id " + id;
            }
            em.remove(users);
            em.getTransaction().commit();
            return "Usuario eliminado con exito";
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Users> consultarLista() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
            Query q = em.createQuery(cq);

            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public String[][] getMatrizUsuarios() {
        this.listaUsuarios = consultarLista();
        String[][] matrizUser = new String[this.listaUsuarios.size()][Users.LISTA_USUARIOS.length];
        for (int i = 0; i < matrizUser.length; i++) //filas
        {
            for (int j = 0; j < matrizUser[0].length; j++) //columnas
            {
                matrizUser[i][j] = this.listaUsuarios.get(i).getDatosU(j);
            }
        }
        return matrizUser;
    }

    public boolean verificacionU(Users user) throws IOException {
        List<Users> usuariosRegistrados = listaUsuarios;
        for (Users u : usuariosRegistrados) {
            if (u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())) {
                if (u.getProfile().equals("Admin")) {
                    return true;
                }
                JOptionPane.showMessageDialog(null, "No tiene acceso");
                return false;
            }
        }
        JOptionPane.showMessageDialog(null, "ERROR: Datos incorrectos");
        return false;
    }
}
