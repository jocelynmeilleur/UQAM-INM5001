/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author jocelynm
 */
public class InterfaceAnalyseur extends javax.swing.JFrame {

    private String description;
    private String symboleTitre;
    private String nomTitre;
    JFreeChart prixJFreechart;
    JFreeChart indiceJFreechart;
    private static AnalysteMacd analyste;
    private TitresBoursiers listeInitialisation;
    private static ArrayList<TitreBoursier> historique = null;

    /**
     * Creates new form InterfaceAnalyseur
     */
    public InterfaceAnalyseur() throws ParseException {

        listeInitialisation = new TitresBoursiers();
        initComponents();
        setConfigTab();

    }

    private static TimeSeries getSeriePrixFermeture(AnalysteMacd analyste) {
        
        TimeSeries prixFermeture = new TimeSeries("Prix fermeture");
        
        for (int i = 0; i < analyste.getCotesBoursieres().size(); i++) {

            //TODO, s'assurer qu'il n'y a pas de doublons dans la liste sinon le graphique ne sera pas generer

            //System.out.println(analyste.getCotesBoursieres().get(i).toString());
            prixFermeture.add(new Day(analyste.getCotesBoursieres().get(i).getDate()), analyste.getCotesBoursieres().get(i).getPrixCloture());   
        }
        
        return prixFermeture;
    }
    
    private static TimeSeries getSerieEmaMax(AnalysteMacd analyste) {
        
        TimeSeries emaMax = new TimeSeries("EMA Max");
        
        for (int i = 0; i < analyste.getHistoriqueCoteEmaMax().size(); i++) {

            //TODO, s'assurer qu'il n'y a pas de doublons dans la liste sinon le graphique ne sera pas generer

            //System.out.println(analyste.getCotesBoursieres().get(i).toString());
            emaMax.add(new Day(analyste.getHistoriqueCoteEmaMax().get(i).getDate()), analyste.getHistoriqueCoteEmaMax().get(i).getPrix());   
        }
        
        return emaMax;
    }
    
    private static TimeSeries getSerieEmaMin(AnalysteMacd analyste) {
        
        TimeSeries emaMin = new TimeSeries("EMA Min");
        
        for (int i = 0; i < analyste.getHistoriqueCoteEmaMin().size(); i++) {

            //TODO, s'assurer qu'il n'y a pas de doublons dans la liste sinon le graphique ne sera pas generer

            //System.out.println(analyste.getCotesBoursieres().get(i).toString());
            emaMin.add(new Day(analyste.getHistoriqueCoteEmaMin().get(i).getDate()), analyste.getHistoriqueCoteEmaMin().get(i).getPrix());   
        }
        
        return emaMin;
    }
    
        //TimeSeries ligneSignal = new TimeSeries("Ligne Signal");
    private static TimeSeries getSerieMacd(AnalysteMacd analyste) {
        
        TimeSeries macd = new TimeSeries("MACD");
        
        for (int i = 0; i < analyste.getHistoriqueIndiceMacd().size(); i++) {

            //TODO, s'assurer qu'il n'y a pas de doublons dans la liste sinon le graphique ne sera pas generer

            //System.out.println(analyste.getHistoriqueIndiceMacd().get(i).toString());
            macd.add(new Day(analyste.getHistoriqueIndiceMacd().get(i).getDate()), analyste.getHistoriqueIndiceMacd().get(i).getIndice());
        }
     
        return macd;
    }
    
    private static TimeSeries getSerieLigneSignal(AnalysteMacd analyste) {
        
        TimeSeries ligneSignal = new TimeSeries("Ligne Signal");
        
        for (int i = 0; i < analyste.getHistoriqueIndiceSignal().size(); i++) {

            //TODO, s'assurer qu'il n'y a pas de doublons dans la liste sinon le graphique ne sera pas generer

            //System.out.println(analyste.getHistoriqueIndiceMacd().get(i).toString());
            ligneSignal.add(new Day(analyste.getHistoriqueIndiceSignal().get(i).getDate()), analyste.getHistoriqueIndiceSignal().get(i).getIndice());
        }
     
        return ligneSignal;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panneauAnalyse = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextDesc = new javax.swing.JTextField();
        jButtonAnalyser = new javax.swing.JButton();
        labelRecommandation = new javax.swing.JLabel();
        texteRecommandation = new javax.swing.JTextField();
        prixChartPanel = new javax.swing.JPanel();
        prixJFreechart = creerGraphePrix();
        prixChartPanel = new ChartPanel(prixJFreechart, true, true, true, false, true);
        indiceChartPanel = new javax.swing.JPanel();
        indiceJFreechart = creerGrapheIndice();
        indiceChartPanel = new ChartPanel(indiceJFreechart, true, true, true, false, true);
        jTextTitre = new javax.swing.JTextField();
        jComboPeriode = new javax.swing.JComboBox();
        panneauConfiguration = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextUrlHist = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextUrlDesc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextConnBD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextSMTP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextCourriel = new javax.swing.JTextField();
        jButtonSaveConfig = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Titre Boursier");

        jTextDesc.setEditable(false);
        jTextDesc.setFocusable(false);

        jButtonAnalyser.setText("Analyser");
        jButtonAnalyser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnalyserActionPerformed(evt);
            }
        });

        labelRecommandation.setText("Recommandation");

        texteRecommandation.setBackground(new java.awt.Color(204, 204, 204));
        texteRecommandation.setColumns(20);

        javax.swing.GroupLayout prixChartPanelLayout = new javax.swing.GroupLayout(prixChartPanel);
        prixChartPanel.setLayout(prixChartPanelLayout);
        prixChartPanelLayout.setHorizontalGroup(
            prixChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );
        prixChartPanelLayout.setVerticalGroup(
            prixChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout indiceChartPanelLayout = new javax.swing.GroupLayout(indiceChartPanel);
        indiceChartPanel.setLayout(indiceChartPanelLayout);
        indiceChartPanelLayout.setHorizontalGroup(
            indiceChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 565, Short.MAX_VALUE)
        );
        indiceChartPanelLayout.setVerticalGroup(
            indiceChartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );

        jTextTitre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTitreActionPerformed(evt);
            }
        });
        jTextTitre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextTitreFocusLost(evt);
            }
        });

        jComboPeriode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 mois", "6 mois", "1 an", "5 ans", "10 ans" }));
        jComboPeriode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPeriodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panneauAnalyseLayout = new javax.swing.GroupLayout(panneauAnalyse);
        panneauAnalyse.setLayout(panneauAnalyseLayout);
        panneauAnalyseLayout.setHorizontalGroup(
            panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauAnalyseLayout.createSequentialGroup()
                .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panneauAnalyseLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panneauAnalyseLayout.createSequentialGroup()
                                .addComponent(prixChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addComponent(indiceChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panneauAnalyseLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextTitre)
                                    .addComponent(jComboPeriode, 0, 85, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonAnalyser, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panneauAnalyseLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelRecommandation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(texteRecommandation, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
        );
        panneauAnalyseLayout.setVerticalGroup(
            panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneauAnalyseLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAnalyser))
                .addGap(34, 34, 34)
                .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prixChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(indiceChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRecommandation)
                    .addComponent(texteRecommandation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Analyse", panneauAnalyse);

        jLabel2.setText("URL d'obtention de l'historique des titres boursiers: ");

        jLabel3.setText("URL d'obtention de la description des titres boursiers: ");

        jLabel4.setText("Ligne de connexion à la base de données locale:");

        jLabel5.setText("Serveur SMTP:");

        jLabel6.setText("Adresse courriel du destinataire des recommandations:");

        jButtonSaveConfig.setText("Sauvegarder");
        jButtonSaveConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveConfigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panneauConfigurationLayout = new javax.swing.GroupLayout(panneauConfiguration);
        panneauConfiguration.setLayout(panneauConfigurationLayout);
        panneauConfigurationLayout.setHorizontalGroup(
            panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneauConfigurationLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panneauConfigurationLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextSMTP, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panneauConfigurationLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextConnBD, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panneauConfigurationLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextUrlHist, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panneauConfigurationLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextUrlDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panneauConfigurationLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSaveConfig)
                            .addComponent(jTextCourriel, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(497, Short.MAX_VALUE))
        );
        panneauConfigurationLayout.setVerticalGroup(
            panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneauConfigurationLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextUrlHist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextUrlDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextConnBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextSMTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextCourriel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jButtonSaveConfig)
                .addContainerGap(334, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Configuration", panneauConfiguration);

        jMenu1.setText("Fichier");
        jMenu1.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                jMenu1MenuSelected(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Quitter");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuItem1.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jMenuItem1MenuKeyPressed(evt);
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setConfigTab(){
        
        // Populer les valeurs des champs de configuration
        
          jTextUrlHist.setText(Main.config.getUrlHistoriqueTitres());
          jTextUrlDesc.setText(Main.config.getUrlDescTitre());
          jTextConnBD.setText(Main.config.getConnexionString());
          jTextSMTP.setText(Main.config.getSmtpServer());
          jTextCourriel.setText(Main.config.getCourrielDestinataire());
         
    }
    
    private void jButtonAnalyserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnalyserActionPerformed
        // Le titre est dans jTextTitre
        // La période est dans jComboPeriode

        XYDataset data;
        TimeSeriesCollection dataset;

        Date debut;
        debut = getDateDebut(jComboPeriode.getSelectedIndex());

        try {
            historique = Main.dbAccess.obtenirHistorique(jTextTitre.getText(), debut);
            analyste = new AnalysteMacd(historique);
            System.out.println("Taille de l'historique: " + historique.size());
            System.out.println("Taille de l'analyste: " + analyste.getCotesBoursieres().size());

            XYPlot prixPlot = (XYPlot) prixJFreechart.getPlot();
            data = prixPlot.getDataset();
            dataset = (TimeSeriesCollection) data;
            dataset.removeSeries(2);
            dataset.removeSeries(1);
            dataset.removeSeries(0);           
            
            // Prix fermeture
            dataset.addSeries(getSeriePrixFermeture(analyste));

            // EMA Max
            dataset.addSeries(getSerieEmaMax(analyste));

            // EMA Min
            dataset.addSeries(getSerieEmaMin(analyste));
            
            XYPlot indicePlot = (XYPlot) indiceJFreechart.getPlot();
            data = indicePlot.getDataset();
            dataset = (TimeSeriesCollection) data;
            dataset.removeSeries(1);
            dataset.removeSeries(0);
            
            // MACD
            dataset.addSeries(getSerieMacd(analyste));
            
            //Ligne de signal
            dataset.addSeries(getSerieLigneSignal(analyste));

            updateComponents();

        } catch (IOException | ParseException | SQLException ex) {
            Logger.getLogger(InterfaceAnalyseur.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(historique);
        System.out.println(historique.get(0));

    }//GEN-LAST:event_jButtonAnalyserActionPerformed

    private Date getDateDebut(int selectedCombo) {

        Date aujourdhui = new Date();
        Date debut;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(aujourdhui);

        switch (selectedCombo) {
            case 0:
                gc.add(Calendar.MONTH, -1);  // 1 mois
                debut = gc.getTime();
                break;
            case 1:
                gc.add(Calendar.MONTH, -6); // 6 mois
                debut = gc.getTime();
                break;
            case 2:
                gc.add(Calendar.YEAR, -1);  // 1 an
                debut = gc.getTime();
                break;
            case 3:
                gc.add(Calendar.YEAR, -5); // 5 ans
                debut = gc.getTime();
                break;
            default:
                gc.add(Calendar.YEAR, -10); // 10 ans
                debut = gc.getTime();
        }

        return debut;
    }

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu1MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu1MenuSelected
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MenuSelected

    private void jMenuItem1MenuKeyPressed(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jMenuItem1MenuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1MenuKeyPressed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTextTitreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextTitreFocusLost

        // Afficher la description du titre

        try {
            jTextDesc.setText(Main.dbAccess.getDesc(jTextTitre.getText()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(InterfaceAnalyseur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(InterfaceAnalyseur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTextTitreFocusLost

    private void jComboPeriodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPeriodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboPeriodeActionPerformed

    private void jTextTitreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTitreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTitreActionPerformed

    private void jButtonSaveConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveConfigActionPerformed
        
        Main.config.setUrlHistoriqueTitres(jTextUrlHist.getText());
        Main.config.setUrlDescTitre(jTextUrlDesc.getText());
        Main.config.setConnexionString(jTextConnBD.getText());
        Main.config.setSmtpServer(jTextSMTP.getText());
        Main.config.setCourrielDestinataire(jTextCourriel.getText());
        Main.config.saveConfig();
        
        javax.swing.JOptionPane.showMessageDialog(null,"Configuration sauvegardée"); 
          
        
    }//GEN-LAST:event_jButtonSaveConfigActionPerformed

    private static JFreeChart creerGraphePrix() {

        JFreeChart jfreechart = null;

        try {
            XYDataset xydataset = creerDatasetPrix();

            String s = "Prix";
            jfreechart = ChartFactory.createTimeSeriesChart(s, "date", "prix", xydataset, false, true, false);
            
            XYPlot xyplot = (XYPlot) jfreechart.getPlot();
            NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
            numberaxis.setUpperMargin(.05); // Distance de $.05 entre la + grande valeur et la fin du tableau
            numberaxis.setLowerMargin(.05);
            
            DecimalFormat decimalformat = new DecimalFormat("00.00");
            numberaxis.setNumberFormatOverride(decimalformat);
          
            XYItemRenderer xyitemrenderer = xyplot.getRenderer();
            //Prix
            xyitemrenderer.setSeriesStroke(0, new BasicStroke(1.5f));  // Set line thickness
            xyitemrenderer.setSeriesPaint(0, Color.BLACK);             // Set line color
            //Ema Max
            xyitemrenderer.setSeriesStroke(1, new BasicStroke(1.0f));  
            xyitemrenderer.setSeriesPaint(1, Color.DARK_GRAY);
            //Ema Max
            xyitemrenderer.setSeriesStroke(2, new BasicStroke(1.0f)); 
            xyitemrenderer.setSeriesPaint(2, Color.GRAY);
                       
            /*  
            xyitemrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
            xyplot.mapDatasetToRangeAxis(1, 1);
            XYBarRenderer xybarrenderer = new XYBarRenderer(0.20000000000000001D);
            xybarrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
            xyplot.setRenderer(1, xybarrenderer);
            */
                      
        } catch (ParseException exception) {

            System.err.println(exception.getMessage());
        }

        return jfreechart;
    }

    private static JFreeChart creerGrapheIndice() {

        JFreeChart jfreechart = null;

        try {
            XYDataset xydataset = creerDatasetIndice();

            String s = "Indice";
            jfreechart = ChartFactory.createTimeSeriesChart(s, "date", "indice", xydataset, false, true, false);
            
            XYPlot xyplot = (XYPlot) jfreechart.getPlot();
            NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
            numberaxis.setUpperMargin(.05); // Distance de $.05 entre la + grande valeur et la fin du tableau
            numberaxis.setLowerMargin(.05);
            
            DecimalFormat decimalformat = new DecimalFormat("00.00");
            numberaxis.setNumberFormatOverride(decimalformat);
            
            XYItemRenderer xyitemrenderer = xyplot.getRenderer();
            //Macd
            xyitemrenderer.setSeriesStroke(1, new BasicStroke(1.0f));  
            xyitemrenderer.setSeriesPaint(1, Color.BLUE);
            //Ligne de signal
            xyitemrenderer.setSeriesStroke(2, new BasicStroke(1.0f)); 
            xyitemrenderer.setSeriesPaint(2, Color.RED);
            
            /*
            xyitemrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
            xyplot.mapDatasetToRangeAxis(1, 1);
            XYBarRenderer xybarrenderer = new XYBarRenderer(0.20000000000000001D);
            xybarrenderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
            xyplot.setRenderer(1, xybarrenderer);
            */

        } catch (ParseException exception) {

            System.err.println(exception.getMessage());
        }

        return jfreechart;
    }

    private static XYDataset creerDatasetPrix() throws ParseException {

        final TimeSeriesCollection dataset = new TimeSeriesCollection();

        analyste = new AnalysteMacd(TitresBoursiers.getListeInitialisation());
        
        // Prix fermeture
        dataset.addSeries(getSeriePrixFermeture(analyste));

        // EMA Max
        dataset.addSeries(getSerieEmaMax(analyste));

        // EMA Min
        dataset.addSeries(getSerieEmaMin(analyste));

        return dataset;
    }

    private static XYDataset creerDatasetIndice() throws ParseException {

        final TimeSeriesCollection dataset = new TimeSeriesCollection();

        analyste = new AnalysteMacd(TitresBoursiers.getListeInitialisation());

        // MACD
        dataset.addSeries(getSerieMacd(analyste));

        // Ligne Signal
        dataset.addSeries(getSerieLigneSignal(analyste));

        return dataset;
    }

    private void updateComponents() {

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
                texteRecommandation.setBackground(new Color(224, 217, 27));
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

    
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel indiceChartPanel;
    private javax.swing.JButton jButtonAnalyser;
    private javax.swing.JButton jButtonSaveConfig;
    private javax.swing.JComboBox jComboPeriode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextConnBD;
    private javax.swing.JTextField jTextCourriel;
    private javax.swing.JTextField jTextDesc;
    private javax.swing.JTextField jTextSMTP;
    private javax.swing.JTextField jTextTitre;
    private javax.swing.JTextField jTextUrlDesc;
    private javax.swing.JTextField jTextUrlHist;
    private javax.swing.JLabel labelRecommandation;
    private javax.swing.JPanel panneauAnalyse;
    private javax.swing.JPanel panneauConfiguration;
    private javax.swing.JPanel prixChartPanel;
    private javax.swing.JTextField texteRecommandation;
    // End of variables declaration//GEN-END:variables
}
