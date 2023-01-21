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
import juanramos.entidades.Estudiante;
import juanramos.entidades.Padrefamilia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import juanramos.daos.exceptions.IllegalOrphanException;
import juanramos.daos.exceptions.NonexistentEntityException;
import juanramos.entidades.Reporte;

/**
 *
 * @author juand
 */
public class DaoReporte implements Serializable {

    public DaoReporte(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregar(Reporte reporte) {
        if (reporte.getPadrefamiliaList() == null) {
            reporte.setPadrefamiliaList(new ArrayList<Padrefamilia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bus busesPlacaBus = reporte.getBusesPlacaBus();
            if (busesPlacaBus != null) {
                busesPlacaBus = em.getReference(busesPlacaBus.getClass(), busesPlacaBus.getPlacaBus());
                reporte.setBusesPlacaBus(busesPlacaBus);
            }
            Estudiante estudiantesidEstudiantes = reporte.getEstudiantesidEstudiantes();
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes = em.getReference(estudiantesidEstudiantes.getClass(), estudiantesidEstudiantes.getIdEstudiantes());
                reporte.setEstudiantesidEstudiantes(estudiantesidEstudiantes);
            }
            List<Padrefamilia> attachedPadrefamiliaList = new ArrayList<Padrefamilia>();
            for (Padrefamilia padrefamiliaListPadrefamiliaToAttach : reporte.getPadrefamiliaList()) {
                padrefamiliaListPadrefamiliaToAttach = em.getReference(padrefamiliaListPadrefamiliaToAttach.getClass(), padrefamiliaListPadrefamiliaToAttach.getIdPadre());
                attachedPadrefamiliaList.add(padrefamiliaListPadrefamiliaToAttach);
            }
            reporte.setPadrefamiliaList(attachedPadrefamiliaList);
            em.persist(reporte);
            if (busesPlacaBus != null) {
                busesPlacaBus.getReporteList().add(reporte);
                busesPlacaBus = em.merge(busesPlacaBus);
            }
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes.getReporteList().add(reporte);
                estudiantesidEstudiantes = em.merge(estudiantesidEstudiantes);
            }
            for (Padrefamilia padrefamiliaListPadrefamilia : reporte.getPadrefamiliaList()) {
                Reporte oldReportesidReporteOfPadrefamiliaListPadrefamilia = padrefamiliaListPadrefamilia.getReportesidReporte();
                padrefamiliaListPadrefamilia.setReportesidReporte(reporte);
                padrefamiliaListPadrefamilia = em.merge(padrefamiliaListPadrefamilia);
                if (oldReportesidReporteOfPadrefamiliaListPadrefamilia != null) {
                    oldReportesidReporteOfPadrefamiliaListPadrefamilia.getPadrefamiliaList().remove(padrefamiliaListPadrefamilia);
                    oldReportesidReporteOfPadrefamiliaListPadrefamilia = em.merge(oldReportesidReporteOfPadrefamiliaListPadrefamilia);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Reporte reporte) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reporte persistentReporte = em.find(Reporte.class, reporte.getIdReporte());
            Bus busesPlacaBusOld = persistentReporte.getBusesPlacaBus();
            Bus busesPlacaBusNew = reporte.getBusesPlacaBus();
            Estudiante estudiantesidEstudiantesOld = persistentReporte.getEstudiantesidEstudiantes();
            Estudiante estudiantesidEstudiantesNew = reporte.getEstudiantesidEstudiantes();
            List<Padrefamilia> padrefamiliaListOld = persistentReporte.getPadrefamiliaList();
            List<Padrefamilia> padrefamiliaListNew = reporte.getPadrefamiliaList();
            List<String> illegalOrphanMessages = null;
            for (Padrefamilia padrefamiliaListOldPadrefamilia : padrefamiliaListOld) {
                if (!padrefamiliaListNew.contains(padrefamiliaListOldPadrefamilia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Padrefamilia " + padrefamiliaListOldPadrefamilia + " since its reportesidReporte field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (busesPlacaBusNew != null) {
                busesPlacaBusNew = em.getReference(busesPlacaBusNew.getClass(), busesPlacaBusNew.getPlacaBus());
                reporte.setBusesPlacaBus(busesPlacaBusNew);
            }
            if (estudiantesidEstudiantesNew != null) {
                estudiantesidEstudiantesNew = em.getReference(estudiantesidEstudiantesNew.getClass(), estudiantesidEstudiantesNew.getIdEstudiantes());
                reporte.setEstudiantesidEstudiantes(estudiantesidEstudiantesNew);
            }
            List<Padrefamilia> attachedPadrefamiliaListNew = new ArrayList<Padrefamilia>();
            for (Padrefamilia padrefamiliaListNewPadrefamiliaToAttach : padrefamiliaListNew) {
                padrefamiliaListNewPadrefamiliaToAttach = em.getReference(padrefamiliaListNewPadrefamiliaToAttach.getClass(), padrefamiliaListNewPadrefamiliaToAttach.getIdPadre());
                attachedPadrefamiliaListNew.add(padrefamiliaListNewPadrefamiliaToAttach);
            }
            padrefamiliaListNew = attachedPadrefamiliaListNew;
            reporte.setPadrefamiliaList(padrefamiliaListNew);
            reporte = em.merge(reporte);
            if (busesPlacaBusOld != null && !busesPlacaBusOld.equals(busesPlacaBusNew)) {
                busesPlacaBusOld.getReporteList().remove(reporte);
                busesPlacaBusOld = em.merge(busesPlacaBusOld);
            }
            if (busesPlacaBusNew != null && !busesPlacaBusNew.equals(busesPlacaBusOld)) {
                busesPlacaBusNew.getReporteList().add(reporte);
                busesPlacaBusNew = em.merge(busesPlacaBusNew);
            }
            if (estudiantesidEstudiantesOld != null && !estudiantesidEstudiantesOld.equals(estudiantesidEstudiantesNew)) {
                estudiantesidEstudiantesOld.getReporteList().remove(reporte);
                estudiantesidEstudiantesOld = em.merge(estudiantesidEstudiantesOld);
            }
            if (estudiantesidEstudiantesNew != null && !estudiantesidEstudiantesNew.equals(estudiantesidEstudiantesOld)) {
                estudiantesidEstudiantesNew.getReporteList().add(reporte);
                estudiantesidEstudiantesNew = em.merge(estudiantesidEstudiantesNew);
            }
            for (Padrefamilia padrefamiliaListNewPadrefamilia : padrefamiliaListNew) {
                if (!padrefamiliaListOld.contains(padrefamiliaListNewPadrefamilia)) {
                    Reporte oldReportesidReporteOfPadrefamiliaListNewPadrefamilia = padrefamiliaListNewPadrefamilia.getReportesidReporte();
                    padrefamiliaListNewPadrefamilia.setReportesidReporte(reporte);
                    padrefamiliaListNewPadrefamilia = em.merge(padrefamiliaListNewPadrefamilia);
                    if (oldReportesidReporteOfPadrefamiliaListNewPadrefamilia != null && !oldReportesidReporteOfPadrefamiliaListNewPadrefamilia.equals(reporte)) {
                        oldReportesidReporteOfPadrefamiliaListNewPadrefamilia.getPadrefamiliaList().remove(padrefamiliaListNewPadrefamilia);
                        oldReportesidReporteOfPadrefamiliaListNewPadrefamilia = em.merge(oldReportesidReporteOfPadrefamiliaListNewPadrefamilia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reporte.getIdReporte();
                if (buscarReporte(id) == null) {
                    throw new NonexistentEntityException("The reporte with id " + id + " no longer exists.");
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
            Reporte reporte;
            try {
                reporte = em.getReference(Reporte.class, id);
                reporte.getIdReporte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reporte with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Padrefamilia> padrefamiliaListOrphanCheck = reporte.getPadrefamiliaList();
            for (Padrefamilia padrefamiliaListOrphanCheckPadrefamilia : padrefamiliaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reporte (" + reporte + ") cannot be destroyed since the Padrefamilia " + padrefamiliaListOrphanCheckPadrefamilia + " in its padrefamiliaList field has a non-nullable reportesidReporte field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Bus busesPlacaBus = reporte.getBusesPlacaBus();
            if (busesPlacaBus != null) {
                busesPlacaBus.getReporteList().remove(reporte);
                busesPlacaBus = em.merge(busesPlacaBus);
            }
            Estudiante estudiantesidEstudiantes = reporte.getEstudiantesidEstudiantes();
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes.getReporteList().remove(reporte);
                estudiantesidEstudiantes = em.merge(estudiantesidEstudiantes);
            }
            em.remove(reporte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reporte> listarTodosLosReportes() {
        return findReporteEntities(true, -1, -1);
    }

    public List<Reporte> findReporteEntities(int maxResults, int firstResult) {
        return findReporteEntities(false, maxResults, firstResult);
    }

    private List<Reporte> findReporteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reporte.class));
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

    public Reporte buscarReporte(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reporte.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalReportes() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reporte> rt = cq.from(Reporte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
