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
@Table(name = "local")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Local.findAll", query = "SELECT l FROM Local l"),
    @NamedQuery(name = "Local.findById", query = "SELECT l FROM Local l WHERE l.id = :id"),
    @NamedQuery(name = "Local.findByNome", query = "SELECT l FROM Local l WHERE l.nome = :nome"),
    @NamedQuery(name = "Local.findByLatitude", query = "SELECT l FROM Local l WHERE l.latitude = :latitude"),
    @NamedQuery(name = "Local.findByLongitude", query = "SELECT l FROM Local l WHERE l.longitude = :longitude")})
public class Local implements Serializable {
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
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Column(name = "longitude")
    private BigDecimal longitude;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localChegadaid")
    private Collection<Servico> servicoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localPartidaid")
    private Collection<Servico> servicoCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localpartidaid")
    private Collection<Servicotaxi> servicotaxiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localchegadaid")
    private Collection<Servicotaxi> servicotaxiCollection1;
    @JoinColumn(name = "Cidadeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cidade cidadeid;

    public Local() {
    }

    public Local(Integer id) {
        this.id = id;
    }

    public Local(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
    public Collection<Servico> getServicoCollection() {
        return servicoCollection;
    }

    public void setServicoCollection(Collection<Servico> servicoCollection) {
        this.servicoCollection = servicoCollection;
    }

    @XmlTransient
    public Collection<Servico> getServicoCollection1() {
        return servicoCollection1;
    }

    public void setServicoCollection1(Collection<Servico> servicoCollection1) {
        this.servicoCollection1 = servicoCollection1;
    }

    @XmlTransient
    public Collection<Servicotaxi> getServicotaxiCollection() {
        return servicotaxiCollection;
    }

    public void setServicotaxiCollection(Collection<Servicotaxi> servicotaxiCollection) {
        this.servicotaxiCollection = servicotaxiCollection;
    }

    @XmlTransient
    public Collection<Servicotaxi> getServicotaxiCollection1() {
        return servicotaxiCollection1;
    }

    public void setServicotaxiCollection1(Collection<Servicotaxi> servicotaxiCollection1) {
        this.servicotaxiCollection1 = servicotaxiCollection1;
    }

    public Cidade getCidadeid() {
        return cidadeid;
    }

    public void setCidadeid(Cidade cidadeid) {
        this.cidadeid = cidadeid;
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
        if (!(object instanceof Local)) {
            return false;
        }
        Local other = (Local) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Local[ id=" + id + " ]";
    }
    
}
