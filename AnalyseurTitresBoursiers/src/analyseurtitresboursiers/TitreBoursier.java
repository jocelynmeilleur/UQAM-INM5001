/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.util.Date;

/**
 *
 * @author ForestPierre
 */
public class TitreBoursier {
    
    private String titre;
    private String description;
    private Date dateFermeture;
    private double valeurFermeture;

    public TitreBoursier(String titre, String description, Date dateFermeture, double valeurFermeture) {
        this.titre = titre;
        this.description = description;
        this.dateFermeture = dateFermeture;
        this.valeurFermeture = valeurFermeture;
    }
    
    public TitreBoursier(){
        
    }

    public Date getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(Date dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getValeurFermeture() {
        return valeurFermeture;
    }

    public void setValeurFermeture(double valeurFermeture) {
        this.valeurFermeture = valeurFermeture;
    }
    
}
