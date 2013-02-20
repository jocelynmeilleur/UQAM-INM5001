/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jocelynm
 */
public class GestionHistorique<T> {
    
    private List<T> listeHistoriques;

    public GestionHistorique() {
        
        this.listeHistoriques = new ArrayList<>();
    }
    
    public void ajouterAHistorique(T element) {
        
        this.listeHistoriques.add(element);
    }
    
    public List<T> recupererHistorique() {
        return this.listeHistoriques;
    }
    
}
