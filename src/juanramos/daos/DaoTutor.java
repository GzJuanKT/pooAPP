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
import juanramos.entidades.Horario;
import juanramos.entidades.Tutor;

/**
 *
 * @author juand
 */
public class DaoTutor implements Serializable {

    public DaoTutor(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregar(Tutor tutor) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bus busesPlacaBus = tutor.getBusesPlacaBus();
            if (busesPlacaBus != null) {
                busesPlacaBus = em.getReference(busesPlacaBus.getClass(), busesPlacaBus.getPlacaBus());
                tutor.setBusesPlacaBus(busesPlacaBus);
            }
            Horario horariosIdJornada = tutor.getHorariosIdJornada();
            if (horariosIdJornada != null) {
                horariosIdJornada = em.getReference(horariosIdJornada.getClass(), horariosIdJornada.getIdJornada());
                tutor.setHorariosIdJornada(horariosIdJornada);
            }
            em.persist(tutor);
            if (busesPlacaBus != null) {
                busesPlacaBus.getTutorList().add(tutor);
                busesPlacaBus = em.merge(busesPlacaBus);
            }
            if (horariosIdJornada != null) {
                horariosIdJornada.getTutorList().add(tutor);
                horariosIdJornada = em.merge(horariosIdJornada);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscarTutor(tutor.getIdTutor()) != null) {
                throw new PreexistingEntityException("Tutor " + tutor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Tutor tutor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tutor persistentTutor = em.find(Tutor.class, tutor.getIdTutor());
            Bus busesPlacaBusOld = persistentTutor.getBusesPlacaBus();
            Bus busesPlacaBusNew = tutor.getBusesPlacaBus();
            Horario horariosIdJornadaOld = persistentTutor.getHorariosIdJornada();
            Horario horariosIdJornadaNew = tutor.getHorariosIdJornada();
            if (busesPlacaBusNew != null) {
                busesPlacaBusNew = em.getReference(busesPlacaBusNew.getClass(), busesPlacaBusNew.getPlacaBus());
                tutor.setBusesPlacaBus(busesPlacaBusNew);
            }
            if (horariosIdJornadaNew != null) {
                horariosIdJornadaNew = em.getReference(horariosIdJornadaNew.getClass(), horariosIdJornadaNew.getIdJornada());
                tutor.setHorariosIdJornada(horariosIdJornadaNew);
            }
            tutor = em.merge(tutor);
            if (busesPlacaBusOld != null && !busesPlacaBusOld.equals(busesPlacaBusNew)) {
                busesPlacaBusOld.getTutorList().remove(tutor);
                busesPlacaBusOld = em.merge(busesPlacaBusOld);
            }
            if (busesPlacaBusNew != null && !busesPlacaBusNew.equals(busesPlacaBusOld)) {
                busesPlacaBusNew.getTutorList().add(tutor);
                busesPlacaBusNew = em.merge(busesPlacaBusNew);
            }
            if (horariosIdJornadaOld != null && !horariosIdJornadaOld.equals(horariosIdJornadaNew)) {
                horariosIdJornadaOld.getTutorList().remove(tutor);
                horariosIdJornadaOld = em.merge(horariosIdJornadaOld);
            }
            if (horariosIdJornadaNew != null && !horariosIdJornadaNew.equals(horariosIdJornadaOld)) {
                horariosIdJornadaNew.getTutorList().add(tutor);
                horariosIdJornadaNew = em.merge(horariosIdJornadaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tutor.getIdTutor();
                if (buscarTutor(id) == null) {
                    throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.");
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
            Tutor tutor;
            try {
                tutor = em.getReference(Tutor.class, id);
                tutor.getIdTutor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.", enfe);
            }
            Bus busesPlacaBus = tutor.getBusesPlacaBus();
            if (busesPlacaBus != null) {
                busesPlacaBus.getTutorList().remove(tutor);
                busesPlacaBus = em.merge(busesPlacaBus);
            }
            Horario horariosIdJornada = tutor.getHorariosIdJornada();
            if (horariosIdJornada != null) {
                horariosIdJornada.getTutorList().remove(tutor);
                horariosIdJornada = em.merge(horariosIdJornada);
            }
            em.remove(tutor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tutor> listarTodosLosTutores() {
        return findTutorEntities(true, -1, -1);
    }

    public List<Tutor> findTutorEntities(int maxResults, int firstResult) {
        return findTutorEntities(false, maxResults, firstResult);
    }

    private List<Tutor> findTutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tutor.class));
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

    public Tutor buscarTutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tutor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalTutores() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tutor> rt = cq.from(Tutor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
