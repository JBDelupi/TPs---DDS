package TestEntrega2;

import Controller.ContribucionController;
import Models.Domain.FormasDeContribucion.EntregaDeTarjeta;
import Models.Domain.FormasDeContribucion.FormaDeContribucion;
import Models.Domain.FormasDeContribucion.TipoDonacion;
import Models.Domain.Heladera;
import Models.Domain.Personas.Colaborador;
import Models.Domain.Personas.Humano;
import Models.Domain.Personas.Juridico;
import Models.Domain.Personas.PersonaVulnerable;
import Models.Domain.Tarjeta.Tarjeta;
import Models.Domain.TipoJuridico;
import Models.Domain.Vianda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

public class TestEntrega2_Tarjeta {
    Colaborador fulano;
    Colaborador nasa;
    PersonaVulnerable persona1;
    Tarjeta tarjeta1;
    ContribucionController controllerHumano;
    ContribucionController controllerJuridico;

//--------------------------------- Forma de contribución "Entrega Tarjeta" ----------------------------
    @BeforeEach
    public void init(){
        fulano = new Humano("Fulano", "Mengano");
        nasa = new Juridico("nasa", TipoJuridico.GUBERNAMENTAL, null, null);
        persona1 = new PersonaVulnerable("Persona1", 0);
        controllerHumano = new ContribucionController(fulano);
        controllerJuridico = new ContribucionController(nasa);
        tarjeta1 = new Tarjeta((Humano) fulano, persona1);
    }

    // Se crea correctamente la entrega de tarjetas
    @Test
    public void creacionDeEntregaTarjetas()throws IOException {
        controllerHumano.create(TipoDonacion.ENTREGA_TARJETAS, "Persona1", 3);
        Assertions.assertEquals(1, fulano.getFormaDeContribucion().size());
    }

    // Se crea una persona vulnerable
    @Test
    public void registroDePersonaVulnerable() throws IOException {
        controllerHumano.create(TipoDonacion.ENTREGA_TARJETAS, "Persona1", 3);
        FormaDeContribucion contribucion = fulano.getFormaDeContribucion().get(0);

        // Verifica si la contribución es del tipo que tiene una tarjeta
        if (contribucion instanceof EntregaDeTarjeta) {
            EntregaDeTarjeta contribucionConTarjeta = (EntregaDeTarjeta) contribucion;
            String nombre = contribucionConTarjeta.getTarjeta().getTitular().getNombre();
            Assertions.assertEquals("Persona1", nombre);
        } else {
            Assertions.fail("La contribución no es del tipo ContribucionConTarjeta");
        }
    }

    // Tira error si una juridica trata de entregar tarjetas
    @Test
    public void unaPersonaJuridicaNoPuedeEntregarTarjeta()throws IOException {
        Assertions.assertThrows(ContribucionController.UnauthorizedAccessException.class, () -> {
            controllerJuridico.create(TipoDonacion.ENTREGA_TARJETAS, null, null ,null ,null);
        });
    }

    //--------------------------------- uso de una tarjeta ----------------------------

    //La cantidad de usos no se sigue sumando si se alcanza la cantidad maxima de usos
    @Test
    public void cantidadDeUsosMaximaAlcanzada(){
        Heladera heladera = new Heladera();
        heladera.setCapacidadDeViandas(5);
        heladera.setAbierto(true);

        Vianda vianda = new Vianda();
        vianda.setNombre("Pollo");
        Vianda vianda2 = new Vianda();
        vianda2.setNombre("Galletita");
        Vianda vianda3 = new Vianda();
        vianda3.setNombre("Carne");
        Vianda vianda4 = new Vianda();
        vianda4.setNombre("Salchicha");

        heladera.agregarVianda(vianda);
        heladera.agregarVianda(vianda2);
        heladera.agregarVianda(vianda3);
        heladera.agregarVianda(vianda4);

        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);


        Assertions.assertEquals(4, tarjeta1.getUsos().size());
    }

    // Se reinicia la cantidad de usos tras un nuevo dia
    @Test
    public void reinicioDeUsos(){
        Heladera heladera = new Heladera();
        heladera.setCapacidadDeViandas(5);
        heladera.setAbierto(true);

        Vianda vianda = new Vianda();
        vianda.setNombre("Pollo");
        Vianda vianda2 = new Vianda();
        vianda2.setNombre("Galletita");
        Vianda vianda3 = new Vianda();
        vianda3.setNombre("Carne");
        Vianda vianda4 = new Vianda();
        vianda4.setNombre("Salchicha");

        heladera.agregarVianda(vianda);
        heladera.agregarVianda(vianda2);
        heladera.agregarVianda(vianda3);
        heladera.agregarVianda(vianda4);

        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);
        LocalDate maniana = LocalDate.now().plusDays(1);
        tarjeta1.setFechaUltUso(maniana);
        tarjeta1.agregarNuevoUso(heladera);
        System.out.println(tarjeta1.getUsos().get(3).getVianda());
        Assertions.assertEquals(1, tarjeta1.getUsosHoy());


    }
}
