package Service.Notificacion.SMS;

import Service.Notificacion.Mensaje.Mensaje;
import Service.Notificacion.Notificacion;

public class SmsAdapter implements Notificacion {
    private AdapterVonage adapter;

    public SmsAdapter(){
        this.adapter = new AdapterVonage();
    }

    public void Notificar(Mensaje mensaje) {
        adapter.Notificar(mensaje.getDestinatario(), mensaje.getContenido());

    }
}
