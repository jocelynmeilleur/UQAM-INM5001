/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author ForestPierre
 */
public class YahooFinance {
    
    
    public static ArrayList<TitreBoursier> getValeurFermeture(String symbol, Date debut) throws MalformedURLException, IOException, ParseException{
        
            URL url;
            BufferedReader reader;
            String sCurrentLine;
            String descTitre;
            ArrayList<TitreBoursier> historique = new ArrayList<>();
                      
             descTitre = getDescFromYahoo(symbol);
             
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          
             Date aujourdhui = new Date();
             Calendar cal = Calendar.getInstance();

             cal.setTime(debut);
             String a = String.valueOf(cal.get(Calendar.MONTH));
             String b =String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
             String c = String.valueOf(cal.get(Calendar.YEAR));
              
             cal.setTime(aujourdhui);
             String d = String.valueOf(cal.get(Calendar.MONTH));
             String e = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
             String f = String.valueOf(cal.get(Calendar.YEAR));
             
             String periode = "&a=" + a + "&b=" + b + "&c=" + c + "&d=" + d + "&e=" + e + "&f=" + f;
            //System.out.println("From date (a: month-1, b: day, c: year) - To date (d: month-1, e: day, f: year) - Trading period (g: (d)aily)");
            // selon http://code.google.com/p/yahoo-finance-managed/wiki/csvHistQuotesDownload
            // 9 Fev 2003
                    
            //url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker + "&a=06&b=9&c=1996&d=06&e=20&f=2010&g=d");
           
     
            url = new URL(Main.config.getUrlHistoriqueTitres() + symbol + periode);
            
                     
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            reader.readLine(); // skip header
            while ((sCurrentLine = reader.readLine()) != null) {
                String delims = "[,]+";
                String[] champs = sCurrentLine.split(delims);
                TitreBoursier titre = new TitreBoursier();
                titre.setTitre(symbol);
                titre.setDescription(descTitre);
                titre.setDateFermeture(formatter.parse(champs[0]));
                titre.setValeurFermeture(Double.parseDouble(champs[4]));
                historique.add(titre);
             }
            return historique;
        
    }
    
    public static String getDescFromYahoo(String symbol) throws MalformedURLException, IOException{
           
            URL url;
            BufferedReader reader;
                          
            url = new URL(Main.config.getUrlDescTitre() + symbol);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String delims = "[,]+";
            String[] champs = reader.readLine().split(delims);
            if (champs[1].contains("N/A")){
               return("N/A");
            }
            else {
                return(champs[0].replace("\"", ""));
            }
            
    }
    
    public static void ShowSymbolFromYahoo(){
        
     // inspiré de http://johnbokma.com/mexit/2008/08/19/java-open-url-default-browser.html 16 mars 2013

      if( !java.awt.Desktop.isDesktopSupported() ) {

            System.err.println( "Desktop is not supported (fatal)" );
            System.exit( 1 );
        }

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

        if( !desktop.isSupported( java.awt.Desktop.Action.BROWSE ) ) {

            System.err.println( "Desktop doesn't support the browse action (fatal)" );
            System.exit( 1 );
        }

            try {

                java.net.URI uri = new java.net.URI( "http://ca.finance.yahoo.com/lookup?s=.to" );
                desktop.browse( uri );
            }
            catch ( URISyntaxException | IOException e ) {

                System.err.println( e.getMessage() );
            }
                
    }
    
    
}

