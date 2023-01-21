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
import juanramos.entidades.Barrio;
import juanramos.entidades.Colegio;
import juanramos.entidades.Estudiante;
import juanramos.entidades.Horario;
import juanramos.entidades.Tutor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import juanramos.daos.exceptions.IllegalOrphanException;
import juanramos.daos.exceptions.NonexistentEntityException;
import juanramos.daos.exceptions.PreexistingEntityException;
import juanramos.entidades.Bus;
import juanramos.entidades.Reporte;
import juanramos.entidades.Conductor;

/**
 *
 * @author juand
 */
public class DaoBus implements Serializable {

    public DaoBus(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregar(Bus bus) throws PreexistingEntityException, Exception {
        if (bus.getTutorList() == null) {
            bus.setTutorList(new ArrayList<Tutor>());
        }
        if (bus.getReporteList() == null) {
            bus.setReporteList(new ArrayList<Reporte>());
        }
        if (bus.getConductorList() == null) {
            bus.setConductorList(new ArrayList<Conductor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barrio barriosidBarrio = bus.getBarriosidBarrio();
            if (barriosidBarrio != null) {
                barriosidBarrio = em.getReference(barriosidBarrio.getClass(), barriosidBarrio.getIdBarrio());
                bus.setBarriosidBarrio(barriosidBarrio);
            }
            Colegio colegiosidColegio = bus.getColegiosidColegio();
            if (colegiosidColegio != null) {
                colegiosidColegio = em.getReference(colegiosidColegio.getClass(), colegiosidColegio.getIdColegio());
                bus.setColegiosidColegio(colegiosidColegio);
            }
            Estudiante estudiantesidEstudiantes = bus.getEstudiantesidEstudiantes();
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes = em.getReference(estudiantesidEstudiantes.getClass(), estudiantesidEstudiantes.getIdEstudiantes());
                bus.setEstudiantesidEstudiantes(estudiantesidEstudiantes);
            }
            Horario horariosIdJornada = bus.getHorariosIdJornada();
            if (horariosIdJornada != null) {
                horariosIdJornada = em.getReference(horariosIdJornada.getClass(), horariosIdJornada.getIdJornada());
                bus.setHorariosIdJornada(horariosIdJornada);
            }
            List<Tutor> attachedTutorList = new ArrayList<Tutor>();
            for (Tutor tutorListTutorToAttach : bus.getTutorList()) {
                tutorListTutorToAttach = em.getReference(tutorListTutorToAttach.getClass(), tutorListTutorToAttach.getIdTutor());
                attachedTutorList.add(tutorListTutorToAttach);
            }
            bus.setTutorList(attachedTutorList);
            List<Reporte> attachedReporteList = new ArrayList<Reporte>();
            for (Reporte reporteListReporteToAttach : bus.getReporteList()) {
                reporteListReporteToAttach = em.getReference(reporteListReporteToAttach.getClass(), reporteListReporteToAttach.getIdReporte());
                attachedReporteList.add(reporteListReporteToAttach);
            }
            bus.setReporteList(attachedReporteList);
            List<Conductor> attachedConductorList = new ArrayList<Conductor>();
            for (Conductor conductorListConductorToAttach : bus.getConductorList()) {
                conductorListConductorToAttach = em.getReference(conductorListConductorToAttach.getClass(), conductorListConductorToAttach.getIdConductor());
                attachedConductorList.add(conductorListConductorToAttach);
            }
            bus.setConductorList(attachedConductorList);
            em.persist(bus);
            if (barriosidBarrio != null) {
                barriosidBarrio.getBusList().add(bus);
                barriosidBarrio = em.merge(barriosidBarrio);
            }
            if (colegiosidColegio != null) {
                colegiosidColegio.getBusList().add(bus);
                colegiosidColegio = em.merge(colegiosidColegio);
            }
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes.getBusList().add(bus);
                estudiantesidEstudiantes = em.merge(estudiantesidEstudiantes);
            }
            if (horariosIdJornada != null) {
                horariosIdJornada.getBusList().add(bus);
                horariosIdJornada = em.merge(horariosIdJornada);
            }
            for (Tutor tutorListTutor : bus.getTutorList()) {
                Bus oldBusesPlacaBusOfTutorListTutor = tutorListTutor.getBusesPlacaBus();
                tutorListTutor.setBusesPlacaBus(bus);
                tutorListTutor = em.merge(tutorListTutor);
                if (oldBusesPlacaBusOfTutorListTutor != null) {
                    oldBusesPlacaBusOfTutorListTutor.getTutorList().remove(tutorListTutor);
                    oldBusesPlacaBusOfTutorListTutor = em.merge(oldBusesPlacaBusOfTutorListTutor);
                }
            }
            for (Reporte reporteListReporte : bus.getReporteList()) {
                Bus oldBusesPlacaBusOfReporteListReporte = reporteListReporte.getBusesPlacaBus();
                reporteListReporte.setBusesPlacaBus(bus);
                reporteListReporte = em.merge(reporteListReporte);
                if (oldBusesPlacaBusOfReporteListReporte != null) {
                    oldBusesPlacaBusOfReporteListReporte.getReporteList().remove(reporteListReporte);
                    oldBusesPlacaBusOfReporteListReporte = em.merge(oldBusesPlacaBusOfReporteListReporte);
                }
            }
            for (Conductor conductorListConductor : bus.getConductorList()) {
                Bus oldBusesPlacaBusOfConductorListConductor = conductorListConductor.getBusesPlacaBus();
                conductorListConductor.setBusesPlacaBus(bus);
                conductorListConductor = em.merge(conductorListConductor);
                if (oldBusesPlacaBusOfConductorListConductor != null) {
                    oldBusesPlacaBusOfConductorListConductor.getConductorList().remove(conductorListConductor);
                    oldBusesPlacaBusOfConductorListConductor = em.merge(oldBusesPlacaBusOfConductorListConductor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscarBus(bus.getPlacaBus()) != null) {
                throw new PreexistingEntityException("Bus " + bus + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Bus bus) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bus persistentBus = em.find(Bus.class, bus.getPlacaBus());
            Barrio barriosidBarrioOld = persistentBus.getBarriosidBarrio();
            Barrio barriosidBarrioNew = bus.getBarriosidBarrio();
            Colegio colegiosidColegioOld = persistentBus.getColegiosidColegio();
            Colegio colegiosidColegioNew = bus.getColegiosidColegio();
            Estudiante estudiantesidEstudiantesOld = persistentBus.getEstudiantesidEstudiantes();
            Estudiante estudiantesidEstudiantesNew = bus.getEstudiantesidEstudiantes();
            Horario horariosIdJornadaOld = persistentBus.getHorariosIdJornada();
            Horario horariosIdJornadaNew = bus.getHorariosIdJornada();
            List<Tutor> tutorListOld = persistentBus.getTutorList();
            List<Tutor> tutorListNew = bus.getTutorList();
            List<Reporte> reporteListOld = persistentBus.getReporteList();
            List<Reporte> reporteListNew = bus.getReporteList();
            List<Conductor> conductorListOld = persistentBus.getConductorList();
            List<Conductor> conductorListNew = bus.getConductorList();
            List<String> illegalOrphanMessages = null;
            for (Tutor tutorListOldTutor : tutorListOld) {
                if (!tutorListNew.contains(tutorListOldTutor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tutor " + tutorListOldTutor + " since its busesPlacaBus field is not nullable.");
                }
            }
            for (Reporte reporteListOldReporte : reporteListOld) {
                if (!reporteListNew.contains(reporteListOldReporte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reporte " + reporteListOldReporte + " since its busesPlacaBus field is not nullable.");
                }
            }
            for (Conductor conductorListOldConductor : conductorListOld) {
                if (!conductorListNew.contains(conductorListOldConductor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Conductor " + conductorListOldConductor + " since its busesPlacaBus field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (barriosidBarrioNew != null) {
                barriosidBarrioNew = em.getReference(barriosidBarrioNew.getClass(), barriosidBarrioNew.getIdBarrio());
                bus.setBarriosidBarrio(barriosidBarrioNew);
            }
            if (colegiosidColegioNew != null) {
                colegiosidColegioNew = em.getReference(colegiosidColegioNew.getClass(), colegiosidColegioNew.getIdColegio());
                bus.setColegiosidColegio(colegiosidColegioNew);
            }
            if (estudiantesidEstudiantesNew != null) {
                estudiantesidEstudiantesNew = em.getReference(estudiantesidEstudiantesNew.getClass(), estudiantesidEstudiantesNew.getIdEstudiantes());
                bus.setEstudiantesidEstudiantes(estudiantesidEstudiantesNew);
            }
            if (horariosIdJornadaNew != null) {
                horariosIdJornadaNew = em.getReference(horariosIdJornadaNew.getClass(), horariosIdJornadaNew.getIdJornada());
                bus.setHorariosIdJornada(horariosIdJornadaNew);
            }
            List<Tutor> attachedTutorListNew = new ArrayList<Tutor>();
            for (Tutor tutorListNewTutorToAttach : tutorListNew) {
                tutorListNewTutorToAttach = em.getReference(tutorListNewTutorToAttach.getClass(), tutorListNewTutorToAttach.getIdTutor());
                attachedTutorListNew.add(tutorListNewTutorToAttach);
            }
            tutorListNew = attachedTutorListNew;
            bus.setTutorList(tutorListNew);
            List<Reporte> attachedReporteListNew = new ArrayList<Reporte>();
            for (Reporte reporteListNewReporteToAttach : reporteListNew) {
                reporteListNewReporteToAttach = em.getReference(reporteListNewReporteToAttach.getClass(), reporteListNewReporteToAttach.getIdReporte());
                attachedReporteListNew.add(reporteListNewReporteToAttach);
            }
            reporteListNew = attachedReporteListNew;
            bus.setReporteList(reporteListNew);
            List<Conductor> attachedConductorListNew = new ArrayList<Conductor>();
            for (Conductor conductorListNewConductorToAttach : conductorListNew) {
                conductorListNewConductorToAttach = em.getReference(conductorListNewConductorToAttach.getClass(), conductorListNewConductorToAttach.getIdConductor());
                attachedConductorListNew.add(conductorListNewConductorToAttach);
            }
            conductorListNew = attachedConductorListNew;
            bus.setConductorList(conductorListNew);
            bus = em.merge(bus);
            if (barriosidBarrioOld != null && !barriosidBarrioOld.equals(barriosidBarrioNew)) {
                barriosidBarrioOld.getBusList().remove(bus);
                barriosidBarrioOld = em.merge(barriosidBarrioOld);
            }
            if (barriosidBarrioNew != null && !barriosidBarrioNew.equals(barriosidBarrioOld)) {
                barriosidBarrioNew.getBusList().add(bus);
                barriosidBarrioNew = em.merge(barriosidBarrioNew);
            }
            if (colegiosidColegioOld != null && !colegiosidColegioOld.equals(colegiosidColegioNew)) {
                colegiosidColegioOld.getBusList().remove(bus);
                colegiosidColegioOld = em.merge(colegiosidColegioOld);
            }
            if (colegiosidColegioNew != null && !colegiosidColegioNew.equals(colegiosidColegioOld)) {
                colegiosidColegioNew.getBusList().add(bus);
                colegiosidColegioNew = em.merge(colegiosidColegioNew);
            }
            if (estudiantesidEstudiantesOld != null && !estudiantesidEstudiantesOld.equals(estudiantesidEstudiantesNew)) {
                estudiantesidEstudiantesOld.getBusList().remove(bus);
                estudiantesidEstudiantesOld = em.merge(estudiantesidEstudiantesOld);
            }
            if (estudiantesidEstudiantesNew != null && !estudiantesidEstudiantesNew.equals(estudiantesidEstudiantesOld)) {
                estudiantesidEstudiantesNew.getBusList().add(bus);
                estudiantesidEstudiantesNew = em.merge(estudiantesidEstudiantesNew);
            }
            if (horariosIdJornadaOld != null && !horariosIdJornadaOld.equals(horariosIdJornadaNew)) {
                horariosIdJornadaOld.getBusList().remove(bus);
                horariosIdJornadaOld = em.merge(horariosIdJornadaOld);
            }
            if (horariosIdJornadaNew != null && !horariosIdJornadaNew.equals(horariosIdJornadaOld)) {
                horariosIdJornadaNew.getBusList().add(bus);
                horariosIdJornadaNew = em.merge(horariosIdJornadaNew);
            }
            for (Tutor tutorListNewTutor : tutorListNew) {
                if (!tutorListOld.contains(tutorListNewTutor)) {
                    Bus oldBusesPlacaBusOfTutorListNewTutor = tutorListNewTutor.getBusesPlacaBus();
                    tutorListNewTutor.setBusesPlacaBus(bus);
                    tutorListNewTutor = em.merge(tutorListNewTutor);
                    if (oldBusesPlacaBusOfTutorListNewTutor != null && !oldBusesPlacaBusOfTutorListNewTutor.equals(bus)) {
                        oldBusesPlacaBusOfTutorListNewTutor.getTutorList().remove(tutorListNewTutor);
                        oldBusesPlacaBusOfTutorListNewTutor = em.merge(oldBusesPlacaBusOfTutorListNewTutor);
                    }
                }
            }
            for (Reporte reporteListNewReporte : reporteListNew) {
                if (!reporteListOld.contains(reporteListNewReporte)) {
                    Bus oldBusesPlacaBusOfReporteListNewReporte = reporteListNewReporte.getBusesPlacaBus();
                    reporteListNewReporte.setBusesPlacaBus(bus);
                    reporteListNewReporte = em.merge(reporteListNewReporte);
                    if (oldBusesPlacaBusOfReporteListNewReporte != null && !oldBusesPlacaBusOfReporteListNewReporte.equals(bus)) {
                        oldBusesPlacaBusOfReporteListNewReporte.getReporteList().remove(reporteListNewReporte);
                        oldBusesPlacaBusOfReporteListNewReporte = em.merge(oldBusesPlacaBusOfReporteListNewReporte);
                    }
                }
            }
            for (Conductor conductorListNewConductor : conductorListNew) {
                if (!conductorListOld.contains(conductorListNewConductor)) {
                    Bus oldBusesPlacaBusOfConductorListNewConductor = conductorListNewConductor.getBusesPlacaBus();
                    conductorListNewConductor.setBusesPlacaBus(bus);
                    conductorListNewConductor = em.merge(conductorListNewConductor);
                    if (oldBusesPlacaBusOfConductorListNewConductor != null && !oldBusesPlacaBusOfConductorListNewConductor.equals(bus)) {
                        oldBusesPlacaBusOfConductorListNewConductor.getConductorList().remove(conductorListNewConductor);
                        oldBusesPlacaBusOfConductorListNewConductor = em.merge(oldBusesPlacaBusOfConductorListNewConductor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = bus.getPlacaBus();
                if (buscarBus(id) == null) {
                    throw new NonexistentEntityException("The bus with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void eliminar(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bus bus;
            try {
                bus = em.getReference(Bus.class, id);
                bus.getPlacaBus();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bus with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tutor> tutorListOrphanCheck = bus.getTutorList();
            for (Tutor tutorListOrphanCheckTutor : tutorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Bus (" + bus + ") cannot be destroyed since the Tutor " + tutorListOrphanCheckTutor + " in its tutorList field has a non-nullable busesPlacaBus field.");
            }
            List<Reporte> reporteListOrphanCheck = bus.getReporteList();
            for (Reporte reporteListOrphanCheckReporte : reporteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Bus (" + bus + ") cannot be destroyed since the Reporte " + reporteListOrphanCheckReporte + " in its reporteList field has a non-nullable busesPlacaBus field.");
            }
            List<Conductor> conductorListOrphanCheck = bus.getConductorList();
            for (Conductor conductorListOrphanCheckConductor : conductorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Bus (" + bus + ") cannot be destroyed since the Conductor " + conductorListOrphanCheckConductor + " in its conductorList field has a non-nullable busesPlacaBus field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Barrio barriosidBarrio = bus.getBarriosidBarrio();
            if (barriosidBarrio != null) {
                barriosidBarrio.getBusList().remove(bus);
                barriosidBarrio = em.merge(barriosidBarrio);
            }
            Colegio colegiosidColegio = bus.getColegiosidColegio();
            if (colegiosidColegio != null) {
                colegiosidColegio.getBusList().remove(bus);
                colegiosidColegio = em.merge(colegiosidColegio);
            }
            Estudiante estudiantesidEstudiantes = bus.getEstudiantesidEstudiantes();
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes.getBusList().remove(bus);
                estudiantesidEstudiantes = em.merge(estudiantesidEstudiantes);
            }
            Horario horariosIdJornada = bus.getHorariosIdJornada();
            if (horariosIdJornada != null) {
                horariosIdJornada.getBusList().remove(bus);
                horariosIdJornada = em.merge(horariosIdJornada);
            }
            em.remove(bus);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bus> listarTodosLosBuses() {
        return findBusEntities(true, -1, -1);
    }

    public List<Bus> findBusEntities(int maxResults, int firstResult) {
        return findBusEntities(false, maxResults, firstResult);
    }

    private List<Bus> findBusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bus.class));
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

    public Bus buscarBus(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bus.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalBuses() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bus> rt = cq.from(Bus.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
