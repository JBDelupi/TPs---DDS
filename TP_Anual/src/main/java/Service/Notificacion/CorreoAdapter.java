package Service.Notificacion;

public class CorreoAdapter implements Notificacion {

    private AdapterJavaxMail adapter;

    public CorreoAdapter() {
        this.adapter = new AdapterJavaxMail();
    }

    public void Notificar(Mensaje mensaje){
        adapter.enviar(mensaje.getDestinatario(), mensaje.getAsunto(), mensaje.getContenido());
    }

}
