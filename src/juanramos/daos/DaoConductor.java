/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to editar this template
 */
package juanramos.daos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import juanramos.daos.exceptions.NonexistentEntityException;
import juanramos.daos.exceptions.PreexistingEntityException;
import juanramos.entidades.Bus;
import juanramos.entidades.Conductor;
import juanramos.entidades.Horario;

/**
 *
 * @author juand
 */
public class DaoConductor implements Serializable {

    public DaoConductor(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregar(Conductor conductor) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bus busesPlacaBus = conductor.getBusesPlacaBus();
            if (busesPlacaBus != null) {
                busesPlacaBus = em.getReference(busesPlacaBus.getClass(), busesPlacaBus.getPlacaBus());
                conductor.setBusesPlacaBus(busesPlacaBus);
            }
            Horario horariosIdJornada = conductor.getHorariosIdJornada();
            if (horariosIdJornada != null) {
                horariosIdJornada = em.getReference(horariosIdJornada.getClass(), horariosIdJornada.getIdJornada());
                conductor.setHorariosIdJornada(horariosIdJornada);
            }
            em.persist(conductor);
            if (busesPlacaBus != null) {
                busesPlacaBus.getConductorList().add(conductor);
                busesPlacaBus = em.merge(busesPlacaBus);
            }
            if (horariosIdJornada != null) {
                horariosIdJornada.getConductorList().add(conductor);
                horariosIdJornada = em.merge(horariosIdJornada);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscarConductor(conductor.getIdConductor()) != null) {
                throw new PreexistingEntityException("Conductor " + conductor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Conductor conductor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conductor persistentConductor = em.find(Conductor.class, conductor.getIdConductor());
            Bus busesPlacaBusOld = persistentConductor.getBusesPlacaBus();
            Bus busesPlacaBusNew = conductor.getBusesPlacaBus();
            Horario horariosIdJornadaOld = persistentConductor.getHorariosIdJornada();
            Horario horariosIdJornadaNew = conductor.getHorariosIdJornada();
            if (busesPlacaBusNew != null) {
                busesPlacaBusNew = em.getReference(busesPlacaBusNew.getClass(), busesPlacaBusNew.getPlacaBus());
                conductor.setBusesPlacaBus(busesPlacaBusNew);
            }
            if (horariosIdJornadaNew != null) {
                horariosIdJornadaNew = em.getReference(horariosIdJornadaNew.getClass(), horariosIdJornadaNew.getIdJornada());
                conductor.setHorariosIdJornada(horariosIdJornadaNew);
            }
            conductor = em.merge(conductor);
            if (busesPlacaBusOld != null && !busesPlacaBusOld.equals(busesPlacaBusNew)) {
                busesPlacaBusOld.getConductorList().remove(conductor);
                busesPlacaBusOld = em.merge(busesPlacaBusOld);
            }
            if (busesPlacaBusNew != null && !busesPlacaBusNew.equals(busesPlacaBusOld)) {
                busesPlacaBusNew.getConductorList().add(conductor);
                busesPlacaBusNew = em.merge(busesPlacaBusNew);
            }
            if (horariosIdJornadaOld != null && !horariosIdJornadaOld.equals(horariosIdJornadaNew)) {
                horariosIdJornadaOld.getConductorList().remove(conductor);
                horariosIdJornadaOld = em.merge(horariosIdJornadaOld);
            }
            if (horariosIdJornadaNew != null && !horariosIdJornadaNew.equals(horariosIdJornadaOld)) {
                horariosIdJornadaNew.getConductorList().add(conductor);
                horariosIdJornadaNew = em.merge(horariosIdJornadaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = conductor.getIdConductor();
                if (buscarConductor(id) == null) {
                    throw new NonexistentEntityException("The conductor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminar(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conductor conductor;
            try {
                conductor = em.getReference(Conductor.class, id);
                conductor.getIdConductor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conductor with id " + id + " no longer exists.", enfe);
            }
            Bus busesPlacaBus = conductor.getBusesPlacaBus();
            if (busesPlacaBus != null) {
                busesPlacaBus.getConductorList().remove(conductor);
                busesPlacaBus = em.merge(busesPlacaBus);
            }
            Horario horariosIdJornada = conductor.getHorariosIdJornada();
            if (horariosIdJornada != null) {
                horariosIdJornada.getConductorList().remove(conductor);
                horariosIdJornada = em.merge(horariosIdJornada);
            }
            em.remove(conductor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Conductor> listarTodosLosConductores() {
        return findConductorEntities(true, -1, -1);
    }

    public List<Conductor> findConductorEntities(int maxResults, int firstResult) {
        return findConductorEntities(false, maxResults, firstResult);
    }

    private List<Conductor> findConductorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Conductor.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Conductor buscarConductor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Conductor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalConductores() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Conductor> rt = cq.from(Conductor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
