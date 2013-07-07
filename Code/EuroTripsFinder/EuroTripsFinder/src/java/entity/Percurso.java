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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.jasper.tagplugins.jstl.ForEach;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "percurso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Percurso.findAll", query = "SELECT p FROM Percurso p"),
    @NamedQuery(name = "Percurso.findById", query = "SELECT p FROM Percurso p WHERE p.id = :id"),
    @NamedQuery(name = "Percurso.findByLimiteetapas", query = "SELECT p FROM Percurso p WHERE p.limiteetapas = :limiteetapas"),
    @NamedQuery(name = "Percurso.findByNumeroetapas", query = "SELECT p FROM Percurso p WHERE p.numeroetapas = :numeroetapas"),
    @NamedQuery(name = "Percurso.findByValortotal", query = "SELECT p FROM Percurso p WHERE p.valortotal = :valortotal"),
    @NamedQuery(name = "Percurso.findByNome", query = "SELECT p FROM Percurso p WHERE p.nome = :nome")})
public class Percurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "limiteetapas")
    private int limiteetapas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numeroetapas")
    private int numeroetapas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valortotal")
    private BigDecimal valortotal;
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;
    @JoinTable(name = "percurso_etapa", joinColumns = {
        @JoinColumn(name = "Percursoid", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "Etapaid", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Etapa> etapaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "percursoid")
    private Collection<Viagem> viagemCollection;
    @JoinColumn(name = "Utilizadorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Utilizador utilizadorid;

    public Percurso() {
    }

    public Percurso(Integer id) {
        this.id = id;
    }

    public Percurso(Integer id, int limiteetapas, int numeroetapas, BigDecimal valortotal) {
        this.id = id;
        this.limiteetapas = limiteetapas;
        this.numeroetapas = numeroetapas;
        this.valortotal = valortotal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLimiteetapas() {
        return limiteetapas;
    }

    public void setLimiteetapas(int limiteetapas) {
        this.limiteetapas = limiteetapas;
    }

    public int getNumeroetapas() {
        return numeroetapas;
    }

    public void setNumeroetapas(int numeroetapas) {
        this.numeroetapas = numeroetapas;
    }

    public BigDecimal getValortotal() {
        return valortotal;
    }

    public void setValortotal(BigDecimal valortotal) {
        this.valortotal = valortotal;
    }
    
    public BigDecimal getCustoTotal(){
        BigDecimal valor = BigDecimal.ZERO;
        for (Etapa etapa : etapaCollection) {
            valor = valor.add(etapa.getValor());
        }
        return valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Etapa> getEtapaCollection() {
        return etapaCollection;
    }

    public void setEtapaCollection(Collection<Etapa> etapaCollection) {
        this.etapaCollection = etapaCollection;
    }

    @XmlTransient
    public Collection<Viagem> getViagemCollection() {
        return viagemCollection;
    }

    public void setViagemCollection(Collection<Viagem> viagemCollection) {
        this.viagemCollection = viagemCollection;
    }

    public Utilizador getUtilizadorid() {
        return utilizadorid;
    }

    public void setUtilizadorid(Utilizador utilizadorid) {
        this.utilizadorid = utilizadorid;
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
        if (!(object instanceof Percurso)) {
            return false;
        }
        Percurso other = (Percurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Percurso[ id=" + id + " ]";
    }
    
}
