package Service.Notificacion.Mensaje;

import Service.Notificacion.Correo.CorreoAdapter;
import Service.Notificacion.Notificacion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajeCanje implements Mensaje {
    private String asunto;
    private String destinatario;
    private String contenido;

    public MensajeCanje(String destinatario, String contenido) {
        this.destinatario = destinatario;
        this.asunto = "Canje realizado en Decco Colaboraciones";
        this.generarMensaje(contenido);
    }

    @Override
    public void generarMensaje(String informacion) {
        this.contenido = "<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Canje realizado exitosamente</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #f9f9f9;\n" +
                "        }\n" +
                "        .email-container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 20px auto;\n" +
                "            background: #ffffff;\n" +
                "            border-radius: 8px;\n" +
                "            overflow: hidden;\n" +
                "            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .email-header {\n" +
                "            background-color: #0F172B;\n" +
                "            color: #FEA116;\n" +
                "            text-align: center;\n" +
                "            padding: 20px;\n" +
                "        }\n" +
                "        .email-header h1 {\n" +
                "            background-color: #0F172B;\n" +
                "            margin: 0;\n" +
                "            font-size: 24px;\n" +
                "        }\n" +
                "        .email-content {\n" +
                "            padding: 20px;\n" +
                "            color: #000000;\n" +
                "        }\n" +
                "        .email-content p {\n" +
                "            font-size: 16px;\n" +
                "            line-height: 1.5;\n" +
                "            margin-bottom: 15px;\n" +
                "        }\n" +
                "        .email-content strong {\n" +
                "            color: #0047ab;\n" +
                "        }\n" +
                "        .email-logo {\n" +
                "            text-align: center;\n" +
                "            margin: 20px 0;\n" +
                "        }\n" +
                "        .email-logo img {\n" +
                "            width: 400px;\n" +
                "            border: none;\n" +
                "        }\n" +
                "        .email-footer {\n" +
                "            background-color: #f1f1f1;\n" +
                "            text-align: center;\n" +
                "            padding: 10px;\n" +
                "            font-size: 14px;\n" +
                "            color: #666666;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"email-container\">\n" +
                "        <div class=\"email-header\">\n" +
                "            <h1>¡Canje realizado exitosamente!</h1>\n" +
                "        </div>\n" +
                "        <div class=\"email-content\">\n" +
                "            <p>Nos alegra que formes parte de nuestra comunidad.</p>\n" +
                "            <p>Felicitaciones! Usted a realizado un canje del producto: <strong>"+ informacion +"</strong></p>\n" +
                "            <p>Nos pondremos en contacto en la brevedad para coordinar la entrega.</p>\n" +
                "            <p>Consulta si tienes alguna duda o necesitas más información.</p>\n" +
                "            <div class=\"email-logo\">\n" +
                "                <img src=\"https://i.ibb.co/12NHRP3/ace19094-2716-46a2-a71c-875244eb1f4c.webp\" alt=\"Logo Decco Canje\">\n" +
                "            </div>\n" +
                "            <p>Saludos cordiales,<br>El equipo de Decco Colaboraciones</p>\n" +
                "        </div>\n" +
                "        <div class=\"email-footer\">\n" +
                "            <p>&copy; 2024 Decco Colaboraciones. Todos los derechos reservados.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";


        // Aquí invocas al adaptador de correo para enviar la notificación
        Notificacion correoAdapter = new CorreoAdapter();
        correoAdapter.Notificar(this);
    }


}
