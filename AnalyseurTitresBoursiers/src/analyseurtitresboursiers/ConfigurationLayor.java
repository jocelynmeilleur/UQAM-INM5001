/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author ForestPierre
 */
public class ConfigurationLayor {

   private AppConfiguration configuration;
   private String urlHistoriqueTitres;
   private String urlDescTitre;
   private String connexionString;
   private String smtpServer;
   private String courrielDestinataire;

   
    public ConfigurationLayor(String fichierConfig){
        
       try {
		BufferedReader br = new BufferedReader(
			new FileReader(fichierConfig));
 
           configuration = new Gson().fromJson(br,AppConfiguration.class);
           urlHistoriqueTitres = configuration.getUrlHistoriqueTitres();
           urlDescTitre = configuration.getUrlDescTitre();
           connexionString = configuration.getConnexionString();
           smtpServer      = configuration.getSmtpServer();
           courrielDestinataire = configuration.getCourrielDestinataire();

	} catch (IOException e) {
		e.printStackTrace();
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
       
    }
    
