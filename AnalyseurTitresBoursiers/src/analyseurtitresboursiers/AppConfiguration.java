/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;


/**
 *
 * @author ForestPierre
 */
public class AppConfiguration {
    
    private String urlHistoriqueTitres;
    private String urlDescTitre;
    private String connexionString;
    private String smtpServer;
    private String courrielDestinataire;
   
     
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

