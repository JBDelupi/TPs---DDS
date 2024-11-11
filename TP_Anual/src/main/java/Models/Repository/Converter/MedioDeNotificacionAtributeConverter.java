package Models.Repository.Converter;

import Service.Notificacion.Correo.CorreoAdapter;
import Service.Notificacion.Notificacion;
import Service.Notificacion.SMS.SmsAdapter;
import Service.Notificacion.Telegram.TelegramAdapter;
import Service.Notificacion.Whatsapp.WhatsappAdapter;
import jakarta.persistence.AttributeConverter;

public class MedioDeNotificacionAtributeConverter  implements AttributeConverter<Notificacion,String> {


    @Override
    public String convertToDatabaseColumn(Notificacion notificacion) {
        String nombreMedioDeComunicacion = null;

        switch (notificacion.getClass().getSimpleName()){
            case "WhatsappAdapter" : nombreMedioDeComunicacion = "Whatsapp"; break;
            case "TelegramAdapter" : nombreMedioDeComunicacion = "Telegram"; break;
            case "SmsAdapter" : nombreMedioDeComunicacion = "SMS"; break;
            case "CorreoAdapter" : nombreMedioDeComunicacion = "Correo"; break;

        }
        return nombreMedioDeComunicacion;
    }

    @Override
    public Notificacion convertToEntityAttribute(String s) {
        Notificacion medioNotificacion = null;
        switch (s){
            case "Whatsapp": medioNotificacion = new WhatsappAdapter(); break;
            case "Correo": medioNotificacion = new CorreoAdapter(); break;
            case "SMS": medioNotificacion = new SmsAdapter(); break;
            case "Telegram": medioNotificacion = new TelegramAdapter(); break;
            default: medioNotificacion = new CorreoAdapter(); break;
        }
        return medioNotificacion;
    }

}
