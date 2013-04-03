/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author jocelynm
 */
public class CoteBoursiere {
   
    private Date date;
    private double prixCloture;
    static Logger logger = Logger.getLogger(CoteBoursiere.class);
        
    public CoteBoursiere(Date date, double prixCloture) {
        this.date = date;
        this.prixCloture = prixCloture; 
    }
            
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrixCloture() {
        return prixCloture;
    }

    public void setPrixCloture(double prixCloture) {
        this.prixCloture = prixCloture;
    }
    
    public boolean estValide() {
        return true;
    }
    
    private boolean anneeMoisJourValide(String date) {
        
        boolean valide;
        
        String patronDate = "yyyy-MM-dd";
        SimpleDateFormat format  = new SimpleDateFormat(patronDate);
        
        format.setLenient(false);
        
        try {
            format.parse(date);
            valide = true;
        }  catch (ParseException e) {
            logger.error("Erreur de parser", e);
            logger.error(e.getMessage());
            valide = false;
        }

        return valide;
    }
    
    private boolean prixClotureValide(String prixCloture) {
        
        return (0 <= Double.parseDouble(prixCloture));
    }
    
    @Override
    public String toString() {
        return "CoteBoursiere{" + "date=" + date + ", prixCloture=" + prixCloture + '}';
    }
    
}
