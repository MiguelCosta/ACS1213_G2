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
 * @author Miguel
 */
@Entity
@Table(name = "meiotransporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meiotransporte.findAll", query = "SELECT m FROM Meiotransporte m"),
    @NamedQuery(name = "Meiotransporte.findById", query = "SELECT m FROM Meiotransporte m WHERE m.id = :id"),
    @NamedQuery(name = "Meiotransporte.findByNome", query = "SELECT m FROM Meiotransporte m WHERE m.nome = :nome"),
    @NamedQuery(name = "Meiotransporte.findByServico", query = "SELECT m FROM Meiotransporte m WHERE m.servico = :servico")})
public class Meiotransporte implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "servico")
    private String servico;
    @JoinColumn(name = "TipoMeioTransporteid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tipomeiotransporte tipoMeioTransporteid;
    @JoinColumn(name = "Companhiaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Companhia companhiaid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "meioTransporteid")
    private Collection<Etapa> etapaCollection;

    public Meiotransporte() {
    }

    public Meiotransporte(Integer id) {
        this.id = id;
    }

    public Meiotransporte(Integer id, String nome, String servico) {
        this.id = id;
        this.nome = nome;
        this.servico = servico;
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

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public Tipomeiotransporte getTipoMeioTransporteid() {
        return tipoMeioTransporteid;
    }

    public void setTipoMeioTransporteid(Tipomeiotransporte tipoMeioTransporteid) {
        this.tipoMeioTransporteid = tipoMeioTransporteid;
    }

    public Companhia getCompanhiaid() {
        return companhiaid;
    }

    public void setCompanhiaid(Companhia companhiaid) {
        this.companhiaid = companhiaid;
    }

    @XmlTransient
    public Collection<Etapa> getEtapaCollection() {
        return etapaCollection;
    }

    public void setEtapaCollection(Collection<Etapa> etapaCollection) {
        this.etapaCollection = etapaCollection;
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
        if (!(object instanceof Meiotransporte)) {
            return false;
        }
        Meiotransporte other = (Meiotransporte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Meiotransporte[ id=" + id + " ]";
    }
    
}
