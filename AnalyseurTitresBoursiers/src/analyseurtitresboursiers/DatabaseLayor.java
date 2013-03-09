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
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ForestPierre
 */
public class DatabaseLayor {

    private Connection connexion;
    private String connexionString;
    private ArrayList<TitreBoursier> titresBoursiers = new ArrayList<>();

    public DatabaseLayor() {
    }

    public boolean connect() {

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

        public ArrayList<TitreBoursier> obtenirTitreEnLot() throws IOException, MalformedURLException, ParseException, SQLException {

            // Retourne une collection des titres à traiter en lot
            // i.e. ceux dont enLot = "O"
            
            
        ArrayList<TitreBoursier> titresEnLot = new ArrayList<>();

        ResultSet rs = null;
        Statement stmt = null;

        String sql = "SELECT * FROM titre where Enlot = 'O'";
 
        try {
            stmt = connexion.createStatement();

            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
            }
            // Now do something with the ResultSet ....
            if (rs.next()) {
                while (!rs.isAfterLast()) {
                    TitreBoursier titre = new TitreBoursier();
                    titre.setTitre(rs.getNString("symbol"));
                    titre.setDescription(rs.getNString("description"));
                    titresEnLot.add(titre);
                    rs.next();
                }
            }


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }
        }

        return titresEnLot;

    }
    
    
    public ArrayList<TitreBoursier> obtenirHistorique(String symbol, Date debut) throws IOException, MalformedURLException, ParseException, SQLException {

        ArrayList<TitreBoursier> historique = new ArrayList<>();

        ResultSet rs = null;
        Statement stmt = null;
        Date histMaxDate = getMaxDate(symbol);
        Date aujourdhui = new Date();

        // Si l'historique n'est pas dans la BD, on l'ajoute

        if (histMaxDate == null) {  // pas d'historique dans la BD
            Calendar cal = Calendar.getInstance();
            cal.set(1900, 1, 1);  // On prend le maximum d'historique
            Date minDate = cal.getTime();
            uploadHistorique(symbol, minDate);
        } else {
            if (histMaxDate.compareTo(aujourdhui) < 0) {    // histMaxDate < aujourdhui
                // Charger l'historique récent
                uploadHistorique(symbol, histMaxDate);
            }
        }


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        String sql = "SELECT * FROM titre"
                + " JOIN historique ON titre.idtitre = historique.fk_titre"
                + " WHERE symbol = '" + symbol + "' AND dateFermeture > '" + formatter.format(debut) + "'"
                + " ORDER by dateFermeture";

        try {
            stmt = connexion.createStatement();

            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
            }
            // Now do something with the ResultSet ....
            if (rs.next()) {
                while (!rs.isAfterLast()) {
                    TitreBoursier titre = new TitreBoursier();
                    titre.setTitre(rs.getNString("symbol"));
                    titre.setDescription(rs.getNString("description"));
                    titre.setDateFermeture(rs.getDate("dateFermeture"));
                    titre.setValeurFermeture(rs.getDouble("valeurFermeture"));
                    historique.add(titre);
                    rs.next();
                }
            }


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }
        }


        return historique;

    }

    public Date getMaxDate(String symbol) {

        Date histMaxDate = null;
        ResultSet rs = null;
        Statement stmt = null;
        String sql = "SELECT * FROM titresBoursiers.titre where symbol = '" + symbol + "'";

        try {
            stmt = connexion.createStatement();

            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
            }
            // Now do something with the ResultSet ....
            if (rs.next()) {
                histMaxDate = rs.getDate("histMaxDate");
            }


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }
        }

        return histMaxDate;
    }

    public int getIdTitre(String symbol) {

        int idTitre = 0;
        ResultSet rs = null;
        Statement stmt = null;
        String sql = "SELECT * FROM titresBoursiers.titre where symbol = '" + symbol + "'";

        try {
            stmt = connexion.createStatement();

            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
            }
            // Now do something with the ResultSet ....
            if (rs.next()) {
                idTitre = rs.getInt("IdTitre");
            }


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }
        }

        return idTitre;
    }

    public String getDesc(String symbol) throws MalformedURLException, IOException, SQLException {

        ResultSet rs = null;
        Statement stmt = null;
        String description = "";

        try {
            stmt = connexion.createStatement();

            if (stmt.execute("SELECT description FROM titresBoursiers.titre where symbol = '" + symbol + "'")) {
                rs = stmt.getResultSet();
            }
            // Now do something with the ResultSet ....
            if (rs.next()) {
                description = rs.getNString("description");
            } else {
                description = YahooFinance.getDescFromYahoo(symbol);
            }


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore
            }
        }

        return description;
    }

    public boolean isExistTitre(String symbol) {

        boolean isExist;

        if (getIdTitre(symbol) == 0) {
            isExist = false;
        } else {
            isExist = true;
        }

        return isExist;
    }

    public void uploadHistorique(String symbol, Date debut) throws SQLException, MalformedURLException, IOException, ParseException {


        ArrayList<TitreBoursier> historique = YahooFinance.getValeurFermeture(symbol, debut);
        Statement stmt = connexion.createStatement();
        String sqlStmt;
        String sql;
        int idTitre;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // Ajout dans la Table Titre si elle n'existe pas déjà

        if (!isExistTitre(symbol)) {
            // Établir la requête de base et remplacer les %éléments% dans la requête
            Calendar cal = Calendar.getInstance();
            cal.set(1900, 1, 1);  // On prend le maximum d'historique
            Date minDate = cal.getTime();

            sqlStmt = "INSERT INTO Titre (symbol,description,histMaxDate,enlot)"
                    + " VALUES('%symbol%','%description%','%histMaxDate%','N')";

            sql = sqlStmt.replace("%symbol%", historique.get(0).getTitre());
            sql = sql.replace("%description%", historique.get(0).getDescription());
            sql = sql.replace("%histMaxDate%", formatter.format(minDate));
            System.out.println(sql);

            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            idTitre = rs.getInt(1);
        } else {
            idTitre = getIdTitre(symbol);
        }

        // Ajout de chacunes des valeurs de fermeture dans l'historique
        Date histMaxDate = getMaxDate(symbol);

        sqlStmt = "INSERT INTO historique (fk_titre,dateFermeture,valeurFermeture)"
                + " VALUES(%fk_titre%,'%dateFermeture%',%valeurFermeture%)";

        for (int x = 0; x < historique.size(); x++) {
            if ((historique.get(x).getDateFermeture()).compareTo(histMaxDate) > 0) {
                sql = sqlStmt.replace("%fk_titre%", Integer.toString(idTitre));
                sql = sql.replace("%dateFermeture%", formatter.format(historique.get(x).getDateFermeture()));
                sql = sql.replace("%valeurFermeture%", Double.toString(historique.get(x).getValeurFermeture()));
                stmt.executeUpdate(sql);
            }
        }

        // Update le MaxDate pour le titre
        sqlStmt = "UPDATE Titre SET histMaxDate = '%histMaxDate%' WHERE idTitre = %idTitre%";
        sql = sqlStmt.replace("%idTitre%", String.valueOf(idTitre));
        sql = sql.replace("%histMaxDate%", formatter.format(historique.get(0).getDateFermeture()));
        System.out.println(sql);
        stmt.executeUpdate(sql);




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
