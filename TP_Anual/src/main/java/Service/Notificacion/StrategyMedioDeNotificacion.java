package Service.Notificacion;

import Service.Notificacion.Correo.CorreoAdapter;
import Service.Notificacion.SMS.SmsAdapter;
import Service.Notificacion.Telegram.TelegramAdapter;
import Service.Notificacion.Whatsapp.WhatsappAdapter;

public class StrategyMedioDeNotificacion {

    public static Notificacion strategy(String s){
        return switch (s) {
            case "whatsapp" -> new WhatsappAdapter();
            case "correo" -> new CorreoAdapter();
            case "sms" -> new SmsAdapter();
            case "telegram" -> new TelegramAdapter();
            default -> null;
        };
    }
}
