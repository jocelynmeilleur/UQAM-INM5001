/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//import java.util.List;

/**
 *
 * @author jocelynm
 */
public class TitresBoursiers {

    private static ArrayList<TitreBoursier> tsx = new ArrayList<>();

    public TitresBoursiers()  {

        try {
            TitreBoursier titreBoursier;
            String titre = "BBD.B";
            String description = "Bombardier Inc.";
            Date dateFermeture;
            String patronDate = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(patronDate);

            dateFermeture = format.parse("2012-01-01");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 25.0);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-02");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.875);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-03");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.781);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-04");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.594);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-05");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.500);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-06");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.625);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-07");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 25.219);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-08");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 27.250);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-09");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 25);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-10");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 25);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-11");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.5);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-12");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 22);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-13");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 23.260);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-14");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 22.25);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-15");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 23.25);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-16");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 25);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-17");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.875);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-18");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.781);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-19");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.594);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-20");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.5);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-21");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.625);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-22");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 25.219);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-23");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 25);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-24");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 25.5);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-25");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 25.6);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-26");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.5);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-27");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 23);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-28");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 23);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-29");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 23);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-30");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 24.25);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-01-31");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 25);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-02-01");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 26.3);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-02-02");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 26);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-02-03");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 27.250);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-02-04");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 27.5);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-02-05");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 27.750);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-02-06");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 28);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-02-07");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 28);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-02-08");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 28.1);
            tsx.add(titreBoursier);

            dateFermeture = format.parse("2012-02-09");
            titreBoursier = new TitreBoursier(titre, description, dateFermeture, 28.8);
            tsx.add(titreBoursier);
            
            System.out.println("Taille " + tsx.size());

        } catch (ParseException exception) {
            System.err.println(exception.getMessage());
        }

    }

    public static ArrayList<TitreBoursier> getTsx() {
        return tsx;
    }
}
