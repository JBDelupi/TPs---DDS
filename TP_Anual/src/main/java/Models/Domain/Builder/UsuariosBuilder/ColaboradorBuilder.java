package Models.Domain.Builder.UsuariosBuilder;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Personas.Actores.*;
import Models.Domain.Tarjetas.TarjetaAccesos;
import Service.Notificacion.Correo.CorreoAdapter;
import Service.Notificacion.Mensaje;
import Service.Notificacion.Notificacion;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class ColaboradorBuilder {
    private Colaborador colaborador;

    public ColaboradorBuilder(){this.colaborador = new Colaborador();}

    public Colaborador construir(Persona persona) {
        colaborador.setContribuciones(new ArrayList<>());
        colaborador.setPuntaje(0.0);
        colaborador.setHistorialCanje(new ArrayList<>());

        if (persona instanceof Fisico) {
            TarjetaAccesos tarjetaAccesos = new TarjetaAccesos(persona);
            tarjetaAccesos.setCodigo(String.valueOf(RandomGenerator.getDefault().nextInt(0, 100000)));
            colaborador.setTarjeta(tarjetaAccesos);
            Mensaje mensaje = new Mensaje();
            mensaje.setContenido("BIENVENIDO NUEVO CONTRIBUYENTE TU TARJETA ES: " + tarjetaAccesos.getCodigo());
            mensaje.setDestinatario(persona.getCorreElectronico());
            mensaje.setAsunto("BIENVENIDO A DECCO CONTRIBUCIONES");
            Notificacion correo = new CorreoAdapter();
            correo.Notificar(mensaje);
        }

        return this.colaborador;
    }
}
