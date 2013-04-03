/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author jocelynm
 */
public class AnalysteMacd {

    private static int EMA_MIN = 26;
    private static int EMA_MAX = 12;
    private static int EMA_MACD = 9;
    private GestionHistorique historiqueCoteBoursiere;
    private GestionHistorique historiqueCoteEmaMin;
    private GestionHistorique historiqueCoteEmaMax;
    private GestionHistorique historiqueIndiceMacd;
    private GestionHistorique historiqueIndiceSignal;
    static Logger logger = Logger.getLogger(AnalysteMacd.class);

    public AnalysteMacd(ArrayList<TitreBoursier> liste) {

        logger.info("AnalysteMacd");

        historiqueCoteBoursiere = new GestionHistorique();

        for (TitreBoursier coteBoursiere : liste) {

            try {
                CoteBoursiere cote = new CoteBoursiere(coteBoursiere.getDateFermeture(), coteBoursiere.getValeurFermeture());

                if (cote.estValide()) {
                    historiqueCoteBoursiere.ajouterAHistorique(cote);
                }
            } catch (Exception e) {
                //System.err.println(e.getMessage());
                logger.error("Erreur accès CoteBoursiere", e);
                logger.error(e.getMessage());
            }
        }

        this.genererListes();

    }

    private void genererListes() {

        double valeurLisseeMax;
        double valeurLisseeMin;
        double indiceMacd;
        double indiceLigneSignal;

        historiqueCoteEmaMax = new GestionHistorique();
        historiqueCoteEmaMin = new GestionHistorique();
        historiqueIndiceMacd = new GestionHistorique();
        historiqueIndiceSignal = new GestionHistorique();

        for (int i = 0; i < this.getCotesBoursieres().size(); i++) {

            Date date = this.getCotesBoursieres().get(i).getDate();
            double prixCloture = this.getCotesBoursieres().get(i).getPrixCloture();

            if (0 == i) {

                // Calculer EMA(12 jours)
                valeurLisseeMax = prixCloture;
                historiqueCoteEmaMax.ajouterAHistorique(new CoteEma(date, valeurLisseeMax, EMA_MAX));
                // Calculer EMA(26 jours)
                valeurLisseeMin = prixCloture;
                historiqueCoteEmaMin.ajouterAHistorique(new CoteEma(date, valeurLisseeMin, EMA_MIN));
                // Calculer MACD
                indiceMacd = (valeurLisseeMax - valeurLisseeMin);
                historiqueIndiceMacd.ajouterAHistorique(new IndiceMacd(date, indiceMacd));
                // Calculer EMA (MACD 9 jours)
                indiceLigneSignal = indiceMacd;
                historiqueIndiceSignal.ajouterAHistorique(new IndiceLigneSignal(date, indiceLigneSignal));

            } else {

                // Calculer EMA(12 jours)
                valeurLisseeMax = (getFacteurExponentiel(EMA_MAX) * (prixCloture))
                        + ((1 - getFacteurExponentiel(EMA_MAX)) * this.getHistoriqueCoteEmaMax().get(i - 1).getPrix());
                historiqueCoteEmaMax.ajouterAHistorique(new CoteEma(date, valeurLisseeMax, EMA_MAX));
                // Calculer EMA(26 jours)
                valeurLisseeMin = (getFacteurExponentiel(EMA_MIN) * (prixCloture))
                        + ((1 - getFacteurExponentiel(EMA_MIN)) * this.getHistoriqueCoteEmaMin().get(i - 1).getPrix());
                historiqueCoteEmaMin.ajouterAHistorique(new CoteEma(date, valeurLisseeMin, EMA_MIN));
                // Calculer MACD
                indiceMacd = (this.getHistoriqueCoteEmaMax().get(i).getPrix() - this.getHistoriqueCoteEmaMin().get(i).getPrix());
                historiqueIndiceMacd.ajouterAHistorique(new IndiceMacd(date, indiceMacd));
                // Calculer EMA (MACD 9 jours)
                indiceLigneSignal = (getFacteurExponentiel(EMA_MACD) * (indiceMacd))
                        + ((1 - getFacteurExponentiel(EMA_MACD)) * this.getHistoriqueIndiceSignal().get(i - 1).getIndice());
                historiqueIndiceSignal.ajouterAHistorique(new IndiceLigneSignal(date, indiceLigneSignal));

            }

        }

    }

    private double getFacteurExponentiel(int periode) {
        return (2.0 / (periode + 1));
    }

    public List<CoteBoursiere> getCotesBoursieres() {
        return historiqueCoteBoursiere.recupererHistorique();
    }

    public List<CoteEma> getHistoriqueCoteEmaMax() {
        return historiqueCoteEmaMax.recupererHistorique();
    }

    public List<CoteEma> getHistoriqueCoteEmaMin() {
        return historiqueCoteEmaMin.recupererHistorique();
    }

    public List<IndiceMacd> getHistoriqueIndiceMacd() {
        return historiqueIndiceMacd.recupererHistorique();
    }

    public List<IndiceLigneSignal> getHistoriqueIndiceSignal() {
        return historiqueIndiceSignal.recupererHistorique();
    }

    public boolean estValide() {
        return false;
    }

    private double getDivergenceDernier() {

        int positionDernier = (this.getHistoriqueIndiceMacd().size()) - 1;

        return this.getHistoriqueIndiceMacd().get(positionDernier).getIndice() - this.getHistoriqueIndiceSignal().get(positionDernier).getIndice();

    }

    private double getDivergenceAvantDernier() {

        int positionDernier = (this.getHistoriqueIndiceMacd().size()) - 1;
        int positionAvantDernier = positionDernier - 1;

        return this.getHistoriqueIndiceMacd().get(positionAvantDernier).getIndice() - this.getHistoriqueIndiceSignal().get(positionAvantDernier).getIndice();

    }

//    public boolean estAchatBatch() {
//
//        boolean decision = false;
//
//        if (0 < this.getDivergenceDernier()) {
//            if (0 >= this.getDivergenceAvantDernier()) {
//                decision = true;
//            }
//        }
//
//        return decision;
//    }
//
//    public boolean estVenteBatch() {
//
//        boolean decision = false;
//
//        if (0 > this.getDivergenceDernier()) {
//            if (0 <= this.getDivergenceAvantDernier()) {
//                decision = true;
//            }
//        }
//
//        return decision;
//    }

    public boolean estVenteInteractif() {

        boolean decision = false;

        if (0 > this.getDivergenceDernier()) {
            decision = true;
        }

        return decision;
    }

    public boolean estAchatInteractif() {

        boolean decision = false;

        if (0 < this.getDivergenceDernier()) {

            if (this.getDivergenceAvantDernier() <= this.getDivergenceDernier()) {

                decision = true;
            }
        }

        return decision;
    }
    
    public boolean estGardeInteractif() {

        boolean decision = false;

        if (0 < this.getDivergenceDernier()) {
            if (this.getDivergenceAvantDernier() > this.getDivergenceDernier()) {
                decision = true;
            }
        }

        return decision;
    }

    public boolean estNeutreInteractif() {

        boolean decision = false;

        //if ((0 == this.getDivergenceDernier()) || ( (0 > this.getDivergenceDernier()) && (this.getDivergenceAvantDernier() < this.getDivergenceDernier()) )) {
        if (0 == this.getDivergenceDernier()) {
            decision = true;
        }

        return decision;
    }
   
}
