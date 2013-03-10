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
    
    private final static String MSG_HEADER = "Bonjour, \nVoici votre rapport quotidien\n\n";
    private final static String MSG_ACHAT = "Recommandations ACHAT:\n";
    private final static String MSG_VENTE = "Recommandations VENTE:\n";
    private final static String MSG_SAUT_SECTION = "\n\n";
    private final static String MSG_FOOTER = "\u00a9 Forest/Meilleur - 2013";
    
    private List<TitreBoursier> recommendationsAchat = new ArrayList();
    private List<TitreBoursier> recommendationsVente = new ArrayList();
    
    private DatabaseLayor databaseLayor;
    

    public BatchAnalyseur(DatabaseLayor databaseLayor) {    
        this.databaseLayor = databaseLayor;
    }
    
    public void traiter() throws IOException, MalformedURLException, ParseException, SQLException {
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.MONTH, -6);
        Date debut = gc.getTime();
        
        ArrayList<TitreBoursier> historique = this.databaseLayor.obtenirHistorique("POW.TO", debut);
        AnalysteMacd analyste = new AnalysteMacd(historique);
        //System.out.println("Taille de l'historique: " + historique.size());
        //System.out.println("Taille de l'analyste: " + analyste.getCotesBoursieres().size());
        TitreBoursier titreBoursier = historique.get(historique.size() - 1);
        
        if (analyste.estAchatBatch()) {
            recommendationsAchat.add(titreBoursier);
        } 
        
        if (analyste.estVenteBatch()) {
            recommendationsVente.add(titreBoursier);
        }  
    }
    
    public List<TitreBoursier> getRecommandationsAchat() {
        return recommendationsAchat;
    }
    
    public List<TitreBoursier> getRecommandationsVente() {
        return recommendationsVente;
    }
    
    public void envoyerCourriel(String titre) {
        
        String msgBody = "";
        
//        System.out.println("Taille des recommandations ACHAT: " + this.getRecommandationsAchat().size());
//        System.out.println("Taille des recommandations VENTE: " + this.getRecommandationsVente().size());
        msgBody = msgBody + MSG_HEADER;
        
        msgBody = msgBody + MSG_ACHAT;
        
        for (TitreBoursier titreBoursier : this.getRecommandationsAchat()) {
            String msg = String.format("%s (%s): %.2f$ | %s", titreBoursier.getTitre(), titreBoursier.getDescription(), titreBoursier.getValeurFermeture(), titreBoursier.getDateFermeture()); 
            msgBody = msgBody +  msg;
        }
        
        msgBody = msgBody + MSG_SAUT_SECTION;
        
        msgBody = msgBody + MSG_VENTE;
        
        for (TitreBoursier titreBoursier : this.getRecommandationsVente()) {
            String msg = String.format("%s (%s): %.2f$ | %s", titreBoursier.getTitre(), titreBoursier.getDescription(), titreBoursier.getValeurFermeture(), titreBoursier.getDateFermeture()); 
            msgBody = msgBody +  msg;
        }
        
        msgBody = msgBody + MSG_SAUT_SECTION;
        
        msgBody = msgBody + MSG_FOOTER;

        MailLayor.send(titre, msgBody);
        //System.out.println(titre + "\n" + msgBody);
    }
    
}
