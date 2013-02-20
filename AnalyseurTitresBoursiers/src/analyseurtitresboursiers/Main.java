/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author jocelynm
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {
//                    AnalysteInterface analysteInterface = new AnalysteInterface("Analyseur de titres boursiers", "BBD.B", "Bombardier Inc.");
//                    analysteInterface.pack();
//                    RefineryUtilities.centerFrameOnScreen(analysteInterface);
//                    analysteInterface.setVisible(true);
                    new InterfaceAnalyseur().setVisible(true);
                } catch (Exception exception) {

                    System.err.println(exception.getMessage());
                }
            }
        });
    }
}
