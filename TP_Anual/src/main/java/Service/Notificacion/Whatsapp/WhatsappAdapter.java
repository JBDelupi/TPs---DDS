package Service.Notificacion.Whatsapp;

import Service.Notificacion.Mensaje.Mensaje;
import Service.Notificacion.Notificacion;


public class WhatsappAdapter implements Notificacion{
    private AdapterBotWhatsapp adapter;

    public WhatsappAdapter(){
        this.adapter = new AdapterBotWhatsapp();
    }

    public void Notificar(Mensaje mensaje) {
        adapter.Notificar(mensaje.getDestinatario(), mensaje.getContenido());
    }
}
