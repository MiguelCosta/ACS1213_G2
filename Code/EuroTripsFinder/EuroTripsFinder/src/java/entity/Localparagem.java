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
@Table(name = "localparagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Localparagem.findAll", query = "SELECT l FROM Localparagem l"),
    @NamedQuery(name = "Localparagem.findById", query = "SELECT l FROM Localparagem l WHERE l.id = :id"),
    @NamedQuery(name = "Localparagem.findByNome", query = "SELECT l FROM Localparagem l WHERE l.nome = :nome"),
    @NamedQuery(name = "Localparagem.findByCodigo", query = "SELECT l FROM Localparagem l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "Localparagem.findByDescricao", query = "SELECT l FROM Localparagem l WHERE l.descricao = :descricao")})
public class Localparagem implements Serializable {
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
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "cidadeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cidade cidadeid;
    @JoinColumn(name = "coordenadaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Coordenada coordenadaid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localparagemdestinoid")
    private Collection<Etapa> etapaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "localparageminicialid")
    private Collection<Etapa> etapaCollection1;

    public Localparagem() {
    }

    public Localparagem(Integer id) {
        this.id = id;
    }

    public Localparagem(Integer id, String nome, String codigo, String descricao) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cidade getCidadeid() {
        return cidadeid;
    }

    public void setCidadeid(Cidade cidadeid) {
        this.cidadeid = cidadeid;
    }

    public Coordenada getCoordenadaid() {
        return coordenadaid;
    }

    public void setCoordenadaid(Coordenada coordenadaid) {
        this.coordenadaid = coordenadaid;
    }

    @XmlTransient
    public Collection<Etapa> getEtapaCollection() {
        return etapaCollection;
    }

    public void setEtapaCollection(Collection<Etapa> etapaCollection) {
        this.etapaCollection = etapaCollection;
    }

    @XmlTransient
    public Collection<Etapa> getEtapaCollection1() {
        return etapaCollection1;
    }

    public void setEtapaCollection1(Collection<Etapa> etapaCollection1) {
        this.etapaCollection1 = etapaCollection1;
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
        if (!(object instanceof Localparagem)) {
            return false;
        }
        Localparagem other = (Localparagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Localparagem[ id=" + id + " ]";
    }
    
}
