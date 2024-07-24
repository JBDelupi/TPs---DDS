package Service.Notificacion;

public class MensajeBuilder {
  private Mensaje  mensaje;


  public MensajeBuilder() {
      mensaje = new Mensaje();
  }

  public MensajeBuilder contenido(String contenido){
      this.mensaje.setContenido(contenido);
      return this;
  }

    public MensajeBuilder destinatario(String destinatario){
        this.mensaje.setDestinatario(destinatario);
        return this;
    }

    public MensajeBuilder remitente(String remitente){
        this.mensaje.setRemitente(remitente);
        return this;
    }

    public MensajeBuilder asunto(String asunto){
        this.mensaje.setAsunto(asunto);
        return this;
    }


    public Mensaje construir(){
      // Lanza excepcion si no cumple con el remitente y destinarario
      return mensaje;
  }



}
