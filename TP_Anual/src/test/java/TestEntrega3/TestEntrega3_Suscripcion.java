package TestEntrega3;

/*
public class TestEntrega3_Suscripcion {
    Humano lucas;
    Humano tobi;
    Humano bauti;
    Heladera heladera;

    @BeforeEach
    public void init(){
        HumanoBuilder humanoBuilder1 = new HumanoBuilder();


        lucas = humanoBuilder1
                .nombre("Lucas")
                .apellido("Martinez")
                .correoElectronico("2067757786")
                .construir();
                lucas.setMedioDeNotificacion(new TelegramAdapter());

        bauti = humanoBuilder1
                .nombre("Bauti")
                .apellido("Delupi")
                .correoElectronico("1738213090")
                .construir();
                bauti.setMedioDeNotificacion(new TelegramAdapter());


        tobi = humanoBuilder1
                .nombre("Tobi")
                .apellido("Duren")
                .correoElectronico("541156383089")
                .construir();
                tobi.setMedioDeNotificacion(new SmsAdapter());

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
        heladera.setCapacidadDeViandas(5);


        heladera.agregarVianda(new Vianda());
        heladera.agregarVianda(new Vianda());
        heladera.agregarVianda(new Vianda());

        FallaTecnica unaFalla = new FallaTecnica(heladera,lucas);


        Assertions.assertEquals(1, heladera.getSubscriptores().size());
    }


}
*/