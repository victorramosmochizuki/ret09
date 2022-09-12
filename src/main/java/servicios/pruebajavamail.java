/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.Usuario;

/**
 *
 * @author Renato
 */
public class pruebajavamail {

    public static String consult = "";

    public static void notificarCorreo(Usuario uss) throws Exception {
        String correo = uss.getEMAUSU();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("otaner983@gmail.com", "renato2020HUAMAN+");
            }
        });

        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date fechaActual = new Date(System.currentTimeMillis());
            String fechSystem = dateFormat2.format(fechaActual);
            String thisIp = InetAddress.getLocalHost().getHostAddress();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correo));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject("Correo de notificacion ");
            message.setText("BUENOS DIAS " + uss.getNOMUSU() + " USTED A INICIADO SESION AL SISTEMA EDUCASI"
                    + "\n con el correo: " + uss.getEMAUSU() + "\n desde la direccion IP: " + thisIp
                    + "\n en la Fecha : " + fechSystem
                    + "\n Muchas gracias por ser parte de educasi");

            Transport.send(message);
            Logger.getGlobal().log(Level.INFO, "HECHO");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) throws Exception {
        Usuario uss = new Usuario();
        uss.getEMAUSU("victor.ramos@vallegrande.edu.pe");
        notificarCorreo(uss);

    }
}
