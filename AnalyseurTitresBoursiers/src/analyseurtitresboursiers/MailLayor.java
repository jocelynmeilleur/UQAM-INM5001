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

/**
 *
 * @author ForestPierre
 */
public  class MailLayor {
    
    public static void send( String sujet, String corps){
    
        // inspiré de : http://www.tutorialspoint.com/java/java_sending_email.htm Mars 2013
        
      String host = Main.config.getSmtpServer();

      // Get system properties
      Properties properties = System.getProperties();
      
            // Setup mail server
      
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress("analyseur@maison.com"));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(Main.config.getCourrielDestinataire()));

         // Set Subject: header field
         message.setSubject(sujet);

         // Now set the actual message
         message.setText(corps);

         // Send message
         Transport.send(message);
         System.out.println("Message envoyé....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
        
    }
    
}
