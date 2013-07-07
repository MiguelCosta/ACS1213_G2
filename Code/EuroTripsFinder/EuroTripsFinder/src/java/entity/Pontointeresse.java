/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author JorgeMaia
 */
@Entity
@Table(name = "pontointeresse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pontointeresse.findAll", query = "SELECT p FROM Pontointeresse p"),
    @NamedQuery(name = "Pontointeresse.findById", query = "SELECT p FROM Pontointeresse p WHERE p.id = :id"),
    @NamedQuery(name = "Pontointeresse.findByNome", query = "SELECT p FROM Pontointeresse p WHERE p.nome = :nome"),
    @NamedQuery(name = "Pontointeresse.findByDescricao", query = "SELECT p FROM Pontointeresse p WHERE p.descricao = :descricao")})
public class Pontointeresse implements Serializable, IPontoInteresse {
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
    @Size(min = 1, max = 2048)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "cidadeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cidade cidadeid;

    public Pontointeresse() {
    }

    public Pontointeresse(Integer id) {
        this.id = id;
    }

    public Pontointeresse(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pontointeresse)) {
            return false;
        }
        Pontointeresse other = (Pontointeresse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pontointeresse[ id=" + id + " ]";
    }
    
}
