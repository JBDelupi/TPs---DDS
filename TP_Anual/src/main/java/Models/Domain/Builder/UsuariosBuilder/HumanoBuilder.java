package Models.Domain.Builder.UsuariosBuilder;


import Models.Domain.Builder.TarjetaBuilder;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Domain.Tarjetas.Tarjeta;
import Models.Domain.Tarjetas.TarjetaAccesos;
import Service.Notificacion.Correo.CorreoAdapter;
import Service.Notificacion.Mensaje;
import Service.Notificacion.Notificacion;

import java.time.LocalDate;
import java.util.random.RandomGenerator;

public class HumanoBuilder {
    private Humano humano;

    public HumanoBuilder(){
        this.humano = new Humano();
    }

    public HumanoBuilder nombre(String nombre){
        this.humano.setNombre(nombre);
        return this;
    }

    public HumanoBuilder apellido(String apellido){
        this.humano.setApellido(apellido);
        return this;
    }

    public HumanoBuilder fechaNacimiento(LocalDate fechaNacimiento){
        this.humano.setFechaNacimiento(fechaNacimiento);
        return this;
    }

    public HumanoBuilder correoElectronico(String correo){
        this.humano.setCodigoDeNotificacion(correo);
        return this;
    }

    public HumanoBuilder numeroDocumento(String documento){
        this.humano.setNumeroDocumento(documento);
        return this;
    }

    public HumanoBuilder tipoDocumento(TipoDeDocumento documento){
        this.humano.setTipoDeDocumento(documento);
        return this;
    }


    public Humano construir(){

        TarjetaAccesos tarjetaAccesos = new TarjetaAccesos(humano);
        tarjetaAccesos.setCodigo(String.valueOf(RandomGenerator.getDefault().nextInt(0,100000)));
        humano.setTarjeta(tarjetaAccesos);
        Mensaje mensaje = new Mensaje();
        mensaje.setContenido("BIENVENIDO NUEVO CONTRIBUYENTE TU TARJETA ES: " + tarjetaAccesos.getCodigo());
        mensaje.setDestinatario(humano.getCodigoDeNotificacion());
        mensaje.setAsunto("BIENVENIDO A DECCO CONTRIBUCIONES");
        Notificacion correo = new CorreoAdapter();
        correo.Notificar(mensaje);


        return this.humano;
    }

}
