/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author Miguel
 */
@Entity
@Table(name = "coordenada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Coordenada.findAll", query = "SELECT c FROM Coordenada c"),
    @NamedQuery(name = "Coordenada.findById", query = "SELECT c FROM Coordenada c WHERE c.id = :id"),
    @NamedQuery(name = "Coordenada.findByNome", query = "SELECT c FROM Coordenada c WHERE c.nome = :nome"),
    @NamedQuery(name = "Coordenada.findByLatitude", query = "SELECT c FROM Coordenada c WHERE c.latitude = :latitude"),
    @NamedQuery(name = "Coordenada.findByLongitude", query = "SELECT c FROM Coordenada c WHERE c.longitude = :longitude")})
public class Coordenada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nome")
    private String nome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private BigDecimal longitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coordenadaid")
    private Collection<Localparagem> localparagemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coordenadaid")
    private Collection<Cidade> cidadeCollection;

    public Coordenada() {
    }

    public Coordenada(Integer id) {
        this.id = id;
    }

    public Coordenada(Integer id, String nome, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @XmlTransient
    public Collection<Localparagem> getLocalparagemCollection() {
        return localparagemCollection;
    }

    public void setLocalparagemCollection(Collection<Localparagem> localparagemCollection) {
        this.localparagemCollection = localparagemCollection;
    }

    @XmlTransient
    public Collection<Cidade> getCidadeCollection() {
        return cidadeCollection;
    }

    public void setCidadeCollection(Collection<Cidade> cidadeCollection) {
        this.cidadeCollection = cidadeCollection;
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
        if (!(object instanceof Coordenada)) {
            return false;
        }
        Coordenada other = (Coordenada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Coordenada[ id=" + id + " ]";
    }
    
}
