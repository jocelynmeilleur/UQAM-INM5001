/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleEdge;

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
    private DatabaseLayor databaseLayor;
    private static NumberAxis numberAxisPrix = null;
    private static DateAxis dateAxisPrix = null;
    private static NumberAxis numberAxisIndice = null;
    private static DateAxis dateAxisIndice = null;
    private static LegendTitle legendPrix;
    private static LegendTitle legendIndice;

    /**
     * Creates new form InterfaceAnalyseur
     */
    public InterfaceAnalyseur(DatabaseLayor databaseLayor) throws ParseException {
        this.databaseLayor = databaseLayor;
        listeInitialisation = new TitresBoursiers();
        initComponents();
        setConfigTab();
        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
        jTextTitre.requestFocus();

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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        titresboursiersPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("titresboursiersPU").createEntityManager();
        titreQuery = java.beans.Beans.isDesignTime() ? null : titresboursiersPUEntityManager.createQuery("SELECT t FROM Titre t");
        titreList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : titreQuery.getResultList();
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
        jButtonVoirTitre = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
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
        jButtonTestMail = new javax.swing.JButton();
        jCheckBoxSMTP = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabelUserName = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jTextUserName = new javax.swing.JTextField();
        jLabelSMTPPort = new javax.swing.JLabel();
        jTextSMTPPort = new javax.swing.JTextField();
        jPasswordSMTP = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextTitreEnLot = new javax.swing.JTextField();
        jTextTitreEnLotDesc = new javax.swing.JTextField();
        jButtonAjouter = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEnLot = new javax.swing.JTable();
        jButtonSaveEnLot = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 0));

        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });

        panneauAnalyse.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Titre Boursier");

        jTextDesc.setEditable(false);
        jTextDesc.setFocusable(false);

        jButtonAnalyser.setText("Analyser");
        jButtonAnalyser.setNextFocusableComponent(jTextTitre);
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

        jTextTitre.setNextFocusableComponent(jComboPeriode);
        jTextTitre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTitreActionPerformed(evt);
            }
        });
        jTextTitre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextTitreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextTitreFocusLost(evt);
            }
        });

        jComboPeriode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 mois", "6 mois", "1 an", "5 ans", "10 ans" }));
        jComboPeriode.setNextFocusableComponent(jButtonAnalyser);
        jComboPeriode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPeriodeActionPerformed(evt);
            }
        });

        jButtonVoirTitre.setText("Voir titres disponibles...");
        jButtonVoirTitre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoirTitreActionPerformed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/analyseurtitresboursiers/yahoo.png"))); // NOI18N

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                .addComponent(indiceChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panneauAnalyseLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextTitre)
                                    .addComponent(jComboPeriode, 0, 85, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonAnalyser, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panneauAnalyseLayout.createSequentialGroup()
                                        .addComponent(jTextDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonVoirTitre)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(137, 137, 137))))
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
                .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panneauAnalyseLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonVoirTitre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboPeriode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAnalyser))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauAnalyseLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prixChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(indiceChartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(panneauAnalyseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRecommandation)
                    .addComponent(texteRecommandation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jTabbedPane1.addTab("Analyse", panneauAnalyse);

        panneauConfiguration.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("URL d'obtention de l'historique des titres boursiers: ");

        jLabel3.setText("URL d'obtention de la description des titres boursiers: ");

        jLabel4.setText("Ligne de connexion à la base de données locale:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Serveur sortant SMTP:");

        jLabel6.setText("Adresse courriel du destinataire des recommandations:");

        jButtonSaveConfig.setText("Sauvegarder");
        jButtonSaveConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveConfigActionPerformed(evt);
            }
        });

        jButtonTestMail.setText("Tester envoi courriel");
        jButtonTestMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTestMailActionPerformed(evt);
            }
        });

        jCheckBoxSMTP.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxSMTP.setText("Mon serveur sortant (SMTP) requiert une authentification");
        jCheckBoxSMTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSMTPActionPerformed(evt);
            }
        });

        jLabel8.setText("Se connecter à l'aide de:");

        jLabelUserName.setText("Nom d'utilisateur:");

        jLabelPassword.setText("Mot de passe:");

        jLabelSMTPPort.setText("No. Port Serveur sortant (SMTP):");

        javax.swing.GroupLayout panneauConfigurationLayout = new javax.swing.GroupLayout(panneauConfiguration);
        panneauConfiguration.setLayout(panneauConfigurationLayout);
        panneauConfigurationLayout.setHorizontalGroup(
            panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneauConfigurationLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panneauConfigurationLayout.createSequentialGroup()
                        .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBoxSMTP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonTestMail)
                            .addComponent(jButtonSaveConfig)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauConfigurationLayout.createSequentialGroup()
                                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextUrlHist, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextCourriel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                                    .addComponent(jTextSMTP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                                    .addComponent(jTextUrlDesc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                                    .addComponent(jTextConnBD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
                                .addGap(500, 500, 500))))
                    .addGroup(panneauConfigurationLayout.createSequentialGroup()
                        .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panneauConfigurationLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel8))
                            .addGroup(panneauConfigurationLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panneauConfigurationLayout.createSequentialGroup()
                                        .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelSMTPPort, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextSMTPPort, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPasswordSMTP, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panneauConfigurationLayout.createSequentialGroup()
                                        .addComponent(jLabelUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(454, 454, 454))))
        );
        panneauConfigurationLayout.setVerticalGroup(
            panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneauConfigurationLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextUrlHist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextUrlDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextConnBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panneauConfigurationLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxSMTP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8))
                    .addComponent(jTextSMTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUserName))
                .addGap(14, 14, 14)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordSMTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextSMTPPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSMTPPort))
                .addGap(18, 25, Short.MAX_VALUE)
                .addGroup(panneauConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panneauConfigurationLayout.createSequentialGroup()
                        .addComponent(jTextCourriel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jButtonTestMail)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSaveConfig))
                    .addComponent(jLabel6))
                .addContainerGap(185, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Configuration", panneauConfiguration);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Titre boursier");

        jTextTitreEnLot.setNextFocusableComponent(jButtonAjouter);
        jTextTitreEnLot.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextTitreEnLotFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextTitreEnLotFocusLost(evt);
            }
        });

        jTextTitreEnLotDesc.setEditable(false);

        jButtonAjouter.setText("Ajouter");
        jButtonAjouter.setNextFocusableComponent(jTextTitreEnLot);
        jButtonAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterActionPerformed(evt);
            }
        });

        jTableEnLot.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableEnLot.setColumnSelectionAllowed(true);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, titreList, jTableEnLot);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${symbol}"));
        columnBinding.setColumnName("Symbol");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${description}"));
        columnBinding.setColumnName("Description");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${enlot}"));
        columnBinding.setColumnName("Enlot");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTableEnLot);
        jTableEnLot.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableEnLot.getColumnModel().getColumn(0).setResizable(false);
        jTableEnLot.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTableEnLot.getColumnModel().getColumn(1).setResizable(false);
        jTableEnLot.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTableEnLot.getColumnModel().getColumn(2).setResizable(false);
        jTableEnLot.getColumnModel().getColumn(2).setPreferredWidth(50);

        jButtonSaveEnLot.setText("Sauvegarder");
        jButtonSaveEnLot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveEnLotActionPerformed(evt);
            }
        });

        jButton1.setText("Voir titres disposibles...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextTitreEnLot, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAjouter)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextTitreEnLotDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonSaveEnLot)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(636, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextTitreEnLot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextTitreEnLotDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAjouter)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonSaveEnLot)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Titres en lot", jPanel1);

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
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
                jMenuItem1MenuKeyPressed(evt);
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

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setConfigTab() {

        // Populer les valeurs des champs de configuration

        jTextUrlHist.setText(Main.config.getUrlHistoriqueTitres());
        jTextUrlDesc.setText(Main.config.getUrlDescTitre());
        jTextConnBD.setText(Main.config.getConnexionString());
        jTextSMTP.setText(Main.config.getSmtpServer());
        jTextCourriel.setText(Main.config.getCourrielDestinataire());
        jCheckBoxSMTP.setSelected(Main.config.isSmtpAuthenticated());
        jTextUserName.setText(Main.config.getSmtpUserName());
        jPasswordSMTP.setText(Main.config.getSmtpPassword());
        jTextSMTPPort.setText(Main.config.getSmtpPort());
    }

    private void jButtonAnalyserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnalyserActionPerformed
        // Le titre est dans jTextTitre
        // La période est dans jComboPeriode

        XYDataset data;
        TimeSeriesCollection dataset;

        Date debut;
        debut = getDateDebut(jComboPeriode.getSelectedIndex());
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        jButtonAnalyser.setEnabled(false);

        try {
            historique = this.databaseLayor.obtenirHistorique(jTextTitre.getText(), debut);
            if (historique.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, "Avertissement: Pas d'historique pour la période à analyser");
            }
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

            updateRecommandation();
            updateGraph();

        } catch (IOException | ParseException | SQLException ex) {
            Logger.getLogger(InterfaceAnalyseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        jButtonAnalyser.setEnabled(true);


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
        if (!jTextTitre.getText().isEmpty()) {

            jTextTitre.setText(jTextTitre.getText().toUpperCase());
            try {
                jTextDesc.setText(this.databaseLayor.getDesc(jTextTitre.getText()));
                if (jTextDesc.getText().contains("N/A")) {
                    jButtonAnalyser.setEnabled(false);
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(InterfaceAnalyseur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | SQLException ex) {
                Logger.getLogger(InterfaceAnalyseur.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        Main.config.setSmtpAuthenticated(jCheckBoxSMTP.isSelected());
        Main.config.setSmtpUserName(jTextUserName.getText());
        Main.config.setSmtpPassword(jPasswordSMTP.getText());
        Main.config.setSmtpPort(jTextSMTPPort.getText());
        Main.config.saveConfig();

        javax.swing.JOptionPane.showMessageDialog(null, "Configuration sauvegardée");


    }//GEN-LAST:event_jButtonSaveConfigActionPerformed

    private void jTextTitreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextTitreFocusGained
        // TODO add your handling code here:
        jButtonAnalyser.setEnabled(true);
    }//GEN-LAST:event_jTextTitreFocusGained

    private void jButtonTestMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTestMailActionPerformed
        // TODO add your handling code here:
        MailLayor.send("sujet message", "Ceci est le corps du message");
    }//GEN-LAST:event_jButtonTestMailActionPerformed

    private void jTextTitreEnLotFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextTitreEnLotFocusGained
        // TODO add your handling code here:
        jButtonAjouter.setEnabled(true);
    }//GEN-LAST:event_jTextTitreEnLotFocusGained

    private void jTextTitreEnLotFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextTitreEnLotFocusLost
        // TODO add your handling code here:
        // Afficher la description du titre
        if (!jTextTitreEnLot.getText().isEmpty()) {

            jTextTitreEnLot.setText(jTextTitreEnLot.getText().toUpperCase());

            try {
                jTextTitreEnLotDesc.setText(this.databaseLayor.getDesc(jTextTitreEnLot.getText()));
                if (jTextTitreEnLotDesc.getText().contains("N/A")) {
                    jButtonAjouter.setEnabled(false);
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(InterfaceAnalyseur.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | SQLException ex) {
                Logger.getLogger(InterfaceAnalyseur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTextTitreEnLotFocusLost

    private void jButtonSaveEnLotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveEnLotActionPerformed
        // TODO add your handling code here:
        titresboursiersPUEntityManager.getTransaction().begin();
        titresboursiersPUEntityManager.getTransaction().commit();
        javax.swing.JOptionPane.showMessageDialog(null, "Sélection de titres sauvegardée");

    }//GEN-LAST:event_jButtonSaveEnLotActionPerformed

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        // TODO add your handling code here:
        refreshEnLot();

    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void jButtonAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterActionPerformed

        ArrayList<TitreBoursier> temp = new ArrayList<>();
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        jButtonAjouter.setEnabled(false);

        try {
            // TODO add your handling code here:
            temp = this.databaseLayor.obtenirHistorique(jTextTitreEnLot.getText(), new Date());
        } catch (IOException | ParseException | SQLException ex) {
            Logger.getLogger(InterfaceAnalyseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshEnLot();
        jTextTitreEnLot.setText("");
        jTextTitreEnLotDesc.setText("");
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        jButtonAjouter.setEnabled(true);

    }//GEN-LAST:event_jButtonAjouterActionPerformed

    private void jButtonVoirTitreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoirTitreActionPerformed
        // TODO add your handling code here:
        YahooFinance.ShowSymbolFromYahoo();

    }//GEN-LAST:event_jButtonVoirTitreActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // TODO add your handling code here:
        YahooFinance.ShowSymbolFromYahoo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBoxSMTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSMTPActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxSMTP.isSelected()) {
            jLabelUserName.setEnabled(true);
            jLabelSMTPPort.setEnabled(true);
            jLabelPassword.setEnabled(true);
            jTextUserName.setEnabled(true);
            jPasswordSMTP.setEnabled(true);
            jTextSMTPPort.setEnabled(true);
        } else {
            jLabelUserName.setEnabled(false);
            jLabelSMTPPort.setEnabled(false);
            jLabelPassword.setEnabled(false);
            jTextUserName.setEnabled(false);
            jPasswordSMTP.setEnabled(false);
            jTextSMTPPort.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBoxSMTPActionPerformed

    private void refreshEnLot() {
        titreQuery = java.beans.Beans.isDesignTime() ? null : titresboursiersPUEntityManager.createQuery("SELECT t FROM Titre t ORDER by t.symbol");
        titreList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : titreQuery.getResultList();
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, titreList, jTableEnLot);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${symbol}"));
        columnBinding.setColumnName("Symbol");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${description}"));
        columnBinding.setColumnName("Description");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${enlot}"));
        columnBinding.setColumnName("Enlot");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTableEnLot);
        jTableEnLot.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableEnLot.getColumnModel().getColumn(0).setResizable(false);
        jTableEnLot.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTableEnLot.getColumnModel().getColumn(1).setResizable(false);
        jTableEnLot.getColumnModel().getColumn(1).setPreferredWidth(250);
        jTableEnLot.getColumnModel().getColumn(2).setResizable(false);
        jTableEnLot.getColumnModel().getColumn(2).setPreferredWidth(50);
        TableColumn enLotColumn = jTableEnLot.getColumnModel().getColumn(2);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTableEnLot.getColumnModel().getColumn(2).setCellRenderer( rightRenderer );
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("O");
        comboBox.addItem("N");
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer();
        //DefaultListCellRenderer.LEFT;
        //DefaultListCellRenderer.CENTER;
        //DefaultListCellRenderer.RIGHT;
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        comboBox.setRenderer(dlcr);
        enLotColumn.setCellEditor(new DefaultCellEditor(comboBox));
        jTableEnLot.revalidate();

    }

    private static JFreeChart creerGraphePrix() {

        JFreeChart jfreechart = null;

        try {
            XYDataset xydataset = creerDatasetPrix();

            String s = "Prix";
            jfreechart = ChartFactory.createTimeSeriesChart(s, "date", "prix", xydataset, true, true, false);

            XYPlot xyplot = (XYPlot) jfreechart.getPlot();

            numberAxisPrix = (NumberAxis) xyplot.getRangeAxis();
            numberAxisPrix.setVisible(false);
            numberAxisPrix.setUpperMargin(.05); // Distance de $.05 entre la + grande valeur et la fin du tableau
            numberAxisPrix.setLowerMargin(.05);

            dateAxisPrix = (DateAxis) xyplot.getDomainAxis();
            dateAxisPrix.setVisible(false);
            dateAxisPrix.setUpperMargin(.05);
            numberAxisPrix.setLowerMargin(.05);

            DecimalFormat decimalformat = new DecimalFormat("00.00");
            numberAxisPrix.setNumberFormatOverride(decimalformat);

            XYItemRenderer xyitemrenderer = xyplot.getRenderer();
            //Prix
            xyitemrenderer.setSeriesStroke(0, new BasicStroke(1.75f));  // Set line thickness
            xyitemrenderer.setSeriesPaint(0, Color.CYAN);             // Set line color
            //Ema Max
            xyitemrenderer.setSeriesStroke(1, new BasicStroke(1.0f));
            xyitemrenderer.setSeriesPaint(1, Color.DARK_GRAY);
            //Ema Max
            xyitemrenderer.setSeriesStroke(2, new BasicStroke(1.0f));
            xyitemrenderer.setSeriesPaint(2, Color.GREEN);

            legendPrix = jfreechart.getLegend();
            legendPrix.setPosition(RectangleEdge.BOTTOM);
            legendPrix.setVisible(false);

            /*
             * xyitemrenderer.setBaseToolTipGenerator(new
             * StandardXYToolTipGenerator("{0}: ({1}, {2})", new
             * SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
             * xyplot.mapDatasetToRangeAxis(1, 1); XYBarRenderer xybarrenderer =
             * new XYBarRenderer(0.20000000000000001D);
             * xybarrenderer.setBaseToolTipGenerator(new
             * StandardXYToolTipGenerator("{0}: ({1}, {2})", new
             * SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
             * xyplot.setRenderer(1, xybarrenderer);
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
            jfreechart = ChartFactory.createTimeSeriesChart(s, "date", "indice", xydataset, true, true, false);

            XYPlot xyplot = (XYPlot) jfreechart.getPlot();

            numberAxisIndice = (NumberAxis) xyplot.getRangeAxis();
            numberAxisIndice.setVisible(false);
            numberAxisIndice.setUpperMargin(.05); // Distance de $.05 entre la + grande valeur et la fin du tableau
            numberAxisIndice.setLowerMargin(.05);

            dateAxisIndice = (DateAxis) xyplot.getDomainAxis();
            dateAxisIndice.setVisible(false);
            dateAxisIndice.setUpperMargin(.05);
            numberAxisIndice.setLowerMargin(.05);

            DecimalFormat decimalformat = new DecimalFormat("00.00");
            numberAxisIndice.setNumberFormatOverride(decimalformat);

            XYItemRenderer xyitemrenderer = xyplot.getRenderer();
            //Macd
            xyitemrenderer.setSeriesStroke(1, new BasicStroke(1.0f));
            xyitemrenderer.setSeriesPaint(1, Color.BLUE);
            //Ligne de signal
            xyitemrenderer.setSeriesStroke(2, new BasicStroke(1.0f));
            xyitemrenderer.setSeriesPaint(2, Color.RED);

            legendIndice = jfreechart.getLegend();
            legendIndice.setPosition(RectangleEdge.BOTTOM);
            legendIndice.setVisible(false);

            /*
             * xyitemrenderer.setBaseToolTipGenerator(new
             * StandardXYToolTipGenerator("{0}: ({1}, {2})", new
             * SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));
             * xyplot.mapDatasetToRangeAxis(1, 1); XYBarRenderer xybarrenderer =
             * new XYBarRenderer(0.20000000000000001D);
             * xybarrenderer.setBaseToolTipGenerator(new
             * StandardXYToolTipGenerator("{0}: ({1}, {2})", new
             * SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")));
             * xyplot.setRenderer(1, xybarrenderer);
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

    private void updateRecommandation() {

        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.MONTH, -2);
        Date debut = gc.getTime();
        try {
            historique = this.databaseLayor.obtenirHistorique(jTextTitre.getText(), debut);
            analyste = new AnalysteMacd(historique);
            System.out.println("Taille de l'historique - recommendation: " + historique.size());
        } catch (IOException | ParseException | SQLException ex) {
            Logger.getLogger(InterfaceAnalyseur.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    private void updateGraph() {
        numberAxisPrix.setVisible(true);
        dateAxisPrix.setVisible(true);
        numberAxisIndice.setVisible(true);
        dateAxisIndice.setVisible(true);
        legendPrix.setVisible(true);
        legendIndice.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel indiceChartPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAjouter;
    private javax.swing.JButton jButtonAnalyser;
    private javax.swing.JButton jButtonSaveConfig;
    private javax.swing.JButton jButtonSaveEnLot;
    private javax.swing.JButton jButtonTestMail;
    private javax.swing.JButton jButtonVoirTitre;
    private javax.swing.JCheckBox jCheckBoxSMTP;
    private javax.swing.JComboBox jComboPeriode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelSMTPPort;
    private javax.swing.JLabel jLabelUserName;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordSMTP;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableEnLot;
    private javax.swing.JTextField jTextConnBD;
    private javax.swing.JTextField jTextCourriel;
    private javax.swing.JTextField jTextDesc;
    private javax.swing.JTextField jTextSMTP;
    private javax.swing.JTextField jTextSMTPPort;
    private javax.swing.JTextField jTextTitre;
    private javax.swing.JTextField jTextTitreEnLot;
    private javax.swing.JTextField jTextTitreEnLotDesc;
    private javax.swing.JTextField jTextUrlDesc;
    private javax.swing.JTextField jTextUrlHist;
    private javax.swing.JTextField jTextUserName;
    private javax.swing.JLabel labelRecommandation;
    private javax.swing.JPanel panneauAnalyse;
    private javax.swing.JPanel panneauConfiguration;
    private javax.swing.JPanel prixChartPanel;
    private javax.swing.JTextField texteRecommandation;
    private java.util.List<analyseurtitresboursiers.Titre> titreList;
    private javax.persistence.Query titreQuery;
    private javax.persistence.EntityManager titresboursiersPUEntityManager;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
