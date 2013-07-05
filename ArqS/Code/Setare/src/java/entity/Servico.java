/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JorgeMaia
 */
@Entity
@Table(name = "servico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servico.findAll", query = "SELECT s FROM Servico s"),
    @NamedQuery(name = "Servico.findById", query = "SELECT s FROM Servico s WHERE s.id = :id"),
    @NamedQuery(name = "Servico.findByDataPartida", query = "SELECT s FROM Servico s WHERE s.dataPartida = :dataPartida"),
    @NamedQuery(name = "Servico.findByDataChegada", query = "SELECT s FROM Servico s WHERE s.dataChegada = :dataChegada"),
    @NamedQuery(name = "Servico.findByDepositoCheio", query = "SELECT s FROM Servico s WHERE s.depositoCheio = :depositoCheio"),
    @NamedQuery(name = "Servico.findByPreco", query = "SELECT s FROM Servico s WHERE s.preco = :preco"),
    @NamedQuery(name = "Servico.findByObs", query = "SELECT s FROM Servico s WHERE s.obs = :obs")})
public class Servico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataPartida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPartida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataChegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataChegada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "depositoCheio")
    private boolean depositoCheio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "preco")
    private BigDecimal preco;
    @Column(name = "Obs")
    private Integer obs;
    @JoinColumn(name = "Carroid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Carro carroid;
    @JoinColumn(name = "LocalChegadaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Local localChegadaid;
    @JoinColumn(name = "LocalPartidaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Local localPartidaid;
    @JoinColumn(name = "Utilizadorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Utilizador utilizadorid;

    public Servico() {
    }

    public Servico(Integer id) {
        this.id = id;
    }

    public Servico(Integer id, Date dataPartida, Date dataChegada, boolean depositoCheio, BigDecimal preco) {
        this.id = id;
        this.dataPartida = dataPartida;
        this.dataChegada = dataChegada;
        this.depositoCheio = depositoCheio;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Date dataPartida) {
        this.dataPartida = dataPartida;
    }

    public Date getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada) {
        this.dataChegada = dataChegada;
    }

    public boolean getDepositoCheio() {
        return depositoCheio;
    }

    public void setDepositoCheio(boolean depositoCheio) {
        this.depositoCheio = depositoCheio;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getObs() {
        return obs;
    }

    public void setObs(Integer obs) {
        this.obs = obs;
    }

    public Carro getCarroid() {
        return carroid;
    }

    public void setCarroid(Carro carroid) {
        this.carroid = carroid;
    }

    public Local getLocalChegadaid() {
        return localChegadaid;
    }

    public void setLocalChegadaid(Local localChegadaid) {
        this.localChegadaid = localChegadaid;
    }

    public Local getLocalPartidaid() {
        return localPartidaid;
    }

    public void setLocalPartidaid(Local localPartidaid) {
        this.localPartidaid = localPartidaid;
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
        if (!(object instanceof Servico)) {
            return false;
        }
        Servico other = (Servico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Servico[ id=" + id + " ]";
    }
    
}
