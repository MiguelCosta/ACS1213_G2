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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JorgeMaia
 */
@Entity
@Table(name = "tempoparagem")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tempoparagem.findAll", query = "SELECT t FROM Tempoparagem t"),
    @NamedQuery(name = "Tempoparagem.findByHorachegada", query = "SELECT t FROM Tempoparagem t WHERE t.horachegada = :horachegada"),
    @NamedQuery(name = "Tempoparagem.findByHorapartida", query = "SELECT t FROM Tempoparagem t WHERE t.horapartida = :horapartida"),
    @NamedQuery(name = "Tempoparagem.findByLocalparagemsequencia", query = "SELECT t FROM Tempoparagem t WHERE t.localparagemsequencia = :localparagemsequencia"),
    @NamedQuery(name = "Tempoparagem.findByTempoparagemid", query = "SELECT t FROM Tempoparagem t WHERE t.tempoparagemid = :tempoparagemid")})
public class Tempoparagem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horachegada")
    @Temporal(TemporalType.TIME)
    private Date horachegada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horapartida")
    @Temporal(TemporalType.TIME)
    private Date horapartida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "localparagemsequencia")
    private int localparagemsequencia;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tempoparagemid")
    private Integer tempoparagemid;
    @JoinColumn(name = "transporteviagemid", referencedColumnName = "transporteviagemid")
    @ManyToOne(optional = false)
    private Transporteviagem transporteviagemid;
    @JoinColumn(name = "localparagemid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Localparagem localparagemid;

    public Tempoparagem() {
    }

    public Tempoparagem(Integer tempoparagemid) {
        this.tempoparagemid = tempoparagemid;
    }

    public Tempoparagem(Integer tempoparagemid, Date horachegada, Date horapartida, int localparagemsequencia) {
        this.tempoparagemid = tempoparagemid;
        this.horachegada = horachegada;
        this.horapartida = horapartida;
        this.localparagemsequencia = localparagemsequencia;
    }

    public Date getHorachegada() {
        return horachegada;
    }

    public void setHorachegada(Date horachegada) {
        this.horachegada = horachegada;
    }

    public Date getHorapartida() {
        return horapartida;
    }

    public void setHorapartida(Date horapartida) {
        this.horapartida = horapartida;
    }

    public int getLocalparagemsequencia() {
        return localparagemsequencia;
    }

    public void setLocalparagemsequencia(int localparagemsequencia) {
        this.localparagemsequencia = localparagemsequencia;
    }

    public Integer getTempoparagemid() {
        return tempoparagemid;
    }

    public void setTempoparagemid(Integer tempoparagemid) {
        this.tempoparagemid = tempoparagemid;
    }

    public Transporteviagem getTransporteviagemid() {
        return transporteviagemid;
    }

    public void setTransporteviagemid(Transporteviagem transporteviagemid) {
        this.transporteviagemid = transporteviagemid;
    }

    public Localparagem getLocalparagemid() {
        return localparagemid;
    }

    public void setLocalparagemid(Localparagem localparagemid) {
        this.localparagemid = localparagemid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tempoparagemid != null ? tempoparagemid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tempoparagem)) {
            return false;
        }
        Tempoparagem other = (Tempoparagem) object;
        if ((this.tempoparagemid == null && other.tempoparagemid != null) || (this.tempoparagemid != null && !this.tempoparagemid.equals(other.tempoparagemid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tempoparagem[ tempoparagemid=" + tempoparagemid + " ]";
    }
    
}
