/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "calendario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calendario.findAll", query = "SELECT c FROM Calendario c"),
    @NamedQuery(name = "Calendario.findByServicoid", query = "SELECT c FROM Calendario c WHERE c.servicoid = :servicoid"),
    @NamedQuery(name = "Calendario.findBySegunda", query = "SELECT c FROM Calendario c WHERE c.segunda = :segunda"),
    @NamedQuery(name = "Calendario.findByTerca", query = "SELECT c FROM Calendario c WHERE c.terca = :terca"),
    @NamedQuery(name = "Calendario.findByQuarta", query = "SELECT c FROM Calendario c WHERE c.quarta = :quarta"),
    @NamedQuery(name = "Calendario.findByQuinta", query = "SELECT c FROM Calendario c WHERE c.quinta = :quinta"),
    @NamedQuery(name = "Calendario.findBySexta", query = "SELECT c FROM Calendario c WHERE c.sexta = :sexta"),
    @NamedQuery(name = "Calendario.findBySabado", query = "SELECT c FROM Calendario c WHERE c.sabado = :sabado"),
    @NamedQuery(name = "Calendario.findByDomingo", query = "SELECT c FROM Calendario c WHERE c.domingo = :domingo"),
    @NamedQuery(name = "Calendario.findByDatainicio", query = "SELECT c FROM Calendario c WHERE c.datainicio = :datainicio"),
    @NamedQuery(name = "Calendario.findByDatafim", query = "SELECT c FROM Calendario c WHERE c.datafim = :datafim")})
public class Calendario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "servicoid")
    private Integer servicoid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "segunda")
    private boolean segunda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "terca")
    private boolean terca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quarta")
    private boolean quarta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quinta")
    private boolean quinta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sexta")
    private boolean sexta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sabado")
    private boolean sabado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "domingo")
    private boolean domingo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datainicio")
    @Temporal(TemporalType.DATE)
    private Date datainicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datafim")
    @Temporal(TemporalType.DATE)
    private Date datafim;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicoid")
    private Collection<Transporteviagem> transporteviagemCollection;

    public Calendario() {
    }

    public Calendario(Integer servicoid) {
        this.servicoid = servicoid;
    }

    public Calendario(Integer servicoid, boolean segunda, boolean terca, boolean quarta, boolean quinta, boolean sexta, boolean sabado, boolean domingo, Date datainicio, Date datafim) {
        this.servicoid = servicoid;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.sabado = sabado;
        this.domingo = domingo;
        this.datainicio = datainicio;
        this.datafim = datafim;
    }

    public Integer getServicoid() {
        return servicoid;
    }

    public void setServicoid(Integer servicoid) {
        this.servicoid = servicoid;
    }

    public boolean getSegunda() {
        return segunda;
    }

    public void setSegunda(boolean segunda) {
        this.segunda = segunda;
    }

    public boolean getTerca() {
        return terca;
    }

    public void setTerca(boolean terca) {
        this.terca = terca;
    }

    public boolean getQuarta() {
        return quarta;
    }

    public void setQuarta(boolean quarta) {
        this.quarta = quarta;
    }

    public boolean getQuinta() {
        return quinta;
    }

    public void setQuinta(boolean quinta) {
        this.quinta = quinta;
    }

    public boolean getSexta() {
        return sexta;
    }

    public void setSexta(boolean sexta) {
        this.sexta = sexta;
    }

    public boolean getSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

    public boolean getDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatafim() {
        return datafim;
    }

    public void setDatafim(Date datafim) {
        this.datafim = datafim;
    }

    @XmlTransient
    public Collection<Transporteviagem> getTransporteviagemCollection() {
        return transporteviagemCollection;
    }

    public void setTransporteviagemCollection(Collection<Transporteviagem> transporteviagemCollection) {
        this.transporteviagemCollection = transporteviagemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servicoid != null ? servicoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendario)) {
            return false;
        }
        Calendario other = (Calendario) object;
        if ((this.servicoid == null && other.servicoid != null) || (this.servicoid != null && !this.servicoid.equals(other.servicoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Calendario[ servicoid=" + servicoid + " ]";
    }
    
}
