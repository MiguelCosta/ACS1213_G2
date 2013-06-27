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
@Table(name = "tipomeiotransporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipomeiotransporte.findAll", query = "SELECT t FROM Tipomeiotransporte t"),
    @NamedQuery(name = "Tipomeiotransporte.findById", query = "SELECT t FROM Tipomeiotransporte t WHERE t.id = :id"),
    @NamedQuery(name = "Tipomeiotransporte.findByNome", query = "SELECT t FROM Tipomeiotransporte t WHERE t.nome = :nome")})
public class Tipomeiotransporte implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoMeioTransporteid")
    private Collection<Meiotransporte> meiotransporteCollection;

    public Tipomeiotransporte() {
    }

    public Tipomeiotransporte(Integer id) {
        this.id = id;
    }

    public Tipomeiotransporte(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @XmlTransient
    public Collection<Meiotransporte> getMeiotransporteCollection() {
        return meiotransporteCollection;
    }

    public void setMeiotransporteCollection(Collection<Meiotransporte> meiotransporteCollection) {
        this.meiotransporteCollection = meiotransporteCollection;
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
        if (!(object instanceof Tipomeiotransporte)) {
            return false;
        }
        Tipomeiotransporte other = (Tipomeiotransporte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Tipomeiotransporte[ id=" + id + " ]";
    }
    
}
