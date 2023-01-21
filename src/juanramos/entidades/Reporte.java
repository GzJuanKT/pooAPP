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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "reportes")
@NamedQueries({
    @NamedQuery(name = "Reporte.findAll", query = "SELECT r FROM Reporte r"),
    @NamedQuery(name = "Reporte.findByIdReporte", query = "SELECT r FROM Reporte r WHERE r.idReporte = :idReporte"),
    @NamedQuery(name = "Reporte.findByFechaReporte", query = "SELECT r FROM Reporte r WHERE r.fechaReporte = :fechaReporte"),
    @NamedQuery(name = "Reporte.findByTiempoServicio", query = "SELECT r FROM Reporte r WHERE r.tiempoServicio = :tiempoServicio"),
    @NamedQuery(name = "Reporte.findByEventos", query = "SELECT r FROM Reporte r WHERE r.eventos = :eventos"),
    @NamedQuery(name = "Reporte.findByPagoRuta", query = "SELECT r FROM Reporte r WHERE r.pagoRuta = :pagoRuta")})
public class Reporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReporte")
    private Integer idReporte;
    @Basic(optional = false)
    @Column(name = "fechaReporte")
    @Temporal(TemporalType.DATE)
    private Date fechaReporte;
    @Basic(optional = false)
    @Column(name = "tiempoServicio")
    private int tiempoServicio;
    @Basic(optional = false)
    @Column(name = "eventos")
    private String eventos;
    @Basic(optional = false)
    @Column(name = "pagoRuta")
    private double pagoRuta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reportesidReporte")
    private List<Padrefamilia> padrefamiliaList;
    @JoinColumn(name = "Buses_PlacaBus", referencedColumnName = "PlacaBus")
    @ManyToOne(optional = false)
    private Bus busesPlacaBus;
    @JoinColumn(name = "Estudiantes_idEstudiantes", referencedColumnName = "idEstudiantes")
    @ManyToOne(optional = false)
    private Estudiante estudiantesidEstudiantes;

    public Reporte() {
    }

    public Reporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public Reporte(Integer idReporte, Date fechaReporte, int tiempoServicio, String eventos, double pagoRuta) {
        this.idReporte = idReporte;
        this.fechaReporte = fechaReporte;
        this.tiempoServicio = tiempoServicio;
        this.eventos = eventos;
        this.pagoRuta = pagoRuta;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public int getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(int tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    public String getEventos() {
        return eventos;
    }

    public void setEventos(String eventos) {
        this.eventos = eventos;
    }

    public double getPagoRuta() {
        return pagoRuta;
    }

    public void setPagoRuta(double pagoRuta) {
        this.pagoRuta = pagoRuta;
    }

    public List<Padrefamilia> getPadrefamiliaList() {
        return padrefamiliaList;
    }

    public void setPadrefamiliaList(List<Padrefamilia> padrefamiliaList) {
        this.padrefamiliaList = padrefamiliaList;
    }

    public Bus getBusesPlacaBus() {
        return busesPlacaBus;
    }

    public void setBusesPlacaBus(Bus busesPlacaBus) {
        this.busesPlacaBus = busesPlacaBus;
    }

    public Estudiante getEstudiantesidEstudiantes() {
        return estudiantesidEstudiantes;
    }

    public void setEstudiantesidEstudiantes(Estudiante estudiantesidEstudiantes) {
        this.estudiantesidEstudiantes = estudiantesidEstudiantes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReporte != null ? idReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reporte)) {
            return false;
        }
        Reporte other = (Reporte) object;
        if ((this.idReporte == null && other.idReporte != null) || (this.idReporte != null && !this.idReporte.equals(other.idReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juanramos.entidades.Reporte[ idReporte=" + idReporte + " ]";
    }
    
}
