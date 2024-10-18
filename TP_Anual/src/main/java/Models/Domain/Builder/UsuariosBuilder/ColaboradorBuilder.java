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
        colaborador.setContribuciones(new ArrayList<>());
        colaborador.setPuntaje(0.0);
        colaborador.setHistorialCanje(new ArrayList<>());

        if (persona instanceof Fisico) {
            TarjetaAccesos tarjetaAccesos = new TarjetaAccesos(persona);
            tarjetaAccesos.setCodigo(String.valueOf(RandomGenerator.getDefault().nextInt(0, 100000)));
            colaborador.setTarjeta(tarjetaAccesos);
            new MensajeBienvenida(persona.getCorreElectronico(), tarjetaAccesos.getCodigo());
        }

        return this.colaborador;
    }
}
