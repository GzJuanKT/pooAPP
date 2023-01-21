/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juanramos.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author juand
 */
@Entity
@Table(name = "buses")
@NamedQueries({
    @NamedQuery(name = "Bus.findAll", query = "SELECT b FROM Bus b"),
    @NamedQuery(name = "Bus.findByPlacaBus", query = "SELECT b FROM Bus b WHERE b.placaBus = :placaBus"),
    @NamedQuery(name = "Bus.findByCapacidad", query = "SELECT b FROM Bus b WHERE b.capacidad = :capacidad")})
public class Bus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PlacaBus")
    private String placaBus;
    @Basic(optional = false)
    @Column(name = "capacidad")
    private short capacidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busesPlacaBus")
    private List<Tutor> tutorList;
    @JoinColumn(name = "Barrios_idBarrio", referencedColumnName = "idBarrio")
    @ManyToOne(optional = false)
    private Barrio barriosidBarrio;
    @JoinColumn(name = "Colegios_idColegio", referencedColumnName = "idColegio")
    @ManyToOne(optional = false)
    private Colegio colegiosidColegio;
    @JoinColumn(name = "Estudiantes_idEstudiantes", referencedColumnName = "idEstudiantes")
    @ManyToOne(optional = false)
    private Estudiante estudiantesidEstudiantes;
    @JoinColumn(name = "Horarios_IdJornada", referencedColumnName = "IdJornada")
    @ManyToOne(optional = false)
    private Horario horariosIdJornada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busesPlacaBus")
    private List<Reporte> reporteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busesPlacaBus")
    private List<Conductor> conductorList;

    public Bus() {
    }

    public Bus(String placaBus) {
        this.placaBus = placaBus;
    }

    public Bus(String placaBus, short capacidad) {
        this.placaBus = placaBus;
        this.capacidad = capacidad;
    }

    public String getPlacaBus() {
        return placaBus;
    }

    public void setPlacaBus(String placaBus) {
        this.placaBus = placaBus;
    }

    public short getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(short capacidad) {
        this.capacidad = capacidad;
    }

    public List<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(List<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    public Barrio getBarriosidBarrio() {
        return barriosidBarrio;
    }

    public void setBarriosidBarrio(Barrio barriosidBarrio) {
        this.barriosidBarrio = barriosidBarrio;
    }

    public Colegio getColegiosidColegio() {
        return colegiosidColegio;
    }

    public void setColegiosidColegio(Colegio colegiosidColegio) {
        this.colegiosidColegio = colegiosidColegio;
    }

    public Estudiante getEstudiantesidEstudiantes() {
        return estudiantesidEstudiantes;
    }

    public void setEstudiantesidEstudiantes(Estudiante estudiantesidEstudiantes) {
        this.estudiantesidEstudiantes = estudiantesidEstudiantes;
    }

    public Horario getHorariosIdJornada() {
        return horariosIdJornada;
    }

    public void setHorariosIdJornada(Horario horariosIdJornada) {
        this.horariosIdJornada = horariosIdJornada;
    }

    public List<Reporte> getReporteList() {
        return reporteList;
    }

    public void setReporteList(List<Reporte> reporteList) {
        this.reporteList = reporteList;
    }

    public List<Conductor> getConductorList() {
        return conductorList;
    }

    public void setConductorList(List<Conductor> conductorList) {
        this.conductorList = conductorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placaBus != null ? placaBus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus other = (Bus) object;
        if ((this.placaBus == null && other.placaBus != null) || (this.placaBus != null && !this.placaBus.equals(other.placaBus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juanramos.entidades.Bus[ placaBus=" + placaBus + " ]";
    }
    
}
