package Service.Notificacion.Correo;

import Service.Notificacion.Mensaje.Mensaje;
import Service.Notificacion.Mensaje.MensajeBienvenida;
import Service.Notificacion.Notificacion;

public class CorreoAdapter implements Notificacion {

    private AdapterJavaxMail adapter;

    public CorreoAdapter() {
        this.adapter = new AdapterJavaxMail();
    }

    public void Notificar(Mensaje mensaje){
        adapter.enviar(mensaje.getDestinatario(), mensaje.getAsunto(), mensaje.getContenido());
    }

}
