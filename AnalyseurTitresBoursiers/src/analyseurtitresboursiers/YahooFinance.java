/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
             
             SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
          
            
            //System.out.println("From date (a: month-1, b: day, c: year) - To date (d: month-1, e: day, f: year) - Trading period (g: (d)aily)");
            // selon http://code.google.com/p/yahoo-finance-managed/wiki/csvHistQuotesDownload
            // 9 Fev 2003
                    
            //url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker + "&a=06&b=9&c=1996&d=06&e=20&f=2010&g=d");
           
            SimpleDateFormat sdf =new SimpleDateFormat("MM");
            String a = sdf.format(debut);
            sdf.applyPattern("dd");
            String b = sdf.format(debut);
            sdf.applyPattern("YYYY");
            String c = sdf.format(debut);
            Date aujourdhui = new Date();
            
            sdf.applyPattern("MM");
            String d = sdf.format(aujourdhui);
            sdf.applyPattern("dd");
            String e = sdf.format(aujourdhui);
            sdf.applyPattern("YYYY");
            String f = sdf.format(aujourdhui);
                        
            String urlDate = "&a=" + a + "&b=" + b + "&c=" + c + "&d=" + d + "&e=" + e + "&f=" + f;

            url = new URL(Main.config.getUrlHistoriqueTitres() + symbol + urlDate);
            
            System.out.println(url);
            
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
    
            return(reader.readLine().replace("\"", ""));
        
            
            
    }
    
    
    
}

