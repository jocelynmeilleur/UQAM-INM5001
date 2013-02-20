/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

/**
 *
 * @author jocelynm
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

public class AnalysteInterface extends ApplicationFrame {

    private String description;
    private String symboleTitre;
    private String nomTitre;
    JFreeChart jfreechart1;
    JFreeChart jfreechart2;
    ChartPanel prixChartPanel;
    ChartPanel indiceChartPanel;
    JPanel recommandationPanel;
    JLabel labelRecommandation;
    JTextField texteRecommandation;
    private static AnalysteMacd analyste;
    private static TitresBoursiers tsx;

    public AnalysteInterface(String description, String symboleTitre, String nomTitre) throws ParseException {

        super(description);

        tsx = new TitresBoursiers();

        jfreechart1 = creerGraphePrix();
        prixChartPanel = new ChartPanel(jfreechart1, true, true, true, false, true);
        prixChartPanel.setPreferredSize(new Dimension(500, 270));

        jfreechart2 = creerGrapheIndice();
        indiceChartPanel = new ChartPanel(jfreechart2, true, true, true, false, true);
        prixChartPanel.setPreferredSize(new Dimension(500, 270));

        // Ajouter graphe Prix fermeture, EMA(12), EMA(26)
        this.add(prixChartPanel, BorderLayout.WEST);

        // Ajouter graphe Prix fermeture, EMA(12), EMA(26)
        this.add(indiceChartPanel, BorderLayout.EAST);

        // Ajouter bouton - Search
        recommandationPanel = new JPanel();
        /*
         * JButton addButton = new JButton("Hello Button");
         * buttonPanel.add(addButton);
         */
        labelRecommandation = new JLabel("Recommandation");
        recommandationPanel.add(labelRecommandation);

        texteRecommandation = new JTextField("-", 20);
        recommandationPanel.add(texteRecommandation);

        System.out.println("estAchatInteractif() " + analyste.estAchatInteractif());

        if (analyste.estAchatInteractif()) {
            Font font = new Font("Verdana", Font.BOLD, 12);
            texteRecommandation.setFont(font);
            texteRecommandation.setForeground(Color.WHITE);
            texteRecommandation.setBackground(Color.GREEN);
            texteRecommandation.setText("ACHAT");
        } else {

            if (analyste.estGardeInteractif()) {
                Font font = new Font("Verdana", Font.BOLD, 12);
                texteRecommandation.setFont(font);
                texteRecommandation.setForeground(Color.WHITE);
                texteRecommandation.setBackground(new Color(224,217,27));
                texteRecommandation.setText("GARDE");
            } else if (analyste.estNeutreInteractif()) {
                Font font = new Font("Verdana", Font.BOLD, 12);
                texteRecommandation.setFont(font);
                texteRecommandation.setForeground(Color.WHITE);
                texteRecommandation.setBackground(Color.GRAY);
                texteRecommandation.setText("NEUTRE");
            } else {
                Font font = new Font("Verdana", Font.BOLD, 12);
                texteRecommandation.setFont(font);
                texteRecommandation.setForeground(Color.WHITE);
                texteRecommandation.setBackground(Color.RED);
                texteRecommandation.setText("VENDRE");
            }
        }

        this.add(recommandationPanel, BorderLayout.SOUTH);

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNomTitre() {
        return nomTitre;
    }

    public void setNomTitre(String nomTitre) {
        this.nomTitre = nomTitre;
    }

    public String getSymboleTitre() {
        return symboleTitre;
    }

    public void setSymboleTitre(String symboleTitre) {
        this.symboleTitre = symboleTitre;
    }

    @Override
    public String toString() {
        return "AnalysteInterface{" + "description=" + description + ", symboleTitre=" + symboleTitre + ", nomTitre=" + nomTitre + '}';
    }

    private static JFreeChart creerGraphePrix() {

        JFreeChart jfreechart = null;

        try {
            XYDataset xydataset = creerDatasetPrix();

            String s = "BBD.B - prix";
            jfreechart = ChartFactory.createTimeSeriesChart(s, "Date", "Price", xydataset, false, true, false);
            XYPlot xyplot = (XYPlot) jfreechart.getPlot();
            NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
            numberaxis.setLowerMargin(0.40000000000000002D);
            DecimalFormat decimalformat = new DecimalFormat("00.00");
            numberaxis.setNumberFormatOverride(decimalformat);
            XYItemRenderer xyitemrenderer = xyplot.getRenderer();
            xyitemrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));

            xyplot.mapDatasetToRangeAxis(1, 1);
            XYBarRenderer xybarrenderer = new XYBarRenderer(0.20000000000000001D);
            xybarrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
            xyplot.setRenderer(1, xybarrenderer);

        } catch (ParseException exception) {

            System.err.println(exception.getMessage());
        }

        return jfreechart;
    }

    private static JFreeChart creerGrapheIndice() {

        JFreeChart jfreechart = null;

        try {
            XYDataset xydataset = creerDatasetIndice();

            String s = "BBD.B - indice";
            jfreechart = ChartFactory.createTimeSeriesChart(s, "Date", "Price", xydataset, false, true, false);
            XYPlot xyplot = (XYPlot) jfreechart.getPlot();
            NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
            numberaxis.setLowerMargin(0.40000000000000002D);
            DecimalFormat decimalformat = new DecimalFormat("00.00");
            numberaxis.setNumberFormatOverride(decimalformat);
            XYItemRenderer xyitemrenderer = xyplot.getRenderer();
            xyitemrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));

            xyplot.mapDatasetToRangeAxis(1, 1);
            XYBarRenderer xybarrenderer = new XYBarRenderer(0.20000000000000001D);
            xybarrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
            xyplot.setRenderer(1, xybarrenderer);

        } catch (ParseException exception) {

            System.err.println(exception.getMessage());
        }

        return jfreechart;
    }

    private static XYDataset creerDatasetPrix() throws ParseException {

        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries prixFermeture = new TimeSeries("Prix fermeture");
        TimeSeries emaMax = new TimeSeries("EMA Max");
        TimeSeries emaMin = new TimeSeries("EMA Min");

        AnalysteMacd analyste = new AnalysteMacd(tsx.getTsx());

        // Prix fermeture
        System.out.println("Taille de l'historique: " + analyste.getCotesBoursieres().size());

        for (int i = 0; i < analyste.getCotesBoursieres().size(); i++) {

            //TODO, s'assurer qu'il n'y a pas de doublons dans la liste sinon le graphique ne sera pas generer

            System.out.println(analyste.getCotesBoursieres().get(i).toString());
            prixFermeture.add(new Day(analyste.getCotesBoursieres().get(i).getDate()), analyste.getCotesBoursieres().get(i).getPrixCloture());
        }

        dataset.addSeries(prixFermeture);

        // EMA Max
        for (int i = 0; i < analyste.getHistoriqueCoteEmaMax().size(); i++) {

            //TODO, s'assurer qu'il n'y a pas de doublons dans la liste sinon le graphique ne sera pas generer

            System.out.println(analyste.getHistoriqueCoteEmaMax().get(i).toString());
            emaMax.add(new Day(analyste.getHistoriqueCoteEmaMax().get(i).getDate()), analyste.getHistoriqueCoteEmaMax().get(i).getPrix());
        }

        dataset.addSeries(emaMax);

        // EMA Min
        for (int i = 0; i < analyste.getHistoriqueCoteEmaMin().size(); i++) {

            //TODO, s'assurer qu'il n'y a pas de doublons dans la liste sinon le graphique ne sera pas generer

            System.out.println(analyste.getHistoriqueCoteEmaMin().get(i).toString());
            emaMin.add(new Day(analyste.getHistoriqueCoteEmaMin().get(i).getDate()), analyste.getHistoriqueCoteEmaMin().get(i).getPrix());
        }

        dataset.addSeries(emaMin);

        return dataset;
    }

    private static XYDataset creerDatasetIndice() throws ParseException {

        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries macd = new TimeSeries("MACD");
        TimeSeries ligneSignal = new TimeSeries("Ligne Signal");

        analyste = new AnalysteMacd(tsx.getTsx());

        // MACD
        for (int i = 0; i < analyste.getHistoriqueIndiceMacd().size(); i++) {

            //TODO, s'assurer qu'il n'y a pas de doublons dans la liste sinon le graphique ne sera pas generer

            System.out.println(analyste.getHistoriqueIndiceMacd().get(i).toString());
            macd.add(new Day(analyste.getHistoriqueIndiceMacd().get(i).getDate()), analyste.getHistoriqueIndiceMacd().get(i).getIndice());
        }

        dataset.addSeries(macd);

        // Ligne Signal
        for (int i = 0; i < analyste.getHistoriqueIndiceSignal().size(); i++) {

            //TODO, s'assurer qu'il n'y a pas de doublons dans la liste sinon le graphique ne sera pas generer

            System.out.println(analyste.getHistoriqueIndiceSignal().get(i).toString());
            ligneSignal.add(new Day(analyste.getHistoriqueIndiceSignal().get(i).getDate()), analyste.getHistoriqueIndiceSignal().get(i).getIndice());
        }

        dataset.addSeries(ligneSignal);

        return dataset;
    }
}
