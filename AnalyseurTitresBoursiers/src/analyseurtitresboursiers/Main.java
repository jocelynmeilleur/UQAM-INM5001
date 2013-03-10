/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author jocelynm
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static ConfigurationLayor config;
    public static DatabaseLayor dbAccess;

    public static void main(final String[] args) {

        boolean modeBatch = false;
        String cfg = "";

        int i = 0;
        while (i < args.length) {
            String arg;
            arg = args[i];

            if (arg.equals("-batch")) {
                System.out.println("set batch mode on");
                modeBatch = true;
            }

            if (arg.equals("-cfg")) {
                System.out.println("lire fichier de config");
                cfg = args[i + 1];
            }

            i++;
        }

        // Lire le fichier de configuration 
        //config = new ConfigurationLayor(args[0]);
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
                batch.envoyerCourriel("AnalyseTitresBoursiers - rapport quotidien: " + sdf.format(now));
                    
            } catch (Exception exception) {
                System.err.println(exception.getMessage());
            }
            
//            try {
//                Thread.sleep(5 * 1000);
//            } catch (Exception exception) {
//                System.err.println(exception.getMessage());
//            }
 
        } else {
            java.awt.EventQueue.invokeLater(new Runnable() {

                @Override
                public void run() {

                    try {
                        new InterfaceAnalyseur().setVisible(true);
                    } catch (Exception exception) {

                        System.err.println(exception.getMessage());
                    }
                }
            });
        }

    }
}
