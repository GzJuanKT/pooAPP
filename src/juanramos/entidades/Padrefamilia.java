/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juanramos.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author juand
 */
@Entity
@Table(name = "padrefamilia")
@NamedQueries({
    @NamedQuery(name = "Padrefamilia.findAll", query = "SELECT p FROM Padrefamilia p"),
    @NamedQuery(name = "Padrefamilia.findByIdPadre", query = "SELECT p FROM Padrefamilia p WHERE p.idPadre = :idPadre"),
    @NamedQuery(name = "Padrefamilia.findByNombrePadre", query = "SELECT p FROM Padrefamilia p WHERE p.nombrePadre = :nombrePadre"),
    @NamedQuery(name = "Padrefamilia.findByApellidoPadre", query = "SELECT p FROM Padrefamilia p WHERE p.apellidoPadre = :apellidoPadre")})
public class Padrefamilia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idPadre")
    private Integer idPadre;
    @Basic(optional = false)
    @Column(name = "NombrePadre")
    private String nombrePadre;
    @Basic(optional = false)
    @Column(name = "ApellidoPadre")
    private String apellidoPadre;
    @JoinColumn(name = "Estudiantes_idEstudiantes", referencedColumnName = "idEstudiantes")
    @ManyToOne(optional = false)
    private Estudiante estudiantesidEstudiantes;
    @JoinColumn(name = "Reportes_idReporte", referencedColumnName = "idReporte")
    @ManyToOne(optional = false)
    private Reporte reportesidReporte;

    public Padrefamilia() {
    }

    public Padrefamilia(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public Padrefamilia(Integer idPadre, String nombrePadre, String apellidoPadre) {
        this.idPadre = idPadre;
        this.nombrePadre = nombrePadre;
        this.apellidoPadre = apellidoPadre;
    }

    public Integer getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(Integer idPadre) {
        this.idPadre = idPadre;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getApellidoPadre() {
        return apellidoPadre;
    }

    public void setApellidoPadre(String apellidoPadre) {
        this.apellidoPadre = apellidoPadre;
    }

    public Estudiante getEstudiantesidEstudiantes() {
        return estudiantesidEstudiantes;
    }

    public void setEstudiantesidEstudiantes(Estudiante estudiantesidEstudiantes) {
        this.estudiantesidEstudiantes = estudiantesidEstudiantes;
    }

    public Reporte getReportesidReporte() {
        return reportesidReporte;
    }

    public void setReportesidReporte(Reporte reportesidReporte) {
        this.reportesidReporte = reportesidReporte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPadre != null ? idPadre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Padrefamilia)) {
            return false;
        }
        Padrefamilia other = (Padrefamilia) object;
        if ((this.idPadre == null && other.idPadre != null) || (this.idPadre != null && !this.idPadre.equals(other.idPadre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juanramos.entidades.Padrefamilia[ idPadre=" + idPadre + " ]";
    }
    
}
