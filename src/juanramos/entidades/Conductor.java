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
@Table(name = "conductor")
@NamedQueries({
    @NamedQuery(name = "Conductor.findAll", query = "SELECT c FROM Conductor c"),
    @NamedQuery(name = "Conductor.findByIdConductor", query = "SELECT c FROM Conductor c WHERE c.idConductor = :idConductor"),
    @NamedQuery(name = "Conductor.findByNombreConductor", query = "SELECT c FROM Conductor c WHERE c.nombreConductor = :nombreConductor")})
public class Conductor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idConductor")
    private Integer idConductor;
    @Basic(optional = false)
    @Column(name = "nombreConductor")
    private String nombreConductor;
    @JoinColumn(name = "Buses_PlacaBus", referencedColumnName = "PlacaBus")
    @ManyToOne(optional = false)
    private Bus busesPlacaBus;
    @JoinColumn(name = "Horarios_IdJornada", referencedColumnName = "IdJornada")
    @ManyToOne(optional = false)
    private Horario horariosIdJornada;

    public Conductor() {
    }

    public Conductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    public Conductor(Integer idConductor, String nombreConductor) {
        this.idConductor = idConductor;
        this.nombreConductor = nombreConductor;
    }

    public Integer getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
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
        hash += (idConductor != null ? idConductor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conductor)) {
            return false;
        }
        Conductor other = (Conductor) object;
        if ((this.idConductor == null && other.idConductor != null) || (this.idConductor != null && !this.idConductor.equals(other.idConductor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juanramos.entidades.Conductor[ idConductor=" + idConductor + " ]";
    }
    
}
