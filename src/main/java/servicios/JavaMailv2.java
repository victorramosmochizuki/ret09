
package servicios;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import modelo.Proveedor;


public class JavaMailv2 {
    
    public static void enviarEmail(Proveedor pro) {
        String remitente = "otaner983@gmail.com";
        String clave = "renato2020HUAMAN+";
        String destinatario = pro.getEMAPROV();
        String asunto = "Bienvenidos a PizzVen";
        String cuerpo = "Tu contraseña es :37bd7372dd";
        String contraseña = "CONTRASEÑA DE ENVIO";
        
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", remitente);
        props.put("mail.smtp.clave", clave);
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(remitente));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);

            BodyPart texto = new MimeBodyPart();
            texto.setText(cuerpo);

            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            message.setContent(multiParte);
            
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", remitente, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException ex) {
            ex.printStackTrace();
            System.out.println("Error en enviarEmail " + ex.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            Proveedor proveedor = new Proveedor();
            proveedor.setEMAPROV("victor.ramos@vallegrande.edu.pe");
            JavaMailv2.enviarEmail(proveedor);
            System.out.println("CORREO ENVIADO");
        } catch (Exception ex) {
            System.out.println("ERROR AL ENVIAR CORREO");
            System.out.println("Error en enviarEmail_S " + ex.getMessage());
        }
    }
       
}


