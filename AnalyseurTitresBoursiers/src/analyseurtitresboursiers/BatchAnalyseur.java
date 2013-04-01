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
import org.apache.log4j.Logger;

/**
 *
 * @author jocelynm
 */
public class BatchAnalyseur {

    private final static String MSG_HEADER = "Bonjour, \n\nVoici votre rapport quotidien\n\n";
    private final static String MSG_ACHAT = "Recommandations ACHAT:\n";
    private final static String MSG_VENTE = "Recommandations VENTE:\n";
    private final static String MSG_SAUT_SECTION = "\n\n";
    private final static String MSG_ACTION_REQUISE = "Aucune action suggérée";
    private final static String MSG_ACTIONS_SUIVIS = "Liste des actions suivis:\n";
    private final static String MSG_FOOTER = "\u00a9 Forest/Meilleur - 2013";
    private List<TitreBoursier> recommendationsAchat = new ArrayList();
    private List<TitreBoursier> recommendationsVente = new ArrayList();
    private List<TitreBoursier> actionsSuivis = new ArrayList();
    private DatabaseLayor databaseLayor;
    static Logger logger = Logger.getLogger(BatchAnalyseur.class);

    public BatchAnalyseur(DatabaseLayor databaseLayor) {
        logger.info("BatchAnalyseur");
        this.databaseLayor = databaseLayor;
    }

    public void traiter() throws IOException, MalformedURLException, ParseException, SQLException {

        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.MONTH, -2);
        Date debut = gc.getTime();

        for (TitreBoursier titreAnalyse : this.databaseLayor.obtenirTitreEnLot()) {

            ArrayList<TitreBoursier> historique = this.databaseLayor.obtenirHistorique(titreAnalyse.getTitre(), debut);
            AnalysteMacd analyste = new AnalysteMacd(historique);

            TitreBoursier titreBoursier = historique.get(historique.size() - 1);

            actionsSuivis.add(titreBoursier);

            if (analyste.estAchatInteractif()) {
                recommendationsAchat.add(titreBoursier);
            } else {
                if (analyste.estGardeInteractif()) {
                    ;
                } else if (analyste.estNeutreInteractif()) {
                    ;
                } else {
                    recommendationsVente.add(titreBoursier);
                }
            }
        }
    }

    public List<TitreBoursier> getRecommandationsAchat() {
        return recommendationsAchat;
    }

    public List<TitreBoursier> getRecommandationsVente() {
        return recommendationsVente;
    }

    public List<TitreBoursier> getListeActionSuivis() {
        return actionsSuivis;
    }

    public void envoyerCourriel(String titre) {

        String msgBody = "";

        msgBody = msgBody + MSG_HEADER;

        msgBody = msgBody + MSG_ACHAT;

        if (0 < this.getRecommandationsAchat().size()) {
            for (TitreBoursier titreBoursier : this.getRecommandationsAchat()) {
                String msg = String.format("%s (%s): %.2f$ | %s\n", titreBoursier.getTitre(), titreBoursier.getDescription(), titreBoursier.getValeurFermeture(), titreBoursier.getDateFermeture());
                msgBody = msgBody + msg;
            }
        } else {
            msgBody = msgBody + MSG_ACTION_REQUISE;
        }

        msgBody = msgBody + MSG_SAUT_SECTION;

        msgBody = msgBody + MSG_VENTE;

        if (0 < this.getRecommandationsVente().size()) {
            for (TitreBoursier titreBoursier : this.getRecommandationsVente()) {
                String msg = String.format("%s (%s): %.2f$ | %s\n", titreBoursier.getTitre(), titreBoursier.getDescription(), titreBoursier.getValeurFermeture(), titreBoursier.getDateFermeture());
                msgBody = msgBody + msg;
            }
        } else {
            msgBody = msgBody + MSG_ACTION_REQUISE;
        }

        msgBody = msgBody + MSG_SAUT_SECTION;

        msgBody = msgBody + MSG_ACTIONS_SUIVIS;

        for (TitreBoursier titreBoursier : this.getListeActionSuivis()) {
            String msg = String.format("%s (%s)\n", titreBoursier.getTitre(), titreBoursier.getDescription());
            msgBody = msgBody + msg;
        }

        msgBody = msgBody + MSG_SAUT_SECTION;
        msgBody = msgBody + MSG_FOOTER;
        
        try {
            MailLayor.send(titre, msgBody);
            //System.out.println(titre + "\n" + msgBody);
        } catch (Exception exception) {
        
        }
        
    }
}
