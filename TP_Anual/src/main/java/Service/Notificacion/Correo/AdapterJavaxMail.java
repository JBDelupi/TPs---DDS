package Service.Notificacion.Correo;



import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class AdapterJavaxMail {
    private String host;
    private int port;
    private String username;
    private String password;

    public AdapterJavaxMail() {
        host = "smtp.gmail.com";
        port = 587;
        username = "deccocolaboraciones@gmail.com";
        password = "sgyh zfim iilv uzdx"; // Asegúrate de usar una contraseña de aplicación de Gmail.
//       contrasenia de la cuenta = "deccocolaboraciones2024"
    }



    public void enviar(String destinatario, String asunto, String cuerpo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", String.valueOf(port));
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Obtener la sesión con autenticación
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });


        try {
            // Creación del mensaje de correo
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setContent(cuerpo, "text/html");

            // Envío del mensaje
            Transport.send(message);

            System.out.println("El correo electrónico ha sido enviado exitosamente.");
        } catch (MessagingException e) {
            System.out.println("Error al enviar el correo electrónico: " + e.getMessage());
        }
    }

}