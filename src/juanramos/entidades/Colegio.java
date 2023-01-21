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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author juand
 */
@Entity
@Table(name = "colegios")
@NamedQueries({
    @NamedQuery(name = "Colegio.findAll", query = "SELECT c FROM Colegio c"),
    @NamedQuery(name = "Colegio.findByIdColegio", query = "SELECT c FROM Colegio c WHERE c.idColegio = :idColegio"),
    @NamedQuery(name = "Colegio.findByColegio", query = "SELECT c FROM Colegio c WHERE c.colegio = :colegio")})
public class Colegio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idColegio")
    private Integer idColegio;
    @Basic(optional = false)
    @Column(name = "colegio")
    private String colegio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colegiosidColegio")
    private List<Bus> busList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "colegiosidColegio")
    private List<Estudiante> estudianteList;

    public Colegio() {
    }

    public Colegio(Integer idColegio) {
        this.idColegio = idColegio;
    }

    public Colegio(Integer idColegio, String colegio) {
        this.idColegio = idColegio;
        this.colegio = colegio;
    }

    public Integer getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(Integer idColegio) {
        this.idColegio = idColegio;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColegio != null ? idColegio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colegio)) {
            return false;
        }
        Colegio other = (Colegio) object;
        if ((this.idColegio == null && other.idColegio != null) || (this.idColegio != null && !this.idColegio.equals(other.idColegio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "juanramos.entidades.Colegio[ idColegio=" + idColegio + " ]";
    }
    
}
