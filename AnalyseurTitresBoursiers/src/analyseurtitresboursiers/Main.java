/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

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

           
       // Lire le fichier de configuration 
        config = new ConfigurationLayor(args[0]);
       // Connexion à la base de données
        dbAccess = new DatabaseLayor();
        dbAccess.setConnexionString(config.getConnexionString());
        dbAccess.connect();
        
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
