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
import juanramos.entidades.Tutor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import juanramos.daos.exceptions.IllegalOrphanException;
import juanramos.daos.exceptions.NonexistentEntityException;
import juanramos.daos.exceptions.PreexistingEntityException;
import juanramos.entidades.Bus;
import juanramos.entidades.Colegio;
import juanramos.entidades.Estudiante;
import juanramos.entidades.Conductor;
import juanramos.entidades.Horario;

/**
 *
 * @author juand
 */
public class DaoHorario implements Serializable {

    public DaoHorario(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregar(Horario horario) throws PreexistingEntityException, Exception {
        if (horario.getTutorList() == null) {
            horario.setTutorList(new ArrayList<Tutor>());
        }
        if (horario.getBusList() == null) {
            horario.setBusList(new ArrayList<Bus>());
        }
        if (horario.getEstudianteList() == null) {
            horario.setEstudianteList(new ArrayList<Estudiante>());
        }
        if (horario.getConductorList() == null) {
            horario.setConductorList(new ArrayList<Conductor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tutor> attachedTutorList = new ArrayList<Tutor>();
            for (Tutor tutorListTutorToAttach : horario.getTutorList()) {
                tutorListTutorToAttach = em.getReference(tutorListTutorToAttach.getClass(), tutorListTutorToAttach.getIdTutor());
                attachedTutorList.add(tutorListTutorToAttach);
            }
            horario.setTutorList(attachedTutorList);
            List<Bus> attachedBusList = new ArrayList<Bus>();
            for (Bus busListBusToAttach : horario.getBusList()) {
                busListBusToAttach = em.getReference(busListBusToAttach.getClass(), busListBusToAttach.getPlacaBus());
                attachedBusList.add(busListBusToAttach);
            }
            horario.setBusList(attachedBusList);
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : horario.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            horario.setEstudianteList(attachedEstudianteList);
            List<Conductor> attachedConductorList = new ArrayList<Conductor>();
            for (Conductor conductorListConductorToAttach : horario.getConductorList()) {
                conductorListConductorToAttach = em.getReference(conductorListConductorToAttach.getClass(), conductorListConductorToAttach.getIdConductor());
                attachedConductorList.add(conductorListConductorToAttach);
            }
            horario.setConductorList(attachedConductorList);
            em.persist(horario);
            for (Tutor tutorListTutor : horario.getTutorList()) {
                Horario oldHorariosIdJornadaOfTutorListTutor = tutorListTutor.getHorariosIdJornada();
                tutorListTutor.setHorariosIdJornada(horario);
                tutorListTutor = em.merge(tutorListTutor);
                if (oldHorariosIdJornadaOfTutorListTutor != null) {
                    oldHorariosIdJornadaOfTutorListTutor.getTutorList().remove(tutorListTutor);
                    oldHorariosIdJornadaOfTutorListTutor = em.merge(oldHorariosIdJornadaOfTutorListTutor);
                }
            }
            for (Bus busListBus : horario.getBusList()) {
                Horario oldHorariosIdJornadaOfBusListBus = busListBus.getHorariosIdJornada();
                busListBus.setHorariosIdJornada(horario);
                busListBus = em.merge(busListBus);
                if (oldHorariosIdJornadaOfBusListBus != null) {
                    oldHorariosIdJornadaOfBusListBus.getBusList().remove(busListBus);
                    oldHorariosIdJornadaOfBusListBus = em.merge(oldHorariosIdJornadaOfBusListBus);
                }
            }
            for (Estudiante estudianteListEstudiante : horario.getEstudianteList()) {
                Horario oldHorariosIdJornadaOfEstudianteListEstudiante = estudianteListEstudiante.getHorariosIdJornada();
                estudianteListEstudiante.setHorariosIdJornada(horario);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldHorariosIdJornadaOfEstudianteListEstudiante != null) {
                    oldHorariosIdJornadaOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldHorariosIdJornadaOfEstudianteListEstudiante = em.merge(oldHorariosIdJornadaOfEstudianteListEstudiante);
                }
            }
            for (Conductor conductorListConductor : horario.getConductorList()) {
                Horario oldHorariosIdJornadaOfConductorListConductor = conductorListConductor.getHorariosIdJornada();
                conductorListConductor.setHorariosIdJornada(horario);
                conductorListConductor = em.merge(conductorListConductor);
                if (oldHorariosIdJornadaOfConductorListConductor != null) {
                    oldHorariosIdJornadaOfConductorListConductor.getConductorList().remove(conductorListConductor);
                    oldHorariosIdJornadaOfConductorListConductor = em.merge(oldHorariosIdJornadaOfConductorListConductor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscarHorario(horario.getIdJornada()) != null) {
                throw new PreexistingEntityException("Horario " + horario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Horario horario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Horario persistentHorario = em.find(Horario.class, horario.getIdJornada());
            List<Tutor> tutorListOld = persistentHorario.getTutorList();
            List<Tutor> tutorListNew = horario.getTutorList();
            List<Bus> busListOld = persistentHorario.getBusList();
            List<Bus> busListNew = horario.getBusList();
            List<Estudiante> estudianteListOld = persistentHorario.getEstudianteList();
            List<Estudiante> estudianteListNew = horario.getEstudianteList();
            List<Conductor> conductorListOld = persistentHorario.getConductorList();
            List<Conductor> conductorListNew = horario.getConductorList();
            List<String> illegalOrphanMessages = null;
            for (Tutor tutorListOldTutor : tutorListOld) {
                if (!tutorListNew.contains(tutorListOldTutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tutor " + tutorListOldTutor + " since its horariosIdJornada field is not nullable.");
                }
            }
            for (Bus busListOldBus : busListOld) {
                if (!busListNew.contains(busListOldBus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bus " + busListOldBus + " since its horariosIdJornada field is not nullable.");
                }
            }
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its horariosIdJornada field is not nullable.");
                }
            }
            for (Conductor conductorListOldConductor : conductorListOld) {
                if (!conductorListNew.contains(conductorListOldConductor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Conductor " + conductorListOldConductor + " since its horariosIdJornada field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Tutor> attachedTutorListNew = new ArrayList<Tutor>();
            for (Tutor tutorListNewTutorToAttach : tutorListNew) {
                tutorListNewTutorToAttach = em.getReference(tutorListNewTutorToAttach.getClass(), tutorListNewTutorToAttach.getIdTutor());
                attachedTutorListNew.add(tutorListNewTutorToAttach);
            }
            tutorListNew = attachedTutorListNew;
            horario.setTutorList(tutorListNew);
            List<Bus> attachedBusListNew = new ArrayList<Bus>();
            for (Bus busListNewBusToAttach : busListNew) {
                busListNewBusToAttach = em.getReference(busListNewBusToAttach.getClass(), busListNewBusToAttach.getPlacaBus());
                attachedBusListNew.add(busListNewBusToAttach);
            }
            busListNew = attachedBusListNew;
            horario.setBusList(busListNew);
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getIdEstudiantes());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            horario.setEstudianteList(estudianteListNew);
            List<Conductor> attachedConductorListNew = new ArrayList<Conductor>();
            for (Conductor conductorListNewConductorToAttach : conductorListNew) {
                conductorListNewConductorToAttach = em.getReference(conductorListNewConductorToAttach.getClass(), conductorListNewConductorToAttach.getIdConductor());
                attachedConductorListNew.add(conductorListNewConductorToAttach);
            }
            conductorListNew = attachedConductorListNew;
            horario.setConductorList(conductorListNew);
            horario = em.merge(horario);
            for (Tutor tutorListNewTutor : tutorListNew) {
                if (!tutorListOld.contains(tutorListNewTutor)) {
                    Horario oldHorariosIdJornadaOfTutorListNewTutor = tutorListNewTutor.getHorariosIdJornada();
                    tutorListNewTutor.setHorariosIdJornada(horario);
                    tutorListNewTutor = em.merge(tutorListNewTutor);
                    if (oldHorariosIdJornadaOfTutorListNewTutor != null && !oldHorariosIdJornadaOfTutorListNewTutor.equals(horario)) {
                        oldHorariosIdJornadaOfTutorListNewTutor.getTutorList().remove(tutorListNewTutor);
                        oldHorariosIdJornadaOfTutorListNewTutor = em.merge(oldHorariosIdJornadaOfTutorListNewTutor);
                    }
                }
            }
            for (Bus busListNewBus : busListNew) {
                if (!busListOld.contains(busListNewBus)) {
                    Horario oldHorariosIdJornadaOfBusListNewBus = busListNewBus.getHorariosIdJornada();
                    busListNewBus.setHorariosIdJornada(horario);
                    busListNewBus = em.merge(busListNewBus);
                    if (oldHorariosIdJornadaOfBusListNewBus != null && !oldHorariosIdJornadaOfBusListNewBus.equals(horario)) {
                        oldHorariosIdJornadaOfBusListNewBus.getBusList().remove(busListNewBus);
                        oldHorariosIdJornadaOfBusListNewBus = em.merge(oldHorariosIdJornadaOfBusListNewBus);
                    }
                }
            }
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Horario oldHorariosIdJornadaOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getHorariosIdJornada();
                    estudianteListNewEstudiante.setHorariosIdJornada(horario);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldHorariosIdJornadaOfEstudianteListNewEstudiante != null && !oldHorariosIdJornadaOfEstudianteListNewEstudiante.equals(horario)) {
                        oldHorariosIdJornadaOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldHorariosIdJornadaOfEstudianteListNewEstudiante = em.merge(oldHorariosIdJornadaOfEstudianteListNewEstudiante);
                    }
                }
            }
            for (Conductor conductorListNewConductor : conductorListNew) {
                if (!conductorListOld.contains(conductorListNewConductor)) {
                    Horario oldHorariosIdJornadaOfConductorListNewConductor = conductorListNewConductor.getHorariosIdJornada();
                    conductorListNewConductor.setHorariosIdJornada(horario);
                    conductorListNewConductor = em.merge(conductorListNewConductor);
                    if (oldHorariosIdJornadaOfConductorListNewConductor != null && !oldHorariosIdJornadaOfConductorListNewConductor.equals(horario)) {
                        oldHorariosIdJornadaOfConductorListNewConductor.getConductorList().remove(conductorListNewConductor);
                        oldHorariosIdJornadaOfConductorListNewConductor = em.merge(oldHorariosIdJornadaOfConductorListNewConductor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = horario.getIdJornada();
                if (buscarHorario(id) == null) {
                    throw new NonexistentEntityException("The horario with id " + id + " no longer exists.");
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
            Horario horario;
            try {
                horario = em.getReference(Horario.class, id);
                horario.getIdJornada();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The horario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tutor> tutorListOrphanCheck = horario.getTutorList();
            for (Tutor tutorListOrphanCheckTutor : tutorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Horario (" + horario + ") cannot be destroyed since the Tutor " + tutorListOrphanCheckTutor + " in its tutorList field has a non-nullable horariosIdJornada field.");
            }
            List<Bus> busListOrphanCheck = horario.getBusList();
            for (Bus busListOrphanCheckBus : busListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Horario (" + horario + ") cannot be destroyed since the Bus " + busListOrphanCheckBus + " in its busList field has a non-nullable horariosIdJornada field.");
            }
            List<Estudiante> estudianteListOrphanCheck = horario.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Horario (" + horario + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable horariosIdJornada field.");
            }
            List<Conductor> conductorListOrphanCheck = horario.getConductorList();
            for (Conductor conductorListOrphanCheckConductor : conductorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Horario (" + horario + ") cannot be destroyed since the Conductor " + conductorListOrphanCheckConductor + " in its conductorList field has a non-nullable horariosIdJornada field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(horario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Horario> listarTodosLosHorarios() {
        return findHorarioEntities(true, -1, -1);
    }

    public List<Horario> findHorarioEntities(int maxResults, int firstResult) {
        return findHorarioEntities(false, maxResults, firstResult);
    }

    private List<Horario> findHorarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Horario.class));
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

    public Horario buscarHorario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Horario.class, id);
        } finally {
            em.close();
        }
    }
        
    public int getIdHorario(String nombreHorario) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT h FROM Horario h WHERE h.jornada = :jornada")
                    .setParameter("jornada", nombreHorario);
            Horario horario = (Horario) q.getSingleResult();
            return horario.getIdJornada();
        } catch (Exception e) {
            return 0;
        } finally {
            em.close();
        }
    }

    public int getTotalHorarios() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Horario> rt = cq.from(Horario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
