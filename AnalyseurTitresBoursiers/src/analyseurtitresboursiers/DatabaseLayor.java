/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author ForestPierre
 */



public class DatabaseLayor {

        private Connection connexion;
    private String connexionString;
    private ArrayList<TitreBoursier> titresBoursiers = new ArrayList<>();
    
    
    public DatabaseLayor(){
      
    }
    
    public boolean connect(){
        
        try {
            connexion = DriverManager.getConnection(connexionString);
            // Do something with the Connection
      
        } catch (SQLException ex) {
        // handle any errors
        System.out.println("SQLException: " + ex.getMessage());
        System.out.println("SQLState: " + ex.getSQLState());
        System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return true;
        
        
    }
    
    public ArrayList<TitreBoursier> obtenirHistorique(String symbol) throws IOException, MalformedURLException, ParseException{
        
        ArrayList<TitreBoursier> historique = new ArrayList<>();
     
        ResultSet rs = null;
        Statement stmt = null;
              
             try {
            stmt = connexion.createStatement();
            //rs = stmt.executeQuery("SELECT * FROM testpf");
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            String sql = "SELECT * FROM titre" +
               " JOIN historique ON titre.idtitre = historique.fk_titre" + 
               " WHERE symbol = '" + symbol + "'";
            
            if (stmt.execute(sql)) {
                 rs = stmt.getResultSet();
            }
            // Now do something with the ResultSet ....
            if (rs.next()){
              while (!rs.isAfterLast()){
                 TitreBoursier titre = new TitreBoursier();
                 titre.setTitre(rs.getNString("symbol"));
                 titre.setDescription(rs.getNString("description"));
                 titre.setDateFermeture(rs.getDate("dateFermeture"));
                 titre.setValeurFermeture(rs.getDouble("valeurFermeture"));
                 historique.add(titre);
                 rs.next();
                }          
            } else {
                // No data in Local DataBase
                historique = YahooFinance.getValeurFermeture(symbol);
               
            }             
      
        }
        catch (SQLException ex){
        // handle any errors
            
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
        // it is a good idea to release
        // resources in a finally{} block
        // in reverse-order of their creation
        // if they are no-longer needed
        if (rs != null) {
             try {
               rs.close();
             } catch (SQLException sqlEx) { } // ignore
            rs = null;
        }
        if (stmt != null) {
            try {
                 stmt.close();
            }  catch (SQLException sqlEx) { } // ignore
            stmt = null;
        }
        }
       
        return historique;
        
    }
    
    public void getData(){
    
        ResultSet rs = null;
        Statement stmt = null;
    
        try {
            stmt = connexion.createStatement();
            //rs = stmt.executeQuery("SELECT * FROM testpf");
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            if (stmt.execute("SELECT * FROM titre")) {
                 rs = stmt.getResultSet();
            }
            // Now do something with the ResultSet ....
            rs.first();
            System.out.println( rs.getNString("symbol"));
           
        }
        catch (SQLException ex){
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
        // it is a good idea to release
        // resources in a finally{} block
        // in reverse-order of their creation
        // if they are no-longer needed
        if (rs != null) {
             try {
               rs.close();
             } catch (SQLException sqlEx) { } // ignore
            rs = null;
        }
        if (stmt != null) {
            try {
                 stmt.close();
            }  catch (SQLException sqlEx) { } // ignore
            stmt = null;
        }
        }
        
      
    }
    
    public String getDesc(String symbol) throws MalformedURLException, IOException{
        
        ResultSet rs = null;
        Statement stmt = null;
        String description = "";
        
             try {
            stmt = connexion.createStatement();
            //rs = stmt.executeQuery("SELECT * FROM testpf");
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            if (stmt.execute("SELECT description FROM titresBoursiers.titre where symbol = '" + symbol + "'")) {
                 rs = stmt.getResultSet();
            }
            // Now do something with the ResultSet ....
           if (rs.next()){
            description =  rs.getNString("description");      
           }
           else {
               description = YahooFinance.getDescFromYahoo(symbol);
           }
         
           
        }
        catch (SQLException ex){
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
        // it is a good idea to release
        // resources in a finally{} block
        // in reverse-order of their creation
        // if they are no-longer needed
        if (rs != null) {
             try {
               rs.close();
             } catch (SQLException sqlEx) { } // ignore
            rs = null;
        }
        if (stmt != null) {
            try {
                 stmt.close();
            }  catch (SQLException sqlEx) { } // ignore
            stmt = null;
        }
        }
       
        return description;
        
    }
    
    
    
    

    public String getConnexionString() {
        return connexionString;
    }

    public void setConnexionString(String connexionString) {
        this.connexionString = connexionString;
    }

    public Connection getConnexion() {
        return connexion;
    }

    public void setConnexion(Connection connexion) {
        this.connexion = connexion;
    }

    public ArrayList<TitreBoursier> getTitresBoursiers() {
        return titresBoursiers;
    }

    public void setTitresBoursiers(ArrayList<TitreBoursier> titresBoursiers) {
        this.titresBoursiers = titresBoursiers;
    }
            
    
    
    
    
    
}
