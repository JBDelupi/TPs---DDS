package Service.Notificacion;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class AdapterJavaxMail {
    private String host;
    private int port;
    private String username;
    private String password;

    public AdapterJavaxMail(){
        host = "smtp.gmail.com";
        port = 587;
        username = "nazaan01@gmail.com";
        password = "mkpvvsowicjnpbos";
    }

    private class SmtpAuthenticator extends Authenticator {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }

    public void enviar(String destinatario, String asunto, String cuerpo) {
        // Configuración del servidor de correo y credenciales
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        SmtpAuthenticator authenticator = new SmtpAuthenticator();

        Session session = Session.getInstance(props, authenticator);

        try {
            // Creación del mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            // Envío del mensaje
            Transport.send(message);

            System.out.println("El correo electrónico ha sido enviado exitosamente.");
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo electrónico: " + e.getMessage());
        }
    }




}