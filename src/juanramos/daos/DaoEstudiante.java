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
import juanramos.entidades.Horario;
import juanramos.entidades.Padrefamilia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import juanramos.daos.exceptions.IllegalOrphanException;
import juanramos.daos.exceptions.NonexistentEntityException;
import juanramos.daos.exceptions.PreexistingEntityException;
import juanramos.entidades.Bus;
import juanramos.entidades.Estudiante;
import juanramos.entidades.Reporte;

/**
 *
 * @author juand
 */
public class DaoEstudiante implements Serializable {

    public DaoEstudiante(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregar(Estudiante estudiante) throws PreexistingEntityException, Exception {
        if (estudiante.getPadrefamiliaList() == null) {
            estudiante.setPadrefamiliaList(new ArrayList<Padrefamilia>());
        }
        if (estudiante.getBusList() == null) {
            estudiante.setBusList(new ArrayList<Bus>());
        }
        if (estudiante.getReporteList() == null) {
            estudiante.setReporteList(new ArrayList<Reporte>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barrio barriosidBarrio = estudiante.getBarriosidBarrio();
            if (barriosidBarrio != null) {
                barriosidBarrio = em.getReference(barriosidBarrio.getClass(), barriosidBarrio.getIdBarrio());
                estudiante.setBarriosidBarrio(barriosidBarrio);
            }
            Colegio colegiosidColegio = estudiante.getColegiosidColegio();
            if (colegiosidColegio != null) {
                colegiosidColegio = em.getReference(colegiosidColegio.getClass(), colegiosidColegio.getIdColegio());
                estudiante.setColegiosidColegio(colegiosidColegio);
            }
            Horario horariosIdJornada = estudiante.getHorariosIdJornada();
            if (horariosIdJornada != null) {
                horariosIdJornada = em.getReference(horariosIdJornada.getClass(), horariosIdJornada.getIdJornada());
                estudiante.setHorariosIdJornada(horariosIdJornada);
            }
            List<Padrefamilia> attachedPadrefamiliaList = new ArrayList<Padrefamilia>();
            for (Padrefamilia padrefamiliaListPadrefamiliaToAttach : estudiante.getPadrefamiliaList()) {
                padrefamiliaListPadrefamiliaToAttach = em.getReference(padrefamiliaListPadrefamiliaToAttach.getClass(), padrefamiliaListPadrefamiliaToAttach.getIdPadre());
                attachedPadrefamiliaList.add(padrefamiliaListPadrefamiliaToAttach);
            }
            estudiante.setPadrefamiliaList(attachedPadrefamiliaList);
            List<Bus> attachedBusList = new ArrayList<Bus>();
            for (Bus busListBusToAttach : estudiante.getBusList()) {
                busListBusToAttach = em.getReference(busListBusToAttach.getClass(), busListBusToAttach.getPlacaBus());
                attachedBusList.add(busListBusToAttach);
            }
            estudiante.setBusList(attachedBusList);
            List<Reporte> attachedReporteList = new ArrayList<Reporte>();
            for (Reporte reporteListReporteToAttach : estudiante.getReporteList()) {
                reporteListReporteToAttach = em.getReference(reporteListReporteToAttach.getClass(), reporteListReporteToAttach.getIdReporte());
                attachedReporteList.add(reporteListReporteToAttach);
            }
            estudiante.setReporteList(attachedReporteList);
            em.persist(estudiante);
            if (barriosidBarrio != null) {
                barriosidBarrio.getEstudianteList().add(estudiante);
                barriosidBarrio = em.merge(barriosidBarrio);
            }
            if (colegiosidColegio != null) {
                colegiosidColegio.getEstudianteList().add(estudiante);
                colegiosidColegio = em.merge(colegiosidColegio);
            }
            if (horariosIdJornada != null) {
                horariosIdJornada.getEstudianteList().add(estudiante);
                horariosIdJornada = em.merge(horariosIdJornada);
            }
            for (Padrefamilia padrefamiliaListPadrefamilia : estudiante.getPadrefamiliaList()) {
                Estudiante oldEstudiantesidEstudiantesOfPadrefamiliaListPadrefamilia = padrefamiliaListPadrefamilia.getEstudiantesidEstudiantes();
                padrefamiliaListPadrefamilia.setEstudiantesidEstudiantes(estudiante);
                padrefamiliaListPadrefamilia = em.merge(padrefamiliaListPadrefamilia);
                if (oldEstudiantesidEstudiantesOfPadrefamiliaListPadrefamilia != null) {
                    oldEstudiantesidEstudiantesOfPadrefamiliaListPadrefamilia.getPadrefamiliaList().remove(padrefamiliaListPadrefamilia);
                    oldEstudiantesidEstudiantesOfPadrefamiliaListPadrefamilia = em.merge(oldEstudiantesidEstudiantesOfPadrefamiliaListPadrefamilia);
                }
            }
            for (Bus busListBus : estudiante.getBusList()) {
                Estudiante oldEstudiantesidEstudiantesOfBusListBus = busListBus.getEstudiantesidEstudiantes();
                busListBus.setEstudiantesidEstudiantes(estudiante);
                busListBus = em.merge(busListBus);
                if (oldEstudiantesidEstudiantesOfBusListBus != null) {
                    oldEstudiantesidEstudiantesOfBusListBus.getBusList().remove(busListBus);
                    oldEstudiantesidEstudiantesOfBusListBus = em.merge(oldEstudiantesidEstudiantesOfBusListBus);
                }
            }
            for (Reporte reporteListReporte : estudiante.getReporteList()) {
                Estudiante oldEstudiantesidEstudiantesOfReporteListReporte = reporteListReporte.getEstudiantesidEstudiantes();
                reporteListReporte.setEstudiantesidEstudiantes(estudiante);
                reporteListReporte = em.merge(reporteListReporte);
                if (oldEstudiantesidEstudiantesOfReporteListReporte != null) {
                    oldEstudiantesidEstudiantesOfReporteListReporte.getReporteList().remove(reporteListReporte);
                    oldEstudiantesidEstudiantesOfReporteListReporte = em.merge(oldEstudiantesidEstudiantesOfReporteListReporte);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscarEstudiante(estudiante.getIdEstudiantes()) != null) {
                throw new PreexistingEntityException("Estudiante " + estudiante + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Estudiante estudiante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante persistentEstudiante = em.find(Estudiante.class, estudiante.getIdEstudiantes());
            Barrio barriosidBarrioOld = persistentEstudiante.getBarriosidBarrio();
            Barrio barriosidBarrioNew = estudiante.getBarriosidBarrio();
            Colegio colegiosidColegioOld = persistentEstudiante.getColegiosidColegio();
            Colegio colegiosidColegioNew = estudiante.getColegiosidColegio();
            Horario horariosIdJornadaOld = persistentEstudiante.getHorariosIdJornada();
            Horario horariosIdJornadaNew = estudiante.getHorariosIdJornada();
            List<Padrefamilia> padrefamiliaListOld = persistentEstudiante.getPadrefamiliaList();
            List<Padrefamilia> padrefamiliaListNew = estudiante.getPadrefamiliaList();
            List<Bus> busListOld = persistentEstudiante.getBusList();
            List<Bus> busListNew = estudiante.getBusList();
            List<Reporte> reporteListOld = persistentEstudiante.getReporteList();
            List<Reporte> reporteListNew = estudiante.getReporteList();
            List<String> illegalOrphanMessages = null;
            for (Padrefamilia padrefamiliaListOldPadrefamilia : padrefamiliaListOld) {
                if (!padrefamiliaListNew.contains(padrefamiliaListOldPadrefamilia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Padrefamilia " + padrefamiliaListOldPadrefamilia + " since its estudiantesidEstudiantes field is not nullable.");
                }
            }
            for (Bus busListOldBus : busListOld) {
                if (!busListNew.contains(busListOldBus)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bus " + busListOldBus + " since its estudiantesidEstudiantes field is not nullable.");
                }
            }
            for (Reporte reporteListOldReporte : reporteListOld) {
                if (!reporteListNew.contains(reporteListOldReporte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reporte " + reporteListOldReporte + " since its estudiantesidEstudiantes field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (barriosidBarrioNew != null) {
                barriosidBarrioNew = em.getReference(barriosidBarrioNew.getClass(), barriosidBarrioNew.getIdBarrio());
                estudiante.setBarriosidBarrio(barriosidBarrioNew);
            }
            if (colegiosidColegioNew != null) {
                colegiosidColegioNew = em.getReference(colegiosidColegioNew.getClass(), colegiosidColegioNew.getIdColegio());
                estudiante.setColegiosidColegio(colegiosidColegioNew);
            }
            if (horariosIdJornadaNew != null) {
                horariosIdJornadaNew = em.getReference(horariosIdJornadaNew.getClass(), horariosIdJornadaNew.getIdJornada());
                estudiante.setHorariosIdJornada(horariosIdJornadaNew);
            }
            List<Padrefamilia> attachedPadrefamiliaListNew = new ArrayList<Padrefamilia>();
            for (Padrefamilia padrefamiliaListNewPadrefamiliaToAttach : padrefamiliaListNew) {
                padrefamiliaListNewPadrefamiliaToAttach = em.getReference(padrefamiliaListNewPadrefamiliaToAttach.getClass(), padrefamiliaListNewPadrefamiliaToAttach.getIdPadre());
                attachedPadrefamiliaListNew.add(padrefamiliaListNewPadrefamiliaToAttach);
            }
            padrefamiliaListNew = attachedPadrefamiliaListNew;
            estudiante.setPadrefamiliaList(padrefamiliaListNew);
            List<Bus> attachedBusListNew = new ArrayList<Bus>();
            for (Bus busListNewBusToAttach : busListNew) {
                busListNewBusToAttach = em.getReference(busListNewBusToAttach.getClass(), busListNewBusToAttach.getPlacaBus());
                attachedBusListNew.add(busListNewBusToAttach);
            }
            busListNew = attachedBusListNew;
            estudiante.setBusList(busListNew);
            List<Reporte> attachedReporteListNew = new ArrayList<Reporte>();
            for (Reporte reporteListNewReporteToAttach : reporteListNew) {
                reporteListNewReporteToAttach = em.getReference(reporteListNewReporteToAttach.getClass(), reporteListNewReporteToAttach.getIdReporte());
                attachedReporteListNew.add(reporteListNewReporteToAttach);
            }
            reporteListNew = attachedReporteListNew;
            estudiante.setReporteList(reporteListNew);
            estudiante = em.merge(estudiante);
            if (barriosidBarrioOld != null && !barriosidBarrioOld.equals(barriosidBarrioNew)) {
                barriosidBarrioOld.getEstudianteList().remove(estudiante);
                barriosidBarrioOld = em.merge(barriosidBarrioOld);
            }
            if (barriosidBarrioNew != null && !barriosidBarrioNew.equals(barriosidBarrioOld)) {
                barriosidBarrioNew.getEstudianteList().add(estudiante);
                barriosidBarrioNew = em.merge(barriosidBarrioNew);
            }
            if (colegiosidColegioOld != null && !colegiosidColegioOld.equals(colegiosidColegioNew)) {
                colegiosidColegioOld.getEstudianteList().remove(estudiante);
                colegiosidColegioOld = em.merge(colegiosidColegioOld);
            }
            if (colegiosidColegioNew != null && !colegiosidColegioNew.equals(colegiosidColegioOld)) {
                colegiosidColegioNew.getEstudianteList().add(estudiante);
                colegiosidColegioNew = em.merge(colegiosidColegioNew);
            }
            if (horariosIdJornadaOld != null && !horariosIdJornadaOld.equals(horariosIdJornadaNew)) {
                horariosIdJornadaOld.getEstudianteList().remove(estudiante);
                horariosIdJornadaOld = em.merge(horariosIdJornadaOld);
            }
            if (horariosIdJornadaNew != null && !horariosIdJornadaNew.equals(horariosIdJornadaOld)) {
                horariosIdJornadaNew.getEstudianteList().add(estudiante);
                horariosIdJornadaNew = em.merge(horariosIdJornadaNew);
            }
            for (Padrefamilia padrefamiliaListNewPadrefamilia : padrefamiliaListNew) {
                if (!padrefamiliaListOld.contains(padrefamiliaListNewPadrefamilia)) {
                    Estudiante oldEstudiantesidEstudiantesOfPadrefamiliaListNewPadrefamilia = padrefamiliaListNewPadrefamilia.getEstudiantesidEstudiantes();
                    padrefamiliaListNewPadrefamilia.setEstudiantesidEstudiantes(estudiante);
                    padrefamiliaListNewPadrefamilia = em.merge(padrefamiliaListNewPadrefamilia);
                    if (oldEstudiantesidEstudiantesOfPadrefamiliaListNewPadrefamilia != null && !oldEstudiantesidEstudiantesOfPadrefamiliaListNewPadrefamilia.equals(estudiante)) {
                        oldEstudiantesidEstudiantesOfPadrefamiliaListNewPadrefamilia.getPadrefamiliaList().remove(padrefamiliaListNewPadrefamilia);
                        oldEstudiantesidEstudiantesOfPadrefamiliaListNewPadrefamilia = em.merge(oldEstudiantesidEstudiantesOfPadrefamiliaListNewPadrefamilia);
                    }
                }
            }
            for (Bus busListNewBus : busListNew) {
                if (!busListOld.contains(busListNewBus)) {
                    Estudiante oldEstudiantesidEstudiantesOfBusListNewBus = busListNewBus.getEstudiantesidEstudiantes();
                    busListNewBus.setEstudiantesidEstudiantes(estudiante);
                    busListNewBus = em.merge(busListNewBus);
                    if (oldEstudiantesidEstudiantesOfBusListNewBus != null && !oldEstudiantesidEstudiantesOfBusListNewBus.equals(estudiante)) {
                        oldEstudiantesidEstudiantesOfBusListNewBus.getBusList().remove(busListNewBus);
                        oldEstudiantesidEstudiantesOfBusListNewBus = em.merge(oldEstudiantesidEstudiantesOfBusListNewBus);
                    }
                }
            }
            for (Reporte reporteListNewReporte : reporteListNew) {
                if (!reporteListOld.contains(reporteListNewReporte)) {
                    Estudiante oldEstudiantesidEstudiantesOfReporteListNewReporte = reporteListNewReporte.getEstudiantesidEstudiantes();
                    reporteListNewReporte.setEstudiantesidEstudiantes(estudiante);
                    reporteListNewReporte = em.merge(reporteListNewReporte);
                    if (oldEstudiantesidEstudiantesOfReporteListNewReporte != null && !oldEstudiantesidEstudiantesOfReporteListNewReporte.equals(estudiante)) {
                        oldEstudiantesidEstudiantesOfReporteListNewReporte.getReporteList().remove(reporteListNewReporte);
                        oldEstudiantesidEstudiantesOfReporteListNewReporte = em.merge(oldEstudiantesidEstudiantesOfReporteListNewReporte);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estudiante.getIdEstudiantes();
                if (buscarEstudiante(id) == null) {
                    throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.");
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
            Estudiante estudiante;
            try {
                estudiante = em.getReference(Estudiante.class, id);
                estudiante.getIdEstudiantes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Padrefamilia> padrefamiliaListOrphanCheck = estudiante.getPadrefamiliaList();
            for (Padrefamilia padrefamiliaListOrphanCheckPadrefamilia : padrefamiliaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Padrefamilia " + padrefamiliaListOrphanCheckPadrefamilia + " in its padrefamiliaList field has a non-nullable estudiantesidEstudiantes field.");
            }
            List<Bus> busListOrphanCheck = estudiante.getBusList();
            for (Bus busListOrphanCheckBus : busListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Bus " + busListOrphanCheckBus + " in its busList field has a non-nullable estudiantesidEstudiantes field.");
            }
            List<Reporte> reporteListOrphanCheck = estudiante.getReporteList();
            for (Reporte reporteListOrphanCheckReporte : reporteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Reporte " + reporteListOrphanCheckReporte + " in its reporteList field has a non-nullable estudiantesidEstudiantes field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Barrio barriosidBarrio = estudiante.getBarriosidBarrio();
            if (barriosidBarrio != null) {
                barriosidBarrio.getEstudianteList().remove(estudiante);
                barriosidBarrio = em.merge(barriosidBarrio);
            }
            Colegio colegiosidColegio = estudiante.getColegiosidColegio();
            if (colegiosidColegio != null) {
                colegiosidColegio.getEstudianteList().remove(estudiante);
                colegiosidColegio = em.merge(colegiosidColegio);
            }
            Horario horariosIdJornada = estudiante.getHorariosIdJornada();
            if (horariosIdJornada != null) {
                horariosIdJornada.getEstudianteList().remove(estudiante);
                horariosIdJornada = em.merge(horariosIdJornada);
            }
            em.remove(estudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudiante> listarTodosLosEstudiantes() {
        return findEstudianteEntities(true, -1, -1);
    }

    public List<Estudiante> findEstudianteEntities(int maxResults, int firstResult) {
        return findEstudianteEntities(false, maxResults, firstResult);
    }

    private List<Estudiante> findEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudiante.class));
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

    public Estudiante buscarEstudiante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalEstudiantes() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudiante> rt = cq.from(Estudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
