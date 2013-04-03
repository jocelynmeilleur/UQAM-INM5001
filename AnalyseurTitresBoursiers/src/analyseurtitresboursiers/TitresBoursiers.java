/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author jocelynm
 */
public class TitresBoursiers {

    private static ArrayList<TitreBoursier> listeTitresBouriers = new ArrayList<>();
    static Logger logger = Logger.getLogger(DatabaseLayor.class);

    public TitresBoursiers()  {

        try {
            TitreBoursier titreBoursier;
            String titre = "-";
            String description = "Données d'initialisation";
            Date dateFermeture;
            String patronDate = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(patronDate);

            dateFermeture = format.parse("1971-01-01");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 0.0);
            listeTitresBouriers.add(titreBoursier);
         
            logger.info("TitresBoursiers() - Taille de la liste: " + listeTitresBouriers.size());

        } catch (ParseException exception) {
            logger.error("Erreur de parser", exception);
            logger.error(exception.getMessage());
        }

    }

    public static ArrayList<TitreBoursier> getListeInitialisation() {
        return listeTitresBouriers;
    }
}
