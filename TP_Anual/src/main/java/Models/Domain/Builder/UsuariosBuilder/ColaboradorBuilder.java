package Models.Domain.Builder.UsuariosBuilder;

import Models.Domain.Personas.Actores.*;
import Models.Domain.Tarjetas.TarjetaAccesos;
import Service.Notificacion.Correo.CorreoAdapter;
import Service.Notificacion.Mensaje.Mensaje;
import Service.Notificacion.Mensaje.MensajeBienvenida;
import Service.Notificacion.Notificacion;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class ColaboradorBuilder {
    private Colaborador colaborador;

    public ColaboradorBuilder(){this.colaborador = new Colaborador();}

    public Colaborador construir(Persona persona) {
        if (persona instanceof Fisico) {

        }
        return this.colaborador;
    }
}
