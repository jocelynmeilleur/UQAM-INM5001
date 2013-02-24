/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    
    public ArrayList<TitreBoursier> obtenirHistorique(String symbol, Date debut) throws IOException, MalformedURLException, ParseException, SQLException{
        
        ArrayList<TitreBoursier> historique = new ArrayList<>();
     
        ResultSet rs;
   
        String sql = "SELECT * FROM titre" +
               " JOIN historique ON titre.idtitre = historique.fk_titre" + 
               " WHERE symbol = '" + symbol + "'";
            
        rs=getResult(sql);
            
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
           historique = YahooFinance.getValeurFermeture(symbol,debut);
              
        }             
     
       return historique;
        
    }
    
    public ResultSet getResult(String sql){

        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = connexion.createStatement();
            //rs = stmt.executeQuery("SELECT * FROM testpf");
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            if (stmt.execute(sql)) {
                 rs = stmt.getResultSet();
            }
            // Now do something with the ResultSet ....
            rs.first();
            rs.next();
                      
            String  description =  "";
            description = rs.getNString("description"); 
                 
        }
        catch (SQLException ex){
        // handle any errors
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
        }

        return rs;
        
    }
    
     
    public String getDesc(String symbol) throws MalformedURLException, IOException, SQLException{
        
        ResultSet rs;
        String description;
        
        String sql = "SELECT * FROM titre" +
                    " WHERE symbol = '" + symbol + "'";
            
        rs=getResult(sql);
        
        // Now do something with the ResultSet ....
           if (rs.next()){
            description =  rs.getNString("description");      
           }
           else {
               description = YahooFinance.getDescFromYahoo(symbol);
           }
       
        return description;
        
    }
    
    public void uploadHistorique(ArrayList<TitreBoursier> historique) throws SQLException{
     
        
            Statement stmt = connexion.createStatement();
                   
            String sqlStmt = "INSERT INTO Titre (symbol,description,histMaxDate,enlot)" 
                       + " VALUES('%symbol%','%histMaxDate%','%description%','N')";
            String sql;
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                      
            sql = sqlStmt.replace("%symbol%",historique.get(0).getTitre());
            sql = sql.replace("%description%",historique.get(0).getDescription());
            sql = sql.replace("%histMaxDate%",formatter.format(historique.get(0).getDateFermeture()));
            System.out.println(sql);
            stmt.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            int idTitre = rs.getInt(1);
                   
            
            sqlStmt = "INSERT INTO historique (fk_titre,dateFermeture,valeurFermeture)" 
                       + " VALUES(%fk_titre%,'%dateFermeture%',%valeurFermeture%)";
         
            
            
            for(int x=0;x<historique.size();x++){
                sql = sqlStmt.replace("%fk_titre%",Integer.toString(idTitre));
                sql = sql.replace("%dateFermeture%",formatter.format(historique.get(x).getDateFermeture()));
                sql = sql.replace("%valeurFermeture%",Double.toString(historique.get(x).getValeurFermeture()));
                stmt.executeUpdate(sql);
            }
            
            
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
