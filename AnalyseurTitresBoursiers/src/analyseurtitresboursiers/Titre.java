/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author ForestPierre
 */
@Entity
@Table(name = "titre", catalog = "titresboursiers", schema = "")
@NamedQueries({
    @NamedQuery(name = "Titre.findAll", query = "SELECT t FROM Titre t"),
    @NamedQuery(name = "Titre.findByIdtitre", query = "SELECT t FROM Titre t WHERE t.idtitre = :idtitre"),
    @NamedQuery(name = "Titre.findBySymbol", query = "SELECT t FROM Titre t WHERE t.symbol = :symbol"),
    @NamedQuery(name = "Titre.findByDescription", query = "SELECT t FROM Titre t WHERE t.description = :description"),
    @NamedQuery(name = "Titre.findByHistMaxDate", query = "SELECT t FROM Titre t WHERE t.histMaxDate = :histMaxDate"),
    @NamedQuery(name = "Titre.findByEnlot", query = "SELECT t FROM Titre t WHERE t.enlot = :enlot")})
public class Titre implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtitre")
    private Integer idtitre;
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "description")
    private String description;
    @Column(name = "histMaxDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histMaxDate;
    @Basic(optional = false)
    @Column(name = "enlot")
    private String enlot;

    public Titre() {
    }

    public Titre(Integer idtitre) {
        this.idtitre = idtitre;
    }

    public Titre(Integer idtitre, String enlot) {
        this.idtitre = idtitre;
        this.enlot = enlot;
    }

    public Integer getIdtitre() {
        return idtitre;
    }

    public void setIdtitre(Integer idtitre) {
        Integer oldIdtitre = this.idtitre;
        this.idtitre = idtitre;
        changeSupport.firePropertyChange("idtitre", oldIdtitre, idtitre);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        String oldSymbol = this.symbol;
        this.symbol = symbol;
        changeSupport.firePropertyChange("symbol", oldSymbol, symbol);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        changeSupport.firePropertyChange("description", oldDescription, description);
    }

    public Date getHistMaxDate() {
        return histMaxDate;
    }

    public void setHistMaxDate(Date histMaxDate) {
        Date oldHistMaxDate = this.histMaxDate;
        this.histMaxDate = histMaxDate;
        changeSupport.firePropertyChange("histMaxDate", oldHistMaxDate, histMaxDate);
    }

    public String getEnlot() {
        return enlot;
    }

    public void setEnlot(String enlot) {
        String oldEnlot = this.enlot;
        this.enlot = enlot;
        changeSupport.firePropertyChange("enlot", oldEnlot, enlot);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtitre != null ? idtitre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Titre)) {
            return false;
        }
        Titre other = (Titre) object;
        if ((this.idtitre == null && other.idtitre != null) || (this.idtitre != null && !this.idtitre.equals(other.idtitre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "analyseurtitresboursiers.Titre[ idtitre=" + idtitre + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
