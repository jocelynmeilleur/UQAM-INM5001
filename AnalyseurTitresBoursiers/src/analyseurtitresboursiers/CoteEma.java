/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.util.Date;

/**
 *
 * @author jocelynm
 */
public class CoteEma {
    
    private Date date;
    private double prix;
    private int periode;

    public CoteEma(Date date, double prix, int periode) {
        this.date = date;
        this.prix = prix;
        this.periode = periode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    
    
    public boolean estValide() {
        
        return false;
    }

    @Override
    public String toString() {
        return "CoteEma{" + "date=" + date + ", prix=" + prix + ", periode=" + periode + '}';
    }
    
    
    
    
}
