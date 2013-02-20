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
public class IndiceLigneSignal {
    
    private Date date;
    private double indice;

    public IndiceLigneSignal(Date date, double indice) {
        this.date = date;
        this.indice = indice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getIndice() {
        return indice;
    }

    public void setIndice(double indice) {
        this.indice = indice;
    }
    
    public boolean estValide() {
        return false;
    }

    @Override
    public String toString() {
        return "IndiceSignal{" + "date=" + date + ", indice=" + indice + '}';
    }
    
    
    
}
