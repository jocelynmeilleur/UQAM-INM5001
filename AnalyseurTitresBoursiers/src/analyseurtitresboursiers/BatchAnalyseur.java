/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

/**
 *
 * @author jocelynm
 */
public class BatchAnalyseur {
    
    private List<String> recommendationsAchat = new ArrayList();
    private List<String> recommendationsVente = new ArrayList();
    
    private DatabaseLayor databaseLayor;
    

    public BatchAnalyseur(DatabaseLayor databaseLayor) {
        
        this.databaseLayor = databaseLayor;
        System.out.println("C'est parti pour l'analyse en batch");
    }
    
    public void traiter() throws IOException, MalformedURLException, ParseException, SQLException {
        
        Date debut;
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.MONTH, -6);
        debut = gc.getTime();
        
        ArrayList<TitreBoursier> historique = this.databaseLayor.obtenirHistorique("POW.TO", debut);
        AnalysteMacd analyste = new AnalysteMacd(historique);
        System.out.println("Taille de l'historique: " + historique.size());
        System.out.println("Taille de l'analyste: " + analyste.getCotesBoursieres().size());
        
        if (analyste.estAchatBatch()) {
            recommendationsAchat.add("POW.TO");
        } 
        
    }
    
    public List<String> getRecommandationsAchat() {
        return recommendationsAchat;
    }
    
    public List<String> getRecommandationsVente() {
        return recommendationsVente;
    }
    
}
