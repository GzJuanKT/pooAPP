/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juanramos.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author juand
 */
@Entity
@Table(name = "horarios")
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByIdJornada", query = "SELECT h FROM Horario h WHERE h.idJornada = :idJornada"),
    @NamedQuery(name = "Horario.findByJornada", query = "SELECT h FROM Horario h WHERE h.jornada = :jornada"),
    @NamedQuery(name = "Horario.findByHoraPartida", query = "SELECT h FROM Horario h WHERE h.horaPartida = :horaPartida"),
    @NamedQuery(name = "Horario.findByHoraLLegada", query = "SELECT h FROM Horario h WHERE h.horaLLegada = :horaLLegada")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdJornada")
    private Integer idJornada;
    @Basic(optional = false)
    @Column(name = "Jornada")
    private String jornada;
    @Basic(optional = false)
    @Column(name = "HoraPartida")
    @Temporal(TemporalType.TIME)
    private Date horaPartida;
    @Basic(optional = false)
    @Column(name = "HoraLLegada")
    @Temporal(TemporalType.TIME)
    private Date horaLLegada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horariosIdJornada")
    private List<Tutor> tutorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horariosIdJornada")
    private List<Bus> busList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horariosIdJornada")
    private List<Estudiante> estudianteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "horariosIdJornada")
    private List<Conductor> conductorList;

    public Horario() {
    }

    public Horario(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Horario(Integer idJornada, String jornada, Date horaPartida, Date horaLLegada) {
        this.idJornada = idJornada;
        this.jornada = jornada;
        this.horaPartida = horaPartida;
        this.horaLLegada = horaLLegada;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public Date getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(Date horaPartida) {
        this.horaPartida = horaPartida;
    }

    public Date getHoraLLegada() {
        return horaLLegada;
    }

    public void setHoraLLegada(Date horaLLegada) {
        this.horaLLegada = horaLLegada;
    }

    public List<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(List<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
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
        hash += (idJornada != null ? idJornada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.idJornada == null && other.idJornada != null) || (this.idJornada != null && !this.idJornada.equals(other.idJornada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juanramos.entidades.Horario[ idJornada=" + idJornada + " ]";
    }
    
}
