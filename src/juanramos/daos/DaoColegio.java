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
import juanramos.entidades.Colegio;
import juanramos.entidades.Estudiante;

/**
 *
 * @author juand
 */
public class DaoColegio implements Serializable {

    public DaoColegio(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregar(Colegio colegio) throws PreexistingEntityException, Exception {
        if (colegio.getBusList() == null) {
            colegio.setBusList(new ArrayList<Bus>());
        }
        if (colegio.getEstudianteList() == null) {
            colegio.setEstudianteList(new ArrayList<Estudiante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Bus> attachedBusList = new ArrayList<Bus>();
            for (Bus busListBusToAttach : colegio.getBusList()) {
                busListBusToAttach = em.getReference(busListBusToAttach.getClass(), busListBusToAttach.getPlacaBus());
                attachedBusList.add(busListBusToAttach);
            }
            colegio.setBusList(attachedBusList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : colegio.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            colegio.setEstudianteList(attachedEstudianteList);
            em.persist(colegio);
            for (Bus busListBus : colegio.getBusList()) {
                Colegio oldColegiosidColegioOfBusListBus = busListBus.getColegiosidColegio();
                busListBus.setColegiosidColegio(colegio);
                busListBus = em.merge(busListBus);
                if (oldColegiosidColegioOfBusListBus != null) {
                    oldColegiosidColegioOfBusListBus.getBusList().remove(busListBus);
                    oldColegiosidColegioOfBusListBus = em.merge(oldColegiosidColegioOfBusListBus);
                }
            }
            for (Estudiante estudianteListEstudiante : colegio.getEstudianteList()) {
                Colegio oldColegiosidColegioOfEstudianteListEstudiante = estudianteListEstudiante.getColegiosidColegio();
                estudianteListEstudiante.setColegiosidColegio(colegio);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldColegiosidColegioOfEstudianteListEstudiante != null) {
                    oldColegiosidColegioOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldColegiosidColegioOfEstudianteListEstudiante = em.merge(oldColegiosidColegioOfEstudianteListEstudiante);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscarColegio(colegio.getIdColegio()) != null) {
                throw new PreexistingEntityException("Colegio " + colegio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Colegio colegio) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Colegio persistentColegio = em.find(Colegio.class, colegio.getIdColegio());
            List<Bus> busListOld = persistentColegio.getBusList();
            List<Bus> busListNew = colegio.getBusList();
            List<Estudiante> estudianteListOld = persistentColegio.getEstudianteList();
            List<Estudiante> estudianteListNew = colegio.getEstudianteList();
            List<String> illegalOrphanMessages = null;
            for (Bus busListOldBus : busListOld) {
                if (!busListNew.contains(busListOldBus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bus " + busListOldBus + " since its colegiosidColegio field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its colegiosidColegio field is not nullable.");
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
            colegio.setBusList(busListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            colegio.setEstudianteList(estudianteListNew);
            colegio = em.merge(colegio);
            for (Bus busListNewBus : busListNew) {
                if (!busListOld.contains(busListNewBus)) {
                    Colegio oldColegiosidColegioOfBusListNewBus = busListNewBus.getColegiosidColegio();
                    busListNewBus.setColegiosidColegio(colegio);
                    busListNewBus = em.merge(busListNewBus);
                    if (oldColegiosidColegioOfBusListNewBus != null && !oldColegiosidColegioOfBusListNewBus.equals(colegio)) {
                        oldColegiosidColegioOfBusListNewBus.getBusList().remove(busListNewBus);
                        oldColegiosidColegioOfBusListNewBus = em.merge(oldColegiosidColegioOfBusListNewBus);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Colegio oldColegiosidColegioOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getColegiosidColegio();
                    estudianteListNewEstudiante.setColegiosidColegio(colegio);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldColegiosidColegioOfEstudianteListNewEstudiante != null && !oldColegiosidColegioOfEstudianteListNewEstudiante.equals(colegio)) {
                        oldColegiosidColegioOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldColegiosidColegioOfEstudianteListNewEstudiante = em.merge(oldColegiosidColegioOfEstudianteListNewEstudiante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = colegio.getIdColegio();
                if (buscarColegio(id) == null) {
                    throw new NonexistentEntityException("The colegio with id " + id + " no longer exists.");
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
            Colegio colegio;
            try {
                colegio = em.getReference(Colegio.class, id);
                colegio.getIdColegio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The colegio with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Bus> busListOrphanCheck = colegio.getBusList();
            for (Bus busListOrphanCheckBus : busListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Colegio (" + colegio + ") cannot be destroyed since the Bus " + busListOrphanCheckBus + " in its busList field has a non-nullable colegiosidColegio field.");
            }
            List<Estudiante> estudianteListOrphanCheck = colegio.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Colegio (" + colegio + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable colegiosidColegio field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(colegio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Colegio> listarTodosLosColegios() {
        return findColegioEntities(true, -1, -1);
    }

    public List<Colegio> findColegioEntities(int maxResults, int firstResult) {
        return findColegioEntities(false, maxResults, firstResult);
    }

    private List<Colegio> findColegioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Colegio.class));
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

    public Colegio buscarColegio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Colegio.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalColegios() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Colegio> rt = cq.from(Colegio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
