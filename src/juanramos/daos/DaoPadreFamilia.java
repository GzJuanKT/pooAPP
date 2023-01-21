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
import juanramos.entidades.Estudiante;
import juanramos.entidades.Padrefamilia;
import juanramos.entidades.Reporte;

/**
 *
 * @author juand
 */
public class DaoPadreFamilia implements Serializable {

    public DaoPadreFamilia(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void agregar(Padrefamilia padrefamilia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante estudiantesidEstudiantes = padrefamilia.getEstudiantesidEstudiantes();
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes = em.getReference(estudiantesidEstudiantes.getClass(), estudiantesidEstudiantes.getIdEstudiantes());
                padrefamilia.setEstudiantesidEstudiantes(estudiantesidEstudiantes);
            }
            Reporte reportesidReporte = padrefamilia.getReportesidReporte();
            if (reportesidReporte != null) {
                reportesidReporte = em.getReference(reportesidReporte.getClass(), reportesidReporte.getIdReporte());
                padrefamilia.setReportesidReporte(reportesidReporte);
            }
            em.persist(padrefamilia);
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes.getPadrefamiliaList().add(padrefamilia);
                estudiantesidEstudiantes = em.merge(estudiantesidEstudiantes);
            }
            if (reportesidReporte != null) {
                reportesidReporte.getPadrefamiliaList().add(padrefamilia);
                reportesidReporte = em.merge(reportesidReporte);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (buscarPadreFamilia(padrefamilia.getIdPadre()) != null) {
                throw new PreexistingEntityException("Padrefamilia " + padrefamilia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editar(Padrefamilia padrefamilia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Padrefamilia persistentPadrefamilia = em.find(Padrefamilia.class, padrefamilia.getIdPadre());
            Estudiante estudiantesidEstudiantesOld = persistentPadrefamilia.getEstudiantesidEstudiantes();
            Estudiante estudiantesidEstudiantesNew = padrefamilia.getEstudiantesidEstudiantes();
            Reporte reportesidReporteOld = persistentPadrefamilia.getReportesidReporte();
            Reporte reportesidReporteNew = padrefamilia.getReportesidReporte();
            if (estudiantesidEstudiantesNew != null) {
                estudiantesidEstudiantesNew = em.getReference(estudiantesidEstudiantesNew.getClass(), estudiantesidEstudiantesNew.getIdEstudiantes());
                padrefamilia.setEstudiantesidEstudiantes(estudiantesidEstudiantesNew);
            }
            if (reportesidReporteNew != null) {
                reportesidReporteNew = em.getReference(reportesidReporteNew.getClass(), reportesidReporteNew.getIdReporte());
                padrefamilia.setReportesidReporte(reportesidReporteNew);
            }
            padrefamilia = em.merge(padrefamilia);
            if (estudiantesidEstudiantesOld != null && !estudiantesidEstudiantesOld.equals(estudiantesidEstudiantesNew)) {
                estudiantesidEstudiantesOld.getPadrefamiliaList().remove(padrefamilia);
                estudiantesidEstudiantesOld = em.merge(estudiantesidEstudiantesOld);
            }
            if (estudiantesidEstudiantesNew != null && !estudiantesidEstudiantesNew.equals(estudiantesidEstudiantesOld)) {
                estudiantesidEstudiantesNew.getPadrefamiliaList().add(padrefamilia);
                estudiantesidEstudiantesNew = em.merge(estudiantesidEstudiantesNew);
            }
            if (reportesidReporteOld != null && !reportesidReporteOld.equals(reportesidReporteNew)) {
                reportesidReporteOld.getPadrefamiliaList().remove(padrefamilia);
                reportesidReporteOld = em.merge(reportesidReporteOld);
            }
            if (reportesidReporteNew != null && !reportesidReporteNew.equals(reportesidReporteOld)) {
                reportesidReporteNew.getPadrefamiliaList().add(padrefamilia);
                reportesidReporteNew = em.merge(reportesidReporteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = padrefamilia.getIdPadre();
                if (buscarPadreFamilia(id) == null) {
                    throw new NonexistentEntityException("The padrefamilia with id " + id + " no longer exists.");
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
            Padrefamilia padrefamilia;
            try {
                padrefamilia = em.getReference(Padrefamilia.class, id);
                padrefamilia.getIdPadre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The padrefamilia with id " + id + " no longer exists.", enfe);
            }
            Estudiante estudiantesidEstudiantes = padrefamilia.getEstudiantesidEstudiantes();
            if (estudiantesidEstudiantes != null) {
                estudiantesidEstudiantes.getPadrefamiliaList().remove(padrefamilia);
                estudiantesidEstudiantes = em.merge(estudiantesidEstudiantes);
            }
            Reporte reportesidReporte = padrefamilia.getReportesidReporte();
            if (reportesidReporte != null) {
                reportesidReporte.getPadrefamiliaList().remove(padrefamilia);
                reportesidReporte = em.merge(reportesidReporte);
            }
            em.remove(padrefamilia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Padrefamilia> listarTodosLosPadresFamilia() {
        return findPadrefamiliaEntities(true, -1, -1);
    }

    public List<Padrefamilia> findPadrefamiliaEntities(int maxResults, int firstResult) {
        return findPadrefamiliaEntities(false, maxResults, firstResult);
    }

    private List<Padrefamilia> findPadrefamiliaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Padrefamilia.class));
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

    public Padrefamilia buscarPadreFamilia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Padrefamilia.class, id);
        } finally {
            em.close();
        }
    }

    public int getTotalPadresFamilia() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Padrefamilia> rt = cq.from(Padrefamilia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
