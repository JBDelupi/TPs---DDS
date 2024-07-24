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

        heladera = new Heladera();

    }
    @Test
    public void PrimeraSubscripcion(){
        ObserverHeladera subscriptor = new FaltanNViandasParaLlenar(lucas,1);
        heladera.agregarSubscriptor(subscriptor);

        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }

    @Test
    public void LeInteresaQueLeNotifiquenNViandasDisponibles(){
        ObserverHeladera subscriptor = new NViandasDisponibles(lucas,0);

        heladera.agregarSubscriptor(subscriptor);

        heladera.setCapacidadDeViandas(5);
        heladera.setAbierto(true);
        heladera.agregarVianda(new Vianda());

        Vianda vianda = heladera.obtenerVianda();

        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }

    @Test
    public void NoLeInteresaQueLeNotifiquenNviandasDisponibles(){
        ObserverHeladera subscriptor = new NViandasDisponibles(lucas,1);
//
        heladera.agregarSubscriptor(subscriptor);

        heladera.setCapacidadDeViandas(5);
        heladera.setAbierto(true);

        heladera.agregarVianda(new Vianda());
        heladera.agregarVianda(new Vianda());
        heladera.agregarVianda(new Vianda());
        heladera.agregarVianda(new Vianda());

        Vianda vianda = heladera.obtenerVianda();

        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }

    @Test
    public void LeInteresaQueLeNotifiquenPublicacionesNviandasParaLlenar(){
        ObserverHeladera subscriptor = new FaltanNViandasParaLlenar(lucas,1);
        heladera.agregarSubscriptor(subscriptor);

        heladera.setCapacidadDeViandas(2);
        heladera.setAbierto(true);
        heladera.agregarVianda(new Vianda());

        Vianda vianda = heladera.obtenerVianda();

        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }

    @Test
    public void NoLeInteresaQueLeNotifiquenPublicacionesNviandasParaLlenar(){
        ObserverHeladera subscriptor = new FaltanNViandasParaLlenar(lucas,2);
        heladera.agregarSubscriptor(subscriptor);


        heladera.setCapacidadDeViandas(5);
        heladera.setAbierto(true);
        heladera.agregarVianda(new Vianda());


        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }


    @Test
    public void LeInteresaQueLeNotifiqueHeladeraSeRompio(){

        ObserverHeladera subscriptor = new SufrioDesperfecto(lucas);
        heladera.agregarSubscriptor(subscriptor);


        FallaTecnica unaFalla = new FallaTecnica(heladera,lucas);


        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }


}
