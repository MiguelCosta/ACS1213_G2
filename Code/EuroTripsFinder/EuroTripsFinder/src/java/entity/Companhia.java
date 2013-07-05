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
@Table(name = "companhia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Companhia.findAll", query = "SELECT c FROM Companhia c"),
    @NamedQuery(name = "Companhia.findById", query = "SELECT c FROM Companhia c WHERE c.id = :id"),
    @NamedQuery(name = "Companhia.findByNome", query = "SELECT c FROM Companhia c WHERE c.nome = :nome"),
    @NamedQuery(name = "Companhia.findByUrl", query = "SELECT c FROM Companhia c WHERE c.url = :url"),
    @NamedQuery(name = "Companhia.findByTimezone", query = "SELECT c FROM Companhia c WHERE c.timezone = :timezone"),
    @NamedQuery(name = "Companhia.findByLingua", query = "SELECT c FROM Companhia c WHERE c.lingua = :lingua"),
    @NamedQuery(name = "Companhia.findByTelefone", query = "SELECT c FROM Companhia c WHERE c.telefone = :telefone"),
    @NamedQuery(name = "Companhia.findByTicketurl", query = "SELECT c FROM Companhia c WHERE c.ticketurl = :ticketurl")})
public class Companhia implements Serializable {
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
    @Size(min = 1, max = 1024)
    @Column(name = "url")
    private String url;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "timezone")
    private String timezone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "lingua")
    private String lingua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "telefone")
    private String telefone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "ticketurl")
    private String ticketurl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companhiaid")
    private Collection<Rota> rotaCollection;

    public Companhia() {
    }

    public Companhia(Integer id) {
        this.id = id;
    }

    public Companhia(Integer id, String nome, String url, String timezone, String lingua, String telefone, String ticketurl) {
        this.id = id;
        this.nome = nome;
        this.url = url;
        this.timezone = timezone;
        this.lingua = lingua;
        this.telefone = telefone;
        this.ticketurl = ticketurl;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTicketurl() {
        return ticketurl;
    }

    public void setTicketurl(String ticketurl) {
        this.ticketurl = ticketurl;
    }

    @XmlTransient
    public Collection<Rota> getRotaCollection() {
        return rotaCollection;
    }

    public void setRotaCollection(Collection<Rota> rotaCollection) {
        this.rotaCollection = rotaCollection;
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
        if (!(object instanceof Companhia)) {
            return false;
        }
        Companhia other = (Companhia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Companhia[ id=" + id + " ]";
    }
    
}
