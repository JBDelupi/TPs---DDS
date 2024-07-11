package TestEntrega2;

/*
public class TestEntrega2_Tarjeta {
    Colaborador fulano;
    Colaborador nasa;
    PersonaVulnerable persona1;
    Tarjeta tarjeta1;
    FactoryContribucion controller;

    Vianda pollo;
    Vianda galletita;
    Vianda carne;
    Vianda salchicha;

    Heladera heladera;


//--------------------------------- Forma de contribución "Entrega Tarjeta" ----------------------------

    @BeforeEach
    public void init(){
        fulano = new Humano();
        nasa = new Juridico();

        persona1 = new PersonaVulnerable("Persona1", 0);
        tarjeta1 = new Tarjeta((Humano) fulano, persona1);

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
        controller = new FactoryContribucion(fulano);
        controller.create(TipoDonacion.ENTREGA_TARJETAS, "PersonaVulnerable", 3);
        Assertions.assertEquals(1, fulano.getFormaDeContribucion().size());
    }


    // Se crea una persona vulnerable
    @Test
    public void registroDePersonaVulnerable() throws IOException {
        controller = new FactoryContribucion(fulano);
        controller.create(TipoDonacion.ENTREGA_TARJETAS, "PersonaVulnerable", 3);

        FormaDeContribucion contribucion = fulano.getFormaDeContribucion().get(0);

        // Verifica si la contribución es del tipo que tiene una tarjeta
        if (contribucion instanceof EntregaDeTarjeta) {
            EntregaDeTarjeta contribucionConTarjeta = (EntregaDeTarjeta) contribucion;
            String nombre = contribucionConTarjeta.getTarjeta().getTitular().getNombre();
            Assertions.assertEquals("PersonaVulnerable", nombre);
        } else {
            Assertions.fail("La contribución no es del tipo ContribucionConTarjeta");
        }
    }

    // Tira error si una juridica trata de entregar tarjetas
    @Test
    public void unaPersonaJuridicaNoPuedeEntregarTarjeta()throws IOException {
        controller = new FactoryContribucion(nasa);
        Assertions.assertThrows(FactoryContribucion.UnauthorizedAccessException.class, () -> {
            controller.create(TipoDonacion.ENTREGA_TARJETAS, null, null);
        });
    }

    //--------------------------------- uso de una tarjeta ----------------------------

    //La cantidad de usos no se sigue sumando si se alcanza la cantidad maxima de usos
    @Test
    public void cantidadDeUsosMaximaAlcanzada(){
        heladera.setAbierto(true);

        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);


        Assertions.assertEquals(4, tarjeta1.getUsos().size());
    }

    // Se reinicia la cantidad de usos tras un nuevo dia
    @Test
    public void reinicioDeUsos() {
        heladera.setAbierto(true);

        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);
        tarjeta1.agregarNuevoUso(heladera);

        LocalDate maniana = LocalDate.now().plusDays(1);
        tarjeta1.setFechaUltUso(maniana);

        tarjeta1.agregarNuevoUso(heladera);


        Assertions.assertEquals(1, tarjeta1.getUsosHoy());
    }

}
*/