package utils;

//import static com.sun.org.glassfish.external.amx.AMXUtil.prop;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mailing {


  

    public static void mailing(String recipient) throws Exception {

        Properties prop = new Properties();
        final String moncompteEmail = "kiftrip@gmail.com";
        final String psw = "Kiftrip2021";
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session ses = Session.getInstance(prop, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(moncompteEmail, psw);
            }
        });

       

            Message msg = new MimeMessage(ses);
            msg.setFrom(new InternetAddress(moncompteEmail));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            msg.setSubject("Votre reclamation a été envoyé");
            msg.setContent("Votre reclamation est en cours de traitement, nous vous repondons dans 3 jours ouvrables </br> merci de bien patienter", "text/html");

            Transport.send(msg);

      

    }

    

    



}
