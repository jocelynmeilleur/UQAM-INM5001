/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author jocelynm
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static Logger logger = Logger.getLogger(Main.class);
    public static ConfigurationLayor config;
    public static DatabaseLayor dbAccess;

    public static void main(final String[] args) {

        PropertyConfigurator.configure("log4j.properties");

        logger.info("-------------------- Démarrage de l'application --------------------");

        boolean modeBatch = false;
        String cfg = "";

        int i = 0;
        while (i < args.length) {
            String arg;
            arg = args[i];

            if (arg.equals("-batch")) {
                logger.info("set batch mode on");
                modeBatch = true;
            }

            if (arg.equals("-cfg")) {
                logger.info("lire fichier de config");
                cfg = args[i + 1];
            }

            i++;
        }

        // Lire le fichier de configuration 
        config = new ConfigurationLayor(cfg);
        // Connexion à la base de données
        dbAccess = new DatabaseLayor();
        dbAccess.setConnexionString(config.getConnexionString());
        dbAccess.connect();

        if (modeBatch) {
            BatchAnalyseur batch = new BatchAnalyseur(dbAccess);

            try {
                batch.traiter();
                Date now = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d MMM yyyy", Locale.CANADA_FRENCH);
                batch.envoyerCourriel("AnalyseurTitresBoursiers - rapport quotidien: " + sdf.format(now));
            } catch (IOException | ParseException | SQLException exception) {
                logger.error("Erreur traitement du mode batch!", exception);
            }

        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {

                    try {
                        new InterfaceAnalyseur(dbAccess).setVisible(true);
                    } catch (Exception exception) {
                        logger.error("Erreur traitement du mode interface!", exception);
                    }
                }
            });
        }
    }
}
