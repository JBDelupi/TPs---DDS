package TestEntrega2;


import Controller.ContribucionController;
import Models.Domain.Excepciones.LimiteDeTarjetaException;
import Models.Domain.Excepciones.Permisos;
import Models.Domain.Excepciones.SinViandasException;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.EntregaDeTarjeta;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Vianda;
import Models.Domain.Personas.Actores.*;
import Models.Domain.Tarjetas.TarjetaAlimentar;
import Models.Domain.Tarjetas.TipoAccion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
/*
public class TestEntrega2_Tarjeta {
    Colaborador fulano;
    Colaborador nasa;
    PersonaVulnerable persona1;
    TarjetaAlimentar tarjeta1;
    ContribucionController controller;



    Vianda pollo;
    Vianda galletita;
    Vianda carne;
    Vianda salchicha;

    Heladera heladera;


//--------------------------------- Forma de contribución "Entrega Tarjeta" ----------------------------

    @BeforeEach
    public void init(){
        fulano = new Fisico();
        nasa = new Juridico();

        persona1 = new PersonaVulnerable();
        persona1.setMenoresACargo(0);
        persona1.setNombre("PersonaVulnerable");

        tarjeta1 = new TarjetaAlimentar(persona1);
        tarjeta1.setColaborador((Fisico) fulano);

        pollo = new Vianda();
        galletita = new Vianda();
        carne = new Vianda();
        salchicha = new Vianda();

        heladera = new Heladera();
        heladera.setCapacidadDeViandas(5);

        heladera.agregarVianda(pollo , galletita, carne, salchicha);
    }

    // Se crea correctamente la entrega de tarjetas
    @Test
    public void creacionDeEntregaTarjetas()throws IOException {
        controller = new ContribucionController(fulano);
        controller.save(TipoDonacion.ENTREGA_TARJETAS, "PersonaVulnerable", 3);
        Assertions.assertEquals(1, fulano.getContribucion().size());
    }


    // Se crea una persona vulnerable
    @Test
    public void registroDePersonaVulnerable() throws IOException {
        controller = new ContribucionController(fulano);
        controller.save(TipoDonacion.ENTREGA_TARJETAS, "PersonaVulnerable", 3);

        Contribucion contribucion = fulano.getContribucion().get(0);

        // Verifica si la contribución es del tipo que tiene una tarjeta
        if (contribucion instanceof EntregaDeTarjeta) {
            EntregaDeTarjeta contribucionConTarjeta = (EntregaDeTarjeta) contribucion;
            PersonaVulnerable persona = (PersonaVulnerable) contribucionConTarjeta.getTarjetaAlimentar().getTitular();
            Assertions.assertEquals("PersonaVulnerable", persona.getNombre());
        } else {
            Assertions.fail("La contribución no es del tipo ContribucionConTarjeta");
        }
    }

    // Tira error si una juridica trata de entregar tarjetas
    @Test
    public void unaPersonaJuridicaNoPuedeEntregarTarjeta()throws IOException {
        controller = new ContribucionController(nasa);
        Assertions.assertThrows(Permisos.UnauthorizedAccessException.class, () -> {
            controller.save(TipoDonacion.ENTREGA_TARJETAS, null, null);
        });
    }

    //--------------------------------- uso de una tarjeta ----------------------------

    //La cantidad de usos no se sigue sumando si se alcanza la cantidad maxima de usos
    @Test
    public void cantidadDeUsosMaximaAlcanzada(){
        heladera.setAbierto(true);

        tarjeta1.agregarNuevoUso(heladera, TipoAccion.QUITAR);
        tarjeta1.agregarNuevoUso(heladera,TipoAccion.QUITAR);
        tarjeta1.agregarNuevoUso(heladera,TipoAccion.QUITAR);
        tarjeta1.agregarNuevoUso(heladera,TipoAccion.QUITAR);

        Assertions.assertThrows(LimiteDeTarjetaException.class, () -> {
            tarjeta1.agregarNuevoUso(heladera,TipoAccion.QUITAR);
        });
    }

    // Se reinicia la cantidad de usos tras un nuevo dia
    @Test
    public void reinicioDeUsosPeroNoHayMasViandasEnLaHeladera() {
        heladera.setAbierto(true);

        tarjeta1.agregarNuevoUso(heladera,TipoAccion.QUITAR);
        tarjeta1.agregarNuevoUso(heladera,TipoAccion.QUITAR);
        tarjeta1.agregarNuevoUso(heladera,TipoAccion.QUITAR);

        LocalDate maniana = LocalDate.now().plusDays(1);
        tarjeta1.setFechaUltUso(maniana);

        Assertions.assertThrows(SinViandasException.class, () -> {
            tarjeta1.agregarNuevoUso(heladera,TipoAccion.QUITAR);
        });


    }

    @Test
    public void reinicioDeUsos(){
        heladera.setAbierto(true);

        tarjeta1.agregarNuevoUso(heladera,TipoAccion.QUITAR);
        tarjeta1.agregarNuevoUso(heladera,TipoAccion.QUITAR);
        tarjeta1.agregarNuevoUso(heladera,TipoAccion.QUITAR);

        LocalDate maniana = LocalDate.now().plusDays(1);
        tarjeta1.setFechaUltUso(maniana);

        tarjeta1.agregarNuevoUso(heladera,TipoAccion.QUITAR);

        Assertions.assertEquals(4, tarjeta1.getUsos().size());

    }




}
*/