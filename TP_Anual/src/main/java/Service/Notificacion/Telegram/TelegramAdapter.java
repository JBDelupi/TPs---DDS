package Service.Notificacion.Telegram;

import Service.Notificacion.Mensaje.Mensaje;
import Service.Notificacion.Notificacion;

public class TelegramAdapter implements Notificacion {
    private AdapterBotTelegram adapter;

    public TelegramAdapter(){
        this.adapter = new AdapterBotTelegram();
    }

    public void Notificar(Mensaje mensaje) {
        adapter.Notificar(mensaje.getDestinatario(), mensaje.getContenido());
    }
}
