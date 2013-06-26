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
@Table(name = "cidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c"),
    @NamedQuery(name = "Cidade.findById", query = "SELECT c FROM Cidade c WHERE c.id = :id"),
    @NamedQuery(name = "Cidade.findByNome", query = "SELECT c FROM Cidade c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cidade.findByPais", query = "SELECT c FROM Cidade c WHERE c.pais = :pais"),
    @NamedQuery(name = "Cidade.findByDistrito", query = "SELECT c FROM Cidade c WHERE c.distrito = :distrito"),
    @NamedQuery(name = "Cidade.findByRegiao", query = "SELECT c FROM Cidade c WHERE c.regiao = :regiao")})
public class Cidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
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
    @Column(name = "pais")
    private String pais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "distrito")
    private String distrito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "regiao")
    private String regiao;
    @OneToMany(mappedBy = "cidadeid")
    private Collection<Localparagem> localparagemCollection;
    @JoinColumn(name = "coordenadaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Coordenada coordenadaid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cidadeid")
    private Collection<Pontointeresse> pontointeresseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cidadeid")
    private Collection<Atividade> atividadeCollection;

    public Cidade() {
    }

    public Cidade(Integer id) {
        this.id = id;
    }

    public Cidade(Integer id, String nome, String pais, String distrito, String regiao) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
        this.distrito = distrito;
        this.regiao = regiao;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    @XmlTransient
    public Collection<Localparagem> getLocalparagemCollection() {
        return localparagemCollection;
    }

    public void setLocalparagemCollection(Collection<Localparagem> localparagemCollection) {
        this.localparagemCollection = localparagemCollection;
    }

    public Coordenada getCoordenadaid() {
        return coordenadaid;
    }

    public void setCoordenadaid(Coordenada coordenadaid) {
        this.coordenadaid = coordenadaid;
    }

    @XmlTransient
    public Collection<Pontointeresse> getPontointeresseCollection() {
        return pontointeresseCollection;
    }

    public void setPontointeresseCollection(Collection<Pontointeresse> pontointeresseCollection) {
        this.pontointeresseCollection = pontointeresseCollection;
    }

    @XmlTransient
    public Collection<Atividade> getAtividadeCollection() {
        return atividadeCollection;
    }

    public void setAtividadeCollection(Collection<Atividade> atividadeCollection) {
        this.atividadeCollection = atividadeCollection;
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
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Cidade[ id=" + id + " ]";
    }
    
}
