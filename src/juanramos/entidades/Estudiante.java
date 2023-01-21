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
@Table(name = "estudiantes")
@NamedQueries({
    @NamedQuery(name = "Estudiante.findAll", query = "SELECT e FROM Estudiante e"),
    @NamedQuery(name = "Estudiante.findByIdEstudiantes", query = "SELECT e FROM Estudiante e WHERE e.idEstudiantes = :idEstudiantes"),
    @NamedQuery(name = "Estudiante.findByNombre", query = "SELECT e FROM Estudiante e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estudiante.findByApellido", query = "SELECT e FROM Estudiante e WHERE e.apellido = :apellido")})
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idEstudiantes")
    private Integer idEstudiantes;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Apellido")
    private String apellido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiantesidEstudiantes")
    private List<Padrefamilia> padrefamiliaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiantesidEstudiantes")
    private List<Bus> busList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudiantesidEstudiantes")
    private List<Reporte> reporteList;
    @JoinColumn(name = "Barrios_idBarrio", referencedColumnName = "idBarrio")
    @ManyToOne(optional = false)
    private Barrio barriosidBarrio;
    @JoinColumn(name = "Colegios_idColegio", referencedColumnName = "idColegio")
    @ManyToOne(optional = false)
    private Colegio colegiosidColegio;
    @JoinColumn(name = "Horarios_IdJornada", referencedColumnName = "IdJornada")
    @ManyToOne(optional = false)
    private Horario horariosIdJornada;

    public Estudiante() {
    }

    public Estudiante(Integer idEstudiantes) {
        this.idEstudiantes = idEstudiantes;
    }

    public Estudiante(Integer idEstudiantes, String nombre, String apellido) {
        this.idEstudiantes = idEstudiantes;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getIdEstudiantes() {
        return idEstudiantes;
    }

    public void setIdEstudiantes(Integer idEstudiantes) {
        this.idEstudiantes = idEstudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Padrefamilia> getPadrefamiliaList() {
        return padrefamiliaList;
    }

    public void setPadrefamiliaList(List<Padrefamilia> padrefamiliaList) {
        this.padrefamiliaList = padrefamiliaList;
    }

    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    public List<Reporte> getReporteList() {
        return reporteList;
    }

    public void setReporteList(List<Reporte> reporteList) {
        this.reporteList = reporteList;
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

    public Horario getHorariosIdJornada() {
        return horariosIdJornada;
    }

    public void setHorariosIdJornada(Horario horariosIdJornada) {
        this.horariosIdJornada = horariosIdJornada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstudiantes != null ? idEstudiantes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.idEstudiantes == null && other.idEstudiantes != null) || (this.idEstudiantes != null && !this.idEstudiantes.equals(other.idEstudiantes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juanramos.entidades.Estudiante[ idEstudiantes=" + idEstudiantes + " ]";
    }
    
}
