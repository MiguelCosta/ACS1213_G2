/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "transporteviagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transporteviagem.findAll", query = "SELECT t FROM Transporteviagem t"),
    @NamedQuery(name = "Transporteviagem.findByTransporteviagemid", query = "SELECT t FROM Transporteviagem t WHERE t.transporteviagemid = :transporteviagemid")})
public class Transporteviagem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "transporteviagemid")
    private String transporteviagemid;
    @JoinColumn(name = "servicoid", referencedColumnName = "servicoid")
    @ManyToOne(optional = false)
    private Calendario servicoid;
    @JoinColumn(name = "Rotaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Rota rotaid;

    public Transporteviagem() {
    }

    public Transporteviagem(String transporteviagemid) {
        this.transporteviagemid = transporteviagemid;
    }

    public String getTransporteviagemid() {
        return transporteviagemid;
    }

    public void setTransporteviagemid(String transporteviagemid) {
        this.transporteviagemid = transporteviagemid;
    }

    public Calendario getServicoid() {
        return servicoid;
    }

    public void setServicoid(Calendario servicoid) {
        this.servicoid = servicoid;
    }

    public Rota getRotaid() {
        return rotaid;
    }

    public void setRotaid(Rota rotaid) {
        this.rotaid = rotaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transporteviagemid != null ? transporteviagemid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transporteviagem)) {
            return false;
        }
        Transporteviagem other = (Transporteviagem) object;
        if ((this.transporteviagemid == null && other.transporteviagemid != null) || (this.transporteviagemid != null && !this.transporteviagemid.equals(other.transporteviagemid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Transporteviagem[ transporteviagemid=" + transporteviagemid + " ]";
    }
    
}
