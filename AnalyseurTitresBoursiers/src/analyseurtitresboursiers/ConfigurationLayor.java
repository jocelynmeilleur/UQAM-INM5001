/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 *
 * @author ForestPierre
 */
public class ConfigurationLayor {

   private AppConfiguration configuration;
   private String urlHistoriqueTitres;
   private String urlDescTitre;
   private String urlTitresDispo;
   private String connexionString;
   private String smtpServer;
   private String courrielDestinataire;
   private String fichierConfig;
   private boolean smtpAuthenticated;
   private String smtpUserName;
   private String smtpPassword;
   private String smtpPort;
   static Logger logger = Logger.getLogger(ConfigurationLayor.class);
   
    public ConfigurationLayor(String fichierConfig){
        
        this.fichierConfig = fichierConfig;
                
       try {
		BufferedReader br = new BufferedReader(
			new FileReader(fichierConfig));
 
           configuration = new Gson().fromJson(br,AppConfiguration.class);
           urlHistoriqueTitres = configuration.getUrlHistoriqueTitres();
           urlDescTitre = configuration.getUrlDescTitre();
           urlTitresDispo = configuration.getUrlTitresDispo();
           connexionString = configuration.getConnexionString();
           smtpServer      = configuration.getSmtpServer();
           courrielDestinataire = configuration.getCourrielDestinataire();
           smtpAuthenticated = configuration.isSmtpAuthenticated();
           smtpUserName = configuration.getSmtpUserName();
           smtpPassword = configuration.getSmtpPassword();
           smtpPort    = configuration.getSmtpPort();
           

	} catch (IOException e) {
                logger.error("Erreur de configuration", e);
		//e.printStackTrace();
                throw new UnsupportedOperationException("Not yet implemented");
        }
    }

  
    public String getConnexionString() {
        return connexionString;
    }

    public void setConnexionString(String connexionString) {
        this.connexionString = connexionString;
    }

    public String getCourrielDestinataire() {
        return courrielDestinataire;
    }

    public void setCourrielDestinataire(String courrielDestinataire) {
        this.courrielDestinataire = courrielDestinataire;
    }

     public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public String getUrlHistoriqueTitres() {
        return urlHistoriqueTitres;
    }

    public void setUrlHistoriqueTitres(String urlHistoriqueTitres) {
        this.urlHistoriqueTitres = urlHistoriqueTitres;
    }
       
     public String getUrlDescTitre() {
        return urlDescTitre;
    }

    public void setUrlDescTitre(String urlDescTitre) {
        this.urlDescTitre = urlDescTitre;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String SmtpPort) {
        this.smtpPort = SmtpPort;
    }

    public boolean isSmtpAuthenticated() {
        return smtpAuthenticated;
    }

    public void setSmtpAuthenticated(boolean smtpAuthenticated) {
        this.smtpAuthenticated = smtpAuthenticated;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public String getSmtpUserName() {
        return smtpUserName;
    }

    public void setSmtpUserName(String smtpUserName) {
        this.smtpUserName = smtpUserName;
    }

    public String getUrlTitresDispo() {
        return urlTitresDispo;
    }

    public void setUrlTitresDispo(String urlTitresDispo) {
        this.urlTitresDispo = urlTitresDispo;
    }

    public String getFichierConfig() {
        return fichierConfig;
    }
    
    
    public void saveConfig(){
        
        configuration.setCourrielDestinataire(courrielDestinataire);
        configuration.setUrlHistoriqueTitres(urlHistoriqueTitres);
        configuration.setUrlTitresDispo(urlTitresDispo);
        configuration.setUrlDescTitre(urlDescTitre);
        configuration.setConnexionString(connexionString);
        configuration.setSmtpServer(smtpServer);
        configuration.setSmtpUserName(smtpUserName);
        configuration.setSmtpPassword(smtpPassword);
        configuration.setSmtpAuthenticated(smtpAuthenticated);
        configuration.setSmtpPort(smtpPort);
        
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(configuration);

            try {
            try (FileWriter writer = new FileWriter(fichierConfig)) {
                writer.write(json);
            }

            } catch (IOException e) {
                logger.error("Erreur de configuration", e);
                //e.printStackTrace();
                throw new UnsupportedOperationException("Not yet implemented");
            }
              
    }
       
    }
    
