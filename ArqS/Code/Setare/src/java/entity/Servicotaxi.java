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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JorgeMaia
 */
@Entity
@Table(name = "servicotaxi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicotaxi.findAll", query = "SELECT s FROM Servicotaxi s"),
    @NamedQuery(name = "Servicotaxi.findById", query = "SELECT s FROM Servicotaxi s WHERE s.id = :id"),
    @NamedQuery(name = "Servicotaxi.findByDatapartida", query = "SELECT s FROM Servicotaxi s WHERE s.datapartida = :datapartida"),
    @NamedQuery(name = "Servicotaxi.findByBagagem", query = "SELECT s FROM Servicotaxi s WHERE s.bagagem = :bagagem"),
    @NamedQuery(name = "Servicotaxi.findByPassageiros", query = "SELECT s FROM Servicotaxi s WHERE s.passageiros = :passageiros"),
    @NamedQuery(name = "Servicotaxi.findByCodigotaxi", query = "SELECT s FROM Servicotaxi s WHERE s.codigotaxi = :codigotaxi")})
public class Servicotaxi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datapartida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datapartida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bagagem")
    private boolean bagagem;
    @Column(name = "passageiros")
    private Integer passageiros;
    @Size(max = 255)
    @Column(name = "codigotaxi")
    private String codigotaxi;
    @JoinColumn(name = "Localpartidaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Local localpartidaid;
    @JoinColumn(name = "Localchegadaid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Local localchegadaid;
    @JoinColumn(name = "Utilizadorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Utilizador utilizadorid;

    public Servicotaxi() {
    }

    public Servicotaxi(Integer id) {
        this.id = id;
    }

    public Servicotaxi(Integer id, Date datapartida, boolean bagagem) {
        this.id = id;
        this.datapartida = datapartida;
        this.bagagem = bagagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatapartida() {
        return datapartida;
    }

    public void setDatapartida(Date datapartida) {
        this.datapartida = datapartida;
    }

    public boolean getBagagem() {
        return bagagem;
    }

    public void setBagagem(boolean bagagem) {
        this.bagagem = bagagem;
    }

    public Integer getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(Integer passageiros) {
        this.passageiros = passageiros;
    }

    public String getCodigotaxi() {
        return codigotaxi;
    }

    public void setCodigotaxi(String codigotaxi) {
        this.codigotaxi = codigotaxi;
    }

    public Local getLocalpartidaid() {
        return localpartidaid;
    }

    public void setLocalpartidaid(Local localpartidaid) {
        this.localpartidaid = localpartidaid;
    }

    public Local getLocalchegadaid() {
        return localchegadaid;
    }

    public void setLocalchegadaid(Local localchegadaid) {
        this.localchegadaid = localchegadaid;
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
        if (!(object instanceof Servicotaxi)) {
            return false;
        }
        Servicotaxi other = (Servicotaxi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Servicotaxi[ id=" + id + " ]";
    }
    
}
