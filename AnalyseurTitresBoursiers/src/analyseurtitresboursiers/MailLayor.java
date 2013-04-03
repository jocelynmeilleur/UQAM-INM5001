/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseurtitresboursiers;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author ForestPierre
 *  
 */


public class MailLayor {
    
    static Logger logger = Logger.getLogger(MailLayor.class);
      
    public static void send(String sujet, String corps, String contentType) throws Exception{

        // inspiré de : http://www.tutorialspoint.com/java/java_sending_email.htm Mars 2013
        
        // contentType peut être: "text/html" ou "text/plain"

      
        String host = Main.config.getSmtpServer();

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server

        properties.setProperty("mail.smtp.host", host);


        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("analyseur@inm5001.uqam.ca"));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(Main.config.getCourrielDestinataire()));

            // Set Subject: header field
            message.setSubject(sujet);

            // Now set the actual message
            message.setContent(corps, contentType);

            if (Main.config.isSmtpAuthenticated()) {
                // SMTP authentifié
                Transport tr = session.getTransport("smtp");
                properties.setProperty("mail.smtp.auth", "true");
                properties.setProperty("mail.smtp.port", Main.config.getSmtpPort());
                tr.connect(host, Main.config.getSmtpUserName(), Main.config.getSmtpPassword());
                message.saveChanges();      // don't forget this
                tr.sendMessage(message, message.getAllRecipients());
                tr.close();
            } else {
                // Send message SMTP non authentifié
                Transport.send(message);
            }

            logger.info("Message envoyé....");
        } catch (MessagingException ex) {
            logger.error("Erreur de configuration courriel", ex);
            throw new Exception("Erreur de configuration courriel");
        }

    }
    
    public static void send(String sujet, String corps) throws Exception {
        send(sujet,corps,"text/plain");
    }
}
