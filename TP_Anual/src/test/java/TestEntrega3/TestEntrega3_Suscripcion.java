package TestEntrega3;

import Models.Domain.Builder.HeladeraBuilder;
import Models.Domain.Builder.UsuariosBuilder.HumanoBuilder;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Domain.Heladera.Suscripciones.*;
import Models.Domain.Heladera.Vianda;
import Models.Domain.Personas.Actores.Humano;
import Service.APIPuntos.Punto;
import Service.Notificacion.CorreoAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEntrega3_Suscripcion {
    Humano lucas;
    ObserverHeladera subscriptor;
    Heladera heladera;

    @BeforeEach
    public void init(){
        HumanoBuilder humanoBuilder = new HumanoBuilder();

        lucas = humanoBuilder
                .nombre("Lucas")
                .apellido("martinez")
                .correoElectronico("lucasiturrioz212@gmail.com")
                .construir();
        lucas.setMedioDeNotificacion(new CorreoAdapter());

        HeladeraBuilder heladeraBuilder = new HeladeraBuilder();

        heladera = new Heladera();

        subscriptor = new SubscriptorHeladera(lucas);
    }
    @Test
    public void PrimeraSubscripcion(){
        heladera.agregarSubscriptor(subscriptor);
        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }

    @Test
    public void LeInteresaQueLeNotifiquenNViandasDisponibles(){
        heladera.agregarSubscriptor(subscriptor);

        Publicacion unaPublicacion = new PublicacionNViandasDisponibles(0);

        subscriptor.agregarPublicacion(unaPublicacion);
        heladera.setCapacidadDeViandas(5);
        heladera.setAbierto(true);
        heladera.agregarVianda(new Vianda());

        Vianda vianda = heladera.obtenerVianda();

        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }

    @Test
    public void NoLeInteresaQueLeNotifiquenNviandasDisponibles(){
        heladera.agregarSubscriptor(subscriptor);

        Publicacion unaPublicacion = new PublicacionNViandasDisponibles(1);

        subscriptor.agregarPublicacion(unaPublicacion);
        heladera.setCapacidadDeViandas(5);
        heladera.setAbierto(true);
        heladera.agregarVianda(new Vianda());

        Vianda vianda = heladera.obtenerVianda();

        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }

    @Test
    public void LeInteresaQueLeNotifiquenPublicacionesNviandasParaLlenar(){
        heladera.agregarSubscriptor(subscriptor);

        Publicacion unaPublicacion = new PublicacionFaltanNViandasParaLLena(1);

        subscriptor.agregarPublicacion(unaPublicacion);
        heladera.setCapacidadDeViandas(2);
        heladera.setAbierto(true);
        heladera.agregarVianda(new Vianda());

        Vianda vianda = heladera.obtenerVianda();

        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }

    @Test
    public void NoLeInteresaQueLeNotifiquenPublicacionesNviandasParaLlenar(){
        heladera.agregarSubscriptor(subscriptor);

        Publicacion unaPublicacion = new PublicacionFaltanNViandasParaLLena(2);

        subscriptor.agregarPublicacion(unaPublicacion);
        heladera.setCapacidadDeViandas(2);
        heladera.setAbierto(true);
        heladera.agregarVianda(new Vianda());


        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }


    @Test
    public void LeInteresaQueLeNotifiqueHeladeraSeRompio(){
        heladera.agregarSubscriptor(subscriptor);

        Publicacion unaPublicacion = new PublicacionSufrioDesperfecto();
        subscriptor.agregarPublicacion(unaPublicacion);




        Punto punto = new Punto();
        punto.setLatitud("7");
        punto.setLongitud("7");

        Direccion direccion = new Direccion();
        direccion.setCentro(punto);

        heladera.setDireccion(direccion);

        FallaTecnica unaFalla = new FallaTecnica(heladera,lucas);


        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }


}
