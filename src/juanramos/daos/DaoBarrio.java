/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to editar this template
 */
package juanramos.daos;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import juanramos.entidades.Bus;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import juanramos.daos.exceptions.IllegalOrphanException;
import juanramos.daos.exceptions.NonexistentEntityException;
import juanramos.daos.exceptions.PreexistingEntityException;
import juanramos.entidades.Barrio;
import juanramos.entidades.Estudiante;

/**
 *
 * @author juand
 */
public class DaoBarrio implements Serializable {

    public DaoBarrio(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregar(Barrio barrio) throws PreexistingEntityException, Exception {
        if (barrio.getBusList() == null) {
            barrio.setBusList(new ArrayList<Bus>());
        }
        if (barrio.getEstudianteList() == null) {
            barrio.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Bus> attachedBusList = new ArrayList<Bus>();
            for (Bus busListBusToAttach : barrio.getBusList()) {
                busListBusToAttach = em.getReference(busListBusToAttach.getClass(), busListBusToAttach.getPlacaBus());
                attachedBusList.add(busListBusToAttach);
            }
            barrio.setBusList(attachedBusList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : barrio.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            barrio.setEstudianteList(attachedEstudianteList);
            em.persist(barrio);
            for (Bus busListBus : barrio.getBusList()) {
                Barrio oldBarriosidBarrioOfBusListBus = busListBus.getBarriosidBarrio();
                busListBus.setBarriosidBarrio(barrio);
                busListBus = em.merge(busListBus);
                if (oldBarriosidBarrioOfBusListBus != null) {
                    oldBarriosidBarrioOfBusListBus.getBusList().remove(busListBus);
                    oldBarriosidBarrioOfBusListBus = em.merge(oldBarriosidBarrioOfBusListBus);
                }
            }
            for (Estudiante estudianteListEstudiante : barrio.getEstudianteList()) {
                Barrio oldBarriosidBarrioOfEstudianteListEstudiante = estudianteListEstudiante.getBarriosidBarrio();
                estudianteListEstudiante.setBarriosidBarrio(barrio);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldBarriosidBarrioOfEstudianteListEstudiante != null) {
                    oldBarriosidBarrioOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldBarriosidBarrioOfEstudianteListEstudiante = em.merge(oldBarriosidBarrioOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscarBarrio(barrio.getIdBarrio()) != null) {
                throw new PreexistingEntityException("Barrio " + barrio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Barrio barrio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barrio persistentBarrio = em.find(Barrio.class, barrio.getIdBarrio());
            List<Bus> busListOld = persistentBarrio.getBusList();
            List<Bus> busListNew = barrio.getBusList();
            List<Estudiante> estudianteListOld = persistentBarrio.getEstudianteList();
            List<Estudiante> estudianteListNew = barrio.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Bus busListOldBus : busListOld) {
                if (!busListNew.contains(busListOldBus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bus " + busListOldBus + " since its barriosidBarrio field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its barriosidBarrio field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Bus> attachedBusListNew = new ArrayList<Bus>();
            for (Bus busListNewBusToAttach : busListNew) {
                busListNewBusToAttach = em.getReference(busListNewBusToAttach.getClass(), busListNewBusToAttach.getPlacaBus());
                attachedBusListNew.add(busListNewBusToAttach);
            }
            busListNew = attachedBusListNew;
            barrio.setBusList(busListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            barrio.setEstudianteList(estudianteListNew);
            barrio = em.merge(barrio);
            for (Bus busListNewBus : busListNew) {
                if (!busListOld.contains(busListNewBus)) {
                    Barrio oldBarriosidBarrioOfBusListNewBus = busListNewBus.getBarriosidBarrio();
                    busListNewBus.setBarriosidBarrio(barrio);
                    busListNewBus = em.merge(busListNewBus);
                    if (oldBarriosidBarrioOfBusListNewBus != null && !oldBarriosidBarrioOfBusListNewBus.equals(barrio)) {
                        oldBarriosidBarrioOfBusListNewBus.getBusList().remove(busListNewBus);
                        oldBarriosidBarrioOfBusListNewBus = em.merge(oldBarriosidBarrioOfBusListNewBus);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Barrio oldBarriosidBarrioOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getBarriosidBarrio();
                    estudianteListNewEstudiante.setBarriosidBarrio(barrio);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldBarriosidBarrioOfEstudianteListNewEstudiante != null && !oldBarriosidBarrioOfEstudianteListNewEstudiante.equals(barrio)) {
                        oldBarriosidBarrioOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldBarriosidBarrioOfEstudianteListNewEstudiante = em.merge(oldBarriosidBarrioOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = barrio.getIdBarrio();
                if (buscarBarrio(id) == null) {
                    throw new NonexistentEntityException("The barrio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminar(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barrio barrio;
            try {
                barrio = em.getReference(Barrio.class, id);
                barrio.getIdBarrio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The barrio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Bus> busListOrphanCheck = barrio.getBusList();
            for (Bus busListOrphanCheckBus : busListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Barrio (" + barrio + ") cannot be destroyed since the Bus " + busListOrphanCheckBus + " in its busList field has a non-nullable barriosidBarrio field.");
            }
            List<Estudiante> estudianteListOrphanCheck = barrio.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Barrio (" + barrio + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable barriosidBarrio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(barrio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Barrio> listarTodosLosBarrios() {
        return findBarrioEntities(true, -1, -1);
    }

    public List<Barrio> findBarrioEntities(int maxResults, int firstResult) {
        return findBarrioEntities(false, maxResults, firstResult);
    }

    private List<Barrio> findBarrioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Barrio.class));
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

    public Barrio buscarBarrio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Barrio.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalBarrios() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Barrio> rt = cq.from(Barrio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
