package Service.Notificacion;

public class NotificacionBuilder {
  private Mensaje  mensaje;


  public NotificacionBuilder() {
      mensaje = new Mensaje();
  }

  public NotificacionBuilder contenido(String contenido){
      this.mensaje.setContenido(contenido);
      return this;
  }

    public NotificacionBuilder destinatario(String destinatario){
        this.mensaje.setDestinatario(destinatario);
        return this;
    }

    public NotificacionBuilder remitente(String remitente){
        this.mensaje.setRemitente(remitente);
        return this;
    }

    public NotificacionBuilder asunto(String asunto){
        this.mensaje.setAsunto(asunto);
        return this;
    }


    public Mensaje construir(){
      // Lanza excepcion si no cumple con el remitente y destinarario
      return mensaje;
  }



}
