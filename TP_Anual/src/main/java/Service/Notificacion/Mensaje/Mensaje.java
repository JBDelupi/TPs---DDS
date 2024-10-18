package Service.Notificacion.Mensaje;

public interface Mensaje {
     void generarMensaje(String contenido);
     String getDestinatario();
     String getAsunto();
     String getContenido();


}
