/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "etapa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etapa.findAll", query = "SELECT e FROM Etapa e"),
    @NamedQuery(name = "Etapa.findById", query = "SELECT e FROM Etapa e WHERE e.id = :id"),
    @NamedQuery(name = "Etapa.findByDatapartida", query = "SELECT e FROM Etapa e WHERE e.datapartida = :datapartida"),
    @NamedQuery(name = "Etapa.findByDatachegada", query = "SELECT e FROM Etapa e WHERE e.datachegada = :datachegada"),
    @NamedQuery(name = "Etapa.findByValor", query = "SELECT e FROM Etapa e WHERE e.valor = :valor"),
    @NamedQuery(name = "Etapa.findByMeioTransporteid", query = "SELECT e FROM Etapa e WHERE e.meioTransporteid = :meioTransporteid")})
public class Etapa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datapartida")
    @Temporal(TemporalType.DATE)
    private Date datapartida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datachegada")
    @Temporal(TemporalType.DATE)
    private Date datachegada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MeioTransporteid")
    private int meioTransporteid;
    @ManyToMany(mappedBy = "etapaCollection")
    private Collection<Percurso> percursoCollection;
    @JoinColumn(name = "localparageminicialid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Localparagem localparageminicialid;
    @JoinColumn(name = "localparagemdestinoid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Localparagem localparagemdestinoid;

    public Etapa() {
    }

    public Etapa(Integer id) {
        this.id = id;
    }

    public Etapa(Integer id, Date datapartida, Date datachegada, BigDecimal valor, int meioTransporteid) {
        this.id = id;
        this.datapartida = datapartida;
        this.datachegada = datachegada;
        this.valor = valor;
        this.meioTransporteid = meioTransporteid;
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

    public String getDatapartidaString() {
        Calendar d = new GregorianCalendar();
        d.setTime(datapartida);
        String strData = d.get(Calendar.YEAR) + "-" + d.get(Calendar.MONTH) + "-" + d.get(Calendar.DAY_OF_MONTH);
        return strData;
    }

    public void setDatapartida(Date datapartida) {
        this.datapartida = datapartida;
    }

    public Date getDatachegada() {
        return datachegada;
    }

    public String getDatachegadaString() {
        Calendar d = new GregorianCalendar();
        d.setTime(datachegada);
        String strData = d.get(Calendar.YEAR) + "-" + d.get(Calendar.MONTH) + "-" + d.get(Calendar.DAY_OF_MONTH);
        return strData;
    }

    public void setDatachegada(Date datachegada) {
        this.datachegada = datachegada;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getMeioTransporteid() {
        return meioTransporteid;
    }

    public void setMeioTransporteid(int meioTransporteid) {
        this.meioTransporteid = meioTransporteid;
    }

    @XmlTransient
    public Collection<Percurso> getPercursoCollection() {
        return percursoCollection;
    }

    public void setPercursoCollection(Collection<Percurso> percursoCollection) {
        this.percursoCollection = percursoCollection;
    }

    public Localparagem getLocalparageminicialid() {
        return localparageminicialid;
    }

    public void setLocalparageminicialid(Localparagem localparageminicialid) {
        this.localparageminicialid = localparageminicialid;
    }

    public Localparagem getLocalparagemdestinoid() {
        return localparagemdestinoid;
    }

    public void setLocalparagemdestinoid(Localparagem localparagemdestinoid) {
        this.localparagemdestinoid = localparagemdestinoid;
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
        if (!(object instanceof Etapa)) {
            return false;
        }
        Etapa other = (Etapa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Etapa[ id=" + id + " ]";
    }
}
