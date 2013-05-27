/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "viagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Viagem.findAll", query = "SELECT v FROM Viagem v"),
    @NamedQuery(name = "Viagem.findById", query = "SELECT v FROM Viagem v WHERE v.id = :id"),
    @NamedQuery(name = "Viagem.findByNome", query = "SELECT v FROM Viagem v WHERE v.nome = :nome"),
    @NamedQuery(name = "Viagem.findByDescricao", query = "SELECT v FROM Viagem v WHERE v.descricao = :descricao"),
    @NamedQuery(name = "Viagem.findByDatainicio", query = "SELECT v FROM Viagem v WHERE v.datainicio = :datainicio"),
    @NamedQuery(name = "Viagem.findByDatafim", query = "SELECT v FROM Viagem v WHERE v.datafim = :datafim")})
public class Viagem implements Serializable {
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
    @JoinColumn(name = "Utilizadorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Utilizador utilizadorid;
    @JoinColumn(name = "percursoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Percurso percursoid;

    public Viagem() {
    }

    public Viagem(Integer id) {
        this.id = id;
    }

    public Viagem(Integer id, String nome, String descricao, Date datainicio, Date datafim) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.datainicio = datainicio;
        this.datafim = datafim;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Utilizador getUtilizadorid() {
        return utilizadorid;
    }

    public void setUtilizadorid(Utilizador utilizadorid) {
        this.utilizadorid = utilizadorid;
    }

    public Percurso getPercursoid() {
        return percursoid;
    }

    public void setPercursoid(Percurso percursoid) {
        this.percursoid = percursoid;
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
        if (!(object instanceof Viagem)) {
            return false;
        }
        Viagem other = (Viagem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Viagem[ id=" + id + " ]";
    }
    
}
