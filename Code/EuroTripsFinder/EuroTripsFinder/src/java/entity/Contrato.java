/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
    @NamedQuery(name = "Contrato.findById", query = "SELECT c FROM Contrato c WHERE c.id = :id"),
    @NamedQuery(name = "Contrato.findByValor", query = "SELECT c FROM Contrato c WHERE c.valor = :valor"),
    @NamedQuery(name = "Contrato.findByDatainicio", query = "SELECT c FROM Contrato c WHERE c.datainicio = :datainicio"),
    @NamedQuery(name = "Contrato.findByDatafim", query = "SELECT c FROM Contrato c WHERE c.datafim = :datafim")})
public class Contrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
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
    @JoinColumn(name = "clienteid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente clienteid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoid")
    private Collection<Artigopublicitario> artigopublicitarioCollection;

    public Contrato() {
    }

    public Contrato(Integer id) {
        this.id = id;
    }

    public Contrato(Integer id, BigDecimal valor, Date datainicio, Date datafim) {
        this.id = id;
        this.valor = valor;
        this.datainicio = datainicio;
        this.datafim = datafim;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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

    public Cliente getClienteid() {
        return clienteid;
    }

    public void setClienteid(Cliente clienteid) {
        this.clienteid = clienteid;
    }

    @XmlTransient
    public Collection<Artigopublicitario> getArtigopublicitarioCollection() {
        return artigopublicitarioCollection;
    }

    public void setArtigopublicitarioCollection(Collection<Artigopublicitario> artigopublicitarioCollection) {
        this.artigopublicitarioCollection = artigopublicitarioCollection;
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
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Contrato[ id=" + id + " ]";
    }
    
}
