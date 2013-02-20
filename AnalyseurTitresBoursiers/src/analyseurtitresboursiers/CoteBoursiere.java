/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jocelynm
 */
public class CoteBoursiere {
   
    private Date date;
    private double prixCloture;
    
//    public CoteBoursiere(String date, String prixCloture) throws ParseException {
//        
//         if (!this.anneeMoisJourValide(date)) {
//            throw new IllegalArgumentException("probleme avec le format de la date, yyyy-MM-dd");
//        }
//
//        if (!this.prixClotureValide(prixCloture)) {
//            throw new IllegalArgumentException("prix de cloture non valide");
//        }
//        
//        String patronDate = "yyyy-MM-dd";
//        SimpleDateFormat format  = new SimpleDateFormat(patronDate);
//        
//        this.date = format.parse(date);
//        this.prixCloture = Double.parseDouble(prixCloture);
//    }
    
//    public CoteBoursiere(Date date, String prixCloture) {
//        this.date = date;
//        this.prixCloture = Double.parseDouble(prixCloture);
//    }
    
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
