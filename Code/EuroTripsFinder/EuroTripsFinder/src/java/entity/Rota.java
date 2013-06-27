/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JorgeMaia
 */
@Entity
@Table(name = "rota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rota.findAll", query = "SELECT r FROM Rota r"),
    @NamedQuery(name = "Rota.findById", query = "SELECT r FROM Rota r WHERE r.id = :id"),
    @NamedQuery(name = "Rota.findByNomecurto", query = "SELECT r FROM Rota r WHERE r.nomecurto = :nomecurto"),
    @NamedQuery(name = "Rota.findByNomelongo", query = "SELECT r FROM Rota r WHERE r.nomelongo = :nomelongo"),
    @NamedQuery(name = "Rota.findByTiporota", query = "SELECT r FROM Rota r WHERE r.tiporota = :tiporota")})
public class Rota implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nomecurto")
    private String nomecurto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nomelongo")
    private String nomelongo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiporota")
    private int tiporota;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rotaid")
    private Collection<Transporteviagem> transporteviagemCollection;
    @JoinColumn(name = "Companhiaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Companhia companhiaid;

    public Rota() {
    }

    public Rota(Integer id) {
        this.id = id;
    }

    public Rota(Integer id, String nomecurto, String nomelongo, int tiporota) {
        this.id = id;
        this.nomecurto = nomecurto;
        this.nomelongo = nomelongo;
        this.tiporota = tiporota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomecurto() {
        return nomecurto;
    }

    public void setNomecurto(String nomecurto) {
        this.nomecurto = nomecurto;
    }

    public String getNomelongo() {
        return nomelongo;
    }

    public void setNomelongo(String nomelongo) {
        this.nomelongo = nomelongo;
    }

    public int getTiporota() {
        return tiporota;
    }

    public void setTiporota(int tiporota) {
        this.tiporota = tiporota;
    }

    @XmlTransient
    public Collection<Transporteviagem> getTransporteviagemCollection() {
        return transporteviagemCollection;
    }

    public void setTransporteviagemCollection(Collection<Transporteviagem> transporteviagemCollection) {
        this.transporteviagemCollection = transporteviagemCollection;
    }

    public Companhia getCompanhiaid() {
        return companhiaid;
    }

    public void setCompanhiaid(Companhia companhiaid) {
        this.companhiaid = companhiaid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rota)) {
            return false;
        }
        Rota other = (Rota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rota[ id=" + id + " ]";
    }
    
}
