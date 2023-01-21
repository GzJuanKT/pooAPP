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
@Table(name = "tutores")
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t"),
    @NamedQuery(name = "Tutor.findByIdTutor", query = "SELECT t FROM Tutor t WHERE t.idTutor = :idTutor"),
    @NamedQuery(name = "Tutor.findByNombreTutor", query = "SELECT t FROM Tutor t WHERE t.nombreTutor = :nombreTutor")})
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idTutor")
    private Integer idTutor;
    @Basic(optional = false)
    @Column(name = "NombreTutor")
    private String nombreTutor;
    @JoinColumn(name = "Buses_PlacaBus", referencedColumnName = "PlacaBus")
    @ManyToOne(optional = false)
    private Bus busesPlacaBus;
    @JoinColumn(name = "Horarios_IdJornada", referencedColumnName = "IdJornada")
    @ManyToOne(optional = false)
    private Horario horariosIdJornada;

    public Tutor() {
    }

    public Tutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public Tutor(Integer idTutor, String nombreTutor) {
        this.idTutor = idTutor;
        this.nombreTutor = nombreTutor;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public Bus getBusesPlacaBus() {
        return busesPlacaBus;
    }

    public void setBusesPlacaBus(Bus busesPlacaBus) {
        this.busesPlacaBus = busesPlacaBus;
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
        hash += (idTutor != null ? idTutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.idTutor == null && other.idTutor != null) || (this.idTutor != null && !this.idTutor.equals(other.idTutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juanramos.entidades.Tutor[ idTutor=" + idTutor + " ]";
    }
    
}
